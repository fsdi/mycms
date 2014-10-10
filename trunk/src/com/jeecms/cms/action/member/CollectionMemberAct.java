/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CollectionMemberAct
/*     */ {
/*     */   public static final String COLLECTION_LIST = "tpl.collectionList";
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng userMng;
/*     */ 
/*     */   @RequestMapping({"/member/collection_list.jspx"})
/*     */   public String collection_list(String queryTitle, Integer queryChannelId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  53 */     CmsSite site = CmsUtils.getSite(request);
/*  54 */     CmsUser user = CmsUtils.getUser(request);
/*  55 */     FrontUtils.frontData(request, model, site);
/*  56 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  58 */     if (!mcfg.isMemberOn()) {
/*  59 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  61 */     if (user == null) {
/*  62 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  64 */     Pagination p = this.contentMng.getPageForCollection(site.getId(), 
/*  65 */       user.getId(), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  66 */     model.addAttribute("pagination", p);
/*  67 */     if (!StringUtils.isBlank(queryTitle)) {
/*  68 */       model.addAttribute("queryTitle", queryTitle);
/*     */     }
/*  70 */     if (queryChannelId != null) {
/*  71 */       model.addAttribute("queryChannelId", queryChannelId);
/*     */     }
/*  73 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  74 */       "member", "tpl.collectionList");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/collect.jspx"})
/*     */   public void collect(Integer cId, Integer operate, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/*  81 */     CmsUser user = CmsUtils.getUser(request);
/*  82 */     JSONObject object = new JSONObject();
/*  83 */     if (user == null) {
/*  84 */       object.put("result", false);
/*     */     } else {
/*  86 */       object.put("result", true);
/*  87 */       this.userMng.updateUserConllection(user, cId, operate);
/*     */     }
/*  89 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/collect_exist.jspx"})
/*     */   public void collect_exist(Integer cId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/*  95 */     CmsSite site = CmsUtils.getSite(request);
/*  96 */     FrontUtils.frontData(request, model, site);
/*  97 */     CmsUser user = CmsUtils.getUser(request);
/*  98 */     JSONObject object = new JSONObject();
/*  99 */     FrontUtils.frontData(request, model, site);
/* 100 */     if (user == null) {
/* 101 */       object.put("result", false);
/*     */     }
/* 103 */     else if (user.getCollectContents().contains(this.contentMng.findById(cId)))
/* 104 */       object.put("result", true);
/*     */     else {
/* 106 */       object.put("result", false);
/*     */     }
/*     */ 
/* 109 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.CollectionMemberAct
 * JD-Core Version:    0.6.0
 */