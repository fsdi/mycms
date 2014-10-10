/*    */ package com.jeecms.cms.action.directive.abs;
/*    */ 
/*    */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public abstract class AbstractCmsGuestbookDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */   public static final String PARAM_CTG_ID = "ctgId";
/*    */   public static final String PARAM_RECOMMEND = "recommend";
/*    */   public static final String PARAM_CHECKED = "checked";
/*    */   public static final String PARAM_ORDER_BY = "orderBy";
/*    */ 
/*    */   @Autowired
/*    */   protected CmsGuestbookMng cmsGuestbookMng;
/*    */ 
/*    */   protected Integer getSiteId(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 42 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ 
/*    */   protected Integer getCtgId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 47 */     return DirectiveUtils.getInt("ctgId", params);
/*    */   }
/*    */ 
/*    */   protected Boolean getRecommend(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 52 */     return DirectiveUtils.getBool("recommend", params);
/*    */   }
/*    */ 
/*    */   protected Boolean getChecked(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 57 */     return DirectiveUtils.getBool("checked", params);
/*    */   }
/*    */ 
/*    */   protected boolean getDesc(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 62 */     Integer orderBy = DirectiveUtils.getInt("orderBy", params);
/*    */ 
/* 64 */     return (orderBy == null) || (orderBy.intValue() == 0);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.abs.AbstractCmsGuestbookDirective
 * JD-Core Version:    0.6.0
 */