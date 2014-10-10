/*    */ package com.jeecms.cms.task.job;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsTask;
/*    */ import com.jeecms.cms.staticpage.StaticPageSvc;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.lang.StringUtils;
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
/*    */ import org.springframework.scheduling.quartz.QuartzJobBean;
/*    */ 
/*    */ public class ContentStaticJob extends QuartzJobBean
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(ContentStaticJob.class);
/*    */   private StaticPageSvc staticPageSvc;
/*    */   private Integer channelId;
/*    */   private Integer siteId;
/*    */ 
/*    */   protected void executeInternal(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 30 */       SchedulerContext schCtx = context.getScheduler().getContext();
/*    */ 
/* 32 */       ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
/* 33 */       this.staticPageSvc = ((StaticPageSvc)appCtx.getBean("staticPageSvc"));
/* 34 */       JobDataMap jdm = context.getJobDetail().getJobDataMap();
/*    */ 
/* 36 */       String channelIdStr = (String)jdm.get(CmsTask.TASK_PARAM_CHANNEL_ID);
/* 37 */       if (!StringUtils.isBlank(channelIdStr)) {
/* 38 */         this.channelId = Integer.valueOf(Integer.parseInt(channelIdStr));
/* 39 */         if (this.channelId.equals(Integer.valueOf(0))) {
/* 40 */           this.channelId = null;
/*    */         }
/*    */       }
/*    */ 
/* 44 */       String siteIdStr = (String)jdm.get(CmsTask.TASK_PARAM_SITE_ID);
/* 45 */       if (!StringUtils.isBlank(siteIdStr))
/* 46 */         this.siteId = Integer.valueOf(Integer.parseInt(siteIdStr));
/*    */     }
/*    */     catch (SchedulerException e1)
/*    */     {
/* 50 */       e1.printStackTrace();
/*    */     }
/* 52 */     staticContent();
/*    */   }
/*    */ 
/*    */   public void staticContent() {
/* 56 */     log.info("static content page");
/*    */     try {
/* 58 */       this.staticPageSvc.content(this.siteId, this.channelId, null);
/*    */     }
/*    */     catch (IOException e) {
/* 61 */       e.printStackTrace();
/*    */     }
/*    */     catch (TemplateException e) {
/* 64 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.job.ContentStaticJob
 * JD-Core Version:    0.6.0
 */