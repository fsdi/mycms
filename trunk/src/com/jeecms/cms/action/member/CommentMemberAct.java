/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CommentMemberAct
/*     */ {
/*  38 */   private static final Logger log = LoggerFactory.getLogger(CommentMemberAct.class);
/*     */   public static final String COMMENT_LIST = "tpl.commentLists";
/*     */   public static final String COMMENT_MNG = "tpl.commentMng";
/*     */   public static final String COMMENT_REPLY = "tpl.commentReply";
/*     */ 
/*     */   @Autowired
/*     */   private CmsCommentMng commentMng;
/*     */ 
/*     */   @RequestMapping({"/member/mycomments.jspx"})
/*     */   public String mycomments(Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  57 */     CmsSite site = CmsUtils.getSite(request);
/*  58 */     CmsUser user = CmsUtils.getUser(request);
/*  59 */     FrontUtils.frontData(request, model, site);
/*  60 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  62 */     if (!mcfg.isMemberOn()) {
/*  63 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  65 */     if (user == null) {
/*  66 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  68 */     Pagination pagination = this.commentMng.getPageForMember(site.getId(), null, 
/*  69 */       user.getId(), null, null, null, null, true, SimplePage.cpn(pageNo), 
/*  70 */       CookieUtils.getPageSize(request));
/*  71 */     model.addAttribute("pagination", pagination);
/*  72 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  73 */       "comment", "tpl.commentLists");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/comment_replay.jspx"})
/*     */   public String guestbook_replay(Integer id, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  81 */     CmsSite site = CmsUtils.getSite(request);
/*  82 */     CmsUser user = CmsUtils.getUser(request);
/*  83 */     FrontUtils.frontData(request, model, site);
/*  84 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  86 */     if (!mcfg.isMemberOn()) {
/*  87 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  89 */     if (user == null) {
/*  90 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  92 */     CmsComment comment = this.commentMng.findById(id);
/*  93 */     if (!comment.getCommentUser().equals(user)) {
/*  94 */       WebErrors errors = WebErrors.create(request);
/*  95 */       errors.addErrorCode("error.noPermissionsView");
/*  96 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/*  98 */     model.addAttribute("comment", comment);
/*  99 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 100 */       "comment", "tpl.commentReply");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/news_comments.jspx"})
/*     */   public String news_comments(Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 116 */     CmsSite site = CmsUtils.getSite(request);
/* 117 */     CmsUser user = CmsUtils.getUser(request);
/* 118 */     FrontUtils.frontData(request, model, site);
/* 119 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 121 */     if (!mcfg.isMemberOn()) {
/* 122 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 124 */     if (user == null) {
/* 125 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 127 */     Pagination pagination = this.commentMng.getPageForMember(site.getId(), null, 
/* 128 */       null, user.getId(), null, null, null, true, SimplePage.cpn(pageNo), 
/* 129 */       CookieUtils.getPageSize(request));
/* 130 */     model.addAttribute("pagination", pagination);
/* 131 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 132 */       "comment", "tpl.commentMng");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/comment_delete.jspx"})
/*     */   public String delete(Integer commentId, Integer userId, String ip, Integer pageNo, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 149 */     CmsSite site = CmsUtils.getSite(request);
/* 150 */     CmsUser user = CmsUtils.getUser(request);
/* 151 */     FrontUtils.frontData(request, model, site);
/* 152 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 154 */     if (!mcfg.isMemberOn()) {
/* 155 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 157 */     if (user == null) {
/* 158 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*     */ 
/* 162 */     if (commentId != null) {
/* 163 */       CmsComment bean = this.commentMng.deleteById(commentId);
/* 164 */       log.info("delete CmsComment id={}", bean.getId());
/*     */     }
/*     */     else {
/* 167 */       List comments = this.commentMng.getListForDel(site.getId(), 
/* 168 */         user.getId(), userId, ip);
/* 169 */       for (int i = 0; i < comments.size(); i++) {
/* 170 */         CmsComment bean = (CmsComment)comments.get(i);
/* 171 */         this.commentMng.deleteById(((CmsComment)comments.get(i)).getId());
/* 172 */         log.info("delete CmsComment id={}", bean.getId());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 182 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.CommentMemberAct
 * JD-Core Version:    0.6.0
 */