/*    */ package com.jeecms.common.page;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Pagination extends SimplePage
/*    */   implements Serializable, Paginable
/*    */ {
/*    */   private List<?> list;
/*    */ 
/*    */   public Pagination()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Pagination(int pageNo, int pageSize, int totalCount)
/*    */   {
/* 26 */     super(pageNo, pageSize, totalCount);
/*    */   }
/*    */ 
/*    */   public Pagination(int pageNo, int pageSize, int totalCount, List<?> list)
/*    */   {
/* 42 */     super(pageNo, pageSize, totalCount);
/* 43 */     this.list = list;
/*    */   }
/*    */ 
/*    */   public int getFirstResult()
/*    */   {
/* 52 */     return (this.pageNo - 1) * this.pageSize;
/*    */   }
/*    */ 
/*    */   public List<?> getList()
/*    */   {
/* 66 */     return this.list;
/*    */   }
/*    */ 
/*    */   public void setList(List list)
/*    */   {
/* 76 */     this.list = list;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.page.Pagination
 * JD-Core Version:    0.6.0
 */