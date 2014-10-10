/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsGuestbook
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsGuestbook";
/*  18 */   public static String PROP_RECOMMEND = "recommend";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_REPLAY_TIME = "replayTime";
/*  21 */   public static String PROP_CREATE_TIME = "createTime";
/*  22 */   public static String PROP_IP = "ip";
/*  23 */   public static String PROP_CHECKED = "checked";
/*  24 */   public static String PROP_EXT = "ext";
/*  25 */   public static String PROP_CTG = "ctg";
/*  26 */   public static String PROP_ADMIN = "admin";
/*  27 */   public static String PROP_MEMBER = "member";
/*  28 */   public static String PROP_ID = "id";
/*     */ 
/*  70 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String ip;
/*     */   private Date createTime;
/*     */   private Date replayTime;
/*     */   private Boolean checked;
/*     */   private Boolean recommend;
/*     */   private CmsGuestbookExt ext;
/*     */   private CmsUser member;
/*     */   private CmsUser admin;
/*     */   private CmsSite site;
/*     */   private CmsGuestbookCtg ctg;
/*     */ 
/*     */   public BaseCmsGuestbook()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGuestbook(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGuestbook(Integer id, CmsSite site, CmsGuestbookCtg ctg, String ip, Date createTime, Boolean checked, Boolean recommend)
/*     */   {
/*  56 */     setId(id);
/*  57 */     setSite(site);
/*  58 */     setCtg(ctg);
/*  59 */     setIp(ip);
/*  60 */     setCreateTime(createTime);
/*  61 */     setChecked(checked);
/*  62 */     setRecommend(recommend);
/*  63 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 100 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 108 */     this.id = id;
/* 109 */     this.hashCode = -2147483648;
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
/*     */   public Date getCreateTime()
/*     */   {
/* 135 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 143 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Date getReplayTime()
/*     */   {
/* 151 */     return this.replayTime;
/*     */   }
/*     */ 
/*     */   public void setReplayTime(Date replayTime)
/*     */   {
/* 159 */     this.replayTime = replayTime;
/*     */   }
/*     */ 
/*     */   public Boolean getChecked()
/*     */   {
/* 167 */     return this.checked;
/*     */   }
/*     */ 
/*     */   public void setChecked(Boolean checked)
/*     */   {
/* 175 */     this.checked = checked;
/*     */   }
/*     */ 
/*     */   public Boolean getRecommend()
/*     */   {
/* 183 */     return this.recommend;
/*     */   }
/*     */ 
/*     */   public void setRecommend(Boolean recommend)
/*     */   {
/* 191 */     this.recommend = recommend;
/*     */   }
/*     */ 
/*     */   public CmsGuestbookExt getExt()
/*     */   {
/* 199 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(CmsGuestbookExt ext)
/*     */   {
/* 207 */     this.ext = ext;
/*     */   }
/*     */ 
/*     */   public CmsUser getMember()
/*     */   {
/* 215 */     return this.member;
/*     */   }
/*     */ 
/*     */   public void setMember(CmsUser member)
/*     */   {
/* 223 */     this.member = member;
/*     */   }
/*     */ 
/*     */   public CmsUser getAdmin()
/*     */   {
/* 231 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(CmsUser admin)
/*     */   {
/* 239 */     this.admin = admin;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 247 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 255 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public CmsGuestbookCtg getCtg()
/*     */   {
/* 263 */     return this.ctg;
/*     */   }
/*     */ 
/*     */   public void setCtg(CmsGuestbookCtg ctg)
/*     */   {
/* 271 */     this.ctg = ctg;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 277 */     if (obj == null) return false;
/* 278 */     if (!(obj instanceof CmsGuestbook)) return false;
/*     */ 
/* 280 */     CmsGuestbook cmsGuestbook = (CmsGuestbook)obj;
/* 281 */     if ((getId() == null) || (cmsGuestbook.getId() == null)) return false;
/* 282 */     return getId().equals(cmsGuestbook.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 287 */     if (-2147483648 == this.hashCode) {
/* 288 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 290 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 291 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 294 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 299 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsGuestbook
 * JD-Core Version:    0.6.0
 */