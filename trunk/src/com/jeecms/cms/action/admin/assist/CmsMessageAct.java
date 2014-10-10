/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsMessageMng;
/*     */ import com.jeecms.cms.manager.assist.CmsReceiverMessageMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
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
/*     */ public class CmsMessageAct
/*     */ {
/*  41 */   private static final Logger log = LoggerFactory.getLogger(CmsMessageAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
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
/*     */   private CmsGroupMng groupMng;
/*     */ 
/*  48 */   @RequestMapping({"/message/v_list.do"})
/*     */   public String list(Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, HttpServletRequest request, HttpServletResponse response, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  49 */     CmsUser user = CmsUtils.getUser(request);
/*  50 */     Pagination pagination = null;
/*  51 */     String returnPage = "message/inbox";
/*  52 */     if (box == null) {
/*  53 */       box = Integer.valueOf(0);
/*     */     }
/*  55 */     if (box.equals(Integer.valueOf(0)))
/*     */     {
/*  57 */       pagination = this.receiverMessageMng.getPage(site.getId(), null, 
/*  58 */         user.getId(), title, sendBeginTime, sendEndTime, status, box, 
/*  59 */         Boolean.valueOf(false), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  60 */       returnPage = "message/inbox";
/*  61 */     } else if (box.equals(Integer.valueOf(1)))
/*     */     {
/*  63 */       pagination = this.messageMng.getPage(site.getId(), user.getId(), null, 
/*  64 */         title, sendBeginTime, sendEndTime, status, box, Boolean.valueOf(false), 
/*  65 */         SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  66 */       returnPage = "message/sendbox";
/*  67 */     } else if (box.equals(Integer.valueOf(2)))
/*     */     {
/*  69 */       pagination = this.messageMng.getPage(site.getId(), user.getId(), null, 
/*  70 */         title, sendBeginTime, sendEndTime, status, box, Boolean.valueOf(false), 
/*  71 */         SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  72 */       returnPage = "message/draftbox";
/*  73 */     } else if (box.equals(Integer.valueOf(3)))
/*     */     {
/*  75 */       pagination = this.receiverMessageMng.getPage(site.getId(), user.getId(), 
/*  76 */         user.getId(), title, sendBeginTime, sendEndTime, status, 
/*  77 */         box, Boolean.valueOf(false), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  78 */       returnPage = "message/trashbox";
/*     */     }
/*  80 */     model.addAttribute("msg", request.getAttribute("msg"));
/*  81 */     model.addAttribute("pagination", pagination);
/*  82 */     model.addAttribute("pageNo", pageNo);
/*  83 */     model.addAttribute("title", title);
/*  84 */     model.addAttribute("sendBeginTime", sendBeginTime);
/*  85 */     model.addAttribute("sendEndTime", sendEndTime);
/*  86 */     model.addAttribute("status", status);
/*  87 */     model.addAttribute("box", box);
/*  88 */     return returnPage; }
/*     */ 
/*     */   @RequestMapping({"/message/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  93 */     List groups = this.groupMng.getList();
/*  94 */     model.addAttribute("groupList", groups);
/*  95 */     return "message/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_send.do"})
/*     */   public String send(CmsMessage message, String username, Integer groupId, Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 104 */     CmsSite site = CmsUtils.getSite(request);
/* 105 */     CmsUser user = CmsUtils.getUser(request);
/* 106 */     Date now = new Date();
/* 107 */     CmsReceiverMessage receiverMessage = new CmsReceiverMessage();
/* 108 */     CmsUser msgReceiverUser = this.userMng.findByUsername(username);
/* 109 */     if (msgReceiverUser != null) {
/* 110 */       messageInfoSet(message, receiverMessage, user, msgReceiverUser, 
/* 111 */         now, site, request);
/*     */     }
/*     */ 
/* 114 */     if ((groupId != null) && (!groupId.equals(Integer.valueOf(-1))))
/*     */     {
/* 119 */       if (groupId.equals(Integer.valueOf(0)))
/*     */       {
/* 121 */         List users = this.userMng.getList(null, null, null, null, Boolean.valueOf(false), Boolean.valueOf(false), 
/* 122 */           null);
/* 123 */         if ((users != null) && (users.size() > 0)) {
/* 124 */           for (int i = 0; i < users.size(); i++) {
/* 125 */             CmsUser tempUser = (CmsUser)users.get(i);
/* 126 */             CmsMessage tempMsg = new CmsMessage();
/* 127 */             tempMsg.setMsgTitle(message.getMsgTitle());
/* 128 */             tempMsg.setMsgContent(message.getMsgContent());
/* 129 */             CmsReceiverMessage tempReceiverMsg = new CmsReceiverMessage();
/* 130 */             if (msgReceiverUser != null) {
/* 131 */               if (!tempUser.equals(msgReceiverUser))
/* 132 */                 messageInfoSet(tempMsg, tempReceiverMsg, user, 
/* 133 */                   tempUser, now, site, request);
/*     */             }
/*     */             else
/* 136 */               messageInfoSet(tempMsg, tempReceiverMsg, user, 
/* 137 */                 tempUser, now, site, request);
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 143 */         List users = this.userMng.getList(null, null, null, groupId, Boolean.valueOf(false), 
/* 144 */           Boolean.valueOf(false), null);
/* 145 */         if ((users != null) && (users.size() > 0)) {
/* 146 */           for (int i = 0; i < users.size(); i++) {
/* 147 */             CmsUser tempUser = (CmsUser)users.get(i);
/* 148 */             CmsMessage tempMsg = new CmsMessage();
/* 149 */             tempMsg.setMsgTitle(message.getMsgTitle());
/* 150 */             tempMsg.setMsgContent(message.getMsgContent());
/* 151 */             CmsReceiverMessage tempReceiverMsg = new CmsReceiverMessage();
/* 152 */             if (msgReceiverUser != null) {
/* 153 */               if (!tempUser.equals(msgReceiverUser))
/* 154 */                 messageInfoSet(tempMsg, tempReceiverMsg, user, 
/* 155 */                   tempUser, now, site, request);
/*     */             }
/*     */             else {
/* 158 */               messageInfoSet(tempMsg, tempReceiverMsg, user, 
/* 159 */                 tempUser, now, site, request);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 166 */     return list(pageNo, title, sendBeginTime, sendEndTime, status, Integer.valueOf(1), 
/* 167 */       request, response, model);
/*     */   }
/*     */ 
/*     */   private void messageInfoSet(CmsMessage message, CmsReceiverMessage receiverMessage, CmsUser sendUser, CmsUser receiverUser, Date sendTime, CmsSite site, HttpServletRequest request)
/*     */   {
/* 174 */     message.setMsgBox(Integer.valueOf(1));
/* 175 */     message.setMsgSendUser(sendUser);
/* 176 */     message.setMsgReceiverUser(receiverUser);
/* 177 */     message.setMsgStatus(Boolean.valueOf(false));
/* 178 */     message.setSendTime(sendTime);
/* 179 */     message.setSite(site);
/* 180 */     this.messageMng.save(message);
/* 181 */     receiverMessage.setMsgBox(Integer.valueOf(0));
/* 182 */     receiverMessage.setMsgContent(message.getMsgContent());
/* 183 */     receiverMessage.setMsgSendUser(sendUser);
/* 184 */     receiverMessage.setMsgReceiverUser(receiverUser);
/* 185 */     receiverMessage.setMsgStatus(false);
/* 186 */     receiverMessage.setMsgTitle(message.getMsgTitle());
/* 187 */     receiverMessage.setSendTime(sendTime);
/* 188 */     receiverMessage.setSite(site);
/* 189 */     receiverMessage.setMessage(message);
/*     */ 
/* 191 */     this.receiverMessageMng.save(receiverMessage);
/* 192 */     log.info("member CmsMessage send CmsMessage success. id={}", 
/* 193 */       message.getId());
/* 194 */     this.cmsLogMng.operating(request, "cmsMessage.log.send", "id=" + 
/* 195 */       message.getId() + ";title=" + message.getMsgTitle());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_save.do"})
/*     */   public String save(CmsMessage message, String username, Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 204 */     CmsSite site = CmsUtils.getSite(request);
/* 205 */     CmsUser user = CmsUtils.getUser(request);
/* 206 */     message.setMsgBox(Integer.valueOf(2));
/* 207 */     message.setMsgSendUser(user);
/* 208 */     CmsUser msgReceiverUser = this.userMng.findByUsername(username);
/* 209 */     message.setMsgReceiverUser(msgReceiverUser);
/* 210 */     message.setMsgStatus(Boolean.valueOf(false));
/*     */ 
/* 212 */     message.setSendTime(null);
/*     */ 
/* 214 */     message.setSite(site);
/* 215 */     this.messageMng.save(message);
/* 216 */     CmsReceiverMessage receiverMessage = new CmsReceiverMessage(message);
/* 217 */     receiverMessage.setMsgBox(Integer.valueOf(2));
/* 218 */     receiverMessage.setMessage(message);
/*     */ 
/* 220 */     this.receiverMessageMng.save(receiverMessage);
/* 221 */     this.cmsLogMng.operating(request, "cmsMessage.log.save", "id=" + 
/* 222 */       message.getId() + ";title=" + message.getMsgTitle());
/* 223 */     return list(pageNo, title, sendBeginTime, sendEndTime, status, Integer.valueOf(2), 
/* 224 */       request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_tosend.do"})
/*     */   public String message_tosend(Integer id, Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 233 */     CmsMessage message = this.messageMng.findById(id);
/* 234 */     message.setMsgBox(Integer.valueOf(1));
/* 235 */     message.setSendTime(new Date());
/* 236 */     this.messageMng.update(message);
/* 237 */     Set receiverMessageSet = message.getReceiverMsgs();
/* 238 */     Iterator it = receiverMessageSet.iterator();
/*     */ 
/* 240 */     while (it.hasNext()) {
/* 241 */       CmsReceiverMessage receiverMessage = (CmsReceiverMessage)it.next();
/* 242 */       receiverMessage.setMsgBox(Integer.valueOf(0));
/* 243 */       receiverMessage.setSendTime(new Date());
/* 244 */       receiverMessage.setMessage(message);
/*     */ 
/* 246 */       this.receiverMessageMng.update(receiverMessage);
/*     */     }
/* 248 */     log.info("member CmsMessage send CmsMessage success. id={}", 
/* 249 */       message.getId());
/* 250 */     this.cmsLogMng.operating(request, "cmsMessage.log.send", "id=" + 
/* 251 */       message.getId() + ";title=" + message.getMsgTitle());
/* 252 */     return list(pageNo, title, sendBeginTime, sendEndTime, status, Integer.valueOf(1), 
/* 253 */       request, response, model);
/*     */   }
/*     */   @RequestMapping({"/message/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 258 */     WebErrors errors = validateEdit(id, request);
/* 259 */     if (errors.hasErrors()) {
/* 260 */       return errors.showErrorPage(model);
/*     */     }
/* 262 */     CmsMessage message = this.messageMng.findById(id);
/* 263 */     model.addAttribute("message", message);
/* 264 */     return "message/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/o_update.do"})
/*     */   public String update(CmsMessage message, Integer pageNo, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 272 */     message = this.messageMng.update(message);
/*     */ 
/* 274 */     Set receiverMessageSet = message.getReceiverMsgs();
/* 275 */     Iterator it = receiverMessageSet.iterator();
/*     */ 
/* 277 */     while (it.hasNext()) {
/* 278 */       CmsReceiverMessage receiverMessage = (CmsReceiverMessage)it.next();
/* 279 */       receiverMessage.setMsgContent(message.getContentHtml());
/* 280 */       receiverMessage.setMsgReceiverUser(message.getMsgReceiverUser());
/* 281 */       receiverMessage.setMsgTitle(message.getMsgTitle());
/* 282 */       receiverMessage.setMessage(message);
/*     */ 
/* 284 */       this.receiverMessageMng.update(receiverMessage);
/*     */     }
/* 286 */     log.info("member CmsMessage update CmsMessage success. id={}", 
/* 287 */       message.getId());
/* 288 */     this.cmsLogMng.operating(request, "cmsMessage.log.update", "id=" + 
/* 289 */       message.getId() + ";title=" + message.getMsgTitle());
/* 290 */     return list(pageNo, title, sendBeginTime, sendEndTime, status, box, 
/* 291 */       request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_read.do"})
/*     */   public String read(Integer id, Integer box, HttpServletRequest request, ModelMap model)
/*     */   {
/* 298 */     CmsUser user = CmsUtils.getUser(request);
/* 299 */     CmsReceiverMessage message = this.receiverMessageMng.findById(id);
/* 300 */     if (message != null)
/*     */     {
/* 303 */       if (message.getMsgReceiverUser().equals(user)) {
/* 304 */         message.setMsgStatus(true);
/* 305 */         this.receiverMessageMng.update(message);
/*     */       }
/* 307 */       model.addAttribute("message", message);
/*     */     }
/*     */     else {
/* 310 */       CmsMessage msg = this.messageMng.findById(id);
/* 311 */       model.addAttribute("message", msg);
/*     */     }
/* 313 */     model.addAttribute("box", box);
/* 314 */     return "message/read";
/*     */   }
/*     */   @RequestMapping({"/message/v_forward.do"})
/*     */   public String forward(Integer id, HttpServletRequest request, ModelMap model) {
/* 319 */     CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(id);
/*     */ 
/* 321 */     if (receiverMessage != null) {
/* 322 */       model.addAttribute("message", receiverMessage);
/*     */     } else {
/* 324 */       CmsMessage message = this.messageMng.findById(id);
/* 325 */       model.addAttribute("message", message);
/*     */     }
/* 327 */     List groups = this.groupMng.getList();
/* 328 */     model.addAttribute("groupList", groups);
/* 329 */     return "message/add";
/*     */   }
/*     */   @RequestMapping({"/message/v_reply.do"})
/*     */   public String reply(Integer id, HttpServletRequest request, ModelMap model) {
/* 334 */     CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(id);
/* 335 */     model.addAttribute("message", receiverMessage);
/* 336 */     List groups = this.groupMng.getList();
/* 337 */     model.addAttribute("groupList", groups);
/* 338 */     return "message/reply";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_trash.do"})
/*     */   public void trash(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 344 */     CmsUser user = CmsUtils.getUser(request);
/* 345 */     JSONObject object = new JSONObject();
/*     */ 
/* 348 */     if (user == null) {
/* 349 */       object.put("result", false);
/*     */     }
/*     */     else {
/* 352 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1)) {
/* 353 */         CmsMessage message = this.messageMng.findById(ids[i.intValue()]);
/* 354 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/* 355 */         if ((message != null) && (message.getMsgSendUser().equals(user)))
/*     */         {
/* 357 */           receiverMessage = new CmsReceiverMessage();
/* 358 */           receiverMessage.setMsgBox(Integer.valueOf(3));
/* 359 */           receiverMessage.setMsgContent(message.getMsgContent());
/* 360 */           receiverMessage.setMsgSendUser(message.getMsgSendUser());
/* 361 */           receiverMessage.setMsgReceiverUser(user);
/* 362 */           receiverMessage.setMsgStatus(message.getMsgStatus().booleanValue());
/* 363 */           receiverMessage.setMsgTitle(message.getMsgTitle());
/* 364 */           receiverMessage.setSendTime(message.getSendTime());
/* 365 */           receiverMessage.setSite(message.getSite());
/* 366 */           receiverMessage.setMessage(null);
/*     */ 
/* 368 */           this.receiverMessageMng.save(receiverMessage);
/*     */ 
/* 370 */           Set receiverMessages = message
/* 371 */             .getReceiverMsgs();
/*     */ 
/* 373 */           if ((receiverMessages != null) && (receiverMessages.size() > 0)) {
/* 374 */             Iterator it = receiverMessages.iterator();
/* 375 */             while (it.hasNext()) {
/* 376 */               CmsReceiverMessage tempReceiverMessage = (CmsReceiverMessage)it.next();
/* 377 */               tempReceiverMessage.setMessage(null);
/* 378 */               this.receiverMessageMng.update(tempReceiverMessage);
/*     */             }
/*     */           }
/* 381 */           this.messageMng.deleteById(ids[i.intValue()]);
/* 382 */           this.cmsLogMng.operating(request, "cmsMessage.log.trash", "id=" + 
/* 383 */             message.getId() + ";title=" + 
/* 384 */             message.getMsgTitle());
/*     */         }
/* 386 */         if ((receiverMessage != null) && 
/* 387 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 388 */           receiverMessage.setMsgBox(Integer.valueOf(3));
/* 389 */           this.receiverMessageMng.update(receiverMessage);
/* 390 */           this.cmsLogMng.operating(request, "cmsMessage.log.trash", "id=" + 
/* 391 */             receiverMessage.getId() + ";title=" + 
/* 392 */             receiverMessage.getMsgTitle());
/*     */         }
/* 394 */         log.info("member CmsMessage trash CmsMessage success. id={}", 
/* 395 */           ids[i.intValue()]);
/*     */       }
/* 397 */       object.put("result", true);
/*     */     }
/* 399 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_revert.do"})
/*     */   public void revert(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 405 */     CmsUser user = CmsUtils.getUser(request);
/* 406 */     JSONObject object = new JSONObject();
/*     */ 
/* 408 */     if (user == null) {
/* 409 */       object.put("result", false);
/*     */     } else {
/* 411 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1)) {
/* 412 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/*     */ 
/* 414 */         if ((receiverMessage != null) && 
/* 415 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 416 */           receiverMessage.setMsgBox(Integer.valueOf(0));
/* 417 */           this.receiverMessageMng.update(receiverMessage);
/* 418 */           this.cmsLogMng.operating(request, "cmsMessage.log.revert", "id=" + 
/* 419 */             receiverMessage.getId() + ";title=" + receiverMessage.getMsgTitle());
/*     */         }
/* 421 */         log.info("member CmsMessage revert CmsMessage success. id={}", 
/* 422 */           ids[i.intValue()]);
/*     */       }
/* 424 */       object.put("result", true);
/*     */     }
/* 426 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_empty.do"})
/*     */   public void empty(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 432 */     CmsUser user = CmsUtils.getUser(request);
/* 433 */     JSONObject object = new JSONObject();
/* 434 */     CmsMessage message = null;
/*     */ 
/* 436 */     if (user == null) {
/* 437 */       object.put("result", false);
/*     */     } else {
/* 439 */       for (Integer i = Integer.valueOf(0); i.intValue() < ids.length; i = Integer.valueOf(i.intValue() + 1))
/*     */       {
/* 441 */         CmsReceiverMessage receiverMessage = this.receiverMessageMng.findById(ids[i.intValue()]);
/* 442 */         if ((receiverMessage != null) && 
/* 443 */           (receiverMessage.getMsgReceiverUser().equals(user))) {
/* 444 */           this.receiverMessageMng.deleteById(ids[i.intValue()]);
/*     */         }
/*     */         else {
/* 447 */           message = receiverMessage.getMessage();
/*     */ 
/* 451 */           if (receiverMessage.getMsgBox().equals(Integer.valueOf(3)))
/*     */           {
/* 453 */             receiverMessage.setMessage(null);
/* 454 */             if (message != null)
/* 455 */               this.messageMng.deleteById(message.getId());
/*     */           }
/*     */           else
/*     */           {
/* 459 */             receiverMessage.setMessage(null);
/*     */           }
/* 461 */           if ((message != null) && 
/* 462 */             (message.getMsgSendUser().equals(user))) {
/* 463 */             this.messageMng.deleteById(message.getId());
/*     */           }
/* 465 */           this.cmsLogMng.operating(request, "cmsMessage.log.empty", "id=" + 
/* 466 */             message.getId() + ";title=" + 
/* 467 */             message.getMsgTitle());
/*     */         }
/* 469 */         log.info("member CmsMessage empty CmsMessage success. id={}", 
/* 470 */           ids[i.intValue()]);
/*     */       }
/* 472 */       object.put("result", true);
/*     */     }
/* 474 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_findUser.do"})
/*     */   public void findUserByUserName(String username, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 480 */     CmsUser user = CmsUtils.getUser(request);
/* 481 */     JSONObject object = new JSONObject();
/* 482 */     if (user == null) {
/* 483 */       object.put("result", false);
/*     */     } else {
/* 485 */       Boolean exist = Boolean.valueOf(this.userMng.usernameNotExist(username));
/* 486 */       object.put("result", true);
/* 487 */       object.put("exist", exist);
/*     */     }
/* 489 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/message/v_countUnreadMsg.do"})
/*     */   public void findUnreadMessagesByUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 496 */     CmsUser user = CmsUtils.getUser(request);
/* 497 */     CmsSite site = CmsUtils.getSite(request);
/* 498 */     JSONObject object = new JSONObject();
/* 499 */     if (user == null) {
/* 500 */       object.put("result", false);
/*     */     } else {
/* 502 */       List receiverMessages = this.receiverMessageMng
/* 503 */         .getList(site.getId(), null, user.getId(), null, null, 
/* 504 */         null, Boolean.valueOf(false), Integer.valueOf(0), Boolean.valueOf(false));
/* 505 */       object.put("result", true);
/* 506 */       if ((receiverMessages != null) && (receiverMessages.size() > 0))
/* 507 */         object.put("count", receiverMessages.size());
/*     */       else {
/* 509 */         object.put("count", 0);
/*     */       }
/* 511 */       object.put("result", true);
/*     */     }
/* 513 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 517 */     WebErrors errors = WebErrors.create(request);
/* 518 */     CmsSite site = CmsUtils.getSite(request);
/* 519 */     if (vldExist(id, site.getId(), errors)) {
/* 520 */       return errors;
/*     */     }
/* 522 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 526 */     if (errors.ifNull(id, "id")) {
/* 527 */       return true;
/*     */     }
/* 529 */     CmsMessage entity = this.messageMng.findById(id);
/* 530 */     if (errors.ifNotExist(entity, CmsMessage.class, id)) {
/* 531 */       return true;
/*     */     }
/* 533 */     if (!entity.getSite().getId().equals(siteId)) {
/* 534 */       errors.notInSite(CmsMessage.class, id);
/* 535 */       return true;
/*     */     }
/* 537 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsMessageAct
 * JD-Core Version:    0.6.0
 */