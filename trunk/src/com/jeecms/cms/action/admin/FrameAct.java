/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class FrameAct
/*     */ {
/*     */   @RequestMapping({"/frame/config_main.do"})
/*     */   public String configMain(ModelMap model)
/*     */   {
/*  11 */     return "frame/config_main";
/*     */   }
/*     */   @RequestMapping({"/frame/config_left.do"})
/*     */   public String configLeft(ModelMap model) {
/*  16 */     return "frame/config_left";
/*     */   }
/*     */   @RequestMapping({"/frame/config_right.do"})
/*     */   public String configRight(ModelMap model) {
/*  21 */     return "frame/config_right";
/*     */   }
/*     */   @RequestMapping({"/frame/user_main.do"})
/*     */   public String userMain(ModelMap model) {
/*  26 */     return "frame/user_main";
/*     */   }
/*     */   @RequestMapping({"/frame/user_left.do"})
/*     */   public String userLeft(ModelMap model) {
/*  31 */     return "frame/user_left";
/*     */   }
/*     */   @RequestMapping({"/frame/user_right.do"})
/*     */   public String userRight(ModelMap model) {
/*  36 */     return "frame/user_right";
/*     */   }
/*     */   @RequestMapping({"/frame/generate_main.do"})
/*     */   public String generateMain(ModelMap model) {
/*  41 */     return "frame/generate_main";
/*     */   }
/*     */   @RequestMapping({"/frame/generate_left.do"})
/*     */   public String generateLeft(ModelMap model) {
/*  46 */     return "frame/generate_left";
/*     */   }
/*     */   @RequestMapping({"/frame/generate_right.do"})
/*     */   public String generateRight(ModelMap model) {
/*  51 */     return "frame/generate_right";
/*     */   }
/*     */   @RequestMapping({"/frame/maintain_main.do"})
/*     */   public String maintainMain(ModelMap model) {
/*  56 */     return "frame/maintain_main";
/*     */   }
/*     */   @RequestMapping({"/frame/maintain_left.do"})
/*     */   public String maintainLeft(ModelMap model) {
/*  61 */     return "frame/maintain_left";
/*     */   }
/*     */   @RequestMapping({"/frame/maintain_right.do"})
/*     */   public String maintainRight(ModelMap model) {
/*  66 */     return "frame/maintain_right";
/*     */   }
/*     */   @RequestMapping({"/frame/assistant_main.do"})
/*     */   public String assistantMain(ModelMap model) {
/*  71 */     return "frame/assistant_main";
/*     */   }
/*     */   @RequestMapping({"/frame/assistant_left.do"})
/*     */   public String assistantLeft(ModelMap model) {
/*  76 */     return "frame/assistant_left";
/*     */   }
/*     */   @RequestMapping({"/frame/assistant_right.do"})
/*     */   public String assistantRight(ModelMap model) {
/*  81 */     return "frame/assistant_right";
/*     */   }
/*     */   @RequestMapping({"/frame/template_main.do"})
/*     */   public String templateMain(ModelMap model) {
/*  86 */     return "frame/template_main";
/*     */   }
/*     */   @RequestMapping({"/frame/resource_main.do"})
/*     */   public String resourceMain(ModelMap model) {
/*  91 */     return "frame/resource_main";
/*     */   }
/*     */   @RequestMapping({"/frame/channel_main.do"})
/*     */   public String channelMain(ModelMap model) {
/*  96 */     return "frame/channel_main";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/frame/doctype_main.do"})
/*     */   public String doctypeMain(ModelMap model) {
/* 102 */     return "frame/doctype_main";
/*     */   }
/*     */   @RequestMapping({"/frame/doc_main.do"})
/*     */   public String docMain(ModelMap model) {
/* 107 */     return "frame/doc_main";
/*     */   }
/*     */   @RequestMapping({"/frame/content_main.do"})
/*     */   public String contentMain(ModelMap model) {
/* 112 */     return "frame/content_main";
/*     */   }
/*     */   @RequestMapping({"/frame/statistic_main.do"})
/*     */   public String statisticMain(ModelMap model) {
/* 117 */     return "frame/statistic_main";
/*     */   }
/*     */   @RequestMapping({"/frame/statistic_left.do"})
/*     */   public String statisticLeft() {
/* 122 */     return "frame/statistic_left";
/*     */   }
/*     */   @RequestMapping({"/frame/statistic_right.do"})
/*     */   public String statisticRight() {
/* 127 */     return "frame/statistic_right";
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.FrameAct
 * JD-Core Version:    0.6.0
 */