/*     */ package com.jeecms.cms.action.front;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.assist.CmsKeywordMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.page.Paginable;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.core.web.front.URLHelper;
/*     */ import com.jeecms.core.web.front.URLHelper.PageInfo;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class DynamicPageAct
/*     */ {
/*  37 */   private static final Logger log = LoggerFactory.getLogger(DynamicPageAct.class);
/*     */   public static final String TPL_INDEX = "tpl.index";
/*     */   public static final String GROUP_FORBIDDEN = "login.groupAccessForbidden";
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsKeywordMng cmsKeywordMng;
/*     */ 
/*     */   @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(HttpServletRequest request, ModelMap model)
/*     */   {
/*  53 */     CmsSite site = CmsUtils.getSite(request);
/*  54 */     FrontUtils.frontData(request, model, site);
/*  55 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  56 */       "index", "tpl.index");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/index.jhtml"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String indexForWeblogic(HttpServletRequest request, ModelMap model)
/*     */   {
/*  68 */     return index(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/**/*.*"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String dynamic(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  80 */     int pageNo = URLHelper.getPageNo(request);
/*  81 */     String[] params = URLHelper.getParams(request);
/*  82 */     URLHelper.PageInfo info = URLHelper.getPageInfo(request);
/*  83 */     String[] paths = URLHelper.getPaths(request);
/*  84 */     int len = paths.length;
/*  85 */     if (len == 1)
/*     */     {
/*  87 */       return channel(paths[0], pageNo, params, info, request, response, 
/*  88 */         model);
/*  89 */     }if (len == 2) {
/*  90 */       if (paths[1].equals("index"))
/*     */       {
/*  92 */         return channel(paths[0], pageNo, params, info, request, 
/*  93 */           response, model);
/*     */       }
/*     */       try
/*     */       {
/*  97 */         Integer id = Integer.valueOf(Integer.parseInt(paths[1]));
/*  98 */         return content(id, pageNo, params, info, request, response, 
/*  99 */           model);
/*     */       } catch (NumberFormatException e) {
/* 101 */         log.debug("Content id must String: {}", paths[1]);
/* 102 */         return FrontUtils.pageNotFound(request, response, model);
/*     */       }
/*     */     }
/*     */ 
/* 106 */     log.debug("Illegal path length: {}, paths: {}", Integer.valueOf(len), paths);
/* 107 */     return FrontUtils.pageNotFound(request, response, model);
/*     */   }
/*     */ 
/*     */   public String channel(String path, int pageNo, String[] params, URLHelper.PageInfo info, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 114 */     CmsSite site = CmsUtils.getSite(request);
/* 115 */     Channel channel = this.channelMng.findByPathForTag(path, site.getId());
/* 116 */     if (channel == null) {
/* 117 */       log.debug("Channel path not found: {}", path);
/* 118 */       return FrontUtils.pageNotFound(request, response, model);
/*     */     }
/*     */ 
/* 121 */     model.addAttribute("channel", channel);
/* 122 */     FrontUtils.frontData(request, model, site);
/* 123 */     FrontUtils.frontPageData(request, model);
/* 124 */     return channel.getTplChannelOrDef();
/*     */   }
/*     */ 
/*     */   public String content(Integer id, int pageNo, String[] params, URLHelper.PageInfo info, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 130 */     Content content = this.contentMng.findById(id);
/* 131 */     if (content == null) {
/* 132 */       log.debug("Content id not found: {}", id);
/* 133 */       return FrontUtils.pageNotFound(request, response, model);
/*     */     }
/* 135 */     CmsUser user = CmsUtils.getUser(request);
/* 136 */     CmsSite site = content.getSite();
/* 137 */     Set<CmsGroup> groups = content.getViewGroupsExt();
/* 138 */     int len = groups.size();
/*     */ 
/* 140 */     if (len != 0)
/*     */     {
/* 142 */       if (user == null) {
/* 143 */         return FrontUtils.showLogin(request, model, site);
/*     */       }
/*     */ 
/* 146 */       Integer gid = user.getGroup().getId();
/* 147 */       boolean right = false;
/* 148 */       for (CmsGroup group : groups) {
/* 149 */         if (group.getId().equals(gid)) {
/* 150 */           right = true;
/* 151 */           break;
/*     */         }
/*     */       }
/* 154 */       if (!right) {
/* 155 */         String gname = user.getGroup().getName();
/* 156 */         return FrontUtils.showMessage(request, model, "login.groupAccessForbidden", new String[] { 
/* 157 */           gname });
/*     */       }
/*     */     }
/* 160 */     String txt = content.getTxtByNo(pageNo);
/*     */ 
/* 162 */     txt = this.cmsKeywordMng.attachKeyword(site.getId(), txt);
/* 163 */     Paginable pagination = new SimplePage(pageNo, 1, content.getPageCount());
/* 164 */     model.addAttribute("pagination", pagination);
/* 165 */     FrontUtils.frontPageData(request, model);
/* 166 */     model.addAttribute("content", content);
/* 167 */     model.addAttribute("channel", content.getChannel());
/* 168 */     model.addAttribute("title", content.getTitleByNo(pageNo));
/* 169 */     model.addAttribute("txt", txt);
/* 170 */     model.addAttribute("pic", content.getPictureByNo(pageNo));
/* 171 */     FrontUtils.frontData(request, model, site);
/* 172 */     return content.getTplContentOrDef();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.DynamicPageAct
 * JD-Core Version:    0.6.0
 */