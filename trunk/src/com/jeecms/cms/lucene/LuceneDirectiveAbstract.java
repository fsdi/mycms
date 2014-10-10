/*    */ package com.jeecms.cms.lucene;
/*    */ 
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Date;
/*    */ import java.util.Map;
/*    */ 
/*    */ public abstract class LuceneDirectiveAbstract
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_QUERY = "q";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */   public static final String PARAM_CHANNEL_ID = "channelId";
/*    */   public static final String PARAM_START_DATE = "startDate";
/*    */   public static final String PARAM_END_DATE = "endDate";
/*    */   public static final String PARAM_CATEGORY = "category";
/*    */   public static final String PARAM_WORKPLACE = "workplace";
/*    */ 
/*    */   protected String getQuery(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 44 */     return DirectiveUtils.getString("q", params);
/*    */   }
/*    */ 
/*    */   protected Integer getSiteId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 49 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ 
/*    */   protected Integer getChannelId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 54 */     return DirectiveUtils.getInt("channelId", params);
/*    */   }
/*    */ 
/*    */   protected Date getStartDate(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 59 */     return DirectiveUtils.getDate("startDate", params);
/*    */   }
/*    */ 
/*    */   protected Date getEndDate(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 64 */     return DirectiveUtils.getDate("endDate", params);
/*    */   }
/*    */ 
/*    */   protected String getCategory(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 69 */     return DirectiveUtils.getString("category", params);
/*    */   }
/*    */ 
/*    */   protected String getWorkplace(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 74 */     return DirectiveUtils.getString("workplace", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneDirectiveAbstract
 * JD-Core Version:    0.6.0
 */