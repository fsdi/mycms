/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.Constants;
/*     */ import com.jeecms.cms.manager.assist.CmsDataBackMng;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.util.DateUtils;
/*     */ import com.jeecms.common.util.StrUtils;
/*     */ import com.jeecms.common.util.Zipper;
/*     */ import com.jeecms.common.util.Zipper.FileEntry;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class DataAct
/*     */ {
/*  50 */   private static String SUFFIX = "sql";
/*  51 */   private static String SPLIT = "`";
/*  52 */   private static String BR = "\r\n";
/*  53 */   private static String SLASH = "/";
/*  54 */   private static String SPACE = " ";
/*  55 */   private static String BRANCH = ";";
/*  56 */   private static String INSERT_INTO = " INSERT INTO ";
/*  57 */   private static String VALUES = "VALUES";
/*  58 */   private static String LEFTBRACE = "(";
/*  59 */   private static String RIGHTBRACE = ")";
/*  60 */   private static String QUOTES = "'";
/*  61 */   private static String COMMA = ",";
/*  62 */   private static String DISABLEFOREIGN = "SET FOREIGN_KEY_CHECKS = 0;\r\n";
/*  63 */   private static String ABLEFOREIGN = "SET FOREIGN_KEY_CHECKS = 1;\r\n";
/*  64 */   private static String dbXmlFileName = "/WEB-INF/config/jdbc.properties";
/*     */   private static final String INVALID_PARAM = "template.invalidParams";
/*     */   private static String backup_table;
/*  68 */   private static final Logger log = LoggerFactory.getLogger(ResourceAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Autowired
/*     */   private CmsDataBackMng dataBackMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsResourceMng resourceMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*  73 */   @RequestMapping({"/data/v_list.do"})
/*     */   public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response) { List tables = new ArrayList();
/*     */     try {
/*  75 */       tables = this.dataBackMng.listTabels(this.dataBackMng.getDefaultCatalog());
/*     */     } catch (SQLException e) {
/*  77 */       model.addAttribute("msg", e.toString());
/*  78 */       return "common/error_message";
/*     */     }
/*  80 */     model.addAttribute("tables", tables);
/*  81 */     return "data/list"; }
/*     */ 
/*     */   @RequestMapping({"/data/v_listfields.do"})
/*     */   public String listfiled(String tablename, ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  87 */     List list = this.dataBackMng.listFields(tablename);
/*  88 */     model.addAttribute("list", list);
/*  89 */     return "data/fields";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/v_revert.do"})
/*     */   public String listDataBases(ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*     */     try {
/*  97 */       String defaultCatalog = this.dataBackMng.getDefaultCatalog();
/*  98 */       model.addAttribute("defaultCatalog", defaultCatalog);
/*     */     } catch (SQLException e) {
/* 100 */       model.addAttribute("msg", e.toString());
/* 101 */       return "common/error_message";
/*     */     }
/* 103 */     List databases = this.dataBackMng.listDataBases();
/* 104 */     model.addAttribute("databases", databases);
/* 105 */     model.addAttribute("backuppath", "/WEB-INF/buckup");
/* 106 */     return "data/databases";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_revert.do"})
/*     */   public String revert(String filename, String db, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
/* 112 */     String backpath = this.realPathResolver.get("/WEB-INF/buckup");
/* 113 */     String backFilePath = backpath + SLASH + filename;
/* 114 */     String sql = readFile(backFilePath);
/*     */ 
/* 116 */     this.dataBackMng.executeSQL("use " + SPLIT + db + SPLIT + BR);
/* 117 */     this.dataBackMng.executeSQL(sql);
/*     */     try
/*     */     {
/* 120 */       String defaultCatalog = this.dataBackMng.getDefaultCatalog();
/* 121 */       if (!defaultCatalog.equals(db)) {
/* 122 */         String dbXmlPath = this.realPathResolver.get(dbXmlFileName);
/* 123 */         dbXml(dbXmlPath, defaultCatalog, db);
/*     */       }
/*     */     } catch (Exception e) {
/* 126 */       WebErrors errors = WebErrors.create(request);
/* 127 */       errors.addErrorCode("db.revert.error");
/* 128 */       errors.addErrorString(e.getMessage());
/* 129 */       if (errors.hasErrors()) {
/* 130 */         return errors.showErrorPage(model);
/*     */       }
/*     */     }
/*     */ 
/* 134 */     this.session.setAttribute(request, response, "auth_key", null);
/* 135 */     request.getSession().invalidate();
/* 136 */     return "login";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_backup.do"})
/*     */   public String backup(String[] tableNames, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
/* 142 */     String backpath = this.realPathResolver.get("/WEB-INF/buckup");
/* 143 */     File backDirectory = new File(backpath);
/* 144 */     if (!backDirectory.exists()) {
/* 145 */       backDirectory.mkdir();
/*     */     }
/* 147 */     DateUtils dateUtils = DateUtils.getDateInstance();
/* 148 */     String backFilePath = backpath + SLASH + dateUtils.getNowString() + "." + 
/* 149 */       SUFFIX;
/* 150 */     File file = new File(backFilePath);
/* 151 */     Thread thread = new DateBackupTableThread(file, tableNames);
/* 152 */     thread.start();
/* 153 */     return "data/backupProgress";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/v_listfiles.do"})
/*     */   public String listBackUpFiles(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
/* 159 */     model.addAttribute("list", this.resourceMng.listFile("/WEB-INF/buckup", false));
/* 160 */     return "data/files";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/v_selectfile.do"})
/*     */   public String selectBackUpFiles(ModelMap model, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 167 */     model.addAttribute("list", this.resourceMng.listFile("/WEB-INF/buckup", false));
/* 168 */     return "data/selectfile";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_delete.do"})
/*     */   public String delete(String root, String[] names, HttpServletRequest request, ModelMap model, HttpServletResponse response) {
/* 174 */     WebErrors errors = validateDelete(names, request);
/* 175 */     if (errors.hasErrors()) {
/* 176 */       return errors.showErrorPage(model);
/*     */     }
/* 178 */     int count = this.resourceMng.delete(names);
/* 179 */     log.info("delete Resource count: {}", Integer.valueOf(count));
/* 180 */     for (String name : names) {
/* 181 */       log.info("delete Resource name={}", name);
/* 182 */       this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + 
/* 183 */         name);
/*     */     }
/* 185 */     model.addAttribute("root", root);
/* 186 */     return listBackUpFiles(model, request, response);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
/* 192 */     String name = RequestUtils.getQueryParam(request, "name");
/* 193 */     int count = this.resourceMng.delete(new String[] { name });
/* 194 */     log.info("delete Resource {}, count {}", name, Integer.valueOf(count));
/* 195 */     this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + name);
/* 196 */     return listBackUpFiles(model, request, response);
/*     */   }
/*     */   @RequestMapping({"/data/v_rename.do"})
/*     */   public String renameInput(HttpServletRequest request, ModelMap model) {
/* 201 */     String name = RequestUtils.getQueryParam(request, "name");
/* 202 */     String origName = name.substring("/WEB-INF/buckup".length());
/* 203 */     model.addAttribute("origName", origName);
/* 204 */     return "data/rename";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/data/o_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String renameSubmit(String root, String origName, String distName, HttpServletRequest request, ModelMap model, HttpServletResponse response) {
/* 210 */     String orig = "/WEB-INF/buckup" + origName;
/* 211 */     String dist = "/WEB-INF/buckup" + distName;
/* 212 */     this.resourceMng.rename(orig, dist);
/* 213 */     log.info("name Resource from {} to {}", orig, dist);
/* 214 */     model.addAttribute("root", root);
/* 215 */     return listBackUpFiles(model, request, response);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_export.do"})
/*     */   public String exportSubmit(String[] names, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
/*     */   {
/* 222 */     if (validate(names, request)) {
/* 223 */       WebErrors errors = WebErrors.create(request);
/* 224 */       errors.addErrorCode("template.invalidParams");
/* 225 */       return errors.showErrorPage(model);
/*     */     }
/* 227 */     String backName = "back";
/* 228 */     if (names[0] != null) {
/* 229 */       backName = names[0].substring(names[0].indexOf("/WEB-INF/buckup") + "/WEB-INF/buckup".length() + 1);
/*     */     }
/* 231 */     List fileEntrys = new ArrayList();
/* 232 */     response.setContentType("application/x-download;charset=UTF-8");
/* 233 */     response.addHeader("Content-disposition", "filename=" + 
/* 234 */       backName + ".zip");
/* 235 */     for (String filename : names) {
/* 236 */       File file = new File(this.realPathResolver.get(filename));
/* 237 */       fileEntrys.add(new Zipper.FileEntry("", "", file));
/*     */     }
/*     */     try
/*     */     {
/* 241 */       Zipper.zip(response.getOutputStream(), fileEntrys, "GBK");
/*     */     } catch (IOException e) {
/* 243 */       log.error("export db error!", e);
/*     */     }
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/data/o_backup_progress.do"})
/*     */   public void getBackupProgress(HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 252 */     JSONObject json = new JSONObject();
/* 253 */     json.put("tablename", backup_table);
/* 254 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   public void dbXml(String fileName, String oldDbHost, String dbHost) throws Exception
/*     */   {
/* 259 */     String s = FileUtils.readFileToString(new File(fileName));
/* 260 */     s = StringUtils.replace(s, oldDbHost, dbHost);
/* 261 */     FileUtils.writeStringToFile(new File(fileName), s);
/*     */   }
/*     */ 
/*     */   private String readFile(String filename) throws IOException {
/* 265 */     File file = new File(filename);
/* 266 */     if ((filename == null) || (filename.equals("")))
/*     */     {
/* 268 */       throw new NullPointerException("<@s.m 'db.fileerror'/>");
/*     */     }
/* 270 */     long len = file.length();
/* 271 */     byte[] bytes = new byte[(int)len];
/* 272 */     BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
/* 273 */     int r = bufferedInputStream.read(bytes);
/* 274 */     if (r != len)
/* 275 */       throw new IOException("<@s.m 'db.filereaderror'/>");
/* 276 */     bufferedInputStream.close();
/* 277 */     return new String(bytes, "utf-8");
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(String[] names, HttpServletRequest request) {
/* 281 */     WebErrors errors = WebErrors.create(request);
/* 282 */     errors.ifEmpty(names, "names");
/* 283 */     for (String id : names) {
/* 284 */       vldExist(id, errors);
/*     */     }
/* 286 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(String name, WebErrors errors) {
/* 290 */     return errors.ifNull(name, "name");
/*     */   }
/*     */ 
/*     */   private boolean validate(String[] names, HttpServletRequest request)
/*     */   {
/* 296 */     if (names != null) {
/* 297 */       for (String name : names)
/*     */       {
/* 299 */         if (!name.contains("/WEB-INF/backup/")) {
/* 300 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 304 */     return false;
/*     */   }
/*     */   private class DateBackupTableThread extends Thread {
/*     */     private File file;
/*     */     private String[] tablenames;
/*     */ 
/* 312 */     public DateBackupTableThread(File file, String[] tablenames) { this.file = file;
/* 313 */       this.tablenames = tablenames; }
/*     */ 
/*     */     public void run()
/*     */     {
/* 317 */       OutputStreamWriter writer = null;
/*     */       try {
/* 319 */         FileOutputStream out = new FileOutputStream(this.file);
/* 320 */         writer = new OutputStreamWriter(out, "utf8");
/* 321 */         writer.write(Constants.ONESQL_PREFIX + DataAct.DISABLEFOREIGN);
/* 322 */         for (int i = 0; i < this.tablenames.length; i++) {
/* 323 */           DataAct.backup_table = this.tablenames[i];
/* 324 */           backupTable(writer, this.tablenames[i]);
/*     */         }
/* 326 */         writer.write(Constants.ONESQL_PREFIX + DataAct.ABLEFOREIGN);
/* 327 */         DataAct.backup_table = "";
/* 328 */         writer.close();
/* 329 */         out.close();
/*     */       } catch (IOException e) {
/* 331 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     private String backupTable(OutputStreamWriter writer, String tablename) throws IOException {
/* 335 */       writer.write(createOneTableSql(tablename));
/* 336 */       writer.flush();
/* 337 */       return tablename;
/*     */     }
/*     */ 
/*     */     private String createOneTableSql(String tablename) {
/* 341 */       StringBuffer buffer = new StringBuffer();
/*     */ 
/* 343 */       buffer.append(Constants.ONESQL_PREFIX + "DROP TABLE IF EXISTS " + 
/* 344 */         tablename + DataAct.BRANCH + DataAct.BR);
/* 345 */       buffer.append(Constants.ONESQL_PREFIX + 
/* 346 */         DataAct.this.dataBackMng.createTableDDL(tablename) + DataAct.BRANCH + DataAct.BR + 
/* 347 */         Constants.ONESQL_PREFIX);
/* 348 */       List results = DataAct.this.dataBackMng.createTableData(tablename);
/* 349 */       for (int i = 0; i < results.size(); i++)
/*     */       {
/* 351 */         Object[] oneResult = (Object[])results.get(i);
/* 352 */         buffer.append(createOneInsertSql(oneResult, tablename));
/*     */       }
/* 354 */       return buffer.toString();
/*     */     }
/*     */ 
/*     */     private String createOneInsertSql(Object[] oneResult, String tablename) {
/* 358 */       StringBuffer buffer = new StringBuffer();
/* 359 */       buffer.append(Constants.ONESQL_PREFIX + DataAct.INSERT_INTO + DataAct.SPLIT + tablename + 
/* 360 */         DataAct.SPLIT + DataAct.SPACE + DataAct.VALUES + DataAct.LEFTBRACE);
/* 361 */       for (int j = 0; j < oneResult.length; j++) {
/* 362 */         if (oneResult[j] != null) {
/* 363 */           if ((oneResult[j] instanceof Date))
/* 364 */             buffer.append(DataAct.QUOTES + oneResult[j] + DataAct.QUOTES);
/* 365 */           else if ((oneResult[j] instanceof String))
/* 366 */             buffer.append(DataAct.QUOTES + 
/* 367 */               StrUtils.replaceKeyString((String)oneResult[j]) + 
/* 368 */               DataAct.QUOTES);
/* 369 */           else if ((oneResult[j] instanceof Boolean)) {
/* 370 */             if (((Boolean)oneResult[j]).booleanValue())
/* 371 */               buffer.append(1);
/*     */             else
/* 373 */               buffer.append(0);
/*     */           }
/*     */           else
/* 376 */             buffer.append(oneResult[j]);
/*     */         }
/*     */         else {
/* 379 */           buffer.append(oneResult[j]);
/*     */         }
/* 381 */         buffer.append(DataAct.COMMA);
/*     */       }
/* 383 */       buffer = buffer.deleteCharAt(buffer.lastIndexOf(DataAct.COMMA));
/* 384 */       buffer.append(DataAct.RIGHTBRACE + DataAct.BRANCH + DataAct.BR);
/* 385 */       return buffer.toString();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.DataAct
 * JD-Core Version:    0.6.0
 */