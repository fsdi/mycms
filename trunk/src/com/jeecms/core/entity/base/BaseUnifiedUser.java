/*     */ package com.jeecms.core.entity.base;
/*     */ 
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseUnifiedUser
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "UnifiedUser";
/*  18 */   public static String PROP_ACTIVATION = "activation";
/*  19 */   public static String PROP_ERROR_COUNT = "errorCount";
/*  20 */   public static String PROP_ACTIVATION_CODE = "activationCode";
/*  21 */   public static String PROP_ERROR_IP = "errorIp";
/*  22 */   public static String PROP_PASSWORD = "password";
/*  23 */   public static String PROP_ERROR_TIME = "errorTime";
/*  24 */   public static String PROP_RESET_KEY = "resetKey";
/*  25 */   public static String PROP_LOGIN_COUNT = "loginCount";
/*  26 */   public static String PROP_LAST_LOGIN_TIME = "lastLoginTime";
/*  27 */   public static String PROP_EMAIL = "email";
/*  28 */   public static String PROP_REGISTER_TIME = "registerTime";
/*  29 */   public static String PROP_USERNAME = "username";
/*  30 */   public static String PROP_ID = "id";
/*  31 */   public static String PROP_REGISTER_IP = "registerIp";
/*  32 */   public static String PROP_LAST_LOGIN_IP = "lastLoginIp";
/*  33 */   public static String PROP_RESET_PWD = "resetPwd";
/*     */ 
/*  77 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String username;
/*     */   private String email;
/*     */   private String password;
/*     */   private Date registerTime;
/*     */   private String registerIp;
/*     */   private Date lastLoginTime;
/*     */   private String lastLoginIp;
/*     */   private Integer loginCount;
/*     */   private String resetKey;
/*     */   private String resetPwd;
/*     */   private Date errorTime;
/*     */   private Integer errorCount;
/*     */   private String errorIp;
/*     */   private Boolean activation;
/*     */   private String activationCode;
/*     */ 
/*     */   public BaseUnifiedUser()
/*     */   {
/*  38 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseUnifiedUser(Integer id)
/*     */   {
/*  45 */     setId(id);
/*  46 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseUnifiedUser(Integer id, String username, String password, Date registerTime, String registerIp, Integer loginCount, Integer errorCount, Boolean activation)
/*     */   {
/*  62 */     setId(id);
/*  63 */     setUsername(username);
/*  64 */     setPassword(password);
/*  65 */     setRegisterTime(registerTime);
/*  66 */     setRegisterIp(registerIp);
/*  67 */     setLoginCount(loginCount);
/*  68 */     setErrorCount(errorCount);
/*  69 */     setActivation(activation);
/*  70 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 108 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 116 */     this.id = id;
/* 117 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/* 127 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/* 135 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 143 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 151 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 159 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 167 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public Date getRegisterTime()
/*     */   {
/* 175 */     return this.registerTime;
/*     */   }
/*     */ 
/*     */   public void setRegisterTime(Date registerTime)
/*     */   {
/* 183 */     this.registerTime = registerTime;
/*     */   }
/*     */ 
/*     */   public String getRegisterIp()
/*     */   {
/* 191 */     return this.registerIp;
/*     */   }
/*     */ 
/*     */   public void setRegisterIp(String registerIp)
/*     */   {
/* 199 */     this.registerIp = registerIp;
/*     */   }
/*     */ 
/*     */   public Date getLastLoginTime()
/*     */   {
/* 207 */     return this.lastLoginTime;
/*     */   }
/*     */ 
/*     */   public void setLastLoginTime(Date lastLoginTime)
/*     */   {
/* 215 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */ 
/*     */   public String getLastLoginIp()
/*     */   {
/* 223 */     return this.lastLoginIp;
/*     */   }
/*     */ 
/*     */   public void setLastLoginIp(String lastLoginIp)
/*     */   {
/* 231 */     this.lastLoginIp = lastLoginIp;
/*     */   }
/*     */ 
/*     */   public Integer getLoginCount()
/*     */   {
/* 239 */     return this.loginCount;
/*     */   }
/*     */ 
/*     */   public void setLoginCount(Integer loginCount)
/*     */   {
/* 247 */     this.loginCount = loginCount;
/*     */   }
/*     */ 
/*     */   public String getResetKey()
/*     */   {
/* 255 */     return this.resetKey;
/*     */   }
/*     */ 
/*     */   public void setResetKey(String resetKey)
/*     */   {
/* 263 */     this.resetKey = resetKey;
/*     */   }
/*     */ 
/*     */   public String getResetPwd()
/*     */   {
/* 271 */     return this.resetPwd;
/*     */   }
/*     */ 
/*     */   public void setResetPwd(String resetPwd)
/*     */   {
/* 279 */     this.resetPwd = resetPwd;
/*     */   }
/*     */ 
/*     */   public Date getErrorTime()
/*     */   {
/* 287 */     return this.errorTime;
/*     */   }
/*     */ 
/*     */   public void setErrorTime(Date errorTime)
/*     */   {
/* 295 */     this.errorTime = errorTime;
/*     */   }
/*     */ 
/*     */   public Integer getErrorCount()
/*     */   {
/* 303 */     return this.errorCount;
/*     */   }
/*     */ 
/*     */   public void setErrorCount(Integer errorCount)
/*     */   {
/* 311 */     this.errorCount = errorCount;
/*     */   }
/*     */ 
/*     */   public String getErrorIp()
/*     */   {
/* 319 */     return this.errorIp;
/*     */   }
/*     */ 
/*     */   public void setErrorIp(String errorIp)
/*     */   {
/* 327 */     this.errorIp = errorIp;
/*     */   }
/*     */ 
/*     */   public Boolean getActivation()
/*     */   {
/* 335 */     return this.activation;
/*     */   }
/*     */ 
/*     */   public void setActivation(Boolean activation)
/*     */   {
/* 343 */     this.activation = activation;
/*     */   }
/*     */ 
/*     */   public String getActivationCode()
/*     */   {
/* 351 */     return this.activationCode;
/*     */   }
/*     */ 
/*     */   public void setActivationCode(String activationCode)
/*     */   {
/* 359 */     this.activationCode = activationCode;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 365 */     if (obj == null) return false;
/* 366 */     if (!(obj instanceof UnifiedUser)) return false;
/*     */ 
/* 368 */     UnifiedUser unifiedUser = (UnifiedUser)obj;
/* 369 */     if ((getId() == null) || (unifiedUser.getId() == null)) return false;
/* 370 */     return getId().equals(unifiedUser.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 375 */     if (-2147483648 == this.hashCode) {
/* 376 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 378 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 379 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 382 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 387 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.base.BaseUnifiedUser
 * JD-Core Version:    0.6.0
 */