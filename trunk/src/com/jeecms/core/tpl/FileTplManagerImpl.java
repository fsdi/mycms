/*     */ package com.jeecms.core.tpl;
/*     */ 
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ public class FileTplManagerImpl
/*     */   implements TplManager
/*     */ {
/*  21 */   private static Logger log = LoggerFactory.getLogger(FileTplManagerImpl.class);
/*     */   private String root;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   public int delete(String[] names)
/*     */   {
/*  25 */     int count = 0;
/*  26 */     for (String name : names) {
/*  27 */       File f = new File(this.realPathResolver.get(name));
/*  28 */       if (f.isDirectory()) {
/*  29 */         if (FileUtils.deleteQuietly(f)) {
/*  30 */           count++;
/*     */         }
/*     */       }
/*  33 */       else if (f.delete()) {
/*  34 */         count++;
/*     */       }
/*     */     }
/*     */ 
/*  38 */     return count;
/*     */   }
/*     */ 
/*     */   public Tpl get(String name) {
/*  42 */     File f = new File(this.realPathResolver.get(name));
/*  43 */     if (f.exists()) {
/*  44 */       return new FileTpl(f, this.root);
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   public List<Tpl> getListByPrefix(String prefix)
/*     */   {
/*  51 */     File f = new File(this.realPathResolver.get(prefix));
/*  52 */     String name = prefix.substring(prefix.lastIndexOf("/") + 1);
/*     */     File parent;
/*  54 */     if (prefix.endsWith("/")) {
/*  55 */       name = "";
/*  56 */       parent = f;
/*     */     } else {
/*  58 */       parent = f.getParentFile();
/*     */     }
/*  60 */     if (parent.exists()) {
/*  61 */       File[] files = parent.listFiles(new PrefixFilter(name));
/*  62 */       if (files != null) {
/*  63 */         List list = new ArrayList();
/*  64 */         for (File file : files) {
/*  65 */           list.add(new FileTpl(file, this.root));
/*     */         }
/*  67 */         return list;
/*     */       }
/*  69 */       return new ArrayList(0);
/*     */     }
/*     */ 
/*  72 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   public List<String> getNameListByPrefix(String prefix)
/*     */   {
/*  77 */     List<Tpl> list = getListByPrefix(prefix);
/*  78 */     List result = new ArrayList(list.size());
/*  79 */     for (Tpl tpl : list) {
/*  80 */       result.add(tpl.getName());
/*     */     }
/*  82 */     return result;
/*     */   }
/*     */ 
/*     */   public List<Tpl> getChild(String path) {
/*  86 */     File file = new File(this.realPathResolver.get(path));
/*  87 */     File[] child = file.listFiles();
/*  88 */     if (child != null) {
/*  89 */       List list = new ArrayList(child.length);
/*  90 */       for (File f : child) {
/*  91 */         list.add(new FileTpl(f, this.root));
/*     */       }
/*  93 */       return list;
/*     */     }
/*  95 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   public void rename(String orig, String dist)
/*     */   {
/* 100 */     String os = this.realPathResolver.get(orig);
/* 101 */     File of = new File(os);
/* 102 */     String ds = this.realPathResolver.get(dist);
/* 103 */     File df = new File(ds);
/*     */     try {
/* 105 */       if (of.isDirectory())
/* 106 */         FileUtils.moveDirectory(of, df);
/*     */       else
/* 108 */         FileUtils.moveFile(of, df);
/*     */     }
/*     */     catch (IOException e) {
/* 111 */       log.error("Move template error: " + orig + " to " + dist, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void save(String name, String source, boolean isDirectory) {
/* 116 */     String real = this.realPathResolver.get(name);
/* 117 */     File f = new File(real);
/* 118 */     if (isDirectory)
/* 119 */       f.mkdirs();
/*     */     else
/*     */       try {
/* 122 */         FileUtils.writeStringToFile(f, source, "UTF-8");
/*     */       } catch (IOException e) {
/* 124 */         log.error("Save template error: " + name, e);
/* 125 */         throw new RuntimeException(e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void save(String path, MultipartFile file)
/*     */   {
/* 131 */     File f = new File(this.realPathResolver.get(path), 
/* 132 */       file.getOriginalFilename());
/*     */     try {
/* 134 */       file.transferTo(f);
/*     */     } catch (IllegalStateException e) {
/* 136 */       log.error("upload template error!", e);
/*     */     } catch (IOException e) {
/* 138 */       log.error("upload template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(String name, String source) {
/* 143 */     String real = this.realPathResolver.get(name);
/* 144 */     File f = new File(real);
/*     */     try {
/* 146 */       FileUtils.writeStringToFile(f, source, "UTF-8");
/*     */     } catch (IOException e) {
/* 148 */       log.error("Save template error: " + name, e);
/* 149 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 159 */     this.realPathResolver = realPathResolver;
/* 160 */     this.root = realPathResolver.get("");
/*     */   }
/*     */   private static class PrefixFilter implements FileFilter {
/*     */     private String prefix;
/*     */ 
/*     */     public PrefixFilter(String prefix) {
/* 167 */       this.prefix = prefix;
/*     */     }
/*     */ 
/*     */     public boolean accept(File file) {
/* 171 */       String name = file.getName();
/* 172 */       return (file.isFile()) && (name.startsWith(this.prefix));
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.tpl.FileTplManagerImpl
 * JD-Core Version:    0.6.0
 */