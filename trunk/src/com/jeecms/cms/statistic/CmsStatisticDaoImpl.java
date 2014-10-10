/*     */ package com.jeecms.cms.statistic;
/*     */ 
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateSimpleDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.hibernate.Query;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CmsStatisticDaoImpl extends HibernateSimpleDao
/*     */   implements CmsStatisticDao
/*     */ {
/*     */   public long memberStatistic(CmsStatistic.TimeRange timeRange)
/*     */   {
/*  23 */     Finder f = createCacheableFinder("select count(*) from CmsUser bean where 1=1");
/*  24 */     if (timeRange != null) {
/*  25 */       f.append(" and bean.registerTime >= :begin");
/*  26 */       f.append(" and bean.registerTime < :end");
/*  27 */       f.setParam("begin", timeRange.getBegin());
/*  28 */       f.setParam("end", timeRange.getEnd());
/*     */     }
/*  30 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   public long contentStatistic(CmsStatistic.TimeRange timeRange, Map<String, Object> restrictions)
/*     */   {
/*  35 */     Finder f = createCacheableFinder("select count(*) from Content bean");
/*  36 */     Integer userId = (Integer)restrictions.get("userId");
/*  37 */     Integer channelId = (Integer)restrictions.get("channelId");
/*  38 */     if (channelId != null) {
/*  39 */       f.append(" join bean.channel channel,Channel parent");
/*  40 */       f.append(" where channel.lft between parent.lft and parent.rgt");
/*  41 */       f.append(" and channel.site.id=parent.site.id");
/*  42 */       f.append(" and parent.id=:parentId");
/*  43 */       f.setParam("parentId", channelId);
/*     */     } else {
/*  45 */       f.append(" where bean.site.id=:siteId").setParam("siteId", 
/*  46 */         restrictions.get("siteId"));
/*     */     }
/*  48 */     if (timeRange != null) {
/*  49 */       f.append(" and bean.contentExt.releaseDate >= :begin");
/*  50 */       f.append(" and bean.contentExt.releaseDate < :end");
/*  51 */       f.setParam("begin", timeRange.getBegin());
/*  52 */       f.setParam("end", timeRange.getEnd());
/*     */     }
/*  54 */     if (userId != null) {
/*  55 */       f.append(" and bean.user.id=:userId").setParam("userId", userId);
/*     */     }
/*  57 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   public long commentStatistic(CmsStatistic.TimeRange timeRange, Map<String, Object> restrictions)
/*     */   {
/*  62 */     Finder f = createCacheableFinder("select count(*) from CmsComment bean where bean.site.id=:siteId");
/*  63 */     f.setParam("siteId", restrictions.get("siteId"));
/*  64 */     if (timeRange != null) {
/*  65 */       f.append(" and bean.createTime >= :begin");
/*  66 */       f.append(" and bean.createTime < :end");
/*  67 */       f.setParam("begin", timeRange.getBegin());
/*  68 */       f.setParam("end", timeRange.getEnd());
/*     */     }
/*  70 */     Boolean isReplyed = (Boolean)restrictions.get("isReplyed");
/*  71 */     if (isReplyed != null) {
/*  72 */       if (isReplyed.booleanValue())
/*  73 */         f.append(" and bean.replayTime is not null");
/*     */       else {
/*  75 */         f.append(" and bean.replayTime is null");
/*     */       }
/*     */     }
/*  78 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   public long guestbookStatistic(CmsStatistic.TimeRange timeRange, Map<String, Object> restrictions)
/*     */   {
/*  83 */     Finder f = createCacheableFinder("select count(*) from CmsGuestbook bean where bean.site.id=:siteId");
/*  84 */     f.setParam("siteId", restrictions.get("siteId"));
/*  85 */     if (timeRange != null) {
/*  86 */       f.append(" and bean.createTime >= :begin");
/*  87 */       f.append(" and bean.createTime < :end");
/*  88 */       f.setParam("begin", timeRange.getBegin());
/*  89 */       f.setParam("end", timeRange.getEnd());
/*     */     }
/*  91 */     Boolean isReplyed = (Boolean)restrictions.get("isReplyed");
/*  92 */     if (isReplyed != null) {
/*  93 */       if (isReplyed.booleanValue())
/*  94 */         f.append(" and bean.replayTime is not null");
/*     */       else {
/*  96 */         f.append(" and bean.replayTime is null");
/*     */       }
/*     */     }
/*  99 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   public List<Object[]> getPvCountByGroup(Integer siteId)
/*     */   {
/* 104 */     Finder f = createCacheableFinder("select count(*),bean.accessDate from CmsSiteFlow bean where bean.site.id=:siteId group by bean.accessDate");
/* 105 */     f.setParam("siteId", siteId);
/* 106 */     return find(f);
/*     */   }
/*     */ 
/*     */   public long getPvCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 110 */     Finder f = createCacheableFinder("select count(*) from CmsSiteFlow bean");
/* 111 */     return byTimeRange(f, siteId, timeRange);
/*     */   }
/*     */ 
/*     */   public long getPvCount(Integer siteId) {
/* 115 */     return getPvCountByTimeRange(siteId, null);
/*     */   }
/*     */ 
/*     */   public List<Object[]> getUniqueIpCountByGroup(Integer siteId)
/*     */   {
/* 120 */     Finder f = createCacheableFinder("select count(distinct bean.accessIp),bean.accessDate from CmsSiteFlow bean where bean.site.id=:siteId group by bean.accessDate");
/* 121 */     f.setParam("siteId", siteId);
/* 122 */     return find(f);
/*     */   }
/*     */ 
/*     */   public long getUniqueIpCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 126 */     Finder f = createCacheableFinder("select count(distinct bean.accessIp) from CmsSiteFlow bean");
/* 127 */     return byTimeRange(f, siteId, timeRange);
/*     */   }
/*     */ 
/*     */   public long getUniqueIpCount(Integer siteId) {
/* 131 */     return getUniqueIpCountByTimeRange(siteId, null);
/*     */   }
/*     */ 
/*     */   public List<Object[]> getUniqueVisitorCountByGroup(Integer siteId)
/*     */   {
/* 136 */     Finder f = createCacheableFinder("select count(distinct bean.sessionId),bean.accessDate from CmsSiteFlow bean where bean.site.id=:siteId group by bean.accessDate");
/* 137 */     f.setParam("siteId", siteId);
/* 138 */     return find(f);
/*     */   }
/*     */ 
/*     */   public long getUniqueVisitorCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange)
/*     */   {
/* 143 */     Finder f = createCacheableFinder("select count(distinct bean.sessionId) from CmsSiteFlow bean");
/* 144 */     return byTimeRange(f, siteId, timeRange);
/*     */   }
/*     */ 
/*     */   public long getUniqueVisitorCount(Integer siteId) {
/* 148 */     return getUniqueVisitorCountByTimeRange(siteId, null);
/*     */   }
/*     */ 
/*     */   public Pagination flowAnalysisPage(String groupCondition, Integer siteId, Integer pageNo, Integer pageSize)
/*     */   {
/* 153 */     Finder f = createCacheableFinder("select count(*),bean." + 
/* 154 */       groupCondition + 
/* 155 */       " from CmsSiteFlow bean where bean.site.id=:siteId group by bean." + 
/* 156 */       groupCondition + " order by count(*) desc");
/* 157 */     f.setParam("siteId", siteId);
/* 158 */     f.setMaxResults(pageSize.intValue());
/* 159 */     f.setFirstResult((pageNo.intValue() - 1) * pageSize.intValue());
/* 160 */     return new Pagination(pageNo.intValue(), pageSize.intValue(), 
/* 161 */       getTotalCount(f.getOrigHql(), 
/* 161 */       siteId), find(f));
/*     */   }
/*     */ 
/*     */   public void flowInit(Integer siteId, Date startDate, Date endDate) {
/* 165 */     Finder f = Finder.create("delete from CmsSiteFlow bean where bean.site.id=:siteId");
/* 166 */     f.setParam("siteId", siteId);
/* 167 */     if (startDate != null) {
/* 168 */       f.append(" and bean.accessTime >= :startDate");
/* 169 */       f.setParam("startDate", startDate);
/*     */     }
/* 171 */     if (endDate != null) {
/* 172 */       f.append(" and bean.accessTime <= :endDate");
/* 173 */       f.setParam("endDate", endDate);
/*     */     }
/* 175 */     Query query = f.createQuery(getSession());
/* 176 */     query.executeUpdate();
/*     */   }
/*     */ 
/*     */   public long flowAnalysisTotal(Integer siteId) {
/* 180 */     Finder f = createCacheableFinder("select count(*) from CmsSiteFlow bean where bean.site.id=:siteId");
/* 181 */     f.setParam("siteId", siteId);
/* 182 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   private int getTotalCount(String hql, Integer siteId) {
/* 186 */     Finder f = createCacheableFinder(hql);
/* 187 */     f.setParam("siteId", siteId);
/* 188 */     return find(f).size();
/*     */   }
/*     */ 
/*     */   private long byTimeRange(Finder f, Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 192 */     f.append(" where bean.site.id=:siteId").setParam("siteId", siteId);
/* 193 */     if (timeRange != null) {
/* 194 */       f.append(" and bean.accessTime >= :begin");
/* 195 */       f.append(" and bean.accessTime < :end");
/* 196 */       f.setParam("begin", timeRange.getBegin());
/* 197 */       f.setParam("end", timeRange.getEnd());
/*     */     }
/* 199 */     return ((Long)find(f).iterator().next()).longValue();
/*     */   }
/*     */ 
/*     */   private Finder createCacheableFinder(String hql) {
/* 203 */     Finder finder = Finder.create(hql);
/* 204 */     finder.setCacheable(true);
/* 205 */     return finder;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatisticDaoImpl
 * JD-Core Version:    0.6.0
 */