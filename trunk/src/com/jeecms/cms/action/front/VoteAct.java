/*     */ package com.jeecms.cms.action.front;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteRecordMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteReplyMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteSubTopicMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.util.ArrayUtils;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.http.Cookie;
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
/*     */ public class VoteAct
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(VoteAct.class);
/*     */   public static final String VOTE_COOKIE_PREFIX = "_vote_cookie_";
/*     */   public static final String VOTE_INPUT = "tpl.voteInput";
/*     */   public static final String VOTE_RESULT = "tpl.voteResult";
/*     */   public static final String VOTE_REPLY_RESULT = "tpl.voteReplyResult";
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteTopicMng cmsVoteTopicMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteSubTopicMng cmsVoteSubTopicMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteReplyMng cmsVoteReplyMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteRecordMng cmsVoteRecordMng;
/*     */ 
/*     */   @RequestMapping(value={"/vote_result.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String result(Integer voteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  55 */     CmsSite site = CmsUtils.getSite(request);
/*  56 */     CmsVoteTopic vote = null;
/*  57 */     if (voteId != null) {
/*  58 */       vote = this.cmsVoteTopicMng.findById(voteId);
/*     */     }
/*  60 */     if (vote != null) {
/*  61 */       model.addAttribute("vote", vote);
/*  62 */       FrontUtils.frontData(request, model, site);
/*  63 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  64 */         "special", "tpl.voteResult");
/*     */     }
/*  66 */     WebErrors errors = WebErrors.create(request);
/*  67 */     errors.addError("error.vote.novotefind");
/*  68 */     return FrontUtils.showError(request, response, model, errors);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/vote_reply_view.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String reply_view(Integer subId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  75 */     CmsSite site = CmsUtils.getSite(request);
/*  76 */     CmsVoteSubTopic subTopic = null;
/*  77 */     if (subId != null) {
/*  78 */       subTopic = this.cmsVoteSubTopicMng.findById(subId);
/*     */     }
/*  80 */     if (subTopic != null) {
/*  81 */       Pagination pagination = this.cmsVoteReplyMng.getPage(subId, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  82 */       model.addAttribute("subTopic", subTopic);
/*  83 */       model.addAttribute("pagination", pagination);
/*  84 */       FrontUtils.frontData(request, model, site);
/*  85 */       FrontUtils.frontPageData(request, model);
/*  86 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  87 */         "special", "tpl.voteReplyResult");
/*     */     }
/*  89 */     WebErrors errors = WebErrors.create(request);
/*  90 */     errors.addError("error.vote.novotesubfind");
/*  91 */     return FrontUtils.showError(request, response, model, errors);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/vote.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(Integer voteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  98 */     CmsSite site = CmsUtils.getSite(request);
/*  99 */     CmsVoteTopic vote = null;
/* 100 */     if (voteId != null) {
/* 101 */       vote = this.cmsVoteTopicMng.findById(voteId);
/*     */     }
/* 103 */     if (vote != null) {
/* 104 */       model.addAttribute("vote", vote);
/* 105 */       FrontUtils.frontData(request, model, site);
/* 106 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 107 */         "special", "tpl.voteInput");
/*     */     }
/* 109 */     WebErrors errors = WebErrors.create(request);
/* 110 */     errors.addError("error.vote.novotefind");
/* 111 */     return FrontUtils.showError(request, response, model, errors);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/vote.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(Integer voteId, Integer[] subIds, String[] reply, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 118 */     CmsSite site = CmsUtils.getSite(request);
/* 119 */     CmsUser user = CmsUtils.getUser(request);
/* 120 */     String ip = RequestUtils.getIpAddr(request);
/* 121 */     String cookieName = "_vote_cookie_" + voteId;
/* 122 */     Cookie cookie = CookieUtils.getCookie(request, cookieName);
/*     */     String cookieValue;
/* 124 */     if ((cookie != null) && (!StringUtils.isBlank(cookie.getValue())))
/* 125 */       cookieValue = cookie.getValue();
/*     */     else {
/* 127 */       cookieValue = null;
/*     */     }
/* 129 */     List itemIds = getItemIdsParam(request, subIds);
/* 130 */     Integer[] subTxtIds = (Integer[])null;
/* 131 */     if ((reply != null) && (reply.length > 0)) {
/* 132 */       subTxtIds = new Integer[reply.length];
/* 133 */       List subTxtIdList = new ArrayList();
/* 134 */       for (int i = 0; i < itemIds.size(); i++) {
/* 135 */         if (itemIds.get(i) == null) {
/* 136 */           subTxtIdList.add(subIds[i]);
/*     */         }
/*     */       }
/*     */ 
/* 140 */       subTxtIds = (Integer[])subTxtIdList.toArray(subTxtIds);
/*     */     }
/* 142 */     if (!validateSubmit(voteId, subIds, itemIds, user, ip, cookieValue, model)) {
/* 143 */       if (cookieValue == null)
/*     */       {
/* 145 */         cookieValue = StringUtils.remove(UUID.randomUUID().toString(), 
/* 146 */           "-");
/*     */ 
/* 148 */         CookieUtils.addCookie(request, response, cookieName, 
/* 149 */           cookieValue, Integer.valueOf(2147483647), null);
/*     */       }
/* 151 */       CmsVoteTopic vote = this.cmsVoteTopicMng.vote(voteId, subTxtIds, itemIds, reply, user, ip, cookieValue);
/* 152 */       model.addAttribute("status", Integer.valueOf(0));
/* 153 */       model.addAttribute("vote", vote);
/*     */ 
/* 155 */       log.info("vote CmsVote id={}, name={}", vote.getId(), 
/* 156 */         vote.getTitle());
/*     */     }
/* 158 */     FrontUtils.frontData(request, model, site);
/* 159 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 160 */       "special", "tpl.voteResult");
/*     */   }
/*     */ 
/*     */   private List<Integer[]> getItemIdsParam(HttpServletRequest request, Integer[] subIds) {
/* 164 */     List itemIds = new ArrayList();
/* 165 */     for (Integer subId : subIds) {
/* 166 */       itemIds.add(getSubItemIdsParam(request, subId));
/*     */     }
/* 168 */     return itemIds;
/*     */   }
/*     */ 
/*     */   private Integer[] getSubItemIdsParam(HttpServletRequest request, Integer subId) {
/* 172 */     String[] paramValues = request.getParameterValues("itemIds" + subId);
/* 173 */     return ArrayUtils.convertStrArrayToInt(paramValues);
/*     */   }
/*     */ 
/*     */   private boolean validateSubmit(Integer topicId, Integer[] subIds, List<Integer[]> itemIds, CmsUser user, String ip, String cookie, ModelMap model)
/*     */   {
/* 180 */     if (topicId == null) {
/* 181 */       model.addAttribute("status", Integer.valueOf(1));
/* 182 */       return true;
/*     */     }
/*     */ 
/* 185 */     if ((itemIds == null) || (itemIds.size() <= 0)) {
/* 186 */       model.addAttribute("status", Integer.valueOf(2));
/* 187 */       return true;
/*     */     }
/*     */ 
/* 190 */     for (int i = 0; i < subIds.length; i++) {
/* 191 */       if ((this.cmsVoteSubTopicMng.findById(subIds[i]).getIsText()) || 
/* 192 */         (itemIds.get(i) != null)) continue;
/* 193 */       model.addAttribute("status", Integer.valueOf(2));
/* 194 */       return true;
/*     */     }
/*     */ 
/* 198 */     CmsVoteTopic topic = this.cmsVoteTopicMng.findById(topicId);
/*     */ 
/* 200 */     if (topic == null) {
/* 201 */       model.addAttribute("status", Integer.valueOf(100));
/* 202 */       return true;
/*     */     }
/*     */ 
/* 205 */     List<Integer> itemTotalIds = new ArrayList();
/* 206 */     for (Integer[] ids : itemIds) {
/* 207 */       if ((ids != null) && (ids.length > 0)) {
/* 208 */         for (Integer id : ids) {
/* 209 */           itemTotalIds.add(id);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 214 */     for (Integer itemId : itemTotalIds) {
/* 215 */       boolean contains = false;
/* 216 */       for (CmsVoteItem item : topic.getItems()) {
/* 217 */         if (item.getId().equals(itemId)) {
/* 218 */           contains = true;
/* 219 */           break;
/*     */         }
/*     */       }
/* 222 */       if (!contains) {
/* 223 */         model.addAttribute("status", Integer.valueOf(101));
/* 224 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 229 */     if ((topic.getRestrictMember().booleanValue()) && (user == null)) {
/* 230 */       model.addAttribute("status", Integer.valueOf(501));
/* 231 */       return true;
/*     */     }
/*     */ 
/* 235 */     if (topic.getDisabled().booleanValue()) {
/* 236 */       model.addAttribute("status", Integer.valueOf(200));
/* 237 */       return true;
/*     */     }
/*     */ 
/* 246 */     long now = System.currentTimeMillis();
/*     */ 
/* 248 */     Date start = topic.getStartTime();
/* 249 */     if ((start != null) && (now < start.getTime())) {
/* 250 */       model.addAttribute("status", Integer.valueOf(202));
/* 251 */       model.addAttribute("startTime", start);
/* 252 */       return true;
/*     */     }
/*     */ 
/* 255 */     Date end = topic.getEndTime();
/* 256 */     if ((end != null) && (now > end.getTime())) {
/* 257 */       model.addAttribute("status", Integer.valueOf(203));
/* 258 */       model.addAttribute("endTime", end);
/* 259 */       return true;
/*     */     }
/* 261 */     Integer hour = topic.getRepeateHour();
/* 262 */     if ((hour == null) || (hour.intValue() > 0))
/*     */     {
/* 265 */       if (topic.getRestrictMember().booleanValue()) {
/* 266 */         Date vtime = this.cmsVoteRecordMng.lastVoteTimeByUserId(user.getId(), 
/* 267 */           topicId);
/* 268 */         if ((hour == null) || 
/* 269 */           (vtime.getTime() + hour.intValue() * 60 * 60 * 1000 > now)) {
/* 270 */           model.addAttribute("status", Integer.valueOf(204));
/* 271 */           return true;
/*     */         }
/*     */       }
/*     */ 
/* 275 */       if (topic.getRestrictIp().booleanValue()) {
/* 276 */         Date vtime = this.cmsVoteRecordMng.lastVoteTimeByIp(ip, topicId);
/* 277 */         if ((hour == null) || 
/* 278 */           (vtime.getTime() + hour.intValue() * 60 * 60 * 1000 > now)) {
/* 279 */           model.addAttribute("status", Integer.valueOf(205));
/* 280 */           return true;
/*     */         }
/*     */       }
/*     */ 
/* 284 */       if ((topic.getRestrictCookie().booleanValue()) && (cookie != null)) {
/* 285 */         Date vtime = this.cmsVoteRecordMng.lastVoteTimeByCookie(cookie, topicId);
/* 286 */         if ((hour == null) || 
/* 287 */           (vtime.getTime() + hour.intValue() * 60 * 60 * 1000 > now)) {
/* 288 */           model.addAttribute("status", Integer.valueOf(206));
/* 289 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 293 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.VoteAct
 * JD-Core Version:    0.6.0
 */