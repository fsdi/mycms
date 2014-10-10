/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteItemDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsVoteItemDaoImpl extends HibernateBaseDao<CmsVoteItem, Integer>
/*    */   implements CmsVoteItemDao
/*    */ {
/*    */   public Pagination getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria(new Criterion[0]);
/* 16 */     Pagination page = findByCriteria(crit, pageNo, pageSize);
/* 17 */     return page;
/*    */   }
/*    */ 
/*    */   public CmsVoteItem findById(Integer id) {
/* 21 */     CmsVoteItem entity = (CmsVoteItem)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsVoteItem save(CmsVoteItem bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteItem deleteById(Integer id) {
/* 31 */     CmsVoteItem entity = (CmsVoteItem)super.get(id);
/* 32 */     if (entity != null) {
/* 33 */       getSession().delete(entity);
/*    */     }
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsVoteItem> getEntityClass()
/*    */   {
/* 40 */     return CmsVoteItem.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsVoteItemDaoImpl
 * JD-Core Version:    0.6.0
 */