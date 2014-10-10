/*    */ package com.jeecms.cms.action.admin.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*    */ import com.jeecms.cms.manager.assist.CmsDictionaryMng;
/*    */ import com.jeecms.cms.manager.main.CmsLogMng;
/*    */ import com.jeecms.cms.manager.main.CmsSiteCompanyMng;
/*    */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.WebErrors;
/*    */ import com.jeecms.core.manager.FtpMng;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CmsSiteConfigAct
/*    */ {
/* 29 */   private static final Logger log = LoggerFactory.getLogger(CmsSiteConfigAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private FtpMng ftpMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsLogMng cmsLogMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteCompanyMng siteCompanyMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsDictionaryMng dictionaryMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteMng manager;
/*    */ 
/* 33 */   @RequestMapping({"/site_config/v_base_edit.do"})
/*    */   public String baseEdit(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/* 34 */     List ftpList = this.ftpMng.getList();
/* 35 */     model.addAttribute("ftpList", ftpList);
/* 36 */     model.addAttribute("cmsSite", site);
/* 37 */     return "site_config/base_edit"; }
/*    */ 
/*    */   @RequestMapping({"/site_config/o_base_update.do"})
/*    */   public String baseUpdate(CmsSite bean, Integer uploadFtpId, HttpServletRequest request, ModelMap model)
/*    */   {
/* 43 */     WebErrors errors = validateBaseUpdate(bean, request);
/* 44 */     if (errors.hasErrors()) {
/* 45 */       return errors.showErrorPage(model);
/*    */     }
/* 47 */     CmsSite site = CmsUtils.getSite(request);
/* 48 */     bean.setId(site.getId());
/* 49 */     bean = this.manager.update(bean, uploadFtpId);
/* 50 */     model.addAttribute("message", "global.success");
/* 51 */     log.info("update CmsSite success. id={}", site.getId());
/* 52 */     this.cmsLogMng.operating(request, "cmsSiteConfig.log.updateBase", null);
/* 53 */     return baseEdit(request, model);
/*    */   }
/*    */   @RequestMapping({"/site_config/v_company_edit.do"})
/*    */   public String companyEdit(HttpServletRequest request, ModelMap model) {
/* 58 */     CmsSite site = CmsUtils.getSite(request);
/* 59 */     CmsSiteCompany company = site.getSiteCompany();
/* 60 */     List scales = this.dictionaryMng.getList("scale");
/* 61 */     List natures = this.dictionaryMng.getList("nature");
/* 62 */     List industrys = this.dictionaryMng.getList("industry");
/* 63 */     model.addAttribute("company", company);
/* 64 */     model.addAttribute("scales", scales);
/* 65 */     model.addAttribute("natures", natures);
/* 66 */     model.addAttribute("industrys", industrys);
/* 67 */     return "site_config/company_edit";
/*    */   }
/*    */   @RequestMapping({"/site_config/o_company_update.do"})
/*    */   public String companyUpdate(CmsSiteCompany company, HttpServletRequest request, ModelMap model) {
/* 72 */     CmsSite site = CmsUtils.getSite(request);
/* 73 */     WebErrors errors = validateCompanyUpdate(site, company, request);
/* 74 */     if (errors.hasErrors()) {
/* 75 */       return errors.showErrorPage(model);
/*    */     }
/* 77 */     this.siteCompanyMng.update(company);
/* 78 */     model.addAttribute("message", "global.success");
/* 79 */     log.info("update CmsSite success. id={}", site.getId());
/* 80 */     this.cmsLogMng.operating(request, "cmsSiteConfig.log.updateBase", null);
/* 81 */     return companyEdit(request, model);
/*    */   }
/*    */ 
/*    */   private WebErrors validateBaseUpdate(CmsSite bean, HttpServletRequest request)
/*    */   {
/* 86 */     WebErrors errors = WebErrors.create(request);
/* 87 */     return errors;
/*    */   }
/*    */ 
/*    */   private WebErrors validateCompanyUpdate(CmsSite site, CmsSiteCompany company, HttpServletRequest request)
/*    */   {
/* 92 */     WebErrors errors = WebErrors.create(request);
/* 93 */     if (!company.getId().equals(site.getId())) {
/* 94 */       errors.addErrorCode("error.notInSite", new Object[] { CmsSiteCompany.class, company.getId() });
/*    */     }
/* 96 */     return errors;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsSiteConfigAct
 * JD-Core Version:    0.6.0
 */