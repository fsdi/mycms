/*    */ package com.jeecms.common.file;
/*    */ 
/*    */ import com.jeecms.common.util.Num62;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.RandomStringUtils;
/*    */ 
/*    */ public class FileNameUtils
/*    */ {
/* 18 */   public static final DateFormat pathDf = new SimpleDateFormat("yyyyMM");
/*    */ 
/* 22 */   public static final DateFormat nameDf = new SimpleDateFormat("ddHHmmss");
/*    */ 
/*    */   public static String genPathName()
/*    */   {
/* 32 */     return pathDf.format(new Date());
/*    */   }
/*    */ 
/*    */   public static String genFileName()
/*    */   {
/* 43 */     return nameDf.format(new Date()) + 
/* 44 */       RandomStringUtils.random(4, Num62.N36_CHARS);
/*    */   }
/*    */ 
/*    */   public static String genFileName(String ext)
/*    */   {
/* 55 */     return genFileName() + "." + ext;
/*    */   }
/*    */ 
/*    */   public static String getFileSufix(String fileName) {
/* 59 */     int splitIndex = fileName.lastIndexOf(".");
/* 60 */     return fileName.substring(splitIndex + 1);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 64 */     System.out.println(genPathName());
/* 65 */     System.out.println(genFileName());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.file.FileNameUtils
 * JD-Core Version:    0.6.0
 */