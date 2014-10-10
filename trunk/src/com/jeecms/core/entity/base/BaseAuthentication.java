/*     */ package com.jeecms.core.entity.base;
/*     */ 
/*     */ import com.jeecms.core.entity.Authentication;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseAuthentication
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "Authentication";
/*  18 */   public static String PROP_LOGIN_IP = "loginIp";
/*  19 */   public static String PROP_LOGIN_TIME = "loginTime";
/*  20 */   public static String PROP_UPDATE_TIME = "updateTime";
/*  21 */   public static String PROP_EMAIL = "email";
/*  22 */   public static String PROP_ID = "id";
/*  23 */   public static String PROP_USERNAME = "username";
/*  24 */   public static String PROP_UID = "uid";
/*     */ 
/*  64 */   private int hashCode = -2147483648;
/*     */   private String id;
/*     */   private Integer uid;
/*     */   private String username;
/*     */   private String email;
/*     */   private Date loginTime;
/*     */   private String loginIp;
/*     */   private Date updateTime;
/*     */ 
/*     */   public BaseAuthentication()
/*     */   {
/*  29 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseAuthentication(String id)
/*     */   {
/*  36 */     setId(id);
/*  37 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseAuthentication(String id, Integer uid, String username, Date loginTime, String loginIp, Date updateTime)
/*     */   {
/*  51 */     setId(id);
/*  52 */     setUid(uid);
/*  53 */     setUsername(username);
/*  54 */     setLoginTime(loginTime);
/*  55 */     setLoginIp(loginIp);
/*  56 */     setUpdateTime(updateTime);
/*  57 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  94 */     this.id = id;
/*  95 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Integer getUid()
/*     */   {
/* 105 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Integer uid)
/*     */   {
/* 113 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/* 121 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/* 129 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 137 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 145 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public Date getLoginTime()
/*     */   {
/* 153 */     return this.loginTime;
/*     */   }
/*     */ 
/*     */   public void setLoginTime(Date loginTime)
/*     */   {
/* 161 */     this.loginTime = loginTime;
/*     */   }
/*     */ 
/*     */   public String getLoginIp()
/*     */   {
/* 169 */     return this.loginIp;
/*     */   }
/*     */ 
/*     */   public void setLoginIp(String loginIp)
/*     */   {
/* 177 */     this.loginIp = loginIp;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 185 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime)
/*     */   {
/* 193 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 199 */     if (obj == null) return false;
/* 200 */     if (!(obj instanceof Authentication)) return false;
/*     */ 
/* 202 */     Authentication authentication = (Authentication)obj;
/* 203 */     if ((getId() == null) || (authentication.getId() == null)) return false;
/* 204 */     return getId().equals(authentication.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 209 */     if (-2147483648 == this.hashCode) {
/* 210 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 212 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 213 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 216 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 221 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.base.BaseAuthentication
 * JD-Core Version:    0.6.0
 */