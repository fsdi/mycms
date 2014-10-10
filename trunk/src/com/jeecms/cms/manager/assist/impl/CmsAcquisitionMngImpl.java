/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsAcquisitionDao;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition.AcquisitionResultType;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import com.jeecms.cms.entity.main.ContentTxt;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*     */ import com.jeecms.cms.service.ChannelDeleteChecker;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsAcquisitionMngImpl
/*     */   implements CmsAcquisitionMng, ChannelDeleteChecker
/*     */ {
/*     */   private ChannelMng channelMng;
/*     */   private ContentMng contentMng;
/*     */   private ContentTypeMng contentTypeMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private CmsAcquisitionDao dao;
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelMng modelMng;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsAcquisition> getList(Integer siteId)
/*     */   {
/*  34 */     return this.dao.getList(siteId);
/*     */   }
/*     */   @Transactional
/*     */   public CmsAcquisition findById(Integer id) {
/*  39 */     CmsAcquisition entity = this.dao.findById(id);
/*  40 */     return entity;
/*     */   }
/*     */ 
/*     */   public void stop(Integer id) {
/*  44 */     CmsAcquisition acqu = findById(id);
/*  45 */     if (acqu == null) {
/*  46 */       return;
/*     */     }
/*  48 */     if (acqu.getStatus().intValue() == 1) {
/*  49 */       acqu.setStatus(Integer.valueOf(0));
/*  50 */     } else if (acqu.getStatus().intValue() == 2) {
/*  51 */       acqu.setCurrNum(Integer.valueOf(0));
/*  52 */       acqu.setCurrItem(Integer.valueOf(0));
/*  53 */       acqu.setTotalItem(Integer.valueOf(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pause(Integer id) {
/*  58 */     CmsAcquisition acqu = findById(id);
/*  59 */     if (acqu == null) {
/*  60 */       return;
/*     */     }
/*  62 */     if (acqu.getStatus().intValue() == 1)
/*  63 */       acqu.setStatus(Integer.valueOf(2));
/*     */   }
/*     */ 
/*     */   public CmsAcquisition start(Integer id)
/*     */   {
/*  68 */     CmsAcquisition acqu = findById(id);
/*  69 */     if (acqu == null) {
/*  70 */       return acqu;
/*     */     }
/*  72 */     acqu.setStatus(Integer.valueOf(1));
/*  73 */     acqu.setStartTime(new Date());
/*  74 */     acqu.setEndTime(null);
/*  75 */     if (acqu.getCurrNum().intValue() <= 0) {
/*  76 */       acqu.setCurrNum(Integer.valueOf(1));
/*     */     }
/*  78 */     if (acqu.getCurrItem().intValue() <= 0) {
/*  79 */       acqu.setCurrItem(Integer.valueOf(1));
/*     */     }
/*  81 */     acqu.setTotalItem(Integer.valueOf(0));
/*  82 */     return acqu;
/*     */   }
/*     */ 
/*     */   public void end(Integer id) {
/*  86 */     CmsAcquisition acqu = findById(id);
/*  87 */     if (acqu == null) {
/*  88 */       return;
/*     */     }
/*  90 */     acqu.setStatus(Integer.valueOf(0));
/*  91 */     acqu.setEndTime(new Date());
/*  92 */     acqu.setCurrNum(Integer.valueOf(0));
/*  93 */     acqu.setCurrItem(Integer.valueOf(0));
/*  94 */     acqu.setTotalItem(Integer.valueOf(0));
/*  95 */     acqu.setTotalItem(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   public boolean isNeedBreak(Integer id, int currNum, int currItem, int totalItem)
/*     */   {
/* 100 */     CmsAcquisition acqu = findById(id);
/* 101 */     if (acqu == null)
/* 102 */       return true;
/* 103 */     if (acqu.isPuase()) {
/* 104 */       acqu.setCurrNum(Integer.valueOf(currNum));
/* 105 */       acqu.setCurrItem(Integer.valueOf(currItem));
/* 106 */       acqu.setTotalItem(Integer.valueOf(totalItem));
/* 107 */       acqu.setEndTime(new Date());
/* 108 */       return true;
/* 109 */     }if (acqu.isStop()) {
/* 110 */       acqu.setCurrNum(Integer.valueOf(0));
/* 111 */       acqu.setCurrItem(Integer.valueOf(0));
/* 112 */       acqu.setTotalItem(Integer.valueOf(0));
/* 113 */       acqu.setEndTime(new Date());
/* 114 */       return true;
/*     */     }
/* 116 */     acqu.setCurrNum(Integer.valueOf(currNum));
/* 117 */     acqu.setCurrItem(Integer.valueOf(currItem));
/* 118 */     acqu.setTotalItem(Integer.valueOf(totalItem));
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition save(CmsAcquisition bean, Integer channelId, Integer typeId, Integer userId, Integer siteId)
/*     */   {
/* 125 */     bean.setChannel(this.channelMng.findById(channelId));
/* 126 */     bean.setType(this.contentTypeMng.findById(typeId));
/* 127 */     bean.setUser(this.cmsUserMng.findById(userId));
/* 128 */     bean.setSite(this.cmsSiteMng.findById(siteId));
/* 129 */     bean.init();
/* 130 */     this.dao.save(bean);
/* 131 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition update(CmsAcquisition bean, Integer channelId, Integer typeId)
/*     */   {
/* 136 */     Updater updater = new Updater(bean);
/* 137 */     bean = this.dao.updateByUpdater(updater);
/* 138 */     bean.setChannel(this.channelMng.findById(channelId));
/* 139 */     bean.setType(this.contentTypeMng.findById(typeId));
/* 140 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition deleteById(Integer id) {
/* 144 */     CmsAcquisition bean = this.dao.deleteById(id);
/* 145 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition[] deleteByIds(Integer[] ids) {
/* 149 */     CmsAcquisition[] beans = new CmsAcquisition[ids.length];
/* 150 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 151 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 153 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content saveContent(String title, String txt, String origin, String author, String description, Date releaseDate, Integer acquId, CmsAcquisition.AcquisitionResultType resultType, CmsAcquisitionTemp temp, CmsAcquisitionHistory history)
/*     */   {
/* 160 */     CmsAcquisition acqu = findById(acquId);
/* 161 */     Content c = new Content();
/* 162 */     c.setSite(acqu.getSite());
/* 163 */     c.setModel(this.modelMng.getDefModel());
/* 164 */     ContentExt cext = new ContentExt();
/* 165 */     ContentTxt ctxt = new ContentTxt();
/* 166 */     cext.setAuthor(author);
/* 167 */     cext.setOrigin(origin);
/* 168 */     cext.setReleaseDate(releaseDate);
/* 169 */     cext.setTitle(title);
/* 170 */     cext.setDescription(description);
/* 171 */     ctxt.setTxt(txt);
/* 172 */     Content content = this.contentMng.save(c, cext, ctxt, null, null, null, 
/* 173 */       null, null, null, null, null, null, acqu.getChannel().getId(), 
/* 174 */       acqu.getType().getId(), Boolean.valueOf(false), acqu.getUser(), false);
/* 175 */     history.setTitle(title);
/* 176 */     history.setContent(content);
/* 177 */     history.setDescription(resultType.name());
/* 178 */     temp.setTitle(title);
/* 179 */     temp.setDescription(resultType.name());
/* 180 */     return content;
/*     */   }
/*     */ 
/*     */   public String checkForChannelDelete(Integer channelId) {
/* 184 */     if (this.dao.countByChannelId(channelId) > 0) {
/* 185 */       return "cmsAcquisition.error.cannotDeleteChannel";
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition getStarted(Integer siteId)
/*     */   {
/* 192 */     return this.dao.getStarted(siteId);
/*     */   }
/*     */ 
/*     */   public Integer hasStarted(Integer siteId) {
/* 196 */     return Integer.valueOf(getStarted(siteId) == null ? 0 : getMaxQueue(siteId).intValue() + 1);
/*     */   }
/*     */ 
/*     */   public Integer getMaxQueue(Integer siteId) {
/* 200 */     return this.dao.getMaxQueue(siteId);
/*     */   }
/*     */ 
/*     */   public void addToQueue(Integer[] ids, Integer queueNum) {
/* 204 */     for (Integer id : ids) {
/* 205 */       CmsAcquisition acqu = findById(id);
/* 206 */       if ((acqu.getStatus().intValue() == 1) || (acqu.getQueue().intValue() > 0))
/*     */         continue;
/*     */       Integer tmp55_54 = queueNum; queueNum = Integer.valueOf(tmp55_54.intValue() + 1); acqu.setQueue(tmp55_54);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cancel(Integer siteId, Integer id) {
/* 214 */     CmsAcquisition acqu = findById(id);
/* 215 */     Integer queue = acqu.getQueue();
/* 216 */     for (CmsAcquisition c : getLargerQueues(siteId, queue)) {
/* 217 */       c.setQueue(Integer.valueOf(c.getQueue().intValue() - 1));
/*     */     }
/* 219 */     acqu.setQueue(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   public List<CmsAcquisition> getLargerQueues(Integer siteId, Integer queueNum) {
/* 223 */     return this.dao.getLargerQueues(siteId, queueNum);
/*     */   }
/*     */ 
/*     */   public CmsAcquisition popAcquFromQueue(Integer siteId) {
/* 227 */     CmsAcquisition acquisition = this.dao.popAcquFromQueue(siteId);
/* 228 */     if (acquisition != null) {
/* 229 */       Integer id = acquisition.getId();
/* 230 */       cancel(siteId, id);
/*     */     }
/* 232 */     return acquisition;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelMng(ChannelMng channelMng)
/*     */   {
/* 246 */     this.channelMng = channelMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentMng(ContentMng contentMng) {
/* 251 */     this.contentMng = contentMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentTypeMng(ContentTypeMng contentTypeMng) {
/* 256 */     this.contentTypeMng = contentTypeMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 261 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 266 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsAcquisitionDao dao) {
/* 271 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsAcquisitionMngImpl
 * JD-Core Version:    0.6.0
 */