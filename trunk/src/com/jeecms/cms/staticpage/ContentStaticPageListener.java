/*    */ package com.jeecms.cms.staticpage;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.service.ContentListenerAbstract;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class ContentStaticPageListener extends ContentListenerAbstract
/*    */ {
/* 20 */   private static final Logger log = LoggerFactory.getLogger(ContentStaticPageListener.class);
/*    */   private static final String IS_CHECKED = "isChecked";
/*    */   private StaticPageSvc staticPageSvc;
/*    */ 
/*    */   public void afterSave(Content content)
/*    */   {
/* 28 */     if (content.isChecked())
/*    */       try {
/* 30 */         this.staticPageSvc.contentRelated(content);
/*    */       } catch (IOException e) {
/* 32 */         log.error("", e);
/*    */       } catch (TemplateException e) {
/* 34 */         log.error("", e);
/*    */       }
/*    */   }
/*    */ 
/*    */   public Map<String, Object> preChange(Content content)
/*    */   {
/* 41 */     Map map = new HashMap();
/* 42 */     map.put("isChecked", Boolean.valueOf(content.isChecked()));
/* 43 */     return map;
/*    */   }
/*    */ 
/*    */   public void afterChange(Content content, Map<String, Object> map)
/*    */   {
/* 48 */     boolean pre = ((Boolean)map.get("isChecked")).booleanValue();
/* 49 */     boolean curr = content.isChecked();
/*    */     try {
/* 51 */       if ((pre) && (!curr))
/* 52 */         this.staticPageSvc.deleteContent(content);
/* 53 */       else if ((!pre) && (curr))
/* 54 */         this.staticPageSvc.contentRelated(content);
/* 55 */       else if ((pre) && (curr))
/* 56 */         this.staticPageSvc.contentRelated(content);
/*    */     }
/*    */     catch (IOException e) {
/* 59 */       log.error("", e);
/*    */     } catch (TemplateException e) {
/* 61 */       log.error("", e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void afterDelete(Content content)
/*    */   {
/* 67 */     this.staticPageSvc.deleteContent(content);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setStaticPageSvc(StaticPageSvc staticPageSvc)
/*    */   {
/* 74 */     this.staticPageSvc = staticPageSvc;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.ContentStaticPageListener
 * JD-Core Version:    0.6.0
 */