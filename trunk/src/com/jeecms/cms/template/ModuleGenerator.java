/*     */ package com.jeecms.cms.template;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ModuleGenerator
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(ModuleGenerator.class);
/*  30 */   public static final String SPT = File.separator;
/*     */   public static final String ENCODING = "UTF-8";
/*  34 */   private Properties prop = new Properties();
/*     */   private String packName;
/*     */   private String fileName;
/*     */   private File daoImplFile;
/*     */   private File daoFile;
/*     */   private File managerFile;
/*     */   private File managerImplFile;
/*     */   private File actionFile;
/*     */   private File pageListFile;
/*     */   private File pageEditFile;
/*     */   private File pageAddFile;
/*     */   private File daoImplTpl;
/*     */   private File daoTpl;
/*     */   private File managerTpl;
/*     */   private File managerImplTpl;
/*     */   private File actionTpl;
/*     */   private File pageListTpl;
/*     */   private File pageEditTpl;
/*     */   private File pageAddTpl;
/*     */ 
/*     */   public ModuleGenerator(String packName, String fileName)
/*     */   {
/*  57 */     this.packName = packName;
/*  58 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   private void loadProperties()
/*     */   {
/*     */     try {
/*  64 */       log.debug("packName=" + this.packName);
/*  65 */       log.debug("fileName=" + this.fileName);
/*  66 */       FileInputStream fileInput = new FileInputStream(
/*  67 */         getFilePath(this.packName, this.fileName));
/*  68 */       this.prop.load(fileInput);
/*  69 */       String entityUp = this.prop.getProperty("Entity");
/*  70 */       log.debug("entityUp:" + entityUp);
/*  71 */       if ((entityUp == null) || (entityUp.trim().equals(""))) {
/*  72 */         log.warn("Entity not specified, exit!");
/*  73 */         return;
/*     */       }
/*  75 */       String entityLow = entityUp.substring(0, 1).toLowerCase() + 
/*  76 */         entityUp.substring(1);
/*  77 */       log.debug("entityLow:" + entityLow);
/*  78 */       this.prop.put("entity", entityLow);
/*  79 */       if (log.isDebugEnabled()) {
/*  80 */         Set ps = this.prop.keySet();
/*  81 */         for (Iterator localIterator = ps.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/*  82 */           log.debug(o + "=" + this.prop.get(o)); }
/*     */       }
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  86 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void prepareFile() {
/*  93 */     String daoImplFilePath = getFilePath(this.prop.getProperty("dao_impl_p"), 
/*  94 */       this.prop.getProperty("Entity") + "DaoImpl.java");
/*  95 */     this.daoImplFile = new File(daoImplFilePath);
/*  96 */     log.debug("daoImplFile:" + this.daoImplFile.getAbsolutePath());
/*     */ 
/*  98 */     String daoFilePath = getFilePath(this.prop.getProperty("dao_p"), 
/*  99 */       this.prop.getProperty("Entity") + 
/* 100 */       "Dao.java");
/* 101 */     this.daoFile = new File(daoFilePath);
/* 102 */     log.debug("daoFile:" + this.daoFile.getAbsolutePath());
/*     */ 
/* 104 */     String managerFilePath = getFilePath(this.prop.getProperty("manager_p"), 
/* 105 */       this.prop.getProperty("Entity") + "Mng.java");
/* 106 */     this.managerFile = new File(managerFilePath);
/* 107 */     log.debug("managerFile:" + this.managerFile.getAbsolutePath());
/*     */ 
/* 109 */     String managerImplFilePath = getFilePath(
/* 110 */       this.prop.getProperty("manager_impl_p"), this.prop.getProperty("Entity") + 
/* 111 */       "MngImpl.java");
/* 112 */     this.managerImplFile = new File(managerImplFilePath);
/* 113 */     log.debug("managerImplFile:" + this.managerImplFile.getAbsolutePath());
/* 114 */     String actionFilePath = getFilePath(this.prop.getProperty("action_p"), 
/* 115 */       this.prop.getProperty("Entity") + 
/* 116 */       "Act.java");
/* 117 */     this.actionFile = new File(actionFilePath);
/* 118 */     log.debug("actionFile:" + this.actionFile.getAbsolutePath());
/*     */ 
/* 120 */     String pagePath = "WebContent/WEB-INF/" + 
/* 121 */       this.prop.getProperty("config_sys") + "/" + 
/* 122 */       this.prop.getProperty("config_entity") + "/";
/* 123 */     this.pageListFile = new File(pagePath + "list.html");
/* 124 */     log.debug("pageListFile:" + this.pageListFile.getAbsolutePath());
/* 125 */     this.pageEditFile = new File(pagePath + "edit.html");
/* 126 */     log.debug("pageEditFile:" + this.pageEditFile.getAbsolutePath());
/* 127 */     this.pageAddFile = new File(pagePath + "add.html");
/* 128 */     log.debug("pageAddFile:" + this.pageAddFile.getAbsolutePath());
/*     */   }
/*     */ 
/*     */   private void prepareTemplate() {
/* 132 */     String tplPack = this.prop.getProperty("template_dir");
/* 133 */     log.debug("tplPack:" + tplPack);
/* 134 */     this.daoImplTpl = new File(getFilePath(tplPack, "dao_impl.txt"));
/* 135 */     this.daoTpl = new File(getFilePath(tplPack, "dao.txt"));
/* 136 */     this.managerImplTpl = new File(getFilePath(tplPack, "manager_impl.txt"));
/* 137 */     this.managerTpl = new File(getFilePath(tplPack, "manager.txt"));
/* 138 */     this.actionTpl = new File(getFilePath(tplPack, "action.txt"));
/* 139 */     this.pageListTpl = new File(getFilePath(tplPack, "page_list.txt"));
/* 140 */     this.pageAddTpl = new File(getFilePath(tplPack, "page_add.txt"));
/* 141 */     this.pageEditTpl = new File(getFilePath(tplPack, "page_edit.txt"));
/*     */   }
/*     */ 
/*     */   private static void stringToFile(File file, String s) throws IOException {
/* 145 */     FileUtils.writeStringToFile(file, s, "UTF-8");
/*     */   }
/*     */ 
/*     */   private void writeFile() {
/*     */     try {
/* 150 */       if ("true".equals(this.prop.getProperty("is_dao"))) {
/* 151 */         stringToFile(this.daoImplFile, readTpl(this.daoImplTpl));
/* 152 */         stringToFile(this.daoFile, readTpl(this.daoTpl));
/*     */       }
/* 154 */       if ("true".equals(this.prop.getProperty("is_manager"))) {
/* 155 */         stringToFile(this.managerImplFile, readTpl(this.managerImplTpl));
/* 156 */         stringToFile(this.managerFile, readTpl(this.managerTpl));
/*     */       }
/* 158 */       if ("true".equals(this.prop.getProperty("is_action"))) {
/* 159 */         stringToFile(this.actionFile, readTpl(this.actionTpl));
/*     */       }
/* 161 */       if ("true".equals(this.prop.getProperty("is_page"))) {
/* 162 */         stringToFile(this.pageListFile, readTpl(this.pageListTpl));
/* 163 */         stringToFile(this.pageAddFile, readTpl(this.pageAddTpl));
/* 164 */         stringToFile(this.pageEditFile, readTpl(this.pageEditTpl));
/*     */       }
/*     */     } catch (IOException e) {
/* 167 */       log.warn("write file faild! " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private String readTpl(File tpl) {
/* 172 */     String content = null;
/*     */     try {
/* 174 */       content = FileUtils.readFileToString(tpl, "UTF-8");
/* 175 */       Set ps = this.prop.keySet();
/* 176 */       for (Iterator localIterator = ps.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 177 */         String key = (String)o;
/* 178 */         String value = this.prop.getProperty(key);
/* 179 */         content = content.replaceAll("\\#\\{" + key + "\\}", value); }
/*     */     }
/*     */     catch (IOException e) {
/* 182 */       log.warn("read file faild. " + e.getMessage());
/*     */     }
/* 184 */     return content;
/*     */   }
/*     */ 
/*     */   private String getFilePath(String packageName, String name)
/*     */   {
/* 189 */     log.debug("replace:" + packageName);
/* 190 */     String path = packageName.replaceAll("\\.", "/");
/* 191 */     log.debug("after relpace:" + path);
/* 192 */     return "src/" + path + "/" + name;
/*     */   }
/*     */ 
/*     */   public void generate() {
/* 196 */     loadProperties();
/* 197 */     prepareFile();
/* 198 */     prepareTemplate();
/* 199 */     writeFile();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 203 */     String packName = "com.jeecms.common.developer.template";
/* 204 */     String fileName = "template.properties";
/* 205 */     new ModuleGenerator(packName, fileName).generate();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.template.ModuleGenerator
 * JD-Core Version:    0.6.0
 */