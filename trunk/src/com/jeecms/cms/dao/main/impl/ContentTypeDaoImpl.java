/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentTypeDao;
/*    */ import com.jeecms.cms.entity.main.ContentType;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentTypeDaoImpl extends HibernateBaseDao<ContentType, Integer>
/*    */   implements ContentTypeDao
/*    */ {
/*    */   public List<ContentType> getList(boolean containDisabled)
/*    */   {
/* 18 */     Finder f = Finder.create("from ContentType bean");
/* 19 */     if (!containDisabled) {
/* 20 */       f.append(" where bean.disabled=false");
/*    */     }
/* 22 */     f.append(" order by bean.id asc");
/* 23 */     return find(f);
/*    */   }
/*    */ 
/*    */   public ContentType getDef() {
/* 27 */     String hql = "from ContentType bean where bean.disabled=false order by bean.id asc";
/*    */ 
/* 29 */     Query query = getSession().createQuery(hql).setMaxResults(1);
/* 30 */     List list = query.list();
/* 31 */     if (list.size() > 0) {
/* 32 */       return (ContentType)list.get(0);
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   public ContentType findById(Integer id)
/*    */   {
/* 39 */     ContentType entity = (ContentType)get(id);
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentType save(ContentType bean) {
/* 44 */     getSession().save(bean);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public ContentType deleteById(Integer id) {
/* 49 */     ContentType entity = (ContentType)super.get(id);
/* 50 */     if (entity != null) {
/* 51 */       getSession().delete(entity);
/*    */     }
/* 53 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<ContentType> getEntityClass()
/*    */   {
/* 58 */     return ContentType.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentTypeDaoImpl
 * JD-Core Version:    0.6.0
 */