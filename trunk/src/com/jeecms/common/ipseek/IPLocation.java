/*    */ package com.jeecms.common.ipseek;
/*    */ 
/*    */ public class IPLocation
/*    */ {
/*  8 */   private String country = "";
/*  9 */   private String area = "";
/*    */ 
/*    */   public IPLocation() {
/*    */   }
/*    */   public IPLocation(String country, String area) {
/* 14 */     this.country = country;
/* 15 */     this.area = area;
/*    */   }
/*    */ 
/*    */   public IPLocation getCopy() {
/* 19 */     IPLocation ret = new IPLocation();
/* 20 */     ret.country = this.country;
/* 21 */     ret.area = this.area;
/* 22 */     return ret;
/*    */   }
/*    */ 
/*    */   public String getCountry() {
/* 26 */     return this.country;
/*    */   }
/*    */ 
/*    */   public void setCountry(String country) {
/* 30 */     this.country = country;
/*    */   }
/*    */ 
/*    */   public String getArea() {
/* 34 */     return this.area;
/*    */   }
/*    */ 
/*    */   public void setArea(String area)
/*    */   {
/* 39 */     if ("CZ88.NET".equals(area.trim())) {
/* 40 */       this.area = "局域网";
/* 41 */       return;
/*    */     }
/* 43 */     this.area = area;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.ipseek.IPLocation
 * JD-Core Version:    0.6.0
 */