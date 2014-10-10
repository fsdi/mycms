/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseContentTxt;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ContentTxt extends BaseContentTxt
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*   8 */   public static String PAGE_START = "<p>[NextPage]";
/*   9 */   public static String PAGE_END = "[/NextPage]</p>";
/*     */ 
/*     */   public int getTxtCount() {
/*  12 */     String txt = getTxt();
/*  13 */     if (StringUtils.isBlank(txt)) {
/*  14 */       return 1;
/*     */     }
/*  16 */     return StringUtils.countMatches(txt, PAGE_START) + 1;
/*     */   }
/*     */ 
/*     */   public String getTxtByNo(int pageNo)
/*     */   {
/*  21 */     String txt = getTxt();
/*  22 */     if ((StringUtils.isBlank(txt)) || (pageNo < 1)) {
/*  23 */       return null;
/*     */     }
/*  25 */     int start = 0; int end = 0;
/*  26 */     for (int i = 0; i < pageNo; i++)
/*     */     {
/*  28 */       if (i != 0) {
/*  29 */         start = txt.indexOf(PAGE_END, end);
/*  30 */         if (start == -1) {
/*  31 */           return null;
/*     */         }
/*  33 */         start += PAGE_END.length();
/*     */       }
/*     */ 
/*  36 */       end = txt.indexOf(PAGE_START, start);
/*  37 */       if (end == -1) {
/*  38 */         end = txt.length();
/*     */       }
/*     */     }
/*  41 */     return txt.substring(start, end);
/*     */   }
/*     */ 
/*     */   public String getTitleByNo(int pageNo) {
/*  45 */     if (pageNo < 1) {
/*  46 */       return null;
/*     */     }
/*  48 */     String title = getContent().getTitle();
/*  49 */     if (pageNo == 1) {
/*  50 */       return title;
/*     */     }
/*  52 */     String txt = getTxt();
/*  53 */     int start = 0; int end = 0;
/*  54 */     for (int i = 1; i < pageNo; i++) {
/*  55 */       start = txt.indexOf(PAGE_START, end);
/*  56 */       if (start == -1) {
/*  57 */         return title;
/*     */       }
/*  59 */       start += PAGE_START.length();
/*     */ 
/*  61 */       end = txt.indexOf(PAGE_END, start);
/*  62 */       if (end == -1) {
/*  63 */         return title;
/*     */       }
/*     */     }
/*  66 */     String result = txt.substring(start, end);
/*  67 */     if (!StringUtils.isBlank(result)) {
/*  68 */       return result;
/*     */     }
/*  70 */     return title;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  75 */     blankToNull();
/*     */   }
/*     */ 
/*     */   public void blankToNull() {
/*  79 */     if (StringUtils.isBlank(getTxt())) {
/*  80 */       setTxt(null);
/*     */     }
/*  82 */     if (StringUtils.isBlank(getTxt1())) {
/*  83 */       setTxt1(null);
/*     */     }
/*  85 */     if (StringUtils.isBlank(getTxt2())) {
/*  86 */       setTxt2(null);
/*     */     }
/*  88 */     if (StringUtils.isBlank(getTxt3()))
/*  89 */       setTxt3(null);
/*     */   }
/*     */ 
/*     */   public boolean isAllBlank()
/*     */   {
/* 101 */     return (StringUtils.isBlank(getTxt())) && (StringUtils.isBlank(getTxt1())) && 
/* 100 */       (StringUtils.isBlank(getTxt2())) && 
/* 101 */       (StringUtils.isBlank(getTxt3()));
/*     */   }
/*     */ 
/*     */   public ContentTxt()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ContentTxt(Integer id)
/*     */   {
/* 113 */     super(id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentTxt
 * JD-Core Version:    0.6.0
 */