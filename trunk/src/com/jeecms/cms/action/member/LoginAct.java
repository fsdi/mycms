/*    */ package com.jeecms.cms.action.member;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class LoginAct
/*    */ {
/*    */   public static final String LOGIN_CSI = "tpl.loginCsi";
/*    */ 
/*    */   @RequestMapping({"/login_csi.jspx"})
/*    */   public String csi(HttpServletRequest request, ModelMap model)
/*    */   {
/* 29 */     CmsSite site = CmsUtils.getSite(request);
/*    */ 
/* 31 */     model.putAll(RequestUtils.getQueryParams(request));
/* 32 */     FrontUtils.frontData(request, model, site);
/* 33 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 34 */       "csi", "tpl.loginCsi");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.LoginAct
 * JD-Core Version:    0.6.0
 */