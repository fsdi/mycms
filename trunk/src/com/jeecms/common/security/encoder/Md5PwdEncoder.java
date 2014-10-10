/*     */ package com.jeecms.common.security.encoder;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ 
/*     */ public class Md5PwdEncoder
/*     */   implements PwdEncoder
/*     */ {
/*     */   private String defaultSalt;
/*     */ 
/*     */   public String encodePassword(String rawPass)
/*     */   {
/*  14 */     return encodePassword(rawPass, this.defaultSalt);
/*     */   }
/*     */ 
/*     */   public String encodePassword(String rawPass, String salt) {
/*  18 */     String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
/*  19 */     MessageDigest messageDigest = getMessageDigest();
			  byte[] digest;
/*     */     try
/*     */     {
/*  22 */       digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/*  24 */       throw new IllegalStateException("UTF-8 not supported!");
/*     */     }
/*  26 */     return new String(Hex.encodeHex(digest));
/*     */   }
/*     */ 
/*     */   public boolean isPasswordValid(String encPass, String rawPass) {
/*  30 */     return isPasswordValid(encPass, rawPass, this.defaultSalt);
/*     */   }
/*     */ 
/*     */   public boolean isPasswordValid(String encPass, String rawPass, String salt) {
/*  34 */     if (encPass == null) {
/*  35 */       return false;
/*     */     }
/*  37 */     String pass2 = encodePassword(rawPass, salt);
/*  38 */     return encPass.equals(pass2);
/*     */   }
/*     */ 
/*     */   protected final MessageDigest getMessageDigest() {
/*  42 */     String algorithm = "MD5";
/*     */     try {
/*  44 */       return MessageDigest.getInstance(algorithm); } catch (NoSuchAlgorithmException e) {
/*     */     }
/*  46 */     throw new IllegalArgumentException("No such algorithm [" + 
/*  47 */       algorithm + "]");
/*     */   }
/*     */ 
/*     */   protected String mergePasswordAndSalt(String password, Object salt, boolean strict)
/*     */   {
/*  72 */     if (password == null) {
/*  73 */       password = "";
/*     */     }
/*  75 */     if ((strict) && (salt != null) && (
/*  76 */       (salt.toString().lastIndexOf("{") != -1) || 
/*  77 */       (salt.toString().lastIndexOf("}") != -1))) {
/*  78 */       throw new IllegalArgumentException(
/*  79 */         "Cannot use { or } in salt.toString()");
/*     */     }
/*     */ 
/*  82 */     if ((salt == null) || ("".equals(salt))) {
/*  83 */       return password;
/*     */     }
/*  85 */     return password + "{" + salt.toString() + "}";
/*     */   }
/*     */ 
/*     */   public String getDefaultSalt()
/*     */   {
/* 100 */     return this.defaultSalt;
/*     */   }
/*     */ 
/*     */   public void setDefaultSalt(String defaultSalt)
/*     */   {
/* 109 */     this.defaultSalt = defaultSalt;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.encoder.Md5PwdEncoder
 * JD-Core Version:    0.6.0
 */