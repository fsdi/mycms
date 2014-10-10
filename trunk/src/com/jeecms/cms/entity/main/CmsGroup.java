/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseCmsGroup;
/*     */ import com.jeecms.common.hibernate3.PriorityComparator;
/*     */ import com.jeecms.common.hibernate3.PriorityInterface;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class CmsGroup extends BaseCmsGroup
/*     */   implements PriorityInterface
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<CmsGroup> groups)
/*     */   {
/*  26 */     Integer[] ids = new Integer[groups.size()];
/*  27 */     int i = 0;
/*  28 */     for (CmsGroup g : groups) {
/*  29 */       ids[(i++)] = g.getId();
/*     */     }
/*  31 */     return ids;
/*     */   }
/*     */ 
/*     */   public static List<CmsGroup> sortByList(Set<CmsGroup> source, List<CmsGroup> target)
/*     */   {
/*  45 */     List list = new ArrayList(source.size());
/*  46 */     for (CmsGroup g : target) {
/*  47 */       if (source.contains(g)) {
/*  48 */         list.add(g);
/*     */       }
/*     */     }
/*  51 */     return list;
/*     */   }
/*     */ 
/*     */   public boolean isAllowSuffix(String ext)
/*     */   {
/*  61 */     String suffix = getAllowSuffix();
/*  62 */     if (StringUtils.isBlank(suffix)) {
/*  63 */       return true;
/*     */     }
/*  65 */     String[] attr = StringUtils.split(suffix, ",");
/*  66 */     int i = 0; for (int len = attr.length; i < len; i++) {
/*  67 */       if (attr[i].equals(ext)) {
/*  68 */         return true;
/*     */       }
/*     */     }
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   public void init() {
/*  75 */     if (getRegDef() == null)
/*  76 */       setRegDef(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public Set<Integer> getViewChannelIds(Integer siteId) {
/*  80 */     Set<Channel> channels = getViewChannels();
/*  81 */     Set ids = new HashSet();
/*  82 */     for (Channel c : channels) {
/*  83 */       if (c.getSite().getId().equals(siteId)) {
/*  84 */         ids.add(c.getId());
/*     */       }
/*     */     }
/*  87 */     return ids;
/*     */   }
/*     */   public Set<Integer> getContriChannelIds(Integer siteId) {
/*  90 */     Set<Channel> channels = getContriChannels();
/*  91 */     Set ids = new HashSet();
/*  92 */     for (Channel c : channels) {
/*  93 */       if (c.getSite().getId().equals(siteId)) {
/*  94 */         ids.add(c.getId());
/*     */       }
/*     */     }
/*  97 */     return ids;
/*     */   }
/*     */   public void addToViewChannels(Channel channel) {
/* 100 */     Set channels = getViewChannels();
/* 101 */     if (channels == null) {
/* 102 */       channels = new TreeSet(new PriorityComparator());
/* 103 */       setViewChannels(channels);
/*     */     }
/* 105 */     channels.add(channel);
/* 106 */     channel.getViewGroups().add(this);
/*     */   }
/*     */ 
/*     */   public void addToContriChannels(Channel channel) {
/* 110 */     Set channels = getContriChannels();
/* 111 */     if (channels == null) {
/* 112 */       channels = new TreeSet(new PriorityComparator());
/* 113 */       setContriChannels(channels);
/*     */     }
/* 115 */     channels.add(channel);
/* 116 */     channel.getContriGroups().add(this);
/*     */   }
/*     */ 
/*     */   public CmsGroup()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsGroup(Integer id)
/*     */   {
/* 128 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsGroup(Integer id, String name, Integer priority, Integer allowPerDay, Integer allowMaxFile, Boolean needCaptcha, Boolean needCheck, Boolean regDef)
/*     */   {
/* 152 */     super(id, 
/* 146 */       name, 
/* 147 */       priority, 
/* 148 */       allowPerDay, 
/* 149 */       allowMaxFile, 
/* 150 */       needCaptcha, 
/* 151 */       needCheck, 
/* 152 */       regDef);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsGroup
 * JD-Core Version:    0.6.0
 */