/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentTagDao;
/*    */ import com.jeecms.cms.entity.main.ContentTag;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentTagDaoImpl extends HibernateBaseDao<ContentTag, Integer>
/*    */   implements ContentTagDao
/*    */ {
/*    */   public List<ContentTag> getList(Integer count, boolean cacheable)
/*    */   {
/* 20 */     String hql = "from ContentTag bean order by bean.count desc";
/* 21 */     Query query = getSession().createQuery(hql);
/* 22 */     if (count != null) {
/* 23 */       query.setMaxResults(count.intValue());
/*    */     }
/* 25 */     query.setCacheable(cacheable);
/* 26 */     return query.list();
/*    */   }
/*    */ 
/*    */   public Pagination getPage(String name, int pageNo, int pageSize, boolean cacheable)
/*    */   {
/* 31 */     Finder f = Finder.create("from ContentTag bean");
/* 32 */     if (!StringUtils.isBlank(name)) {
/* 33 */       f.append(" where bean.name like :name");
/* 34 */       f.setParam("name", "%" + name + "%");
/*    */     }
/* 36 */     f.append(" order by bean.count desc");
/* 37 */     f.setCacheable(cacheable);
/* 38 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public ContentTag findById(Integer id) {
/* 42 */     ContentTag entity = (ContentTag)get(id);
/* 43 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentTag findByName(String name, boolean cacheable) {
/* 47 */     String hql = "from ContentTag bean where bean.name=:name";
/* 48 */     return (ContentTag)getSession().createQuery(hql).setParameter("name", 
/* 49 */       name).setCacheable(cacheable).uniqueResult();
/*    */   }
/*    */ 
/*    */   public ContentTag save(ContentTag bean) {
/* 53 */     getSession().save(bean);
/* 54 */     return bean;
/*    */   }
/*    */ 
/*    */   public ContentTag deleteById(Integer id) {
/* 58 */     ContentTag entity = (ContentTag)super.get(id);
/* 59 */     if (entity != null) {
/* 60 */       getSession().delete(entity);
/*    */     }
/* 62 */     return entity;
/*    */   }
/*    */ 
/*    */   public int deleteContentRef(Integer id) {
/* 66 */     Query query = getSession().getNamedQuery("ContentTag.deleteContentRef");
/* 67 */     return query.setParameter(0, id).executeUpdate();
/*    */   }
/*    */ 
/*    */   public int countContentRef(Integer id) {
/* 71 */     Query query = getSession().getNamedQuery("ContentTag.countContentRef");
/* 72 */     return ((Number)query.setParameter(0, id).list().iterator().next())
/* 73 */       .intValue();
/*    */   }
/*    */ 
/*    */   protected Class<ContentTag> getEntityClass()
/*    */   {
/* 78 */     return ContentTag.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentTagDaoImpl
 * JD-Core Version:    0.6.0
 */