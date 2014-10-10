/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.Constants;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsFireWallConfigAct
/*     */ {
/*     */   public static final String FIREWALL_LOGIN = "firewall_login";
/*     */   public static final String FIREWALL_CONFIG_LASTMODIFIED = "firewall_config_lastmodified";
/*  37 */   String property_firewall_password = "firewall.password";
/*  38 */   String property_firewall_open = "firewall.open";
/*  39 */   String property_firewall_domain = "firewall.domain";
/*  40 */   String property_firewall_hour = "firewall.hour";
/*  41 */   String property_firewall_week = "firewall.week";
/*  42 */   String property_firewall_ips = "firewall.ips";
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteMng siteManager;
/*     */ 
/*  46 */   @RequestMapping({"/config/v_login.do"})
/*     */   public String v_login(HttpServletRequest request, Model model) { return "config/firewall_login"; }
/*     */ 
/*     */   @RequestMapping({"/config/o_login.do"})
/*     */   public String o_login(String password, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
/*  51 */     InputStream in = new FileInputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/*  52 */     Properties p = new Properties();
/*  53 */     p.load(in);
/*  54 */     String pass = p.getProperty(this.property_firewall_password);
/*  55 */     if (pass.equals(password)) {
/*  56 */       this.session.setAttribute(request, response, "firewall_login", Boolean.valueOf(true));
/*  57 */       return edit(request, model);
/*     */     }
/*  59 */     return "config/firewall_login_error";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/v_firewall.do"})
/*     */   public String edit(HttpServletRequest request, Model model) throws IOException {
/*  65 */     Boolean is_login = (Boolean)this.session.getAttribute(request, "firewall_login");
/*  66 */     if ((is_login != null) && (is_login.booleanValue())) {
/*  67 */       InputStream in = new FileInputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/*  68 */       Properties p = new Properties();
/*  69 */       p.load(in);
/*  70 */       String password = p.getProperty(this.property_firewall_password);
/*  71 */       String open = p.getProperty(this.property_firewall_open);
/*  72 */       String domain = p.getProperty(this.property_firewall_domain);
/*  73 */       String hour = p.getProperty(this.property_firewall_hour);
/*  74 */       String week = p.getProperty(this.property_firewall_week);
/*  75 */       String ips = p.getProperty(this.property_firewall_ips);
/*  76 */       String[] hours = StringUtils.split(hour, ",");
/*  77 */       Set hourIds = new HashSet();
/*  78 */       for (String h : hours) {
/*  79 */         hourIds.add(Integer.decode(h));
/*     */       }
/*  81 */       String[] weeks = StringUtils.split(week, ",");
/*  82 */       Object weekIds = new HashSet();
/*  83 */       for (String w : weeks) {
/*  84 */         ((Set)weekIds).add(Integer.decode(w));
/*     */       }
/*  86 */       model.addAttribute("password", password);
/*  87 */       model.addAttribute("open", open);
/*  88 */       model.addAttribute("domain", domain);
/*  89 */       model.addAttribute("hour", hour);
/*  90 */       model.addAttribute("week", week);
/*  91 */       model.addAttribute("ips", ips);
/*  92 */       model.addAttribute("hours", hourIds);
/*  93 */       model.addAttribute("weeks", weekIds);
/*  94 */       return "config/firewall_edit";
/*     */     }
/*  96 */     return (String)"config/firewall_login";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_firewall.do"})
/*     */   public String update(HttpServletRequest request, Model model, String open, String oldPassword, String password, String domain, String[] week, String[] hour, String ips)
/*     */     throws IOException
/*     */   {
/* 104 */     InputStream in = new FileInputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/* 105 */     Properties p = new Properties();
/* 106 */     p.load(in);
/* 107 */     if (StringUtils.isNotBlank(password)) {
/* 108 */       p.setProperty(this.property_firewall_password, password);
/*     */     }
/* 110 */     p.setProperty(this.property_firewall_open, open);
/* 111 */     p.setProperty(this.property_firewall_domain, domain);
/* 112 */     CmsSite site = CmsUtils.getSite(request);
/* 113 */     configSiteDomainAlias(site, domain);
/* 114 */     if ((week != null) && (week.length > 0))
/* 115 */       p.setProperty(this.property_firewall_week, convertArrayToString(week));
/*     */     else {
/* 117 */       p.setProperty(this.property_firewall_week, "");
/*     */     }
/* 119 */     if ((hour != null) && (hour.length > 0))
/* 120 */       p.setProperty(this.property_firewall_hour, convertArrayToString(hour));
/*     */     else {
/* 122 */       p.setProperty(this.property_firewall_hour, "");
/*     */     }
/* 124 */     p.setProperty(this.property_firewall_ips, ips);
/* 125 */     OutputStream out = new FileOutputStream(this.realPathResolver.get(Constants.FIREWALL_CONFIGPATH));
/* 126 */     p.store(out, "update firewall config");
/* 127 */     model.addAttribute("message", "global.success");
/* 128 */     return edit(request, model);
/*     */   }
/*     */ 
/*     */   private String convertArrayToString(String[] arrays) {
/* 132 */     String str = "";
/* 133 */     if ((arrays != null) && (arrays.length > 0)) {
/* 134 */       for (String s : arrays) {
/* 135 */         str = str + s + ",";
/*     */       }
/*     */     }
/* 138 */     return str;
/*     */   }
/*     */   private void configSiteDomainAlias(CmsSite site, String domain) {
/* 141 */     if (StringUtils.isNotBlank(site.getDomainAlias())) {
/* 142 */       if (!site.getDomainAlias().contains(domain))
/* 143 */         site.setDomainAlias(site.getDomainAlias() + "," + domain);
/*     */     }
/*     */     else {
/* 146 */       site.setDomainAlias(domain);
/*     */     }
/* 148 */     if (site.getUploadFtp() != null)
/* 149 */       this.siteManager.update(site, site.getUploadFtp().getId());
/*     */     else
/* 151 */       this.siteManager.update(site, null);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsFireWallConfigAct
 * JD-Core Version:    0.6.0
 */