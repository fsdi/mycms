/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsFriendlink;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CmsFriendlink extends BaseCmsFriendlink
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 11 */     if (getPriority() == null) {
/* 12 */       setPriority(Integer.valueOf(10));
/*    */     }
/* 14 */     if (getViews() == null) {
/* 15 */       setViews(Integer.valueOf(0));
/*    */     }
/* 17 */     if (getEnabled() == null) {
/* 18 */       setEnabled(Boolean.valueOf(true));
/*    */     }
/* 20 */     blankToNull();
/*    */   }
/*    */ 
/*    */   public void blankToNull() {
/* 24 */     if (StringUtils.isBlank(getLogo()))
/* 25 */       setLogo(null);
/*    */   }
/*    */ 
/*    */   public CmsFriendlink()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsFriendlink(Integer id)
/*    */   {
/* 38 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsFriendlink(Integer id, CmsFriendlinkCtg category, CmsSite site, String name, String domain, Integer views, Integer priority, Boolean enabled)
/*    */   {
/* 50 */     super(id, category, site, name, domain, views, priority, enabled);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsFriendlink
 * JD-Core Version:    0.6.0
 */