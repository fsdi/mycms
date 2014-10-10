/*    */ package com.jeecms.cms.task.job;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsTask;
/*    */ import com.jeecms.cms.service.AcquisitionSvc;
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
/*    */ public class AcquisiteJob extends QuartzJobBean
/*    */ {
/* 19 */   private static final Logger log = LoggerFactory.getLogger(AcquisiteJob.class);
/*    */   private AcquisitionSvc acquisitionSvc;
/*    */   private Integer acquId;
/*    */ 
/*    */   protected void executeInternal(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 22 */       SchedulerContext schCtx = context.getScheduler().getContext();
/* 23 */       JobDataMap jdm = context.getJobDetail().getJobDataMap();
/*    */ 
/* 25 */       this.acquId = Integer.valueOf(Integer.parseInt((String)jdm.get(CmsTask.TASK_PARAM_ACQU_ID)));
/*    */ 
/* 27 */       ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
/* 28 */       this.acquisitionSvc = ((AcquisitionSvc)appCtx.getBean("acquisitionSvc"));
/*    */     }
/*    */     catch (SchedulerException e1) {
/* 31 */       e1.printStackTrace();
/*    */     }
/* 33 */     acquStart();
/*    */   }
/*    */ 
/*    */   public void acquStart() {
/* 37 */     log.info("task acquisite " + this.acquId);
/* 38 */     this.acquisitionSvc.start(this.acquId);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.job.AcquisiteJob
 * JD-Core Version:    0.6.0
 */