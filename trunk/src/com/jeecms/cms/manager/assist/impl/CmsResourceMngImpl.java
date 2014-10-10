/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.common.file.FileWrap;
/*     */ import com.jeecms.common.file.FileWrap.FileComparator;
/*     */ import com.jeecms.common.util.Zipper.FileEntry;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.tools.zip.ZipEntry;
/*     */ import org.apache.tools.zip.ZipFile;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Service
/*     */ public class CmsResourceMngImpl
/*     */   implements CmsResourceMng
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(CmsResourceMngImpl.class);
/*     */ 
/* 286 */   private FileFilter filter = new FileFilter()
/*     */   {
/*     */     public boolean accept(File file) {
/* 289 */       return (file.isDirectory()) || 
/* 289 */         (FileWrap.editableExt(FilenameUtils.getExtension(file
/* 290 */         .getName())));
/*     */     }
/* 286 */   };
/*     */   private RealPathResolver realPathResolver;
/*     */   private CmsFileMng fileMng;
/*     */ 
/*     */   public List<FileWrap> listFile(String path, boolean dirAndEditable)
/*     */   {
/*  45 */     File parent = new File(this.realPathResolver.get(path));
/*  46 */     if (parent.exists())
/*     */     {
/*     */       File[] files;
/*  48 */       if (dirAndEditable)
/*  49 */         files = parent.listFiles(this.filter);
/*     */       else {
/*  51 */         files = parent.listFiles();
/*     */       }
/*  53 */       Arrays.sort(files, new FileWrap.FileComparator());
/*  54 */       List list = new ArrayList(files.length);
/*  55 */       for (File f : files) {
/*  56 */         list.add(new FileWrap(f, this.realPathResolver.get("")));
/*     */       }
/*  58 */       return list;
/*     */     }
/*  60 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   public List<FileWrap> listFileValid(String path, boolean dirAndEditable)
/*     */   {
/*  65 */     File parent = new File(this.realPathResolver.get(path));
/*  66 */     if (parent.exists())
/*     */     {
/*     */       File[] files;
/*  68 */       if (dirAndEditable)
/*  69 */         files = parent.listFiles(this.filter);
/*     */       else {
/*  71 */         files = parent.listFiles();
/*     */       }
/*  73 */       Arrays.sort(files, new FileWrap.FileComparator());
/*  74 */       List list = new ArrayList(files.length);
/*     */ 
/*  76 */       for (File f : files) {
/*  77 */         CmsFile file = this.fileMng.findByPath(f.getName());
/*  78 */         if (file != null)
/*  79 */           list.add(new FileWrap(f, this.realPathResolver.get(""), null, file.getFileIsvalid()));
/*     */         else {
/*  81 */           list.add(new FileWrap(f, this.realPathResolver.get(""), null, Boolean.valueOf(false)));
/*     */         }
/*     */       }
/*  84 */       return list;
/*     */     }
/*  86 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   public List<FileWrap> queryFiles(String path, Boolean valid)
/*     */   {
/*  91 */     File parent = new File(this.realPathResolver.get(path));
/*  92 */     if (parent.exists())
/*     */     {
/*  94 */       File[] files = parent.listFiles();
/*  95 */       Arrays.sort(files, new FileWrap.FileComparator());
/*  96 */       List list = new ArrayList(files.length);
/*     */ 
/*  98 */       for (File f : files) {
/*  99 */         CmsFile file = this.fileMng.findByPath(f.getName());
/* 100 */         if (valid != null) {
/* 101 */           if (file != null) {
/* 102 */             if (file.getFileIsvalid().equals(valid)) {
/* 103 */               list.add(new FileWrap(f, this.realPathResolver.get(""), null, valid));
/*     */             }
/*     */           }
/* 106 */           else if (valid.equals(Boolean.valueOf(false))) {
/* 107 */             list.add(new FileWrap(f, this.realPathResolver.get(""), null, Boolean.valueOf(false)));
/*     */           }
/*     */ 
/*     */         }
/* 111 */         else if (file != null)
/* 112 */           list.add(new FileWrap(f, this.realPathResolver.get(""), null, file.getFileIsvalid()));
/*     */         else {
/* 114 */           list.add(new FileWrap(f, this.realPathResolver.get(""), null, Boolean.valueOf(false)));
/*     */         }
/*     */       }
/*     */ 
/* 118 */       return list;
/*     */     }
/* 120 */     return new ArrayList(0);
/*     */   }
/*     */ 
/*     */   public boolean createDir(String path, String dirName)
/*     */   {
/* 125 */     File parent = new File(this.realPathResolver.get(path));
/* 126 */     parent.mkdirs();
/* 127 */     File dir = new File(parent, dirName);
/* 128 */     return dir.mkdir();
/*     */   }
/*     */ 
/*     */   public void saveFile(String path, MultipartFile file) throws IllegalStateException, IOException
/*     */   {
/* 133 */     File dest = new File(this.realPathResolver.get(path), 
/* 134 */       file.getOriginalFilename());
/* 135 */     file.transferTo(dest);
/*     */   }
/*     */ 
/*     */   public void createFile(String path, String filename, String data) throws IOException
/*     */   {
/* 140 */     File parent = new File(this.realPathResolver.get(path));
/* 141 */     parent.mkdirs();
/* 142 */     File file = new File(parent, filename);
/* 143 */     FileUtils.writeStringToFile(file, data, "UTF-8");
/*     */   }
/*     */ 
/*     */   public String readFile(String name) throws IOException {
/* 147 */     File file = new File(this.realPathResolver.get(name));
/* 148 */     return FileUtils.readFileToString(file, "UTF-8");
/*     */   }
/*     */ 
/*     */   public void updateFile(String name, String data) throws IOException {
/* 152 */     File file = new File(this.realPathResolver.get(name));
/* 153 */     FileUtils.writeStringToFile(file, data, "UTF-8");
/*     */   }
/*     */ 
/*     */   public int delete(String[] names) {
/* 157 */     int count = 0;
/*     */ 
/* 159 */     for (String name : names) {
/* 160 */       File f = new File(this.realPathResolver.get(name));
/* 161 */       if (FileUtils.deleteQuietly(f)) {
/* 162 */         count++;
/*     */       }
/*     */     }
/* 165 */     return count;
/*     */   }
/*     */ 
/*     */   public void rename(String origName, String destName) {
/* 169 */     File orig = new File(this.realPathResolver.get(origName));
/* 170 */     File dest = new File(this.realPathResolver.get(destName));
/* 171 */     orig.renameTo(dest);
/*     */   }
/*     */ 
/*     */   public void copyTplAndRes(CmsSite from, CmsSite to) throws IOException {
/* 175 */     String fromSol = from.getTplSolution();
/* 176 */     String toSol = to.getTplSolution();
/* 177 */     File tplFrom = new File(this.realPathResolver.get(from.getTplPath()), 
/* 178 */       fromSol);
/* 179 */     File tplTo = new File(this.realPathResolver.get(to.getTplPath()), toSol);
/* 180 */     FileUtils.copyDirectory(tplFrom, tplTo);
/* 181 */     File resFrom = new File(this.realPathResolver.get(from.getResPath()), 
/* 182 */       fromSol);
/* 183 */     if (resFrom.exists()) {
/* 184 */       File resTo = new File(this.realPathResolver.get(to.getResPath()), toSol);
/* 185 */       FileUtils.copyDirectory(resFrom, resTo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void delTplAndRes(CmsSite site) throws IOException {
/* 190 */     File tpl = new File(this.realPathResolver.get(site.getTplPath()));
/* 191 */     File res = new File(this.realPathResolver.get(site.getResPath()));
/* 192 */     FileUtils.deleteDirectory(tpl);
/* 193 */     FileUtils.deleteDirectory(res);
/*     */   }
/*     */ 
/*     */   public String[] getSolutions(String path) {
/* 197 */     File tpl = new File(this.realPathResolver.get(path));
/* 198 */     return tpl.list(new FilenameFilter() {
/*     */       public boolean accept(File dir, String name) {
/* 200 */         return dir.isDirectory();
/*     */       } } );
/*     */   }
/*     */ 
/*     */   public List<FileEntry> export(CmsSite site, String solution) {
/* 206 */     List fileEntrys = new ArrayList();
/* 207 */     File tpl = new File(this.realPathResolver.get(site.getTplPath()), solution);
/* 208 */     fileEntrys.add(new FileEntry("", "", tpl));
/* 209 */     File res = new File(this.realPathResolver.get(site.getResPath()), solution);
/* 210 */     if (res.exists()) {
/* 211 */       for (File r : res.listFiles()) {
/* 212 */         fileEntrys.add(new FileEntry("${res}", r));
/*     */       }
/*     */     }
/* 215 */     return fileEntrys;
/*     */   }
/*     */ 
/*     */   public void imoport(File file, CmsSite site) throws IOException
/*     */   {
/* 220 */     String resRoot = site.getResPath();
/* 221 */     String tplRoot = site.getTplPath();
/*     */ 
/* 223 */     ZipFile zip = new ZipFile(file, "GBK");
/*     */ 
/* 229 */     byte[] buf = new byte[1024];
/*     */ 
/* 231 */     InputStream is = null;
/* 232 */     OutputStream os = null;
/* 233 */     String solution = null;
/*     */ 
/* 235 */     Enumeration en = zip.getEntries();
/* 236 */     while (en.hasMoreElements()) {
/* 237 */       String name = ((ZipEntry)en.nextElement()).getName();
/* 238 */       if (!name.startsWith("${res}")) {
/* 239 */         solution = name.substring(0, name.indexOf("/"));
/* 240 */         break;
/*     */       }
/*     */     }
/* 243 */     if (solution == null) {
/* 244 */       return;
/*     */     }
/* 246 */     en = zip.getEntries();
/* 247 */     while (en.hasMoreElements()) {
/* 248 */       ZipEntry entry = (ZipEntry)en.nextElement();
/* 249 */       if (!entry.isDirectory()) {
/* 250 */         String name = entry.getName();
/* 251 */         log.debug("unzip file：{}", name);
/*     */         String filename;
/* 253 */         if (name.startsWith("${res}"))
/* 254 */           filename = resRoot + "/" + solution + 
/* 255 */             name.substring("${res}".length());
/*     */         else {
/* 257 */           filename = tplRoot + "/" + name;
/*     */         }
/* 259 */         log.debug("解压地址：{}", filename);
/* 260 */         File outFile = new File(this.realPathResolver.get(filename));
/* 261 */         File pfile = outFile.getParentFile();
/* 262 */         if (!pfile.exists())
/* 263 */           pfile.mkdirs(); int len;
/*     */         try {
/* 266 */           is = zip.getInputStream(entry);
/* 267 */           os = new FileOutputStream(outFile);
/* 268 */           while ((len = is.read(buf)) != -1)
/*     */           {
/* 269 */             os.write(buf, 0, len);
/*     */           }
/*     */         } finally {
/* 272 */           if (is != null) {
/* 273 */             is.close();
/* 274 */             is = null;
/*     */           }
/* 276 */           if (os != null) {
/* 277 */             os.close();
/* 278 */             os = null;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 299 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */   @Autowired
/*     */   public void setFileMng(CmsFileMng fileMng) {
/* 304 */     this.fileMng = fileMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsResourceMngImpl
 * JD-Core Version:    0.6.0
 */