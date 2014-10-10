/*     */ package com.jeecms.cms.web;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*     */ import com.jeecms.common.web.springmvc.MessageResolver;
/*     */ import com.jeecms.core.web.front.URLHelper;
/*     */ import com.jeecms.core.web.front.URLHelper.PageInfo;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.AdapterTemplateModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.propertyeditors.LocaleEditor;
/*     */ import org.springframework.context.MessageSource;
/*     */ 
/*     */ public class FrontUtils
/*     */ {
/*     */   public static final String PAGE_NOT_FOUND = "tpl.pageNotFound";
/*     */   public static final String SUCCESS_PAGE = "tpl.successPage";
/*     */   public static final String ERROR_PAGE = "tpl.errorPage";
/*     */   public static final String MESSAGE_PAGE = "tpl.messagePage";
/*     */   public static final String RES_SYS = "resSys";
/*     */   public static final String RES_TPL = "res";
/*     */   public static final String RES_EXP = "${res}";
/*     */   public static final String BASE = "base";
/*     */   public static final String SITE = "site";
/*     */   public static final String USER = "user";
/*     */   public static final String PAGE_NO = "pageNo";
/*     */   public static final String COUNT = "count";
/*     */   public static final String FIRST = "first";
/*     */   public static final String LOCATION = "location";
/*     */   public static final String HREF = "href";
/*     */   public static final String HREF_FORMER = "hrefFormer";
/*     */   public static final String HREF_LATTER = "hrefLatter";
/*     */   public static final String PARAM_STYLE_LIST = "styleList";
/*     */   public static final String PARAM_SYS_PAGE = "sysPage";
/*     */   public static final String PARAM_USER_PAGE = "userPage";
/*     */   public static final String RETURN_URL = "returnUrl";
/*     */   public static final String ARGS = "args";
/*     */ 
/*     */   public static String getTplPath(HttpServletRequest request, String solution, String dir, String name)
/*     */   {
/* 152 */     return solution + "/" + dir + "/" + 
/* 153 */       MessageResolver.getMessage(request, name, new Object[0]) + ".html";
/*     */   }
/*     */ 
/*     */   public static String getTplPath(MessageSource messageSource, String lang, String solution, String dir, String name)
/*     */   {
/* 172 */     LocaleEditor localeEditor = new LocaleEditor();
/* 173 */     localeEditor.setAsText(lang);
/* 174 */     Locale locale = (Locale)localeEditor.getValue();
/* 175 */     return solution + "/" + dir + "/" + 
/* 176 */       messageSource.getMessage(name, null, locale) + ".html";
/*     */   }
/*     */ 
/*     */   public static String getTplPath(String solution, String dir, String name)
/*     */   {
/* 191 */     return solution + "/" + dir + "/" + name + ".html";
/*     */   }
/*     */ 
/*     */   public static String pageNotFound(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model)
/*     */   {
/* 203 */     response.setStatus(404);
/* 204 */     CmsSite site = CmsUtils.getSite(request);
/* 205 */     frontData(request, model, site);
/* 206 */     return getTplPath(request, site.getSolutionPath(), "common", 
/* 207 */       "tpl.pageNotFound");
/*     */   }
/*     */ 
/*     */   public static String showSuccess(HttpServletRequest request, Map<String, Object> model, String nextUrl)
/*     */   {
/* 219 */     CmsSite site = CmsUtils.getSite(request);
/* 220 */     frontData(request, model, site);
/* 221 */     if (!StringUtils.isBlank(nextUrl)) {
/* 222 */       model.put("nextUrl", nextUrl);
/*     */     }
/* 224 */     return getTplPath(request, site.getSolutionPath(), "common", 
/* 225 */       "tpl.successPage");
/*     */   }
/*     */ 
/*     */   public static String showError(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, WebErrors errors)
/*     */   {
/* 239 */     CmsSite site = CmsUtils.getSite(request);
/* 240 */     frontData(request, model, site);
/* 241 */     errors.toModel(model);
/* 242 */     return getTplPath(request, site.getSolutionPath(), "common", 
/* 243 */       "tpl.errorPage");
/*     */   }
/*     */ 
/*     */   public static String showMessage(HttpServletRequest request, Map<String, Object> model, String message, String[] args)
/*     */   {
/* 257 */     CmsSite site = CmsUtils.getSite(request);
/* 258 */     frontData(request, model, site);
/* 259 */     model.put("message", message);
/* 260 */     if (args != null) {
/* 261 */       model.put("args", args);
/*     */     }
/* 263 */     return getTplPath(request, site.getSolutionPath(), "common", 
/* 264 */       "tpl.messagePage");
/*     */   }
/*     */ 
/*     */   public static String showLogin(HttpServletRequest request, Map<String, Object> model, CmsSite site, String message)
/*     */   {
/* 278 */     if (!StringUtils.isBlank(message)) {
/* 279 */       model.put("message", message);
/*     */     }
/* 281 */     StringBuilder buff = new StringBuilder("redirect:");
/* 282 */     buff.append(site.getLoginUrl()).append("?");
/* 283 */     buff.append("returnUrl").append("=");
/* 284 */     buff.append(RequestUtils.getLocation(request));
/* 285 */     if (!StringUtils.isBlank(site.getProcessUrl())) {
/* 286 */       buff.append("&").append("processUrl").append(site.getProcessUrl());
/*     */     }
/* 288 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   public static String showLogin(HttpServletRequest request, Map<String, Object> model, CmsSite site)
/*     */   {
/* 301 */     return showLogin(request, model, site, "true");
/*     */   }
/*     */ 
/*     */   public static void frontData(HttpServletRequest request, Map<String, Object> map, CmsSite site)
/*     */   {
/* 312 */     CmsUser user = CmsUtils.getUser(request);
/* 313 */     String location = RequestUtils.getLocation(request);
/* 314 */     Long startTime = (Long)request.getAttribute("_start_time");
/* 315 */     frontData(map, site, user, location, startTime);
/*     */   }
/*     */ 
/*     */   public static void frontData(Map<String, Object> map, CmsSite site, CmsUser user, String location, Long startTime)
/*     */   {
/* 320 */     if (startTime != null) {
/* 321 */       map.put("_start_time", startTime);
/*     */     }
/* 323 */     if (user != null) {
/* 324 */       map.put("user", user);
/*     */     }
/* 326 */     map.put("site", site);
/* 327 */     String ctx = site.getContextPath() == null ? "" : site.getContextPath();
/* 328 */     map.put("base", ctx);
/* 329 */     map.put("resSys", ctx + "/r/cms");
/* 330 */     String res = ctx + "/r/cms" + "/" + site.getPath() + "/" + 
/* 331 */       site.getTplSolution();
/*     */ 
/* 333 */     map.put("res", res.substring(1));
/* 334 */     map.put("location", location);
/*     */   }
/*     */ 
/*     */   public static void putLocation(Map<String, Object> map, String location) {
/* 338 */     map.put("location", location);
/*     */   }
/*     */ 
/*     */   public static void frontPageData(HttpServletRequest request, Map<String, Object> map)
/*     */   {
/* 349 */     int pageNo = URLHelper.getPageNo(request);
/* 350 */     URLHelper.PageInfo info = URLHelper.getPageInfo(request);
/* 351 */     String href = info.getHref();
/* 352 */     String hrefFormer = info.getHrefFormer();
/* 353 */     String hrefLatter = info.getHrefLatter();
/* 354 */     frontPageData(pageNo, href, hrefFormer, hrefLatter, map);
/*     */   }
/*     */ 
/*     */   public static void frontPageData(int pageNo, String href, String hrefFormer, String hrefLatter, Map<String, Object> map)
/*     */   {
/* 368 */     map.put("pageNo", Integer.valueOf(pageNo));
/* 369 */     map.put("href", href);
/* 370 */     map.put("hrefFormer", hrefFormer);
/* 371 */     map.put("hrefLatter", hrefLatter);
/*     */   }
/*     */ 
/*     */   public static CmsSite getSite(Environment env)
/*     */     throws TemplateModelException
/*     */   {
/* 383 */     TemplateModel model = env.getGlobalVariable("site");
/* 384 */     if ((model instanceof AdapterTemplateModel)) {
/* 385 */       return (CmsSite)((AdapterTemplateModel)model)
/* 386 */         .getAdaptedObject(CmsSite.class);
/*     */     }
/* 388 */     throw new TemplateModelException("'site' not found in DataModel");
/*     */   }
/*     */ 
/*     */   public static int getPageNo(Environment env)
/*     */     throws TemplateException
/*     */   {
/* 401 */     TemplateModel pageNo = env.getGlobalVariable("pageNo");
/* 402 */     if ((pageNo instanceof TemplateNumberModel)) {
/* 403 */       return ((TemplateNumberModel)pageNo).getAsNumber().intValue();
/*     */     }
/* 405 */     throw new TemplateModelException("'pageNo' not found in DataModel.");
/*     */   }
/*     */ 
/*     */   public static int getFirst(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 412 */     Integer first = DirectiveUtils.getInt("first", params);
/* 413 */     if ((first == null) || (first.intValue() <= 0)) {
/* 414 */       return 0;
/*     */     }
/* 416 */     return first.intValue() - 1;
/*     */   }
/*     */ 
/*     */   public static int getCount(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 429 */     Integer count = DirectiveUtils.getInt("count", params);
/* 430 */     if ((count == null) || (count.intValue() <= 0) || (count.intValue() >= 5000)) {
/* 431 */       return 5000;
/*     */     }
/* 433 */     return count.intValue();
/*     */   }
/*     */ 
/*     */   public static void includePagination(CmsSite site, Map<String, TemplateModel> params, Environment env)
/*     */     throws TemplateException, IOException
/*     */   {
/* 440 */     String sysPage = DirectiveUtils.getString("sysPage", params);
/* 441 */     String userPage = DirectiveUtils.getString("userPage", params);
/* 442 */     if (!StringUtils.isBlank(sysPage)) {
/* 443 */       String tpl = "/WEB-INF/t/cms_sys_defined/style_page/channel_" + sysPage + ".html";
/* 444 */       env.include(tpl, "UTF-8", true);
/* 445 */     } else if (!StringUtils.isBlank(userPage)) {
/* 446 */       String tpl = getTplPath(site.getSolutionPath(), "style_list", 
/* 447 */         userPage);
/* 448 */       env.include(tpl, "UTF-8", true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void includeTpl(String tplName, CmsSite site, Map<String, TemplateModel> params, Environment env)
/*     */     throws IOException, TemplateException
/*     */   {
/* 467 */     String subTpl = DirectiveUtils.getString("tplSub", params);
/*     */     String tpl;
/* 469 */     if (StringUtils.isBlank(subTpl))
/* 470 */       tpl = getTplPath(site.getSolutionPath(), "tag", tplName);
/*     */     else {
/* 472 */       tpl = getTplPath(site.getSolutionPath(), "tag", tplName + "_" + 
/* 473 */         subTpl);
/*     */     }
/* 475 */     env.include(tpl, "UTF-8", true);
/*     */   }
/*     */ 
/*     */   public static void includeTpl(String listStyle, CmsSite site, Environment env)
/*     */     throws IOException, TemplateException
/*     */   {
/* 489 */     String tpl = getTplPath(site.getSolutionPath(), "style_list", 
/* 490 */       listStyle);
/* 491 */     env.include(tpl, "UTF-8", true);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.FrontUtils
 * JD-Core Version:    0.6.0
 */