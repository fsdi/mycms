/*     */ package com.jeecms.core.manager.impl;
/*     */ 
/*     */ import com.jeecms.core.dao.DbTplDao;
/*     */ import com.jeecms.core.entity.DbTpl;
/*     */ import com.jeecms.core.tpl.ParentDirIsFileExceptioin;
/*     */ import com.jeecms.core.tpl.Tpl;
/*     */ import com.jeecms.core.tpl.TplManager;
/*     */ import freemarker.cache.TemplateLoader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class DbTplMngImpl
/*     */   implements TemplateLoader, TplManager
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(DbTplMngImpl.class);
/*     */ 
/* 198 */   private String[] ignoreLocales = { "_zh_CN.", "_zh.", "_en_US.", "_en." };
/*     */   private DbTplDao dao;
/*     */ 
/*     */   public Object findTemplateSource(String name)
/*     */     throws IOException
/*     */   {
/*  40 */     for (String ignore : this.ignoreLocales) {
/*  41 */       if (name.indexOf(ignore) != -1) {
/*  42 */         log.debug("templete ignore: {}", name);
/*  43 */         return null;
/*     */       }
/*     */     }
/*  46 */     name = "/" + name;
/*  47 */     Tpl tpl = get(name);
/*  48 */     if (tpl == null) {
/*  49 */       log.debug("templete not found: {}", name);
/*  50 */       return null;
/*  51 */     }if (tpl.isDirectory()) {
/*  52 */       log.warn("template is a directory,not a file!");
/*  53 */       return null;
/*     */     }
/*  55 */     log.debug("templete loaded: {}", name);
/*  56 */     return tpl;
/*     */   }
/*     */ 
/*     */   public long getLastModified(Object templateSource)
/*     */   {
/*  64 */     return ((DbTpl)templateSource).getLastModified();
/*     */   }
/*     */ 
/*     */   public Reader getReader(Object templateSource, String encoding)
/*     */     throws IOException
/*     */   {
/*  72 */     return new StringReader(((DbTpl)templateSource).getSource());
/*     */   }
/*     */ 
/*     */   public void closeTemplateSource(Object templateSource)
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Tpl get(String name)
/*     */   {
/*  84 */     DbTpl entity = this.dao.findById(name);
/*  85 */     return entity;
/*     */   }
/*     */ 
/*     */   public void save(String name, String source, boolean isDirectory) {
/*  89 */     DbTpl bean = new DbTpl();
/*  90 */     bean.setId(name);
/*  91 */     if ((!isDirectory) && (source == null)) {
/*  92 */       source = "";
/*     */     }
/*  94 */     bean.setSource(source);
/*  95 */     bean.setLastModified(System.currentTimeMillis());
/*  96 */     bean.setDirectory(isDirectory);
/*  97 */     this.dao.save(bean);
/*  98 */     createParentDir(name);
/*     */   }
/*     */ 
/*     */   public void save(String path, MultipartFile file) {
/* 102 */     String name = path + "/" + file.getOriginalFilename();
/*     */     try {
/* 104 */       String source = new String(file.getBytes(), "UTF-8");
/* 105 */       save(name, source, false);
/*     */     } catch (UnsupportedEncodingException e) {
/* 107 */       log.error("upload template error!", e);
/*     */     } catch (IOException e) {
/* 109 */       log.error("upload template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void createParentDir(String name) {
/* 114 */     String[] dirs = DbTpl.getParentDir(name);
/*     */ 
/* 117 */     for (String dir : dirs) {
/* 118 */       Tpl parentDir = get(dir);
/* 119 */       if ((parentDir != null) && (!parentDir.isDirectory()))
/* 120 */         throw new ParentDirIsFileExceptioin(
/* 121 */           "parent directory is a file: " + parentDir.getName());
/* 122 */       if (parentDir == null) {
/* 123 */         DbTpl dirTpl = new DbTpl();
/* 124 */         dirTpl.setId(dir);
/* 125 */         dirTpl.setDirectory(true);
/* 126 */         this.dao.save(dirTpl);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(String name, String source) {
/* 132 */     DbTpl entity = (DbTpl)get(name);
/* 133 */     entity.setSource(source);
/* 134 */     entity.setLastModified(System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   public int delete(String[] names) {
/* 138 */     int count = 0;
/*     */ 
/* 140 */     for (String name : names) {
/* 141 */       DbTpl tpl = this.dao.deleteById(name);
/* 142 */       count++;
/* 143 */       if (tpl.isDirectory()) {
/* 144 */         count += deleteByDir(tpl.getName());
/*     */       }
/*     */     }
/* 147 */     return names.length;
/*     */   }
/*     */ 
/*     */   private int deleteByDir(String dir) {
/* 151 */     dir = dir + "/";
/* 152 */     List<Tpl> list = (List<Tpl>) getListByPrefix(dir);
/* 153 */     for (Tpl tpl : list) {
/* 154 */       this.dao.deleteById(tpl.getName());
/*     */     }
/* 156 */     return list.size();
/*     */   }
/*     */ 
/*     */   public List<? extends Tpl> getListByPrefix(String prefix) {
/* 160 */     return this.dao.getStartWith(prefix);
/*     */   }
/*     */ 
/*     */   public List<String> getNameListByPrefix(String prefix)
/*     */   {
/* 165 */     List list = new LinkedList();
/* 166 */     for (Tpl tpl : getListByPrefix(prefix)) {
/* 167 */       list.add(tpl.getName());
/*     */     }
/* 169 */     return list;
/*     */   }
/*     */ 
/*     */   public List<? extends Tpl> getChild(String path) {
/* 173 */     List dirs = this.dao.getChild(path, true);
/* 174 */     List files = this.dao.getChild(path, false);
/* 175 */     dirs.addAll(files);
/* 176 */     return dirs;
/*     */   }
/*     */ 
/*     */   public void rename(String orig, String dist) {
/* 180 */     DbTpl tpl = this.dao.deleteById(orig);
/* 181 */     if (tpl == null) {
/* 182 */       return;
/*     */     }
/* 184 */     this.dao.deleteById(orig);
/* 185 */     String name = StringUtils.replace(tpl.getId(), orig, dist, 1);
/* 186 */     save(name, tpl.getSource(), tpl.isDirectory());
/* 187 */     createParentDir(name);
/* 188 */     if (tpl.isDirectory()) {
/* 189 */       List<DbTpl> list = this.dao.getStartWith(orig + "/");
/* 190 */       for (DbTpl t : list) {
/* 191 */         this.dao.deleteById(t.getId());
/* 192 */         name = StringUtils.replace(t.getId(), orig, dist, 1);
/* 193 */         save(name, t.getSource(), t.isDirectory());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setIgnoreLocales(String[] ignoreLocales)
/*     */   {
/* 201 */     this.ignoreLocales = ignoreLocales;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(DbTplDao dao)
/*     */   {
/* 208 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.impl.DbTplMngImpl
 * JD-Core Version:    0.6.0
 */