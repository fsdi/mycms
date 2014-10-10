/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsTask;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class BaseCmsTask
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsTask";
/*  18 */   public static String PROP_USER = "user";
/*  19 */   public static String PROP_JOB_CLASS = "jobClass";
/*  20 */   public static String PROP_SITE = "site";
/*  21 */   public static String PROP_TYPE = "type";
/*  22 */   public static String PROP_INTERVAL_MINUTE = "intervalMinute";
/*  23 */   public static String PROP_TASK_CODE = "taskCode";
/*  24 */   public static String PROP_EXECYCLE = "execycle";
/*  25 */   public static String PROP_CRON_EXPRESSION = "cronExpression";
/*  26 */   public static String PROP_INTERVAL_HOUR = "intervalHour";
/*  27 */   public static String PROP_INTERVAL_UNIT = "intervalUnit";
/*  28 */   public static String PROP_DAY_OF_WEEK = "dayOfWeek";
/*  29 */   public static String PROP_NAME = "name";
/*  30 */   public static String PROP_DAY_OF_MONTH = "dayOfMonth";
/*  31 */   public static String PROP_HOUR = "hour";
/*  32 */   public static String PROP_ENABLE = "enable";
/*  33 */   public static String PROP_CREATE_TIME = "createTime";
/*  34 */   public static String PROP_MINUTE = "minute";
/*  35 */   public static String PROP_ID = "id";
/*  36 */   public static String PROP_REMARK = "remark";
/*     */ 
/*  80 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String taskCode;
/*     */   private Integer type;
/*     */   private String name;
/*     */   private String jobClass;
/*     */   private Integer execycle;
/*     */   private Integer dayOfMonth;
/*     */   private Integer dayOfWeek;
/*     */   private Integer hour;
/*     */   private Integer minute;
/*     */   private Integer intervalHour;
/*     */   private Integer intervalMinute;
/*     */   private Integer intervalUnit;
/*     */   private String cronExpression;
/*     */   private boolean enable;
/*     */   private String remark;
/*     */   private Date createTime;
/*     */   private CmsUser user;
/*     */   private CmsSite site;
/*     */   private Map<String, String> attr;
/*     */ 
/*     */   public BaseCmsTask()
/*     */   {
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsTask(Integer id)
/*     */   {
/*  48 */     setId(id);
/*  49 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsTask(Integer id, CmsUser user, CmsSite site, Integer type, String name, String jobClass, boolean enable, Date createTime)
/*     */   {
/*  65 */     setId(id);
/*  66 */     setUser(user);
/*  67 */     setSite(site);
/*  68 */     setType(type);
/*  69 */     setName(name);
/*  70 */     setJobClass(jobClass);
/*  71 */     setEnable(enable);
/*  72 */     setCreateTime(createTime);
/*  73 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 119 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 127 */     this.id = id;
/* 128 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTaskCode()
/*     */   {
/* 138 */     return this.taskCode;
/*     */   }
/*     */ 
/*     */   public void setTaskCode(String taskCode)
/*     */   {
/* 146 */     this.taskCode = taskCode;
/*     */   }
/*     */ 
/*     */   public Integer getType()
/*     */   {
/* 154 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(Integer type)
/*     */   {
/* 162 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 170 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 178 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getJobClass()
/*     */   {
/* 186 */     return this.jobClass;
/*     */   }
/*     */ 
/*     */   public void setJobClass(String jobClass)
/*     */   {
/* 194 */     this.jobClass = jobClass;
/*     */   }
/*     */ 
/*     */   public Integer getExecycle()
/*     */   {
/* 202 */     return this.execycle;
/*     */   }
/*     */ 
/*     */   public void setExecycle(Integer execycle)
/*     */   {
/* 210 */     this.execycle = execycle;
/*     */   }
/*     */ 
/*     */   public Integer getDayOfMonth()
/*     */   {
/* 218 */     return this.dayOfMonth;
/*     */   }
/*     */ 
/*     */   public void setDayOfMonth(Integer dayOfMonth)
/*     */   {
/* 226 */     this.dayOfMonth = dayOfMonth;
/*     */   }
/*     */ 
/*     */   public Integer getDayOfWeek()
/*     */   {
/* 234 */     return this.dayOfWeek;
/*     */   }
/*     */ 
/*     */   public void setDayOfWeek(Integer dayOfWeek)
/*     */   {
/* 242 */     this.dayOfWeek = dayOfWeek;
/*     */   }
/*     */ 
/*     */   public Integer getHour()
/*     */   {
/* 250 */     return this.hour;
/*     */   }
/*     */ 
/*     */   public void setHour(Integer hour)
/*     */   {
/* 258 */     this.hour = hour;
/*     */   }
/*     */ 
/*     */   public Integer getMinute()
/*     */   {
/* 266 */     return this.minute;
/*     */   }
/*     */ 
/*     */   public void setMinute(Integer minute)
/*     */   {
/* 274 */     this.minute = minute;
/*     */   }
/*     */ 
/*     */   public Integer getIntervalHour()
/*     */   {
/* 282 */     return this.intervalHour;
/*     */   }
/*     */ 
/*     */   public void setIntervalHour(Integer intervalHour)
/*     */   {
/* 290 */     this.intervalHour = intervalHour;
/*     */   }
/*     */ 
/*     */   public Integer getIntervalMinute()
/*     */   {
/* 298 */     return this.intervalMinute;
/*     */   }
/*     */ 
/*     */   public void setIntervalMinute(Integer intervalMinute)
/*     */   {
/* 306 */     this.intervalMinute = intervalMinute;
/*     */   }
/*     */ 
/*     */   public Integer getIntervalUnit()
/*     */   {
/* 314 */     return this.intervalUnit;
/*     */   }
/*     */ 
/*     */   public void setIntervalUnit(Integer intervalUnit)
/*     */   {
/* 322 */     this.intervalUnit = intervalUnit;
/*     */   }
/*     */ 
/*     */   public String getCronExpression()
/*     */   {
/* 330 */     return this.cronExpression;
/*     */   }
/*     */ 
/*     */   public void setCronExpression(String cronExpression)
/*     */   {
/* 338 */     this.cronExpression = cronExpression;
/*     */   }
/*     */ 
/*     */   public boolean isEnable()
/*     */   {
/* 346 */     return this.enable;
/*     */   }
/*     */ 
/*     */   public void setEnable(boolean enable)
/*     */   {
/* 354 */     this.enable = enable;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 362 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 370 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 378 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 386 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 394 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 402 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 410 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 418 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 426 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 434 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 440 */     if (obj == null) return false;
/* 441 */     if (!(obj instanceof CmsTask)) return false;
/*     */ 
/* 443 */     CmsTask cmsTask = (CmsTask)obj;
/* 444 */     if ((getId() == null) || (cmsTask.getId() == null)) return false;
/* 445 */     return getId().equals(cmsTask.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 450 */     if (-2147483648 == this.hashCode) {
/* 451 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 453 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 454 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 457 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 462 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsTask
 * JD-Core Version:    0.6.0
 */