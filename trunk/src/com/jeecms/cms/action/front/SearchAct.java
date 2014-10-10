/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class SearchAct
/*    */ {
/*    */   public static final String SEARCH_INPUT = "tpl.searchInput";
/*    */   public static final String SEARCH_RESULT = "tpl.searchResult";
/*    */   public static final String SEARCH_ERROR = "tpl.searchError";
/*    */   public static final String SEARCH_JOB = "tpl.searchJob";
/*    */ 
/*    */   @RequestMapping(value={"/search*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 31 */     CmsSite site = CmsUtils.getSite(request);
/*    */ 
/* 33 */     model.putAll(RequestUtils.getQueryParams(request));
/* 34 */     FrontUtils.frontData(request, model, site);
/* 35 */     FrontUtils.frontPageData(request, model);
/* 36 */     String q = RequestUtils.getQueryParam(request, "q");
/* 37 */     String parseQ = parseKeywords(q);
/* 38 */     model.addAttribute("input", q);
/* 39 */     model.addAttribute("q", parseQ);
/* 40 */     String channelId = RequestUtils.getQueryParam(request, "channelId");
/* 41 */     if ((StringUtils.isBlank(q)) && (StringUtils.isBlank(channelId))) {
/* 42 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 43 */         "special", "tpl.searchInput");
/*    */     }
/* 45 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 46 */       "special", "tpl.searchResult");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/searchJob*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String searchJob(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 53 */     CmsSite site = CmsUtils.getSite(request);
/* 54 */     String q = RequestUtils.getQueryParam(request, "q");
/* 55 */     String category = RequestUtils.getQueryParam(request, "category");
/* 56 */     String workplace = RequestUtils.getQueryParam(request, "workplace");
/* 57 */     model.putAll(RequestUtils.getQueryParams(request));
/* 58 */     FrontUtils.frontData(request, model, site);
/* 59 */     FrontUtils.frontPageData(request, model);
/*    */ 
/* 61 */     String parseQ = parseKeywords(q);
/* 62 */     model.addAttribute("input", q);
/* 63 */     model.addAttribute("q", parseQ);
/* 64 */     model.addAttribute("queryCategory", category);
/* 65 */     model.addAttribute("queryWorkplace", workplace);
/* 66 */     if (StringUtils.isBlank(q)) {
/* 67 */       model.remove("q");
/*    */     }
/* 69 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 70 */       "special", "tpl.searchJob");
/*    */   }
/*    */ 
/*    */   public static String parseKeywords(String q) {
/* 74 */     char c = '\\';
/* 75 */     int cIndex = q.indexOf(c);
/* 76 */     if ((cIndex != -1) && (cIndex == 0)) {
/* 77 */       q = q.substring(1);
/*    */     }
/* 79 */     if ((cIndex != -1) && (cIndex == q.length() - 1))
/* 80 */       q = q.substring(0, q.length() - 1);
/*    */     try
/*    */     {
/* 83 */       String regular = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";
/* 84 */       Pattern p = Pattern.compile(regular);
/* 85 */       Matcher m = p.matcher(q);
/* 86 */       String src = null;
/* 87 */       while (m.find()) {
/* 88 */         src = m.group();
/* 89 */         q = q.replaceAll("\\" + src, "\\\\" + src);
/*    */       }
/* 91 */       q = q.replaceAll("AND", "and").replaceAll("OR", "or").replace("NOT", "not");
/*    */     } catch (Exception e) {
/* 93 */       q = q;
/*    */     }
/* 95 */     return q;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.SearchAct
 * JD-Core Version:    0.6.0
 */