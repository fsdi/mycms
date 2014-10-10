/*     */ package com.jeecms.cms.dao.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsReceiverMessageDao;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class CmsReceiverMessageDaoImpl extends HibernateBaseDao<CmsReceiverMessage, Integer>
/*     */   implements CmsReceiverMessageDao
/*     */ {
/*     */   public Pagination getPage(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  26 */     String hql = " select msg from CmsReceiverMessage msg where 1=1 ";
/*  27 */     Finder finder = Finder.create(hql);
/*  28 */     if (siteId != null) {
/*  29 */       finder.append(" and msg.site.id=:siteId")
/*  30 */         .setParam("siteId", siteId);
/*     */     }
/*     */ 
/*  33 */     if ((sendUserId != null) && (receiverUserId != null)) {
/*  34 */       finder
/*  35 */         .append(
/*  36 */         " and ((msg.msgReceiverUser.id=:receiverUserId  and msg.msgBox =:box) or (msg.msgSendUser.id=:sendUserId  and msg.msgBox =:box) )")
/*  37 */         .setParam("sendUserId", sendUserId).setParam(
/*  38 */         "receiverUserId", receiverUserId).setParam("box", 
/*  39 */         box);
/*     */     } else {
/*  41 */       if (sendUserId != null) {
/*  42 */         finder.append(" and msg.msgSendUser.id=:sendUserId").setParam(
/*  43 */           "sendUserId", sendUserId);
/*     */       }
/*  45 */       if (receiverUserId != null) {
/*  46 */         finder.append(" and msg.msgReceiverUser.id=:receiverUserId")
/*  47 */           .setParam("receiverUserId", receiverUserId);
/*     */       }
/*  49 */       if (box != null) {
/*  50 */         finder.append(" and msg.msgBox =:box").setParam("box", box);
/*     */       }
/*     */     }
/*  53 */     if (StringUtils.isNotBlank(title)) {
/*  54 */       finder.append(" and msg.msgTitle like:title").setParam("title", 
/*  55 */         "%" + title + "%");
/*     */     }
/*  57 */     if (sendBeginTime != null) {
/*  58 */       finder.append(" and msg.sendTime >=:sendBeginTime").setParam(
/*  59 */         "sendBeginTime", sendBeginTime);
/*     */     }
/*  61 */     if (sendEndTime != null) {
/*  62 */       finder.append(" and msg.sendTime <=:sendEndTime").setParam(
/*  63 */         "sendEndTime", sendEndTime);
/*     */     }
/*  65 */     if (status != null) {
/*  66 */       if (status.booleanValue())
/*  67 */         finder.append(" and msg.msgStatus =true");
/*     */       else {
/*  69 */         finder.append(" and msg.msgStatus =false");
/*     */       }
/*     */     }
/*  72 */     finder.append(" order by msg.id desc");
/*     */ 
/*  74 */     return find(finder, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<CmsReceiverMessage> getList(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable)
/*     */   {
/*  82 */     String hql = " select msg from CmsReceiverMessage msg where 1=1  ";
/*  83 */     Finder finder = Finder.create(hql);
/*  84 */     if (siteId != null) {
/*  85 */       finder.append(" and msg.site.id=:siteId")
/*  86 */         .setParam("siteId", siteId);
/*     */     }
/*     */ 
/*  89 */     if ((sendUserId != null) && (receiverUserId != null)) {
/*  90 */       finder
/*  91 */         .append(
/*  92 */         " and ((msg.msgReceiverUser.id=:receiverUserId  and msg.msgBox =:box) or (msg.msgSendUser.id=:sendUserId  and msg.msgBox =:box) )")
/*  93 */         .setParam("sendUserId", sendUserId).setParam(
/*  94 */         "receiverUserId", receiverUserId).setParam("box", 
/*  95 */         box);
/*     */     } else {
/*  97 */       if (sendUserId != null) {
/*  98 */         finder.append(" and msg.msgSendUser.id=:sendUserId").setParam(
/*  99 */           "sendUserId", sendUserId);
/*     */       }
/* 101 */       if (receiverUserId != null) {
/* 102 */         finder.append(" and msg.msgReceiverUser.id=:receiverUserId")
/* 103 */           .setParam("receiverUserId", receiverUserId);
/*     */       }
/* 105 */       if (box != null) {
/* 106 */         finder.append(" and msg.msgBox =:box").setParam("box", box);
/*     */       }
/*     */     }
/* 109 */     if (StringUtils.isNotBlank(title)) {
/* 110 */       finder.append(" and msg.msgTitle like:title").setParam("title", 
/* 111 */         "%" + title + "%");
/*     */     }
/* 113 */     if (sendBeginTime != null) {
/* 114 */       finder.append(" and msg.sendTime >=:sendBeginTime").setParam(
/* 115 */         "sendBeginTime", sendBeginTime);
/*     */     }
/* 117 */     if (sendEndTime != null) {
/* 118 */       finder.append(" and msg.sendTime <=:sendEndTime").setParam(
/* 119 */         "sendEndTime", sendEndTime);
/*     */     }
/* 121 */     if (status != null) {
/* 122 */       if (status.booleanValue())
/* 123 */         finder.append(" and msg.msgStatus =true");
/*     */       else {
/* 125 */         finder.append(" and msg.msgStatus =false");
/*     */       }
/*     */     }
/* 128 */     finder.append(" order by msg.id desc");
/* 129 */     return find(finder);
/*     */   }
/*     */ 
/*     */   public CmsReceiverMessage findById(Integer id) {
/* 133 */     return (CmsReceiverMessage)super.get(id);
/*     */   }
/*     */ 
/*     */   public CmsReceiverMessage save(CmsReceiverMessage bean) {
/* 137 */     getSession().save(bean);
/* 138 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsReceiverMessage update(CmsReceiverMessage bean) {
/* 142 */     getSession().update(bean);
/* 143 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsReceiverMessage deleteById(Integer id) {
/* 147 */     CmsReceiverMessage entity = (CmsReceiverMessage)super.get(id);
/* 148 */     if (entity != null) {
/* 149 */       getSession().delete(entity);
/*     */     }
/* 151 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsReceiverMessage[] deleteByIds(Integer[] ids) {
/* 155 */     CmsReceiverMessage[] messages = new CmsReceiverMessage[ids.length];
/* 156 */     for (int i = 0; i < ids.length; i++) {
/* 157 */       messages[i] = ((CmsReceiverMessage)get(ids[i]));
/* 158 */       deleteById(ids[i]);
/*     */     }
/* 160 */     return messages;
/*     */   }
/*     */ 
/*     */   protected Class<CmsReceiverMessage> getEntityClass()
/*     */   {
/* 165 */     return CmsReceiverMessage.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsReceiverMessageDaoImpl
 * JD-Core Version:    0.6.0
 */