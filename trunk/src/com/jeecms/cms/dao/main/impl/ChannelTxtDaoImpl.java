/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ChannelTxtDao;
/*    */ import com.jeecms.cms.entity.main.ChannelTxt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ChannelTxtDaoImpl extends HibernateBaseDao<ChannelTxt, Integer>
/*    */   implements ChannelTxtDao
/*    */ {
/*    */   public ChannelTxt findById(Integer id)
/*    */   {
/* 13 */     ChannelTxt entity = (ChannelTxt)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public ChannelTxt save(ChannelTxt bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ChannelTxt> getEntityClass()
/*    */   {
/* 24 */     return ChannelTxt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ChannelTxtDaoImpl
 * JD-Core Version:    0.6.0
 */