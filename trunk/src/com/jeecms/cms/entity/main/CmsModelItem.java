/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsModelItem;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsModelItem extends BaseCmsModelItem
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 11 */     if (getPriority() == null) {
/* 12 */       setPriority(Integer.valueOf(10));
/*    */     }
/* 14 */     if (getSingle() == null) {
/* 15 */       setSingle(Boolean.valueOf(true));
/*    */     }
/* 17 */     if (getCustom() == null) {
/* 18 */       setCustom(Boolean.valueOf(true));
/*    */     }
/* 20 */     if (getDisplay() == null)
/* 21 */       setDisplay(Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */   public void emptyToNull()
/*    */   {
/* 27 */     if (StringUtils.isBlank(getDefValue())) {
/* 28 */       setDefValue(null);
/*    */     }
/* 30 */     if (StringUtils.isBlank(getOptValue())) {
/* 31 */       setOptValue(null);
/*    */     }
/* 33 */     if (StringUtils.isBlank(getSize())) {
/* 34 */       setSize(null);
/*    */     }
/* 36 */     if (StringUtils.isBlank(getRows())) {
/* 37 */       setRows(null);
/*    */     }
/* 39 */     if (StringUtils.isBlank(getCols())) {
/* 40 */       setCols(null);
/*    */     }
/* 42 */     if (StringUtils.isBlank(getHelp())) {
/* 43 */       setHelp(null);
/*    */     }
/* 45 */     if (StringUtils.isBlank(getHelpPosition()))
/* 46 */       setHelpPosition(null);
/*    */   }
/*    */ 
/*    */   public CmsModelItem()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsModelItem(Integer id)
/*    */   {
/* 59 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsModelItem(Integer id, CmsModel model, String field, String label, Integer dataType, Boolean single, Boolean channel, Boolean custom, Boolean display)
/*    */   {
/* 85 */     super(id, 
/* 78 */       model, 
/* 79 */       field, 
/* 80 */       label, 
/* 81 */       dataType, 
/* 82 */       single, 
/* 83 */       channel, 
/* 84 */       custom, 
/* 85 */       display);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsModelItem
 * JD-Core Version:    0.6.0
 */