/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsVoteDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private CmsVoteTopicMng cmsVoteTopicMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 40 */     CmsSite site = FrontUtils.getSite(env);
/*    */ 
/* 42 */     Integer id = getId(params);
/*    */     CmsVoteTopic vote;
/* 43 */     if (id != null) {
/* 44 */       vote = this.cmsVoteTopicMng.findById(id);
/*    */     } else {
/* 46 */       Integer siteId = getSiteId(params);
/* 47 */       if (siteId == null) {
/* 48 */         siteId = site.getId();
/*    */       }
/* 50 */       vote = this.cmsVoteTopicMng.getDefTopic(siteId);
/*    */     }
/*    */ 
/* 53 */     Map paramWrap = new HashMap(
/* 54 */       params);
/* 55 */     paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(vote));
/* 56 */     Map origMap = 
/* 57 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 58 */     body.render(env.getOut());
/* 59 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 64 */     return DirectiveUtils.getInt("id", params);
/*    */   }
/*    */ 
/*    */   private Integer getSiteId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 69 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsVoteDirective
 * JD-Core Version:    0.6.0
 */