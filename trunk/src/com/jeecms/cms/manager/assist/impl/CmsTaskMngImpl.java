/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsTaskDao;
/*     */ import com.jeecms.cms.entity.assist.CmsTask;
/*     */ import com.jeecms.cms.manager.assist.CmsTaskMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsTaskMngImpl
/*     */   implements CmsTaskMng
/*     */ {
/*     */   private CmsTaskDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(Integer siteId, int pageNo, int pageSize)
/*     */   {
/*  21 */     Pagination page = this.dao.getPage(siteId, pageNo, pageSize);
/*  22 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsTask> getList() {
/*  27 */     return this.dao.getList();
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsTask findById(Integer id) {
/*  32 */     CmsTask entity = this.dao.findById(id);
/*  33 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsTask save(CmsTask bean) {
/*  37 */     this.dao.save(bean);
/*  38 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTask update(CmsTask bean, Map<String, String> attr) {
/*  42 */     Updater updater = new Updater(bean);
/*  43 */     updater.include("intervalUnit");
/*  44 */     updater.include("cronExpression");
/*  45 */     updater.include("dayOfMonth");
/*  46 */     updater.include("dayOfWeek");
/*  47 */     updater.include("hour");
/*  48 */     updater.include("minute");
/*  49 */     updater.include("repeatCount");
/*  50 */     updater.include("intervalHour");
/*  51 */     updater.include("intervalMinute");
/*  52 */     bean = this.dao.updateByUpdater(updater);
/*     */ 
/*  54 */     if (attr != null) {
/*  55 */       Map attrOrig = bean.getAttr();
/*  56 */       attrOrig.clear();
/*  57 */       attrOrig.putAll(attr);
/*     */     }
/*  59 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTask deleteById(Integer id) {
/*  63 */     CmsTask bean = this.dao.deleteById(id);
/*  64 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTask[] deleteByIds(Integer[] ids) {
/*  68 */     CmsTask[] beans = new CmsTask[ids.length];
/*  69 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  70 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  72 */     return beans;
/*     */   }
/*     */ 
/*     */   public String getCronExpressionFromDB(Integer taskId) {
/*  76 */     CmsTask task = findById(taskId);
/*  77 */     if (task.getExecycle().equals(Integer.valueOf(CmsTask.EXECYCLE_CRON))) {
/*  78 */       return task.getCronExpression();
/*     */     }
/*  80 */     Integer execycle = task.getIntervalUnit();
/*  81 */     String excep = "";
/*  82 */     if (execycle.equals(Integer.valueOf(CmsTask.EXECYCLE_MONTH)))
/*  83 */       excep = "0  " + task.getMinute() + " " + task.getHour() + " " + task.getDayOfMonth() + " * ?";
/*  84 */     else if (execycle.equals(Integer.valueOf(CmsTask.EXECYCLE_WEEK)))
/*  85 */       excep = "0  " + task.getMinute() + " " + task.getHour() + " " + " ? " + " * " + task.getDayOfWeek();
/*  86 */     else if (execycle.equals(Integer.valueOf(CmsTask.EXECYCLE_DAY)))
/*  87 */       excep = "0  " + task.getMinute() + " " + task.getHour() + " " + " * * ?";
/*  88 */     else if (execycle.equals(Integer.valueOf(CmsTask.EXECYCLE_HOUR)))
/*  89 */       excep = "0 0 */" + task.getIntervalHour() + " * * ?";
/*  90 */     else if (execycle.equals(Integer.valueOf(CmsTask.EXECYCLE_MINUTE))) {
/*  91 */       excep = "0  */" + task.getIntervalMinute() + " * * * ?";
/*     */     }
/*  93 */     return excep;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsTaskDao dao)
/*     */   {
/* 101 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsTaskMngImpl
 * JD-Core Version:    0.6.0
 */