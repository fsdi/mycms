/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractContentDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.manager.main.ContentMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
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
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ContentListDirective extends AbstractContentDirective
/*    */ {
/*    */   public static final String TPL_NAME = "content_list";
/*    */   public static final String PARAM_IDS = "ids";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 47 */     CmsSite site = FrontUtils.getSite(env);
/* 48 */     List list = getList(params, env);
/*    */ 
/* 50 */     Map paramWrap = new HashMap(
/* 51 */       params);
/* 52 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 53 */     Map origMap = 
/* 54 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 55 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 56 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 57 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 58 */       if (StringUtils.isBlank(listStyle)) {
/* 59 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 61 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 62 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 63 */       if (StringUtils.isBlank(listStyle)) {
/* 64 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 66 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 67 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 68 */       FrontUtils.includeTpl("content_list", site, params, env);
/* 69 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 70 */       body.render(env.getOut());
/*    */     } else {
/* 72 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 74 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   protected List<Content> getList(Map<String, TemplateModel> params, Environment env)
/*    */     throws TemplateException
/*    */   {
/* 80 */     Integer[] ids = DirectiveUtils.getIntArray("ids", params);
/* 81 */     if (ids != null) {
/* 82 */       return this.contentMng.getListByIdsForTag(ids, getOrderBy(params));
/*    */     }
/* 84 */     return (List)super.getData(params, env);
/*    */   }
/*    */ 
/*    */   protected boolean isPage()
/*    */   {
/* 90 */     return false;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ContentListDirective
 * JD-Core Version:    0.6.0
 */