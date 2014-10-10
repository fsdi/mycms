/*     */ package com.jeecms.cms.dao.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsCommentDao;
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CmsCommentDaoImpl extends HibernateBaseDao<CmsComment, Integer>
/*     */   implements CmsCommentDao
/*     */ {
/*     */   public Pagination getPage(Integer siteId, Integer contentId, Integer greaterThen, Boolean checked, boolean recommend, boolean desc, int pageNo, int pageSize, boolean cacheable)
/*     */   {
/*  19 */     Finder f = getFinder(siteId, contentId, null, null, greaterThen, checked, 
/*  20 */       Boolean.valueOf(recommend), desc, cacheable);
/*  21 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<CmsComment> getList(Integer siteId, Integer contentId, Integer greaterThen, Boolean checked, boolean recommend, boolean desc, int count, boolean cacheable)
/*     */   {
/*  28 */     Finder f = getFinder(siteId, contentId, null, null, greaterThen, checked, 
/*  29 */       Boolean.valueOf(recommend), desc, cacheable);
/*  30 */     f.setMaxResults(count);
/*  31 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageForMember(Integer siteId, Integer contentId, Integer toUserId, Integer fromUserId, Integer greaterThen, Boolean checked, Boolean recommend, boolean desc, int pageNo, int pageSize, boolean cacheable)
/*     */   {
/*  36 */     Finder f = getFinder(siteId, contentId, toUserId, fromUserId, greaterThen, checked, 
/*  37 */       recommend, desc, cacheable);
/*  38 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<CmsComment> getListForDel(Integer siteId, Integer userId, Integer commentUserId, String ip)
/*     */   {
/*  44 */     Finder f = Finder.create("from CmsComment bean where 1=1");
/*  45 */     if (siteId != null) {
/*  46 */       f.append(" and bean.site.id=:siteId");
/*  47 */       f.setParam("siteId", siteId);
/*     */     }
/*  49 */     if (commentUserId != null) {
/*  50 */       f.append(" and bean.commentUser.id=:commentUserId");
/*  51 */       f.setParam("commentUserId", commentUserId);
/*     */     }
/*  53 */     if (userId != null) {
/*  54 */       f.append(" and bean.content.user.id=:fromUserId");
/*  55 */       f.setParam("fromUserId", userId);
/*     */     }
/*  57 */     f.setCacheable(false);
/*  58 */     return find(f);
/*     */   }
/*     */ 
/*     */   private Finder getFinder(Integer siteId, Integer contentId, Integer toUserId, Integer fromUserId, Integer greaterThen, Boolean checked, Boolean recommend, boolean desc, boolean cacheable)
/*     */   {
/*  64 */     Finder f = Finder.create("from CmsComment bean where 1=1");
/*  65 */     if (contentId != null) {
/*  66 */       f.append(" and bean.content.id=:contentId");
/*  67 */       f.setParam("contentId", contentId);
/*  68 */     } else if (siteId != null) {
/*  69 */       f.append(" and bean.site.id=:siteId");
/*  70 */       f.setParam("siteId", siteId);
/*     */     }
/*  72 */     if (toUserId != null) {
/*  73 */       f.append(" and bean.commentUser.id=:commentUserId");
/*  74 */       f.setParam("commentUserId", toUserId);
/*  75 */     } else if (fromUserId != null) {
/*  76 */       f.append(" and bean.content.user.id=:fromUserId");
/*  77 */       f.setParam("fromUserId", fromUserId);
/*     */     }
/*  79 */     if (greaterThen != null) {
/*  80 */       f.append(" and bean.ups>=:greatTo");
/*  81 */       f.setParam("greatTo", greaterThen);
/*     */     }
/*  83 */     if (checked != null) {
/*  84 */       f.append(" and bean.checked=:checked");
/*  85 */       f.setParam("checked", checked);
/*     */     }
/*  87 */     if ((recommend != null) && 
/*  88 */       (recommend.booleanValue())) {
/*  89 */       f.append(" and bean.recommend=true");
/*     */     }
/*     */ 
/*  92 */     if (desc)
/*  93 */       f.append(" order by bean.id desc");
/*     */     else {
/*  95 */       f.append(" order by bean.id asc");
/*     */     }
/*  97 */     f.setCacheable(cacheable);
/*  98 */     return f;
/*     */   }
/*     */ 
/*     */   public CmsComment findById(Integer id) {
/* 102 */     CmsComment entity = (CmsComment)get(id);
/* 103 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsComment save(CmsComment bean) {
/* 107 */     getSession().save(bean);
/* 108 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsComment deleteById(Integer id) {
/* 112 */     CmsComment entity = (CmsComment)super.get(id);
/* 113 */     if (entity != null) {
/* 114 */       getSession().delete(entity);
/*     */     }
/* 116 */     return entity;
/*     */   }
/*     */ 
/*     */   public int deleteByContentId(Integer contentId) {
/* 120 */     String hql = "delete from CmsComment bean where bean.content.id=:contentId";
/* 121 */     return getSession().createQuery(hql).setParameter("contentId", 
/* 122 */       contentId).executeUpdate();
/*     */   }
/*     */ 
/*     */   protected Class<CmsComment> getEntityClass()
/*     */   {
/* 127 */     return CmsComment.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsCommentDaoImpl
 * JD-Core Version:    0.6.0
 */