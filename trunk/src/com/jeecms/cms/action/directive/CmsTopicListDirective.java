/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.CmsTopicMng;
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
/*    */ public class CmsTopicListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String TPL_NAME = "topic_list";
/*    */   public static final String PARAM_CHANNEL_ID = "channelId";
/*    */   public static final String PARAM_RECOMMEND = "recommend";
/*    */ 
/*    */   @Autowired
/*    */   private CmsTopicMng cmsTopicMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 54 */     CmsSite site = FrontUtils.getSite(env);
/* 55 */     List list = this.cmsTopicMng.getListForTag(getChannelId(params), 
/* 56 */       getRecommend(params), Integer.valueOf(FrontUtils.getCount(params)));
/*    */ 
/* 58 */     Map paramWrap = new HashMap(
/* 59 */       params);
/* 60 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 61 */     Map origMap = 
/* 62 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 63 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 64 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 65 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 66 */       if (StringUtils.isBlank(listStyle)) {
/* 67 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 69 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 70 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 71 */       if (StringUtils.isBlank(listStyle)) {
/* 72 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 74 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 75 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 76 */       FrontUtils.includeTpl("topic_list", site, params, env);
/* 77 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 78 */       body.render(env.getOut());
/*    */     } else {
/* 80 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 82 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getChannelId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 87 */     return DirectiveUtils.getInt("channelId", params);
/*    */   }
/*    */ 
/*    */   private boolean getRecommend(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 92 */     Boolean recommend = DirectiveUtils.getBool("recommend", params);
/* 93 */     return recommend != null ? recommend.booleanValue() : false;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsTopicListDirective
 * JD-Core Version:    0.6.0
 */