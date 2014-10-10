/*     */ package com.jeecms.core.entity;
/*     */ 
/*     */ import com.jeecms.common.upload.UploadUtils;
/*     */ import com.jeecms.common.util.MyFileUtils;
/*     */ import com.jeecms.core.entity.base.BaseFtp;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.SocketException;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.net.PrintCommandListener;
/*     */ import org.apache.commons.net.ftp.FTPClient;
/*     */ import org.apache.commons.net.ftp.FTPReply;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Ftp extends BaseFtp
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   private static final Logger log = LoggerFactory.getLogger(Ftp.class);
/*     */ 
/*     */   public String storeByExt(String path, String ext, InputStream in) throws IOException
/*     */   {
/*  33 */     String filename = UploadUtils.generateFilename(path, ext);
/*  34 */     store(filename, in);
/*  35 */     return filename;
/*     */   }
/*     */ 
/*     */   public String storeByFilename(String filename, InputStream in) throws IOException
/*     */   {
/*  40 */     store(filename, in);
/*  41 */     return filename;
/*     */   }
/*     */ 
/*     */   public File retrieve(String name, String fileName) throws IOException {
/*  45 */     String path = System.getProperty("java.io.tmpdir");
/*  46 */     File file = new File(path, fileName);
/*  47 */     file = UploadUtils.getUniqueFile(file);
/*     */ 
/*  49 */     FTPClient ftp = getClient();
/*  50 */     OutputStream output = new FileOutputStream(file);
/*  51 */     ftp.retrieveFile(getPath() + name, output);
/*  52 */     output.close();
/*  53 */     ftp.logout();
/*  54 */     ftp.disconnect();
/*  55 */     return file;
/*     */   }
/*     */ 
/*     */   public boolean restore(String name, File file) throws IOException {
/*  59 */     store(name, FileUtils.openInputStream(file));
/*  60 */     file.deleteOnExit();
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   public int storeByFloder(String folder, String rootPath)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       FTPClient ftp = getClient();
/*  69 */       if (ftp != null) {
/*  70 */         List<File> files = MyFileUtils.getFiles(new File(folder));
/*  71 */         for (File file : files) {
/*  72 */           String filename = getPath() + file.getName();
/*  73 */           String name = FilenameUtils.getName(filename);
/*  74 */           String path = FilenameUtils.getFullPath(filename);
/*  75 */           String fileAbsolutePath = file.getAbsolutePath();
/*  76 */           String fileRelativePath = fileAbsolutePath.substring(rootPath.length() + 1, fileAbsolutePath.indexOf(name));
/*  77 */           path = path + fileRelativePath.replace("\\", "/");
/*  78 */           if (!ftp.changeWorkingDirectory(path)) {
/*  79 */             String[] ps = StringUtils.split(path, '/');
/*  80 */             String p = "/";
/*  81 */             ftp.changeWorkingDirectory(p);
/*  82 */             for (String s : ps) {
/*  83 */               p = p + s + "/";
/*  84 */               if (!ftp.changeWorkingDirectory(p)) {
/*  85 */                 ftp.makeDirectory(s);
/*  86 */                 ftp.changeWorkingDirectory(p);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*  91 */           name = new String(name.getBytes(getEncoding()), "iso-8859-1");
/*  92 */           if (!file.isFile()) {
/*  93 */             ftp.makeDirectory(name);
/*     */           } else {
/*  95 */             InputStream in = new FileInputStream(file.getAbsolutePath());
/*  96 */             ftp.storeFile(name, in);
/*  97 */             in.close();
/*     */           }
/*     */         }
/* 100 */         ftp.logout();
/* 101 */         ftp.disconnect();
/*     */       }
/* 103 */       return 0;
/*     */     } catch (SocketException e) {
/* 105 */       log.error("ftp store error", e);
/* 106 */       return 3;
/*     */     } catch (IOException e) {
/* 108 */       log.error("ftp store error", e);
/* 109 */     }return 4;
/*     */   }
/*     */ 
/*     */   private int store(String remote, InputStream in)
/*     */   {
/*     */     try {
/* 115 */       FTPClient ftp = getClient();
/* 116 */       if (ftp != null) {
/* 117 */         String filename = getPath() + remote;
/* 118 */         String name = FilenameUtils.getName(filename);
/* 119 */         String path = FilenameUtils.getFullPath(filename);
/* 120 */         if (!ftp.changeWorkingDirectory(path)) {
/* 121 */           String[] ps = StringUtils.split(path, '/');
/* 122 */           String p = "/";
/* 123 */           ftp.changeWorkingDirectory(p);
/* 124 */           for (String s : ps) {
/* 125 */             p = p + s + "/";
/* 126 */             if (!ftp.changeWorkingDirectory(p)) {
/* 127 */               ftp.makeDirectory(s);
/* 128 */               ftp.changeWorkingDirectory(p);
/*     */             }
/*     */           }
/*     */         }
/* 132 */         ftp.storeFile(name, in);
/* 133 */         ftp.logout();
/* 134 */         ftp.disconnect();
/*     */       }
/* 136 */       in.close();
/* 137 */       return 0;
/*     */     } catch (SocketException e) {
/* 139 */       log.error("ftp store error", e);
/* 140 */       return 3;
/*     */     } catch (IOException e) {
/* 142 */       log.error("ftp store error", e);
/* 143 */     }return 4;
/*     */   }
/*     */ 
/*     */   private FTPClient getClient() throws SocketException, IOException
/*     */   {
/* 148 */     FTPClient ftp = new FTPClient();
/* 149 */     ftp.addProtocolCommandListener(
/* 150 */       new PrintCommandListener(new PrintWriter(System.out)));
/* 151 */     ftp.setDefaultPort(getPort().intValue());
/* 152 */     ftp.connect(getIp());
/* 153 */     int reply = ftp.getReplyCode();
/* 154 */     if (!FTPReply.isPositiveCompletion(reply)) {
/* 155 */       log.warn("FTP server refused connection: {}", getIp());
/* 156 */       ftp.disconnect();
/* 157 */       return null;
/*     */     }
/* 159 */     if (!ftp.login(getUsername(), getPassword())) {
/* 160 */       log.warn("FTP server refused login: {}, user: {}", getIp(), 
/* 161 */         getUsername());
/* 162 */       ftp.logout();
/* 163 */       ftp.disconnect();
/* 164 */       return null;
/*     */     }
/* 166 */     ftp.setControlEncoding(getEncoding());
/* 167 */     ftp.setFileType(2);
/* 168 */     ftp.enterLocalPassiveMode();
/* 169 */     return ftp;
/*     */   }
/*     */ 
/*     */   public Ftp()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Ftp(Integer id)
/*     */   {
/* 181 */     super(id);
/*     */   }
/*     */ 
/*     */   public Ftp(Integer id, String name, String ip, Integer port, String encoding, String url)
/*     */   {
/* 201 */     super(id, 
/* 197 */       name, 
/* 198 */       ip, 
/* 199 */       port, 
/* 200 */       encoding, 
/* 201 */       url);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.Ftp
 * JD-Core Version:    0.6.0
 */