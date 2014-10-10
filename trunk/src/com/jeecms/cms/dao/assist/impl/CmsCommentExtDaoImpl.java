/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsCommentExtDao;
/*    */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsCommentExtDaoImpl extends HibernateBaseDao<CmsCommentExt, Integer>
/*    */   implements CmsCommentExtDao
/*    */ {
/*    */   public Pagination getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria(new Criterion[0]);
/* 16 */     Pagination page = findByCriteria(crit, pageNo, pageSize);
/* 17 */     return page;
/*    */   }
/*    */ 
/*    */   public CmsCommentExt findById(Integer id) {
/* 21 */     CmsCommentExt entity = (CmsCommentExt)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsCommentExt save(CmsCommentExt bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteByContentId(Integer contentId) {
/* 31 */     String hql = "delete from CmsCommentExt bean where bean.id in (select c.id from CmsComment c where c.content.id=:contentId)";
/*    */ 
/* 33 */     return getSession().createQuery(hql).setParameter("contentId", 
/* 34 */       contentId).executeUpdate();
/*    */   }
/*    */ 
/*    */   public CmsCommentExt deleteById(Integer id) {
/* 38 */     CmsCommentExt entity = (CmsCommentExt)super.get(id);
/* 39 */     if (entity != null) {
/* 40 */       getSession().delete(entity);
/*    */     }
/* 42 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsCommentExt> getEntityClass()
/*    */   {
/* 47 */     return CmsCommentExt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsCommentExtDaoImpl
 * JD-Core Version:    0.6.0
 */