/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsCommentExt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsCommentExt";
/*  18 */   public static String PROP_COMMENT = "comment";
/*  19 */   public static String PROP_IP = "ip";
/*  20 */   public static String PROP_TEXT = "text";
/*  21 */   public static String PROP_REPLY = "reply";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  42 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String ip;
/*     */   private String text;
/*     */   private String reply;
/*     */   private CmsComment comment;
/*     */ 
/*     */   public BaseCmsCommentExt()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsCommentExt(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  72 */     this.id = id;
/*  73 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getIp()
/*     */   {
/*  83 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/*  91 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  99 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 107 */     this.text = text;
/*     */   }
/*     */ 
/*     */   public String getReply()
/*     */   {
/* 115 */     return this.reply;
/*     */   }
/*     */ 
/*     */   public void setReply(String reply)
/*     */   {
/* 123 */     this.reply = reply;
/*     */   }
/*     */ 
/*     */   public CmsComment getComment()
/*     */   {
/* 131 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(CmsComment comment)
/*     */   {
/* 139 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 145 */     if (obj == null) return false;
/* 146 */     if (!(obj instanceof CmsCommentExt)) return false;
/*     */ 
/* 148 */     CmsCommentExt cmsCommentExt = (CmsCommentExt)obj;
/* 149 */     if ((getId() == null) || (cmsCommentExt.getId() == null)) return false;
/* 150 */     return getId().equals(cmsCommentExt.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 155 */     if (-2147483648 == this.hashCode) {
/* 156 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 158 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 159 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 162 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 167 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsCommentExt
 * JD-Core Version:    0.6.0
 */