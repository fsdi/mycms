/*     */ package com.jeecms.cms;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class Install
/*     */ {
/*     */   public static void dbXml(String fileName, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword)
/*     */     throws Exception
/*     */   {
/*  25 */     String s = FileUtils.readFileToString(new File(fileName));
/*  26 */     s = StringUtils.replace(s, "DB_HOST", dbHost);
/*  27 */     s = StringUtils.replace(s, "DB_PORT", dbPort);
/*  28 */     s = StringUtils.replace(s, "DB_NAME", dbName);
/*  29 */     s = StringUtils.replace(s, "DB_USER", dbUser);
/*  30 */     s = StringUtils.replace(s, "DB_PASSWORD", dbPassword);
/*  31 */     FileUtils.writeStringToFile(new File(fileName), s);
/*     */   }
/*     */ 
/*     */   public static Connection getConn(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception
/*     */   {
/*  36 */     Class.forName("com.mysql.jdbc.Driver");
/*  37 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*  38 */     String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + 
/*  39 */       "?user=" + dbUser + "&password=" + dbPassword + 
/*  40 */       "&characterEncoding=utf8";
/*  41 */     Connection conn = DriverManager.getConnection(connStr);
/*  42 */     return conn;
/*     */   }
/*     */ 
/*     */   public static void webXml(String fromFile, String toFile) throws Exception {
/*  46 */     FileUtils.copyFile(new File(fromFile), new File(toFile));
/*     */   }
/*     */ 
/*     */   public static void createDb(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword)
/*     */     throws Exception
/*     */   {
/*  61 */     Class.forName("com.mysql.jdbc.Driver");
/*  62 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*  63 */     String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user=" + 
/*  64 */       dbUser + "&password=" + dbPassword + 
/*  65 */       "&characterEncoding=UTF8";
/*  66 */     Connection conn = DriverManager.getConnection(connStr);
/*  67 */     Statement stat = conn.createStatement();
/*  68 */     String sql = "drop database if exists " + dbName;
/*  69 */     stat.execute(sql);
/*  70 */     sql = "create database " + dbName + " CHARACTER SET UTF8";
/*  71 */     stat.execute(sql);
/*  72 */     stat.close();
/*  73 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static void changeDbCharset(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception
/*     */   {
/*  78 */     Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
/*  79 */     Statement stat = conn.createStatement();
/*  80 */     String sql = "ALTER DATABASE " + dbName + " CHARACTER SET UTF8";
/*  81 */     stat.execute(sql);
/*  82 */     stat.close();
/*  83 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static void createTable(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, List<String> sqlList)
/*     */     throws Exception
/*     */   {
/* 100 */     Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
/* 101 */     Statement stat = conn.createStatement();
/* 102 */     for (String dllsql : sqlList) {
/* 103 */       System.out.println(dllsql);
/* 104 */       stat.execute(dllsql);
/*     */     }
/* 106 */     stat.close();
/* 107 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static void updateConfig(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, String domain, String cxtPath, String port)
/*     */     throws Exception
/*     */   {
/* 126 */     Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
/* 127 */     Statement stat = conn.createStatement();
/* 128 */     String sql = "update jc_site set domain='" + domain + "'";
/* 129 */     stat.executeUpdate(sql);
/* 130 */     sql = "update jc_config set context_path='" + cxtPath + "',port=" + 
/* 131 */       port;
/* 132 */     stat.executeUpdate(sql);
/* 133 */     stat.close();
/* 134 */     conn.close();
/*     */   }
/*     */ 
/*     */   public static List<String> readSql(String fileName)
/*     */     throws Exception
/*     */   {
/* 146 */     BufferedReader br = new BufferedReader(
/* 147 */       new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
/* 148 */     List sqlList = new ArrayList();
/* 149 */     StringBuilder sqlSb = new StringBuilder();
/* 150 */     String s = "";
/* 151 */     while ((s = br.readLine()) != null) {
/* 152 */       if ((s.startsWith("/*")) || (s.startsWith("#")) || 
/* 153 */         (StringUtils.isBlank(s))) {
/*     */         continue;
/*     */       }
/* 156 */       if (s.endsWith(";")) {
/* 157 */         sqlSb.append(s);
/* 158 */         sqlSb.setLength(sqlSb.length() - 1);
/* 159 */         sqlList.add(sqlSb.toString());
/* 160 */         sqlSb.setLength(0);
/*     */       } else {
/* 162 */         sqlSb.append(s);
/*     */       }
/*     */     }
/* 165 */     br.close();
/* 166 */     return sqlList;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.Install
 * JD-Core Version:    0.6.0
 */