/*    */ package com.jeecms.cms.service;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ContentListenerAbstract
/*    */   implements ContentListener
/*    */ {
/*    */   public void afterChange(Content content, Map<String, Object> map)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void afterDelete(Content content)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void afterSave(Content content)
/*    */   {
/*    */   }
/*    */ 
/*    */   public Map<String, Object> preChange(Content content)
/*    */   {
/* 21 */     return null;
/*    */   }
/*    */ 
/*    */   public void preDelete(Content content)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void preSave(Content content)
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.service.ContentListenerAbstract
 * JD-Core Version:    0.6.0
 */