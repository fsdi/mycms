/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsFriendlinkCtg;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ 
/*    */ public class CmsFriendlinkCtg extends BaseCmsFriendlinkCtg
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsFriendlinkCtg()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg(Integer id, CmsSite site, String name, Integer priority)
/*    */   {
/* 35 */     super(id, 
/* 33 */       site, 
/* 34 */       name, 
/* 35 */       priority);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsFriendlinkCtg
 * JD-Core Version:    0.6.0
 */