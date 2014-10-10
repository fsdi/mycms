/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteReply;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsVoteReply
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsVoteReply";
/*  18 */   public static String PROP_SUB_TOPIC = "subTopic";
/*  19 */   public static String PROP_ID = "id";
/*  20 */   public static String PROP_REPLY = "reply";
/*     */ 
/*  40 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String reply;
/*     */   private CmsVoteSubTopic subTopic;
/*     */ 
/*     */   public BaseCmsVoteReply()
/*     */   {
/*  25 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteReply(Integer id)
/*     */   {
/*  32 */     setId(id);
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  60 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  68 */     this.id = id;
/*  69 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getReply()
/*     */   {
/*  79 */     return this.reply;
/*     */   }
/*     */ 
/*     */   public void setReply(String reply)
/*     */   {
/*  87 */     this.reply = reply;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic getSubTopic()
/*     */   {
/*  95 */     return this.subTopic;
/*     */   }
/*     */ 
/*     */   public void setSubTopic(CmsVoteSubTopic subTopic)
/*     */   {
/* 103 */     this.subTopic = subTopic;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 109 */     if (obj == null) return false;
/* 110 */     if (!(obj instanceof CmsVoteReply)) return false;
/*     */ 
/* 112 */     CmsVoteReply cmsVoteReply = (CmsVoteReply)obj;
/* 113 */     if ((getId() == null) || (cmsVoteReply.getId() == null)) return false;
/* 114 */     return getId().equals(cmsVoteReply.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 119 */     if (-2147483648 == this.hashCode) {
/* 120 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 122 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 123 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 126 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsVoteReply
 * JD-Core Version:    0.6.0
 */