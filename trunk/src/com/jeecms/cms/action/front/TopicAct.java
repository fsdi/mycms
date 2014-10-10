/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsTopic;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.manager.main.CmsTopicMng;
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
/*    */ public class TopicAct
/*    */ {
/*    */   public static final String TOPIC_INDEX = "tpl.topicIndex";
/*    */   public static final String TOPIC_CHANNEL = "tpl.topicChannel";
/*    */   public static final String TOPIC_DEFAULT = "tpl.topicDefault";
/*    */ 
/*    */   @Autowired
/*    */   private CmsTopicMng cmsTopicMng;
/*    */ 
/*    */   @Autowired
/*    */   private ChannelMng channelMng;
/*    */ 
/*    */   @RequestMapping(value={"/topic*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String index(Integer channelId, Integer topicId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 36 */     CmsSite site = CmsUtils.getSite(request);
/* 37 */     FrontUtils.frontData(request, model, site);
/* 38 */     FrontUtils.frontPageData(request, model);
/* 39 */     if (topicId != null) {
/* 40 */       CmsTopic topic = this.cmsTopicMng.findById(topicId);
/* 41 */       if (topic == null) {
/* 42 */         return FrontUtils.pageNotFound(request, response, model);
/*    */       }
/* 44 */       model.addAttribute("topic", topic);
/* 45 */       String tpl = topic.getTplContent();
/* 46 */       if (StringUtils.isBlank(tpl)) {
/* 47 */         tpl = FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 48 */           "topic", "tpl.topicDefault");
/*    */       }
/* 50 */       return tpl;
/* 51 */     }if (channelId != null) {
/* 52 */       Channel channel = this.channelMng.findById(channelId);
/* 53 */       if (channel != null)
/* 54 */         model.addAttribute("channel", channel);
/*    */       else {
/* 56 */         return FrontUtils.pageNotFound(request, response, model);
/*    */       }
/* 58 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 59 */         "topic", "tpl.topicChannel");
/*    */     }
/* 61 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 62 */       "topic", "tpl.topicIndex");
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/topic/{id}.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String topic(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 69 */     CmsSite site = CmsUtils.getSite(request);
/* 70 */     if (id == null) {
/* 71 */       return FrontUtils.pageNotFound(request, response, model);
/*    */     }
/* 73 */     CmsTopic topic = this.cmsTopicMng.findById(id);
/* 74 */     if (topic == null) {
/* 75 */       return FrontUtils.pageNotFound(request, response, model);
/*    */     }
/* 77 */     model.addAttribute("topic", topic);
/* 78 */     String tpl = topic.getTplContent();
/* 79 */     if (StringUtils.isBlank(tpl)) {
/* 80 */       tpl = FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 81 */         "topic", "tpl.topicDefault");
/*    */     }
/* 83 */     FrontUtils.frontData(request, model, site);
/* 84 */     FrontUtils.frontPageData(request, model);
/* 85 */     return tpl;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.TopicAct
 * JD-Core Version:    0.6.0
 */