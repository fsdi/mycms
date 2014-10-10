/*    */ package com.jeecms.cms.task.job;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsTask;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*    */ import com.jeecms.cms.staticpage.StaticPageSvc;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.quartz.JobDataMap;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerContext;
/*    */ import org.quartz.SchedulerException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.orm.hibernate3.SessionFactoryUtils;
/*    */ import org.springframework.scheduling.quartz.QuartzJobBean;
/*    */ 
/*    */ public class IndexStaticJob extends QuartzJobBean
/*    */ {
/* 30 */   private static final Logger log = LoggerFactory.getLogger(IndexStaticJob.class);
/*    */   private StaticPageSvc staticPageSvc;
/*    */   private CmsSiteMng cmsSiteMng;
/*    */   private SessionFactory sessionFactory;
/*    */   private Integer siteId;
/*    */ 
/*    */   protected void executeInternal(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 33 */       SchedulerContext schCtx = context.getScheduler().getContext();
/* 34 */       JobDataMap jdm = context.getJobDetail().getJobDataMap();
/*    */ 
/* 36 */       ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
/* 37 */       this.cmsSiteMng = ((CmsSiteMng)appCtx.getBean("cmsSiteMng"));
/* 38 */       this.staticPageSvc = ((StaticPageSvc)appCtx.getBean("staticPageSvc"));
/* 39 */       this.sessionFactory = ((SessionFactory)appCtx.getBean("sessionFactory"));
/* 40 */       this.siteId = Integer.valueOf(Integer.parseInt((String)jdm.get(CmsTask.TASK_PARAM_SITE_ID)));
/*    */     }
/*    */     catch (SchedulerException e1) {
/* 43 */       e1.printStackTrace();
/*    */     }
/* 45 */     staticIndex();
/*    */   }
/*    */   public void staticIndex() {
/* 48 */     log.info("static index  page");
/* 49 */     CmsSite site = this.cmsSiteMng.findById(this.siteId);
/* 50 */     Session session = SessionFactoryUtils.getSession(this.sessionFactory, true);
/* 51 */     session = this.sessionFactory.openSession();
/* 52 */     session.beginTransaction();
/* 53 */     site = (CmsSite)session.get(site.getClass(), site.getId());
/*    */     try {
/* 55 */       this.staticPageSvc.index(site);
/*    */     } catch (IOException e) {
/* 57 */       log.error("static index error!", e);
/*    */     } catch (TemplateException e) {
/* 59 */       log.error("static index error!", e);
/*    */     }
/* 61 */     session.flush();
/* 62 */     session.close();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.job.IndexStaticJob
 * JD-Core Version:    0.6.0
 */