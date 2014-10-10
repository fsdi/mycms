/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsTopic;
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsTopic extends BaseCmsTopic
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public String getSname()
/*    */   {
/* 18 */     if (!StringUtils.isBlank(getShortName())) {
/* 19 */       return getShortName();
/*    */     }
/* 21 */     return getName();
/*    */   }
/*    */ 
/*    */   public void init()
/*    */   {
/* 26 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 30 */     if (StringUtils.isBlank(getTitleImg())) {
/* 31 */       setTitleImg(null);
/*    */     }
/* 33 */     if (StringUtils.isBlank(getContentImg())) {
/* 34 */       setContentImg(null);
/*    */     }
/* 36 */     if (StringUtils.isBlank(getShortName()))
/* 37 */       setShortName(null);
/*    */   }
/*    */ 
/*    */   public static Integer[] fetchIds(Collection<CmsTopic> topics)
/*    */   {
/* 48 */     Integer[] ids = new Integer[topics.size()];
/* 49 */     int i = 0;
/* 50 */     for (CmsTopic g : topics) {
/* 51 */       ids[(i++)] = g.getId();
/*    */     }
/* 53 */     return ids;
/*    */   }
/*    */ 
/*    */   public CmsTopic()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsTopic(Integer id)
/*    */   {
/* 65 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsTopic(Integer id, String name, Integer priority, Boolean recommend)
/*    */   {
/* 81 */     super(id, 
/* 79 */       name, 
/* 80 */       priority, 
/* 81 */       recommend);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsTopic
 * JD-Core Version:    0.6.0
 */