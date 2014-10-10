/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
/*    */ import com.jeecms.common.web.freemarker.ParamsRequiredException;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsGuestbookCtgListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String TPL_NAME = "geustbook_ctg_list";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private CmsGuestbookCtgMng cmsGuestbookCtgMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 49 */     CmsSite site = FrontUtils.getSite(env);
/*    */ 
/* 51 */     List list = this.cmsGuestbookCtgMng
/* 52 */       .getList(getSiteId(params));
/*    */ 
/* 54 */     Map paramWrap = new HashMap(
/* 55 */       params);
/* 56 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 57 */     Map origMap = 
/* 58 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 59 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 60 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 61 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 62 */       if (StringUtils.isBlank(listStyle)) {
/* 63 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 65 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 66 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 67 */       if (StringUtils.isBlank(listStyle)) {
/* 68 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 70 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 71 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 72 */       FrontUtils.includeTpl("geustbook_ctg_list", site, params, env);
/* 73 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 74 */       body.render(env.getOut());
/*    */     } else {
/* 76 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 78 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getSiteId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 83 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsGuestbookCtgListDirective
 * JD-Core Version:    0.6.0
 */