/*     */ package com.jeecms.common.upload;
/*     */ 
/*     */ import com.jeecms.common.util.Num62;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.RandomStringUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class UploadUtils
/*     */ {
/*  19 */   public static final DateFormat MONTH_FORMAT = new SimpleDateFormat(
/*  20 */     "/yyyyMM/ddHHmmss");
/*     */ 
/*  22 */   public static final DateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat(
/*  23 */     "yyyyMM");
/*     */ 
/*  25 */   public static final DateFormat SHORT_FORMAT = new SimpleDateFormat(
/*  26 */     "ddHHmmss");
/*     */ 
/*  42 */   protected static final Pattern ILLEGAL_CURRENT_FOLDER_PATTERN = Pattern.compile("^[^/]|[^/]$|/\\.{1,2}|\\\\|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}");
/*     */ 
/*     */   public static String generateMonthname()
/*     */   {
/*  29 */     return YEAR_MONTH_FORMAT.format(new Date());
/*     */   }
/*     */ 
/*     */   public static String generateFilename(String path, String ext) {
/*  33 */     return path + MONTH_FORMAT.format(new Date()) + 
/*  34 */       RandomStringUtils.random(4, Num62.N36_CHARS) + "." + ext;
/*     */   }
/*     */ 
/*     */   public static String generateByFilename(String path, String fileName, String ext) {
/*  38 */     return path + fileName + "." + ext;
/*     */   }
/*     */ 
/*     */   public static String sanitizeFileName(String filename)
/*     */   {
/*  57 */     if (StringUtils.isBlank(filename)) {
/*  58 */       return filename;
/*     */     }
/*  60 */     String name = forceSingleExtension(filename);
/*     */ 
/*  63 */     return name.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
/*     */   }
/*     */ 
/*     */   public static String sanitizeFolderName(String folderName)
/*     */   {
/*  78 */     if (StringUtils.isBlank(folderName)) {
/*  79 */       return folderName;
/*     */     }
/*     */ 
/*  82 */     return folderName.replaceAll(
/*  83 */       "\\.|\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
/*     */   }
/*     */ 
/*     */   public static boolean isValidPath(String path)
/*     */   {
/*  96 */     if (StringUtils.isBlank(path)) {
/*  97 */       return false;
/*     */     }
/*     */ 
/* 100 */     return !ILLEGAL_CURRENT_FOLDER_PATTERN.matcher(path).find();
/*     */   }
/*     */ 
/*     */   public static String forceSingleExtension(String filename)
/*     */   {
/* 113 */     return filename.replaceAll("\\.(?![^.]+$)", "_");
/*     */   }
/*     */ 
/*     */   public static boolean isSingleExtension(String filename)
/*     */   {
/* 125 */     return filename.matches("[^\\.]+\\.[^\\.]+");
/*     */   }
/*     */ 
/*     */   public static void checkDirAndCreate(File dir)
/*     */   {
/* 135 */     if (!dir.exists())
/* 136 */       dir.mkdirs();
/*     */   }
/*     */ 
/*     */   public static File getUniqueFile(File file)
/*     */   {
/* 150 */     if (!file.exists()) {
/* 151 */       return file;
/*     */     }
/* 153 */     File tmpFile = new File(file.getAbsolutePath());
/* 154 */     File parentDir = tmpFile.getParentFile();
/* 155 */     int count = 1;
/* 156 */     String extension = FilenameUtils.getExtension(tmpFile.getName());
/* 157 */     String baseName = FilenameUtils.getBaseName(tmpFile.getName());
/*     */     do
/* 159 */       tmpFile = new File(parentDir, baseName + "(" + count++ + ")." + 
/* 160 */         extension);
/* 158 */     while (
/* 161 */       tmpFile.exists());
/* 162 */     return tmpFile;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 166 */     System.out.println(generateFilename("/base", "gif"));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.upload.UploadUtils
 * JD-Core Version:    0.6.0
 */