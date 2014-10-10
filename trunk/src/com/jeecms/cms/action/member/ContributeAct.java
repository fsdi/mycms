/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import com.jeecms.cms.entity.main.ContentTxt;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.util.StrUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ContributeAct
/*     */ {
/*  48 */   private static final Logger log = LoggerFactory.getLogger(ContributeAct.class);
/*     */   public static final String CONTRIBUTE_LIST = "tpl.contributeList";
/*     */   public static final String CONTRIBUTE_ADD = "tpl.contributeAdd";
/*     */   public static final String CONTRIBUTE_EDIT = "tpl.contributeEdit";
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentTypeMng contentTypeMng;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   protected CmsModelMng cmsModelMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @RequestMapping({"/member/contribute_list.jspx"})
/*     */   public String list(String queryTitle, Integer queryChannelId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  70 */     CmsSite site = CmsUtils.getSite(request);
/*  71 */     CmsUser user = CmsUtils.getUser(request);
/*  72 */     FrontUtils.frontData(request, model, site);
/*  73 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  75 */     if (!mcfg.isMemberOn()) {
/*  76 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  78 */     if (user == null) {
/*  79 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  81 */     Pagination p = this.contentMng.getPageForMember(queryTitle, queryChannelId, 
/*  82 */       site.getId(), user.getId(), SimplePage.cpn(pageNo), 20);
/*  83 */     model.addAttribute("pagination", p);
/*  84 */     if (!StringUtils.isBlank(queryTitle)) {
/*  85 */       model.addAttribute("queryTitle", queryTitle);
/*     */     }
/*  87 */     if (queryChannelId != null) {
/*  88 */       model.addAttribute("queryChannelId", queryChannelId);
/*     */     }
/*  90 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  91 */       "member", "tpl.contributeList");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/contribute_add.jspx"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/* 103 */     CmsSite site = CmsUtils.getSite(request);
/* 104 */     CmsUser user = CmsUtils.getUser(request);
/* 105 */     FrontUtils.frontData(request, model, site);
/* 106 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 108 */     if (!mcfg.isMemberOn()) {
/* 109 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 111 */     if (user == null) {
/* 112 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*     */ 
/* 115 */     Set rights = user.getGroup().getContriChannels();
/* 116 */     List topList = this.channelMng.getTopList(site.getId(), true);
/* 117 */     List channelList = Channel.getListForSelect(topList, rights, 
/* 118 */       true);
/* 119 */     model.addAttribute("channelList", channelList);
/* 120 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 121 */       "member", "tpl.contributeAdd");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/contribute_save.jspx"})
/*     */   public String save(String title, String author, String description, String txt, String tagStr, Integer channelId, Integer modelId, String captcha, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 153 */     CmsSite site = CmsUtils.getSite(request);
/* 154 */     CmsUser user = CmsUtils.getUser(request);
/* 155 */     FrontUtils.frontData(request, model, site);
/* 156 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 158 */     if (!mcfg.isMemberOn()) {
/* 159 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 161 */     if (user == null) {
/* 162 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 164 */     WebErrors errors = validateSave(title, author, description, txt, 
/* 165 */       tagStr, channelId, site, user, captcha, request, response);
/* 166 */     if (errors.hasErrors()) {
/* 167 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/*     */ 
/* 170 */     Content c = new Content();
/* 171 */     c.setSite(site);
/* 172 */     CmsModel defaultModel = this.cmsModelMng.getDefModel();
/* 173 */     if (modelId != null) {
/* 174 */       CmsModel m = this.cmsModelMng.findById(modelId);
/* 175 */       if (m != null)
/* 176 */         c.setModel(m);
/*     */       else
/* 178 */         c.setModel(defaultModel);
/*     */     }
/*     */     else {
/* 181 */       c.setModel(defaultModel);
/*     */     }
/* 183 */     ContentExt ext = new ContentExt();
/* 184 */     ext.setTitle(title);
/* 185 */     ext.setAuthor(author);
/* 186 */     ext.setDescription(description);
/* 187 */     ContentTxt t = new ContentTxt();
/* 188 */     t.setTxt(txt);
/* 189 */     ContentType type = this.contentTypeMng.getDef();
/* 190 */     if (type == null) {
/* 191 */       throw new RuntimeException("Default ContentType not found.");
/*     */     }
/* 193 */     Integer typeId = type.getId();
/* 194 */     String[] tagArr = StrUtils.splitAndTrim(tagStr, ",", null);
/* 195 */     c = this.contentMng.save(c, ext, t, null, null, null, tagArr, null, null, 
/* 196 */       null, null, null, channelId, typeId, null, user, true);
/* 197 */     log.info("member contribute save Content success. id={}", c.getId());
/* 198 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/contribute_edit.jspx"})
/*     */   public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 214 */     CmsSite site = CmsUtils.getSite(request);
/* 215 */     CmsUser user = CmsUtils.getUser(request);
/* 216 */     FrontUtils.frontData(request, model, site);
/* 217 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 219 */     if (!mcfg.isMemberOn()) {
/* 220 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 222 */     if (user == null) {
/* 223 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 225 */     WebErrors errors = validateEdit(id, site, user, request);
/* 226 */     if (errors.hasErrors()) {
/* 227 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 229 */     Content content = this.contentMng.findById(id);
/*     */ 
/* 231 */     Set rights = user.getGroup().getContriChannels();
/* 232 */     List topList = this.channelMng.getTopList(site.getId(), true);
/* 233 */     List channelList = Channel.getListForSelect(topList, rights, 
/* 234 */       true);
/* 235 */     model.addAttribute("content", content);
/* 236 */     model.addAttribute("channelList", channelList);
/* 237 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 238 */       "member", "tpl.contributeEdit");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/contribute_update.jspx"})
/*     */   public String update(Integer id, String title, String author, String description, String txt, String tagStr, Integer channelId, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 270 */     CmsSite site = CmsUtils.getSite(request);
/* 271 */     CmsUser user = CmsUtils.getUser(request);
/* 272 */     FrontUtils.frontData(request, model, site);
/* 273 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 275 */     if (!mcfg.isMemberOn()) {
/* 276 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 278 */     if (user == null) {
/* 279 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 281 */     WebErrors errors = validateUpdate(id, channelId, site, user, request);
/* 282 */     if (errors.hasErrors()) {
/* 283 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 285 */     Content c = new Content();
/* 286 */     c.setId(id);
/* 287 */     c.setSite(site);
/* 288 */     ContentExt ext = new ContentExt();
/* 289 */     ext.setId(id);
/* 290 */     ext.setTitle(title);
/* 291 */     ext.setAuthor(author);
/* 292 */     ext.setDescription(description);
/* 293 */     ContentTxt t = new ContentTxt();
/* 294 */     t.setId(id);
/* 295 */     t.setTxt(txt);
/* 296 */     String[] tagArr = StrUtils.splitAndTrim(tagStr, ",", null);
/* 297 */     this.contentMng.update(c, ext, t, tagArr, null, null, null, null, null, 
/* 298 */       null, null, null, null, channelId, null, null, user, true);
/* 299 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/contribute_delete.jspx"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, String nextUrl, HttpServletResponse response, ModelMap model)
/*     */   {
/* 317 */     CmsSite site = CmsUtils.getSite(request);
/* 318 */     CmsUser user = CmsUtils.getUser(request);
/* 319 */     FrontUtils.frontData(request, model, site);
/* 320 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 322 */     if (!mcfg.isMemberOn()) {
/* 323 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 325 */     if (user == null) {
/* 326 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 328 */     WebErrors errors = validateDelete(ids, site, user, request);
/* 329 */     if (errors.hasErrors()) {
/* 330 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 332 */     Content[] arr = this.contentMng.deleteByIds(ids);
/* 333 */     log.info("member contribute delete Content success. ids={}", 
/* 334 */       StringUtils.join(arr, ","));
/* 335 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(String title, String author, String description, String txt, String tagStr, Integer channelId, CmsSite site, CmsUser user, String captcha, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 342 */     WebErrors errors = WebErrors.create(request);
/*     */     try {
/* 344 */       if (!this.imageCaptchaService.validateResponseForID(
/* 345 */         this.session.getSessionId(request, response), captcha).booleanValue())
/*     */       {
/* 346 */         errors.addErrorCode("error.invalidCaptcha");
/* 347 */         return errors;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 350 */       errors.addErrorCode("error.exceptionCaptcha");
/* 351 */       log.warn("", e);
/* 352 */       return errors;
/*     */     }
/* 354 */     if (errors.ifBlank(title, "title", 150)) {
/* 355 */       return errors;
/*     */     }
/* 357 */     if (errors.ifMaxLength(author, "author", 100)) {
/* 358 */       return errors;
/*     */     }
/* 360 */     if (errors.ifMaxLength(description, "description", 255)) {
/* 361 */       return errors;
/*     */     }
/*     */ 
/* 364 */     if (errors.ifBlank(txt, "txt", 1048575)) {
/* 365 */       return errors;
/*     */     }
/* 367 */     if (errors.ifMaxLength(tagStr, "tagStr", 255)) {
/* 368 */       return errors;
/*     */     }
/* 370 */     if (errors.ifNull(channelId, "channelId")) {
/* 371 */       return errors;
/*     */     }
/* 373 */     if (vldChannel(errors, site, user, channelId)) {
/* 374 */       return errors;
/*     */     }
/* 376 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, CmsSite site, CmsUser user, HttpServletRequest request)
/*     */   {
/* 381 */     WebErrors errors = WebErrors.create(request);
/* 382 */     if (vldOpt(errors, site, user, new Integer[] { id })) {
/* 383 */       return errors;
/*     */     }
/* 385 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, Integer channelId, CmsSite site, CmsUser user, HttpServletRequest request)
/*     */   {
/* 390 */     WebErrors errors = WebErrors.create(request);
/* 391 */     if (vldOpt(errors, site, user, new Integer[] { id })) {
/* 392 */       return errors;
/*     */     }
/* 394 */     if (vldChannel(errors, site, user, channelId)) {
/* 395 */       return errors;
/*     */     }
/* 397 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, CmsSite site, CmsUser user, HttpServletRequest request)
/*     */   {
/* 402 */     WebErrors errors = WebErrors.create(request);
/* 403 */     if (vldOpt(errors, site, user, ids)) {
/* 404 */       return errors;
/*     */     }
/* 406 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldOpt(WebErrors errors, CmsSite site, CmsUser user, Integer[] ids)
/*     */   {
/* 411 */     for (Integer id : ids) {
/* 412 */       if (errors.ifNull(id, "id")) {
/* 413 */         return true;
/*     */       }
/* 415 */       Content c = this.contentMng.findById(id);
/*     */ 
/* 417 */       if (errors.ifNotExist(c, Content.class, id)) {
/* 418 */         return true;
/*     */       }
/*     */ 
/* 421 */       if (!c.getUser().getId().equals(user.getId())) {
/* 422 */         errors.noPermission(Content.class, id);
/* 423 */         return true;
/*     */       }
/*     */ 
/* 426 */       if (!c.getSite().getId().equals(site.getId())) {
/* 427 */         errors.notInSite(Content.class, id);
/* 428 */         return true;
/*     */       }
/*     */ 
/* 431 */       if (c.getCheckStep().byteValue() > 0) {
/* 432 */         errors.addErrorCode("member.contentChecked");
/* 433 */         return true;
/*     */       }
/*     */     }
/* 436 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean vldChannel(WebErrors errors, CmsSite site, CmsUser user, Integer channelId)
/*     */   {
/* 441 */     Channel channel = this.channelMng.findById(channelId);
/* 442 */     if (errors.ifNotExist(channel, Channel.class, channelId)) {
/* 443 */       return true;
/*     */     }
/* 445 */     if (!channel.getSite().getId().equals(site.getId())) {
/* 446 */       errors.notInSite(Channel.class, channelId);
/* 447 */       return true;
/*     */     }
/* 449 */     if (!channel.getContriGroups().contains(user.getGroup())) {
/* 450 */       errors.noPermission(Channel.class, channelId);
/* 451 */       return true;
/*     */     }
/* 453 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.ContributeAct
 * JD-Core Version:    0.6.0
 */