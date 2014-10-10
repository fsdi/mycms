/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsReceiverMessage
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsReceiverMessage";
/*  18 */   public static String PROP_MSG_STATUS = "msgStatus";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_MESSAGE = "message";
/*  21 */   public static String PROP_MSG_SEND_USER = "msgSendUser";
/*  22 */   public static String PROP_MSG_CONTENT = "msgContent";
/*  23 */   public static String PROP_MSG_BOX = "msgBox";
/*  24 */   public static String PROP_SEND_TIME = "sendTime";
/*  25 */   public static String PROP_ID = "id";
/*  26 */   public static String PROP_MSG_RECEIVER_USER = "msgReceiverUser";
/*  27 */   public static String PROP_MSG_TITLE = "msgTitle";
/*     */ 
/*  73 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String msgTitle;
/*     */   private String msgContent;
/*     */   private Date sendTime;
/*     */   private boolean msgStatus;
/*     */   private Integer msgBox;
/*     */   private CmsUser msgReceiverUser;
/*     */   private CmsUser msgSendUser;
/*     */   private CmsSite site;
/*     */   private CmsMessage message;
/*     */ 
/*     */   public BaseCmsReceiverMessage()
/*     */   {
/*  32 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsReceiverMessage(Integer id)
/*     */   {
/*  39 */     setId(id);
/*  40 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsReceiverMessage(Integer id, CmsUser msgReceiverUser, CmsUser msgSendUser, CmsSite site, String msgTitle, String msgContent, Date sendTime, boolean msgStatus, Integer msgBox)
/*     */   {
/*  57 */     setId(id);
/*  58 */     setMsgReceiverUser(msgReceiverUser);
/*  59 */     setMsgSendUser(msgSendUser);
/*  60 */     setSite(site);
/*  61 */     setMsgTitle(msgTitle);
/*  62 */     setMsgContent(msgContent);
/*  63 */     setSendTime(sendTime);
/*  64 */     setMsgStatus(msgStatus);
/*  65 */     setMsgBox(msgBox);
/*  66 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 102 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 110 */     this.id = id;
/* 111 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getMsgTitle()
/*     */   {
/* 121 */     return this.msgTitle;
/*     */   }
/*     */ 
/*     */   public void setMsgTitle(String msgTitle)
/*     */   {
/* 129 */     this.msgTitle = msgTitle;
/*     */   }
/*     */ 
/*     */   public String getMsgContent()
/*     */   {
/* 137 */     return this.msgContent;
/*     */   }
/*     */ 
/*     */   public void setMsgContent(String msgContent)
/*     */   {
/* 145 */     this.msgContent = msgContent;
/*     */   }
/*     */ 
/*     */   public Date getSendTime()
/*     */   {
/* 153 */     return this.sendTime;
/*     */   }
/*     */ 
/*     */   public void setSendTime(Date sendTime)
/*     */   {
/* 161 */     this.sendTime = sendTime;
/*     */   }
/*     */ 
/*     */   public boolean isMsgStatus()
/*     */   {
/* 169 */     return this.msgStatus;
/*     */   }
/*     */ 
/*     */   public void setMsgStatus(boolean msgStatus)
/*     */   {
/* 177 */     this.msgStatus = msgStatus;
/*     */   }
/*     */ 
/*     */   public Integer getMsgBox()
/*     */   {
/* 185 */     return this.msgBox;
/*     */   }
/*     */ 
/*     */   public void setMsgBox(Integer msgBox)
/*     */   {
/* 193 */     this.msgBox = msgBox;
/*     */   }
/*     */ 
/*     */   public CmsMessage getMessage()
/*     */   {
/* 201 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(CmsMessage message)
/*     */   {
/* 209 */     this.message = message;
/*     */   }
/*     */ 
/*     */   public CmsUser getMsgReceiverUser()
/*     */   {
/* 217 */     return this.msgReceiverUser;
/*     */   }
/*     */ 
/*     */   public void setMsgReceiverUser(CmsUser msgReceiverUser)
/*     */   {
/* 225 */     this.msgReceiverUser = msgReceiverUser;
/*     */   }
/*     */ 
/*     */   public CmsUser getMsgSendUser()
/*     */   {
/* 233 */     return this.msgSendUser;
/*     */   }
/*     */ 
/*     */   public void setMsgSendUser(CmsUser msgSendUser)
/*     */   {
/* 241 */     this.msgSendUser = msgSendUser;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 249 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 257 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 263 */     if (obj == null) return false;
/* 264 */     if (!(obj instanceof CmsReceiverMessage)) return false;
/*     */ 
/* 266 */     CmsReceiverMessage cmsReceiverMessage = (CmsReceiverMessage)obj;
/* 267 */     if ((getId() == null) || (cmsReceiverMessage.getId() == null)) return false;
/* 268 */     return getId().equals(cmsReceiverMessage.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 273 */     if (-2147483648 == this.hashCode) {
/* 274 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 276 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 277 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 280 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 285 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsReceiverMessage
 * JD-Core Version:    0.6.0
 */