/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsSiteCompany
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsSiteCompany";
/*  18 */   public static String PROP_NAME = "name";
/*  19 */   public static String PROP_DESCRIPTION = "description";
/*  20 */   public static String PROP_SITE = "site";
/*  21 */   public static String PROP_ADDRESS = "address";
/*  22 */   public static String PROP_CONTACT = "contact";
/*  23 */   public static String PROP_LATITUDE = "latitude";
/*  24 */   public static String PROP_ID = "id";
/*  25 */   public static String PROP_LONGITUDE = "longitude";
/*  26 */   public static String PROP_SCALE = "scale";
/*  27 */   public static String PROP_NATURE = "nature";
/*  28 */   public static String PROP_INDUSTRY = "industry";
/*     */ 
/*  60 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String scale;
/*     */   private String nature;
/*     */   private String industry;
/*     */   private String contact;
/*     */   private String description;
/*     */   private String address;
/*     */   private Float longitude;
/*     */   private Float latitude;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsSiteCompany()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteCompany(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteCompany(Integer id, String name)
/*     */   {
/*  51 */     setId(id);
/*  52 */     setName(name);
/*  53 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  88 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  96 */     this.id = id;
/*  97 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 107 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 115 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getScale()
/*     */   {
/* 123 */     return this.scale;
/*     */   }
/*     */ 
/*     */   public void setScale(String scale)
/*     */   {
/* 131 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */   public String getNature()
/*     */   {
/* 139 */     return this.nature;
/*     */   }
/*     */ 
/*     */   public void setNature(String nature)
/*     */   {
/* 147 */     this.nature = nature;
/*     */   }
/*     */ 
/*     */   public String getIndustry()
/*     */   {
/* 155 */     return this.industry;
/*     */   }
/*     */ 
/*     */   public void setIndustry(String industry)
/*     */   {
/* 163 */     this.industry = industry;
/*     */   }
/*     */ 
/*     */   public String getContact()
/*     */   {
/* 171 */     return this.contact;
/*     */   }
/*     */ 
/*     */   public void setContact(String contact)
/*     */   {
/* 179 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 187 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 195 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/* 203 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 211 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public Float getLongitude()
/*     */   {
/* 219 */     return this.longitude;
/*     */   }
/*     */ 
/*     */   public void setLongitude(Float longitude)
/*     */   {
/* 227 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */   public Float getLatitude()
/*     */   {
/* 235 */     return this.latitude;
/*     */   }
/*     */ 
/*     */   public void setLatitude(Float latitude)
/*     */   {
/* 243 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 251 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 259 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 265 */     if (obj == null) return false;
/* 266 */     if (!(obj instanceof CmsSiteCompany)) return false;
/*     */ 
/* 268 */     CmsSiteCompany cmsSiteCompany = (CmsSiteCompany)obj;
/* 269 */     if ((getId() == null) || (cmsSiteCompany.getId() == null)) return false;
/* 270 */     return getId().equals(cmsSiteCompany.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 275 */     if (-2147483648 == this.hashCode) {
/* 276 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 278 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 279 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 282 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 287 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsSiteCompany
 * JD-Core Version:    0.6.0
 */