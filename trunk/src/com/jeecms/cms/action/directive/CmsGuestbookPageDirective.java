/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractCmsGuestbookDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
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
/*    */ public class CmsGuestbookPageDirective extends AbstractCmsGuestbookDirective
/*    */ {
/*    */   public static final String TPL_NAME = "guestbook_page";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     CmsSite site = FrontUtils.getSite(env);
/* 43 */     int pageNo = FrontUtils.getPageNo(env);
/* 44 */     int count = FrontUtils.getCount(params);
/* 45 */     Pagination page = this.cmsGuestbookMng.getPage(getSiteId(params), 
/* 46 */       getCtgId(params), null, getRecommend(params), getChecked(params), 
/* 47 */       getDesc(params), true, pageNo, count);
/*    */ 
/* 49 */     Map paramWrap = new HashMap(
/* 50 */       params);
/* 51 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 52 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getList()));
/* 53 */     Map origMap = 
/* 54 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 55 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 56 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 57 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 58 */       if (StringUtils.isBlank(listStyle)) {
/* 59 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 61 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 62 */       FrontUtils.includePagination(site, params, env);
/* 63 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 64 */       if (StringUtils.isBlank(listStyle)) {
/* 65 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 67 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 68 */       FrontUtils.includePagination(site, params, env);
/* 69 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 70 */       FrontUtils.includeTpl("guestbook_page", site, params, env);
/* 71 */       FrontUtils.includePagination(site, params, env);
/* 72 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 73 */       body.render(env.getOut());
/* 74 */       FrontUtils.includePagination(site, params, env);
/*    */     } else {
/* 76 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 78 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsGuestbookPageDirective
 * JD-Core Version:    0.6.0
 */