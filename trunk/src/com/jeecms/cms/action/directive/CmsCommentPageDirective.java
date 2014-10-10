/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractCmsCommentDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsCommentMng;
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
/*    */ public class CmsCommentPageDirective extends AbstractCmsCommentDirective
/*    */ {
/*    */   public static final String TPL_NAME = "comment_page";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     CmsSite site = FrontUtils.getSite(env);
/* 43 */     Pagination page = this.cmsCommentMng.getPageForTag(null, 
/* 44 */       getContentId(params), getGreaterThen(params), 
/* 45 */       getChecked(params), getRecommend(params), getDesc(params), 
/* 46 */       FrontUtils.getPageNo(env), FrontUtils.getCount(params));
/*    */ 
/* 48 */     Map paramWrap = new HashMap(
/* 49 */       params);
/* 50 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 51 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getList()));
/* 52 */     Map origMap = 
/* 53 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 54 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 55 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 56 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 57 */       if (StringUtils.isBlank(listStyle)) {
/* 58 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 60 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 61 */       FrontUtils.includePagination(site, params, env);
/* 62 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 63 */       if (StringUtils.isBlank(listStyle)) {
/* 64 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 66 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 67 */       FrontUtils.includePagination(site, params, env);
/* 68 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 69 */       FrontUtils.includeTpl("comment_page", site, params, env);
/* 70 */       FrontUtils.includePagination(site, params, env);
/* 71 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 72 */       body.render(env.getOut());
/* 73 */       FrontUtils.includePagination(site, params, env);
/*    */     } else {
/* 75 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 77 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsCommentPageDirective
 * JD-Core Version:    0.6.0
 */