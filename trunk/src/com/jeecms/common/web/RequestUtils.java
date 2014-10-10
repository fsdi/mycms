/*     */ package com.jeecms.common.web;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.web.util.UrlPathHelper;
/*     */ 
/*     */ public class RequestUtils
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);
/*     */ 
/*     */   public static String getQueryParam(HttpServletRequest request, String name)
/*     */   {
/*  39 */     if (StringUtils.isBlank(name)) {
/*  40 */       return null;
/*     */     }
/*  42 */     if (request.getMethod().equalsIgnoreCase("POST")) {
/*  43 */       return request.getParameter(name);
/*     */     }
/*  45 */     String s = request.getQueryString();
/*  46 */     if (StringUtils.isBlank(s))
/*  47 */       return null;
/*     */     try
/*     */     {
/*  50 */       s = URLDecoder.decode(s, "UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  52 */       log.error("encoding UTF-8 not support?", e);
/*     */     }
/*  54 */     String[] values = (String[])parseQueryString(s).get(name);
/*  55 */     if ((values != null) && (values.length > 0)) {
/*  56 */       return values[(values.length - 1)];
/*     */     }
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getQueryParams(HttpServletRequest request)
/*     */   {
/*     */     Map<String, String[]> map;
/*  65 */     if (request.getMethod().equalsIgnoreCase("POST")) {
/*  66 */       map = request.getParameterMap();
/*     */     } else {
/*  68 */       String s = request.getQueryString();
/*  69 */       if (StringUtils.isBlank(s))
/*  70 */         return new HashMap();
/*     */       try
/*     */       {
/*  73 */         s = URLDecoder.decode(s, "UTF-8");
/*     */       } catch (UnsupportedEncodingException e) {
/*  75 */         log.error("encoding UTF-8 not support?", e);
/*     */       }
/*  77 */       map = parseQueryString(s);
/*     */     }
/*     */ 
/*  80 */     Map params = new HashMap(map.size());
/*     */ 
/*  82 */     for (Map.Entry entry : map.entrySet()) {
/*  83 */       int len = ((String[])entry.getValue()).length;
/*  84 */       if (len == 1)
/*  85 */         params.put((String)entry.getKey(), ((String[])entry.getValue())[0]);
/*  86 */       else if (len > 1) {
/*  87 */         params.put((String)entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*  90 */     return params;
/*     */   }
/*     */ 
/*     */   public static Map<String, String[]> parseQueryString(String s)
/*     */   {
/* 123 */     String[] valArray = (String[])null;
/* 124 */     if (s == null) {
/* 125 */       throw new IllegalArgumentException();
/*     */     }
/* 127 */     Map ht = new HashMap();
/* 128 */     StringTokenizer st = new StringTokenizer(s, "&");
/* 129 */     while (st.hasMoreTokens()) {
/* 130 */       String pair = st.nextToken();
/* 131 */       int pos = pair.indexOf('=');
/* 132 */       if (pos == -1) {
/*     */         continue;
/*     */       }
/* 135 */       String key = pair.substring(0, pos);
/* 136 */       String val = pair.substring(pos + 1, pair.length());
/* 137 */       if (ht.containsKey(key)) {
/* 138 */         String[] oldVals = (String[])ht.get(key);
/* 139 */         valArray = new String[oldVals.length + 1];
/* 140 */         for (int i = 0; i < oldVals.length; i++) {
/* 141 */           valArray[i] = oldVals[i];
/*     */         }
/* 143 */         valArray[oldVals.length] = val;
/*     */       } else {
/* 145 */         valArray = new String[1];
/* 146 */         valArray[0] = val;
/*     */       }
/* 148 */       ht.put(key, valArray);
/*     */     }
/* 150 */     return ht;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getRequestMap(HttpServletRequest request, String prefix)
/*     */   {
/* 155 */     return getRequestMap(request, prefix, false);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getRequestMapWithPrefix(HttpServletRequest request, String prefix)
/*     */   {
/* 160 */     return getRequestMap(request, prefix, true);
/*     */   }
/*     */ 
/*     */   private static Map<String, String> getRequestMap(HttpServletRequest request, String prefix, boolean nameWithPrefix)
/*     */   {
/* 166 */     Map map = new HashMap();
/* 167 */     Enumeration names = request.getParameterNames();
/*     */ 
/* 169 */     while (names.hasMoreElements()) {
/* 170 */       String name = (String)names.nextElement();
/* 171 */       if (name.startsWith(prefix)) {
/* 172 */         String key = nameWithPrefix ? name : name.substring(prefix.length());
/* 173 */         String value = StringUtils.join(request.getParameterValues(name), ',');
/* 174 */         map.put(key, value);
/*     */       }
/*     */     }
/* 177 */     return map;
/*     */   }
/*     */ 
/*     */   public static String getIpAddr(HttpServletRequest request)
/*     */   {
/* 192 */     String ip = request.getHeader("X-Real-IP");
/* 193 */     if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip))) {
/* 194 */       return ip;
/*     */     }
/* 196 */     ip = request.getHeader("X-Forwarded-For");
/* 197 */     if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip)))
/*     */     {
/* 199 */       int index = ip.indexOf(',');
/* 200 */       if (index != -1) {
/* 201 */         return ip.substring(0, index);
/*     */       }
/* 203 */       return ip;
/*     */     }
/*     */ 
/* 206 */     return request.getRemoteAddr();
/*     */   }
/*     */ 
/*     */   public static String getLocation(HttpServletRequest request)
/*     */   {
/* 219 */     UrlPathHelper helper = new UrlPathHelper();
/* 220 */     StringBuffer buff = request.getRequestURL();
/* 221 */     String uri = request.getRequestURI();
/* 222 */     String origUri = helper.getOriginatingRequestUri(request);
/* 223 */     buff.replace(buff.length() - uri.length(), buff.length(), origUri);
/* 224 */     String queryString = helper.getOriginatingQueryString(request);
/* 225 */     if (queryString != null) {
/* 226 */       buff.append("?").append(queryString);
/*     */     }
/* 228 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   public static String getRequestedSessionId(HttpServletRequest request)
/*     */   {
/* 241 */     String sid = request.getRequestedSessionId();
/* 242 */     String ctx = request.getContextPath();
/*     */ 
/* 244 */     if ((request.isRequestedSessionIdFromURL()) || (StringUtils.isBlank(ctx))) {
/* 245 */       return sid;
/*     */     }
/*     */ 
/* 248 */     Cookie cookie = CookieUtils.getCookie(request, 
/* 249 */       "JSESSIONID");
/* 250 */     if (cookie != null) {
/* 251 */       return cookie.getValue();
/*     */     }
/* 253 */     return request.getSession().getId();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.RequestUtils
 * JD-Core Version:    0.6.0
 */