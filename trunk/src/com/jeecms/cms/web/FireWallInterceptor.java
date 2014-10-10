/*     */ package com.jeecms.cms.web;
/*     */ 
/*     */ import com.jeecms.cms.Constants;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*     */ 
/*     */ public class FireWallInterceptor extends HandlerInterceptorAdapter
/*     */   implements InitializingBean, DisposableBean
/*     */ {
/*  27 */   private static String property_firewall_open = "firewall.open";
/*  28 */   private static String property_firewall_domain = "firewall.domain";
/*  29 */   private static String property_firewall_hour = "firewall.hour";
/*  30 */   private static String property_firewall_week = "firewall.week";
/*  31 */   private static String property_firewall_ips = "firewall.ips";
/*     */   public static final String FIREWALL_CONFIG_LASTMODIFIED = "firewall_config_lastmodified";
/*     */   private InputStream in;
/*  36 */   private Properties p = new Properties();
/*     */   private File fireWallConfigFile;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/*  39 */     this.fireWallConfigFile = new File(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/*  40 */     this.in = new FileInputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/*  41 */     this.p.load(this.in);
/*     */   }
/*     */   public void destroy() throws Exception {
/*  44 */     this.in.close();
/*     */   }
/*     */ 
/*     */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/*  48 */     Boolean configFileModified = Boolean.valueOf(false);
/*  49 */     Long configLastModifiedTime = getFireWallConfigFileLastModifiedTime(request);
/*  50 */     if ((configLastModifiedTime == null) || (this.fireWallConfigFile.lastModified() > configLastModifiedTime.longValue())) {
/*  51 */       configFileModified = Boolean.valueOf(true);
/*  52 */       changeConfigModifiedTime(request);
/*     */     }
/*     */ 
/*  59 */     if (configFileModified.booleanValue()) {
/*  60 */       this.in = new FileInputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/*  61 */       this.p.load(this.in);
/*  62 */       this.in.close();
/*     */     }
/*  64 */     String open = this.p.getProperty(property_firewall_open);
/*  65 */     String domain = this.p.getProperty(property_firewall_domain);
/*  66 */     String ips = this.p.getProperty(property_firewall_ips);
/*  67 */     String week = this.p.getProperty(property_firewall_week);
/*  68 */     String hour = this.p.getProperty(property_firewall_hour);
/*  69 */     String[] ipArrays = StringUtils.split(ips, ",");
/*  70 */     String[] weekArrays = StringUtils.split(week, ",");
/*  71 */     String[] hourArrays = StringUtils.split(hour, ",");
/*     */ 
/*  73 */     String requestIp = RequestUtils.getIpAddr(request);
/*  74 */     if (open.equals("1")) {
/*  75 */       if (!isAuthDomain(domain, request.getServerName()).booleanValue()) {
/*  76 */         return false;
/*     */       }
/*  78 */       if (!isAuthIp(ipArrays, requestIp).booleanValue()) {
/*  79 */         return false;
/*     */       }
/*  81 */       if (!isAuthWeek(weekArrays).booleanValue()) {
/*  82 */         return false;
/*     */       }
/*  84 */       if (!isAuthHour(hourArrays).booleanValue()) {
/*  85 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  91 */     return true;
/*     */   }
/*     */   private Boolean isAuthDomain(String domain, String requestDomain) {
/*  94 */     if (StringUtils.isNotBlank(domain)) {
/*  95 */       if (domain.equals(requestDomain)) {
/*  96 */         return Boolean.valueOf(true);
/*     */       }
/*  98 */       return Boolean.valueOf(false);
/*     */     }
/*     */ 
/* 101 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   private Boolean isAuthIp(String[] ips, String requestIp) {
/* 105 */     if ((ips != null) && (ips.length > 0)) {
/* 106 */       for (String ip : ips) {
/* 107 */         if (ip.equals(requestIp))
/* 108 */           return Boolean.valueOf(true);
/*     */       }
/*     */     }
/*     */     else {
/* 112 */       return Boolean.valueOf(true);
/*     */     }
/* 114 */     return Boolean.valueOf(false);
/*     */   }
/*     */   private Boolean isAuthWeek(String[] weeks) {
/* 117 */     Calendar c = Calendar.getInstance();
/* 118 */     int day_of_week = c.get(7);
/* 119 */     if ((weeks != null) && (weeks.length > 0)) {
/* 120 */       for (String week : weeks) {
/* 121 */         if (week.equals(day_of_week))
/* 122 */           return Boolean.valueOf(true);
/*     */       }
/*     */     }
/*     */     else {
/* 126 */       return Boolean.valueOf(true);
/*     */     }
/* 128 */     return Boolean.valueOf(false);
/*     */   }
/*     */   private Boolean isAuthHour(String[] hours) {
/* 131 */     Calendar c = Calendar.getInstance();
/* 132 */     int hour_of_day = c.get(11);
/* 133 */     if ((hours != null) && (hours.length > 0)) {
/* 134 */       for (String hour : hours) {
/* 135 */         if (hour.equals(hour_of_day))
/* 136 */           return Boolean.valueOf(true);
/*     */       }
/*     */     }
/*     */     else {
/* 140 */       return Boolean.valueOf(true);
/*     */     }
/* 142 */     return Boolean.valueOf(false);
/*     */   }
/*     */   private Long getFireWallConfigFileLastModifiedTime(HttpServletRequest request) {
/* 145 */     return (Long)request.getSession().getServletContext().getAttribute("firewall_config_lastmodified");
/*     */   }
/*     */   private void changeConfigModifiedTime(HttpServletRequest request) {
/* 148 */     request.getSession().getServletContext().setAttribute("firewall_config_lastmodified", Long.valueOf(Calendar.getInstance().getTime().getTime()));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.FireWallInterceptor
 * JD-Core Version:    0.6.0
 */