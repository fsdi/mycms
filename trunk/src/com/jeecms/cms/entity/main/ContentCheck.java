/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseContentCheck;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ContentCheck extends BaseContentCheck
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final byte DRAFT = 0;
/*    */   public static final byte CHECKING = 1;
/*    */   public static final byte CHECKED = 2;
/*    */   public static final byte RECYCLE = 3;
/*    */ 
/*    */   public void init()
/*    */   {
/* 27 */     byte zero = 0;
/* 28 */     if (getCheckStep() == null) {
/* 29 */       setCheckStep(Byte.valueOf(zero));
/*    */     }
/* 31 */     if (getRejected() == null)
/* 32 */       setRejected(Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */   public void blankToNull()
/*    */   {
/* 37 */     if (StringUtils.isBlank(getCheckOpinion()))
/* 38 */       setCheckOpinion(null);
/*    */   }
/*    */ 
/*    */   public ContentCheck()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ContentCheck(Integer id)
/*    */   {
/* 51 */     super(id);
/*    */   }
/*    */ 
/*    */   public ContentCheck(Integer id, Byte checkStep, Boolean rejected)
/*    */   {
/* 65 */     super(id, 
/* 64 */       checkStep, 
/* 65 */       rejected);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentCheck
 * JD-Core Version:    0.6.0
 */