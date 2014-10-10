/*    */ package com.jeecms.common.web;
/*    */ 
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public abstract class WebDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/* 12 */   protected final Logger log = LoggerFactory.getLogger(getClass());
/*    */   public static final String OUT_LIST = "tag_list";
/*    */   public static final String OUT_PAGINATION = "tag_pagination";
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.WebDirective
 * JD-Core Version:    0.6.0
 */