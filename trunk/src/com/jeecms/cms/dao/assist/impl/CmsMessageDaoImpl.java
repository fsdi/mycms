/*     */ package com.jeecms.cms.dao.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsMessageDao;
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class CmsMessageDaoImpl extends HibernateBaseDao<CmsMessage, Integer>
/*     */   implements CmsMessageDao
/*     */ {
/*     */   public Pagination getPage(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  24 */     String hql = " select msg from CmsMessage msg where 1=1 ";
/*  25 */     Finder finder = Finder.create(hql);
/*  26 */     if (siteId != null) {
/*  27 */       finder.append(" and msg.site.id=:siteId")
/*  28 */         .setParam("siteId", siteId);
/*     */     }
/*  30 */     if ((sendUserId != null) && (receiverUserId != null)) {
/*  31 */       finder.append(" and (msg.msgSendUser.id=:sendUserId or msg.msgReceiverUser.id=:receiverUserId)").setParam(
/*  32 */         "sendUserId", sendUserId).setParam("receiverUserId", receiverUserId);
/*     */     } else {
/*  34 */       if (sendUserId != null) {
/*  35 */         finder.append(" and msg.msgSendUser.id=:sendUserId").setParam(
/*  36 */           "sendUserId", sendUserId);
/*     */       }
/*  38 */       if (receiverUserId != null) {
/*  39 */         finder.append(" and msg.msgReceiverUser.id=:receiverUserId")
/*  40 */           .setParam("receiverUserId", receiverUserId);
/*     */       }
/*     */     }
/*     */ 
/*  44 */     if (StringUtils.isNotBlank(title)) {
/*  45 */       finder.append(" and msg.msgTitle like:title").setParam("title", 
/*  46 */         "%" + title + "%");
/*     */     }
/*  48 */     if (sendBeginTime != null) {
/*  49 */       finder.append(" and msg.sendTime >=:sendBeginTime").setParam(
/*  50 */         "sendBeginTime", sendBeginTime);
/*     */     }
/*  52 */     if (sendEndTime != null) {
/*  53 */       finder.append(" and msg.sendTime <=:sendEndTime").setParam(
/*  54 */         "sendEndTime", sendEndTime);
/*     */     }
/*  56 */     if (status != null) {
/*  57 */       if (status.booleanValue())
/*  58 */         finder.append(" and msg.msgStatus =true");
/*     */       else {
/*  60 */         finder.append(" and msg.msgStatus =false");
/*     */       }
/*     */     }
/*  63 */     if (box != null) {
/*  64 */       finder.append(" and msg.msgBox =:box").setParam("box", box);
/*     */     }
/*  66 */     finder.append(" order by msg.id desc");
/*  67 */     return find(finder, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public CmsMessage findById(Integer id) {
/*  71 */     return (CmsMessage)super.get(id);
/*     */   }
/*     */ 
/*     */   public CmsMessage save(CmsMessage bean) {
/*  75 */     getSession().save(bean);
/*  76 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsMessage update(CmsMessage bean) {
/*  80 */     getSession().update(bean);
/*  81 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsMessage deleteById(Integer id) {
/*  85 */     CmsMessage entity = (CmsMessage)super.get(id);
/*  86 */     if (entity != null) {
/*  87 */       getSession().delete(entity);
/*     */     }
/*  89 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsMessage[] deleteByIds(Integer[] ids) {
/*  93 */     CmsMessage[] messages = new CmsMessage[ids.length];
/*  94 */     for (int i = 0; i < ids.length; i++) {
/*  95 */       messages[i] = ((CmsMessage)get(ids[i]));
/*  96 */       deleteById(ids[i]);
/*     */     }
/*  98 */     return messages;
/*     */   }
/*     */ 
/*     */   protected Class<CmsMessage> getEntityClass()
/*     */   {
/* 103 */     return CmsMessage.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsMessageDaoImpl
 * JD-Core Version:    0.6.0
 */