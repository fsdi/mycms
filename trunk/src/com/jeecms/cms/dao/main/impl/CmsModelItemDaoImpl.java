/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsModelItemDao;
/*    */ import com.jeecms.cms.entity.main.CmsModelItem;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsModelItemDaoImpl extends HibernateBaseDao<CmsModelItem, Integer>
/*    */   implements CmsModelItemDao
/*    */ {
/*    */   public List<CmsModelItem> getList(Integer modelId, boolean isChannel, boolean hasDisabled)
/*    */   {
/* 17 */     StringBuilder sb = new StringBuilder(
/* 18 */       "from CmsModelItem bean where bean.model.id=? and bean.channel=?");
/* 19 */     if (!hasDisabled) {
/* 20 */       sb.append(" and bean.display=true");
/*    */     }
/* 22 */     sb.append(" order by bean.priority asc,bean.id asc");
/* 23 */     return find(sb.toString(), new Object[] { modelId, Boolean.valueOf(isChannel) });
/*    */   }
/*    */ 
/*    */   public CmsModelItem findById(Integer id) {
/* 27 */     CmsModelItem entity = (CmsModelItem)get(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModelItem save(CmsModelItem bean) {
/* 32 */     getSession().save(bean);
/* 33 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsModelItem deleteById(Integer id) {
/* 37 */     CmsModelItem entity = (CmsModelItem)super.get(id);
/* 38 */     if (entity != null) {
/* 39 */       getSession().delete(entity);
/*    */     }
/* 41 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsModelItem> getEntityClass()
/*    */   {
/* 46 */     return CmsModelItem.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsModelItemDaoImpl
 * JD-Core Version:    0.6.0
 */