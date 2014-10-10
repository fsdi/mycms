/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsComment;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.common.util.StrUtils;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CmsComment extends BaseCmsComment
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public String getText()
/*    */   {
/* 12 */     return getCommentExt().getText();
/*    */   }
/*    */ 
/*    */   public String getTextHtml() {
/* 16 */     return StrUtils.txt2htm(getText());
/*    */   }
/*    */ 
/*    */   public String getReply() {
/* 20 */     return getCommentExt().getReply();
/*    */   }
/*    */ 
/*    */   public String getReplayHtml() {
/* 24 */     return StrUtils.txt2htm(getReply());
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 28 */     return getCommentExt().getIp();
/*    */   }
/*    */ 
/*    */   public void init() {
/* 32 */     short zero = 0;
/* 33 */     if (getDowns() == null) {
/* 34 */       setDowns(Short.valueOf(zero));
/*    */     }
/* 36 */     if (getUps() == null) {
/* 37 */       setUps(Short.valueOf(zero));
/*    */     }
/* 39 */     if (getChecked() == null) {
/* 40 */       setChecked(Boolean.valueOf(false));
/*    */     }
/* 42 */     if (getRecommend() == null) {
/* 43 */       setRecommend(Boolean.valueOf(false));
/*    */     }
/* 45 */     if (getCreateTime() == null)
/* 46 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*    */   }
/*    */ 
/*    */   public CmsComment()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsComment(Integer id)
/*    */   {
/* 59 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsComment(Integer id, Content content, CmsSite site, Date createTime, Short ups, Short downs, Boolean recommend, Boolean checked)
/*    */   {
/* 83 */     super(id, 
/* 77 */       content, 
/* 78 */       site, 
/* 79 */       createTime, 
/* 80 */       ups, 
/* 81 */       downs, 
/* 82 */       recommend, 
/* 83 */       checked);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsComment
 * JD-Core Version:    0.6.0
 */