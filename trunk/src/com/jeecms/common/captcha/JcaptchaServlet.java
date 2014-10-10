/*    */ package com.jeecms.common.captcha;
/*    */ 
/*    */ import com.jeecms.common.web.session.SessionProvider;
/*    */ import com.octo.captcha.service.CaptchaServiceException;
/*    */ import com.octo.captcha.service.image.ImageCaptchaService;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.io.output.ByteArrayOutputStream;
/*    */ import org.springframework.beans.factory.BeanFactoryUtils;
/*    */ import org.springframework.web.context.WebApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ 
/*    */ public class JcaptchaServlet extends HttpServlet
/*    */ {
/*    */   public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
/*    */   private ImageCaptchaService captchaService;
/*    */   private SessionProvider session;
/*    */ 
/*    */   public void init()
/*    */     throws ServletException
/*    */   {
/* 34 */     WebApplicationContext appCtx = 
/* 35 */       WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/* 36 */     this.captchaService = 
/* 37 */       ((ImageCaptchaService)BeanFactoryUtils.beanOfTypeIncludingAncestors(appCtx, ImageCaptchaService.class));
/* 38 */     this.session = 
/* 39 */       ((SessionProvider)BeanFactoryUtils.beanOfTypeIncludingAncestors(appCtx, SessionProvider.class));
/*    */   }
/*    */ 
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 45 */     byte[] captchaChallengeAsJpeg = (byte[])null;
/*    */ 
/* 47 */     ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
/*    */     try
/*    */     {
/* 53 */       String captchaId = this.session.getSessionId(request, response);
/* 54 */       BufferedImage challenge = this.captchaService.getImageChallengeForID(
/* 55 */         captchaId, request.getLocale());
/*    */ 
/* 57 */       ImageIO.write(challenge, "jpeg", jpegOutputStream);
/*    */     } catch (IllegalArgumentException e) {
/* 59 */       response.sendError(404);
/* 60 */       return;
/*    */     } catch (CaptchaServiceException e) {
/* 62 */       response.sendError(500);
/* 63 */       return;
/*    */     }
/*    */ 
/* 70 */     captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
/*    */ 
/* 73 */     response.setHeader("Cache-Control", "no-store");
/* 74 */     response.setHeader("Pragma", "no-cache");
/* 75 */     response.setDateHeader("Expires", 0L);
/* 76 */     response.setContentType("image/jpeg");
/*    */ 
/* 78 */     ServletOutputStream responseOutputStream = response.getOutputStream();
/* 79 */     responseOutputStream.write(captchaChallengeAsJpeg);
/* 80 */     responseOutputStream.flush();
/* 81 */     responseOutputStream.close();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.captcha.JcaptchaServlet
 * JD-Core Version:    0.6.0
 */