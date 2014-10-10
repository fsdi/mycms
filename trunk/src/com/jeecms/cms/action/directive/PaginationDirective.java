/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class PaginationDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_CONTENT = "content";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 38 */     CmsSite site = FrontUtils.getSite(env);
/* 39 */     String content = DirectiveUtils.getString("content", params);
/* 40 */     if ("1".equals(content)) {
/* 41 */       String sysPage = DirectiveUtils.getString("sysPage", params);
/* 42 */       String userPage = DirectiveUtils.getString("userPage", params);
/* 43 */       if (!StringUtils.isBlank(sysPage)) {
/* 44 */         String tpl = "/WEB-INF/t/cms_sys_defined/style_page/content_" + sysPage + ".html";
/* 45 */         env.include(tpl, "UTF-8", true);
/* 46 */       } else if (!StringUtils.isBlank(userPage)) {
/* 47 */         String tpl = FrontUtils.getTplPath(site.getSolutionPath(), 
/* 48 */           "style_page", userPage);
/* 49 */         env.include(tpl, "UTF-8", true);
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 54 */       FrontUtils.includePagination(site, params, env);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.PaginationDirective
 * JD-Core Version:    0.6.0
 */