/*     */ package com.jeecms.cms.action.directive.abs;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.ContentTag;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.manager.main.ContentTagMng;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.TemplateDirectiveModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ public abstract class AbstractContentDirective
/*     */   implements TemplateDirectiveModel
/*     */ {
/*     */   public static final String PARAM_TAG_ID = "tagId";
/*     */   public static final String PARAM_TAG_NAME = "tagName";
/*     */   public static final String PARAM_TOPIC_ID = "topicId";
/*     */   public static final String PARAM_CHANNEL_ID = "channelId";
/*     */   public static final String PARAM_CHANNEL_PATH = "channelPath";
/*     */   public static final String PARAM_CHANNEL_OPTION = "channelOption";
/*     */   public static final String PARAM_SITE_ID = "siteId";
/*     */   public static final String PARAM_TYPE_ID = "typeId";
/*     */   public static final String PARAM_RECOMMEND = "recommend";
/*     */   public static final String PARAM_TITLE = "title";
/*     */   public static final String PARAM_IMAGE = "image";
/*     */   public static final String PARAM_ORDER_BY = "orderBy";
/*     */   public static final String PARAM_EXCLUDE_ID = "excludeId";
/*     */ 
/*     */   @Autowired
/*     */   protected ContentTagMng contentTagMng;
/*     */ 
/*     */   @Autowired
/*     */   protected ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   protected CmsSiteMng cmsSiteMng;
/*     */ 
/*     */   @Autowired
/*     */   protected ContentMng contentMng;
/*     */ 
/*     */   protected Integer[] getTagIds(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 114 */     Integer[] ids = DirectiveUtils.getIntArray("tagId", params);
/* 115 */     if ((ids != null) && (ids.length > 0)) {
/* 116 */       return ids;
/*     */     }
/* 118 */     String nameStr = DirectiveUtils.getString("tagName", params);
/* 119 */     if (StringUtils.isBlank(nameStr)) {
/* 120 */       return null;
/*     */     }
/* 122 */     String[] names = StringUtils.split(nameStr, ',');
/* 123 */     Set set = new HashSet();
/*     */ 
/* 125 */     for (String name : names) {
/* 126 */       ContentTag tag = this.contentTagMng.findByNameForTag(name);
/* 127 */       if (tag != null) {
/* 128 */         set.add(tag.getId());
/*     */       }
/*     */     }
/* 131 */     if (set.size() > 0) {
/* 132 */       return (Integer[])set.toArray(new Integer[set.size()]);
/*     */     }
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   protected Integer getTopicId(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 141 */     return DirectiveUtils.getInt("topicId", params);
/*     */   }
/*     */ 
/*     */   protected Integer[] getChannelIds(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 146 */     Integer[] ids = DirectiveUtils.getIntArray("channelId", params);
/* 147 */     if ((ids != null) && (ids.length > 0)) {
/* 148 */       return ids;
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   protected String[] getChannelPaths(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 156 */     String nameStr = DirectiveUtils.getString("channelPath", params);
/* 157 */     if (StringUtils.isBlank(nameStr)) {
/* 158 */       return null;
/*     */     }
/* 160 */     return StringUtils.split(nameStr, ',');
/*     */   }
/*     */ 
/*     */   protected Integer[] getChannelIdsByPaths(String[] paths, Integer siteId) {
/* 164 */     Set set = new HashSet();
/*     */ 
/* 166 */     for (String path : paths) {
/* 167 */       Channel channel = this.channelMng.findByPathForTag(path, siteId);
/* 168 */       if (channel != null) {
/* 169 */         set.add(channel.getId());
/*     */       }
/*     */     }
/* 172 */     if (set.size() > 0) {
/* 173 */       return (Integer[])set.toArray(new Integer[set.size()]);
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   protected Integer[] getChannelIdsOrPaths(Map<String, TemplateModel> params, Integer[] siteIds)
/*     */     throws TemplateException
/*     */   {
/* 191 */     Integer[] ids = getChannelIds(params);
/* 192 */     if (ids != null) {
/* 193 */       return ids;
/*     */     }
/* 195 */     String[] paths = getChannelPaths(params);
/* 196 */     if (paths == null) {
/* 197 */       return null;
/*     */     }
/* 199 */     Set set = new HashSet();
/*     */     int i;
/*     */     Object localObject;
/*     */     CmsSite site;
/* 201 */     if (siteIds == null) {
/* 202 */       List list = this.cmsSiteMng.getListFromCache();
/* 203 */       siteIds = new Integer[list.size()];
/* 204 */       i = 0;
/* 205 */       for (localObject = list.iterator(); ((Iterator)localObject).hasNext(); ) { site = (CmsSite)((Iterator)localObject).next();
/* 206 */         siteIds[(i++)] = site.getId();
/*     */       }
/*     */     }
/* 209 */     for (Integer siteId : siteIds) {
/* 210 */       for (String path : paths) {
/* 211 */         Channel channel = this.channelMng.findByPathForTag(path, siteId);
/* 212 */         if (channel != null) {
/* 213 */           set.add(channel.getId());
/*     */         }
/*     */       }
/*     */     }
/* 217 */     if (set.size() > 0) {
/* 218 */       return (Integer[])set.toArray(new Integer[set.size()]);
/*     */     }
/* 220 */     return null;
/*     */   }
/*     */ 
/*     */   protected int getChannelOption(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 226 */     Integer option = DirectiveUtils.getInt("channelOption", params);
/* 227 */     if ((option == null) || (option.intValue() < 0) || (option.intValue() > 2)) {
/* 228 */       return 0;
/*     */     }
/* 230 */     return option.intValue();
/*     */   }
/*     */ 
/*     */   protected Integer[] getSiteIds(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 236 */     Integer[] siteIds = DirectiveUtils.getIntArray("siteId", params);
/* 237 */     return siteIds;
/*     */   }
/*     */ 
/*     */   protected Integer[] getTypeIds(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 242 */     Integer[] typeIds = DirectiveUtils.getIntArray("typeId", params);
/* 243 */     return typeIds;
/*     */   }
/*     */ 
/*     */   protected Boolean getHasTitleImg(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 248 */     String titleImg = DirectiveUtils.getString("image", params);
/* 249 */     if ("1".equals(titleImg))
/* 250 */       return Boolean.valueOf(true);
/* 251 */     if ("2".equals(titleImg)) {
/* 252 */       return Boolean.valueOf(false);
/*     */     }
/* 254 */     return null;
/*     */   }
/*     */ 
/*     */   protected Boolean getRecommend(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 260 */     String recommend = DirectiveUtils.getString("recommend", params);
/* 261 */     if ("1".equals(recommend))
/* 262 */       return Boolean.valueOf(true);
/* 263 */     if ("2".equals(recommend)) {
/* 264 */       return Boolean.valueOf(false);
/*     */     }
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   protected String getTitle(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 272 */     return DirectiveUtils.getString("title", params);
/*     */   }
/*     */ 
/*     */   protected int getOrderBy(Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 277 */     Integer orderBy = DirectiveUtils.getInt("orderBy", params);
/* 278 */     if (orderBy == null) {
/* 279 */       return 0;
/*     */     }
/* 281 */     return orderBy.intValue();
/*     */   }
/*     */ 
/*     */   protected Object getData(Map<String, TemplateModel> params, Environment env)
/*     */     throws TemplateException
/*     */   {
/* 287 */     int orderBy = getOrderBy(params);
/* 288 */     Boolean titleImg = getHasTitleImg(params);
/* 289 */     Boolean recommend = getRecommend(params);
/* 290 */     Integer[] typeIds = getTypeIds(params);
/* 291 */     Integer[] siteIds = getSiteIds(params);
/* 292 */     String title = getTitle(params);
/* 293 */     int count = FrontUtils.getCount(params);
/*     */ 
/* 295 */     Integer[] tagIds = getTagIds(params);
/* 296 */     if (tagIds != null) {
/* 297 */       Integer[] channelIds = getChannelIdsOrPaths(params, siteIds);
/* 298 */       Integer excludeId = DirectiveUtils.getInt("excludeId", params);
/* 299 */       if (isPage()) {
/* 300 */         int pageNo = FrontUtils.getPageNo(env);
/* 301 */         return this.contentMng.getPageByTagIdsForTag(tagIds, siteIds, 
/* 302 */           channelIds, typeIds, excludeId, titleImg, recommend, 
/* 303 */           title, orderBy, pageNo, count);
/*     */       }
/* 305 */       int first = FrontUtils.getFirst(params);
/* 306 */       return this.contentMng.getListByTagIdsForTag(tagIds, siteIds, 
/* 307 */         channelIds, typeIds, excludeId, titleImg, recommend, 
/* 308 */         title, orderBy, Integer.valueOf(first), Integer.valueOf(count));
/*     */     }
/*     */ 
/* 311 */     Integer topicId = getTopicId(params);
/* 312 */     if (topicId != null) {
/* 313 */       Integer[] channelIds = getChannelIdsOrPaths(params, siteIds);
/* 314 */       if (isPage()) {
/* 315 */         int pageNo = FrontUtils.getPageNo(env);
/* 316 */         return this.contentMng.getPageByTopicIdForTag(topicId, siteIds, 
/* 317 */           channelIds, typeIds, titleImg, recommend, title, 
/* 318 */           orderBy, pageNo, count);
/*     */       }
/* 320 */       int first = FrontUtils.getFirst(params);
/* 321 */       return this.contentMng.getListByTopicIdForTag(topicId, siteIds, 
/* 322 */         channelIds, typeIds, titleImg, recommend, title, 
/* 323 */         orderBy, Integer.valueOf(first), Integer.valueOf(count));
/*     */     }
/*     */ 
/* 326 */     Integer[] channelIds = getChannelIds(params);
/* 327 */     if (channelIds != null) {
/* 328 */       int option = getChannelOption(params);
/* 329 */       if (isPage()) {
/* 330 */         int pageNo = FrontUtils.getPageNo(env);
/* 331 */         return this.contentMng.getPageByChannelIdsForTag(channelIds, 
/* 332 */           typeIds, titleImg, recommend, title, orderBy, option, 
/* 333 */           pageNo, count);
/*     */       }
/* 335 */       int first = FrontUtils.getFirst(params);
/* 336 */       return this.contentMng.getListByChannelIdsForTag(channelIds, 
/* 337 */         typeIds, titleImg, recommend, title, orderBy, option, 
/* 338 */         Integer.valueOf(first), Integer.valueOf(count));
/*     */     }
/*     */ 
/* 341 */     String[] channelPaths = getChannelPaths(params);
/* 342 */     if (channelPaths != null) {
/* 343 */       int option = getChannelOption(params);
/*     */ 
/* 345 */       boolean pathsToIds = false;
/* 346 */       Integer siteId = null;
/* 347 */       if ((siteIds == null) || (siteIds.length == 0)) {
/* 348 */         List siteList = this.cmsSiteMng.getListFromCache();
/* 349 */         if (siteList.size() == 1) {
/* 350 */           pathsToIds = true;
/* 351 */           siteId = ((CmsSite)siteList.get(0)).getId();
/*     */         }
/* 353 */       } else if ((siteIds != null) && (siteIds.length == 1)) {
/* 354 */         pathsToIds = true;
/* 355 */         siteId = siteIds[0];
/*     */       }
/* 357 */       if (pathsToIds) {
/* 358 */         channelIds = getChannelIdsByPaths(channelPaths, siteId);
/* 359 */         if (channelIds != null) {
/* 360 */           if (isPage()) {
/* 361 */             int pageNo = FrontUtils.getPageNo(env);
/* 362 */             return this.contentMng.getPageByChannelIdsForTag(channelIds, 
/* 363 */               typeIds, titleImg, recommend, title, orderBy, 
/* 364 */               option, pageNo, count);
/*     */           }
/* 366 */           int first = FrontUtils.getFirst(params);
/* 367 */           return this.contentMng.getListByChannelIdsForTag(channelIds, 
/* 368 */             typeIds, titleImg, recommend, title, orderBy, 
/* 369 */             option, Integer.valueOf(first), Integer.valueOf(count));
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 375 */         if (isPage()) {
/* 376 */           int pageNo = FrontUtils.getPageNo(env);
/* 377 */           return this.contentMng.getPageByChannelPathsForTag(channelPaths, 
/* 378 */             siteIds, typeIds, titleImg, recommend, title, 
/* 379 */             orderBy, pageNo, count);
/*     */         }
/* 381 */         int first = FrontUtils.getFirst(params);
/* 382 */         return this.contentMng.getListByChannelPathsForTag(channelPaths, 
/* 383 */           siteIds, typeIds, titleImg, recommend, title, 
/* 384 */           orderBy, Integer.valueOf(first), Integer.valueOf(count));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 389 */     if (isPage()) {
/* 390 */       int pageNo = FrontUtils.getPageNo(env);
/* 391 */       return this.contentMng.getPageBySiteIdsForTag(siteIds, typeIds, 
/* 392 */         titleImg, recommend, title, orderBy, pageNo, count);
/*     */     }
/* 394 */     int first = FrontUtils.getFirst(params);
/* 395 */     return this.contentMng.getListBySiteIdsForTag(siteIds, typeIds, 
/* 396 */       titleImg, recommend, title, orderBy, Integer.valueOf(first), Integer.valueOf(count));
/*     */   }
/*     */ 
/*     */   protected abstract boolean isPage();
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.abs.AbstractContentDirective
 * JD-Core Version:    0.6.0
 */