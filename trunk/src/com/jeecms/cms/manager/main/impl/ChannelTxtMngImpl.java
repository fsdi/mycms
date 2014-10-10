/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ChannelTxtDao;
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.ChannelTxt;
/*    */ import com.jeecms.cms.manager.main.ChannelTxtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ChannelTxtMngImpl
/*    */   implements ChannelTxtMng
/*    */ {
/*    */   private ChannelTxtDao dao;
/*    */ 
/*    */   public ChannelTxt save(ChannelTxt txt, Channel channel)
/*    */   {
/* 23 */     if (txt.isAllBlank()) {
/* 24 */       return null;
/*    */     }
/* 26 */     txt.setChannel(channel);
/* 27 */     txt.init();
/* 28 */     this.dao.save(txt);
/* 29 */     return txt;
/*    */   }
/*    */ 
/*    */   public ChannelTxt update(ChannelTxt txt, Channel channel)
/*    */   {
/* 37 */     ChannelTxt entity = this.dao.findById(channel.getId());
/* 38 */     if (entity == null) {
/* 39 */       entity = save(txt, channel);
/* 40 */       channel.getChannelTxtSet().add(entity);
/* 41 */       return entity;
/*    */     }
/* 43 */     if (txt.isAllBlank()) {
/* 44 */       channel.getChannelTxtSet().clear();
/* 45 */       return null;
/*    */     }
/* 47 */     Updater updater = new Updater(txt);
/* 48 */     entity = this.dao.updateByUpdater(updater);
/* 49 */     entity.blankToNull();
/* 50 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ChannelTxtDao dao)
/*    */   {
/* 59 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ChannelTxtMngImpl
 * JD-Core Version:    0.6.0
 */