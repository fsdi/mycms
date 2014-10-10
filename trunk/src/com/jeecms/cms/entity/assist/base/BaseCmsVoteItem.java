/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsVoteItem
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsVoteItem";
/*  18 */   public static String PROP_TOPIC = "topic";
/*  19 */   public static String PROP_PRIORITY = "priority";
/*  20 */   public static String PROP_TITLE = "title";
/*  21 */   public static String PROP_VOTE_COUNT = "voteCount";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  60 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private Integer voteCount;
/*     */   private Integer priority;
/*     */   private CmsVoteTopic topic;
/*     */   private CmsVoteSubTopic subTopic;
/*     */ 
/*     */   public BaseCmsVoteItem()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteItem(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteItem(Integer id, CmsVoteTopic topic, String title, Integer voteCount, Integer priority)
/*     */   {
/*  48 */     setId(id);
/*  49 */     setTopic(topic);
/*  50 */     setTitle(title);
/*  51 */     setVoteCount(voteCount);
/*  52 */     setPriority(priority);
/*  53 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  83 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  91 */     this.id = id;
/*  92 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 102 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 110 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Integer getVoteCount()
/*     */   {
/* 118 */     return this.voteCount;
/*     */   }
/*     */ 
/*     */   public void setVoteCount(Integer voteCount)
/*     */   {
/* 126 */     this.voteCount = voteCount;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 134 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 142 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic getTopic()
/*     */   {
/* 150 */     return this.topic;
/*     */   }
/*     */ 
/*     */   public void setTopic(CmsVoteTopic topic)
/*     */   {
/* 158 */     this.topic = topic;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic getSubTopic()
/*     */   {
/* 163 */     return this.subTopic;
/*     */   }
/*     */ 
/*     */   public void setSubTopic(CmsVoteSubTopic subTopic) {
/* 167 */     this.subTopic = subTopic;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj) {
/* 171 */     if (obj == null) return false;
/* 172 */     if (!(obj instanceof CmsVoteItem)) return false;
/*     */ 
/* 174 */     CmsVoteItem cmsVoteItem = (CmsVoteItem)obj;
/* 175 */     if ((getId() == null) || (cmsVoteItem.getId() == null)) return false;
/* 176 */     return getId().equals(cmsVoteItem.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 181 */     if (-2147483648 == this.hashCode) {
/* 182 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 184 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 185 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 188 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsVoteItem
 * JD-Core Version:    0.6.0
 */