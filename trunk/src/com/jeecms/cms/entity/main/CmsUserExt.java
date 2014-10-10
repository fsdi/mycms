/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsUserExt;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsUserExt extends BaseCmsUserExt
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void blankToNull()
/*    */   {
/* 12 */     if (StringUtils.isBlank(getRealname())) {
/* 13 */       setRealname(null);
/*    */     }
/* 15 */     if (StringUtils.isBlank(getIntro())) {
/* 16 */       setIntro(null);
/*    */     }
/* 18 */     if (StringUtils.isBlank(getComefrom())) {
/* 19 */       setComefrom(null);
/*    */     }
/* 21 */     if (StringUtils.isBlank(getMobile())) {
/* 22 */       setMobile(null);
/*    */     }
/* 24 */     if (StringUtils.isBlank(getPhone())) {
/* 25 */       setPhone(null);
/*    */     }
/* 27 */     if (StringUtils.isBlank(getMsn())) {
/* 28 */       setMsn(null);
/*    */     }
/* 30 */     if (StringUtils.isBlank(getQq()))
/* 31 */       setQq(null);
/*    */   }
/*    */ 
/*    */   public CmsUserExt()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsUserExt(Integer id)
/*    */   {
/* 44 */     super(id);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsUserExt
 * JD-Core Version:    0.6.0
 */