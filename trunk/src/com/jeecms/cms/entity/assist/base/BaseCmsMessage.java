/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsMessage
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsMessage";
/*  18 */   public static String PROP_MSG_STATUS = "msgStatus";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_MSG_SEND_USER = "msgSendUser";
/*  21 */   public static String PROP_MSG_CONTENT = "msgContent";
/*  22 */   public static String PROP_MSG_BOX = "msgBox";
/*  23 */   public static String PROP_SEND_TIME = "sendTime";
/*  24 */   public static String PROP_ID = "id";
/*  25 */   public static String PROP_MSG_RECEIVER_USER = "msgReceiverUser";
/*  26 */   public static String PROP_MSG_TITLE = "msgTitle";
/*     */ 
/*  68 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String msgTitle;
/*     */   private String msgContent;
/*     */   private Date sendTime;
/*     */   private Boolean msgStatus;
/*     */   private Integer msgBox;
/*     */   private CmsUser msgReceiverUser;
/*     */   private CmsUser msgSendUser;
/*     */   private CmsSite site;
/*     */   private Set<CmsReceiverMessage> receiverMsgs;
/*     */ 
/*     */   public BaseCmsMessage()
/*     */   {
/*  31 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsMessage(Integer id)
/*     */   {
/*  38 */     setId(id);
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsMessage(Integer id, CmsUser msgReceiverUser, CmsUser msgSendUser, CmsSite site, String msgTitle, Boolean msgStatus, Integer msgBox)
/*     */   {
/*  54 */     setId(id);
/*  55 */     setMsgReceiverUser(msgReceiverUser);
/*  56 */     setMsgSendUser(msgSendUser);
/*  57 */     setSite(site);
/*  58 */     setMsgTitle(msgTitle);
/*  59 */     setMsgStatus(msgStatus);
/*  60 */     setMsgBox(msgBox);
/*  61 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  97 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 105 */     this.id = id;
/* 106 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getMsgTitle()
/*     */   {
/* 116 */     return this.msgTitle;
/*     */   }
/*     */ 
/*     */   public void setMsgTitle(String msgTitle)
/*     */   {
/* 124 */     this.msgTitle = msgTitle;
/*     */   }
/*     */ 
/*     */   public String getMsgContent()
/*     */   {
/* 132 */     return this.msgContent;
/*     */   }
/*     */ 
/*     */   public void setMsgContent(String msgContent)
/*     */   {
/* 140 */     this.msgContent = msgContent;
/*     */   }
/*     */ 
/*     */   public Date getSendTime()
/*     */   {
/* 148 */     return this.sendTime;
/*     */   }
/*     */ 
/*     */   public void setSendTime(Date sendTime)
/*     */   {
/* 156 */     this.sendTime = sendTime;
/*     */   }
/*     */ 
/*     */   public Boolean getMsgStatus()
/*     */   {
/* 164 */     return this.msgStatus;
/*     */   }
/*     */ 
/*     */   public void setMsgStatus(Boolean msgStatus)
/*     */   {
/* 172 */     this.msgStatus = msgStatus;
/*     */   }
/*     */ 
/*     */   public Integer getMsgBox()
/*     */   {
/* 180 */     return this.msgBox;
/*     */   }
/*     */ 
/*     */   public void setMsgBox(Integer msgBox)
/*     */   {
/* 188 */     this.msgBox = msgBox;
/*     */   }
/*     */ 
/*     */   public CmsUser getMsgReceiverUser()
/*     */   {
/* 196 */     return this.msgReceiverUser;
/*     */   }
/*     */ 
/*     */   public void setMsgReceiverUser(CmsUser msgReceiverUser)
/*     */   {
/* 204 */     this.msgReceiverUser = msgReceiverUser;
/*     */   }
/*     */ 
/*     */   public CmsUser getMsgSendUser()
/*     */   {
/* 212 */     return this.msgSendUser;
/*     */   }
/*     */ 
/*     */   public void setMsgSendUser(CmsUser msgSendUser)
/*     */   {
/* 220 */     this.msgSendUser = msgSendUser;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 228 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 236 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Set<CmsReceiverMessage> getReceiverMsgs() {
/* 240 */     return this.receiverMsgs;
/*     */   }
/*     */ 
/*     */   public void setReceiverMsgs(Set<CmsReceiverMessage> receiverMsgs)
/*     */   {
/* 245 */     this.receiverMsgs = receiverMsgs;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj) {
/* 249 */     if (obj == null) return false;
/* 250 */     if (!(obj instanceof CmsMessage)) return false;
/*     */ 
/* 252 */     CmsMessage cmsMessage = (CmsMessage)obj;
/* 253 */     if ((getId() == null) || (cmsMessage.getId() == null)) return false;
/* 254 */     return getId().equals(cmsMessage.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 259 */     if (-2147483648 == this.hashCode) {
/* 260 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 262 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 263 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 266 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 271 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsMessage
 * JD-Core Version:    0.6.0
 */