/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsMessageMng;
/*     */ import com.jeecms.cms.manager.assist.CmsReceiverMessageMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class MessageAct
/*     */ {
/*  49 */   private static final Logger log = LoggerFactory.getLogger(MessageAct.class);
/*     */   public static final String MESSAGE_IN_BOX_LIST = "tpl.messageInBoxLists";
/*     */   public static final String MESSAGE_DRAFT_LIST = "tpl.messageDraftLists";
/*     */   public static final String MESSAGE_SEND_LIST = "tpl.messageSendLists";
/*     */   public static final String MESSAGE_TRASH_LIST = "tpl.messageTrashLists";
/*     */   public static final String MESSAGE_MNG = "tpl.messageMng";
/*     */   public static final String MESSAGE_ADD = "tpl.messageAdd";
/*     */   public static final String MESSAGE_EDIT = "tpl.messageEdit";
/*     */   public static final String MESSAGE_READ = "tpl.messageRead";
/*     */   public static final String MESSAGE_REPLY = "tpl.messageReply";
/*     */ 
/*     */   @Autowired
/*     */   private CmsMessageMng messageMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsReceiverMessageMng receiverMessageMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @RequestMapping({"/member/message_mng.jspx"})
/*     */   public String message_mng(Integer box, String msg, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  75 */     CmsSite site = CmsUtils.getSite(request);
/*  76 */     CmsUser user = CmsUtils.getUser(request);
/*  77 */     FrontUtils.frontData(request, model, site);
/*  78 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  80 */     if (!mcfg.isMemberOn()) {
/*  81 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  83 */     if (user == null) {
/*  84 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  86 */     if (box != null)
/*  87 */       model.addAttribute("box", box);
/*     */     else {
/*  89 */       model.addAttribute("box", Integer.valueOf(0));
/*     */     }
/*  91 */     model.addAttribute("msg", msg);
/*  92 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  93 */       "message", "tpl.messageMng");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_list.jspx"})
/*     */   public String message_inbox(Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 117 */     CmsSite site = CmsUtils.getSite(request);
/* 118 */     CmsUser user = CmsUtils.getUser(request);
/* 119 */     FrontUtils.frontData(request, model, site);
/* 120 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 122 */     if (!mcfg.isMemberOn()) {
/* 123 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 125 */     if (user == null) {
/* 126 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 128 */     Pagination pagination = null;
/* 129 */     String returnPage = "tpl.messageInBoxLists";
/* 130 */     if (box.equals(Integer.valueOf(0)))
/*     */     {
/* 132 */       pagination = this.receiverMessageMng.getPage(site.getId(), null, 
/* 133 */         user.getId(), title, sendBeginTime, sendEndTime, status, box, 
/* 134 */         Boolean.valueOf(false), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 135 */       returnPage = "tpl.messageInBoxLists";
/* 136 */     } else if (box.equals(Integer.valueOf(1)))
/*     */     {
/* 138 */       pagination = this.messageMng.getPage(site.getId(), user.getId(), null, 
/* 139 */         title, sendBeginTime, sendEndTime, status, box, Boolean.valueOf(false), 
/* 140 */         SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 141 */       returnPage = "tpl.messageSendLists";
/* 142 */     } else if (box.equals(Integer.valueOf(2)))
/*     */     {
/* 144 */       pagination = this.messageMng.getPage(site.getId(), user.getId(), null, 
/* 145 */         title, sendBeginTime, sendEndTime, status, box, Boolean.valueOf(false), 
/* 146 */         SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 147 */       returnPage = "tpl.messageDraftLists";
/* 148 */     } else if (box.equals(Integer.valueOf(3)))
/*     */     {
/* 150 */       pagination = this.receiverMessageMng.getPage(site.getId(), user.getId(), 
/* 151 */         user.getId(), title, sendBeginTime, sendEndTime, status, 
/* 152 */         box, Boolean.valueOf(false), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 153 */       returnPage = "tpl.messageTrashLists";
/*     */     }
/* 155 */     model.addAttribute("msg", request.getAttribute("msg"));
/* 156 */     model.addAttribute("pagination", pagination);
/* 157 */     model.addAttribute("pageNo", pageNo);
/* 158 */     model.addAttribute("title", title);
/* 159 */     model.addAttribute("sendBeginTime", sendBeginTime);
/* 160 */     model.addAttribute("sendEndTime", sendEndTime);
/* 161 */     model.addAttribute("status", status);
/* 162 */     model.addAttribute("box", box);
/* 163 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 164 */       "message", returnPage);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_add.jspx"})
/*     */   public String message_add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 170 */     CmsSite site = CmsUtils.getSite(request);
/* 171 */     CmsUser user = CmsUtils.getUser(request);
/* 172 */     FrontUtils.frontData(request, model, site);
/* 173 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 175 */     if (!mcfg.isMemberOn()) {
/* 176 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 178 */     if (user == null) {
/* 179 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 181 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 182 */       "message", "tpl.messageAdd");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_reply.jspx"})
/*     */   public String message_reply(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 188 */     CmsSite site = CmsUtils.getSite(request);
/* 189 */     CmsUser user = CmsUtils.getUser(request);
/* 190 */     FrontUtils.frontData(request, model, site);
/* 191 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 193 */     if (!mcfg.isMemberOn()) {
/* 194 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 196 */     if (user == null) {
/* 197 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 199 */     CmsReceiverMessage message = this.receiverMessageMng.findById(id);
/*     */ 
/* 201 */     if (!message.getMsgReceiverUser().equals(user)) {
/* 202 */       WebErrors errors = WebErrors.create(request);
/* 203 */       errors.addErrorCode("error.noPermissionsView");
/* 204 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 206 */     model.addAttribute("message", message);
/* 207 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 208 */       "message", "tpl.messageReply");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_send.jspx"})
/*     */   public String message_send(CmsMessage message, String username, String captcha, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 216 */     CmsSite site = CmsUtils.getSite(request);
/* 217 */     CmsUser user = CmsUtils.getUser(request);
/* 218 */     FrontUtils.frontData(request, model, site);
/* 219 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 221 */     if (!mcfg.isMemberOn()) {
/* 222 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 224 */     if (user == null) {
/* 225 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 227 */     WebErrors errors = validateCaptcha(captcha, request, response);
/* 228 */     if (errors.hasErrors()) {
/* 229 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/*     */ 
/* 232 */     message.setMsgBox(Integer.valueOf(1));
/* 233 */     message.setMsgSendUser(user);
/* 234 */     CmsUser msgReceiverUser = this.userMng.findByUsername(username);
/* 235 */     message.setMsgReceiverUser(msgReceiverUser);
/* 236 */     message.setMsgStatus(Boolean.valueOf(false));
/* 237 */     message.setSendTime(new Date());
/* 238 */     message.setSite(site);
/* 239 */     this.messageMng.save(message);
/* 240 */     CmsReceiverMessage receiverMessage = new CmsReceiverMessage(message);
/* 241 */     receiverMessage.setMsgBox(Integer.valueOf(0));
/* 242 */     receiverMessage.setMessage(message);
/*     */ 
/* 244 */     this.receiverMessageMng.save(receiverMessage);
/* 245 */     log.info("member CmsMessage save CmsMessage success. id={}", 
/* 246 */       message.getId());
/* 247 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_save.jspx"})
/*     */   public String message_save(CmsMessage message, String username, String captcha, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 255 */     CmsSite site = CmsUtils.getSite(request);
/* 256 */     CmsUser user = CmsUtils.getUser(request);
/* 257 */     FrontUtils.frontData(request, model, site);
/* 258 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 260 */     if (!mcfg.isMemberOn()) {
/* 261 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 263 */     if (user == null) {
/* 264 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 266 */     WebErrors errors = validateCaptcha(captcha, request, response);
/* 267 */     if (errors.hasErrors()) {
/* 268 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 270 */     message.setMsgBox(Integer.valueOf(2));
/* 271 */     message.setMsgSendUser(user);
/* 272 */     CmsUser msgReceiverUser = this.userMng.findByUsername(username);
/* 273 */     message.setMsgReceiverUser(msgReceiverUser);
/* 274 */     message.setMsgStatus(Boolean.valueOf(false));
/*     */ 
/* 276 */     message.setSendTime(null);
/*     */ 
/* 278 */     message.setSite(site);
/* 279 */     this.messageMng.save(message);
/* 280 */     CmsReceiverMessage receiverMessage = new CmsReceiverMessage(message);
/* 281 */     receiverMessage.setMsgBox(Integer.valueOf(2));
/* 282 */     receiverMessage.setMessage(message);
/*     */ 
/* 284 */     this.receiverMessageMng.save(receiverMessage);
/* 285 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_tosend.jspx"})
/*     */   public String message_tosend(Integer id, String nextUrl, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 293 */     CmsSite site = CmsUtils.getSite(request);
/* 294 */     CmsUser user = CmsUtils.getUser(request);
/* 295 */     FrontUtils.frontData(request, model, site);
/* 296 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 298 */     if (!mcfg.isMemberOn()) {
/* 299 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 301 */     if (user == null) {
/* 302 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 304 */     WebErrors errors = validateCaptcha(captcha, request, response);
/* 305 */     if (errors.hasErrors()) {
/* 306 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 308 */     CmsMessage message = this.messageMng.findById(id);
/* 309 */     message.setMsgBox(Integer.valueOf(1));
/* 310 */     message.setSendTime(new Date());
/* 311 */     this.messageMng.update(message);
/* 312 */     Set receiverMessageSet = message.getReceiverMsgs();
/* 313 */     Iterator it = receiverMessageSet.iterator();
/*     */ 
/* 315 */     while (it.hasNext()) {
/* 316 */       CmsReceiverMessage receiverMessage = (CmsReceiverMessage)it.next();
/* 317 */       receiverMessage.setMsgBox(Integer.valueOf(0));
/* 318 */       receiverMessage.setSendTime(new Date());
/* 319 */       receiverMessage.setMessage(message);
/*     */ 
/* 321 */       this.receiverMessageMng.update(receiverMessage);
/*     */     }
/* 323 */     log.info("member CmsMessage save CmsMessage success. id={}", 
/* 324 */       message.getId());
/* 325 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_edit.jspx"})
/*     */   public String message_edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 331 */     CmsSite site = CmsUtils.getSite(request);
/* 332 */     CmsUser user = CmsUtils.getUser(request);
/* 333 */     FrontUtils.frontData(request, model, site);
/* 334 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 336 */     if (!mcfg.isMemberOn()) {
/* 337 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 339 */     if (user == null) {
/* 340 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 342 */     CmsMessage message = this.messageMng.findById(id);
/*     */ 
/* 344 */     if (!message.getMsgSendUser().equals(user)) {
/* 345 */       WebErrors errors = WebErrors.create(request);
/* 346 */       errors.addErrorCode("error.noPermissionsView");
/* 347 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 349 */     model.addAttribute("message", message);
/* 350 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 351 */       "message", "tpl.messageEdit");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_update.jspx"})
/*     */   public String message_update(CmsMessage message, String nextUrl, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 358 */     CmsSite site = CmsUtils.getSite(request);
/* 359 */     CmsUser user = CmsUtils.getUser(request);
/* 360 */     FrontUtils.frontData(request, model, site);
/* 361 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 363 */     if (!mcfg.isMemberOn()) {
/* 364 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 366 */     if (user == null) {
/* 367 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 369 */     WebErrors errors = validateCaptcha(captcha, request, response);
/* 370 */     if (errors.hasErrors()) {
/* 371 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 373 */     message = this.messageMng.update(message);
/*     */ 
/* 375 */     Set receiverMessageSet = message.getReceiverMsgs();
/* 376 */     Iterator it = receiverMessageSet.iterator();
/*     */ 
/* 378 */     while (it.hasNext()) {
/* 379 */       CmsReceiverMessage receiverMessage = (CmsReceiverMessage)it.next();
/* 380 */       receiverMessage.setMsgContent(message.getContentHtml());
/* 381 */       receiverMessage.setMsgReceiverUser(message.getMsgReceiverUser());
/* 382 */       receiverMessage.setMsgTitle(message.getMsgTitle());
/* 383 */       receiverMessage.setMessage(message);
/*     */ 
/* 385 */       this.receiverMessageMng.update(receiverMessage);
/*     */     }
/* 387 */     log.info("member CmsMessage update CmsMessage success. id={}", 
/* 388 */       message.getId());
/* 389 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_read.jspx"})
/*     */   public String message_read(Integer id, Integer box, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 396 */     CmsSite site = CmsUtils.getSite(request);
/* 397 */     CmsUser user = CmsUtils.getUser(request);
/* 398 */     FrontUtils.frontData(request, model, site);
/* 399 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 401 */     if (!mcfg.isMemberOn()) {
/* 402 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 404 */     if (user == null) {
/* 405 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 407 */     CmsReceiverMessage message = this.receiverMessageMng.findById(id);
/* 408 */     if (message != null)
/*     */     {
/* 411 */       if ((!message.getMsgReceiverUser().equals(user)) && 
/* 412 */         (!message.getMsgSendUser().equals(user))) {
/* 413 */         WebErrors errors = WebErrors.create(request);
/* 414 */         errors.addErrorCode("error.noPermissionsView");
/* 415 */         return FrontUtils.showError(request, response, model, errors);
/*     */       }
/*     */ 
/* 418 */       if (message.getMsgReceiverUser().equals(user)) {
/* 419 */         message.setMsgStatus(true);
/* 420 */         this.receiverMessageMng.update(message);
/* 421 */         log
/* 422 */           .info(
/* 423 */           "member CmsMessage read CmsMessage success. id={}", 
/* 424 */           id);
/*     */       }
/* 426 */       model.addAttribute("message", message);
/*     */     }
/*     */     else {
/* 429 */       CmsMessage msg = this.messageMng.findById(id);
/* 430 */       model.addAttribute("message", msg);
/*     */     }
/* 432 */     model.addAttribute("box", box);
/* 433 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 434 */       "message", "tpl.messageRead");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_forward.jspx"})
/*     */   public String message_forward(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 441 */     CmsSite site = CmsUtils.getSite(request);
/* 442 */     CmsUser user = CmsUtils.getUser(request);
/* 443 */     FrontUtils.frontData(request, model, site);
/* 444 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 446 */     if (!mcfg.isMemberOn()) {
/* 447 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 449 */     if (user == null) {
/* 450 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 452 */     CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(id);
/*     */ 
/* 454 */     if (receiverMessage != null) {
/* 455 */       model.addAttribute("message", receiverMessage);
/*     */     } else {
/* 457 */       CmsMessage message = this.messageMng.findById(id);
/* 458 */       model.addAttribute("message", message);
/*     */     }
/* 460 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 461 */       "message", "tpl.messageAdd");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_trash.jspx"})
/*     */   public void message_trash(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 468 */     CmsUser user = CmsUtils.getUser(request);
/* 469 */     JSONObject object = new JSONObject();
/*     */ 
/* 472 */     if (user == null) {
/* 473 */       object.put("result", false);
/*     */     }
/*     */     else {
/* 476 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1)) {
/* 477 */         CmsMessage message = this.messageMng.findById(ids[i.intValue()]);
/* 478 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/* 479 */         if ((message != null) && (message.getMsgSendUser().equals(user))) {
/* 480 */           message.setMsgBox(Integer.valueOf(3));
/* 481 */           receiverMessage = new CmsReceiverMessage();
/* 482 */           receiverMessage.setMsgBox(Integer.valueOf(3));
/* 483 */           receiverMessage.setMsgContent(message.getMsgContent());
/* 484 */           receiverMessage.setMsgSendUser(message.getMsgSendUser());
/* 485 */           receiverMessage.setMsgReceiverUser(user);
/* 486 */           receiverMessage.setMsgStatus(message.getMsgStatus().booleanValue());
/* 487 */           receiverMessage.setMsgTitle(message.getMsgTitle());
/* 488 */           receiverMessage.setSendTime(message.getSendTime());
/* 489 */           receiverMessage.setSite(message.getSite());
/* 490 */           receiverMessage.setMessage(null);
/*     */ 
/* 492 */           this.receiverMessageMng.save(receiverMessage);
/*     */ 
/* 494 */           Set receiverMessages = message
/* 495 */             .getReceiverMsgs();
/* 496 */           if ((receiverMessages != null) && (receiverMessages.size() > 0)) {
/* 497 */             Iterator it = receiverMessages.iterator();
/*     */ 
/* 499 */             while (it.hasNext()) {
/* 500 */               CmsReceiverMessage tempReceiverMessage = (CmsReceiverMessage)it.next();
/* 501 */               tempReceiverMessage.setMessage(null);
/* 502 */               this.receiverMessageMng.update(tempReceiverMessage);
/*     */             }
/*     */           }
/* 505 */           this.messageMng.deleteById(ids[i.intValue()]);
/*     */         }
/* 507 */         if ((receiverMessage != null) && 
/* 508 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 509 */           receiverMessage.setMsgBox(Integer.valueOf(3));
/* 510 */           this.receiverMessageMng.update(receiverMessage);
/*     */         }
/* 512 */         log.info("member CmsMessage trash CmsMessage success. id={}", 
/* 513 */           ids[i.intValue()]);
/*     */       }
/* 515 */       object.put("result", true);
/*     */     }
/* 517 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_revert.jspx"})
/*     */   public void message_revert(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 524 */     CmsUser user = CmsUtils.getUser(request);
/* 525 */     JSONObject object = new JSONObject();
/*     */ 
/* 527 */     if (user == null) {
/* 528 */       object.put("result", false);
/*     */     } else {
/* 530 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1)) {
/* 531 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/*     */ 
/* 533 */         if ((receiverMessage != null) && 
/* 534 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 535 */           receiverMessage.setMsgBox(Integer.valueOf(0));
/* 536 */           this.receiverMessageMng.update(receiverMessage);
/*     */         }
/* 538 */         log.info("member CmsMessage revert CmsMessage success. id={}", 
/* 539 */           ids[i.intValue()]);
/*     */       }
/* 541 */       object.put("result", true);
/*     */     }
/* 543 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_empty.jspx"})
/*     */   public void message_empty(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 550 */     CmsUser user = CmsUtils.getUser(request);
/* 551 */     JSONObject object = new JSONObject();
/*     */ 
/* 554 */     if (user == null) {
/* 555 */       object.put("result", false);
/*     */     } else {
/* 557 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1))
/*     */       {
/* 559 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/* 560 */         if ((receiverMessage != null) && 
/* 561 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 562 */           this.receiverMessageMng.deleteById(ids[i.intValue()]);
/*     */         }
/*     */         else {
/* 565 */           CmsMessage message = receiverMessage.getMessage();
/* 566 */           if (receiverMessage.getMsgBox().equals(Integer.valueOf(3)))
/*     */           {
/* 568 */             receiverMessage.setMessage(null);
/* 569 */             if (message != null)
/* 570 */               this.messageMng.deleteById(message.getId());
/*     */           }
/*     */           else
/*     */           {
/* 574 */             receiverMessage.setMessage(null);
/*     */           }
/* 576 */           if ((message != null) && 
/* 577 */             (message.getMsgSendUser().equals(user))) {
/* 578 */             this.messageMng.deleteById(message.getId());
/*     */           }
/*     */         }
/* 581 */         log.info("member CmsMessage empty CmsMessage success. id={}", 
/* 582 */           ids[i.intValue()]);
/*     */       }
/* 584 */       object.put("result", true);
/*     */     }
/* 586 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_findUser.jspx"})
/*     */   public void findUserByUserName(String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 593 */     CmsUser user = CmsUtils.getUser(request);
/* 594 */     JSONObject object = new JSONObject();
/* 595 */     if (user == null) {
/* 596 */       object.put("result", false);
/*     */     }
/*     */     else {
/* 599 */       Boolean exist = Boolean.valueOf(this.userMng.usernameNotExistInMember(username));
/* 600 */       object.put("result", true);
/* 601 */       object.put("exist", exist);
/*     */     }
/* 603 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_countUnreadMsg.jspx"})
/*     */   public void findUnreadMessagesByUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 610 */     CmsUser user = CmsUtils.getUser(request);
/* 611 */     CmsSite site = CmsUtils.getSite(request);
/* 612 */     JSONObject object = new JSONObject();
/* 613 */     if (user == null) {
/* 614 */       object.put("result", false);
/*     */     } else {
/* 616 */       List receiverMessages = this.receiverMessageMng
/* 617 */         .getList(site.getId(), null, user.getId(), null, null, 
/* 618 */         null, Boolean.valueOf(false), Integer.valueOf(0), Boolean.valueOf(false));
/* 619 */       object.put("result", true);
/* 620 */       if ((receiverMessages != null) && (receiverMessages.size() > 0))
/* 621 */         object.put("count", receiverMessages.size());
/*     */       else {
/* 623 */         object.put("count", 0);
/*     */       }
/* 625 */       object.put("result", true);
/*     */     }
/* 627 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/message_delete.jspx"})
/*     */   public String message_delete(Integer[] ids, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 635 */     CmsSite site = CmsUtils.getSite(request);
/* 636 */     CmsUser user = CmsUtils.getUser(request);
/* 637 */     FrontUtils.frontData(request, model, site);
/* 638 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 640 */     if (!mcfg.isMemberOn()) {
/* 641 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 643 */     if (user == null) {
/* 644 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*     */ 
/* 647 */     Boolean permission = Boolean.valueOf(true);
/* 648 */     if ((ids != null) && (ids.length > 0)) {
/* 649 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1)) {
/* 650 */         CmsMessage message = this.messageMng.findById(ids[i.intValue()]);
/*     */ 
/* 652 */         if ((message.getMsgReceiverUser().equals(user)) || 
/* 653 */           (message.getMsgSendUser().equals(user))) continue;
/* 654 */         permission = Boolean.valueOf(false);
/*     */       }
/*     */ 
/* 657 */       if (permission.booleanValue()) {
/* 658 */         this.messageMng.deleteByIds(ids);
/* 659 */         for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1))
/* 660 */           log
/* 661 */             .info(
/* 662 */             "member CmsMessage delete CmsMessage success. id={}", 
/* 663 */             ids[i.intValue()]);
/*     */       }
/*     */       else {
/* 666 */         WebErrors errors = WebErrors.create(request);
/* 667 */         errors.addErrorCode("error.noPermissionsView");
/* 668 */         return FrontUtils.showError(request, response, model, errors);
/*     */       }
/*     */     }
/* 671 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   private WebErrors validateCaptcha(String captcha, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 676 */     WebErrors errors = WebErrors.create(request);
/*     */     try {
/* 678 */       if (!this.imageCaptchaService.validateResponseForID(
/* 679 */         this.session.getSessionId(request, response), captcha).booleanValue())
/*     */       {
/* 680 */         errors.addErrorCode("error.invalidCaptcha");
/* 681 */         return errors;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 684 */       errors.addErrorCode("error.exceptionCaptcha");
/* 685 */       log.warn("", e);
/* 686 */       return errors;
/*     */     }
/* 688 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.MessageAct
 * JD-Core Version:    0.6.0
 */