/*    */ package com.jeecms.cms.lucene;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.service.ContentListenerAbstract;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.lucene.queryParser.ParseException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class ContentLuceneListener extends ContentListenerAbstract
/*    */ {
/* 19 */   private static final Logger log = LoggerFactory.getLogger(ContentLuceneListener.class);
/*    */   private static final String IS_CHECKED = "isChecked";
/*    */   private LuceneContentSvc luceneContentSvc;
/*    */ 
/*    */   public void afterSave(Content content)
/*    */   {
/* 27 */     if (content.isChecked())
/*    */       try {
/* 29 */         this.luceneContentSvc.createIndex(content);
/*    */       } catch (IOException e) {
/* 31 */         log.error("", e);
/*    */       }
/*    */   }
/*    */ 
/*    */   public Map<String, Object> preChange(Content content)
/*    */   {
/* 38 */     Map map = new HashMap();
/* 39 */     map.put("isChecked", Boolean.valueOf(content.isChecked()));
/* 40 */     return map;
/*    */   }
/*    */ 
/*    */   public void afterChange(Content content, Map<String, Object> map)
/*    */   {
/* 45 */     boolean pre = ((Boolean)map.get("isChecked")).booleanValue();
/* 46 */     boolean curr = content.isChecked();
/*    */     try {
/* 48 */       if ((pre) && (!curr))
/* 49 */         this.luceneContentSvc.deleteIndex(content.getId());
/* 50 */       else if ((!pre) && (curr))
/* 51 */         this.luceneContentSvc.createIndex(content);
/* 52 */       else if ((pre) && (curr))
/* 53 */         this.luceneContentSvc.updateIndex(content);
/*    */     }
/*    */     catch (IOException e) {
/* 56 */       log.error("", e);
/*    */     } catch (ParseException e) {
/* 58 */       log.error("", e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void afterDelete(Content content)
/*    */   {
/*    */     try {
/* 65 */       this.luceneContentSvc.deleteIndex(content.getId());
/*    */     } catch (IOException e) {
/* 67 */       log.error("", e);
/*    */     } catch (ParseException e) {
/* 69 */       log.error("", e);
/*    */     }
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setLuceneContentSvc(LuceneContentSvc luceneContentSvc)
/*    */   {
/* 77 */     this.luceneContentSvc = luceneContentSvc;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.ContentLuceneListener
 * JD-Core Version:    0.6.0
 */