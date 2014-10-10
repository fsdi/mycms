/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsFileDao;
/*    */ import com.jeecms.cms.entity.assist.CmsFile;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsFileDaoImpl extends HibernateBaseDao<CmsFile, Integer>
/*    */   implements CmsFileDao
/*    */ {
/*    */   public List<CmsFile> getList(Boolean valid)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsFile bean where 1=1");
/* 19 */     if (valid != null) {
/* 20 */       if (valid.booleanValue())
/* 21 */         f.append(" and bean.fileIsvalid=true");
/*    */       else {
/* 23 */         f.append(" and bean.fileIsvalid=false");
/*    */       }
/*    */     }
/* 26 */     f.append(" order by bean.id desc");
/* 27 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsFile findByPath(String path)
/*    */   {
/* 32 */     Finder f = Finder.create("from CmsFile bean where bean.filePath  like '%" + path + "%'");
/* 33 */     List li = find(f);
/* 34 */     if ((li != null) && (li.size() > 0)) {
/* 35 */       return (CmsFile)li.get(0);
/*    */     }
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   public CmsFile findById(Integer id)
/*    */   {
/* 43 */     CmsFile entity = (CmsFile)get(id);
/* 44 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsFile save(CmsFile bean) {
/* 48 */     getSession().save(bean);
/* 49 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFile deleteById(Integer id) {
/* 53 */     CmsFile entity = (CmsFile)super.get(id);
/* 54 */     if (entity != null) {
/* 55 */       getSession().delete(entity);
/*    */     }
/* 57 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsFile deleteByPath(String path) {
/* 61 */     CmsFile entity = findByPath(path);
/* 62 */     if (entity != null) {
/* 63 */       getSession().delete(entity);
/*    */     }
/* 65 */     return entity;
/*    */   }
/*    */ 
/*    */   public void deleteByContentId(Integer contentId) {
/* 69 */     String sql = "delete from CmsFile file where file.content.id=:contentId";
/* 70 */     getSession().createQuery(sql).setParameter("contentId", contentId).executeUpdate();
/*    */   }
/*    */ 
/*    */   protected Class<CmsFile> getEntityClass()
/*    */   {
/* 75 */     return CmsFile.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsFileDaoImpl
 * JD-Core Version:    0.6.0
 */