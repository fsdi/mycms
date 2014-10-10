/*     */ package com.jeecms.common.page;
/*     */ 
/*     */ public class SimplePage
/*     */   implements Paginable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int DEF_COUNT = 20;
/* 121 */   protected int totalCount = 0;
/* 122 */   protected int pageSize = 20;
/* 123 */   protected int pageNo = 1;
/*     */ 
/*     */   public static int cpn(Integer pageNo)
/*     */   {
/*  17 */     return (pageNo == null) || (pageNo.intValue() < 1) ? 1 : pageNo.intValue();
/*     */   }
/*     */ 
/*     */   public SimplePage()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SimplePage(int pageNo, int pageSize, int totalCount)
/*     */   {
/*  34 */     setTotalCount(totalCount);
/*  35 */     setPageSize(pageSize);
/*  36 */     setPageNo(pageNo);
/*  37 */     adjustPageNo();
/*     */   }
/*     */ 
/*     */   public void adjustPageNo()
/*     */   {
/*  44 */     if (this.pageNo == 1) {
/*  45 */       return;
/*     */     }
/*  47 */     int tp = getTotalPage();
/*  48 */     if (this.pageNo > tp)
/*  49 */       this.pageNo = tp;
/*     */   }
/*     */ 
/*     */   public int getPageNo()
/*     */   {
/*  57 */     return this.pageNo;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/*  64 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public int getTotalCount()
/*     */   {
/*  71 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public int getTotalPage()
/*     */   {
/*  78 */     int totalPage = this.totalCount / this.pageSize;
/*  79 */     if ((totalPage == 0) || (this.totalCount % this.pageSize != 0)) {
/*  80 */       totalPage++;
/*     */     }
/*  82 */     return totalPage;
/*     */   }
/*     */ 
/*     */   public boolean isFirstPage()
/*     */   {
/*  89 */     return this.pageNo <= 1;
/*     */   }
/*     */ 
/*     */   public boolean isLastPage()
/*     */   {
/*  96 */     return this.pageNo >= getTotalPage();
/*     */   }
/*     */ 
/*     */   public int getNextPage()
/*     */   {
/* 103 */     if (isLastPage()) {
/* 104 */       return this.pageNo;
/*     */     }
/* 106 */     return this.pageNo + 1;
/*     */   }
/*     */ 
/*     */   public int getPrePage()
/*     */   {
/* 114 */     if (isFirstPage()) {
/* 115 */       return this.pageNo;
/*     */     }
/* 117 */     return this.pageNo - 1;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(int totalCount)
/*     */   {
/* 131 */     if (totalCount < 0)
/* 132 */       this.totalCount = 0;
/*     */     else
/* 134 */       this.totalCount = totalCount;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 144 */     if (pageSize < 1)
/* 145 */       this.pageSize = 20;
/*     */     else
/* 147 */       this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageNo(int pageNo)
/*     */   {
/* 157 */     if (pageNo < 1)
/* 158 */       this.pageNo = 1;
/*     */     else
/* 160 */       this.pageNo = pageNo;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.page.SimplePage
 * JD-Core Version:    0.6.0
 */