/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsConfig;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.entity.main.ContentAttachment;
/*    */ import com.jeecms.cms.manager.main.ContentCountMng;
/*    */ import com.jeecms.cms.manager.main.ContentMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.common.security.encoder.PwdEncoder;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONArray;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class AttachmentAct
/*    */ {
/* 30 */   private static final Logger log = LoggerFactory.getLogger(AttachmentAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private ContentMng contentMng;
/*    */ 
/*    */   @Autowired
/*    */   private ContentCountMng contentCountMng;
/*    */ 
/*    */   @Autowired
/*    */   private PwdEncoder pwdEncoder;
/*    */ 
/* 36 */   @RequestMapping(value={"/attachment.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void attachment(Integer cid, Integer i, Long t, String k, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException { CmsConfig config = CmsUtils.getSite(request).getConfig();
/* 37 */     String code = config.getDownloadCode();
/* 38 */     int h = config.getDownloadTime().intValue() * 60 * 60 * 1000;
/* 39 */     if (this.pwdEncoder.isPasswordValid(k, cid + ";" + i + ";" + t, code)) {
/* 40 */       long curr = System.currentTimeMillis();
/* 41 */       if (t.longValue() + h > curr) {
/* 42 */         Content c = this.contentMng.findById(cid);
/* 43 */         if (c != null) {
/* 44 */           List list = c.getAttachments();
/* 45 */           if (list.size() > i.intValue()) {
/* 46 */             this.contentCountMng.downloadCount(c.getId());
/* 47 */             ContentAttachment ca = (ContentAttachment)list.get(i.intValue());
/* 48 */             response.sendRedirect(ca.getPath());
/* 49 */             return;
/*    */           }
/* 51 */           log.info("download index is out of range: {}", i);
/*    */         }
/*    */         else {
/* 54 */           log.info("Content not found: {}", cid);
/*    */         }
/*    */       } else {
/* 57 */         log.info("download time is expired!");
/*    */       }
/*    */     } else {
/* 60 */       log.info("download key error!");
/*    */     }
/* 62 */     response.setStatus(404);
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/attachment_url.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public void url(Integer cid, Integer n, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 76 */     if ((cid == null) || (n == null)) {
/* 77 */       return;
/*    */     }
/* 79 */     CmsConfig config = CmsUtils.getSite(request).getConfig();
/* 80 */     String code = config.getDownloadCode();
/* 81 */     long t = System.currentTimeMillis();
/* 82 */     JSONArray arr = new JSONArray();
/*    */ 
/* 84 */     for (int i = 0; i < n.intValue(); i++) {
/* 85 */       String key = this.pwdEncoder.encodePassword(cid + ";" + i + ";" + t, code);
/* 86 */       arr.put("&t=" + t + "&k=" + key);
/*    */     }
/* 88 */     ResponseUtils.renderText(response, arr.toString());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.AttachmentAct
 * JD-Core Version:    0.6.0
 */