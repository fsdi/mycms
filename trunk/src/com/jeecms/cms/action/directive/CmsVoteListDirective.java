/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
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
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsVoteListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String COUNT = "count";
/*    */   public static final String DEF = "def";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private CmsVoteTopicMng cmsVoteTopicMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 46 */     Integer count = getCount(params);
/* 47 */     if (count == null) {
/* 48 */       count = Integer.valueOf(2147483647);
/*    */     }
/* 50 */     Boolean def = getDef(params);
/* 51 */     List voteTopicList = this.cmsVoteTopicMng.getList(def, getSiteId(params), count.intValue());
/* 52 */     Map paramWrap = new HashMap(
/* 53 */       params);
/* 54 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(voteTopicList));
/* 55 */     Map origMap = 
/* 56 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 57 */     body.render(env.getOut());
/* 58 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getCount(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 63 */     return DirectiveUtils.getInt("count", params);
/*    */   }
/*    */ 
/*    */   private Boolean getDef(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 68 */     String def = DirectiveUtils.getString("def", params);
/* 69 */     if ("1".equals(def))
/* 70 */       return Boolean.valueOf(true);
/* 71 */     if ("2".equals(def)) {
/* 72 */       return Boolean.valueOf(false);
/*    */     }
/* 74 */     return null;
/*    */   }
/*    */ 
/*    */   private Integer getSiteId(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 80 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsVoteListDirective
 * JD-Core Version:    0.6.0
 */