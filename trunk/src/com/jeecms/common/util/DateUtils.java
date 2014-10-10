/*     */ package com.jeecms.common.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DateUtils
/*     */ {
/*  12 */   private StringBuffer buffer = new StringBuffer();
/*  13 */   private static String ZERO = "0";
/*     */   private static DateUtils date;
/*  15 */   public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/*  16 */   public static SimpleDateFormat format1 = new SimpleDateFormat(
/*  17 */     "yyyyMMdd HH:mm:ss");
/*     */ 
/*     */   public String getNowString()
/*     */   {
/*  20 */     Calendar calendar = getCalendar();
/*  21 */     this.buffer.delete(0, this.buffer.capacity());
/*  22 */     this.buffer.append(getYear(calendar));
/*     */ 
/*  24 */     if (getMonth(calendar) < 10) {
/*  25 */       this.buffer.append(ZERO);
/*     */     }
/*  27 */     this.buffer.append(getMonth(calendar));
/*     */ 
/*  29 */     if (getDate(calendar) < 10) {
/*  30 */       this.buffer.append(ZERO);
/*     */     }
/*  32 */     this.buffer.append(getDate(calendar));
/*  33 */     if (getHour(calendar) < 10) {
/*  34 */       this.buffer.append(ZERO);
/*     */     }
/*  36 */     this.buffer.append(getHour(calendar));
/*  37 */     if (getMinute(calendar) < 10) {
/*  38 */       this.buffer.append(ZERO);
/*     */     }
/*  40 */     this.buffer.append(getMinute(calendar));
/*  41 */     if (getSecond(calendar) < 10) {
/*  42 */       this.buffer.append(ZERO);
/*     */     }
/*  44 */     this.buffer.append(getSecond(calendar));
/*  45 */     return this.buffer.toString();
/*     */   }
/*     */ 
/*     */   private static int getDateField(Date date, int field) {
/*  49 */     Calendar c = getCalendar();
/*  50 */     c.setTime(date);
/*  51 */     return c.get(field);
/*     */   }
/*     */   public static int getYearsBetweenDate(Date begin, Date end) {
/*  54 */     int bYear = getDateField(begin, 1);
/*  55 */     int eYear = getDateField(end, 1);
/*  56 */     return eYear - bYear;
/*     */   }
/*     */ 
/*     */   public static int getMonthsBetweenDate(Date begin, Date end) {
/*  60 */     int bMonth = getDateField(begin, 2);
/*  61 */     int eMonth = getDateField(end, 2);
/*  62 */     return eMonth - bMonth;
/*     */   }
/*     */   public static int getWeeksBetweenDate(Date begin, Date end) {
/*  65 */     int bWeek = getDateField(begin, 3);
/*  66 */     int eWeek = getDateField(end, 3);
/*  67 */     return eWeek - bWeek;
/*     */   }
/*     */   public static int getDaysBetweenDate(Date begin, Date end) {
/*  70 */     int bDay = getDateField(begin, 6);
/*  71 */     int eDay = getDateField(end, 6);
/*  72 */     return eDay - bDay;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  77 */     System.out.println(getSpecficDateStart(new Date(), 288));
/*     */   }
/*     */ 
/*     */   public static Date getSpecficYearStart(Date date, int amount)
/*     */   {
/*  88 */     Calendar cal = Calendar.getInstance();
/*  89 */     cal.setTime(date);
/*  90 */     cal.add(1, amount);
/*  91 */     cal.set(6, 1);
/*  92 */     return getStartDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficYearEnd(Date date, int amount)
/*     */   {
/* 103 */     Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
/* 104 */     Calendar cal = Calendar.getInstance();
/* 105 */     cal.setTime(temp);
/* 106 */     cal.add(6, -1);
/* 107 */     return getFinallyDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficMonthStart(Date date, int amount)
/*     */   {
/* 118 */     Calendar cal = Calendar.getInstance();
/* 119 */     cal.setTime(date);
/* 120 */     cal.add(2, amount);
/* 121 */     cal.set(5, 1);
/* 122 */     return getStartDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficMonthEnd(Date date, int amount)
/*     */   {
/* 133 */     Calendar cal = Calendar.getInstance();
/* 134 */     cal.setTime(getSpecficMonthStart(date, amount + 1));
/* 135 */     cal.add(6, -1);
/* 136 */     return getFinallyDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficWeekStart(Date date, int amount)
/*     */   {
/* 147 */     Calendar cal = Calendar.getInstance();
/* 148 */     cal.setTime(date);
/* 149 */     cal.setFirstDayOfWeek(2);
/* 150 */     cal.add(4, amount);
/* 151 */     cal.set(7, 2);
/* 152 */     return getStartDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficWeekEnd(Date date, int amount)
/*     */   {
/* 163 */     Calendar cal = Calendar.getInstance();
/* 164 */     cal.setFirstDayOfWeek(2);
/* 165 */     cal.add(4, amount);
/* 166 */     cal.set(7, 1);
/* 167 */     return getFinallyDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getSpecficDateStart(Date date, int amount) {
/* 171 */     Calendar cal = Calendar.getInstance();
/* 172 */     cal.setTime(date);
/* 173 */     cal.add(6, amount);
/* 174 */     return getStartDate(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getFinallyDate(Date date)
/*     */   {
/* 184 */     String temp = format.format(date);
/* 185 */     temp = temp + " 23:59:59";
/*     */     try
/*     */     {
/* 188 */       return format1.parse(temp); } catch (ParseException e) {
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date getStartDate(Date date)
/*     */   {
/* 201 */     String temp = format.format(date);
/* 202 */     temp = temp + " 00:00:00";
/*     */     try
/*     */     {
/* 205 */       return format1.parse(temp); } catch (Exception e) {
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean isInDate(Date date, Date compareDate)
/*     */   {
/* 219 */     return (compareDate.after(getStartDate(date))) && (compareDate.before(getFinallyDate(date)));
/*     */   }
/*     */ 
/*     */   private int getYear(Calendar calendar)
/*     */   {
/* 226 */     return calendar.get(1);
/*     */   }
/*     */ 
/*     */   private int getMonth(Calendar calendar) {
/* 230 */     return calendar.get(2) + 1;
/*     */   }
/*     */ 
/*     */   private int getDate(Calendar calendar) {
/* 234 */     return calendar.get(5);
/*     */   }
/*     */ 
/*     */   private int getHour(Calendar calendar) {
/* 238 */     return calendar.get(11);
/*     */   }
/*     */ 
/*     */   private int getMinute(Calendar calendar) {
/* 242 */     return calendar.get(12);
/*     */   }
/*     */ 
/*     */   private int getSecond(Calendar calendar) {
/* 246 */     return calendar.get(13);
/*     */   }
/*     */ 
/*     */   private static Calendar getCalendar() {
/* 250 */     return Calendar.getInstance();
/*     */   }
/*     */ 
/*     */   public static DateUtils getDateInstance() {
/* 254 */     if (date == null) {
/* 255 */       date = new DateUtils();
/*     */     }
/* 257 */     return date;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.DateUtils
 * JD-Core Version:    0.6.0
 */