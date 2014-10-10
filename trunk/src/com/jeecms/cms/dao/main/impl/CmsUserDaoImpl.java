/*     */ package com.jeecms.cms.dao.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsUserDao;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CmsUserDaoImpl extends HibernateBaseDao<CmsUser, Integer>
/*     */   implements CmsUserDao
/*     */ {
/*     */   public Pagination getPage(String username, String email, Integer siteId, Integer groupId, Boolean disabled, Boolean admin, Integer rank, int pageNo, int pageSize)
/*     */   {
/*  21 */     Finder f = Finder.create("select bean from CmsUser bean");
/*  22 */     if (siteId != null) {
/*  23 */       f.append(" join bean.userSites userSite");
/*  24 */       f.append(" where userSite.site.id=:siteId");
/*  25 */       f.setParam("siteId", siteId);
/*     */     } else {
/*  27 */       f.append(" where 1=1");
/*     */     }
/*  29 */     if (!StringUtils.isBlank(username)) {
/*  30 */       f.append(" and bean.username like :username");
/*  31 */       f.setParam("username", "%" + username + "%");
/*     */     }
/*  33 */     if (!StringUtils.isBlank(email)) {
/*  34 */       f.append(" and bean.email like :email");
/*  35 */       f.setParam("email", "%" + email + "%");
/*     */     }
/*  37 */     if (groupId != null) {
/*  38 */       f.append(" and bean.group.id=:groupId");
/*  39 */       f.setParam("groupId", groupId);
/*     */     }
/*  41 */     if (disabled != null) {
/*  42 */       f.append(" and bean.disabled=:disabled");
/*  43 */       f.setParam("disabled", disabled);
/*     */     }
/*  45 */     if (admin != null) {
/*  46 */       f.append(" and bean.admin=:admin");
/*  47 */       f.setParam("admin", admin);
/*     */     }
/*  49 */     if (rank != null) {
/*  50 */       f.append(" and bean.rank<=:rank");
/*  51 */       f.setParam("rank", rank);
/*     */     }
/*  53 */     f.append(" order by bean.id desc");
/*  54 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<CmsUser> getList(String username, String email, Integer siteId, Integer groupId, Boolean disabled, Boolean admin, Integer rank)
/*     */   {
/*  60 */     Finder f = Finder.create("select bean from CmsUser bean");
/*  61 */     if (siteId != null) {
/*  62 */       f.append(" join bean.userSites userSite");
/*  63 */       f.append(" where userSite.site.id=:siteId");
/*  64 */       f.setParam("siteId", siteId);
/*     */     } else {
/*  66 */       f.append(" where 1=1");
/*     */     }
/*  68 */     if (!StringUtils.isBlank(username)) {
/*  69 */       f.append(" and bean.username like :username");
/*  70 */       f.setParam("username", "%" + username + "%");
/*     */     }
/*  72 */     if (!StringUtils.isBlank(email)) {
/*  73 */       f.append(" and bean.email like :email");
/*  74 */       f.setParam("email", "%" + email + "%");
/*     */     }
/*  76 */     if (groupId != null) {
/*  77 */       f.append(" and bean.group.id=:groupId");
/*  78 */       f.setParam("groupId", groupId);
/*     */     }
/*  80 */     if (disabled != null) {
/*  81 */       f.append(" and bean.disabled=:disabled");
/*  82 */       f.setParam("disabled", disabled);
/*     */     }
/*  84 */     if (admin != null) {
/*  85 */       f.append(" and bean.admin=:admin");
/*  86 */       f.setParam("admin", admin);
/*     */     }
/*  88 */     if (rank != null) {
/*  89 */       f.append(" and bean.rank<=:rank");
/*  90 */       f.setParam("rank", rank);
/*     */     }
/*  92 */     f.append(" order by bean.id desc");
/*  93 */     return find(f);
/*     */   }
/*     */ 
/*     */   public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel, Boolean disabled, Integer rank)
/*     */   {
/*  99 */     Finder f = Finder.create("select bean from CmsUser");
/* 100 */     f.append(" bean join bean.userSites us");
/* 101 */     f.append(" where us.site.id=:siteId");
/* 102 */     f.setParam("siteId", siteId);
/* 103 */     if (allChannel != null) {
/* 104 */       f.append(" and us.allChannel=:allChannel");
/* 105 */       f.setParam("allChannel", allChannel);
/*     */     }
/* 107 */     if (disabled != null) {
/* 108 */       f.append(" and bean.disabled=:disabled");
/* 109 */       f.setParam("disabled", disabled);
/*     */     }
/* 111 */     if (rank != null) {
/* 112 */       f.append(" and bean.rank<=:rank");
/* 113 */       f.setParam("rank", rank);
/*     */     }
/* 115 */     f.append(" and bean.admin=true");
/* 116 */     f.append(" order by bean.id asc");
/* 117 */     return find(f);
/*     */   }
/*     */ 
/*     */   public CmsUser findById(Integer id) {
/* 121 */     CmsUser entity = (CmsUser)get(id);
/* 122 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsUser findByUsername(String username) {
/* 126 */     return (CmsUser)findUniqueByProperty("username", username);
/*     */   }
/*     */ 
/*     */   public int countByUsername(String username) {
/* 130 */     String hql = "select count(*) from CmsUser bean where bean.username=:username";
/* 131 */     Query query = getSession().createQuery(hql);
/* 132 */     query.setParameter("username", username);
/* 133 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */   public int countMemberByUsername(String username) {
/* 136 */     String hql = "select count(*) from CmsUser bean where bean.username=:username and bean.admin=false";
/* 137 */     Query query = getSession().createQuery(hql);
/* 138 */     query.setParameter("username", username);
/* 139 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public int countByEmail(String email) {
/* 143 */     String hql = "select count(*) from CmsUser bean where bean.email=:email";
/* 144 */     Query query = getSession().createQuery(hql);
/* 145 */     query.setParameter("email", email);
/* 146 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public CmsUser save(CmsUser bean) {
/* 150 */     getSession().save(bean);
/* 151 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsUser deleteById(Integer id) {
/* 155 */     CmsUser entity = (CmsUser)super.get(id);
/* 156 */     if (entity != null) {
/* 157 */       getSession().delete(entity);
/*     */     }
/* 159 */     return entity;
/*     */   }
/*     */ 
/*     */   protected Class<CmsUser> getEntityClass()
/*     */   {
/* 164 */     return CmsUser.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsUserDaoImpl
 * JD-Core Version:    0.6.0
 */