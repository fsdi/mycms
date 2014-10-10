/*    */ package com.jeecms.cms;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.RequestDispatcher;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class UpdateServlet extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 18 */     String dbHost = request.getParameter("dbHost");
/* 19 */     String dbPort = request.getParameter("dbPort");
/* 20 */     String dbName = request.getParameter("dbName");
/* 21 */     String dbUser = request.getParameter("dbUser");
/* 22 */     String dbPassword = request.getParameter("dbPassword");
/*    */ 
/* 25 */     String domain = request.getParameter("domain");
/* 26 */     String cxtPath = request.getParameter("cxtPath");
/* 27 */     String port = request.getParameter("port");
/*    */ 
/* 29 */     String dbFileName = request.getParameter("dbFileName");
/* 30 */     String dbXmlFileName = "/WEB-INF/config/jdbc.properties";
/* 31 */     String webXmlFrom = "/update/config/web.xml";
/* 32 */     String webXmlTo = "/WEB-INF/web.xml";
/*    */     try
/*    */     {
/* 35 */       String sqlPath = getServletContext().getRealPath(dbFileName);
/* 36 */       List sqlList = Install.readSql(sqlPath);
/* 37 */       Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword, 
/* 38 */         sqlList);
/*    */ 
/* 40 */       Install.updateConfig(dbHost, dbPort, dbName, dbUser, dbPassword, 
/* 41 */         domain, cxtPath, port);
/*    */ 
/* 43 */       String dbXmlPath = getServletContext().getRealPath(dbXmlFileName);
/*    */ 
/* 45 */       Install.dbXml(dbXmlPath, dbHost, dbPort, dbName, dbUser, 
/* 46 */         dbPassword);
/*    */ 
/* 48 */       String webXmlFromPath = getServletContext().getRealPath(webXmlFrom);
/* 49 */       String webXmlToPath = getServletContext().getRealPath(webXmlTo);
/* 50 */       Install.webXml(webXmlFromPath, webXmlToPath);
/*    */     } catch (Exception e) {
/* 52 */       throw new ServletException("update failed!", e);
/*    */     }
/* 54 */     RequestDispatcher dispatcher = request
/* 55 */       .getRequestDispatcher("/update/update_setup.jsp");
/* 56 */     dispatcher.forward(request, response);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.UpdateServlet
 * JD-Core Version:    0.6.0
 */