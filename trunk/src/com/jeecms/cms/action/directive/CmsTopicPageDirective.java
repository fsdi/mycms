/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.CmsTopicMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.page.Pagination;
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
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsTopicPageDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String TPL_NAME = "topic_page";
/*    */   public static final String PARAM_CHANNEL_ID = "channelId";
/*    */   public static final String PARAM_RECOMMEND = "recommend";
/*    */ 
/*    */   @Autowired
/*    */   private CmsTopicMng cmsTopicMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 52 */     CmsSite site = FrontUtils.getSite(env);
/* 53 */     Pagination page = this.cmsTopicMng.getPageForTag(getChannelId(params), 
/* 54 */       getRecommend(params), FrontUtils.getPageNo(env), 
/* 55 */       FrontUtils.getCount(params));
/*    */ 
/* 57 */     Map paramWrap = new HashMap(
/* 58 */       params);
/* 59 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 60 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(page.getList()));
/* 61 */     Map origMap = 
/* 62 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 63 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 64 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 65 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 66 */       if (StringUtils.isBlank(listStyle)) {
/* 67 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 69 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 70 */       FrontUtils.includePagination(site, params, env);
/* 71 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 72 */       if (StringUtils.isBlank(listStyle)) {
/* 73 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 75 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 76 */       FrontUtils.includePagination(site, params, env);
/* 77 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 78 */       FrontUtils.includeTpl("topic_page", site, params, env);
/* 79 */       FrontUtils.includePagination(site, params, env);
/* 80 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 81 */       body.render(env.getOut());
/* 82 */       FrontUtils.includePagination(site, params, env);
/*    */     } else {
/* 84 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 86 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getChannelId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 91 */     return DirectiveUtils.getInt("channelId", params);
/*    */   }
/*    */ 
/*    */   private boolean getRecommend(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 96 */     Boolean recommend = DirectiveUtils.getBool("recommend", params);
/* 97 */     return recommend != null ? recommend.booleanValue() : false;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsTopicPageDirective
 * JD-Core Version:    0.6.0
 */