/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteReply;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsVoteSubTopic
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsVoteSubTopic";
/*  18 */   public static String PROP_TYPE = "type";
/*  19 */   public static String PROP_VOTE_TOPIC = "voteTopic";
/*  20 */   public static String PROP_ID = "id";
/*  21 */   public static String PROP_TITLE = "title";
/*  22 */   public static String PROP_PRIORITY = "priority";
/*     */ 
/*  60 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private Integer type;
/*     */   private Integer priority;
/*     */   private CmsVoteTopic voteTopic;
/*     */   private Set<CmsVoteItem> voteItems;
/*     */   private Set<CmsVoteReply> voteReplys;
/*     */ 
/*     */   public BaseCmsVoteSubTopic()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteSubTopic(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteSubTopic(Integer id, CmsVoteTopic voteTopic, String title, Integer type, Integer priority)
/*     */   {
/*  48 */     setId(id);
/*  49 */     setVoteTopic(voteTopic);
/*  50 */     setTitle(title);
/*  51 */     setType(type);
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
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  94 */     this.id = id;
/*  95 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 105 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 113 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Integer getType()
/*     */   {
/* 121 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(Integer type)
/*     */   {
/* 129 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 137 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 145 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic getVoteTopic()
/*     */   {
/* 153 */     return this.voteTopic;
/*     */   }
/*     */ 
/*     */   public void setVoteTopic(CmsVoteTopic voteTopic)
/*     */   {
/* 161 */     this.voteTopic = voteTopic;
/*     */   }
/*     */ 
/*     */   public Set<CmsVoteItem> getVoteItems()
/*     */   {
/* 169 */     return this.voteItems;
/*     */   }
/*     */ 
/*     */   public void setVoteItems(Set<CmsVoteItem> voteItems)
/*     */   {
/* 177 */     this.voteItems = voteItems;
/*     */   }
/*     */ 
/*     */   public Set<CmsVoteReply> getVoteReplys()
/*     */   {
/* 185 */     return this.voteReplys;
/*     */   }
/*     */ 
/*     */   public void setVoteReplys(Set<CmsVoteReply> voteReplys)
/*     */   {
/* 193 */     this.voteReplys = voteReplys;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 199 */     if (obj == null) return false;
/* 200 */     if (!(obj instanceof CmsVoteSubTopic)) return false;
/*     */ 
/* 202 */     CmsVoteSubTopic cmsVoteSubTopic = (CmsVoteSubTopic)obj;
/* 203 */     if ((getId() == null) || (cmsVoteSubTopic.getId() == null)) return false;
/* 204 */     return getId().equals(cmsVoteSubTopic.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsVoteSubTopic
 * JD-Core Version:    0.6.0
 */