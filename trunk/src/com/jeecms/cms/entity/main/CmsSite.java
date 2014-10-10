/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseCmsSite;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class CmsSite extends BaseCmsSite
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getUrl()
/*     */   {
/*  24 */     if (getStaticIndex().booleanValue()) {
/*  25 */       return getUrlStatic();
/*     */     }
/*  27 */     return getUrlDynamic();
/*     */   }
/*     */ 
/*     */   public String getUrlWhole()
/*     */   {
/*  37 */     if (getStaticIndex().booleanValue()) {
/*  38 */       return "/";
/*     */     }
/*  40 */     return "/";
/*     */   }
/*     */ 
/*     */   public String getUrlDynamic()
/*     */   {
/*  45 */     return "/";
/*     */   }
/*     */ 
/*     */   public String getUrlStatic() {
/*  49 */     return "/";
/*     */   }
/*     */ 
/*     */   public StringBuilder getUrlBuffer(boolean dynamic, Boolean whole, boolean forIndex)
/*     */   {
///*  54 */     boolean relative = whole != null ? true : whole.booleanValue() ? false : getRelativePath().booleanValue();
			  boolean relative = whole != null ? true : getRelativePath().booleanValue();
	
/*  55 */     String ctx = getContextPath();
/*  56 */     StringBuilder url = new StringBuilder();
/*  57 */     if (!relative) {
/*  58 */       url.append(getProtocol()).append(getDomain());
/*  59 */       if (getPort() != null) {
/*  60 */         url.append(":").append(getPort());
/*     */       }
/*     */     }
/*  63 */     if (!StringUtils.isBlank(ctx)) {
/*  64 */       url.append(ctx);
/*     */     }
/*  66 */     if (dynamic) {
/*  67 */       String servlet = getServletPoint();
/*  68 */       if (!StringUtils.isBlank(servlet)) {
/*  69 */         url.append(servlet);
/*     */       }
/*     */     }
/*  72 */     else if (!forIndex) {
/*  73 */       String staticDir = getStaticDir();
/*  74 */       if (!StringUtils.isBlank(staticDir)) {
/*  75 */         url.append(staticDir);
/*     */       }
/*     */     }
/*     */ 
/*  79 */     return url;
/*     */   }
/*     */ 
/*     */   public String getTplPath()
/*     */   {
/*  88 */     return "/WEB-INF/t/cms/" + getPath();
/*     */   }
/*     */ 
/*     */   public String getSolutionPath()
/*     */   {
/*  97 */     return "/WEB-INF/t/cms/" + getPath() + "/" + getTplSolution();
/*     */   }
/*     */ 
/*     */   public String getResPath()
/*     */   {
/* 106 */     return "/r/cms/" + getPath();
/*     */   }
/*     */ 
/*     */   public String getUploadPath()
/*     */   {
/* 115 */     return "/u/cms/" + getPath();
/*     */   }
/*     */ 
/*     */   public String getLibraryPath() {
/* 119 */     return "/wenku/" + getPath();
/*     */   }
/*     */ 
/*     */   public String getUploadBase()
/*     */   {
/* 130 */     CmsConfig config = getConfig();
/* 131 */     String ctx = config.getContextPath();
/* 132 */     if (config.getUploadToDb().booleanValue()) {
/* 133 */       if (!StringUtils.isBlank(ctx)) {
/* 134 */         return ctx + config.getDbFileUri();
/*     */       }
/* 136 */       return config.getDbFileUri();
/*     */     }
/* 138 */     if (getUploadFtp() != null) {
/* 139 */       return getUploadFtp().getUrl();
/*     */     }
/* 141 */     if (!StringUtils.isBlank(ctx)) {
/* 142 */       return ctx;
/*     */     }
/* 144 */     return "";
/*     */   }
/*     */ 
/*     */   public String getServletPoint()
/*     */   {
/* 150 */     CmsConfig config = getConfig();
/* 151 */     if (config != null) {
/* 152 */       return config.getServletPoint();
/*     */     }
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContextPath()
/*     */   {
/* 159 */     CmsConfig config = getConfig();
/* 160 */     if (config != null) {
/* 161 */       return config.getContextPath();
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getPort()
/*     */   {
/* 168 */     CmsConfig config = getConfig();
/* 169 */     if (config != null) {
/* 170 */       return config.getPort();
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   public String getDefImg()
/*     */   {
/* 177 */     CmsConfig config = getConfig();
/* 178 */     if (config != null) {
/* 179 */       return config.getDefImg();
/*     */     }
/* 181 */     return null;
/*     */   }
/*     */ 
/*     */   public String getLoginUrl()
/*     */   {
/* 186 */     CmsConfig config = getConfig();
/* 187 */     if (config != null) {
/* 188 */       return config.getLoginUrl();
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   public String getProcessUrl()
/*     */   {
/* 195 */     CmsConfig config = getConfig();
/* 196 */     if (config != null) {
/* 197 */       return config.getProcessUrl();
/*     */     }
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */   public int getUsernameMinLen()
/*     */   {
/* 204 */     return getConfig().getMemberConfig().getUsernameMinLen();
/*     */   }
/*     */ 
/*     */   public int getPasswordMinLen() {
/* 208 */     return getConfig().getMemberConfig().getPasswordMinLen();
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<CmsSite> sites) {
/* 212 */     if (sites == null) {
/* 213 */       return null;
/*     */     }
/* 215 */     Integer[] ids = new Integer[sites.size()];
/* 216 */     int i = 0;
/* 217 */     for (CmsSite s : sites) {
/* 218 */       ids[(i++)] = s.getId();
/*     */     }
/* 220 */     return ids;
/*     */   }
/*     */ 
/*     */   public void init() {
/* 224 */     if (StringUtils.isBlank(getProtocol())) {
/* 225 */       setProtocol("http://");
/*     */     }
/* 227 */     if (StringUtils.isBlank(getTplSolution())) {
/* 228 */       setTplSolution("default");
/*     */     }
/* 230 */     if (getFinalStep() == null) {
/* 231 */       byte step = 2;
/* 232 */       setFinalStep(Byte.valueOf(step));
/*     */     }
/*     */   }
/*     */ 
/*     */   public CmsSite()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsSite(Integer id)
/*     */   {
/* 245 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsSite(Integer id, CmsConfig config, String domain, String path, String name, String protocol, String dynamicSuffix, String staticSuffix, Boolean indexToRoot, Boolean staticIndex, String localeAdmin, String localeFront, String tplSolution, Byte finalStep, Byte afterCheck, Boolean relativePath, Boolean resycleOn)
/*     */   {
/* 287 */     super(id, 
/* 272 */       config, 
/* 273 */       domain, 
/* 274 */       path, 
/* 275 */       name, 
/* 276 */       protocol, 
/* 277 */       dynamicSuffix, 
/* 278 */       staticSuffix, 
/* 279 */       indexToRoot, 
/* 280 */       staticIndex, 
/* 281 */       localeAdmin, 
/* 282 */       localeFront, 
/* 283 */       tplSolution, 
/* 284 */       finalStep, 
/* 285 */       afterCheck, 
/* 286 */       relativePath, 
/* 287 */       resycleOn);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsSite
 * JD-Core Version:    0.6.0
 */