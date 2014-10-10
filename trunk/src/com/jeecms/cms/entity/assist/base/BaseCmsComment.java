/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsComment
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsComment";
/*  18 */   public static String PROP_RECOMMEND = "recommend";
/*  19 */   public static String PROP_COMMENT_USER = "commentUser";
/*  20 */   public static String PROP_REPLAY_USER = "replayUser";
/*  21 */   public static String PROP_SITE = "site";
/*  22 */   public static String PROP_REPLAY_TIME = "replayTime";
/*  23 */   public static String PROP_CREATE_TIME = "createTime";
/*  24 */   public static String PROP_DOWNS = "downs";
/*  25 */   public static String PROP_UPS = "ups";
/*  26 */   public static String PROP_CHECKED = "checked";
/*  27 */   public static String PROP_COMMENT_EXT = "commentExt";
/*  28 */   public static String PROP_CONTENT = "content";
/*  29 */   public static String PROP_ID = "id";
/*     */ 
/*  73 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Date createTime;
/*     */   private Date replayTime;
/*     */   private Short ups;
/*     */   private Short downs;
/*     */   private Boolean recommend;
/*     */   private Boolean checked;
/*     */   private CmsCommentExt commentExt;
/*     */   private CmsUser replayUser;
/*     */   private Content content;
/*     */   private CmsUser commentUser;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsComment()
/*     */   {
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsComment(Integer id)
/*     */   {
/*  41 */     setId(id);
/*  42 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsComment(Integer id, Content content, CmsSite site, Date createTime, Short ups, Short downs, Boolean recommend, Boolean checked)
/*     */   {
/*  58 */     setId(id);
/*  59 */     setContent(content);
/*  60 */     setSite(site);
/*  61 */     setCreateTime(createTime);
/*  62 */     setUps(ups);
/*  63 */     setDowns(downs);
/*  64 */     setRecommend(recommend);
/*  65 */     setChecked(checked);
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 104 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 112 */     this.id = id;
/* 113 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 123 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 131 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Date getReplayTime()
/*     */   {
/* 139 */     return this.replayTime;
/*     */   }
/*     */ 
/*     */   public void setReplayTime(Date replayTime)
/*     */   {
/* 147 */     this.replayTime = replayTime;
/*     */   }
/*     */ 
/*     */   public Short getUps()
/*     */   {
/* 155 */     return this.ups;
/*     */   }
/*     */ 
/*     */   public void setUps(Short ups)
/*     */   {
/* 163 */     this.ups = ups;
/*     */   }
/*     */ 
/*     */   public Short getDowns()
/*     */   {
/* 171 */     return this.downs;
/*     */   }
/*     */ 
/*     */   public void setDowns(Short downs)
/*     */   {
/* 179 */     this.downs = downs;
/*     */   }
/*     */ 
/*     */   public Boolean getRecommend()
/*     */   {
/* 187 */     return this.recommend;
/*     */   }
/*     */ 
/*     */   public void setRecommend(Boolean recommend)
/*     */   {
/* 195 */     this.recommend = recommend;
/*     */   }
/*     */ 
/*     */   public Boolean getChecked()
/*     */   {
/* 203 */     return this.checked;
/*     */   }
/*     */ 
/*     */   public void setChecked(Boolean checked)
/*     */   {
/* 211 */     this.checked = checked;
/*     */   }
/*     */ 
/*     */   public CmsCommentExt getCommentExt()
/*     */   {
/* 219 */     return this.commentExt;
/*     */   }
/*     */ 
/*     */   public void setCommentExt(CmsCommentExt commentExt)
/*     */   {
/* 227 */     this.commentExt = commentExt;
/*     */   }
/*     */ 
/*     */   public CmsUser getReplayUser()
/*     */   {
/* 235 */     return this.replayUser;
/*     */   }
/*     */ 
/*     */   public void setReplayUser(CmsUser replayUser)
/*     */   {
/* 243 */     this.replayUser = replayUser;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 251 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 259 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public CmsUser getCommentUser()
/*     */   {
/* 267 */     return this.commentUser;
/*     */   }
/*     */ 
/*     */   public void setCommentUser(CmsUser commentUser)
/*     */   {
/* 275 */     this.commentUser = commentUser;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 283 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 291 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 297 */     if (obj == null) return false;
/* 298 */     if (!(obj instanceof CmsComment)) return false;
/*     */ 
/* 300 */     CmsComment cmsComment = (CmsComment)obj;
/* 301 */     if ((getId() == null) || (cmsComment.getId() == null)) return false;
/* 302 */     return getId().equals(cmsComment.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 307 */     if (-2147483648 == this.hashCode) {
/* 308 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 310 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 311 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 314 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 319 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsComment
 * JD-Core Version:    0.6.0
 */