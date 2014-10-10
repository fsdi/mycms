/*     */ package com.jeecms.cms.dao.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ChannelDao;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class ChannelDaoImpl extends HibernateBaseDao<Channel, Integer>
/*     */   implements ChannelDao
/*     */ {
/*     */   public List<Channel> getTopList(Integer siteId, boolean hasContentOnly, boolean displayOnly, boolean cacheable)
/*     */   {
/*  20 */     Finder f = getTopFinder(siteId, hasContentOnly, displayOnly, cacheable);
/*  21 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getTopPage(Integer siteId, boolean hasContentOnly, boolean displayOnly, boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  26 */     Finder f = getTopFinder(siteId, hasContentOnly, displayOnly, cacheable);
/*  27 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   private Finder getTopFinder(Integer siteId, boolean hasContentOnly, boolean displayOnly, boolean cacheable)
/*     */   {
/*  32 */     Finder f = Finder.create("from Channel bean");
/*  33 */     f.append(" where bean.site.id=:siteId and bean.parent.id is null");
/*  34 */     f.setParam("siteId", siteId);
/*  35 */     if (hasContentOnly) {
/*  36 */       f.append(" and bean.hasContent=true");
/*     */     }
/*  38 */     if (displayOnly) {
/*  39 */       f.append(" and bean.display=true");
/*     */     }
/*  41 */     f.append(" order by bean.priority asc,bean.id asc");
/*  42 */     f.setCacheable(cacheable);
/*  43 */     return f;
/*     */   }
/*     */ 
/*     */   public List<Channel> getTopListByRigth(Integer userId, Integer siteId, boolean hasContentOnly)
/*     */   {
/*  49 */     Finder f = Finder.create("select bean from Channel bean");
/*  50 */     f.append(" join bean.users user");
/*  51 */     f.append(" where user.id=:userId and bean.site.id=:siteId");
/*  52 */     f.setParam("userId", userId).setParam("siteId", siteId);
/*  53 */     if (hasContentOnly) {
/*  54 */       f.append(" and bean.hasContent=true");
/*     */     }
/*  56 */     f.append(" and bean.parent.id is null");
/*  57 */     f.append(" order by bean.priority asc,bean.id asc");
/*  58 */     return find(f);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChildList(Integer parentId, boolean hasContentOnly, boolean displayOnly, boolean cacheable)
/*     */   {
/*  64 */     Finder f = getChildFinder(parentId, hasContentOnly, displayOnly, 
/*  65 */       cacheable);
/*  66 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getChildPage(Integer parentId, boolean hasContentOnly, boolean displayOnly, boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  71 */     Finder f = getChildFinder(parentId, hasContentOnly, displayOnly, 
/*  72 */       cacheable);
/*  73 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   private Finder getChildFinder(Integer parentId, boolean hasContentOnly, boolean displayOnly, boolean cacheable)
/*     */   {
/*  78 */     Finder f = Finder.create("from Channel bean");
/*  79 */     f.append(" where bean.parent.id=:parentId");
/*  80 */     f.setParam("parentId", parentId);
/*  81 */     if (hasContentOnly) {
/*  82 */       f.append(" and bean.hasContent=true");
/*     */     }
/*  84 */     if (displayOnly) {
/*  85 */       f.append(" and bean.display=true");
/*     */     }
/*  87 */     f.append(" order by bean.priority asc,bean.id asc");
/*  88 */     return f;
/*     */   }
/*     */ 
/*     */   public List<Channel> getChildListByRight(Integer userId, Integer parentId, boolean hasContentOnly)
/*     */   {
/*  94 */     Finder f = Finder.create("select bean from Channel bean");
/*  95 */     f.append(" join bean.users user");
/*  96 */     f.append(" where user.id=:userId and bean.parent.id=:parentId");
/*  97 */     f.setParam("userId", userId).setParam("parentId", parentId);
/*  98 */     if (hasContentOnly) {
/*  99 */       f.append(" and bean.hasContent=true");
/*     */     }
/* 101 */     f.append(" order by bean.priority asc,bean.id asc");
/* 102 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Channel findById(Integer id) {
/* 106 */     Channel entity = (Channel)get(id);
/* 107 */     return entity;
/*     */   }
/*     */ 
/*     */   public Channel findByPath(String path, Integer siteId, boolean cacheable) {
/* 111 */     String hql = "from Channel bean where bean.path=? and bean.site.id=?";
/* 112 */     Query query = getSession().createQuery(hql);
/* 113 */     query.setParameter(0, path).setParameter(1, siteId);
/*     */ 
/* 115 */     query.setMaxResults(1);
/* 116 */     return (Channel)query.setCacheable(cacheable).uniqueResult();
/*     */   }
/*     */ 
/*     */   public Channel save(Channel bean) {
/* 120 */     getSession().save(bean);
/* 121 */     return bean;
/*     */   }
/*     */ 
/*     */   public Channel deleteById(Integer id) {
/* 125 */     Channel entity = (Channel)super.get(id);
/* 126 */     if (entity != null) {
/* 127 */       getSession().delete(entity);
/*     */     }
/* 129 */     return entity;
/*     */   }
/*     */ 
/*     */   protected Class<Channel> getEntityClass()
/*     */   {
/* 134 */     return Channel.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ChannelDaoImpl
 * JD-Core Version:    0.6.0
 */