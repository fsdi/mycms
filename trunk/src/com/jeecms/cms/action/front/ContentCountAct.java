/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.manager.main.ContentCountMng;
/*    */ import com.jeecms.cms.service.ContentCountCache;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ContentCountAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ContentCountCache contentCountCache;
/*    */ 
/*    */   @Autowired
/*    */   private ContentCountMng contentCountMng;
/*    */ 
/*    */   @RequestMapping(value={"/content_view.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void contentView(Integer contentId, HttpServletRequest request, HttpServletResponse response)
/*    */     throws JSONException
/*    */   {
/* 22 */     if (contentId == null) {
/* 23 */       ResponseUtils.renderJson(response, "[]");
/* 24 */       return;
/*    */     }
/* 26 */     int[] counts = this.contentCountCache.viewAndGet(contentId);
/*    */ 
/* 28 */     if (counts != null) {
/* 29 */       String json = new JSONArray(counts).toString();
/* 30 */       ResponseUtils.renderJson(response, json);
/*    */     } else {
/* 32 */       ResponseUtils.renderJson(response, "[]");
/*    */     }
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/content_up.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void contentUp(Integer contentId, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 39 */     if (contentId == null) {
/* 40 */       ResponseUtils.renderJson(response, "false");
/*    */     } else {
/* 42 */       this.contentCountMng.contentUp(contentId);
/* 43 */       ResponseUtils.renderJson(response, "true");
/*    */     }
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/content_down.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void contentDown(Integer contentId, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 50 */     if (contentId == null) {
/* 51 */       ResponseUtils.renderJson(response, "false");
/*    */     } else {
/* 53 */       this.contentCountMng.contentDown(contentId);
/* 54 */       ResponseUtils.renderJson(response, "true");
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.ContentCountAct
 * JD-Core Version:    0.6.0
 */