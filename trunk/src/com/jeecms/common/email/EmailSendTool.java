/*     */ package com.jeecms.common.email;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.Authenticator;
/*     */ import javax.mail.Message.RecipientType;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.PasswordAuthentication;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ 
/*     */ public class EmailSendTool
/*     */ {
/*     */   private String host;
/*     */   private String username;
/*     */   private String password;
/*  24 */   private String mail_head_name = "this is head of this mail";
/*     */ 
/*  26 */   private String mail_head_value = "this is head of this mail";
/*     */   private String mail_to;
/*     */   private String mail_from;
/*  32 */   private String mail_subject = "this is the subject of this test mail";
/*     */ 
/*  34 */   private String mail_body = "this is the mail_body of this test mail";
/*     */ 
/*  36 */   private String personalName = "";
/*     */ 
/*     */   public EmailSendTool()
/*     */   {
/*     */   }
/*     */ 
/*     */   public EmailSendTool(String host, String username, String password, String mailto, String subject, String text, String name, String head_name, String head_value)
/*     */   {
/*  44 */     this.host = host;
/*  45 */     this.username = username;
/*  46 */     this.mail_from = username;
/*  47 */     this.password = password;
/*  48 */     this.mail_to = mailto;
/*  49 */     this.mail_subject = subject;
/*  50 */     this.mail_body = text;
/*  51 */     this.personalName = name;
/*  52 */     this.mail_head_name = head_name;
/*  53 */     this.mail_head_value = head_value;
/*     */   }
/*     */ 
/*     */   public void send()
/*     */     throws MessagingException, UnsupportedEncodingException
/*     */   {
/*  64 */     Properties props = new Properties();
/*  65 */     Authenticator auth = new Email_Autherticator();
/*  66 */     props.put("mail.smtp.host", this.host);
/*  67 */     props.put("mail.smtp.auth", "true");
/*  68 */     Session session = Session.getDefaultInstance(props, auth);
/*     */ 
/*  70 */     MimeMessage message = new MimeMessage(session);
/*     */ 
/*  72 */     message.setSubject(this.mail_subject);
/*  73 */     message.setText(this.mail_body);
/*  74 */     message.setHeader(this.mail_head_name, this.mail_head_value);
/*     */ 
/*  76 */     message.setSentDate(new Date());
/*  77 */     Address address = new InternetAddress(this.mail_from, this.personalName);
/*  78 */     message.setFrom(address);
/*  79 */     Address toAddress = new InternetAddress(this.mail_to);
/*  80 */     message.addRecipient(RecipientType.TO, toAddress);
/*  81 */     Transport.send(message);
/*     */   }
/*     */ 
/*     */   public String getHost()
/*     */   {
/* 104 */     return this.host;
/*     */   }
/*     */ 
/*     */   public void setHost(String host) {
/* 108 */     this.host = host;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 112 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 116 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/* 120 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/* 124 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getMail_head_name() {
/* 128 */     return this.mail_head_name;
/*     */   }
/*     */ 
/*     */   public void setMail_head_name(String mail_head_name) {
/* 132 */     this.mail_head_name = mail_head_name;
/*     */   }
/*     */ 
/*     */   public String getMail_head_value() {
/* 136 */     return this.mail_head_value;
/*     */   }
/*     */ 
/*     */   public void setMail_head_value(String mail_head_value) {
/* 140 */     this.mail_head_value = mail_head_value;
/*     */   }
/*     */ 
/*     */   public String getMail_to() {
/* 144 */     return this.mail_to;
/*     */   }
/*     */ 
/*     */   public void setMail_to(String mail_to) {
/* 148 */     this.mail_to = mail_to;
/*     */   }
/*     */ 
/*     */   public String getMail_from() {
/* 152 */     return this.mail_from;
/*     */   }
/*     */ 
/*     */   public void setMail_from(String mail_from) {
/* 156 */     this.mail_from = mail_from;
/*     */   }
/*     */ 
/*     */   public String getMail_subject() {
/* 160 */     return this.mail_subject;
/*     */   }
/*     */ 
/*     */   public void setMail_subject(String mail_subject) {
/* 164 */     this.mail_subject = mail_subject;
/*     */   }
/*     */ 
/*     */   public String getMail_body() {
/* 168 */     return this.mail_body;
/*     */   }
/*     */ 
/*     */   public void setMail_body(String mail_body) {
/* 172 */     this.mail_body = mail_body;
/*     */   }
/*     */ 
/*     */   public String getPersonalName() {
/* 176 */     return this.personalName;
/*     */   }
/*     */ 
/*     */   public void setPersonalName(String personalName) {
/* 180 */     this.personalName = personalName;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 184 */     EmailSendTool sendEmail = new EmailSendTool("smtp.163.com", 
/* 185 */       "jeecms2012@163.com", "jeecms2012strong", "664592270@qq.com", 
/* 186 */       "测试", "文本内容", "我", "", "");
/*     */     try {
/* 188 */       sendEmail.send();
/*     */     } catch (Exception ex) {
/* 190 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public class Email_Autherticator extends Authenticator
/*     */   {
/*     */     public Email_Autherticator()
/*     */     {
/*     */     }
/*     */ 
/*     */     public Email_Autherticator(String user, String pwd)
/*     */     {
/*  94 */       EmailSendTool.this.username = user;
/*  95 */       EmailSendTool.this.password = pwd;
/*     */     }
/*     */ 
/*     */     public PasswordAuthentication getPasswordAuthentication() {
/*  99 */       return new PasswordAuthentication(EmailSendTool.this.username, EmailSendTool.this.password);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.email.EmailSendTool
 * JD-Core Version:    0.6.0
 */