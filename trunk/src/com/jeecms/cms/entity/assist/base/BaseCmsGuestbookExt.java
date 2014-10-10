/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsGuestbookExt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsGuestbookExt";
/*  18 */   public static String PROP_EMAIL = "email";
/*  19 */   public static String PROP_REPLY = "reply";
/*  20 */   public static String PROP_TITLE = "title";
/*  21 */   public static String PROP_QQ = "qq";
/*  22 */   public static String PROP_CONTENT = "content";
/*  23 */   public static String PROP_ID = "id";
/*  24 */   public static String PROP_PHONE = "phone";
/*  25 */   public static String PROP_GUESTBOOK = "guestbook";
/*     */ 
/*  45 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String content;
/*     */   private String reply;
/*     */   private String email;
/*     */   private String phone;
/*     */   private String qq;
/*     */   private CmsGuestbook guestbook;
/*     */ 
/*     */   public BaseCmsGuestbookExt()
/*     */   {
/*  30 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGuestbookExt(Integer id)
/*     */   {
/*  37 */     setId(id);
/*  38 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  70 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  78 */     this.id = id;
/*  79 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/*  89 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/*  97 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 105 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 113 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getReply()
/*     */   {
/* 121 */     return this.reply;
/*     */   }
/*     */ 
/*     */   public void setReply(String reply)
/*     */   {
/* 129 */     this.reply = reply;
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
/*     */   public String getPhone()
/*     */   {
/* 153 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 161 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/* 169 */     return this.qq;
/*     */   }
/*     */ 
/*     */   public void setQq(String qq)
/*     */   {
/* 177 */     this.qq = qq;
/*     */   }
/*     */ 
/*     */   public CmsGuestbook getGuestbook()
/*     */   {
/* 185 */     return this.guestbook;
/*     */   }
/*     */ 
/*     */   public void setGuestbook(CmsGuestbook guestbook)
/*     */   {
/* 193 */     this.guestbook = guestbook;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 199 */     if (obj == null) return false;
/* 200 */     if (!(obj instanceof CmsGuestbookExt)) return false;
/*     */ 
/* 202 */     CmsGuestbookExt cmsGuestbookExt = (CmsGuestbookExt)obj;
/* 203 */     if ((getId() == null) || (cmsGuestbookExt.getId() == null)) return false;
/* 204 */     return getId().equals(cmsGuestbookExt.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsGuestbookExt
 * JD-Core Version:    0.6.0
 */