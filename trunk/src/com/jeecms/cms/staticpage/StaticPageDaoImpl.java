/*     */ package com.jeecms.cms.staticpage;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.assist.CmsKeywordMng;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateSimpleDao;
/*     */ import com.jeecms.common.page.Paginable;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import com.jeecms.core.web.front.URLHelper;
/*     */ import com.jeecms.core.web.front.URLHelper.PageInfo;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.CacheMode;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.ScrollMode;
/*     */ import org.hibernate.ScrollableResults;
/*     */ import org.hibernate.Session;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class StaticPageDaoImpl extends HibernateSimpleDao
/*     */   implements StaticPageDao
/*     */ {
/*     */   private CmsKeywordMng cmsKeywordMng;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   public int channelStatic(Integer siteId, Integer channelId, boolean containChild, Configuration conf, Map<String, Object> data)
/*     */     throws IOException, TemplateException
/*     */   {
/*  47 */     Finder finder = Finder.create("select bean from Channel bean");
/*  48 */     if (channelId != null) {
/*  49 */       if (containChild) {
/*  50 */         finder.append(",Channel parent where").append(
/*  51 */           " bean.lft between parent.lft and parent.rgt").append(
/*  52 */           " and parent.site.id=bean.site.id").append(
/*  53 */           " and parent.id=:channelId");
/*  54 */         finder.setParam("channelId", channelId);
/*     */       } else {
/*  56 */         finder.append(" where bean.id=:channelId");
/*  57 */         finder.setParam("channelId", channelId);
/*     */       }
/*  59 */     } else if (siteId != null) {
/*  60 */       finder.append(" where bean.site.id=:siteId");
/*  61 */       finder.setParam("siteId", siteId);
/*     */     }
/*  63 */     Session session = getSession();
/*  64 */     ScrollableResults channels = finder.createQuery(session).setCacheMode(
/*  65 */       CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY);
/*  66 */     int count = 0;
/*     */ 
/*  70 */     Writer out = null;
/*     */ 
/*  75 */     if (data == null) {
/*  76 */       data = new HashMap();
/*     */     }
/*  78 */     while (channels.next()) {
/*  79 */       Channel c = (Channel)channels.get(0);
/*  80 */       CmsSite site = c.getSite();
/*  81 */       FrontUtils.frontData(data, site, null, null, null);
/*     */ 
/*  83 */       if ((!StringUtils.isBlank(c.getLink())) || (!c.getStaticChannel().booleanValue()))
/*     */       {
/*     */         continue;
/*     */       }
/*  87 */       int childs = childsOfChannel(c.getId());
/*     */       int totalPage;
/*  88 */       if (!c.getModel().getHasContent().booleanValue()) {
/*  89 */         totalPage = 1;
/*     */       }
/*     */       else
/*     */       {
/*     */         int quantity;
/*  91 */         if (c.getListChild().booleanValue()) {
/*  92 */           quantity = childs;
/*     */         }
/*     */         else
/*     */         {
/*  94 */           if ((!c.getListChild().booleanValue()) && (childs > 0))
/*  95 */             quantity = contentsOfParentChannel(c.getId());
/*     */           else
/*  97 */             quantity = contentsOfChannel(c.getId());
/*     */         }
/* 100 */         if (quantity <= 0)
/* 101 */           totalPage = 1;
/*     */         else {
/* 103 */           totalPage = (quantity - 1) / c.getPageSize().intValue() + 1;
/*     */         }
/*     */       }
/* 106 */       for (int i = 1; i <= totalPage; i++) {
/* 107 */         String filename = c.getStaticFilename(i);
/* 108 */         String real = this.realPathResolver.get(filename.toString());
/* 109 */         File f = new File(real);
/* 110 */         File parent = f.getParentFile();
/* 111 */         if (!parent.exists()) {
/* 112 */           parent.mkdirs();
/*     */         }
/* 114 */         Template tpl = conf.getTemplate(c.getTplChannelOrDef());
/* 115 */         String urlStatic = c.getUrlStatic(i);
/* 116 */         URLHelper.PageInfo info = URLHelper.getPageInfo(filename.substring(
/* 117 */           filename.lastIndexOf("/")), null);
/* 118 */         FrontUtils.frontPageData(i, info.getHref(), 
/* 119 */           info.getHrefFormer(), info.getHrefLatter(), data);
/* 120 */         FrontUtils.putLocation(data, urlStatic);
/* 121 */         data.put("channel", c);
/*     */         try
/*     */         {
/* 124 */           out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
/* 125 */           tpl.process(data, out);
/* 126 */           this.log.info("create static file: {}", f.getAbsolutePath());
/*     */         } finally {
/* 128 */           if (out != null) {
/* 129 */             out.close();
/*     */           }
/*     */         }
/*     */       }
/* 133 */       count++; if (count % 20 == 0) {
/* 134 */         session.clear();
/*     */       }
/*     */     }
/* 137 */     return count;
/*     */   }
/*     */ 
/*     */   public void channelStatic(Channel c, boolean firstOnly, Configuration conf, Map<String, Object> data)
/*     */     throws IOException, TemplateException
/*     */   {
/* 143 */     if ((!StringUtils.isBlank(c.getLink())) || (!c.getStaticChannel().booleanValue())) {
/* 144 */       return;
/*     */     }
/* 146 */     if (data == null) {
/* 147 */       data = new HashMap();
/*     */     }
/*     */ 
/* 150 */     int childs = childsOfChannel(c.getId());
/*     */     int totalPage;
/* 152 */     if ((firstOnly) || (!c.getModel().getHasContent().booleanValue()) || (
/* 153 */       (!c.getListChild().booleanValue()) && (childs > 0))) {
/* 154 */       totalPage = 1;
/*     */     }
/*     */     else
/*     */     {
/*     */       int quantity;
/* 156 */       if (c.getListChild().booleanValue())
/* 157 */         quantity = childs;
/*     */       else
/* 159 */         quantity = contentsOfChannel(c.getId());
/* 161 */       if (quantity <= 0)
/* 162 */         totalPage = 1;
/*     */       else {
/* 164 */         totalPage = (quantity - 1) / c.getPageSize().intValue() + 1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 170 */     Writer out = null;
/*     */ 
/* 172 */     CmsSite site = c.getSite();
/* 173 */     FrontUtils.frontData(data, site, null, null, null);
/* 174 */     for (int i = 1; i <= totalPage; i++) {
/* 175 */       String filename = c.getStaticFilename(i);
/* 176 */       String real = this.realPathResolver.get(filename.toString());
/* 177 */       File f = new File(real);
/* 178 */       File parent = f.getParentFile();
/* 179 */       if (!parent.exists()) {
/* 180 */         parent.mkdirs();
/*     */       }
/* 182 */       Template tpl = conf.getTemplate(c.getTplChannelOrDef());
/* 183 */       String urlStatic = c.getUrlStatic(i);
/* 184 */       URLHelper.PageInfo info = URLHelper.getPageInfo(filename.substring(
/* 185 */         filename.lastIndexOf("/")), null);
/* 186 */       FrontUtils.frontPageData(i, info.getHref(), info.getHrefFormer(), 
/* 187 */         info.getHrefLatter(), data);
/* 188 */       FrontUtils.putLocation(data, urlStatic);
/* 189 */       data.put("channel", c);
/*     */       try
/*     */       {
/* 192 */         out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
/* 193 */         tpl.process(data, out);
/* 194 */         this.log.info("create static file: {}", f.getAbsolutePath());
/*     */       } finally {
/* 196 */         if (out != null)
/* 197 */           out.close();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int contentsOfChannel(Integer channelId)
/*     */   {
/* 204 */     String hql = "select count(*) from Content bean join bean.channels as channel where channel.id=:channelId and bean.status=2";
/*     */ 
/* 208 */     Query query = getSession().createQuery(hql);
/* 209 */     query.setParameter("channelId", channelId);
/* 210 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public int contentsOfParentChannel(Integer channelId) {
/* 214 */     String hql = "select count(*) from Content bean join bean.channel channel,Channel parent  where channel.lft between parent.lft and parent.rgt and channel.site.id=parent.site.id and parent.id=:parentId and bean.status=2";
/*     */ 
/* 218 */     Query query = getSession().createQuery(hql);
/* 219 */     query.setParameter("parentId", channelId);
/* 220 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public int childsOfChannel(Integer channelId) {
/* 224 */     String hql = "select count(*) from Channel bean where bean.parent.id=:channelId";
/*     */ 
/* 226 */     Query query = getSession().createQuery(hql);
/* 227 */     query.setParameter("channelId", channelId);
/* 228 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public int contentStatic(Integer siteId, Integer channelId, Date start, Configuration conf, Map<String, Object> data)
/*     */     throws IOException, TemplateException
/*     */   {
/* 234 */     Finder f = Finder.create("select bean from Content bean");
/* 235 */     if (channelId != null) {
/* 236 */       f.append(" join bean.channel node,Channel parent");
/* 237 */       f.append(" where node.lft between parent.lft and parent.rgt");
/* 238 */       f.append(" and parent.id=:channelId");
/* 239 */       f.append(" and node.site.id=parent.site.id");
/* 240 */       f.setParam("channelId", channelId);
/* 241 */     } else if (siteId != null) {
/* 242 */       f.append(" where bean.site.id=:siteId");
/* 243 */       f.setParam("siteId", siteId);
/*     */     } else {
/* 245 */       f.append(" where 1=1");
/*     */     }
/* 247 */     if (start != null) {
/* 248 */       f.append(" and bean.sortDate>=:start");
/* 249 */       f.setParam("start", start);
/*     */     }
/* 251 */     f.append(" and bean.status=2");
/* 252 */     Session session = getSession();
/* 253 */     ScrollableResults contents = f.createQuery(session).setCacheMode(
/* 254 */       CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY);
/* 255 */     int count = 0;
/*     */ 
/* 264 */     Writer out = null;
/* 265 */     if (data == null) {
/* 266 */       data = new HashMap();
/*     */     }
/* 268 */     while (contents.next()) {
/* 269 */       Content c = (Content)contents.get(0);
/* 270 */       Channel chnl = c.getChannel();
/*     */ 
/* 272 */       if ((!StringUtils.isBlank(c.getLink())) || (!chnl.getStaticContent().booleanValue()))
/*     */       {
/*     */         continue;
/*     */       }
/* 276 */       if (!c.getNeedRegenerate().booleanValue()) {
/*     */         continue;
/*     */       }
/* 279 */       CmsSite site = c.getSite();
/* 280 */       Template tpl = conf.getTemplate(c.getTplContentOrDef());
/* 281 */       FrontUtils.frontData(data, site, null, null, null);
/* 282 */       data.put("content", c);
/* 283 */       data.put("channel", c.getChannel());
/* 284 */       int totalPage = c.getPageCount();
/* 285 */       for (int pageNo = 1; pageNo <= totalPage; pageNo++) {
/* 286 */         String txt = c.getTxtByNo(pageNo);
/*     */ 
/* 288 */         txt = this.cmsKeywordMng.attachKeyword(site.getId(), txt);
/* 289 */         Paginable pagination = new SimplePage(pageNo, 1, 
/* 290 */           c.getPageCount());
/* 291 */         data.put("pagination", pagination);
/* 292 */         String url = c.getUrlStatic(pageNo);
/* 293 */         URLHelper.PageInfo info = URLHelper.getPageInfo(url
/* 294 */           .substring(url.lastIndexOf("/")), null);
/* 295 */         FrontUtils.putLocation(data, url);
/* 296 */         FrontUtils.frontPageData(pageNo, info.getHref(), 
/* 297 */           info.getHrefFormer(), info.getHrefLatter(), data);
/* 298 */         data.put("title", c.getTitleByNo(pageNo));
/* 299 */         data.put("txt", txt);
/* 300 */         data.put("pic", c.getPictureByNo(pageNo));
/*     */         File file;
/* 301 */         if (pageNo == 1) {
/* 302 */           String real = this.realPathResolver.get(c.getStaticFilename(pageNo));
/* 303 */           file = new File(real);
/* 304 */           File parent = file.getParentFile();
/* 305 */           if (!parent.exists())
/* 306 */             parent.mkdirs();
/*     */         }
/*     */         else {
/* 309 */           String real = this.realPathResolver.get(c.getStaticFilename(pageNo));
/* 310 */           file = new File(real);
/*     */         }
/*     */         try
/*     */         {
/* 314 */           out = new OutputStreamWriter(new FileOutputStream(file), 
/* 315 */             "UTF-8");
/* 316 */           tpl.process(data, out);
/* 317 */           this.log.info("create static file: {}", file.getAbsolutePath());
/*     */         } finally {
/* 319 */           if (out != null) {
/* 320 */             out.close();
/*     */           }
/*     */         }
/*     */       }
/* 324 */       c.setNeedRegenerate(Boolean.valueOf(false));
/* 325 */       count++; if (count % 20 == 0) {
/* 326 */         session.clear();
/*     */       }
/*     */     }
/* 329 */     return count;
/*     */   }
/*     */ 
/*     */   public boolean contentStatic(Content c, Configuration conf, Map<String, Object> data)
/*     */     throws IOException, TemplateException
/*     */   {
/* 335 */     Channel chnl = c.getChannel();
/* 336 */     if ((!StringUtils.isBlank(c.getLink())) || (!chnl.getStaticContent().booleanValue())) {
/* 337 */       return false;
/*     */     }
/*     */ 
/* 340 */     if (!c.getNeedRegenerate().booleanValue()) {
/* 341 */       return false;
/*     */     }
/* 343 */     if (data == null) {
/* 344 */       data = new HashMap();
/*     */     }
/*     */ 
/* 352 */     Writer out = null;
/* 353 */     CmsSite site = c.getSite();
/* 354 */     Template tpl = conf.getTemplate(c.getTplContentOrDef());
/* 355 */     FrontUtils.frontData(data, site, null, null, null);
/* 356 */     data.put("content", c);
/* 357 */     data.put("channel", chnl);
/* 358 */     int totalPage = c.getPageCount();
/* 359 */     for (int pageNo = 1; pageNo <= totalPage; pageNo++) {
/* 360 */       String txt = c.getTxtByNo(pageNo);
/*     */ 
/* 362 */       txt = this.cmsKeywordMng.attachKeyword(site.getId(), txt);
/* 363 */       Paginable pagination = new SimplePage(pageNo, 1, c.getPageCount());
/* 364 */       data.put("pagination", pagination);
/* 365 */       String url = c.getUrlStatic(pageNo);
/* 366 */       URLHelper.PageInfo info = URLHelper.getPageInfo(url.substring(url.lastIndexOf("/")), 
/* 367 */         null);
/* 368 */       FrontUtils.putLocation(data, url);
/* 369 */       FrontUtils.frontPageData(pageNo, info.getHref(), 
/* 370 */         info.getHrefFormer(), info.getHrefLatter(), data);
/* 371 */       data.put("title", c.getTitleByNo(pageNo));
/* 372 */       data.put("txt", txt);
/* 373 */       data.put("pic", c.getPictureByNo(pageNo));
/* 374 */       String real = this.realPathResolver.get(c.getStaticFilename(pageNo));
/* 375 */       File file = new File(real);
/* 376 */       if (pageNo == 1) {
/* 377 */         File parent = file.getParentFile();
/* 378 */         if (!parent.exists()) {
/* 379 */           parent.mkdirs();
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/* 384 */         out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
/* 385 */         tpl.process(data, out);
/* 386 */         this.log.info("create static file: {}", file.getAbsolutePath());
/*     */       } finally {
/* 388 */         if (out != null) {
/* 389 */           out.close();
/*     */         }
/*     */       }
/*     */     }
/* 393 */     c.setNeedRegenerate(Boolean.valueOf(false));
/* 394 */     return true;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsKeywordMng(CmsKeywordMng cmsKeywordMng)
/*     */   {
/* 402 */     this.cmsKeywordMng = cmsKeywordMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver) {
/* 407 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticPageDaoImpl
 * JD-Core Version:    0.6.0
 */