/*    */ package com.jeecms.cms.task;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsTask;
/*    */ import com.jeecms.cms.manager.assist.CmsTaskMng;
/*    */ import java.text.ParseException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.quartz.CronTrigger;
/*    */ import org.quartz.JobDataMap;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class LoadTask
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsTaskMng taskMng;
/*    */ 
/*    */   @Autowired
/*    */   private Scheduler scheduler;
/*    */ 
/*    */   public void loadTask()
/*    */   {
/* 29 */     List tasks = this.taskMng.getList();
/* 30 */     if (tasks.size() > 0)
/* 31 */       for (int i = 0; i < tasks.size(); i++) {
/* 32 */         CmsTask task = (CmsTask)tasks.get(i);
/*    */ 
/* 34 */         if (!task.getEnable()) continue;
/*    */         try {
/* 36 */           JobDetail jobDetail = new JobDetail();
/*    */ 
/* 38 */           if (StringUtils.isNotBlank(task.getTaskCode())) {
/* 39 */             jobDetail.setName(task.getTaskCode());
/*    */           } else {
/* 41 */             UUID uuid = UUID.randomUUID();
/* 42 */             jobDetail.setName(uuid.toString());
/* 43 */             task.setTaskCode(uuid.toString());
/* 44 */             this.taskMng.update(task, task.getAttr());
/*    */           }
/* 46 */           jobDetail.setGroup("DEFAULT");
/*    */ 
/* 48 */           jobDetail.setJobClass(getClassByTask(task.getJobClass()));
/*    */ 
/* 50 */           jobDetail.setJobDataMap(getJobDataMap(task.getAttr()));
/* 51 */           CronTrigger cronTrigger = new CronTrigger("cron_" + i, "DEFAULT", jobDetail.getName(), "DEFAULT");
/*    */ 
/* 53 */           cronTrigger.setCronExpression(this.taskMng.getCronExpressionFromDB(task.getId()));
/*    */ 
/* 55 */           this.scheduler.scheduleJob(jobDetail, cronTrigger);
/*    */         } catch (ParseException e) {
/* 57 */           e.printStackTrace();
/*    */         } catch (SchedulerException e) {
/* 59 */           e.printStackTrace();
/*    */         }
/*    */         catch (ClassNotFoundException e) {
/* 62 */           e.printStackTrace();
/*    */         }
/*    */       }
/*    */   }
/*    */ 
/*    */   private JobDataMap getJobDataMap(Map<String, String> params)
/*    */   {
/* 75 */     JobDataMap jdm = new JobDataMap();
/* 76 */     Set keySet = params.keySet();
/* 77 */     Iterator it = keySet.iterator();
/* 78 */     while (it.hasNext()) {
/* 79 */       String key = (String)it.next();
/* 80 */       jdm.put(key, (String)params.get(key));
/*    */     }
/* 82 */     return jdm;
/*    */   }
/*    */ 
/*    */   private Class getClassByTask(String taskClassName)
/*    */     throws ClassNotFoundException
/*    */   {
/* 93 */     return Class.forName(taskClassName);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.LoadTask
 * JD-Core Version:    0.6.0
 */