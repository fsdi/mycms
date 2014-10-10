/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsJobApply
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsJobApply";
/*  18 */   public static String PROP_USER = "user";
/*  19 */   public static String PROP_ID = "id";
/*  20 */   public static String PROP_CONTENT = "content";
/*  21 */   public static String PROP_APPLY_TIME = "applyTime";
/*     */ 
/*  57 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Date applyTime;
/*     */   private Content content;
/*     */   private CmsUser user;
/*     */ 
/*     */   public BaseCmsJobApply()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsJobApply(Integer id)
/*     */   {
/*  33 */     setId(id);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsJobApply(Integer id, Content content, CmsUser user, Date applyTime)
/*     */   {
/*  46 */     setId(id);
/*  47 */     setContent(content);
/*  48 */     setUser(user);
/*  49 */     setApplyTime(applyTime);
/*  50 */     initialize();
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
/*     */   public Date getApplyTime()
/*     */   {
/*  97 */     return this.applyTime;
/*     */   }
/*     */ 
/*     */   public void setApplyTime(Date applyTime)
/*     */   {
/* 105 */     this.applyTime = applyTime;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 113 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 121 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 129 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 137 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 143 */     if (obj == null) return false;
/* 144 */     if (!(obj instanceof CmsJobApply)) return false;
/*     */ 
/* 146 */     CmsJobApply cmsJobApply = (CmsJobApply)obj;
/* 147 */     if ((getId() == null) || (cmsJobApply.getId() == null)) return false;
/* 148 */     return getId().equals(cmsJobApply.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 153 */     if (-2147483648 == this.hashCode) {
/* 154 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 156 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 157 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 160 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 165 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsJobApply
 * JD-Core Version:    0.6.0
 */