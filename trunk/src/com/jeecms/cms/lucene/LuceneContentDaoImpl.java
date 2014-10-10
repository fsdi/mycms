/*    */ package com.jeecms.cms.lucene;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import org.apache.lucene.index.CorruptIndexException;
/*    */ import org.apache.lucene.index.IndexWriter;
/*    */ import org.hibernate.CacheMode;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.ScrollMode;
/*    */ import org.hibernate.ScrollableResults;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LuceneContentDaoImpl extends HibernateBaseDao<Content, Integer>
/*    */   implements LuceneContentDao
/*    */ {
/*    */   public Integer index(IndexWriter writer, Integer siteId, Integer channelId, Date startDate, Date endDate, Integer startId, Integer max)
/*    */     throws CorruptIndexException, IOException
/*    */   {
/* 25 */     Finder f = Finder.create("select bean from Content bean");
/* 26 */     if (channelId != null) {
/* 27 */       f.append(" join bean.channel channel, Channel parent");
/* 28 */       f.append(" where channel.lft between parent.lft and parent.rgt");
/* 29 */       f.append(" and channel.site.id=parent.site.id");
/* 30 */       f.append(" and parent.id=:parentId");
/* 31 */       f.setParam("parentId", channelId);
/* 32 */     } else if (siteId != null) {
/* 33 */       f.append(" where bean.site.id=:siteId");
/* 34 */       f.setParam("siteId", siteId);
/*    */     } else {
/* 36 */       f.append(" where 1=1");
/*    */     }
/* 38 */     if (startId != null) {
/* 39 */       f.append(" and bean.id > :startId");
/* 40 */       f.setParam("startId", startId);
/*    */     }
/* 42 */     if (startDate != null) {
/* 43 */       f.append(" and bean.contentExt.releaseDate >= :startDate");
/* 44 */       f.setParam("startDate", startDate);
/*    */     }
/* 46 */     if (endDate != null) {
/* 47 */       f.append(" and bean.contentExt.releaseDate <= :endDate");
/* 48 */       f.setParam("endDate", endDate);
/*    */     }
/* 50 */     f.append(" and bean.status=2");
/* 51 */     f.append(" order by bean.id asc");
/* 52 */     if (max != null) {
/* 53 */       f.setMaxResults(max.intValue());
/*    */     }
/* 55 */     Session session = getSession();
/* 56 */     ScrollableResults contents = f.createQuery(getSession()).setCacheMode(
/* 57 */       CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY);
/* 58 */     int count = 0;
/* 59 */     Content content = null;
/* 60 */     while (contents.next()) {
/* 61 */       content = (Content)contents.get(0);
/* 62 */       writer.addDocument(LuceneContent.createDocument(content));
/* 63 */       count++; if (count % 20 == 0) {
/* 64 */         session.clear();
/*    */       }
/*    */     }
/* 67 */     if ((count < max.intValue()) || (content == null))
/*    */     {
/* 69 */       return null;
/*    */     }
/*    */ 
/* 72 */     return content.getId();
/*    */   }
/*    */ 
/*    */   protected Class<Content> getEntityClass()
/*    */   {
/* 79 */     return Content.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContentDaoImpl
 * JD-Core Version:    0.6.0
 */