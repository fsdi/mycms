/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ChannelExtDao;
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.ChannelExt;
/*    */ import com.jeecms.cms.manager.main.ChannelExtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ChannelExtMngImpl
/*    */   implements ChannelExtMng
/*    */ {
/*    */   private ChannelExtDao dao;
/*    */ 
/*    */   public ChannelExt save(ChannelExt ext, Channel channel)
/*    */   {
/* 17 */     channel.setChannelExt(ext);
/* 18 */     ext.setChannel(channel);
/* 19 */     ext.init();
/* 20 */     this.dao.save(ext);
/* 21 */     return ext;
/*    */   }
/*    */ 
/*    */   public ChannelExt update(ChannelExt ext) {
/* 25 */     Updater updater = new Updater(ext);
/* 26 */     updater.include(ChannelExt.PROP_FINAL_STEP);
/* 27 */     updater.include(ChannelExt.PROP_AFTER_CHECK);
/* 28 */     ChannelExt entity = this.dao.updateByUpdater(updater);
/* 29 */     entity.blankToNull();
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ChannelExtDao dao)
/*    */   {
/* 37 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ChannelExtMngImpl
 * JD-Core Version:    0.6.0
 */