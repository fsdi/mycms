/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteRecord;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsVoteRecord
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsVoteRecord";
/*  18 */   public static String PROP_TIME = "time";
/*  19 */   public static String PROP_COOKIE = "cookie";
/*  20 */   public static String PROP_TOPIC = "topic";
/*  21 */   public static String PROP_USER = "user";
/*  22 */   public static String PROP_IP = "ip";
/*  23 */   public static String PROP_ID = "id";
/*     */ 
/*  61 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Date time;
/*     */   private String ip;
/*     */   private String cookie;
/*     */   private CmsUser user;
/*     */   private CmsVoteTopic topic;
/*     */ 
/*     */   public BaseCmsVoteRecord()
/*     */   {
/*  28 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteRecord(Integer id)
/*     */   {
/*  35 */     setId(id);
/*  36 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteRecord(Integer id, CmsVoteTopic topic, Date time, String ip, String cookie)
/*     */   {
/*  49 */     setId(id);
/*  50 */     setTopic(topic);
/*  51 */     setTime(time);
/*  52 */     setIp(ip);
/*  53 */     setCookie(cookie);
/*  54 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  84 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  92 */     this.id = id;
/*  93 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Date getTime()
/*     */   {
/* 103 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time)
/*     */   {
/* 111 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getIp()
/*     */   {
/* 119 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 127 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public String getCookie()
/*     */   {
/* 135 */     return this.cookie;
/*     */   }
/*     */ 
/*     */   public void setCookie(String cookie)
/*     */   {
/* 143 */     this.cookie = cookie;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 151 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 159 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic getTopic()
/*     */   {
/* 167 */     return this.topic;
/*     */   }
/*     */ 
/*     */   public void setTopic(CmsVoteTopic topic)
/*     */   {
/* 175 */     this.topic = topic;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 181 */     if (obj == null) return false;
/* 182 */     if (!(obj instanceof CmsVoteRecord)) return false;
/*     */ 
/* 184 */     CmsVoteRecord cmsVoteRecord = (CmsVoteRecord)obj;
/* 185 */     if ((getId() == null) || (cmsVoteRecord.getId() == null)) return false;
/* 186 */     return getId().equals(cmsVoteRecord.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 191 */     if (-2147483648 == this.hashCode) {
/* 192 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 194 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 195 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 198 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 203 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsVoteRecord
 * JD-Core Version:    0.6.0
 */