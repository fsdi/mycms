/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsMessage;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.common.util.StrUtils;
/*    */ 
/*    */ public class CmsMessage extends BaseCmsMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsMessage()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsMessage(Integer id)
/*    */   {
/* 20 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsMessage(Integer id, CmsUser msgReceiverUser, CmsUser msgSendUser, CmsSite site, String msgTitle, Boolean msgStatus, Integer msgBox)
/*    */   {
/* 42 */     super(id, 
/* 37 */       msgReceiverUser, 
/* 38 */       msgSendUser, 
/* 39 */       site, 
/* 40 */       msgTitle, 
/* 41 */       msgStatus, 
/* 42 */       msgBox);
/*    */   }
/*    */   public String getTitleHtml() {
/* 45 */     return StrUtils.txt2htm(getMsgTitle());
/*    */   }
/*    */   public String getContentHtml() {
/* 48 */     return StrUtils.txt2htm(getMsgContent());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsMessage
 * JD-Core Version:    0.6.0
 */