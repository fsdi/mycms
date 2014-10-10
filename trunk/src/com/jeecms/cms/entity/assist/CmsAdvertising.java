/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsAdvertising;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsAdvertising extends BaseCmsAdvertising
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public int getPercent()
/*    */   {
/* 11 */     if (getDisplayCount().longValue() <= 0L) {
/* 12 */       return 0;
/*    */     }
/* 14 */     return (int)(getClickCount().longValue() * 100L / getDisplayCount().longValue());
/*    */   }
/*    */ 
/*    */   public Long getStartTimeMillis()
/*    */   {
/* 19 */     if (getStartTime() != null) {
/* 20 */       return Long.valueOf(getStartTime().getTime());
/*    */     }
/* 22 */     return null;
/*    */   }
/*    */ 
/*    */   public Long getEndTimeMillis()
/*    */   {
/* 27 */     if (getEndTime() != null) {
/* 28 */       return Long.valueOf(getEndTime().getTime());
/*    */     }
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   public void init()
/*    */   {
/* 35 */     if (getClickCount() == null) {
/* 36 */       setClickCount(Long.valueOf(0L));
/*    */     }
/* 38 */     if (getDisplayCount() == null) {
/* 39 */       setDisplayCount(Long.valueOf(0L));
/*    */     }
/* 41 */     if (getEnabled() == null) {
/* 42 */       setEnabled(Boolean.valueOf(true));
/*    */     }
/* 44 */     if (getWeight() == null) {
/* 45 */       setWeight(Integer.valueOf(1));
/*    */     }
/* 47 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 51 */     if (StringUtils.isBlank(getCode()))
/* 52 */       setCode(null);
/*    */   }
/*    */ 
/*    */   public CmsAdvertising()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsAdvertising(Integer id)
/*    */   {
/* 65 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsAdvertising(Integer id, CmsAdvertisingSpace adspace, CmsSite site, String name, String category, Integer weight, Long displayCount, Long clickCount, Boolean enabled)
/*    */   {
/* 79 */     super(id, adspace, site, name, category, weight, displayCount, 
/* 79 */       clickCount, enabled);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsAdvertising
 * JD-Core Version:    0.6.0
 */