/*     */ package com.jeecms.core.entity;
/*     */ 
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.core.entity.base.BaseConfig;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ 
/*     */ public class Config extends BaseConfig
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public Config()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Config(String id)
/*     */   {
/*  25 */     super(id); } 
/*  91 */   public static class ConfigEmailSender implements EmailSender { public static String EMAIL_HOST = "email_host";
/*  92 */     public static String EMAIL_PORT = "email_port";
/*  93 */     public static String EMAIL_ENCODING = "email_encoding";
/*  94 */     public static String EMAIL_USERNAME = "email_username";
/*  95 */     public static String EMAIL_PASSWORD = "email_password";
/*  96 */     public static String EMAIL_PERSONAL = "email_personal";
/*     */     private Map<String, String> attr;
/*     */ 
/* 101 */     public static ConfigEmailSender create(Map<String, String> map) { if ((map == null) || (StringUtils.isBlank((String)map.get(EMAIL_HOST))) || 
/* 102 */         (StringUtils.isBlank((String)map.get(EMAIL_USERNAME))))
/*     */       {
/* 104 */         return null;
/*     */       }
/* 106 */       ConfigEmailSender sender = new ConfigEmailSender();
/* 107 */       sender.attr = map;
/* 108 */       return sender;
/*     */     }
/*     */ 
/*     */     public Map<String, String> getAttr()
/*     */     {
/* 113 */       if (this.attr == null) {
/* 114 */         this.attr = new HashMap();
/*     */       }
/* 116 */       return this.attr;
/*     */     }
/*     */ 
/*     */     public String getHost() {
/* 120 */       return (String)getAttr().get(EMAIL_HOST);
/*     */     }
/*     */ 
/*     */     public void setHost(String host) {
/* 124 */       getAttr().put(EMAIL_HOST, host);
/*     */     }
/*     */ 
/*     */     public Integer getPort() {
/* 128 */       String port = (String)getAttr().get(EMAIL_HOST);
/* 129 */       if ((StringUtils.isNotBlank(port)) && (NumberUtils.isDigits(port))) {
/* 130 */         return Integer.valueOf(Integer.parseInt(port));
/*     */       }
/* 132 */       return null;
/*     */     }
/*     */ 
/*     */     public void setPort(Integer port)
/*     */     {
/* 137 */       getAttr().put(EMAIL_PORT, port != null ? port.toString() : null);
/*     */     }
/*     */ 
/*     */     public String getEncoding() {
/* 141 */       String encoding = (String)getAttr().get(EMAIL_ENCODING);
/* 142 */       return StringUtils.isNotBlank(encoding) ? encoding : null;
/*     */     }
/*     */ 
/*     */     public void setEncoding(String encoding) {
/* 146 */       getAttr().put(EMAIL_ENCODING, encoding);
/*     */     }
/*     */ 
/*     */     public String getUsername() {
/* 150 */       return (String)getAttr().get(EMAIL_USERNAME);
/*     */     }
/*     */ 
/*     */     public void setUsername(String username) {
/* 154 */       getAttr().put(EMAIL_USERNAME, username);
/*     */     }
/*     */ 
/*     */     public String getPassword() {
/* 158 */       String password = (String)getAttr().get(EMAIL_PASSWORD);
/* 159 */       return StringUtils.isNotBlank(password) ? password : null;
/*     */     }
/*     */ 
/*     */     public void setPassword(String password) {
/* 163 */       getAttr().put(EMAIL_PASSWORD, password);
/*     */     }
/*     */ 
/*     */     public String getPersonal() {
/* 167 */       String personal = (String)getAttr().get(EMAIL_PERSONAL);
/* 168 */       return StringUtils.isNotBlank(personal) ? personal : null;
/*     */     }
/*     */ 
/*     */     public void setPersonal(String personal) {
/* 172 */       getAttr().put(EMAIL_PERSONAL, personal);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class ConfigLogin
/*     */   {
/*  31 */     public static String LOGIN_ERROR_INTERVAL = "login_error_interval";
/*  32 */     public static String LOGIN_ERROR_TIMES = "login_error_times";
/*     */     private Map<String, String> attr;
/*     */ 
/*     */     public static ConfigLogin create(Map<String, String> map)
/*     */     {
/*  37 */       ConfigLogin configLogin = new ConfigLogin();
/*  38 */       configLogin.setAttr(map);
/*  39 */       return configLogin;
/*     */     }
/*     */ 
/*     */     public Map<String, String> getAttr() {
/*  43 */       if (this.attr == null) {
/*  44 */         this.attr = new HashMap();
/*     */       }
/*  46 */       return this.attr;
/*     */     }
/*     */ 
/*     */     public void setAttr(Map<String, String> attr) {
/*  50 */       this.attr = attr;
/*     */     }
/*     */ 
/*     */     public Integer getErrorInterval() {
/*  54 */       String interval = (String)getAttr().get(LOGIN_ERROR_INTERVAL);
/*  55 */       if (NumberUtils.isDigits(interval)) {
/*  56 */         return Integer.valueOf(Integer.parseInt(interval));
/*     */       }
/*     */ 
/*  59 */       return Integer.valueOf(30);
/*     */     }
/*     */ 
/*     */     public void setErrorInterval(Integer errorInterval)
/*     */     {
/*  64 */       if (errorInterval != null)
/*  65 */         getAttr().put(LOGIN_ERROR_INTERVAL, errorInterval.toString());
/*     */       else
/*  67 */         getAttr().put(LOGIN_ERROR_INTERVAL, null);
/*     */     }
/*     */ 
/*     */     public Integer getErrorTimes()
/*     */     {
/*  72 */       String times = (String)getAttr().get(LOGIN_ERROR_TIMES);
/*  73 */       if (NumberUtils.isDigits(times)) {
/*  74 */         return Integer.valueOf(Integer.parseInt(times));
/*     */       }
/*     */ 
/*  77 */       return Integer.valueOf(3);
/*     */     }
/*     */ 
/*     */     public void setErrorTimes(Integer errorTimes)
/*     */     {
/*  82 */       if (errorTimes != null)
/*  83 */         getAttr().put(LOGIN_ERROR_TIMES, errorTimes.toString());
/*     */       else
/*  85 */         getAttr().put(LOGIN_ERROR_TIMES, null); 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class ConfigMessageTemplate implements MessageTemplate {
/* 177 */     public static String MESSAGE_FORGOTPASSWORD_SUBJECT = "message_forgotpassword_subject";
/* 178 */     public static String MESSAGE_FORGOTPASSWORD_TEXT = "message_forgotpassword_text";
/* 179 */     public static String MESSAGE_REGISTER_SUBJECT = "message_register_subject";
/* 180 */     public static String MESSAGE_REGISTER_TEXT = "message_register_text";
/*     */     private Map<String, String> attr;
/*     */ 
/*     */     public static ConfigMessageTemplate createForgotPasswordMessageTemplate(Map<String, String> map) {
/* 185 */       if ((map == null) || (StringUtils.isBlank((String)map.get(MESSAGE_FORGOTPASSWORD_SUBJECT))) || 
/* 186 */         (StringUtils.isBlank((String)map.get(MESSAGE_FORGOTPASSWORD_TEXT))))
/*     */       {
/* 188 */         return null;
/*     */       }
/* 190 */       ConfigMessageTemplate tpl = new ConfigMessageTemplate();
/* 191 */       tpl.setAttr(map);
/* 192 */       return tpl;
/*     */     }
/*     */ 
/*     */     public static ConfigMessageTemplate createRegisterMessageTemplate(Map<String, String> map) {
/* 196 */       if ((map == null) || (StringUtils.isBlank((String)map.get(MESSAGE_REGISTER_SUBJECT))) || 
/* 197 */         (StringUtils.isBlank((String)map.get(MESSAGE_REGISTER_TEXT))))
/*     */       {
/* 199 */         return null;
/*     */       }
/* 201 */       ConfigMessageTemplate tpl = new ConfigMessageTemplate();
/* 202 */       tpl.setAttr(map);
/* 203 */       return tpl;
/*     */     }
/*     */ 
/*     */     public Map<String, String> getAttr() {
/* 207 */       if (this.attr == null) {
/* 208 */         this.attr = new HashMap();
/*     */       }
/* 210 */       return this.attr;
/*     */     }
/*     */ 
/*     */     public void setAttr(Map<String, String> attr) {
/* 214 */       this.attr = attr;
/*     */     }
/*     */ 
/*     */     public String getForgotPasswordSubject() {
/* 218 */       return (String)getAttr().get(MESSAGE_FORGOTPASSWORD_SUBJECT);
/*     */     }
/*     */ 
/*     */     public void setForgotPasswordSubject(String subject) {
/* 222 */       getAttr().put(MESSAGE_FORGOTPASSWORD_SUBJECT, subject);
/*     */     }
/*     */ 
/*     */     public String getForgotPasswordText() {
/* 226 */       return (String)getAttr().get(MESSAGE_FORGOTPASSWORD_TEXT);
/*     */     }
/*     */ 
/*     */     public void setForgotPasswordText(String text) {
/* 230 */       getAttr().put(MESSAGE_FORGOTPASSWORD_TEXT, text);
/*     */     }
/*     */ 
/*     */     public String getRegisterSubject() {
/* 234 */       return (String)getAttr().get(MESSAGE_REGISTER_SUBJECT);
/*     */     }
/*     */ 
/*     */     public void setRegisterSubject(String subject) {
/* 238 */       getAttr().put(MESSAGE_REGISTER_SUBJECT, subject);
/*     */     }
/*     */ 
/*     */     public String getRegisterText() {
/* 242 */       return (String)getAttr().get(MESSAGE_REGISTER_TEXT);
/*     */     }
/*     */ 
/*     */     public void setRegisterText(String text) {
/* 246 */       getAttr().put(MESSAGE_REGISTER_TEXT, text);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.Config
 * JD-Core Version:    0.6.0
 */