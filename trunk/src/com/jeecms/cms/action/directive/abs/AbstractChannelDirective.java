/*    */ package com.jeecms.cms.action.directive.abs;
/*    */ 
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public abstract class AbstractChannelDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_PARENT_ID = "parentId";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */   public static final String PARAM_HAS_CONTENT = "hasContent";
/*    */ 
/*    */   @Autowired
/*    */   protected ChannelMng channelMng;
/*    */ 
/*    */   protected boolean getHasContentOnly(Map<String, TemplateModel> params)
/*    */     throws TemplateException
/*    */   {
/* 34 */     Boolean hasContent = DirectiveUtils.getBool("hasContent", params);
/* 35 */     return (hasContent != null) && (hasContent.booleanValue());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.abs.AbstractChannelDirective
 * JD-Core Version:    0.6.0
 */