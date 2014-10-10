/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ChannelDao;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.ChannelExt;
/*     */ import com.jeecms.cms.entity.main.ChannelTxt;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import com.jeecms.cms.manager.main.ChannelExtMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.ChannelTxtMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.service.ChannelDeleteChecker;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ChannelMngImpl
/*     */   implements ChannelMng
/*     */ {
/*     */   private List<ChannelDeleteChecker> deleteCheckerList;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsModelMng cmsModelMng;
/*     */   private ChannelExtMng channelExtMng;
/*     */   private ChannelTxtMng channelTxtMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private CmsGroupMng cmsGroupMng;
/*     */   private ChannelDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getTopList(Integer siteId, boolean hasContentOnly)
/*     */   {
/*  34 */     return this.dao.getTopList(siteId, hasContentOnly, false, false);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getTopListByRigth(Integer userId, Integer siteId, boolean hasContentOnly) {
/*  40 */     CmsUser user = this.cmsUserMng.findById(userId);
/*  41 */     CmsUserSite us = user.getUserSite(siteId);
/*  42 */     if (us.getAllChannel().booleanValue()) {
/*  43 */       return getTopList(siteId, hasContentOnly);
/*     */     }
/*  45 */     return this.dao.getTopListByRigth(userId, siteId, hasContentOnly);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getTopListForTag(Integer siteId, boolean hasContentOnly) {
/*  51 */     return this.dao.getTopList(siteId, hasContentOnly, true, true);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getTopPageForTag(Integer siteId, boolean hasContentOnly, int pageNo, int pageSize) {
/*  57 */     return this.dao.getTopPage(siteId, hasContentOnly, false, false, pageNo, 
/*  58 */       pageSize);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChildList(Integer parentId, boolean hasContentOnly) {
/*  63 */     return this.dao.getChildList(parentId, hasContentOnly, false, false);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChildListByRight(Integer userId, Integer siteId, Integer parentId, boolean hasContentOnly) {
/*  69 */     CmsUser user = this.cmsUserMng.findById(userId);
/*  70 */     CmsUserSite us = user.getUserSite(siteId);
/*  71 */     if (us.getAllChannel().booleanValue()) {
/*  72 */       return getChildList(parentId, hasContentOnly);
/*     */     }
/*  74 */     return this.dao.getChildListByRight(userId, parentId, hasContentOnly);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChildListForTag(Integer parentId, boolean hasContentOnly)
/*     */   {
/*  81 */     return this.dao.getChildList(parentId, hasContentOnly, true, true);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getChildPageForTag(Integer parentId, boolean hasContentOnly, int pageNo, int pageSize) {
/*  87 */     return this.dao.getChildPage(parentId, hasContentOnly, true, true, pageNo, 
/*  88 */       pageSize);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findById(Integer id) {
/*  93 */     Channel entity = this.dao.findById(id);
/*  94 */     return entity;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByPath(String path, Integer siteId) {
/*  99 */     return this.dao.findByPath(path, siteId, false);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByPathForTag(String path, Integer siteId) {
/* 104 */     return this.dao.findByPath(path, siteId, true);
/*     */   }
/*     */ 
/*     */   public Channel save(Channel bean, ChannelExt ext, ChannelTxt txt, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer[] userIds, Integer siteId, Integer parentId, Integer modelId, Integer[] modelIds, String[] tpls)
/*     */   {
/* 110 */     if (parentId != null) {
/* 111 */       bean.setParent(findById(parentId));
/*     */     }
/* 113 */     bean.setSite(this.cmsSiteMng.findById(siteId));
/* 114 */     CmsModel model = this.cmsModelMng.findById(modelId);
/* 115 */     bean.setModel(model);
/* 116 */     bean.setHasContent(model.getHasContent());
/* 117 */     bean.init();
/* 118 */     this.dao.save(bean);
/* 119 */     this.channelExtMng.save(ext, bean);
/* 120 */     this.channelTxtMng.save(txt, bean);
/*     */ 
/* 122 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0)) {
/* 123 */       for (Integer gid : viewGroupIds) {
/* 124 */         CmsGroup g = this.cmsGroupMng.findById(gid);
/* 125 */         bean.addToViewGroups(g);
/*     */       }
/*     */     }
/* 128 */     if ((contriGroupIds != null) && (contriGroupIds.length > 0)) {
/* 129 */       for (Integer gid : contriGroupIds) {
/* 130 */         CmsGroup g = this.cmsGroupMng.findById(gid);
/* 131 */         bean.addToContriGroups(g);
/*     */       }
/*     */     }
/* 134 */     if ((modelIds != null) && (modelIds.length > 0)) {
/* 135 */       for (int i = 0; i < modelIds.length; i++) {
/* 136 */         CmsModel m = this.cmsModelMng.findById(modelIds[i]);
/* 137 */         bean.addToChannelModels(m, tpls[i]);
/*     */       }
/*     */     }
/*     */ 
/* 141 */     if ((userIds != null) && (userIds.length > 0)) {
/* 142 */       for (Integer uid : userIds) {
/* 143 */         CmsUser u = this.cmsUserMng.findById(uid);
/* 144 */         bean.addToUsers(u);
/*     */       }
/*     */     }
/* 147 */     return bean;
/*     */   }
/*     */ 
/*     */   public Channel update(Channel bean, ChannelExt ext, ChannelTxt txt, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer[] userIds, Integer parentId, Map<String, String> attr, Integer[] modelIds, String[] tpls)
/*     */   {
/* 154 */     Updater updater = new Updater(bean);
/* 155 */     bean = this.dao.updateByUpdater(updater);
/*     */     Channel parent;
/* 158 */     if (parentId != null)
/* 159 */       parent = findById(parentId);
/*     */     else {
/* 161 */       parent = null;
/*     */     }
/* 163 */     bean.setParent(parent);
/*     */ 
/* 165 */     this.channelExtMng.update(ext);
/*     */ 
/* 167 */     this.channelTxtMng.update(txt, bean);
/*     */ 
/* 169 */     Map attrOrig = bean.getAttr();
/* 170 */     attrOrig.clear();
/* 171 */     attrOrig.putAll(attr);
/*     */ 
/* 173 */     for (CmsGroup g : bean.getViewGroups()) {
/* 174 */       g.getViewChannels().remove(bean);
/*     */     }
/* 176 */     bean.getViewGroups().clear();
/* 177 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0))
/*     */     {
/* 179 */       for (Integer gid : viewGroupIds) {
/* 180 */         CmsGroup g = this.cmsGroupMng.findById((Integer)gid);
/* 181 */         bean.addToViewGroups(g);
/*     */       }
/*     */     }
/*     */ 
/* 185 */     for (Object gid = bean.getContriGroups().iterator(); ((Iterator)gid).hasNext(); ) { CmsGroup g = (CmsGroup)((Iterator)gid).next();
/* 186 */       g.getContriChannels().remove(bean);
/*     */     }
/* 188 */     bean.getContriGroups().clear();
/* 189 */     if ((contriGroupIds != null) && (contriGroupIds.length > 0))
/*     */     {
/* 191 */       for (Integer gid : contriGroupIds) {
/* 192 */         CmsGroup g = this.cmsGroupMng.findById(gid);
/* 193 */         bean.addToContriGroups(g);
/*     */       }
/*     */     }
/* 196 */     bean.getChannelModels().clear();
/* 197 */     if ((modelIds != null) && (modelIds.length > 0)) {
/* 198 */       for (int i = 0; i < modelIds.length; i++) {
/* 199 */         CmsModel m = this.cmsModelMng.findById(modelIds[i]);
/* 200 */         bean.addToChannelModels((CmsModel)m, tpls[i]);
/*     */       }
/*     */     }
/*     */ 
/* 204 */     for (Object m = bean.getUsers().iterator(); ((Iterator)m).hasNext(); ) { CmsUser u = (CmsUser)((Iterator)m).next();
/* 205 */       u.getChannels().remove(bean);
/*     */     }
/* 207 */     bean.getUsers().clear();
/* 208 */     if ((userIds != null) && (userIds.length > 0))
/*     */     {
/* 210 */       for (Integer uid : userIds) {
/* 211 */         CmsUser u = this.cmsUserMng.findById(uid);
/* 212 */         bean.addToUsers(u);
/*     */       }
/*     */     }
/* 215 */     return (Channel)(Channel)bean;
/*     */   }
/*     */ 
/*     */   public Channel deleteById(Integer id) {
/* 219 */     Channel entity = this.dao.findById(id);
/* 220 */     for (CmsGroup group : entity.getViewGroups()) {
/* 221 */       group.getViewChannels().remove(entity);
/*     */     }
/* 223 */     for (CmsGroup group : entity.getContriGroups()) {
/* 224 */       group.getContriChannels().remove(entity);
/*     */     }
/* 226 */     entity = this.dao.deleteById(id);
/* 227 */     return entity;
/*     */   }
/*     */ 
/*     */   public Channel[] deleteByIds(Integer[] ids) {
/* 231 */     Channel[] beans = new Channel[ids.length];
/* 232 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 233 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 235 */     return beans;
/*     */   }
/*     */ 
/*     */   public String checkDelete(Integer id) {
/* 239 */     String msg = null;
/* 240 */     for (ChannelDeleteChecker checker : this.deleteCheckerList) {
/* 241 */       msg = checker.checkForChannelDelete(id);
/* 242 */       if (msg != null) {
/* 243 */         return msg;
/*     */       }
/*     */     }
/* 246 */     return msg;
/*     */   }
/*     */ 
/*     */   public Channel[] updatePriority(Integer[] ids, Integer[] priority) {
/* 250 */     int len = ids.length;
/* 251 */     Channel[] beans = new Channel[len];
/* 252 */     for (int i = 0; i < len; i++) {
/* 253 */       beans[i] = findById(ids[i]);
/* 254 */       beans[i].setPriority(priority[i]);
/*     */     }
/* 256 */     return beans;
/*     */   }
/*     */ 
/*     */   public void setDeleteCheckerList(List<ChannelDeleteChecker> deleteCheckerList)
/*     */   {
/* 263 */     this.deleteCheckerList = deleteCheckerList;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng)
/*     */   {
/* 276 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsModelMng(CmsModelMng cmsModelMng) {
/* 281 */     this.cmsModelMng = cmsModelMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setChannelExtMng(ChannelExtMng channelExtMng) {
/* 286 */     this.channelExtMng = channelExtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setChannelTxtMng(ChannelTxtMng channelTxtMng) {
/* 291 */     this.channelTxtMng = channelTxtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 296 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsGroupMng(CmsGroupMng cmsGroupMng) {
/* 301 */     this.cmsGroupMng = cmsGroupMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(ChannelDao dao) {
/* 306 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ChannelMngImpl
 * JD-Core Version:    0.6.0
 */