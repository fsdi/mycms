/*     */ package com.jeecms.common.file;
/*     */ 
/*     */ import com.jeecms.common.image.ImageUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ 
/*     */ public class FileWrap
/*     */ {
/*  28 */   public static final String[] EDITABLE_EXT = { "html", "htm", 
/*  29 */     "css", "js", "txt" };
/*     */   private File file;
/*     */   private String rootPath;
/*     */   private FileFilter filter;
/*     */   private List<FileWrap> child;
/*     */   private String filename;
/*     */   private Boolean valid;
/*     */ 
/*     */   public FileWrap(File file)
/*     */   {
/*  45 */     this(file, null);
/*     */   }
/*     */ 
/*     */   public FileWrap(File file, String rootPath)
/*     */   {
/*  57 */     this(file, rootPath, null);
/*     */   }
/*     */ 
/*     */   public FileWrap(File file, String rootPath, FileFilter filter)
/*     */   {
/*  71 */     this.file = file;
/*  72 */     this.rootPath = rootPath;
/*  73 */     this.filter = filter;
/*     */   }
/*     */ 
/*     */   public FileWrap(File file, String rootPath, FileFilter filter, Boolean valid)
/*     */   {
/*  84 */     this.file = file;
/*  85 */     this.rootPath = rootPath;
/*  86 */     this.filter = filter;
/*  87 */     this.valid = valid;
/*     */   }
/*     */ 
/*     */   public static boolean editableExt(String ext)
/*     */   {
/*  98 */     ext = ext.toLowerCase(Locale.ENGLISH);
/*  99 */     for (String s : EDITABLE_EXT) {
/* 100 */       if (s.equals(ext)) {
/* 101 */         return true;
/*     */       }
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 113 */     String path = this.file.getAbsolutePath();
/* 114 */     String relPath = path.substring(this.rootPath.length());
/* 115 */     return relPath.replace(File.separator, "/");
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 124 */     String name = getName();
/* 125 */     return name.substring(0, name.lastIndexOf('/'));
/*     */   }
/*     */ 
/*     */   public String getFilename()
/*     */   {
/* 136 */     return this.filename != null ? this.filename : this.file.getName();
/*     */   }
/*     */ 
/*     */   public String getExtension()
/*     */   {
/* 145 */     return FilenameUtils.getExtension(this.file.getName());
/*     */   }
/*     */ 
/*     */   public long getLastModified()
/*     */   {
/* 154 */     return this.file.lastModified();
/*     */   }
/*     */ 
/*     */   public Date getLastModifiedDate()
/*     */   {
/* 163 */     return new Timestamp(this.file.lastModified());
/*     */   }
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 172 */     return this.file.length() / 1024L + 1L;
/*     */   }
/*     */ 
/*     */   public String getIco()
/*     */   {
/* 191 */     if (this.file.isDirectory()) {
/* 192 */       return "folder";
/*     */     }
/* 194 */     String ext = getExtension().toLowerCase();
/* 195 */     if ((ext.equals("jpg")) || (ext.equals("jpeg")))
/* 196 */       return "jpg";
/* 197 */     if (ext.equals("png"))
/* 198 */       return "png";
/* 199 */     if (ext.equals("gif"))
/* 200 */       return "gif";
/* 201 */     if ((ext.equals("html")) || (ext.equals("htm")))
/* 202 */       return "html";
/* 203 */     if (ext.equals("swf"))
/* 204 */       return "swf";
/* 205 */     if (ext.equals("txt"))
/* 206 */       return "txt";
/* 207 */     if (ext.equals("sql")) {
/* 208 */       return "sql";
/*     */     }
/* 210 */     return "unknow";
/*     */   }
/*     */ 
/*     */   public List<FileWrap> getChild()
/*     */   {
/* 222 */     if (this.child != null)
/* 223 */       return this.child;
/*     */     File[] files;
/* 226 */     if (this.filter == null)
/* 227 */       files = getFile().listFiles();
/*     */     else {
/* 229 */       files = getFile().listFiles(this.filter);
/*     */     }
/* 231 */     List list = new ArrayList();
/* 232 */     if (files != null) {
/* 233 */       Arrays.sort(files, new FileComparator());
/* 234 */       for (File f : files) {
/* 235 */         FileWrap fw = new FileWrap(f, this.rootPath, this.filter);
/* 236 */         list.add(fw);
/*     */       }
/*     */     }
/* 239 */     return list;
/*     */   }
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 248 */     return this.file;
/*     */   }
/*     */ 
/*     */   public boolean isImage()
/*     */   {
/* 257 */     if (isDirectory()) {
/* 258 */       return false;
/*     */     }
/* 260 */     String ext = getExtension();
/* 261 */     return ImageUtils.isValidImageExt(ext);
/*     */   }
/*     */ 
/*     */   public boolean isEditable()
/*     */   {
/* 270 */     if (isDirectory()) {
/* 271 */       return false;
/*     */     }
/* 273 */     String ext = getExtension().toLowerCase();
/* 274 */     for (String s : EDITABLE_EXT) {
/* 275 */       if (s.equals(ext)) {
/* 276 */         return true;
/*     */       }
/*     */     }
/* 279 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 288 */     return this.file.isDirectory();
/*     */   }
/*     */ 
/*     */   public boolean isFile()
/*     */   {
/* 297 */     return this.file.isFile();
/*     */   }
/*     */ 
/*     */   public void setFile(File file)
/*     */   {
/* 306 */     this.file = file;
/*     */   }
/*     */ 
/*     */   public void setFilename(String filename)
/*     */   {
/* 315 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */   public void setChild(List<FileWrap> child)
/*     */   {
/* 324 */     this.child = child;
/*     */   }
/*     */ 
/*     */   public Boolean getValid()
/*     */   {
/* 329 */     return this.valid;
/*     */   }
/*     */ 
/*     */   public void setValid(Boolean valid) {
/* 333 */     this.valid = valid;
/*     */   }
/*     */ 
/*     */   public static class FileComparator
/*     */     implements Comparator<File>
/*     */   {
/*     */     public int compare(File o1, File o2)
/*     */     {
/* 342 */       if ((o1.isDirectory()) && (!o2.isDirectory()))
/* 343 */         return -1;
/* 344 */       if ((!o1.isDirectory()) && (o2.isDirectory())) {
/* 345 */         return 1;
/*     */       }
/* 347 */       return o1.compareTo(o2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.file.FileWrap
 * JD-Core Version:    0.6.0
 */