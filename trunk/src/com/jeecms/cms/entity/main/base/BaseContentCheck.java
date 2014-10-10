/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentCheck;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseContentCheck
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentCheck";
/*  18 */   public static String PROP_REJECTED = "rejected";
/*  19 */   public static String PROP_CHECK_STEP = "checkStep";
/*  20 */   public static String PROP_CONTENT = "content";
/*  21 */   public static String PROP_ID = "id";
/*  22 */   public static String PROP_CHECK_OPINION = "checkOpinion";
/*     */ 
/*  56 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Byte checkStep;
/*     */   private String checkOpinion;
/*     */   private Boolean rejected;
/*     */   private Date checkDate;
/*     */   private CmsUser reviewer;
/*     */   private Content content;
/*     */ 
/*     */   public BaseContentCheck()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentCheck(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentCheck(Integer id, Byte checkStep, Boolean rejected)
/*     */   {
/*  46 */     setId(id);
/*  47 */     setCheckStep(checkStep);
/*  48 */     setRejected(rejected);
/*  49 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  89 */     this.id = id;
/*  90 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Byte getCheckStep()
/*     */   {
/* 100 */     return this.checkStep;
/*     */   }
/*     */ 
/*     */   public void setCheckStep(Byte checkStep)
/*     */   {
/* 108 */     this.checkStep = checkStep;
/*     */   }
/*     */ 
/*     */   public String getCheckOpinion()
/*     */   {
/* 116 */     return this.checkOpinion;
/*     */   }
/*     */ 
/*     */   public void setCheckOpinion(String checkOpinion)
/*     */   {
/* 124 */     this.checkOpinion = checkOpinion;
/*     */   }
/*     */ 
/*     */   public Boolean getRejected()
/*     */   {
/* 132 */     return this.rejected;
/*     */   }
/*     */ 
/*     */   public void setRejected(Boolean rejected)
/*     */   {
/* 140 */     this.rejected = rejected;
/*     */   }
/*     */ 
/*     */   public Date getCheckDate()
/*     */   {
/* 145 */     return this.checkDate;
/*     */   }
/*     */ 
/*     */   public void setCheckDate(Date checkDate) {
/* 149 */     this.checkDate = checkDate;
/*     */   }
/*     */ 
/*     */   public CmsUser getReviewer() {
/* 153 */     return this.reviewer;
/*     */   }
/*     */ 
/*     */   public void setReviewer(CmsUser reviewer) {
/* 157 */     this.reviewer = reviewer;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 164 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 172 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 178 */     if (obj == null) return false;
/* 179 */     if (!(obj instanceof ContentCheck)) return false;
/*     */ 
/* 181 */     ContentCheck contentCheck = (ContentCheck)obj;
/* 182 */     if ((getId() == null) || (contentCheck.getId() == null)) return false;
/* 183 */     return getId().equals(contentCheck.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 188 */     if (-2147483648 == this.hashCode) {
/* 189 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 191 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 192 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 195 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 200 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentCheck
 * JD-Core Version:    0.6.0
 */