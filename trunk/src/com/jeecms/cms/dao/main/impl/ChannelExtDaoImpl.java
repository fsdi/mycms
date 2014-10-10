/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ChannelExtDao;
/*    */ import com.jeecms.cms.entity.main.ChannelExt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ChannelExtDaoImpl extends HibernateBaseDao<ChannelExt, Integer>
/*    */   implements ChannelExtDao
/*    */ {
/*    */   public ChannelExt save(ChannelExt bean)
/*    */   {
/* 13 */     getSession().save(bean);
/* 14 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ChannelExt> getEntityClass()
/*    */   {
/* 19 */     return ChannelExt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ChannelExtDaoImpl
 * JD-Core Version:    0.6.0
 */