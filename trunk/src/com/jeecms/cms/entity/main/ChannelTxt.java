/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseChannelTxt;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ChannelTxt extends BaseChannelTxt
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 11 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 15 */     if (StringUtils.isBlank(getTxt())) {
/* 16 */       setTxt(null);
/*    */     }
/* 18 */     if (StringUtils.isBlank(getTxt1())) {
/* 19 */       setTxt1(null);
/*    */     }
/* 21 */     if (StringUtils.isBlank(getTxt2())) {
/* 22 */       setTxt2(null);
/*    */     }
/* 24 */     if (StringUtils.isBlank(getTxt3()))
/* 25 */       setTxt3(null);
/*    */   }
/*    */ 
/*    */   public boolean isAllBlank()
/*    */   {
/* 37 */     return (StringUtils.isBlank(getTxt())) && (StringUtils.isBlank(getTxt1())) && 
/* 36 */       (StringUtils.isBlank(getTxt2())) && 
/* 37 */       (StringUtils.isBlank(getTxt3()));
/*    */   }
/*    */ 
/*    */   public ChannelTxt()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ChannelTxt(Integer id)
/*    */   {
/* 49 */     super(id);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ChannelTxt
 * JD-Core Version:    0.6.0
 */