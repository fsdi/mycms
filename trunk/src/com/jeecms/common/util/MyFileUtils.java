/*    */ package com.jeecms.common.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MyFileUtils
/*    */ {
/*    */   public static List<File> getFiles(File folder)
/*    */   {
/* 13 */     List files = new ArrayList();
/* 14 */     iterateFolder(folder, files);
/* 15 */     return files;
/*    */   }
/*    */ 
/*    */   private static void iterateFolder(File folder, List<File> files) {
/* 19 */     File[] flist = folder.listFiles();
/* 20 */     files.add(folder);
/* 21 */     if ((flist == null) || (flist.length == 0))
/* 22 */       files.add(folder);
/*    */     else
/* 24 */       for (File f : flist)
/* 25 */         if (f.isDirectory())
/* 26 */           iterateFolder(f, files);
/*    */         else
/* 28 */           files.add(f);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.MyFileUtils
 * JD-Core Version:    0.6.0
 */