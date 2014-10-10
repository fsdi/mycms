/*     */ package com.jeecms.cms.statistic;
/*     */ 
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.util.ArithmeticUtils;
/*     */ import com.jeecms.common.util.DateFormatUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional(readOnly=true)
/*     */ public class CmsStatisticSvcImpl
/*     */   implements CmsStatisticSvc
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CmsStatisticDao dao;
/*     */ 
/*     */   public List<CmsStatistic> statisticByModel(int type, CmsStatistic.CmsStatisticModel statisticModel, Integer year, Integer month, Integer day, Map<String, Object> restrictions)
/*     */   {
/*  49 */     if (month == null)
/*  50 */       month = Integer.valueOf(0);
/*     */     else {
/*  52 */       month = Integer.valueOf(month.intValue() - 1);
/*     */     }
/*  54 */     if (day == null)
/*  55 */       day = Integer.valueOf(1);
/*     */     Calendar calendar;
/*  57 */     if (year == null)
/*  58 */       calendar = new GregorianCalendar();
/*     */     else {
/*  60 */       calendar = new GregorianCalendar(year.intValue(), month.intValue(), day.intValue());
/*     */     }
/*  62 */     return statisticByModel(type, statisticModel, calendar, restrictions);
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> statisticByModel(int type, CmsStatistic.CmsStatisticModel statisticModel, Calendar calendar, Map<String, Object> restrictions)
/*     */   {
/*  68 */     switch (statisticModel) {
/*     */     case day:
/*  70 */       return statisticByDay(type, calendar, restrictions);
/*     */     case month:
/*  73 */       return statisticByWeek(type, calendar, restrictions);
/*     */     case week:
/*  76 */       return statisticByMonth(type, calendar, restrictions);
/*     */     case year:
/*  79 */       return statisticByYear(type, calendar, restrictions);
/*     */     }
/*     */ 
/*  82 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> statisticByDay(int type, Calendar calendar, Map<String, Object> restrictions)
/*     */   {
/*  87 */     calendar = clearTime(calendar);
/*  88 */     List list = new ArrayList();
/*  89 */     long total = 0L; long count = 0L;
/*     */ 
/*  91 */     Calendar clone = (Calendar)calendar.clone();
/*  92 */     total = statistic(type, getTimeRange(21, clone), restrictions);
/*  93 */     for (int i = 0; i < 24; i++) {
/*  94 */       calendar.set(11, i);
/*  95 */       Date begin = calendar.getTime();
/*  96 */       calendar.set(11, i + 1);
/*  97 */       Date end = calendar.getTime();
/*  98 */       count = statistic(type, CmsStatistic.TimeRange.getInstance(begin, end), restrictions);
/*  99 */       CmsStatistic bean = new CmsStatistic(format(i), Long.valueOf(count), Long.valueOf(total));
/* 100 */       list.add(bean);
/*     */     }
/* 102 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> statisticByWeek(int type, Calendar calendar, Map<String, Object> restrictions)
/*     */   {
/* 107 */     calendar = clearTime(calendar);
/* 108 */     flush(calendar);
/* 109 */     List list = new ArrayList();
/* 110 */     long total = 0L; long count = 0L;
/*     */ 
/* 112 */     Calendar clone = (Calendar)calendar.clone();
/* 113 */     total = statistic(type, getTimeRange(23, clone), restrictions);
/* 114 */     for (int i = 1; i <= 7; i++) {
/* 115 */       calendar.set(7, i);
/* 116 */       Date begin = calendar.getTime();
/* 117 */       if (i == 7)
/* 118 */         calendar.add(7, 1);
/*     */       else {
/* 120 */         calendar.set(7, i + 1);
/*     */       }
/* 122 */       Date end = calendar.getTime();
/* 123 */       count = statistic(type, CmsStatistic.TimeRange.getInstance(begin, end), restrictions);
/* 124 */       CmsStatistic bean = new CmsStatistic(String.valueOf(i), Long.valueOf(count), 
/* 125 */         Long.valueOf(total));
/* 126 */       list.add(bean);
/*     */     }
/* 128 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> statisticByMonth(int type, Calendar calendar, Map<String, Object> restrictions)
/*     */   {
/* 133 */     List list = new ArrayList();
/* 134 */     int year = getYear(calendar);
/* 135 */     int month = getMonth(calendar);
/* 136 */     long total = 0L; long count = 0L;
/* 137 */     int day = 1;
/*     */ 
/* 139 */     calendar = new GregorianCalendar(year, month, day);
/* 140 */     total = statistic(type, getTimeRange(24, (Calendar)calendar.clone()), restrictions);
/* 141 */     Calendar clone = (Calendar)calendar.clone();
/* 142 */     clone.set(2, month + 1);
/* 143 */     Date end = clone.getTime();
/* 144 */     clone.add(5, -1);
/* 145 */     int days = getDay(clone);
/* 146 */     for (int i = 1; i <= days; i++) {
/* 147 */       calendar.set(5, i);
/* 148 */       Date begin = calendar.getTime();
/* 149 */       calendar.set(5, i + 1);
/* 150 */       end = calendar.getTime();
/* 151 */       count = statistic(type, CmsStatistic.TimeRange.getInstance(begin, end), restrictions);
/* 152 */       CmsStatistic bean = new CmsStatistic(String.valueOf(i), Long.valueOf(count), 
/* 153 */         Long.valueOf(total));
/* 154 */       list.add(bean);
/*     */     }
/* 156 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> statisticByYear(int type, Calendar calendar, Map<String, Object> restrictions)
/*     */   {
/* 161 */     List list = new ArrayList();
/* 162 */     int year = getYear(calendar);
/* 163 */     long total = 0L; long count = 0L;
/* 164 */     int day = 1; int month = 0;
/*     */ 
/* 166 */     calendar = new GregorianCalendar(year, month, day);
/* 167 */     Calendar clone = (Calendar)calendar.clone();
/* 168 */     total = statistic(type, getTimeRange(25, clone), restrictions);
/* 169 */     for (int i = 0; i < 12; i++) {
/* 170 */       calendar.set(2, i);
/* 171 */       Date begin = calendar.getTime();
/* 172 */       calendar.set(2, i + 1);
/* 173 */       Date end = calendar.getTime();
/* 174 */       count = statistic(type, CmsStatistic.TimeRange.getInstance(begin, end), restrictions);
/* 175 */       CmsStatistic bean = new CmsStatistic(String.valueOf(i + 1), Long.valueOf(count), 
/* 176 */         Long.valueOf(total));
/* 177 */       list.add(bean);
/*     */     }
/* 179 */     return list;
/*     */   }
/*     */ 
/*     */   private long statistic(int type, CmsStatistic.TimeRange timeRange, Map<String, Object> restrictions)
/*     */   {
/* 184 */     switch (type) {
/*     */     case 1:
/* 186 */       return this.dao.memberStatistic(timeRange);
/*     */     case 2:
/* 189 */       return this.dao.contentStatistic(timeRange, restrictions);
/*     */     case 3:
/* 192 */       return this.dao.commentStatistic(timeRange, restrictions);
/*     */     case 4:
/* 195 */       return this.dao.guestbookStatistic(timeRange, restrictions);
/*     */     }
/*     */ 
/* 198 */     return 0L;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> pvStatistic(Integer siteId) {
/* 202 */     List list = new ArrayList();
/*     */ 
/* 205 */     Calendar calendar = new GregorianCalendar();
/* 206 */     calendar = clearTime(calendar);
/* 207 */     Date begin = calendar.getTime();
/* 208 */     long count = this.dao.getPvCountByTimeRange(siteId, getTimeRange(21));
/* 209 */     CmsStatistic today = new CmsStatistic("statistic.pv.today", Long.valueOf(count));
/* 210 */     today.setVice(DateFormatUtils.formatDate(begin));
/* 211 */     list.add(today);
/* 212 */     calendar.add(5, -1);
/* 213 */     begin = calendar.getTime();
/* 214 */     count = this.dao.getPvCountByTimeRange(siteId, getTimeRange(22));
/* 215 */     CmsStatistic yesterday = new CmsStatistic("statistic.pv.yesterday", 
/* 216 */       Long.valueOf(count));
/* 217 */     yesterday.setVice(DateFormatUtils.formatDate(begin));
/* 218 */     list.add(yesterday);
/* 219 */     count = avg(this.dao.getPvCountByGroup(siteId));
/* 220 */     CmsStatistic avg = new CmsStatistic("statistic.pv.avg", Long.valueOf(count));
/* 221 */     list.add(avg);
/* 222 */     Object[] objs = max(this.dao.getPvCountByGroup(siteId));
/* 223 */     count = ((Integer)objs[0]).intValue();
/* 224 */     CmsStatistic max = new CmsStatistic("statistic.pv.max", Long.valueOf(count));
/* 225 */     max.setVice((String)objs[1]);
/* 226 */     list.add(max);
/* 227 */     count = this.dao.getPvCount(siteId);
/* 228 */     CmsStatistic total = new CmsStatistic("statistic.pv.total", Long.valueOf(count));
/* 229 */     list.add(total);
/* 230 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> uniqueIpStatistic(Integer siteId) {
/* 234 */     List list = new ArrayList();
/*     */ 
/* 237 */     Calendar calendar = new GregorianCalendar();
/* 238 */     calendar = clearTime(calendar);
/* 239 */     Date begin = calendar.getTime();
/* 240 */     long count = this.dao.getUniqueIpCountByTimeRange(siteId, getTimeRange(21));
/* 241 */     CmsStatistic today = new CmsStatistic("statistic.uniqueIp.today", Long.valueOf(count));
/* 242 */     today.setVice(DateFormatUtils.formatDate(begin));
/* 243 */     list.add(today);
/* 244 */     calendar.add(5, -1);
/* 245 */     begin = calendar.getTime();
/* 246 */     count = this.dao.getUniqueIpCountByTimeRange(siteId, getTimeRange(22));
/* 247 */     CmsStatistic yesterday = new CmsStatistic(
/* 248 */       "statistic.uniqueIp.yesterday", Long.valueOf(count));
/* 249 */     yesterday.setVice(DateFormatUtils.formatDate(begin));
/* 250 */     list.add(yesterday);
/* 251 */     count = avg(this.dao.getUniqueIpCountByGroup(siteId));
/* 252 */     CmsStatistic avg = new CmsStatistic("statistic.uniqueIp.avg", Long.valueOf(count));
/* 253 */     list.add(avg);
/* 254 */     Object[] objs = max(this.dao.getUniqueIpCountByGroup(siteId));
/* 255 */     count = ((Integer)objs[0]).intValue();
/* 256 */     CmsStatistic max = new CmsStatistic("statistic.uniqueIp.max", Long.valueOf(count));
/* 257 */     max.setVice((String)objs[1]);
/* 258 */     list.add(max);
/* 259 */     count = this.dao.getUniqueIpCount(siteId);
/* 260 */     CmsStatistic total = new CmsStatistic("statistic.uniqueIp.total", Long.valueOf(count));
/* 261 */     list.add(total);
/* 262 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> uniqueVisitorStatistic(Integer siteId) {
/* 266 */     List list = new ArrayList();
/*     */ 
/* 269 */     Calendar calendar = new GregorianCalendar();
/* 270 */     calendar = clearTime(calendar);
/* 271 */     Date begin = calendar.getTime();
/* 272 */     long count = this.dao.getUniqueVisitorCountByTimeRange(siteId, getTimeRange(21));
/* 273 */     CmsStatistic today = new CmsStatistic("statistic.uniqueVisitor.today", 
/* 274 */       Long.valueOf(count));
/* 275 */     today.setVice(DateFormatUtils.formatDate(begin));
/* 276 */     list.add(today);
/* 277 */     calendar.add(5, -1);
/* 278 */     begin = calendar.getTime();
/* 279 */     count = this.dao.getUniqueVisitorCountByTimeRange(siteId, getTimeRange(22));
/* 280 */     CmsStatistic yesterday = new CmsStatistic(
/* 281 */       "statistic.uniqueVisitor.yesterday", Long.valueOf(count));
/* 282 */     yesterday.setVice(DateFormatUtils.formatDate(begin));
/* 283 */     list.add(yesterday);
/* 284 */     count = avg(this.dao.getUniqueVisitorCountByGroup(siteId));
/* 285 */     CmsStatistic avg = new CmsStatistic("statistic.uniqueVisitor.avg", 
/* 286 */       Long.valueOf(count));
/* 287 */     list.add(avg);
/* 288 */     Object[] objs = max(this.dao.getUniqueVisitorCountByGroup(siteId));
/* 289 */     count = ((Integer)objs[0]).intValue();
/* 290 */     CmsStatistic max = new CmsStatistic("statistic.uniqueVisitor.max", 
/* 291 */       Long.valueOf(count));
/* 292 */     max.setVice((String)objs[1]);
/* 293 */     list.add(max);
/* 294 */     count = this.dao.getUniqueVisitorCount(siteId);
/* 295 */     CmsStatistic total = new CmsStatistic("statistic.uniqueVisitor.total", 
/* 296 */       Long.valueOf(count));
/* 297 */     list.add(total);
/* 298 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> avgViewsStatistic(Integer siteId) {
/* 302 */     List list = new ArrayList();
/*     */ 
/* 305 */     Calendar calendar = new GregorianCalendar();
/* 306 */     calendar = clearTime(calendar);
/* 307 */     Date begin = calendar.getTime();
/* 308 */     long pvs = this.dao.getPvCountByTimeRange(siteId, getTimeRange(21));
/* 309 */     long visitors = this.dao.getUniqueVisitorCountByTimeRange(siteId, getTimeRange(21));
/* 310 */     CmsStatistic today = new CmsStatistic("statistic.avgViews.today", 
/* 311 */       Long.valueOf(pvs / 
/* 311 */       ArithmeticUtils.dividend(visitors)));
/* 312 */     today.setVice(DateFormatUtils.formatDate(begin));
/* 313 */     list.add(today);
/* 314 */     calendar.add(5, -1);
/* 315 */     begin = calendar.getTime();
/* 316 */     pvs = this.dao.getPvCountByTimeRange(siteId, getTimeRange(22));
/* 317 */     visitors = this.dao.getUniqueVisitorCountByTimeRange(siteId, getTimeRange(22));
/* 318 */     CmsStatistic yesterday = new CmsStatistic(
/* 319 */       "statistic.avgViews.yesterday", Long.valueOf(pvs / ArithmeticUtils.dividend(visitors)));
/* 320 */     yesterday.setVice(DateFormatUtils.formatDate(begin));
/* 321 */     list.add(yesterday);
/* 322 */     long count = avg(this.dao.getPvCountByGroup(siteId), this.dao.getUniqueVisitorCountByGroup(siteId));
/* 323 */     CmsStatistic avg = new CmsStatistic("statistic.avgViews.avg", Long.valueOf(count));
/* 324 */     list.add(avg);
/* 325 */     Object[] objs = max(this.dao.getPvCountByGroup(siteId), this.dao.getUniqueVisitorCountByGroup(siteId));
/* 326 */     count = ((Integer)objs[0]).intValue();
/* 327 */     CmsStatistic max = new CmsStatistic("statistic.avgViews.max", Long.valueOf(count));
/* 328 */     max.setVice((String)objs[1]);
/* 329 */     list.add(max);
/* 330 */     pvs = this.dao.getPvCount(siteId);
/* 331 */     visitors = this.dao.getUniqueVisitorCount(siteId);
/* 332 */     CmsStatistic total = new CmsStatistic("statistic.avgViews.total", 
/* 333 */       Long.valueOf(pvs / 
/* 333 */       ArithmeticUtils.dividend(visitors)));
/* 334 */     list.add(total);
/* 335 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CmsStatistic> flowStatistic(int type, Integer siteId) {
/* 339 */     List list = new ArrayList();
/* 340 */     switch (type) {
/*     */     case 11:
/* 342 */       return pvStatistic(siteId);
/*     */     case 12:
/* 345 */       return uniqueIpStatistic(siteId);
/*     */     case 13:
/* 348 */       return uniqueVisitorStatistic(siteId);
/*     */     case 14:
/* 351 */       return avgViewsStatistic(siteId);
/*     */     }
/*     */ 
/* 354 */     return list;
/*     */   }
/*     */ 
/*     */   public Pagination flowAnalysisPage(String groupCondition, Integer siteId, Integer pageNo, Integer pageSize)
/*     */   {
/* 360 */     List list = new ArrayList();
/* 361 */     Pagination pagination = this.dao.flowAnalysisPage(groupCondition, siteId, 
/* 362 */       pageNo, pageSize);
/* 363 */     long total = this.dao.flowAnalysisTotal(siteId);
/* 364 */     for (Object objArr1 : pagination.getList()) {
			  Object[] objArr = (Object[])objArr1;
/* 365 */       CmsStatistic cmsStatistic = new CmsStatistic((String)objArr[1], 
/* 366 */         (Long)objArr[0], Long.valueOf(total));
/* 367 */       list.add(cmsStatistic);
/*     */     }
/* 369 */     pagination.setList(list);
/* 370 */     return pagination;
/*     */   }
/*     */ 
/*     */   public Map<String, List<CmsStatistic>> getWelcomeSiteFlowData(Integer siteId) {
/* 374 */     Map map = new HashMap();
/* 375 */     map.put("today", getListByTimeRange(siteId, getTimeRange(21)));
/* 376 */     map.put("yesterday", getListByTimeRange(siteId, getTimeRange(22)));
/* 377 */     map.put("thisweek", getListByTimeRange(siteId, getTimeRange(23)));
/* 378 */     map.put("thismonth", getListByTimeRange(siteId, getTimeRange(24)));
/* 379 */     map.put("total", getListByTimeRange(siteId, getTimeRange(-1)));
/* 380 */     return map;
/*     */   }
/*     */   @Transactional
/*     */   public void flowInit(Integer siteId, Date startDate, Date endDate) {
/* 385 */     this.dao.flowInit(siteId, startDate, endDate);
/*     */   }
/*     */ 
/*     */   private List<CmsStatistic> getListByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange)
/*     */   {
/* 393 */     List list = new ArrayList();
/* 394 */     list.add(new CmsStatistic(Long.valueOf(getPvCountByTimeRange(siteId, timeRange))));
/* 395 */     list.add(new CmsStatistic(Long.valueOf(getUniqueIpCountByTimeRange(siteId, timeRange))));
/* 396 */     list.add(new CmsStatistic(Long.valueOf(getUniqueVisitorCountByTimeRange(siteId, timeRange))));
/* 397 */     return list;
/*     */   }
/*     */ 
/*     */   private long getPvCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 401 */     return this.dao.getPvCountByTimeRange(siteId, timeRange);
/*     */   }
/*     */ 
/*     */   private long getUniqueIpCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 405 */     return this.dao.getUniqueIpCountByTimeRange(siteId, timeRange);
/*     */   }
/*     */ 
/*     */   private long getUniqueVisitorCountByTimeRange(Integer siteId, CmsStatistic.TimeRange timeRange) {
/* 409 */     return this.dao.getUniqueVisitorCountByTimeRange(siteId, timeRange);
/*     */   }
/*     */ 
/*     */   private String format(int time) {
/* 413 */     Calendar calendar = clearTime(new GregorianCalendar());
/* 414 */     calendar.set(11, time);
/*     */ 
/* 416 */     String begin = DateFormatUtils.format(calendar.getTime(), "HH:mm:ss");
/* 417 */     calendar.add(11, 1);
/* 418 */     String end = DateFormatUtils.format(calendar.getTime(), "HH:mm:ss");
/* 419 */     return begin + "-" + end;
/*     */   }
/*     */ 
/*     */   private int getYear(Calendar calendar) {
/* 423 */     return calendar.get(1);
/*     */   }
/*     */ 
/*     */   private int getMonth(Calendar calendar) {
/* 427 */     return calendar.get(2);
/*     */   }
/*     */ 
/*     */   private int getDay(Calendar calendar) {
/* 431 */     return calendar.get(5);
/*     */   }
/*     */ 
/*     */   private Calendar clearTime(Calendar calendar) {
/* 435 */     return new GregorianCalendar(getYear(calendar), getMonth(calendar), 
/* 436 */       getDay(calendar));
/*     */   }
/*     */ 
/*     */   private void flush(Calendar calendar) {
/* 440 */     calendar.getTime();
/*     */   }
/*     */ 
/*     */   private int avg(List<Object[]> list) {
/* 444 */     int count = 0;
/* 445 */     for (Object[] obj : list) {
/* 446 */       count = (int)(count + ((Long)obj[0]).longValue());
/*     */     }
/* 448 */     return count / ArithmeticUtils.dividend(list.size());
/*     */   }
/*     */ 
/*     */   private int avg(List<Object[]> pvList, List<Object[]> visitorsList) {
/* 452 */     int count = 0;
/* 453 */     if (pvList.size() != visitorsList.size()) {
/* 454 */       return count;
/*     */     }
/* 456 */     for (int i = 0; i < pvList.size(); i++) {
/* 457 */       long pvCount = ((Long)((Object[])pvList.get(i))[0]).longValue();
/* 458 */       long visitorCount = ((Long)((Object[])visitorsList.get(i))[0]).longValue();
/* 459 */       count = (int)(count + pvCount / visitorCount);
/*     */     }
/* 461 */     return count / ArithmeticUtils.dividend(pvList.size());
/*     */   }
/*     */ 
/*     */   private Object[] max(List<Object[]> list) {
/* 465 */     int max = 0;
/* 466 */     String date = null;
/* 467 */     for (Object[] objs : list) {
/* 468 */       long curr = ((Long)objs[0]).longValue();
/* 469 */       if (max < curr) {
/* 470 */         max = (int)curr;
/* 471 */         date = (String)objs[1];
/*     */       }
/*     */     }
/* 474 */     return new Object[] { Integer.valueOf(max), date };
/*     */   }
/*     */ 
/*     */   private Object[] max(List<Object[]> pvList, List<Object[]> visitorsList) {
/* 478 */     int max = 0;
/* 479 */     String date = null;
/* 480 */     if (pvList.size() != visitorsList.size()) {
/* 481 */       return new Object[] { Integer.valueOf(max), date };
/*     */     }
/* 483 */     for (int i = 0; i < pvList.size(); i++) {
/* 484 */       long pvCount = ((Long)((Object[])pvList.get(i))[0]).longValue();
/* 485 */       long visitorCount = ((Long)((Object[])visitorsList.get(i))[0]).longValue();
/* 486 */       long curr = pvCount / visitorCount;
/* 487 */       if (max < curr) {
/* 488 */         max = (int)curr;
/* 489 */         date = (String)((Object[])pvList.get(i))[1];
/*     */       }
/*     */     }
/* 492 */     return new Object[] { Integer.valueOf(max), date };
/*     */   }
/*     */ 
/*     */   private CmsStatistic.TimeRange getTimeRange(int type) {
/* 496 */     return getTimeRange(type, new GregorianCalendar());
/*     */   }
/*     */ 
/*     */   private CmsStatistic.TimeRange getTimeRange(int type, Calendar calendar)
/*     */   {
/* 501 */     calendar = clearTime(calendar);
/*     */ 	  Date begin = null;
			  Date end = null;
/* 503 */     switch (type) {
/*     */     case 21:
/* 505 */       begin = calendar.getTime();
/* 506 */       calendar.add(5, 1);
/* 507 */       end = calendar.getTime();
/* 508 */       return CmsStatistic.TimeRange.getInstance(begin, end);
/*     */     case 22:
/* 511 */       calendar.add(5, -1);
/* 512 */       begin = calendar.getTime();
/* 513 */       calendar.add(5, 1);
/* 514 */       end = calendar.getTime();
/* 515 */       return CmsStatistic.TimeRange.getInstance(begin, end);
/*     */     case 23:
/* 518 */       flush(calendar);
/* 519 */       calendar.set(7, 1);
/* 520 */       begin = calendar.getTime();
/* 521 */       calendar.add(7, 7);
/* 522 */       end = calendar.getTime();
/* 523 */       return CmsStatistic.TimeRange.getInstance(begin, end);
/*     */     case 24:
/* 526 */       int month = calendar.get(2);
/* 527 */       calendar.set(5, 1);
/* 528 */       begin = calendar.getTime();
/* 529 */       calendar.set(2, month + 1);
/* 530 */       end = calendar.getTime();
/* 531 */       return CmsStatistic.TimeRange.getInstance(begin, end);
/*     */     case 25:
/* 534 */       int year = calendar.get(1);
/* 535 */       calendar.set(2, 0);
/* 536 */       calendar.set(5, 1);
/* 537 */       begin = calendar.getTime();
/* 538 */       calendar.set(1, year + 1);
/* 539 */       end = calendar.getTime();
/* 540 */       return CmsStatistic.TimeRange.getInstance(begin, end);
/*     */     }
/*     */ 
/* 543 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatisticSvcImpl
 * JD-Core Version:    0.6.0
 */