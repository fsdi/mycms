/*    */ package com.jeecms.common.web.session.id;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.safehaus.uuid.UUID;
/*    */ import org.safehaus.uuid.UUIDGenerator;
/*    */ 
/*    */ public class JugUUIDGenerator
/*    */   implements SessionIdGenerator
/*    */ {
/*    */   public String get()
/*    */   {
/* 12 */     UUID uuid = UUIDGenerator.getInstance().generateRandomBasedUUID();
/* 13 */     return StringUtils.remove(uuid.toString(), '-');
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 17 */     UUIDGenerator.getInstance().generateRandomBasedUUID();
/* 18 */     long time = System.currentTimeMillis();
/* 19 */     for (int i = 0; i < 100000; i++) {
/* 20 */       UUIDGenerator.getInstance().generateRandomBasedUUID();
/*    */     }
/* 22 */     time = System.currentTimeMillis() - time;
/* 23 */     System.out.println(time);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.session.id.JugUUIDGenerator
 * JD-Core Version:    0.6.0
 */