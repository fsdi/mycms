/*    */ package com.jeecms.cms.statistic;
/*    */ 
/*    */ public class FlowBean
/*    */ {
/*    */   private String accessDate;
/*    */   private String sessionId;
/*    */   private String page;
/*    */ 
/*    */   public FlowBean()
/*    */   {
/*    */   }
/*    */ 
/*    */   public FlowBean(String accessDate, String sessionId, String page)
/*    */   {
/* 11 */     this.accessDate = accessDate;
/* 12 */     this.sessionId = sessionId;
/* 13 */     this.page = page;
/*    */   }
/*    */ 
/*    */   public String getAccessDate() {
/* 17 */     return this.accessDate;
/*    */   }
/*    */ 
/*    */   public void setAccessDate(String accessDate) {
/* 21 */     this.accessDate = accessDate;
/*    */   }
/*    */ 
/*    */   public String getSessionId() {
/* 25 */     return this.sessionId;
/*    */   }
/*    */   public void setSessionId(String sessionId) {
/* 28 */     this.sessionId = sessionId;
/*    */   }
/*    */   public String getPage() {
/* 31 */     return this.page;
/*    */   }
/*    */   public void setPage(String page) {
/* 34 */     this.page = page;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 39 */     int prime = 31;
/* 40 */     int result = 1;
/* 41 */     result = 31 * result + (
/* 42 */       this.accessDate == null ? 0 : this.accessDate.hashCode());
/* 43 */     result = 31 * result + (this.page == null ? 0 : this.page.hashCode());
/* 44 */     result = 31 * result + (
/* 45 */       this.sessionId == null ? 0 : this.sessionId.hashCode());
/* 46 */     return result;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 51 */     if (this == obj)
/* 52 */       return true;
/* 53 */     if (obj == null)
/* 54 */       return false;
/* 55 */     if (getClass() != obj.getClass())
/* 56 */       return false;
/* 57 */     FlowBean other = (FlowBean)obj;
/* 58 */     if (this.accessDate == null) {
/* 59 */       if (other.accessDate != null)
/* 60 */         return false;
/* 61 */     } else if (!this.accessDate.equals(other.accessDate))
/* 62 */       return false;
/* 63 */     if (this.page == null) {
/* 64 */       if (other.page != null)
/* 65 */         return false;
/* 66 */     } else if (!this.page.equals(other.page))
/* 67 */       return false;
/* 68 */     if (this.sessionId == null) {
/* 69 */       if (other.sessionId != null)
/* 70 */         return false;
/* 71 */     } else if (!this.sessionId.equals(other.sessionId))
/* 72 */       return false;
/* 73 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.FlowBean
 * JD-Core Version:    0.6.0
 */