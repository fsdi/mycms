/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsGuestbookExt;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsGuestbookExt extends BaseCmsGuestbookExt
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 11 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 15 */     if (StringUtils.isBlank(getContent())) {
/* 16 */       setContent(null);
/*    */     }
/* 18 */     if (StringUtils.isBlank(getReply())) {
/* 19 */       setReply(null);
/*    */     }
/* 21 */     if (StringUtils.isBlank(getTitle())) {
/* 22 */       setTitle(null);
/*    */     }
/* 24 */     if (StringUtils.isBlank(getEmail())) {
/* 25 */       setEmail(null);
/*    */     }
/* 27 */     if (StringUtils.isBlank(getPhone())) {
/* 28 */       setPhone(null);
/*    */     }
/* 30 */     if (StringUtils.isBlank(getQq()))
/* 31 */       setQq(null);
/*    */   }
/*    */ 
/*    */   public CmsGuestbookExt()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsGuestbookExt(Integer id)
/*    */   {
/* 44 */     super(id);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsGuestbookExt
 * JD-Core Version:    0.6.0
 */