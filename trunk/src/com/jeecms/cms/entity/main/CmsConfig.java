/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsConfig;
/*    */ import java.util.Date;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsConfig extends BaseCmsConfig
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final String VERSION = "version";
/*    */ 
/*    */   public String getVersion()
/*    */   {
/* 12 */     return (String)getAttr().get("version");
/*    */   }
/*    */ 
/*    */   public MemberConfig getMemberConfig() {
/* 16 */     MemberConfig memberConfig = new MemberConfig(getAttr());
/* 17 */     return memberConfig;
/*    */   }
/*    */ 
/*    */   public void setMemberConfig(MemberConfig memberConfig) {
/* 21 */     getAttr().putAll(memberConfig.getAttr());
/*    */   }
/*    */ 
/*    */   public void blankToNull()
/*    */   {
/* 26 */     if (StringUtils.isBlank(getProcessUrl())) {
/* 27 */       setProcessUrl(null);
/*    */     }
/* 29 */     if (StringUtils.isBlank(getContextPath())) {
/* 30 */       setContextPath(null);
/*    */     }
/* 32 */     if (StringUtils.isBlank(getServletPoint()))
/* 33 */       setServletPoint(null);
/*    */   }
/*    */ 
/*    */   public CmsConfig()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsConfig(Integer id)
/*    */   {
/* 46 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsConfig(Integer id, String dbFileUri, Boolean uploadToDb, String defImg, String loginUrl, Date countClearTime, Date countCopyTime, String downloadCode, Integer downloadTime)
/*    */   {
/* 59 */     super(id, dbFileUri, uploadToDb, defImg, loginUrl, countClearTime, 
/* 59 */       countCopyTime, downloadCode, downloadTime);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsConfig
 * JD-Core Version:    0.6.0
 */