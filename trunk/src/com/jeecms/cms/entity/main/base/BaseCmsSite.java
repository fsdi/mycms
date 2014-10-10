/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class BaseCmsSite
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsSite";
/*  18 */   public static String PROP_INDEX_TO_ROOT = "indexToRoot";
/*  19 */   public static String PROP_DOMAIN = "domain";
/*  20 */   public static String PROP_PROTOCOL = "protocol";
/*  21 */   public static String PROP_LOCALE_ADMIN = "localeAdmin";
/*  22 */   public static String PROP_DOMAIN_REDIRECT = "domainRedirect";
/*  23 */   public static String PROP_UPLOAD_FTP = "uploadFtp";
/*  24 */   public static String PROP_RESYCLE_ON = "resycleOn";
/*  25 */   public static String PROP_TPL_SOLUTION = "tplSolution";
/*  26 */   public static String PROP_STATIC_SUFFIX = "staticSuffix";
/*  27 */   public static String PROP_CONFIG = "config";
/*  28 */   public static String PROP_STATIC_INDEX = "staticIndex";
/*  29 */   public static String PROP_DYNAMIC_SUFFIX = "dynamicSuffix";
/*  30 */   public static String PROP_FINAL_STEP = "finalStep";
/*  31 */   public static String PROP_SHORT_NAME = "shortName";
/*  32 */   public static String PROP_STATIC_DIR = "staticDir";
/*  33 */   public static String PROP_DOMAIN_ALIAS = "domainAlias";
/*  34 */   public static String PROP_PATH = "path";
/*  35 */   public static String PROP_AFTER_CHECK = "afterCheck";
/*  36 */   public static String PROP_LOCALE_FRONT = "localeFront";
/*  37 */   public static String PROP_NAME = "name";
/*  38 */   public static String PROP_ID = "id";
/*  39 */   public static String PROP_RELATIVE_PATH = "relativePath";
/*     */ 
/* 101 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String domain;
/*     */   private String path;
/*     */   private String name;
/*     */   private String shortName;
/*     */   private String protocol;
/*     */   private String dynamicSuffix;
/*     */   private String staticSuffix;
/*     */   private String staticDir;
/*     */   private Boolean indexToRoot;
/*     */   private Boolean staticIndex;
/*     */   private String localeAdmin;
/*     */   private String localeFront;
/*     */   private String tplSolution;
/*     */   private Byte finalStep;
/*     */   private Byte afterCheck;
/*     */   private Boolean relativePath;
/*     */   private Boolean resycleOn;
/*     */   private String domainAlias;
/*     */   private String domainRedirect;
/*     */   private CmsSiteCompany siteCompany;
/*     */   private Ftp uploadFtp;
/*     */   private CmsConfig config;
/*     */   private Map<String, String> attr;
/*     */   private Map<String, String> txt;
/*     */   private Map<String, String> cfg;
/*     */ 
/*     */   public BaseCmsSite()
/*     */   {
/*  44 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSite(Integer id)
/*     */   {
/*  51 */     setId(id);
/*  52 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSite(Integer id, CmsConfig config, String domain, String path, String name, String protocol, String dynamicSuffix, String staticSuffix, Boolean indexToRoot, Boolean staticIndex, String localeAdmin, String localeFront, String tplSolution, Byte finalStep, Byte afterCheck, Boolean relativePath, Boolean resycleOn)
/*     */   {
/*  77 */     setId(id);
/*  78 */     setConfig(config);
/*  79 */     setDomain(domain);
/*  80 */     setPath(path);
/*  81 */     setName(name);
/*  82 */     setProtocol(protocol);
/*  83 */     setDynamicSuffix(dynamicSuffix);
/*  84 */     setStaticSuffix(staticSuffix);
/*  85 */     setIndexToRoot(indexToRoot);
/*  86 */     setStaticIndex(staticIndex);
/*  87 */     setLocaleAdmin(localeAdmin);
/*  88 */     setLocaleFront(localeFront);
/*  89 */     setTplSolution(tplSolution);
/*  90 */     setFinalStep(finalStep);
/*  91 */     setAfterCheck(afterCheck);
/*  92 */     setRelativePath(relativePath);
/*  93 */     setResycleOn(resycleOn);
/*  94 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 147 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 155 */     this.id = id;
/* 156 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getDomain()
/*     */   {
/* 166 */     return this.domain;
/*     */   }
/*     */ 
/*     */   public void setDomain(String domain)
/*     */   {
/* 174 */     this.domain = domain;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 182 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path)
/*     */   {
/* 190 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 198 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 206 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getShortName()
/*     */   {
/* 214 */     return this.shortName;
/*     */   }
/*     */ 
/*     */   public void setShortName(String shortName)
/*     */   {
/* 222 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */   public String getProtocol()
/*     */   {
/* 230 */     return this.protocol;
/*     */   }
/*     */ 
/*     */   public void setProtocol(String protocol)
/*     */   {
/* 238 */     this.protocol = protocol;
/*     */   }
/*     */ 
/*     */   public String getDynamicSuffix()
/*     */   {
/* 246 */     return this.dynamicSuffix;
/*     */   }
/*     */ 
/*     */   public void setDynamicSuffix(String dynamicSuffix)
/*     */   {
/* 254 */     this.dynamicSuffix = dynamicSuffix;
/*     */   }
/*     */ 
/*     */   public String getStaticSuffix()
/*     */   {
/* 262 */     return this.staticSuffix;
/*     */   }
/*     */ 
/*     */   public void setStaticSuffix(String staticSuffix)
/*     */   {
/* 270 */     this.staticSuffix = staticSuffix;
/*     */   }
/*     */ 
/*     */   public String getStaticDir()
/*     */   {
/* 278 */     return this.staticDir;
/*     */   }
/*     */ 
/*     */   public void setStaticDir(String staticDir)
/*     */   {
/* 286 */     this.staticDir = staticDir;
/*     */   }
/*     */ 
/*     */   public Boolean getIndexToRoot()
/*     */   {
/* 294 */     return this.indexToRoot;
/*     */   }
/*     */ 
/*     */   public void setIndexToRoot(Boolean indexToRoot)
/*     */   {
/* 302 */     this.indexToRoot = indexToRoot;
/*     */   }
/*     */ 
/*     */   public Boolean getStaticIndex()
/*     */   {
/* 310 */     return this.staticIndex;
/*     */   }
/*     */ 
/*     */   public void setStaticIndex(Boolean staticIndex)
/*     */   {
/* 318 */     this.staticIndex = staticIndex;
/*     */   }
/*     */ 
/*     */   public String getLocaleAdmin()
/*     */   {
/* 326 */     return this.localeAdmin;
/*     */   }
/*     */ 
/*     */   public void setLocaleAdmin(String localeAdmin)
/*     */   {
/* 334 */     this.localeAdmin = localeAdmin;
/*     */   }
/*     */ 
/*     */   public String getLocaleFront()
/*     */   {
/* 342 */     return this.localeFront;
/*     */   }
/*     */ 
/*     */   public void setLocaleFront(String localeFront)
/*     */   {
/* 350 */     this.localeFront = localeFront;
/*     */   }
/*     */ 
/*     */   public String getTplSolution()
/*     */   {
/* 358 */     return this.tplSolution;
/*     */   }
/*     */ 
/*     */   public void setTplSolution(String tplSolution)
/*     */   {
/* 366 */     this.tplSolution = tplSolution;
/*     */   }
/*     */ 
/*     */   public Byte getFinalStep()
/*     */   {
/* 374 */     return this.finalStep;
/*     */   }
/*     */ 
/*     */   public void setFinalStep(Byte finalStep)
/*     */   {
/* 382 */     this.finalStep = finalStep;
/*     */   }
/*     */ 
/*     */   public Byte getAfterCheck()
/*     */   {
/* 390 */     return this.afterCheck;
/*     */   }
/*     */ 
/*     */   public void setAfterCheck(Byte afterCheck)
/*     */   {
/* 398 */     this.afterCheck = afterCheck;
/*     */   }
/*     */ 
/*     */   public Boolean getRelativePath()
/*     */   {
/* 406 */     return this.relativePath;
/*     */   }
/*     */ 
/*     */   public void setRelativePath(Boolean relativePath)
/*     */   {
/* 414 */     this.relativePath = relativePath;
/*     */   }
/*     */ 
/*     */   public Boolean getResycleOn()
/*     */   {
/* 422 */     return this.resycleOn;
/*     */   }
/*     */ 
/*     */   public void setResycleOn(Boolean resycleOn)
/*     */   {
/* 430 */     this.resycleOn = resycleOn;
/*     */   }
/*     */ 
/*     */   public String getDomainAlias()
/*     */   {
/* 438 */     return this.domainAlias;
/*     */   }
/*     */ 
/*     */   public void setDomainAlias(String domainAlias)
/*     */   {
/* 446 */     this.domainAlias = domainAlias;
/*     */   }
/*     */ 
/*     */   public String getDomainRedirect()
/*     */   {
/* 454 */     return this.domainRedirect;
/*     */   }
/*     */ 
/*     */   public void setDomainRedirect(String domainRedirect)
/*     */   {
/* 462 */     this.domainRedirect = domainRedirect;
/*     */   }
/*     */ 
/*     */   public CmsSiteCompany getSiteCompany() {
/* 466 */     return this.siteCompany;
/*     */   }
/*     */ 
/*     */   public void setSiteCompany(CmsSiteCompany siteCompany) {
/* 470 */     this.siteCompany = siteCompany;
/*     */   }
/*     */ 
/*     */   public Ftp getUploadFtp()
/*     */   {
/* 477 */     return this.uploadFtp;
/*     */   }
/*     */ 
/*     */   public void setUploadFtp(Ftp uploadFtp)
/*     */   {
/* 485 */     this.uploadFtp = uploadFtp;
/*     */   }
/*     */ 
/*     */   public CmsConfig getConfig()
/*     */   {
/* 493 */     return this.config;
/*     */   }
/*     */ 
/*     */   public void setConfig(CmsConfig config)
/*     */   {
/* 501 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 509 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 517 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTxt()
/*     */   {
/* 525 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(Map<String, String> txt)
/*     */   {
/* 533 */     this.txt = txt;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getCfg()
/*     */   {
/* 541 */     return this.cfg;
/*     */   }
/*     */ 
/*     */   public void setCfg(Map<String, String> cfg)
/*     */   {
/* 549 */     this.cfg = cfg;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 555 */     if (obj == null) return false;
/* 556 */     if (!(obj instanceof CmsSite)) return false;
/*     */ 
/* 558 */     CmsSite cmsSite = (CmsSite)obj;
/* 559 */     if ((getId() == null) || (cmsSite.getId() == null)) return false;
/* 560 */     return getId().equals(cmsSite.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 565 */     if (-2147483648 == this.hashCode) {
/* 566 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 568 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 569 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 572 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 577 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsSite
 * JD-Core Version:    0.6.0
 */