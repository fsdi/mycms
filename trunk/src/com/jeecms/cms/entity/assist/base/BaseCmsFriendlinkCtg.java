/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsFriendlinkCtg
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsFriendlinkCtg";
/*  18 */   public static String PROP_SITE = "site";
/*  19 */   public static String PROP_PRIORITY = "priority";
/*  20 */   public static String PROP_NAME = "name";
/*  21 */   public static String PROP_ID = "id";
/*     */ 
/*  57 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsFriendlinkCtg()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFriendlinkCtg(Integer id)
/*     */   {
/*  33 */     setId(id);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFriendlinkCtg(Integer id, CmsSite site, String name, Integer priority)
/*     */   {
/*  46 */     setId(id);
/*  47 */     setSite(site);
/*  48 */     setName(name);
/*  49 */     setPriority(priority);
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
/*     */   public String getName()
/*     */   {
/*  97 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 105 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 113 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 121 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 129 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 137 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 143 */     if (obj == null) return false;
/* 144 */     if (!(obj instanceof CmsFriendlinkCtg)) return false;
/*     */ 
/* 146 */     CmsFriendlinkCtg cmsFriendlinkCtg = (CmsFriendlinkCtg)obj;
/* 147 */     if ((getId() == null) || (cmsFriendlinkCtg.getId() == null)) return false;
/* 148 */     return getId().equals(cmsFriendlinkCtg.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsFriendlinkCtg
 * JD-Core Version:    0.6.0
 */