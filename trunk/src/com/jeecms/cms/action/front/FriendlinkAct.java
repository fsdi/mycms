/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkMng;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class FriendlinkAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsFriendlinkMng cmsFriendlinkMng;
/*    */ 
/*    */   @RequestMapping(value={"/friendlink_view.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void view(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 26 */     if (id != null) {
/* 27 */       this.cmsFriendlinkMng.updateViews(id);
/* 28 */       ResponseUtils.renderJson(response, "true");
/*    */     } else {
/* 30 */       ResponseUtils.renderJson(response, "false");
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.FriendlinkAct
 * JD-Core Version:    0.6.0
 */