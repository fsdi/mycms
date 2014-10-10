/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ContentTagDao;
/*     */ import com.jeecms.cms.entity.main.ContentTag;
/*     */ import com.jeecms.cms.manager.main.ContentTagMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ContentTagMngImpl
/*     */   implements ContentTagMng
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(ContentTagMngImpl.class);
/*     */   private ContentTagDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<ContentTag> getListForTag(Integer count)
/*     */   {
/*  29 */     return this.dao.getList(count, true);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageForTag(int pageNo, int pageSize) {
/*  34 */     Pagination page = this.dao.getPage(null, pageNo, pageSize, true);
/*  35 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(String name, int pageNo, int pageSize) {
/*  40 */     Pagination page = this.dao.getPage(name, pageNo, pageSize, false);
/*  41 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public ContentTag findById(Integer id) {
/*  46 */     ContentTag entity = this.dao.findById(id);
/*  47 */     return entity;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public ContentTag findByName(String name) {
/*  52 */     return this.dao.findByName(name, false);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public ContentTag findByNameForTag(String name) {
/*  57 */     return this.dao.findByName(name, true);
/*     */   }
/*     */ 
/*     */   public List<ContentTag> saveTags(String[] tagArr)
/*     */   {
/*  64 */     if ((tagArr == null) || (tagArr.length <= 0)) {
/*  65 */       return null;
/*     */     }
/*  67 */     List list = new ArrayList();
/*     */ 
/*  69 */     Set<String> tagSet = new HashSet();
/*     */ 
/*  71 */     for (String name : tagArr)
/*     */     {
/*  73 */       for (String t : tagSet) {
/*  74 */         if (!t.equalsIgnoreCase(name)) {
/*     */           continue;
/*     */         }
/*     */       }
/*  78 */       tagSet.add(name);
/*  79 */       ContentTag tag = saveTag(name);
/*  80 */       list.add(tag);
/*     */     }
/*  82 */     return list;
/*     */   }
/*     */ 
/*     */   public ContentTag saveTag(String name)
/*     */   {
/*  89 */     ContentTag tag = findByName(name);
/*  90 */     if (tag != null) {
/*  91 */       tag.setCount(Integer.valueOf(tag.getCount().intValue() + 1));
/*     */     } else {
/*  93 */       tag = new ContentTag();
/*  94 */       tag.setName(name);
/*  95 */       tag = save(tag);
/*     */     }
/*  97 */     return tag;
/*     */   }
/*     */ 
/*     */   public List<ContentTag> updateTags(List<ContentTag> tags, String[] tagArr) {
/* 101 */     if (tagArr == null) {
/* 102 */       tagArr = new String[0];
/*     */     }
/* 104 */     List list = new ArrayList();
/*     */ 
/* 106 */     for (String t : tagArr) {
/* 107 */       ContentTag bean = null;
/* 108 */       for (ContentTag tag : tags) {
/* 109 */         if (t.equalsIgnoreCase(tag.getName())) {
/* 110 */           bean = tag;
/* 111 */           break;
/*     */         }
/*     */       }
/* 114 */       if (bean == null) {
/* 115 */         bean = saveTag(t);
/*     */       }
/* 117 */       list.add(bean);
/*     */     }
/* 119 */     Set toBeRemove = new HashSet();
/*     */ 
/* 121 */     for (Iterator it = tags.iterator(); it.hasNext(); ) { 
			    ContentTag tag = (ContentTag)it.next();
/* 122 */       boolean contains = false;
/* 123 */       for (String t : tagArr) {
/* 124 */         if (t.equalsIgnoreCase(tag.getName())) {
/* 125 */           contains = true;
/* 126 */           break;
/*     */         }
/*     */       }
/* 129 */       if (!contains) {
/* 130 */         toBeRemove.add(tag);
/*     */       }
/*     */     }
/* 133 */     tags.clear();
/* 134 */     tags.addAll(list);
/* 135 */     removeTags(toBeRemove);
/* 136 */     return (List<ContentTag>)tags;
/*     */   }
/*     */ 
/*     */   public void removeTags(Collection<ContentTag> tags)
/*     */   {
/* 158 */     Set<ContentTag> toRemove = new HashSet();
/* 159 */     for (ContentTag tag : tags) {
/* 160 */       tag.setCount(Integer.valueOf(tag.getCount().intValue() - 1));
/* 161 */       if (tag.getCount().intValue() <= 0) {
/* 162 */         toRemove.add(tag);
/*     */       }
/*     */     }
/* 165 */     for (ContentTag tag : toRemove)
/*     */     {
/* 167 */       if (this.dao.countContentRef(tag.getId()) <= 1) {
/* 168 */         this.dao.deleteById(tag.getId());
/*     */       }
/*     */       else
/* 171 */         log.warn("ContentTag ref to Content > 1, while ContentTag.ref_counter <= 0");
/*     */     }
/*     */   }
/*     */ 
/*     */   public ContentTag save(ContentTag bean)
/*     */   {
/* 178 */     bean.init();
/* 179 */     this.dao.save(bean);
/* 180 */     return bean;
/*     */   }
/*     */ 
/*     */   public ContentTag update(ContentTag bean) {
/* 184 */     Updater updater = new Updater(bean);
/* 185 */     ContentTag entity = this.dao.updateByUpdater(updater);
/* 186 */     return entity;
/*     */   }
/*     */ 
/*     */   public ContentTag deleteById(Integer id) {
/* 190 */     this.dao.deleteContentRef(id);
/* 191 */     ContentTag bean = this.dao.deleteById(id);
/* 192 */     return bean;
/*     */   }
/*     */ 
/*     */   public ContentTag[] deleteByIds(Integer[] ids) {
/* 196 */     ContentTag[] beans = new ContentTag[ids.length];
/* 197 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 198 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 200 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ContentTagDao dao)
/*     */   {
/* 207 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentTagMngImpl
 * JD-Core Version:    0.6.0
 */