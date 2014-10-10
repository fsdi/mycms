/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserResume;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsUserResume
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsUserResume";
/*  18 */   public static String PROP_USER = "user";
/*  19 */   public static String PROP_TARGET_CATEGORY = "targetCategory";
/*  20 */   public static String PROP_EDU_BACK = "eduBack";
/*  21 */   public static String PROP_COMPANY_INDUSTRY = "companyIndustry";
/*  22 */   public static String PROP_JOB_CATEGORY = "jobCategory";
/*  23 */   public static String PROP_JOB_START = "jobStart";
/*  24 */   public static String PROP_EDU_GRADUATION = "eduGraduation";
/*  25 */   public static String PROP_RECENT_COMPANY = "recentCompany";
/*  26 */   public static String PROP_EDU_SCHOOL = "eduSchool";
/*  27 */   public static String PROP_JOB_NAME = "jobName";
/*  28 */   public static String PROP_JOB_DESCRIPTION = "jobDescription";
/*  29 */   public static String PROP_EDU_DISCIPLINE = "eduDiscipline";
/*  30 */   public static String PROP_TARGET_SALARY = "targetSalary";
/*  31 */   public static String PROP_SELF_EVALUATION = "selfEvaluation";
/*  32 */   public static String PROP_RESUME_NAME = "resumeName";
/*  33 */   public static String PROP_COMPANY_SCALE = "companyScale";
/*  34 */   public static String PROP_SUBORDINATES = "subordinates";
/*  35 */   public static String PROP_TARGET_WORKNATURE = "targetWorknature";
/*  36 */   public static String PROP_TARGET_WORKPLACE = "targetWorkplace";
/*  37 */   public static String PROP_ID = "id";
/*     */ 
/*  69 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String resumeName;
/*     */   private String targetWorknature;
/*     */   private String targetWorkplace;
/*     */   private String targetCategory;
/*     */   private String targetSalary;
/*     */   private String eduSchool;
/*     */   private Date eduGraduation;
/*     */   private String eduBack;
/*     */   private String eduDiscipline;
/*     */   private String recentCompany;
/*     */   private String companyIndustry;
/*     */   private String companyScale;
/*     */   private String jobName;
/*     */   private String jobCategory;
/*     */   private Date jobStart;
/*     */   private String subordinates;
/*     */   private String jobDescription;
/*     */   private String selfEvaluation;
/*     */   private CmsUser user;
/*     */ 
/*     */   public BaseCmsUserResume()
/*     */   {
/*  42 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUserResume(Integer id)
/*     */   {
/*  49 */     setId(id);
/*  50 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUserResume(Integer id, String resumeName)
/*     */   {
/*  60 */     setId(id);
/*  61 */     setResumeName(resumeName);
/*  62 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 106 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 114 */     this.id = id;
/* 115 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getResumeName()
/*     */   {
/* 125 */     return this.resumeName;
/*     */   }
/*     */ 
/*     */   public void setResumeName(String resumeName)
/*     */   {
/* 133 */     this.resumeName = resumeName;
/*     */   }
/*     */ 
/*     */   public String getTargetWorknature()
/*     */   {
/* 141 */     return this.targetWorknature;
/*     */   }
/*     */ 
/*     */   public void setTargetWorknature(String targetWorknature)
/*     */   {
/* 149 */     this.targetWorknature = targetWorknature;
/*     */   }
/*     */ 
/*     */   public String getTargetWorkplace()
/*     */   {
/* 157 */     return this.targetWorkplace;
/*     */   }
/*     */ 
/*     */   public void setTargetWorkplace(String targetWorkplace)
/*     */   {
/* 165 */     this.targetWorkplace = targetWorkplace;
/*     */   }
/*     */ 
/*     */   public String getTargetCategory()
/*     */   {
/* 173 */     return this.targetCategory;
/*     */   }
/*     */ 
/*     */   public void setTargetCategory(String targetCategory)
/*     */   {
/* 181 */     this.targetCategory = targetCategory;
/*     */   }
/*     */ 
/*     */   public String getTargetSalary()
/*     */   {
/* 189 */     return this.targetSalary;
/*     */   }
/*     */ 
/*     */   public void setTargetSalary(String targetSalary)
/*     */   {
/* 197 */     this.targetSalary = targetSalary;
/*     */   }
/*     */ 
/*     */   public String getEduSchool()
/*     */   {
/* 205 */     return this.eduSchool;
/*     */   }
/*     */ 
/*     */   public void setEduSchool(String eduSchool)
/*     */   {
/* 213 */     this.eduSchool = eduSchool;
/*     */   }
/*     */ 
/*     */   public Date getEduGraduation()
/*     */   {
/* 221 */     return this.eduGraduation;
/*     */   }
/*     */ 
/*     */   public void setEduGraduation(Date eduGraduation)
/*     */   {
/* 229 */     this.eduGraduation = eduGraduation;
/*     */   }
/*     */ 
/*     */   public String getEduBack()
/*     */   {
/* 237 */     return this.eduBack;
/*     */   }
/*     */ 
/*     */   public void setEduBack(String eduBack)
/*     */   {
/* 245 */     this.eduBack = eduBack;
/*     */   }
/*     */ 
/*     */   public String getEduDiscipline()
/*     */   {
/* 253 */     return this.eduDiscipline;
/*     */   }
/*     */ 
/*     */   public void setEduDiscipline(String eduDiscipline)
/*     */   {
/* 261 */     this.eduDiscipline = eduDiscipline;
/*     */   }
/*     */ 
/*     */   public String getRecentCompany()
/*     */   {
/* 269 */     return this.recentCompany;
/*     */   }
/*     */ 
/*     */   public void setRecentCompany(String recentCompany)
/*     */   {
/* 277 */     this.recentCompany = recentCompany;
/*     */   }
/*     */ 
/*     */   public String getCompanyIndustry()
/*     */   {
/* 285 */     return this.companyIndustry;
/*     */   }
/*     */ 
/*     */   public void setCompanyIndustry(String companyIndustry)
/*     */   {
/* 293 */     this.companyIndustry = companyIndustry;
/*     */   }
/*     */ 
/*     */   public String getCompanyScale()
/*     */   {
/* 301 */     return this.companyScale;
/*     */   }
/*     */ 
/*     */   public void setCompanyScale(String companyScale)
/*     */   {
/* 309 */     this.companyScale = companyScale;
/*     */   }
/*     */ 
/*     */   public String getJobName()
/*     */   {
/* 317 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   public void setJobName(String jobName)
/*     */   {
/* 325 */     this.jobName = jobName;
/*     */   }
/*     */ 
/*     */   public String getJobCategory()
/*     */   {
/* 333 */     return this.jobCategory;
/*     */   }
/*     */ 
/*     */   public void setJobCategory(String jobCategory)
/*     */   {
/* 341 */     this.jobCategory = jobCategory;
/*     */   }
/*     */ 
/*     */   public Date getJobStart()
/*     */   {
/* 349 */     return this.jobStart;
/*     */   }
/*     */ 
/*     */   public void setJobStart(Date jobStart)
/*     */   {
/* 357 */     this.jobStart = jobStart;
/*     */   }
/*     */ 
/*     */   public String getSubordinates()
/*     */   {
/* 365 */     return this.subordinates;
/*     */   }
/*     */ 
/*     */   public void setSubordinates(String subordinates)
/*     */   {
/* 373 */     this.subordinates = subordinates;
/*     */   }
/*     */ 
/*     */   public String getJobDescription()
/*     */   {
/* 381 */     return this.jobDescription;
/*     */   }
/*     */ 
/*     */   public void setJobDescription(String jobDescription)
/*     */   {
/* 389 */     this.jobDescription = jobDescription;
/*     */   }
/*     */ 
/*     */   public String getSelfEvaluation()
/*     */   {
/* 397 */     return this.selfEvaluation;
/*     */   }
/*     */ 
/*     */   public void setSelfEvaluation(String selfEvaluation)
/*     */   {
/* 405 */     this.selfEvaluation = selfEvaluation;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 413 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 421 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 427 */     if (obj == null) return false;
/* 428 */     if (!(obj instanceof CmsUserResume)) return false;
/*     */ 
/* 430 */     CmsUserResume cmsUserResume = (CmsUserResume)obj;
/* 431 */     if ((getId() == null) || (cmsUserResume.getId() == null)) return false;
/* 432 */     return getId().equals(cmsUserResume.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 437 */     if (-2147483648 == this.hashCode) {
/* 438 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 440 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 441 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 444 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 449 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsUserResume
 * JD-Core Version:    0.6.0
 */