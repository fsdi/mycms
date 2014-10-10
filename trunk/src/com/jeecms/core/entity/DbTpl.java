/*     */ package com.jeecms.core.entity;
/*     */ 
/*     */ import com.jeecms.core.entity.base.BaseDbTpl;
/*     */ import com.jeecms.core.tpl.Tpl;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public class DbTpl extends BaseDbTpl
/*     */   implements Tpl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public static String[] getParentDir(String path)
/*     */   {
/*  27 */     Assert.notNull(path);
/*  28 */     if (!path.startsWith("/")) {
/*  29 */       throw new IllegalArgumentException("path must start with /");
/*     */     }
/*  31 */     List list = new ArrayList();
/*  32 */     int index = path.indexOf("/", 1);
/*  33 */     while (index >= 0) {
/*  34 */       list.add(path.substring(0, index));
/*  35 */       index = path.indexOf("/", index + 1);
/*     */     }
/*  37 */     String[] arr = new String[list.size()];
/*  38 */     return (String[])list.toArray(arr);
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  42 */     return getId();
/*     */   }
/*     */ 
/*     */   public String getPath() {
/*  46 */     String name = getId();
/*  47 */     return getId().substring(0, name.lastIndexOf("/"));
/*     */   }
/*     */ 
/*     */   public String getFilename() {
/*  51 */     String name = getId();
/*  52 */     if (!StringUtils.isBlank(name)) {
/*  53 */       int index = name.lastIndexOf("/");
/*  54 */       if (index != -1) {
/*  55 */         return name.substring(index + 1, name.length());
/*     */       }
/*     */     }
/*  58 */     return name;
/*     */   }
/*     */ 
/*     */   public long getLength() {
/*  62 */     if ((isDirectory()) || (getSource() == null)) {
/*  63 */       return 128L;
/*     */     }
/*     */ 
/*  66 */     return (long)(getSource().length() * 1.5D);
/*     */   }
/*     */ 
/*     */   public int getSize()
/*     */   {
/*  71 */     return (int)(getLength() / 1024L) + 1;
/*     */   }
/*     */ 
/*     */   public Date getLastModifiedDate() {
/*  75 */     return new Timestamp(getLastModified());
/*     */   }
/*     */ 
/*     */   public DbTpl()
/*     */   {
/*     */   }
/*     */ 
/*     */   public DbTpl(String id)
/*     */   {
/*  87 */     super(id);
/*     */   }
/*     */ 
/*     */   public DbTpl(String id, long lastModified, boolean directory)
/*     */   {
/* 101 */     super(id, 
/* 100 */       lastModified, 
/* 101 */       directory);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.DbTpl
 * JD-Core Version:    0.6.0
 */