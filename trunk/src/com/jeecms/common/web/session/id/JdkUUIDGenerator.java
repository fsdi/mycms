/*    */ package com.jeecms.common.web.session.id;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.UUID;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class JdkUUIDGenerator
/*    */   implements SessionIdGenerator
/*    */ {
/*    */   public String get()
/*    */   {
/*  9 */     return StringUtils.remove(UUID.randomUUID().toString(), '-');
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 13 */     UUID.randomUUID();
/* 14 */     long time = System.currentTimeMillis();
/* 15 */     for (int i = 0; i < 100000; i++) {
/* 16 */       UUID.randomUUID();
/*    */     }
/* 18 */     time = System.currentTimeMillis() - time;
/* 19 */     System.out.println(time);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.session.id.JdkUUIDGenerator
 * JD-Core Version:    0.6.0
 */