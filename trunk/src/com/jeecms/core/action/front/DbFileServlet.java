/*    */ package com.jeecms.core.action.front;
/*    */ 
/*    */ import com.jeecms.core.entity.DbFile;
/*    */ import com.jeecms.core.manager.DbFileMng;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.BeanFactoryUtils;
/*    */ import org.springframework.web.context.WebApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ 
/*    */ public class DbFileServlet extends HttpServlet
/*    */ {
/*    */   public static final String PARAM_NAME = "n";
/*    */   private DbFileMng dbFileMng;
/*    */ 
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 29 */     String name = request.getParameter("n");
/* 30 */     if (StringUtils.isBlank(name)) {
/* 31 */       response.sendError(404);
/* 32 */       return;
/*    */     }
/* 34 */     DbFile file = this.dbFileMng.findById(name);
/* 35 */     if (file == null) {
/* 36 */       response.sendError(404);
/* 37 */       return;
/*    */     }
/* 39 */     String mimeType = getServletContext().getMimeType(name);
/* 40 */     if (mimeType != null) {
/* 41 */       response.setContentType(mimeType);
/*    */     }
/* 43 */     String filename = file.getId();
/* 44 */     int index = filename.lastIndexOf("/");
/* 45 */     if (index != -1) {
/* 46 */       filename = filename.substring(index + 1);
/*    */     }
/* 48 */     response.addHeader("Content-disposition", "filename=" + filename);
/* 49 */     response.setContentLength(file.getLength().intValue());
/* 50 */     ServletOutputStream out = response.getOutputStream();
/* 51 */     out.write(file.getContent());
/* 52 */     out.flush();
/* 53 */     out.close();
/*    */   }
/*    */ 
/*    */   public void init() throws ServletException
/*    */   {
/* 58 */     WebApplicationContext appCtx = 
/* 59 */       WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/* 60 */     this.dbFileMng = 
/* 61 */       ((DbFileMng)BeanFactoryUtils.beanOfTypeIncludingAncestors(appCtx, 
/* 61 */       DbFileMng.class));
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.action.front.DbFileServlet
 * JD-Core Version:    0.6.0
 */