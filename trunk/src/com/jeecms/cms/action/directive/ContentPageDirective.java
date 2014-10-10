/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractContentDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
/*    */ import com.jeecms.common.web.freemarker.ParamsRequiredException;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ContentPageDirective extends AbstractContentDirective
/*    */ {
/*    */   public static final String TPL_NAME = "content_page";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     CmsSite site = FrontUtils.getSite(env);
/* 43 */     Pagination page = (Pagination)super.getData(params, env);
/*    */ 
/* 45 */     Map paramWrap = new HashMap(
/* 46 */       params);
/* 47 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 48 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getList()));
/* 49 */     Map origMap = 
/* 50 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 51 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 52 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 53 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 54 */       if (StringUtils.isBlank(listStyle)) {
/* 55 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 57 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 58 */       FrontUtils.includePagination(site, params, env);
/* 59 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 60 */       if (StringUtils.isBlank(listStyle)) {
/* 61 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 63 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 64 */       FrontUtils.includePagination(site, params, env);
/* 65 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 66 */       FrontUtils.includeTpl("content_page", site, params, env);
/* 67 */       FrontUtils.includePagination(site, params, env);
/* 68 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 69 */       if (body != null) {
/* 70 */         body.render(env.getOut());
/* 71 */         FrontUtils.includePagination(site, params, env);
/*    */       }
/*    */     } else {
/* 74 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 76 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   protected boolean isPage()
/*    */   {
/* 81 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ContentPageDirective
 * JD-Core Version:    0.6.0
 */