/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseEmailConfig
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "EmailConfig";
/*  18 */   public static String PROP_PASSWORD = "password";
/*  19 */   public static String PROP_HOST = "host";
/*  20 */   public static String PROP_ENCODING = "encoding";
/*  21 */   public static String PROP_PERSONAL = "personal";
/*  22 */   public static String PROP_USERNAME = "username";
/*     */   private String host;
/*     */   private String encoding;
/*     */   private String username;
/*     */   private String password;
/*     */   private String personal;
/*     */ 
/*     */   public BaseEmailConfig()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getHost()
/*     */   {
/*  50 */     return this.host;
/*     */   }
/*     */ 
/*     */   public void setHost(String host)
/*     */   {
/*  58 */     this.host = host;
/*     */   }
/*     */ 
/*     */   public String getEncoding()
/*     */   {
/*  66 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   public void setEncoding(String encoding)
/*     */   {
/*  74 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/*  82 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/*  90 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/*  98 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 106 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getPersonal()
/*     */   {
/* 114 */     return this.personal;
/*     */   }
/*     */ 
/*     */   public void setPersonal(String personal)
/*     */   {
/* 122 */     this.personal = personal;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseEmailConfig
 * JD-Core Version:    0.6.0
 */