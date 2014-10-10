/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsRole;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class CmsRole extends BaseCmsRole
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public static Integer[] fetchIds(Collection<CmsRole> roles)
/*    */   {
/* 11 */     if (roles == null) {
/* 12 */       return null;
/*    */     }
/* 14 */     Integer[] ids = new Integer[roles.size()];
/* 15 */     int i = 0;
/* 16 */     for (CmsRole r : roles) {
/* 17 */       ids[(i++)] = r.getId();
/*    */     }
/* 19 */     return ids;
/*    */   }
/*    */ 
/*    */   public CmsRole()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsRole(Integer id)
/*    */   {
/* 31 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsRole(Integer id, String name, Integer priority, Boolean m_super)
/*    */   {
/* 47 */     super(id, 
/* 45 */       name, 
/* 46 */       priority, 
/* 47 */       m_super);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsRole
 * JD-Core Version:    0.6.0
 */