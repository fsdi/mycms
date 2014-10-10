/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentCountDao;
/*    */ import com.jeecms.cms.entity.main.ContentCount;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentCountDaoImpl extends HibernateBaseDao<ContentCount, Integer>
/*    */   implements ContentCountDao
/*    */ {
/*    */   public int freshCacheToDB(Ehcache cache)
/*    */   {
/* 20 */     List<Integer> keys = cache.getKeys();
/* 21 */     if (keys.size() <= 0) {
/* 22 */       return 0;
/*    */     }
/*    */ 
/* 26 */     int i = 0;
/* 27 */     String hql = "update ContentCount bean set bean.views=bean.views+:views,bean.viewsMonth=bean.viewsMonth+:views,bean.viewsWeek=bean.viewsWeek+:views,bean.viewsDay=bean.viewsDay+:views where bean.id=:id";
/*    */ 
/* 32 */     Query query = getSession().createQuery(hql);
/* 33 */     for (Integer id : keys) {
/* 34 */       Element e = cache.get(id);
/* 35 */       if (e != null) {
/* 36 */         Integer views = (Integer)e.getValue();
/* 37 */         if (views != null) {
/* 38 */           query.setParameter("views", views);
/* 39 */           query.setParameter("id", id);
/* 40 */           i += query.executeUpdate();
/*    */         }
/*    */       }
/*    */     }
/* 44 */     return i;
/*    */   }
/*    */ 
/*    */   public int clearCount(boolean week, boolean month) {
/* 48 */     StringBuilder hql = new StringBuilder("update ContentCount bean");
/* 49 */     hql.append(" set bean.viewsDay=0,commentsDay=0,upsDay=0");
/* 50 */     if (week) {
/* 51 */       hql.append(",bean.viewsWeek=0,commentsWeek=0,upsWeek=0");
/*    */     }
/* 53 */     if (month) {
/* 54 */       hql.append(",bean.viewsMonth=0,commentsMonth=0,upsMonth=0");
/*    */     }
/* 56 */     return getSession().createQuery(hql.toString()).executeUpdate();
/*    */   }
/*    */ 
/*    */   public int copyCount() {
/* 60 */     String hql = "update Content a set a.viewsDay=(select b.viewsDay from ContentCount b where a.id=b.id),a.commentsDay=(select b.commentsDay from ContentCount b where a.id=b.id),a.downloadsDay=(select b.downloadsDay from ContentCount b where a.id=b.id),a.upsDay=(select b.upsDay from ContentCount b where a.id=b.id)";
/*    */ 
/* 65 */     return getSession().createQuery(hql).executeUpdate();
/*    */   }
/*    */ 
/*    */   public ContentCount findById(Integer id) {
/* 69 */     ContentCount entity = (ContentCount)get(id);
/* 70 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentCount save(ContentCount bean) {
/* 74 */     getSession().save(bean);
/* 75 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ContentCount> getEntityClass()
/*    */   {
/* 80 */     return ContentCount.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentCountDaoImpl
 * JD-Core Version:    0.6.0
 */