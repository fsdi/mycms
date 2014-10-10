/*     */ package com.jeecms.cms.dao.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ContentDao;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.Content.ContentStatus;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class ContentDaoImpl extends HibernateBaseDao<Content, Integer>
/*     */   implements ContentDao
/*     */ {
/*     */   public Pagination getPage(String title, Integer typeId, Integer inputUserId, boolean topLevel, boolean recommend, Content.ContentStatus status, Byte checkStep, Integer siteId, Integer channelId, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  33 */     Finder f = Finder.create("select bean from Content bean");
/*  34 */     if ((Content.ContentStatus.prepared == status) || (Content.ContentStatus.passed == status) || (Content.ContentStatus.rejected == status)) {
/*  35 */       f.append(" join bean.contentCheckSet check");
/*     */     }
/*  37 */     if (channelId != null) {
/*  38 */       f.append(" join bean.channel channel,Channel parent");
/*  39 */       f.append(" where channel.lft between parent.lft and parent.rgt");
/*  40 */       f.append(" and channel.site.id=parent.site.id");
/*  41 */       f.append(" and parent.id=:parentId");
/*  42 */       f.setParam("parentId", channelId);
/*  43 */     } else if (siteId != null) {
/*  44 */       f.append(" where bean.site.id=:siteId");
/*  45 */       f.setParam("siteId", siteId);
/*     */     } else {
/*  47 */       f.append(" where 1=1");
/*     */     }
/*  49 */     if (Content.ContentStatus.prepared == status) {
/*  50 */       f.append(" and check.checkStep<:checkStep");
/*  51 */       f.append(" and check.rejected=false");
/*  52 */       f.setParam("checkStep", checkStep);
/*  53 */     } else if (Content.ContentStatus.passed == status) {
/*  54 */       f.append(" and check.checkStep=:checkStep");
/*  55 */       f.append(" and check.rejected=false");
/*  56 */       f.setParam("checkStep", checkStep);
/*  57 */     } else if (Content.ContentStatus.rejected == status)
/*     */     {
/*  59 */       f.append(" and check.checkStep=:checkStep");
/*  60 */       f.append(" and check.rejected=true");
/*  61 */       f.setParam("checkStep", checkStep);
/*     */     }
/*  63 */     appendQuery(f, title, typeId, inputUserId, status, topLevel, recommend);
/*  64 */     if (Content.ContentStatus.prepared == status)
/*  65 */       f.append(" order by check.checkStep desc,bean.id desc");
/*     */     else {
/*  67 */       appendOrder(f, orderBy);
/*     */     }
/*  69 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Pagination getPageForCollection(Integer siteId, Integer memberId, int pageNo, int pageSize) {
/*  73 */     Finder f = Finder.create("select bean from Content bean join bean.collectUsers user where user.id=:userId").setParam("userId", memberId);
/*  74 */     if (siteId != null) {
/*  75 */       f.append(" and bean.site.id=:siteId");
/*  76 */       f.setParam("siteId", siteId);
/*     */     }
/*  78 */     f.append(" and bean.status<>:status");
/*  79 */     f.setParam("status", Byte.valueOf((byte)3));
/*  80 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Pagination getPageBySelf(String title, Integer typeId, Integer inputUserId, boolean topLevel, boolean recommend, Content.ContentStatus status, Byte checkStep, Integer siteId, Integer channelId, Integer userId, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  88 */     Finder f = Finder.create("select bean from Content bean");
/*  89 */     if ((Content.ContentStatus.prepared == status) || (Content.ContentStatus.passed == status) || (Content.ContentStatus.rejected == status)) {
/*  90 */       f.append(" join bean.contentCheckSet check");
/*     */     }
/*  92 */     if (channelId != null) {
/*  93 */       f.append(" join bean.channel channel,Channel parent");
/*  94 */       f.append(" where channel.lft between parent.lft and parent.rgt");
/*  95 */       f.append(" and channel.site.id=parent.site.id");
/*  96 */       f.append(" and parent.id=:parentId");
/*  97 */       f.setParam("parentId", channelId);
/*  98 */     } else if (siteId != null) {
/*  99 */       f.append(" where bean.site.id=:siteId");
/* 100 */       f.setParam("siteId", siteId);
/*     */     } else {
/* 102 */       f.append(" where 1=1");
/*     */     }
/* 104 */     f.append(" and bean.user.id=:userId");
/* 105 */     f.setParam("userId", userId);
/* 106 */     if (Content.ContentStatus.prepared == status) {
/* 107 */       f.append(" and check.checkStep<:checkStep");
/* 108 */       f.append(" and check.rejected=false");
/* 109 */       f.setParam("checkStep", checkStep);
/* 110 */     } else if (Content.ContentStatus.passed == status) {
/* 111 */       f.append(" and check.checkStep=:checkStep");
/* 112 */       f.append(" and check.rejected=false");
/* 113 */       f.setParam("checkStep", checkStep);
/* 114 */     } else if (Content.ContentStatus.rejected == status) {
/* 115 */       f.append(" and check.checkStep=:checkStep");
/* 116 */       f.append(" and check.rejected=true");
/* 117 */       f.setParam("checkStep", checkStep);
/*     */     }
/* 119 */     appendQuery(f, title, typeId, inputUserId, status, topLevel, recommend);
/* 120 */     if (Content.ContentStatus.prepared == status)
/* 121 */       f.append(" order by check.checkStep desc,bean.id desc");
/*     */     else {
/* 123 */       appendOrder(f, orderBy);
/*     */     }
/* 125 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Pagination getPageByRight(String title, Integer typeId, Integer inputUserId, boolean topLevel, boolean recommend, Content.ContentStatus status, Byte checkStep, Integer siteId, Integer channelId, Integer userId, boolean selfData, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 133 */     Finder f = Finder.create("select bean from Content bean");
/* 134 */     if ((Content.ContentStatus.prepared == status) || (Content.ContentStatus.passed == status) || (Content.ContentStatus.rejected == status)) {
/* 135 */       f.append(" join bean.contentCheckSet check");
/*     */     }
/* 137 */     f.append(" join bean.channel channel join channel.users user");
/* 138 */     if (channelId != null) {
/* 139 */       f.append(",Channel parent");
/* 140 */       f.append(" where channel.lft between parent.lft and parent.rgt");
/* 141 */       f.append(" and channel.site.id=parent.site.id");
/* 142 */       f.append(" and parent.id=:parentId");
/* 143 */       f.setParam("parentId", channelId);
/* 144 */       f.append(" and user.id=:userId");
/* 145 */       f.setParam("userId", userId);
/* 146 */     } else if (siteId != null) {
/* 147 */       f.append(" where user.id=:userId");
/* 148 */       f.setParam("userId", userId);
/* 149 */       f.append(" and bean.site.id=:siteId");
/* 150 */       f.setParam("siteId", siteId);
/*     */     } else {
/* 152 */       f.append(" where user.id=:userId");
/* 153 */       f.setParam("userId", userId);
/*     */     }
/* 155 */     if (selfData)
/*     */     {
/* 157 */       f.append(" and bean.user.id=:userId");
/*     */     }
/* 159 */     if (Content.ContentStatus.prepared == status) {
/* 160 */       f.append(" and check.checkStep<:checkStep");
/* 161 */       f.append(" and check.rejected=false");
/* 162 */       f.setParam("checkStep", checkStep);
/* 163 */     } else if (Content.ContentStatus.passed == status) {
/* 164 */       f.append(" and check.checkStep=:checkStep");
/* 165 */       f.append(" and check.rejected=false");
/* 166 */       f.setParam("checkStep", checkStep);
/* 167 */     } else if (Content.ContentStatus.rejected == status) {
/* 168 */       f.append(" and check.checkStep=:checkStep");
/* 169 */       f.append(" and check.rejected=true");
/* 170 */       f.setParam("checkStep", checkStep);
/*     */     }
/* 172 */     appendQuery(f, title, typeId, inputUserId, status, topLevel, recommend);
/* 173 */     if (Content.ContentStatus.prepared == status)
/* 174 */       f.append(" order by check.checkStep desc,bean.id desc");
/*     */     else {
/* 176 */       appendOrder(f, orderBy);
/*     */     }
/* 178 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   private void appendQuery(Finder f, String title, Integer typeId, Integer inputUserId, Content.ContentStatus status, boolean topLevel, boolean recommend)
/*     */   {
/* 184 */     if (!StringUtils.isBlank(title)) {
/* 185 */       f.append(" and bean.contentExt.title like :title");
/* 186 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 188 */     if (typeId != null) {
/* 189 */       f.append(" and bean.type.id=:typeId");
/* 190 */       f.setParam("typeId", typeId);
/*     */     }
/* 192 */     if (inputUserId != null) {
/* 193 */       f.append(" and bean.user.id=:inputUserId");
/* 194 */       f.setParam("inputUserId", inputUserId);
/*     */     }
/* 196 */     if (topLevel) {
/* 197 */       f.append(" and bean.topLevel>0");
/*     */     }
/* 199 */     if (recommend) {
/* 200 */       f.append(" and bean.recommend=true");
/*     */     }
			if (Content.ContentStatus.draft == status) {
			    f.append(" and bean.status=:status");
			    f.setParam("status", Byte.valueOf((byte)0));
			  } else if (Content.ContentStatus.checked == status) {
			    f.append(" and bean.status=:status");
			    f.setParam("status", Byte.valueOf((byte)2));
			  } else if ((Content.ContentStatus.prepared == status) || (Content.ContentStatus.rejected == status)) {
			    f.append(" and bean.status=:status");
			    f.setParam("status", Byte.valueOf((byte)1));
			  } else if (Content.ContentStatus.passed == status) {
			    f.append(" and (bean.status=:checking or bean.status=:checked)");
			    f.setParam("checking", Byte.valueOf((byte)1));
			    f.setParam("checked", Byte.valueOf((byte)2));
			  } else if (Content.ContentStatus.all == status) {
			    f.append(" and bean.status<>:status");
			    f.setParam("status", Byte.valueOf((byte)3));
			  } else if (Content.ContentStatus.recycle == status) {
			    f.append(" and bean.status=:status");
			    f.setParam("status", Byte.valueOf((byte)3));
			  }
/*     */   }
/*     */ 
/*     */   public Content getSide(Integer id, Integer siteId, Integer channelId, boolean next, boolean cacheable)
/*     */   {
/* 228 */     Finder f = Finder.create("from Content bean where 1=1");
/* 229 */     if (channelId != null) {
/* 230 */       f.append(" and bean.channel.id=:channelId");
/* 231 */       f.setParam("channelId", channelId);
/* 232 */     } else if (siteId != null) {
/* 233 */       f.append(" and bean.site.id=:siteId");
/* 234 */       f.setParam("siteId", siteId);
/*     */     }
/* 236 */     if (next) {
/* 237 */       f.append(" and bean.id>:id");
/* 238 */       f.setParam("id", id);
/* 239 */       f.append(" and bean.status=2");
/* 240 */       f.append(" order by bean.id asc");
/*     */     } else {
/* 242 */       f.append(" and bean.id<:id");
/* 243 */       f.setParam("id", id);
/* 244 */       f.append(" and bean.status=2");
/* 245 */       f.append(" order by bean.id desc");
/*     */     }
/* 247 */     Query query = f.createQuery(getSession());
/* 248 */     query.setCacheable(cacheable).setMaxResults(1);
/* 249 */     return (Content)query.uniqueResult();
/*     */   }
/*     */ 
/*     */   public List<Content> getListByIdsForTag(Integer[] ids, int orderBy)
/*     */   {
/* 254 */     Finder f = Finder.create("from Content bean where bean.id in (:ids)");
/* 255 */     f.setParamList("ids", ids);
/* 256 */     appendOrder(f, orderBy);
/* 257 */     f.setCacheable(true);
/* 258 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageBySiteIdsForTag(Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 264 */     Finder f = bySiteIds(siteIds, typeIds, titleImg, recommend, title, 
/* 265 */       orderBy);
/* 266 */     f.setCacheable(true);
/* 267 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Content> getListBySiteIdsForTag(Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 274 */     Finder f = bySiteIds(siteIds, typeIds, titleImg, recommend, title, 
/* 275 */       orderBy);
/* 276 */     if (first != null) {
/* 277 */       f.setFirstResult(first.intValue());
/*     */     }
/* 279 */     if (count != null) {
/* 280 */       f.setMaxResults(count.intValue());
/*     */     }
/* 282 */     f.setCacheable(true);
/* 283 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageByChannelIdsForTag(Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int option, int pageNo, int pageSize)
/*     */   {
/* 289 */     Finder f = byChannelIds(channelIds, typeIds, titleImg, recommend, 
/* 290 */       title, orderBy, option);
/* 291 */     f.setCacheable(true);
/* 292 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Content> getListByChannelIdsForTag(Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int option, Integer first, Integer count)
/*     */   {
/* 299 */     Finder f = byChannelIds(channelIds, typeIds, titleImg, recommend, 
/* 300 */       title, orderBy, option);
/* 301 */     if (first != null) {
/* 302 */       f.setFirstResult(first.intValue());
/*     */     }
/* 304 */     if (count != null) {
/* 305 */       f.setMaxResults(count.intValue());
/*     */     }
/* 307 */     f.setCacheable(true);
/* 308 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageByChannelPathsForTag(String[] paths, Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 315 */     Finder f = byChannelPaths(paths, siteIds, typeIds, titleImg, recommend, 
/* 316 */       title, orderBy);
/* 317 */     f.setCacheable(true);
/* 318 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Content> getListByChannelPathsForTag(String[] paths, Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 326 */     Finder f = byChannelPaths(paths, siteIds, typeIds, titleImg, recommend, 
/* 327 */       title, orderBy);
/* 328 */     if (first != null) {
/* 329 */       f.setFirstResult(first.intValue());
/*     */     }
/* 331 */     if (count != null) {
/* 332 */       f.setMaxResults(count.intValue());
/*     */     }
/* 334 */     f.setCacheable(true);
/* 335 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageByTopicIdForTag(Integer topicId, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 342 */     Finder f = byTopicId(topicId, siteIds, channelIds, typeIds, titleImg, 
/* 343 */       recommend, title, orderBy);
/* 344 */     f.setCacheable(true);
/* 345 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Content> getListByTopicIdForTag(Integer topicId, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 353 */     Finder f = byTopicId(topicId, siteIds, channelIds, typeIds, titleImg, 
/* 354 */       recommend, title, orderBy);
/* 355 */     if (first != null) {
/* 356 */       f.setFirstResult(first.intValue());
/*     */     }
/* 358 */     if (count != null) {
/* 359 */       f.setMaxResults(count.intValue());
/*     */     }
/* 361 */     f.setCacheable(true);
/* 362 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPageByTagIdsForTag(Integer[] tagIds, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Integer excludeId, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 369 */     Finder f = byTagIds(tagIds, siteIds, channelIds, typeIds, excludeId, 
/* 370 */       titleImg, recommend, title, orderBy);
/* 371 */     f.setCacheable(true);
/* 372 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Content> getListByTagIdsForTag(Integer[] tagIds, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Integer excludeId, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 380 */     Finder f = byTagIds(tagIds, siteIds, channelIds, typeIds, excludeId, 
/* 381 */       titleImg, recommend, title, orderBy);
/* 382 */     if (first != null) {
/* 383 */       f.setFirstResult(first.intValue());
/*     */     }
/* 385 */     if (count != null) {
/* 386 */       f.setMaxResults(count.intValue());
/*     */     }
/* 388 */     f.setCacheable(true);
/* 389 */     return find(f);
/*     */   }
/*     */ 
/*     */   private Finder bySiteIds(Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy)
/*     */   {
/* 394 */     Finder f = Finder.create("select bean from Content bean");
/* 395 */     f.append(" join bean.contentExt as ext where 1=1");
/* 396 */     if (titleImg != null) {
/* 397 */       f.append(" and bean.hasTitleImg=:titleImg");
/* 398 */       f.setParam("titleImg", titleImg);
/*     */     }
/* 400 */     if (recommend != null) {
/* 401 */       f.append(" and bean.recommend=:recommend");
/* 402 */       f.setParam("recommend", recommend);
/*     */     }
/* 404 */     appendReleaseDate(f);
/* 405 */     appendTypeIds(f, typeIds);
/* 406 */     appendSiteIds(f, siteIds);
/* 407 */     f.append(" and bean.status=2");
/* 408 */     if (!StringUtils.isBlank(title)) {
/* 409 */       f.append(" and bean.contentExt.title like :title");
/* 410 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 412 */     appendOrder(f, orderBy);
/* 413 */     return f;
/*     */   }
/*     */ 
/*     */   private Finder byChannelIds(Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int option)
/*     */   {
/* 419 */     Finder f = Finder.create();
/* 420 */     int len = channelIds.length;
/*     */ 
/* 422 */     if ((option == 0) || (len > 1)) {
/* 423 */       f.append("select bean from Content bean");
/* 424 */       f.append(" join bean.contentExt as ext");
/* 425 */       if (len == 1) {
/* 426 */         f.append(" where bean.channel.id=:channelId");
/* 427 */         f.setParam("channelId", channelIds[0]);
/*     */       } else {
/* 429 */         f.append(" where bean.channel.id in (:channelIds)");
/* 430 */         f.setParamList("channelIds", channelIds);
/*     */       }
/* 432 */     } else if (option == 1)
/*     */     {
/* 434 */       f.append("select bean from Content bean");
/* 435 */       f.append(" join bean.contentExt as ext");
/* 436 */       f.append(" join bean.channel node,Channel parent");
/* 437 */       f.append(" where node.lft between parent.lft and parent.rgt");
/* 438 */       f.append(" and bean.site.id=parent.site.id");
/* 439 */       f.append(" and parent.id=:channelId");
/* 440 */       f.setParam("channelId", channelIds[0]);
/* 441 */     } else if (option == 2)
/*     */     {
/* 443 */       f.append("select bean from Content bean");
/* 444 */       f.append(" join bean.contentExt as ext");
/* 445 */       f.append(" join bean.channels as channel");
/* 446 */       f.append(" where channel.id=:channelId");
/* 447 */       f.setParam("channelId", channelIds[0]);
/*     */     } else {
/* 449 */       throw new RuntimeException("option value must be 0 or 1 or 2.");
/*     */     }
/* 451 */     if (titleImg != null) {
/* 452 */       f.append(" and bean.hasTitleImg=:titleImg");
/* 453 */       f.setParam("titleImg", titleImg);
/*     */     }
/* 455 */     if (recommend != null) {
/* 456 */       f.append(" and bean.recommend=:recommend");
/* 457 */       f.setParam("recommend", recommend);
/*     */     }
/* 459 */     appendReleaseDate(f);
/* 460 */     appendTypeIds(f, typeIds);
/* 461 */     f.append(" and bean.status=2");
/* 462 */     if (!StringUtils.isBlank(title)) {
/* 463 */       f.append(" and bean.contentExt.title like :title");
/* 464 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 466 */     appendOrder(f, orderBy);
/* 467 */     return f;
/*     */   }
/*     */ 
/*     */   private Finder byChannelPaths(String[] paths, Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy)
/*     */   {
/* 473 */     Finder f = Finder.create();
/* 474 */     f.append("select bean from Content bean join bean.channel channel");
/* 475 */     f.append(" join bean.contentExt as ext");
/* 476 */     int len = paths.length;
/* 477 */     if (len == 1) {
/* 478 */       f.append(" where channel.path=:path").setParam("path", paths[0]);
/*     */     } else {
/* 480 */       f.append(" where channel.path in (:paths)");
/* 481 */       f.setParamList("paths", paths);
/*     */     }
/* 483 */     if (siteIds != null) {
/* 484 */       len = siteIds.length;
/* 485 */       if (len == 1) {
/* 486 */         f.append(" and channel.site.id=:siteId");
/* 487 */         f.setParam("siteId", siteIds[0]);
/* 488 */       } else if (len > 1) {
/* 489 */         f.append(" and channel.site.id in (:siteIds)");
/* 490 */         f.setParamList("siteIds", siteIds);
/*     */       }
/*     */     }
/* 493 */     if (titleImg != null) {
/* 494 */       f.append(" and bean.hasTitleImg=:titleImg");
/* 495 */       f.setParam("titleImg", titleImg);
/*     */     }
/* 497 */     if (recommend != null) {
/* 498 */       f.append(" and bean.recommend=:recommend");
/* 499 */       f.setParam("recommend", recommend);
/*     */     }
/* 501 */     appendReleaseDate(f);
/* 502 */     appendTypeIds(f, typeIds);
/* 503 */     f.append(" and bean.status=2");
/* 504 */     if (!StringUtils.isBlank(title)) {
/* 505 */       f.append(" and bean.contentExt.title like :title");
/* 506 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 508 */     appendOrder(f, orderBy);
/* 509 */     return f;
/*     */   }
/*     */ 
/*     */   private Finder byTopicId(Integer topicId, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy)
/*     */   {
/* 515 */     Finder f = Finder.create();
/* 516 */     f.append("select bean from Content bean join bean.topics topic");
/* 517 */     f.append(" join bean.contentExt as ext");
/* 518 */     f.append(" where topic.id=:topicId").setParam("topicId", topicId);
/* 519 */     if (titleImg != null) {
/* 520 */       f.append(" and bean.hasTitleImg=:titleImg");
/* 521 */       f.setParam("titleImg", titleImg);
/*     */     }
/* 523 */     if (recommend != null) {
/* 524 */       f.append(" and bean.recommend=:recommend");
/* 525 */       f.setParam("recommend", recommend);
/*     */     }
/* 527 */     appendReleaseDate(f);
/* 528 */     appendTypeIds(f, typeIds);
/* 529 */     appendChannelIds(f, channelIds);
/* 530 */     appendSiteIds(f, siteIds);
/* 531 */     f.append(" and bean.status=2");
/* 532 */     if (!StringUtils.isBlank(title)) {
/* 533 */       f.append(" and bean.contentExt.title like :title");
/* 534 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 536 */     appendOrder(f, orderBy);
/* 537 */     return f;
/*     */   }
/*     */ 
/*     */   private Finder byTagIds(Integer[] tagIds, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Integer excludeId, Boolean titleImg, Boolean recommend, String title, int orderBy)
/*     */   {
/* 543 */     Finder f = Finder.create();
/* 544 */     int len = tagIds.length;
/* 545 */     if (len == 1) {
/* 546 */       f.append("select bean from Content bean join bean.tags tag");
/* 547 */       f.append(" join bean.contentExt as ext");
/* 548 */       f.append(" where tag.id=:tagId").setParam("tagId", tagIds[0]);
/*     */     } else {
/* 550 */       f.append("select distinct bean from Content bean");
/* 551 */       f.append(" join bean.contentExt as ext");
/* 552 */       f.append(" join bean.tags tag");
/* 553 */       f.append(" where tag.id in(:tagIds)");
/* 554 */       f.setParamList("tagIds", tagIds);
/*     */     }
/* 556 */     if (titleImg != null) {
/* 557 */       f.append(" and bean.hasTitleImg=:titleImg");
/* 558 */       f.setParam("titleImg", titleImg);
/*     */     }
/* 560 */     if (recommend != null) {
/* 561 */       f.append(" and bean.recommend=:recommend");
/* 562 */       f.setParam("recommend", recommend);
/*     */     }
/* 564 */     appendReleaseDate(f);
/* 565 */     appendTypeIds(f, typeIds);
/* 566 */     appendChannelIds(f, channelIds);
/* 567 */     appendSiteIds(f, siteIds);
/* 568 */     if (excludeId != null) {
/* 569 */       f.append(" and bean.id<>:excludeId");
/* 570 */       f.setParam("excludeId", excludeId);
/*     */     }
/* 572 */     f.append(" and bean.status=2");
/* 573 */     if (!StringUtils.isBlank(title)) {
/* 574 */       f.append(" and bean.contentExt.title like :title");
/* 575 */       f.setParam("title", "%" + title + "%");
/*     */     }
/* 577 */     appendOrder(f, orderBy);
/* 578 */     return f;
/*     */   }
/*     */ 
/*     */   private void appendReleaseDate(Finder f) {
/* 582 */     f.append(" and ext.releaseDate<:currentDate");
/* 583 */     f.setParam("currentDate", new Date());
/*     */   }
/*     */ 
/*     */   private void appendTypeIds(Finder f, Integer[] typeIds)
/*     */   {
/* 588 */     if (typeIds != null) {
/* 589 */       int len = typeIds.length;
/* 590 */       if (len == 1) {
/* 591 */         f.append(" and bean.type.id=:typeId");
/* 592 */         f.setParam("typeId", typeIds[0]);
/* 593 */       } else if (len > 1) {
/* 594 */         f.append(" and bean.type.id in (:typeIds)");
/* 595 */         f.setParamList("typeIds", typeIds);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendChannelIds(Finder f, Integer[] channelIds)
/*     */   {
/* 602 */     if (channelIds != null) {
/* 603 */       int len = channelIds.length;
/* 604 */       if (len == 1) {
/* 605 */         f.append(" and bean.channel.id=:channelId");
/* 606 */         f.setParam("channelId", channelIds[0]);
/* 607 */       } else if (len > 1) {
/* 608 */         f.append(" and bean.channel.id in (:channelIds)");
/* 609 */         f.setParamList("channelIds", channelIds);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendSiteIds(Finder f, Integer[] siteIds)
/*     */   {
/* 616 */     if (siteIds != null) {
/* 617 */       int len = siteIds.length;
/* 618 */       if (len == 1) {
/* 619 */         f.append(" and bean.site.id=:siteId");
/* 620 */         f.setParam("siteId", siteIds[0]);
/* 621 */       } else if (len > 1) {
/* 622 */         f.append(" and bean.site.id in (:siteIds)");
/* 623 */         f.setParamList("siteIds", siteIds);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendOrder(Finder f, int orderBy) {
/* 629 */     switch (orderBy)
/*     */     {
/*     */     case 1:
/* 632 */       f.append(" order by bean.id asc");
/* 633 */       break;
/*     */     case 2:
/* 636 */       f.append(" order by bean.sortDate desc");
/* 637 */       break;
/*     */     case 3:
/* 640 */       f.append(" order by bean.sortDate asc");
/* 641 */       break;
/*     */     case 4:
/* 644 */       f.append(" order by bean.topLevel desc, bean.sortDate desc");
/* 645 */       break;
/*     */     case 5:
/* 648 */       f.append(" order by bean.topLevel desc, bean.sortDate asc");
/* 649 */       break;
/*     */     case 6:
/* 652 */       f.append(" order by bean.viewsDay desc, bean.id desc");
/* 653 */       break;
/*     */     case 7:
/* 656 */       f.append(" order by bean.contentCount.viewsWeek desc");
/* 657 */       f.append(", bean.id desc");
/* 658 */       break;
/*     */     case 8:
/* 661 */       f.append(" order by bean.contentCount.viewsMonth desc");
/* 662 */       f.append(", bean.id desc");
/* 663 */       break;
/*     */     case 9:
/* 666 */       f.append(" order by bean.contentCount.views desc");
/* 667 */       f.append(", bean.id desc");
/* 668 */       break;
/*     */     case 10:
/* 671 */       f.append(" order by bean.commentsDay desc, bean.id desc");
/* 672 */       break;
/*     */     case 11:
/* 675 */       f.append(" order by bean.contentCount.commentsWeek desc");
/* 676 */       f.append(", bean.id desc");
/* 677 */       break;
/*     */     case 12:
/* 680 */       f.append(" order by bean.contentCount.commentsMonth desc");
/* 681 */       f.append(", bean.id desc");
/* 682 */       break;
/*     */     case 13:
/* 685 */       f.append(" order by bean.contentCount.comments desc");
/* 686 */       f.append(", bean.id desc");
/* 687 */       break;
/*     */     case 14:
/* 690 */       f.append(" order by bean.downloadsDay desc, bean.id desc");
/* 691 */       break;
/*     */     case 15:
/* 694 */       f.append(" order by bean.contentCount.downloadsWeek desc");
/* 695 */       f.append(", bean.id desc");
/* 696 */       break;
/*     */     case 16:
/* 699 */       f.append(" order by bean.contentCount.downloadsMonth desc");
/* 700 */       f.append(", bean.id desc");
/* 701 */       break;
/*     */     case 17:
/* 704 */       f.append(" order by bean.contentCount.downloads desc");
/* 705 */       f.append(", bean.id desc");
/* 706 */       break;
/*     */     case 18:
/* 709 */       f.append(" order by bean.upsDay desc, bean.id desc");
/* 710 */       break;
/*     */     case 19:
/* 713 */       f.append(" order by bean.contentCount.upsWeek desc");
/* 714 */       f.append(", bean.id desc");
/* 715 */       break;
/*     */     case 20:
/* 718 */       f.append(" order by bean.contentCount.upsMonth desc");
/* 719 */       f.append(", bean.id desc");
/* 720 */       break;
/*     */     case 21:
/* 723 */       f.append(" order by bean.contentCount.ups desc, bean.id desc");
/* 724 */       break;
/*     */     default:
/* 727 */       f.append(" order by bean.id desc");
/*     */     }
/*     */   }
/*     */ 
/*     */   public int countByChannelId(int channelId) {
/* 732 */     String hql = "select count(*) from Content bean join bean.channel channel,Channel parent where channel.lft between parent.lft and parent.rgt and channel.site.id=parent.site.id and parent.id=:parentId";
/*     */ 
/* 737 */     Query query = getSession().createQuery(hql);
/* 738 */     query.setParameter("parentId", Integer.valueOf(channelId));
/* 739 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   public Content findById(Integer id) {
/* 743 */     Content entity = (Content)get(id);
/* 744 */     return entity;
/*     */   }
/*     */ 
/*     */   public Content save(Content bean) {
/* 748 */     getSession().save(bean);
/* 749 */     return bean;
/*     */   }
/*     */ 
/*     */   public Content deleteById(Integer id) {
/* 753 */     Content entity = (Content)super.get(id);
/* 754 */     if (entity != null) {
/* 755 */       getSession().delete(entity);
/*     */     }
/* 757 */     return entity;
/*     */   }
/*     */ 
/*     */   protected Class<Content> getEntityClass()
/*     */   {
/* 762 */     return Content.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentDaoImpl
 * JD-Core Version:    0.6.0
 */