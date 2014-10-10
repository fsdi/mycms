/*     */ package com.jeecms.cms.staticpage;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class StaticAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(StaticAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private StaticPageSvc staticPageSvc;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*  34 */   @RequestMapping({"/static/v_welcome.do"})
/*     */   public String welcome() { return "static/welcome"; }
/*     */ 
/*     */   @RequestMapping({"/static/v_index.do"})
/*     */   public String indexInput(HttpServletRequest request, ModelMap model) {
/*  39 */     return "static/index";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/o_index.do"})
/*     */   public void indexSubmit(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*     */     try {
/*  46 */       CmsSite site = CmsUtils.getSite(request);
/*  47 */       this.staticPageSvc.index(site);
/*  48 */       ResponseUtils.renderJson(response, "{'success':true}");
/*     */     } catch (IOException e) {
/*  50 */       log.error("static index error!", e);
/*  51 */       ResponseUtils.renderJson(response, "{'success':false,'msg':'" + 
/*  52 */         e.getMessage() + "'}");
/*     */     } catch (TemplateException e) {
/*  54 */       log.error("static index error!", e);
/*  55 */       ResponseUtils.renderJson(response, "{'success':false,'msg':'" + 
/*  56 */         e.getMessage() + "'}");
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/o_index_remove.do"})
/*     */   public void indexRemove(HttpServletRequest request, HttpServletResponse response) {
/*  63 */     CmsSite site = CmsUtils.getSite(request);
/*     */     String msg;
/*  65 */     if (this.staticPageSvc.deleteIndex(site))
/*  66 */       msg = "{'success':true}";
/*     */     else {
/*  68 */       msg = "{'success':false}";
/*     */     }
/*  70 */     ResponseUtils.renderJson(response, msg);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/v_channel.do"})
/*     */   public String channelInput(HttpServletRequest request, ModelMap model) {
/*  76 */     CmsSite site = CmsUtils.getSite(request);
/*  77 */     List topList = this.channelMng.getTopList(site.getId(), false);
/*  78 */     List channelList = Channel.getListForSelect(topList, null, 
/*  79 */       null, false);
/*  80 */     model.addAttribute("channelList", channelList);
/*  81 */     return "static/channel";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/o_channel.do"})
/*     */   public void channelSubmit(Integer channelId, Boolean containChild, HttpServletRequest request, HttpServletResponse response) {
/*  87 */     CmsSite site = CmsUtils.getSite(request);
/*  88 */     if (containChild == null)
/*  89 */       containChild = Boolean.valueOf(true);
/*     */     String msg;
/*     */     try {
/*  93 */       int count = this.staticPageSvc.channel(site.getId(), channelId, 
/*  94 */         containChild.booleanValue());
/*  95 */       msg = "{'success':true,'count':" + count + "}";
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  97 */       log.error("static channel error!", e);
/*  98 */       msg = "{'success':false,'msg':'" + e.getMessage() + "'}";
/*     */     }
/*     */     catch (TemplateException e)
/*     */     {
/* 100 */       log.error("static channel error!", e);
/* 101 */       msg = "{'success':false,'msg':'" + e.getMessage() + "'}";
/*     */     }
/* 103 */     ResponseUtils.renderJson(response, msg);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/v_content.do"})
/*     */   public String contentInput(HttpServletRequest request, ModelMap model) {
/* 109 */     CmsSite site = CmsUtils.getSite(request);
/* 110 */     List topList = this.channelMng.getTopList(site.getId(), true);
/* 111 */     List channelList = Channel.getListForSelect(topList, null, 
/* 112 */       null, true);
/* 113 */     model.addAttribute("channelList", channelList);
/* 114 */     return "static/content";
/*     */   }
/*     */   @RequestMapping({"/static/o_content.do"})
/*     */   public void contentSubmit(Integer channelId, Date startDate, HttpServletRequest request, HttpServletResponse response) {
/*     */     String msg;
/*     */     try {
/* 122 */       Integer siteId = null;
/* 123 */       if (channelId == null)
/*     */       {
/* 125 */         siteId = CmsUtils.getSiteId(request);
/*     */       }
/* 127 */       int count = this.staticPageSvc.content(siteId, channelId, startDate);
/* 128 */       msg = "{'success':true,'count':" + count + "}";
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 130 */       log.error("static channel error!", e);
/* 131 */       msg = "{'success':false,'msg':'" + e.getMessage() + "'}";
/*     */     }
/*     */     catch (TemplateException e)
/*     */     {
/* 133 */       log.error("static channel error!", e);
/* 134 */       msg = "{'success':false,'msg':'" + e.getMessage() + "'}";
/*     */     }
/* 136 */     ResponseUtils.renderJson(response, msg);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/v_reset_generate.do"})
/*     */   public String resetgenerateInput(HttpServletRequest request, ModelMap model) {
/* 142 */     CmsSite site = CmsUtils.getSite(request);
/* 143 */     List topList = this.channelMng.getTopList(site.getId(), false);
/* 144 */     List channelList = Channel.getListForSelect(topList, null, 
/* 145 */       null, false);
/* 146 */     model.addAttribute("channelList", channelList);
/* 147 */     return "static/resetgenerate";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/static/o_reset_generate.do"})
/*     */   public void resetGenerate(Integer channelId, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/*     */     String msg;
/* 153 */     if (channelId == null) {
/* 154 */       ResponseUtils.renderJson(response, "{'success':false}");
/* 155 */       msg = "{'success':false,'msg':'channel id is null '}";
/*     */     } else {
/* 157 */       Channel c = this.channelMng.findById(channelId);
/* 158 */       if (c == null) {
/* 159 */         msg = "{'success':false,'msg':'channel is not find '}";
/*     */       }
/* 161 */       Integer[] cIds = { channelId };
/* 162 */       List<Content> contents = this.contentMng.getListByChannelIdsForTag(cIds, null, null, null, null, 1, 1, Integer.valueOf(0), Integer.valueOf(2147483647));
/* 163 */       for (Content content : contents) {
/* 164 */         content.getContentExt().setNeedRegenerate(Boolean.valueOf(true));
/* 165 */         this.contentMng.update(content);
/*     */       }
/* 167 */       msg = "{'success':true,'count':" + contents.size() + "}";
/*     */     }
/* 169 */     ResponseUtils.renderJson(response, msg);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticAct
 * JD-Core Version:    0.6.0
 */