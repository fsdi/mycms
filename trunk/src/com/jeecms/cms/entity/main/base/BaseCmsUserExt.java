/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsUserExt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsUserExt";
/*  18 */   public static String PROP_MSN = "msn";
/*  19 */   public static String PROP_BIRTHDAY = "birthday";
/*  20 */   public static String PROP_GENDER = "gender";
/*  21 */   public static String PROP_MOBILE = "mobile";
/*  22 */   public static String PROP_COMEFROM = "comefrom";
/*  23 */   public static String PROP_USER = "user";
/*  24 */   public static String PROP_INTRO = "intro";
/*  25 */   public static String PROP_REALNAME = "realname";
/*  26 */   public static String PROP_QQ = "qq";
/*  27 */   public static String PROP_ID = "id";
/*  28 */   public static String PROP_PHONE = "phone";
/*     */ 
/*  48 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String realname;
/*     */   private Boolean gender;
/*     */   private Date birthday;
/*     */   private String intro;
/*     */   private String comefrom;
/*     */   private String qq;
/*     */   private String msn;
/*     */   private String phone;
/*     */   private String mobile;
/*     */   private String userImg;
/*     */   private String userSignature;
/*     */   private CmsUser user;
/*     */ 
/*     */   public BaseCmsUserExt()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUserExt(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  78 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  86 */     this.id = id;
/*  87 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getRealname()
/*     */   {
/*  97 */     return this.realname;
/*     */   }
/*     */ 
/*     */   public void setRealname(String realname)
/*     */   {
/* 105 */     this.realname = realname;
/*     */   }
/*     */ 
/*     */   public Boolean getGender()
/*     */   {
/* 113 */     return this.gender;
/*     */   }
/*     */ 
/*     */   public void setGender(Boolean gender)
/*     */   {
/* 121 */     this.gender = gender;
/*     */   }
/*     */ 
/*     */   public Date getBirthday()
/*     */   {
/* 129 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/* 137 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   public String getIntro()
/*     */   {
/* 145 */     return this.intro;
/*     */   }
/*     */ 
/*     */   public void setIntro(String intro)
/*     */   {
/* 153 */     this.intro = intro;
/*     */   }
/*     */ 
/*     */   public String getComefrom()
/*     */   {
/* 161 */     return this.comefrom;
/*     */   }
/*     */ 
/*     */   public void setComefrom(String comefrom)
/*     */   {
/* 169 */     this.comefrom = comefrom;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/* 177 */     return this.qq;
/*     */   }
/*     */ 
/*     */   public void setQq(String qq)
/*     */   {
/* 185 */     this.qq = qq;
/*     */   }
/*     */ 
/*     */   public String getMsn()
/*     */   {
/* 193 */     return this.msn;
/*     */   }
/*     */ 
/*     */   public void setMsn(String msn)
/*     */   {
/* 201 */     this.msn = msn;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 209 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 217 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 225 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 233 */     this.mobile = mobile;
/*     */   }
/*     */   public String getUserImg() {
/* 236 */     return this.userImg;
/*     */   }
/*     */ 
/*     */   public void setUserImg(String userImg) {
/* 240 */     this.userImg = userImg;
/*     */   }
/*     */ 
/*     */   public String getUserSignature() {
/* 244 */     return this.userSignature;
/*     */   }
/*     */ 
/*     */   public void setUserSignature(String userSignature) {
/* 248 */     this.userSignature = userSignature;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 256 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 264 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 270 */     if (obj == null) return false;
/* 271 */     if (!(obj instanceof CmsUserExt)) return false;
/*     */ 
/* 273 */     CmsUserExt cmsUserExt = (CmsUserExt)obj;
/* 274 */     if ((getId() == null) || (cmsUserExt.getId() == null)) return false;
/* 275 */     return getId().equals(cmsUserExt.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 280 */     if (-2147483648 == this.hashCode) {
/* 281 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 283 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 284 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 287 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 292 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsUserExt
 * JD-Core Version:    0.6.0
 */