/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.ContentTagMng;
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
/*    */ public class ContentTagListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String TPL_NAME = "tag_list";
/*    */ 
/*    */   @Autowired
/*    */   private ContentTagMng contentTagMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 44 */     CmsSite site = FrontUtils.getSite(env);
/* 45 */     List list = this.contentTagMng.getListForTag(
/* 46 */       Integer.valueOf(FrontUtils.getCount(params)));
/*    */ 
/* 48 */     Map paramWrap = new HashMap(
/* 49 */       params);
/* 50 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 51 */     Map origMap = 
/* 52 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 53 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 54 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 55 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 56 */       if (StringUtils.isBlank(listStyle)) {
/* 57 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 59 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 60 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 61 */       if (StringUtils.isBlank(listStyle)) {
/* 62 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 64 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 65 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 66 */       FrontUtils.includeTpl("tag_list", site, params, env);
/* 67 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 68 */       body.render(env.getOut());
/*    */     } else {
/* 70 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 72 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ContentTagListDirective
 * JD-Core Version:    0.6.0
 */