/*    */ package com.jeecms.cms.action.admin.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*    */ import com.jeecms.cms.manager.main.CmsLogMng;
/*    */ import com.jeecms.cms.manager.main.CmsRoleMng;
/*    */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*    */ import com.jeecms.cms.manager.main.CmsUserMng;
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.ui.ModelMap;
/*    */ 
/*    */ public class CmsAdminAbstract
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   protected CmsSiteMng cmsSiteMng;
/*    */ 
/*    */   @Autowired
/*    */   protected ChannelMng channelMng;
/*    */ 
/*    */   @Autowired
/*    */   protected CmsRoleMng cmsRoleMng;
/*    */ 
/*    */   @Autowired
/*    */   protected CmsGroupMng cmsGroupMng;
/*    */ 
/*    */   @Autowired
/*    */   protected CmsLogMng cmsLogMng;
/*    */ 
/*    */   @Autowired
/*    */   protected CmsUserMng manager;
/*    */ 
/*    */   protected String channelsAddJson(Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 27 */     List channelList = this.channelMng.getTopList(siteId, false);
/* 28 */     model.addAttribute("channelList", channelList);
/* 29 */     response.setHeader("Cache-Control", "no-cache");
/* 30 */     response.setContentType("text/json;charset=UTF-8");
/* 31 */     return "admin/channels_add";
/*    */   }
/*    */ 
/*    */   protected String channelsEditJson(Integer userId, Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 37 */     List channelList = this.channelMng.getTopList(siteId, false);
/* 38 */     CmsUser user = this.manager.findById(userId);
/* 39 */     model.addAttribute("channelList", channelList);
/* 40 */     model.addAttribute("channelIds", user.getChannelIds(siteId));
/* 41 */     response.setHeader("Cache-Control", "no-cache");
/* 42 */     response.setContentType("text/json;charset=UTF-8");
/* 43 */     return "admin/channels_edit";
/*    */   }
/*    */ 
/*    */   protected void checkUserJson(HttpServletRequest request, HttpServletResponse response) {
/* 47 */     String username = RequestUtils.getQueryParam(request, "username");
/*    */     String pass;
/* 49 */     if (StringUtils.isBlank(username))
/* 50 */       pass = "false";
/*    */     else {
/* 52 */       pass = this.manager.usernameNotExist(username) ? "true" : "false";
/*    */     }
/* 54 */     ResponseUtils.renderJson(response, pass);
/*    */   }
/*    */ 
/*    */   protected void checkEmailJson(String email, HttpServletResponse response)
/*    */   {
/*    */     String pass;
/* 59 */     if (StringUtils.isBlank(email))
/* 60 */       pass = "false";
/*    */     else {
/* 62 */       pass = this.manager.emailNotExist(email) ? "true" : "false";
/*    */     }
/* 64 */     ResponseUtils.renderJson(response, pass);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsAdminAbstract
 * JD-Core Version:    0.6.0
 */