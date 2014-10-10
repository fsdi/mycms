/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.ContentTag;
/*    */ import com.jeecms.cms.manager.main.ContentTagMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class TagAct
/*    */ {
/*    */   public static final String TAGS_INDEX = "tpl.tagIndex";
/*    */   public static final String TAGS_DETAIL = "tpl.tagDetail";
/*    */ 
/*    */   @Autowired
/*    */   private ContentTagMng contentTagMng;
/*    */ 
/*    */   @RequestMapping(value={"/tag*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 31 */     CmsSite site = CmsUtils.getSite(request);
/* 32 */     FrontUtils.frontData(request, model, site);
/* 33 */     FrontUtils.frontPageData(request, model);
/* 34 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 35 */       "special", "tpl.tagIndex");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/tag/{path}.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String tags(@PathVariable String path, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 41 */     CmsSite site = CmsUtils.getSite(request);
/*    */     int id;
/*    */     int pageNo;
			 if (StringUtils.isBlank(path)) {
/* 43 */       return FrontUtils.pageNotFound(request, response, model);
/*    */     }
/* 45 */     int index = path.indexOf("_");
/*    */     try
/*    */     {
/* 48 */       if (index != -1) {
/* 49 */         id = Integer.valueOf(path.substring(0, index)).intValue();
/* 50 */         pageNo = Integer.valueOf(path.substring(index + 1, 
/* 51 */           path.length())).intValue();
/*    */       }
/*    */       else {
/* 53 */         id = Integer.valueOf(path).intValue();
/* 54 */         pageNo = 1;
/*    */       }
/*    */     }
/*    */     catch (NumberFormatException e)
/*    */     {
/* 57 */       return FrontUtils.pageNotFound(request, response, model);
/*    */     }

/* 59 */     ContentTag tag = this.contentTagMng.findById(Integer.valueOf(id));
/* 60 */     if (tag == null) {
/* 61 */       return FrontUtils.pageNotFound(request, response, model);
/*    */     }
/* 63 */     model.addAttribute("tag", tag);
/* 64 */     model.addAttribute("pageNo", Integer.valueOf(pageNo));
/* 65 */     FrontUtils.frontData(request, model, site);
/* 66 */     FrontUtils.frontPageData(request, model);
/* 67 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 68 */       "special", "tpl.tagDetail");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.TagAct
 * JD-Core Version:    0.6.0
 */