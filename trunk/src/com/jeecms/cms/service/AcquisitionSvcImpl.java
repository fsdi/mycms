/*     */ package com.jeecms.cms.service;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition.AcquisitionResultType;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentCount;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionHistoryMng;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionMng;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionTempMng;
/*     */ import com.jeecms.cms.manager.main.CmsConfigMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.ContentCountMng;
/*     */ import com.jeecms.common.file.FileNameUtils;
/*     */ import com.jeecms.common.upload.UploadUtils;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.HttpResponseException;
/*     */ import org.apache.http.client.ResponseHandler;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service
/*     */ public class AcquisitionSvcImpl
/*     */   implements AcquisitionSvc
/*     */ {
/*  52 */   private Logger log = LoggerFactory.getLogger(AcquisitionSvcImpl.class);
/*     */   private CmsAcquisitionMng cmsAcquisitionMng;
/*     */   private CmsAcquisitionHistoryMng cmsAcquisitionHistoryMng;
/*     */   private CmsAcquisitionTempMng cmsAcquisitionTempMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteMng siteMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsConfigMng cmsConfigMng;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Autowired
/*     */   private ContentCountMng contentCountMng;
/*     */ 
/*     */   public boolean start(Integer id)
/*     */   {
/*  55 */     CmsAcquisition acqu = this.cmsAcquisitionMng.findById(id);
/*  56 */     if ((acqu == null) || (acqu.getStatus().intValue() == 1)) {
/*  57 */       return false;
/*     */     }
/*  59 */     Thread thread = new AcquisitionThread(acqu);
/*  60 */     thread.start();
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   private void end(CmsAcquisition acqu) {
/*  65 */     Integer siteId = acqu.getSite().getId();
/*  66 */     this.cmsAcquisitionMng.end(acqu.getId());
/*  67 */     CmsAcquisition acquisition = this.cmsAcquisitionMng.popAcquFromQueue(siteId);
/*  68 */     if (acquisition != null) {
/*  69 */       Integer id = acquisition.getId();
/*  70 */       start(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsAcquisitionMng(CmsAcquisitionMng cmsAcquisitionMng)
/*     */   {
/*  88 */     this.cmsAcquisitionMng = cmsAcquisitionMng;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsAcquisitionHistoryMng(CmsAcquisitionHistoryMng cmsAcquisitionHistoryMng) {
/*  94 */     this.cmsAcquisitionHistoryMng = cmsAcquisitionHistoryMng;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsAcquisitionTempMng(CmsAcquisitionTempMng cmsAcquisitionTempMng) {
/* 100 */     this.cmsAcquisitionTempMng = cmsAcquisitionTempMng;
/*     */   }
/*     */ 
/*     */   private CmsAcquisitionTemp newTemp(String channelUrl, String contentUrl, Integer id, Float curr, Float total, CmsSite site)
/*     */   {
/* 460 */     CmsAcquisitionTemp temp = new CmsAcquisitionTemp();
/* 461 */     temp.setChannelUrl(channelUrl);
/* 462 */     temp.setContentUrl(contentUrl);
/* 463 */     temp.setSeq(id);
/* 464 */     NumberFormat nf = NumberFormat.getPercentInstance();
/* 465 */     String percent = nf.format(curr.floatValue() / total.floatValue());
/* 466 */     temp.setPercent(
/* 467 */       Integer.valueOf(Integer.parseInt(percent
/* 467 */       .substring(0, 
/* 467 */       percent.length() - 1))));
/* 468 */     temp.setSite(site);
/* 469 */     return temp;
/*     */   }
/*     */ 
/*     */   private CmsAcquisitionHistory newHistory(String channelUrl, String contentUrl, CmsAcquisition acqu)
/*     */   {
/* 474 */     CmsAcquisitionHistory history = new CmsAcquisitionHistory();
/* 475 */     history.setChannelUrl(channelUrl);
/* 476 */     history.setContentUrl(contentUrl);
/* 477 */     history.setAcquisition(acqu);
/* 478 */     return history;
/*     */   }
/*     */ 
/*     */   private class AcquisitionThread extends Thread
/*     */   {
/*     */     private CmsAcquisition acqu;
/*     */ 
/*     */     public AcquisitionThread(CmsAcquisition acqu)
/*     */     {
/* 107 */       super();
/* 108 */       this.acqu = acqu;
/*     */     }
/*     */ 
/*     */     public void run()
/*     */     {
/* 113 */       if (this.acqu == null) {
/* 114 */         return;
/*     */       }
/* 116 */       this.acqu = AcquisitionSvcImpl.this.cmsAcquisitionMng.start(this.acqu.getId());
/* 117 */       String[] plans = this.acqu.getAllPlans();
/* 118 */       HttpClient client = new DefaultHttpClient();
/* 119 */       CharsetHandler handler = new CharsetHandler(this.acqu.getPageEncoding());
/*     */ 
/* 122 */       int currNum = this.acqu.getCurrNum().intValue();
/* 123 */       int currItem = this.acqu.getCurrItem().intValue();
/* 124 */       Integer acquId = this.acqu.getId();
/* 125 */       for (int i = plans.length - currNum; i >= 0; i--) {
/* 126 */         String url = plans[i];
/* 127 */         List contentList = getContentList(client, handler, url, this.acqu);
/*     */ 
/* 129 */         for (int j = contentList.size() - currItem; j >= 0; j--) {
/* 130 */           if (AcquisitionSvcImpl.this.cmsAcquisitionMng.isNeedBreak(this.acqu.getId(), 
/* 131 */             plans.length - i, contentList.size() - j, 
/* 132 */             contentList.size())) {
/* 133 */             client.getConnectionManager().shutdown();
/* 134 */             AcquisitionSvcImpl.this.log.info("Acquisition#{} breaked", this.acqu.getId());
/* 135 */             return;
/*     */           }
/* 137 */           if (this.acqu.getPauseTime().intValue() > 0) {
/*     */             try {
/* 139 */               Thread.sleep(this.acqu.getPauseTime().intValue());
/*     */             } catch (InterruptedException e) {
/* 141 */               AcquisitionSvcImpl.this.log.warn(null, e);
/*     */             }
/*     */           }
/* 144 */           String link = (String)contentList.get(j);
/* 145 */           float curr = contentList.size() - j;
/* 146 */           float total = contentList.size();
/* 147 */           CmsAcquisitionTemp temp = AcquisitionSvcImpl.this.newTemp(
/* 148 */             url, link, Integer.valueOf(contentList.size() - j), Float.valueOf(curr), Float.valueOf(total), 
/* 149 */             this.acqu.getSite());
/* 150 */           CmsAcquisitionHistory history = AcquisitionSvcImpl.this
/* 151 */             .newHistory(url, link, this.acqu);
/* 152 */           CmsSite site = this.acqu.getSite();
/* 153 */           site = AcquisitionSvcImpl.this.siteMng.findById(site.getId());
/* 154 */           String contextPath = AcquisitionSvcImpl.this.cmsConfigMng.get().getContextPath();
/* 155 */           saveContent(client, handler, contextPath, site.getUploadPath(), acquId, link, temp, 
/* 156 */             history);
/*     */         }
/* 158 */         currItem = 1;
/*     */       }
/* 160 */       client.getConnectionManager().shutdown();
/* 161 */       AcquisitionSvcImpl.this.end(this.acqu);
/* 162 */       AcquisitionSvcImpl.this.log.info("Acquisition#{} complete", this.acqu.getId());
/*     */     }
/*     */ 
/*     */     private List<String> getContentList(HttpClient client, AcquisitionSvcImpl.CharsetHandler handler, String url, CmsAcquisition acqu)
/*     */     {
/* 167 */       String linksetStart = acqu.getLinksetStart();
/* 168 */       String linksetEnd = acqu.getLinksetEnd();
/* 169 */       String linkStart = acqu.getLinkStart();
/* 170 */       String linkEnd = acqu.getLinkEnd();
/* 171 */       List list = new ArrayList();
/*     */       try {
/* 173 */         HttpGet httpget = new HttpGet(new URI(url));
/* 174 */         String base = url.substring(0, url.indexOf("/", 
/* 175 */           url.indexOf("//") + 2));
/* 176 */         String html = (String)client.execute(httpget, handler);
/* 177 */         int start = html.indexOf(linksetStart);
/* 178 */         if (start == -1) {
/* 179 */           return list;
/*     */         }
/* 181 */         start += linksetStart.length();
/* 182 */         int end = html.indexOf(linksetEnd, start);
/* 183 */         if (end == -1) {
/* 184 */           return list;
/*     */         }
/* 186 */         String s = html.substring(start, end);
/* 187 */         start = 0;
/*     */ 
/* 189 */         AcquisitionSvcImpl.this.log.info(s);
/* 190 */         while ((start = s.indexOf(linkStart, start)) != -1) {
/* 191 */           start += linkStart.length();
/* 192 */           end = s.indexOf(linkEnd, start);
/* 193 */           if (end == -1) {
/* 194 */             return list;
/*     */           }
/* 196 */           String link = s.substring(start, end);
/*     */ 
/* 198 */           if (StringUtils.isNotBlank(acqu.getContentPrefix())) {
/* 199 */             link = acqu.getContentPrefix() + link;
/*     */           }
/* 201 */           if (!link.startsWith("http")) {
/* 202 */             link = base + link;
/*     */           }
/* 204 */           AcquisitionSvcImpl.this.log.debug("content link: {}", link);
/* 205 */           list.add(link);
/* 206 */           start = end + linkEnd.length();
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 210 */         AcquisitionSvcImpl.this.log.warn(null, e);
/*     */       }
/* 212 */       return list;
/*     */     }
/*     */ 
/*     */     private Content saveContent(HttpClient client, AcquisitionSvcImpl.CharsetHandler handler, String contextPath, String uploadPath, Integer acquId, String url, CmsAcquisitionTemp temp, CmsAcquisitionHistory history)
/*     */     {
/* 217 */       CmsAcquisition acqu = AcquisitionSvcImpl.this.cmsAcquisitionMng.findById(acquId);
/* 218 */       String titleStart = acqu.getTitleStart();
/* 219 */       String titleEnd = acqu.getTitleEnd();
/* 220 */       String contentStart = acqu.getContentStart();
/* 221 */       String contentEnd = acqu.getContentEnd();
/* 222 */       String viewStart = acqu.getViewStart();
/* 223 */       String viewEnd = acqu.getViewEnd();
/* 224 */       String viewIdStart = acqu.getViewIdStart();
/* 225 */       String viewIdEnd = acqu.getViewIdEnd();
/* 226 */       String viewLink = acqu.getViewLink();
/* 227 */       String authorStart = acqu.getAuthorStart();
/* 228 */       String authorEnd = acqu.getAuthorEnd();
/* 229 */       String originStart = acqu.getOriginStart();
/* 230 */       String originEnd = acqu.getOriginEnd();
/* 231 */       String releaseTimeStart = acqu.getReleaseTimeStart();
/* 232 */       String releaseTimeEnd = acqu.getReleaseTimeEnd();
/* 233 */       String descriptionStart = acqu.getDescriptionStart();
/* 234 */       String descriptionEnd = acqu.getDescriptionEnd();
/* 235 */       history.setAcquisition(acqu);
/*     */       try
/*     */       {
/* 238 */         HttpGet httpget = new HttpGet(new URI(url));
/* 239 */         String html = (String)client.execute(httpget, handler);
/* 240 */         int start = html.indexOf(titleStart);
/* 241 */         if (start == -1) {
/* 242 */           return handerResult(temp, history, null, 
/* 243 */             CmsAcquisition.AcquisitionResultType.TITLESTARTNOTFOUND);
/*     */         }
/* 245 */         start += titleStart.length();
/* 246 */         int end = html.indexOf(titleEnd, start);
/* 247 */         if (end == -1) {
/* 248 */           return handerResult(temp, history, null, 
/* 249 */             CmsAcquisition.AcquisitionResultType.TITLEENDNOTFOUND);
/*     */         }
/* 251 */         String title = html.substring(start, end);
/*     */ 
/* 253 */         if (AcquisitionSvcImpl.this.cmsAcquisitionHistoryMng
/* 253 */           .checkExistByProperties(Boolean.valueOf(true), title).booleanValue()) {
/* 254 */           return handerResult(temp, history, title, 
/* 255 */             CmsAcquisition.AcquisitionResultType.TITLEEXIST, Boolean.valueOf(true));
/*     */         }
/* 257 */         start = html.indexOf(contentStart);
/* 258 */         if (start == -1) {
/* 259 */           return handerResult(temp, history, title, 
/* 260 */             CmsAcquisition.AcquisitionResultType.CONTENTSTARTNOTFOUND);
/*     */         }
/* 262 */         start += contentStart.length();
/* 263 */         end = html.indexOf(contentEnd, start);
/* 264 */         if (end == -1) {
/* 265 */           return handerResult(temp, history, title, 
/* 266 */             CmsAcquisition.AcquisitionResultType.CONTENTENDNOTFOUND);
/*     */         }
/* 268 */         String txt = html.substring(start, end);
/*     */ 
/* 270 */         if (acqu.getImgAcqu().booleanValue()) {
/* 271 */           List<String> imgUrls = getImageSrc(txt);
/* 272 */           for (String img : imgUrls) {
/* 273 */             if (StringUtils.isNotBlank(acqu.getImgPrefix())) {
/* 274 */               img = acqu.getImgPrefix() + img;
/*     */             }
/* 276 */             txt = txt.replace(img, saveImg(img, contextPath, uploadPath));
/*     */           }
/*     */         }
/*     */ 
/* 280 */         String author = null;
/* 281 */         if (StringUtils.isNotBlank(authorStart)) {
/* 282 */           start = html.indexOf(authorStart);
/* 283 */           if (start == -1) {
/* 284 */             return handerResult(temp, history, null, 
/* 285 */               CmsAcquisition.AcquisitionResultType.AUTHORSTARTNOTFOUND);
/*     */           }
/* 287 */           start += authorStart.length();
/* 288 */           end = html.indexOf(authorEnd, start);
/* 289 */           if (end == -1) {
/* 290 */             return handerResult(temp, history, null, 
/* 291 */               CmsAcquisition.AcquisitionResultType.AUTHORENDNOTFOUND);
/*     */           }
/* 293 */           author = html.substring(start, end);
/*     */         }
/*     */ 
/* 296 */         String origin = null;
/* 297 */         if (StringUtils.isNotBlank(originStart)) {
/* 298 */           start = html.indexOf(originStart);
/* 299 */           if (start == -1) {
/* 300 */             return handerResult(temp, history, null, 
/* 301 */               CmsAcquisition.AcquisitionResultType.ORIGINSTARTNOTFOUND);
/*     */           }
/* 303 */           start += originStart.length();
/* 304 */           end = html.indexOf(originEnd, start);
/* 305 */           if (end == -1) {
/* 306 */             return handerResult(temp, history, null, 
/* 307 */               CmsAcquisition.AcquisitionResultType.ORIGINENDNOTFOUND);
/*     */           }
/* 309 */           origin = html.substring(start, end);
/*     */         }
/*     */ 
/* 312 */         String description = null;
/* 313 */         if (StringUtils.isNotBlank(descriptionStart)) {
/* 314 */           start = html.indexOf(descriptionStart);
/* 315 */           if (start == -1) {
/* 316 */             return handerResult(temp, history, null, 
/* 317 */               CmsAcquisition.AcquisitionResultType.DESCRISTARTNOTFOUND);
/*     */           }
/* 319 */           start += descriptionStart.length();
/* 320 */           end = html.indexOf(descriptionEnd, start);
/* 321 */           if (end == -1) {
/* 322 */             return handerResult(temp, history, null, 
/* 323 */               CmsAcquisition.AcquisitionResultType.DESCRIENDNOTFOUND);
/*     */           }
/* 325 */           description = html.substring(start, end);
/*     */         }
/*     */ 
/* 328 */         Date releaseTime = null;
/* 329 */         if (StringUtils.isNotBlank(releaseTimeStart)) {
/* 330 */           start = html.indexOf(releaseTimeStart);
/* 331 */           if (start == -1) {
/* 332 */             return handerResult(temp, history, null, 
/* 333 */               CmsAcquisition.AcquisitionResultType.RELEASESTARTNOTFOUND);
/*     */           }
/* 335 */           start += releaseTimeStart.length();
/* 336 */           end = html.indexOf(releaseTimeEnd, start);
/* 337 */           if (end == -1) {
/* 338 */             return handerResult(temp, history, null, 
/* 339 */               CmsAcquisition.AcquisitionResultType.RELEASEENDNOTFOUND);
/*     */           }
/* 341 */           String releaseDate = html.substring(start, end);
/* 342 */           SimpleDateFormat df = new SimpleDateFormat(acqu.getReleaseTimeFormat());
/* 343 */           releaseTime = df.parse(releaseDate);
/*     */         }
/*     */ 
/* 347 */         String view = null;
/* 348 */         if (StringUtils.isNotBlank(viewLink)) {
/* 349 */           start = html.indexOf(viewIdStart);
/* 350 */           if (start == -1) {
/* 351 */             return handerResult(temp, history, null, 
/* 352 */               CmsAcquisition.AcquisitionResultType.VIEWIDSTARTNOTFOUND);
/*     */           }
/* 354 */           start += viewIdStart.length();
/* 355 */           end = html.indexOf(viewIdEnd, start);
/* 356 */           if (end == -1) {
/* 357 */             return handerResult(temp, history, null, 
/* 358 */               CmsAcquisition.AcquisitionResultType.VIEWIDENDNOTFOUND);
/*     */           }
/* 360 */           viewLink = viewLink + html.substring(start, end);
/* 361 */           HttpGet viewHttpGet = new HttpGet(new URI(viewLink));
/* 362 */           html = (String)client.execute(viewHttpGet, handler);
/*     */         }
/* 364 */         if (StringUtils.isNotBlank(viewStart)) {
/* 365 */           start = html.indexOf(viewStart);
/* 366 */           if (start == -1) {
/* 367 */             return handerResult(temp, history, null, 
/* 368 */               CmsAcquisition.AcquisitionResultType.VIEWSTARTNOTFOUND);
/*     */           }
/* 370 */           start += viewStart.length();
/* 371 */           end = html.indexOf(viewEnd, start);
/* 372 */           if (end == -1) {
/* 373 */             return handerResult(temp, history, null, 
/* 374 */               CmsAcquisition.AcquisitionResultType.VIEWENDNOTFOUND);
/*     */           }
/* 376 */           view = html.substring(start, end);
/*     */         }
/*     */ 
/* 379 */         Content content = AcquisitionSvcImpl.this.cmsAcquisitionMng.saveContent(title, txt, origin, author, description, releaseTime, 
/* 380 */           acquId, CmsAcquisition.AcquisitionResultType.SUCCESS, temp, history);
/* 381 */         if (StringUtils.isNotBlank(view)) {
/* 382 */           ContentCount count = content.getContentCount();
/* 383 */           int c = Integer.parseInt(view);
/*     */ 
/* 385 */           if (StringUtils.isNotBlank(viewLink)) {
/* 386 */             c--;
/*     */           }
/* 388 */           count.setViews(Integer.valueOf(c));
/* 389 */           AcquisitionSvcImpl.this.contentCountMng.update(count);
/*     */         }
/* 391 */         AcquisitionSvcImpl.this.cmsAcquisitionTempMng.save(temp);
/* 392 */         AcquisitionSvcImpl.this.cmsAcquisitionHistoryMng.save(history);
/* 393 */         return content;
/*     */       } catch (Exception e) {
/* 395 */         e.printStackTrace();
/* 396 */         AcquisitionSvcImpl.this.log.warn(null, e);
/* 397 */       }return handerResult(temp, history, null, 
/* 398 */         CmsAcquisition.AcquisitionResultType.UNKNOW);
/*     */     }
/*     */ 
/*     */     private List<String> getImageSrc(String htmlCode)
/*     */     {
/* 403 */       List imageSrcList = new ArrayList();
/* 404 */       String regular = "<img(.*?)src=\"(.*?)\"";
/* 405 */       String img_pre = "(?i)<img(.*?)src=\"";
/* 406 */       String img_sub = "\"";
/* 407 */       Pattern p = Pattern.compile(regular, 2);
/* 408 */       Matcher m = p.matcher(htmlCode);
/* 409 */       String src = null;
/* 410 */       while (m.find()) {
/* 411 */         src = m.group();
/* 412 */         src = src.replaceAll(img_pre, "").replaceAll(img_sub, "").trim();
/* 413 */         imageSrcList.add(src);
/*     */       }
/* 415 */       return imageSrcList;
/*     */     }
/*     */ 
/*     */     private String saveImg(String imgUrl, String contextPath, String uploadPath) {
/* 419 */       HttpClient client = new DefaultHttpClient();
/* 420 */       String outFileName = "";
/*     */       try {
/* 422 */         HttpGet httpget = new HttpGet(new URI(imgUrl));
/* 423 */         HttpResponse response = client.execute(httpget);
/* 424 */         InputStream is = null;
/* 425 */         OutputStream os = null;
/* 426 */         HttpEntity entity = null;
/* 427 */         entity = response.getEntity();
/* 428 */         is = entity.getContent();
/* 429 */         outFileName = UploadUtils.generateFilename(uploadPath, FileNameUtils.getFileSufix(imgUrl));
/* 430 */         os = new FileOutputStream(AcquisitionSvcImpl.this.realPathResolver.get(outFileName));
/* 431 */         IOUtils.copy(is, os);
/*     */       } catch (Exception localException) {
/*     */       }
/* 434 */       return contextPath + outFileName;
/*     */     }
/*     */ 
/*     */     private Content handerResult(CmsAcquisitionTemp temp, CmsAcquisitionHistory history, String title, CmsAcquisition.AcquisitionResultType errorType)
/*     */     {
/* 440 */       return handerResult(temp, history, title, errorType, Boolean.valueOf(false));
/*     */     }
/*     */ 
/*     */     private Content handerResult(CmsAcquisitionTemp temp, CmsAcquisitionHistory history, String title, CmsAcquisition.AcquisitionResultType errorType, Boolean repeat)
/*     */     {
/* 446 */       temp.setDescription(errorType.name());
/* 447 */       temp.setTitle(title);
/* 448 */       AcquisitionSvcImpl.this.cmsAcquisitionTempMng.save(temp);
/* 449 */       if (!repeat.booleanValue()) {
/* 450 */         history.setTitle(title);
/* 451 */         history.setDescription(errorType.name());
/* 452 */         AcquisitionSvcImpl.this.cmsAcquisitionHistoryMng.save(history);
/*     */       }
/* 454 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class CharsetHandler
/*     */     implements ResponseHandler<String>
/*     */   {
/*     */     private String charset;
/*     */ 
/*     */     public CharsetHandler(String charset)
/*     */     {
/* 485 */       this.charset = charset;
/*     */     }
/*     */ 
/*     */     public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
/*     */     {
/* 490 */       StatusLine statusLine = response.getStatusLine();
/* 491 */       if (statusLine.getStatusCode() >= 300) {
/* 492 */         throw new HttpResponseException(statusLine.getStatusCode(), 
/* 493 */           statusLine.getReasonPhrase());
/*     */       }
/* 495 */       HttpEntity entity = response.getEntity();
/* 496 */       if (entity != null) {
/* 497 */         if (!StringUtils.isBlank(this.charset)) {
/* 498 */           return EntityUtils.toString(entity, this.charset);
/*     */         }
/* 500 */         return EntityUtils.toString(entity);
/*     */       }
/*     */ 
/* 503 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.service.AcquisitionSvcImpl
 * JD-Core Version:    0.6.0
 */