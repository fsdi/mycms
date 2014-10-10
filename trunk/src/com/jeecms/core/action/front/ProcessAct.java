/*    */ package com.jeecms.core.action.front;
/*    */ 
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import com.jeecms.common.web.session.SessionProvider;
/*    */ import com.jeecms.core.entity.Authentication;
/*    */ import com.jeecms.core.manager.AuthenticationMng;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ProcessAct
/*    */ {
/* 27 */   private static Logger log = LoggerFactory.getLogger(ProcessAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private AuthenticationMng authMng;
/*    */ 
/*    */   @Autowired
/*    */   private SessionProvider session;
/*    */ 
/* 32 */   @RequestMapping(value={"/process.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String process(HttpServletRequest request, HttpServletResponse response) { String returnUrl = RequestUtils.getQueryParam(request, 
/* 33 */       "returnUrl");
/* 34 */     String authId = RequestUtils.getQueryParam(request, "auth_key");
/* 35 */     Authentication auth = this.authMng.retrieve(authId);
/* 36 */     if (auth != null)
/* 37 */       this.authMng.storeAuthIdToSession(this.session, request, response, 
/* 38 */         auth.getId());
/*    */     else {
/* 40 */       log.warn("Authentication id not found: {}", authId);
/*    */     }
/* 42 */     return "redirect:" + returnUrl;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.action.front.ProcessAct
 * JD-Core Version:    0.6.0
 */