/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsReceiverMessage;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.common.util.StrUtils;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CmsReceiverMessage extends BaseCmsReceiverMessage
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsReceiverMessage()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage(Integer id)
/*    */   {
/* 18 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage(Integer id, CmsUser msgReceiverUser, CmsUser msgSendUser, CmsSite site, String msgTitle, String msgContent, Date sendTime, boolean msgStatus, Integer msgBox)
/*    */   {
/* 32 */     super(id, msgReceiverUser, msgSendUser, site, msgTitle, msgContent, 
/* 32 */       sendTime, msgStatus, msgBox);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage(CmsMessage message)
/*    */   {
/* 39 */     super(message.getId(), message.getMsgReceiverUser(), 
/* 37 */       message.getMsgSendUser(), message.getSite(), message.getMsgTitle(), 
/* 38 */       message.getMsgContent(), message.getSendTime(), 
/* 39 */       message.getMsgStatus().booleanValue(), message.getMsgBox());
/*    */   }
/*    */   public String getTitleHtml() {
/* 42 */     return StrUtils.txt2htm(getMsgTitle());
/*    */   }
/*    */   public String getContentHtml() {
/* 45 */     return StrUtils.txt2htm(getMsgContent());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsReceiverMessage
 * JD-Core Version:    0.6.0
 */