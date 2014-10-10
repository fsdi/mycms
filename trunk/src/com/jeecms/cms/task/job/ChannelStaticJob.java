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
/*    */ public class ChannelStaticJob extends QuartzJobBean
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(ChannelStaticJob.class);
/*    */   private StaticPageSvc staticPageSvc;
/*    */   private Integer channelId;
/*    */   private Integer siteId;
/*    */ 
/*    */   protected void executeInternal(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 29 */       SchedulerContext schCtx = context.getScheduler().getContext();
/*    */ 
/* 31 */       ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
/* 32 */       this.staticPageSvc = ((StaticPageSvc)appCtx.getBean("staticPageSvc"));
/* 33 */       JobDataMap jdm = context.getJobDetail().getJobDataMap();
/*    */ 
/* 35 */       String channelIdStr = (String)jdm.get(CmsTask.TASK_PARAM_CHANNEL_ID);
/* 36 */       if (!StringUtils.isBlank(channelIdStr)) {
/* 37 */         this.channelId = Integer.valueOf(Integer.parseInt(channelIdStr));
/* 38 */         if (this.channelId.equals(Integer.valueOf(0))) {
/* 39 */           this.channelId = null;
/*    */         }
/*    */       }
/*    */ 
/* 43 */       String siteIdStr = (String)jdm.get(CmsTask.TASK_PARAM_SITE_ID);
/* 44 */       if (!StringUtils.isBlank(siteIdStr))
/* 45 */         this.siteId = Integer.valueOf(Integer.parseInt(siteIdStr));
/*    */     }
/*    */     catch (SchedulerException e1)
/*    */     {
/* 49 */       e1.printStackTrace();
/*    */     }
/* 51 */     staitcChannel();
/*    */   }
/*    */ 
/*    */   public void staitcChannel() {
/* 55 */     log.info("staitc Channel page");
/*    */     try {
/* 57 */       this.staticPageSvc.channel(this.siteId, this.channelId, true);
/*    */     }
/*    */     catch (IOException e) {
/* 60 */       e.printStackTrace();
/*    */     }
/*    */     catch (TemplateException e) {
/* 63 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.job.ChannelStaticJob
 * JD-Core Version:    0.6.0
 */