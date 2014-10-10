/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsRole;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsRole
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsRole";
/*  18 */   public static String PROP_SITE = "site";
/*  19 */   public static String PROP_SUPER = "super";
/*  20 */   public static String PROP_PRIORITY = "priority";
/*  21 */   public static String PROP_NAME = "name";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  58 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private Boolean m_super;
/*     */   private CmsSite site;
/*     */   private Set<String> perms;
/*     */ 
/*     */   public BaseCmsRole()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsRole(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsRole(Integer id, String name, Integer priority, Boolean m_super)
/*     */   {
/*  47 */     setId(id);
/*  48 */     setName(name);
/*  49 */     setPriority(priority);
/*  50 */     setSuper(m_super);
/*  51 */     initialize();
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
/*     */   public String getName()
/*     */   {
/* 102 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 110 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 118 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 126 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Boolean getSuper()
/*     */   {
/* 134 */     return this.m_super;
/*     */   }
/*     */ 
/*     */   public void setSuper(Boolean m_super)
/*     */   {
/* 142 */     this.m_super = m_super;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 150 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 158 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Set<String> getPerms()
/*     */   {
/* 166 */     return this.perms;
/*     */   }
/*     */ 
/*     */   public void setPerms(Set<String> perms)
/*     */   {
/* 174 */     this.perms = perms;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 180 */     if (obj == null) return false;
/* 181 */     if (!(obj instanceof CmsRole)) return false;
/*     */ 
/* 183 */     CmsRole cmsRole = (CmsRole)obj;
/* 184 */     if ((getId() == null) || (cmsRole.getId() == null)) return false;
/* 185 */     return getId().equals(cmsRole.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 190 */     if (-2147483648 == this.hashCode) {
/* 191 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 193 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 194 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 197 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 202 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsRole
 * JD-Core Version:    0.6.0
 */