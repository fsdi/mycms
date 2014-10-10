/*     */ package com.jeecms.cms.task.job;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsTask;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.FtpMng;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobExecutionContext;
/*     */ import org.quartz.JobExecutionException;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerContext;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.scheduling.quartz.QuartzJobBean;
/*     */ 
/*     */ public class DistributeJob extends QuartzJobBean
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(DistributeJob.class);
/*     */   private FtpMng ftpMng;
/*     */   private RealPathResolver realPathResolver;
/*     */   private Integer ftpId;
/*     */   private List<String> folders;
/*     */ 
/*     */   protected void executeInternal(JobExecutionContext context)
/*     */     throws JobExecutionException
/*     */   {
/*     */     try
/*     */     {
/*  40 */       SchedulerContext schCtx = context.getScheduler().getContext();
/*  41 */       JobDataMap jdm = context.getJobDetail().getJobDataMap();
/*     */ 
/*  43 */       this.ftpId = Integer.valueOf(Integer.parseInt((String)jdm.get(CmsTask.TASK_PARAM_FTP_ID)));
/*     */ 
/*  45 */       this.folders = getFolderValues(jdm);
/*     */ 
/*  47 */       ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
/*  48 */       this.ftpMng = ((FtpMng)appCtx.getBean("ftpMng"));
/*  49 */       this.realPathResolver = ((RealPathResolver)appCtx.getBean("realPathResolver"));
/*  50 */       distribute();
/*     */     }
/*     */     catch (SchedulerException e1) {
/*  53 */       e1.printStackTrace();
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  56 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/*  59 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void distribute()
/*     */     throws FileNotFoundException, IOException
/*     */   {
/*  67 */     Ftp ftp = this.ftpMng.findById(this.ftpId);
/*  68 */     for (String folder : this.folders) {
/*  69 */       log.info("distribute folder  " + folder);
/*  70 */       String folderPath = this.realPathResolver.get(folder);
/*  71 */       String rootPath = this.realPathResolver.get("");
/*  72 */       if ((StringUtils.isNotBlank(folder)) && (StringUtils.isNotBlank(folderPath)))
/*  73 */         ftp.storeByFloder(folderPath, rootPath);
/*     */     }
/*     */   }
/*     */ 
/*     */   private List<String> getFolderValues(JobDataMap jdm)
/*     */   {
/*  84 */     List<String> folderKeys = getFolderdKeys(jdm);
/*  85 */     List folderValues = new ArrayList();
/*  86 */     for (String key : folderKeys) {
/*  87 */       folderValues.add(getJobDataValue(jdm, key));
/*     */     }
/*  89 */     return folderValues;
/*     */   }
/*     */ 
/*     */   private List<String> getFolderdKeys(JobDataMap jdm)
/*     */   {
/*  98 */     List keys = new ArrayList();
/*  99 */     Set keySet = jdm.keySet();
/* 100 */     Iterator it = keySet.iterator();
/* 101 */     while (it.hasNext()) {
/* 102 */       String key = (String)it.next();
/* 103 */       if (key.startsWith(CmsTask.TASK_PARAM_FOLDER_PREFIX)) {
/* 104 */         keys.add(key);
/*     */       }
/*     */     }
/* 107 */     return keys;
/*     */   }
/*     */ 
/*     */   private String getJobDataValue(JobDataMap jdm, String key)
/*     */   {
/* 117 */     if (StringUtils.isBlank(key)) {
/* 118 */       return null;
/*     */     }
/* 120 */     return (String)jdm.get(key);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.task.job.DistributeJob
 * JD-Core Version:    0.6.0
 */