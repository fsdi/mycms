/*     */ package com.jeecms.cms.entity.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.base.BaseCmsAcquisition;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class CmsAcquisition extends BaseCmsAcquisition
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String PAGE = "[page]";
/*     */   public static final int STOP = 0;
/*     */   public static final int START = 1;
/*     */   public static final int PAUSE = 2;
/*     */ 
/*     */   public boolean isStop()
/*     */   {
/*  40 */     int status = getStatus().intValue();
/*  41 */     return status == 0;
/*     */   }
/*     */ 
/*     */   public boolean isPuase()
/*     */   {
/*  50 */     int status = getStatus().intValue();
/*  51 */     return status == 2;
/*     */   }
/*     */ 
/*     */   public boolean isBreak()
/*     */   {
/*  60 */     int status = getStatus().intValue();
/*  61 */     return (status == 0) || (status == 2);
/*     */   }
/*     */ 
/*     */   public String[] getPlans() {
/*  65 */     String plan = getPlanList();
/*  66 */     if (!StringUtils.isBlank(plan)) {
/*  67 */       return StringUtils.split(plan);
/*     */     }
/*  69 */     return new String[0];
/*     */   }
/*     */ 
/*     */   public String[] getAllPlans()
/*     */   {
/*  74 */     String[] plans = getPlans();
/*  75 */     Integer start = getDynamicStart();
/*  76 */     Integer end = getDynamicEnd();
/*  77 */     if ((!StringUtils.isBlank(getDynamicAddr())) && (start != null) && 
/*  78 */       (end != null) && (start.intValue() >= 0) && (end.intValue() >= start.intValue())) {
/*  79 */       int plansLen = plans.length;
/*  80 */       String[] allPlans = new String[plansLen + end.intValue() - start.intValue() + 1];
/*  81 */       for (int i = 0; i < plansLen; i++) {
/*  82 */         allPlans[i] = plans[i];
/*     */       }
/*  84 */       int i = 0; for (int len = end.intValue() - start.intValue() + 1; i < len; i++) {
/*  85 */         allPlans[(plansLen + i)] = getDynamicAddrByNum(start.intValue() + i);
/*     */       }
/*  87 */       return allPlans;
/*     */     }
/*  89 */     return plans;
/*     */   }
/*     */ 
/*     */   public String getDynamicAddrByNum(int num)
/*     */   {
/*  94 */     return StringUtils.replace(getDynamicAddr(), "[page]", String.valueOf(num));
/*     */   }
/*     */ 
/*     */   public int getTotalNum() {
/*  98 */     int count = 0;
/*  99 */     Integer start = getDynamicStart();
/* 100 */     Integer end = getDynamicEnd();
/* 101 */     if ((!StringUtils.isBlank(getDynamicAddr())) && (start != null) && 
/* 102 */       (end != null)) {
/* 103 */       count = end.intValue() - start.intValue() + 1;
/*     */     }
/* 105 */     if (!StringUtils.isBlank(getPlanList())) {
/* 106 */       count += getPlans().length;
/*     */     }
/* 108 */     return count;
/*     */   }
/*     */ 
/*     */   public void init() {
/* 112 */     if (getStatus() == null) {
/* 113 */       setStatus(Integer.valueOf(0));
/*     */     }
/* 115 */     if (getCurrNum() == null) {
/* 116 */       setCurrNum(Integer.valueOf(0));
/*     */     }
/* 118 */     if (getCurrItem() == null) {
/* 119 */       setCurrItem(Integer.valueOf(0));
/*     */     }
/* 121 */     if (getTotalItem() == null) {
/* 122 */       setTotalItem(Integer.valueOf(0));
/*     */     }
/* 124 */     if (getPauseTime() == null) {
/* 125 */       setPauseTime(Integer.valueOf(0));
/*     */     }
/* 127 */     if (getQueue() == null)
/* 128 */       setQueue(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   public CmsAcquisition()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsAcquisition(Integer id)
/*     */   {
/* 141 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsAcquisition(Integer id, CmsUser user, ContentType type, CmsSite site, Channel channel, String name, Integer status, Integer currNum, Integer currItem, Integer totalItem, Integer pauseTime, String pageEncoding, Integer queue)
/*     */   {
/* 175 */     super(id, 
/* 164 */       user, 
/* 165 */       type, 
/* 166 */       site, 
/* 167 */       channel, 
/* 168 */       name, 
/* 169 */       status, 
/* 170 */       currNum, 
/* 171 */       currItem, 
/* 172 */       totalItem, 
/* 173 */       pauseTime, 
/* 174 */       pageEncoding, 
/* 175 */       queue);
/*     */   }
/*     */ 
/*     */   public static enum AcquisitionRepeatCheckType
/*     */   {
/*  31 */     NONE, TITLE, URL;
/*     */   }
/*     */ 
/*     */   public static enum AcquisitionResultType
/*     */   {
/*  27 */     SUCCESS, TITLESTARTNOTFOUND, TITLEENDNOTFOUND, CONTENTSTARTNOTFOUND, CONTENTENDNOTFOUND, VIEWSTARTNOTFOUND, VIEWENDNOTFOUND, AUTHORSTARTNOTFOUND, AUTHORENDNOTFOUND, ORIGINSTARTNOTFOUND, ORIGINENDNOTFOUND, DESCRISTARTNOTFOUND, DESCRIENDNOTFOUND, RELEASESTARTNOTFOUND, RELEASEENDNOTFOUND, VIEWIDSTARTNOTFOUND, VIEWIDENDNOTFOUND, TITLEEXIST, URLEXIST, UNKNOW;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsAcquisition
 * JD-Core Version:    0.6.0
 */