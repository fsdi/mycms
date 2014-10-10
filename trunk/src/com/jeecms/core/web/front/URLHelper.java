/*     */ package com.jeecms.core.web.front;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.web.util.UrlPathHelper;
/*     */ 
/*     */ public class URLHelper
/*     */ {
/*     */   public static int getPageNo(HttpServletRequest request)
/*     */   {
/*  19 */     return getPageNo(getURI(request));
/*     */   }
/*     */ 
/*     */   public static String[] getPaths(HttpServletRequest request)
/*     */   {
/*  29 */     return getPaths(getURI(request));
/*     */   }
/*     */ 
/*     */   public static String[] getParams(HttpServletRequest request)
/*     */   {
/*  39 */     return getParams(getURI(request));
/*     */   }
/*     */ 
/*     */   private static String getURI(HttpServletRequest request) {
/*  43 */     UrlPathHelper helper = new UrlPathHelper();
/*  44 */     String uri = helper.getOriginatingRequestUri(request);
/*  45 */     String ctx = helper.getOriginatingContextPath(request);
/*  46 */     if (!StringUtils.isBlank(ctx)) {
/*  47 */       return uri.substring(ctx.length());
/*     */     }
/*  49 */     return uri;
/*     */   }
/*     */ 
/*     */   public static PageInfo getPageInfo(HttpServletRequest request)
/*     */   {
/*  60 */     UrlPathHelper helper = new UrlPathHelper();
/*  61 */     String uri = helper.getOriginatingRequestUri(request);
/*  62 */     String queryString = helper.getOriginatingQueryString(request);
/*  63 */     return getPageInfo(uri, queryString);
/*     */   }
/*     */ 
/*     */   public static int getPageNo(String uri)
/*     */   {
/*  74 */     if (uri == null) {
/*  75 */       throw new IllegalArgumentException("URI can not be null");
/*     */     }
/*  77 */     if (!uri.startsWith("/")) {
/*  78 */       throw new IllegalArgumentException("URI must start width '/'");
/*     */     }
/*  80 */     int pageNo = 1;
/*  81 */     int bi = uri.indexOf("_");
/*  82 */     int mi = uri.indexOf("-");
/*  83 */     int pi = uri.indexOf(".");
/*  84 */     if (bi != -1)
/*     */     {
/*     */       String pageNoStr;
/*  86 */       if (mi != -1) {
/*  87 */         pageNoStr = uri.substring(bi + 1, mi);
/*     */       }
/*     */       else
/*     */       {
/*  89 */         if (pi != -1)
/*  90 */           pageNoStr = uri.substring(bi + 1, pi);
/*     */         else
/*  92 */           pageNoStr = uri.substring(bi + 1);
/*     */       }
/*     */       try
/*     */       {
/*  96 */         pageNo = Integer.valueOf(pageNoStr).intValue();
/*     */       } catch (Exception localException) {
/*     */       }
/*     */     }
/* 100 */     return pageNo;
/*     */   }
/*     */ 
/*     */   public static String[] getPaths(String uri)
/*     */   {
/* 111 */     if (uri == null) {
/* 112 */       throw new IllegalArgumentException("URI can not be null");
/*     */     }
/* 114 */     if (!uri.startsWith("/")) {
/* 115 */       throw new IllegalArgumentException("URI must start width '/'");
/*     */     }
/* 117 */     int bi = uri.indexOf("_");
/* 118 */     int mi = uri.indexOf("-");
/* 119 */     int pi = uri.indexOf(".");
/*     */     String pathStr;
/* 122 */     if (bi != -1) {
/* 123 */       pathStr = uri.substring(0, bi);
/*     */     }
/*     */     else
/*     */     {
/* 124 */       if (mi != -1) {
/* 125 */         pathStr = uri.substring(0, mi);
/*     */       }
/*     */       else
/*     */       {
/* 126 */         if (pi != -1)
/* 127 */           pathStr = uri.substring(0, pi);
/*     */         else
/* 129 */           pathStr = uri; 
/*     */       }
/*     */     }
/* 131 */     String[] paths = StringUtils.split(pathStr, '/');
/* 132 */     return paths;
/*     */   }
/*     */ 
/*     */   public static String[] getParams(String uri)
/*     */   {
/* 143 */     if (uri == null) {
/* 144 */       throw new IllegalArgumentException("URI can not be null");
/*     */     }
/* 146 */     if (!uri.startsWith("/")) {
/* 147 */       throw new IllegalArgumentException("URI must start width '/'");
/*     */     }
/* 149 */     int mi = uri.indexOf("-");
/* 150 */     int pi = uri.indexOf(".");
/*     */     String[] params;
/* 152 */     if (mi != -1)
/*     */     {
/*     */       String paramStr;
/* 154 */       if (pi != -1)
/* 155 */         paramStr = uri.substring(mi, pi);
/*     */       else {
/* 157 */         paramStr = uri.substring(mi);
/*     */       }
/* 159 */       params = new String[StringUtils.countMatches(paramStr, "-")];
/* 160 */       int fromIndex = 1;
/* 161 */       int nextIndex = 0;
/* 162 */       int i = 0;
/* 163 */       while ((nextIndex = paramStr.indexOf("-", fromIndex)) != -1) {
/* 164 */         params[(i++)] = paramStr.substring(fromIndex, nextIndex);
/* 165 */         fromIndex = nextIndex + 1;
/*     */       }
/* 167 */       params[(i++)] = paramStr.substring(fromIndex);
/*     */     } else {
/* 169 */       params = new String[0];
/*     */     }
/* 171 */     return params;
/*     */   }
/*     */ 
/*     */   public static PageInfo getPageInfo(String uri, String queryString)
/*     */   {
/* 184 */     if (uri == null) {
/* 185 */       return null;
/*     */     }
/* 187 */     if (!uri.startsWith("/")) {
/* 188 */       throw new IllegalArgumentException("URI must start width '/'");
/*     */     }
/* 190 */     int bi = uri.indexOf("_");
/* 191 */     int mi = uri.indexOf("-");
/* 192 */     int pi = uri.indexOf(".");
/* 193 */     int lastSpt = uri.lastIndexOf("/") + 1;
/*     */     String url;
/* 195 */     if (!StringUtils.isBlank(queryString))
/* 196 */       url = uri + "?" + queryString;
/*     */     else
/* 198 */       url = uri;
/*     */     String urlFormer;
/* 202 */     if (bi != -1) {
/* 203 */       urlFormer = uri.substring(lastSpt, bi);
/*     */     }
/*     */     else
/*     */     {
/* 204 */       if (mi != -1) {
/* 205 */         urlFormer = uri.substring(lastSpt, mi);
/*     */       }
/*     */       else
/*     */       {
/* 206 */         if (pi != -1)
/* 207 */           urlFormer = uri.substring(lastSpt, pi);
/*     */         else
/* 209 */           urlFormer = uri.substring(lastSpt);
/*     */       }
/*     */     }
/*     */     String urlLater;
/* 213 */     if (mi != -1) {
/* 214 */       urlLater = url.substring(mi);
/*     */     }
/*     */     else
/*     */     {
/* 215 */       if (pi != -1)
/* 216 */         urlLater = url.substring(pi);
/*     */       else
/* 218 */         urlLater = url.substring(uri.length());
/*     */     }
/* 220 */     String href = url.substring(lastSpt);
/* 221 */     return new PageInfo(href, urlFormer, urlLater);
/*     */   }
/*     */ 
/*     */   public static class PageInfo
/*     */   {
/*     */     private String href;
/*     */     private String hrefFormer;
/*     */     private String hrefLatter;
/*     */ 
/*     */     public PageInfo(String href, String hrefFormer, String hrefLatter)
/*     */     {
/* 242 */       this.href = href;
/* 243 */       this.hrefFormer = hrefFormer;
/* 244 */       this.hrefLatter = hrefLatter;
/*     */     }
/*     */ 
/*     */     public String getHref() {
/* 248 */       return this.href;
/*     */     }
/*     */ 
/*     */     public void setHref(String href) {
/* 252 */       this.href = href;
/*     */     }
/*     */ 
/*     */     public String getHrefFormer() {
/* 256 */       return this.hrefFormer;
/*     */     }
/*     */ 
/*     */     public void setHrefFormer(String hrefFormer) {
/* 260 */       this.hrefFormer = hrefFormer;
/*     */     }
/*     */ 
/*     */     public String getHrefLatter() {
/* 264 */       return this.hrefLatter;
/*     */     }
/*     */ 
/*     */     public void setHrefLatter(String hrefLatter) {
/* 268 */       this.hrefLatter = hrefLatter;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.web.front.URLHelper
 * JD-Core Version:    0.6.0
 */