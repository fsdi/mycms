/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAcquisitionTempDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsAcquisitionTempDaoImpl extends HibernateBaseDao<CmsAcquisitionTemp, Integer>
/*    */   implements CmsAcquisitionTempDao
/*    */ {
/*    */   public List<CmsAcquisitionTemp> getList(Integer siteId)
/*    */   {
/* 23 */     Finder f = Finder.create("from CmsAcquisitionTemp bean where 1=1");
/* 24 */     if (siteId != null) {
/* 25 */       f.append(" and bean.site.id=:siteId");
/* 26 */       f.setParam("siteId", siteId);
/*    */     }
/* 28 */     f.append(" order by bean.id desc");
/* 29 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp findById(Integer id) {
/* 33 */     CmsAcquisitionTemp entity = (CmsAcquisitionTemp)get(id);
/* 34 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp save(CmsAcquisitionTemp bean) {
/* 38 */     getSession().save(bean);
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp deleteById(Integer id) {
/* 43 */     CmsAcquisitionTemp entity = (CmsAcquisitionTemp)super.get(id);
/* 44 */     if (entity != null) {
/* 45 */       getSession().delete(entity);
/*    */     }
/* 47 */     return entity;
/*    */   }
/*    */ 
/*    */   public Integer getPercent(Integer siteId) {
/* 51 */     Query query = getSession()
/* 52 */       .createQuery(
/* 53 */       "select max(percent) from CmsAcquisitionTemp where site.id=:siteId")
/* 54 */       .setParameter("siteId", siteId);
/* 55 */     return (Integer)(query.uniqueResult() == null ? Integer.valueOf(0) : 
/* 56 */       query.uniqueResult());
/*    */   }
/*    */ 
/*    */   public void clear(Integer siteId, String channelUrl) {
/* 60 */     StringBuilder sb = new StringBuilder(100);
/* 61 */     Map<String,Object> params = new HashMap();
/* 62 */     sb.append("delete from CmsAcquisitionTemp where site.id=:siteId");
/* 63 */     params.put("siteId", siteId);
/* 64 */     if (StringUtils.isNotBlank(channelUrl)) {
/* 65 */       sb.append(" and channelUrl!=:channelUrl");
/* 66 */       params.put("channelUrl", channelUrl);
/*    */     }
/* 68 */     Query query = getSession().createQuery(sb.toString());
/* 69 */     for (Map.Entry entry : params.entrySet()) {
/* 70 */       query.setParameter((String)entry.getKey(), entry.getValue());
/*    */     }
/* 72 */     query.executeUpdate();
/*    */   }
/*    */ 
/*    */   protected Class<CmsAcquisitionTemp> getEntityClass()
/*    */   {
/* 77 */     return CmsAcquisitionTemp.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsAcquisitionTempDaoImpl
 * JD-Core Version:    0.6.0
 */