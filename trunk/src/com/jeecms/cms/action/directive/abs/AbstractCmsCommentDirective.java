/*    */ package com.jeecms.cms.action.directive.abs;
/*    */ 
/*    */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public abstract class AbstractCmsCommentDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_CONTENT_ID = "contentId";
/*    */   public static final String PARAM_GREATER_THEN = "greaterThen";
/*    */   public static final String PARAM_CHECKED = "checked";
/*    */   public static final String PARAM_RECOMMEND = "recommend";
/*    */   public static final String PARAM_ORDER_BY = "orderBy";
/*    */ 
/*    */   @Autowired
/*    */   protected CmsCommentMng cmsCommentMng;
/*    */ 
/*    */   protected Integer getContentId(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 44 */     return DirectiveUtils.getInt("contentId", params);
/*    */   }
/*    */ 
/*    */   protected Integer getGreaterThen(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 49 */     return DirectiveUtils.getInt("greaterThen", params);
/*    */   }
/*    */ 
/*    */   protected Boolean getChecked(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 54 */     return DirectiveUtils.getBool("checked", params);
/*    */   }
/*    */ 
/*    */   protected boolean getRecommend(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 59 */     Boolean recommend = DirectiveUtils.getBool("recommend", params);
/* 60 */     return recommend != null ? recommend.booleanValue() : false;
/*    */   }
/*    */ 
/*    */   protected boolean getDesc(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 65 */     Integer orderBy = DirectiveUtils.getInt("orderBy", params);
/*    */ 
/* 67 */     return (orderBy == null) || (orderBy.intValue() == 0);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.abs.AbstractCmsCommentDirective
 * JD-Core Version:    0.6.0
 */