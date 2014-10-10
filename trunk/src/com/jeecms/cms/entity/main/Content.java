/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.main.base.BaseContent;
/*     */ import com.jeecms.cms.staticpage.StaticPageUtils;
/*     */ import com.jeecms.cms.web.CmsThreadVariable;
/*     */ import com.jeecms.core.web.ContentInterface;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class Content extends BaseContent
/*     */   implements ContentInterface
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  60 */   private DateFormat df = new SimpleDateFormat("/yyyyMMdd");
/*     */ 
/*     */   public Boolean getStaticContent() {
/*  63 */     Channel channel = getChannel();
/*  64 */     if (channel != null) {
/*  65 */       return channel.getStaticContent();
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/*  77 */     if (!StringUtils.isBlank(getLink())) {
/*  78 */       return getLink();
/*     */     }
/*  80 */     if (getStaticContent().booleanValue()) {
/*  81 */       return getUrlStatic(Boolean.valueOf(false), 1);
/*     */     }
/*  83 */     return getUrlDynamic(null);
/*     */   }
/*     */ 
/*     */   public String getUrlWhole()
/*     */   {
/*  88 */     if (!StringUtils.isBlank(getLink())) {
/*  89 */       return getLink();
/*     */     }
/*  91 */     if (getStaticContent().booleanValue()) {
/*  92 */       return getUrlStatic(Boolean.valueOf(true), 1);
/*     */     }
/*  94 */     return getUrlDynamic(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public String getUrlStatic()
/*     */   {
/* 100 */     return getUrlStatic(null, 1);
/*     */   }
/*     */ 
/*     */   public String getUrlStatic(int pageNo) {
/* 104 */     return getUrlStatic(null, pageNo);
/*     */   }
/*     */ 
/*     */   public String getUrlStatic(Boolean whole, int pageNo) {
/* 108 */     if (!StringUtils.isBlank(getLink())) {
/* 109 */       return getLink();
/*     */     }
/* 111 */     CmsSite site = getSite();
/* 112 */     StringBuilder url = site.getUrlBuffer(false, whole, false);
/* 113 */     String filename = getStaticFilenameByRule();
/* 114 */     if (!StringUtils.isBlank(filename)) {
/* 115 */       if (pageNo > 1) {
/* 116 */         int index = filename.indexOf(".", filename.lastIndexOf("/"));
/* 117 */         if (index != -1) {
/* 118 */           url.append(filename.subSequence(0, index)).append("_");
/* 119 */           url.append(pageNo).append(
/* 120 */             filename.subSequence(index, filename.length()));
/*     */         } else {
/* 122 */           url.append(filename).append("_").append(pageNo);
/*     */         }
/*     */       } else {
/* 125 */         url.append(filename);
/*     */       }
/*     */     }
/*     */     else {
/* 129 */       url.append("/").append(getChannel().getPath());
/* 130 */       url.append(this.df.format(getReleaseDate()));
/* 131 */       url.append("/").append(getId());
/* 132 */       if (pageNo > 1) {
/* 133 */         url.append("_").append(pageNo);
/*     */       }
/* 135 */       url.append(site.getStaticSuffix());
/*     */     }
/*     */ 
/* 138 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public String getUrlDynamic() {
/* 142 */     return getUrlDynamic(null);
/*     */   }
/*     */ 
/*     */   public String getStaticFilename(int pageNo) {
/* 146 */     CmsSite site = getSite();
/* 147 */     StringBuilder url = new StringBuilder();
/* 148 */     String staticDir = site.getStaticDir();
/* 149 */     if (!StringUtils.isBlank(staticDir)) {
/* 150 */       url.append(staticDir);
/*     */     }
/* 152 */     String filename = getStaticFilenameByRule();
/* 153 */     if (!StringUtils.isBlank(filename)) {
/* 154 */       int index = filename.indexOf(".", filename.lastIndexOf("/"));
/* 155 */       if (pageNo > 1) {
/* 156 */         if (index != -1) {
/* 157 */           url.append(filename.substring(0, index));
/* 158 */           url.append("_").append(pageNo);
/* 159 */           url.append(filename.substring(index));
/*     */         } else {
/* 161 */           url.append(filename).append("_").append(pageNo);
/*     */         }
/*     */       }
/* 164 */       else url.append(filename);
/*     */     }
/*     */     else
/*     */     {
/* 168 */       url.append("/").append(getChannel().getPath());
/* 169 */       url.append(this.df.format(getReleaseDate()));
/* 170 */       url.append("/").append(getId());
/* 171 */       if (pageNo > 1) {
/* 172 */         url.append("_").append(pageNo);
/*     */       }
/* 174 */       url.append(site.getStaticSuffix());
/*     */     }
/* 176 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public String getStaticFilenameByRule() {
/* 180 */     Channel channel = getChannel();
/* 181 */     CmsModel model = channel.getModel();
/* 182 */     String rule = channel.getContentRule();
/* 183 */     if (StringUtils.isBlank(rule)) {
/* 184 */       return null;
/*     */     }
/* 186 */     String url = StaticPageUtils.staticUrlRule(rule, model.getId(), 
/* 187 */       model.getPath(), channel.getId(), channel.getPath(), getId(), 
/* 188 */       getReleaseDate());
/* 189 */     return url;
/*     */   }
/*     */ 
/*     */   public String getUrlDynamic(Boolean whole) {
/* 193 */     if (!StringUtils.isBlank(getLink())) {
/* 194 */       return getLink();
/*     */     }
/* 196 */     CmsSite site = getSite();
/* 197 */     StringBuilder url = site.getUrlBuffer(true, whole, false);
/* 198 */     url.append("/").append(getChannel().getPath());
/* 199 */     url.append("/").append(getId()).append(site.getDynamicSuffix());
/* 200 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public Set<Channel> getChannelsWithoutMain() {
/* 204 */     Set set = new HashSet(getChannels());
/* 205 */     set.remove(getChannel());
/* 206 */     return set;
/*     */   }
/*     */ 
/*     */   public void setContentTxt(ContentTxt txt) {
/* 210 */     Set set = getContentTxtSet();
/* 211 */     if (set == null) {
/* 212 */       set = new HashSet();
/* 213 */       setContentTxtSet(set);
/*     */     }
/* 215 */     if (!set.isEmpty()) {
/* 216 */       set.clear();
/*     */     }
/* 218 */     set.add(txt);
/*     */   }
/*     */ 
/*     */   public void setContentCheck(ContentCheck check) {
/* 222 */     Set set = getContentCheckSet();
/* 223 */     if (set == null) {
/* 224 */       set = new HashSet();
/* 225 */       setContentCheckSet(set);
/*     */     }
/* 227 */     if (!set.isEmpty()) {
/* 228 */       set.clear();
/*     */     }
/* 230 */     set.add(check);
/*     */   }
/*     */ 
/*     */   public void addToChannels(Channel channel) {
/* 234 */     Set channels = getChannels();
/* 235 */     if (channels == null) {
/* 236 */       channels = new HashSet();
/* 237 */       setChannels(channels);
/*     */     }
/* 239 */     channels.add(channel);
/*     */   }
/*     */ 
/*     */   public void addToTopics(CmsTopic topic) {
/* 243 */     Set topics = getTopics();
/* 244 */     if (topics == null) {
/* 245 */       topics = new HashSet();
/* 246 */       setTopics(topics);
/*     */     }
/* 248 */     topics.add(topic);
/*     */   }
/*     */ 
/*     */   public void addToGroups(CmsGroup group) {
/* 252 */     Set groups = getViewGroups();
/* 253 */     if (groups == null) {
/* 254 */       groups = new HashSet();
/* 255 */       setViewGroups(groups);
/*     */     }
/* 257 */     groups.add(group);
/*     */   }
/*     */ 
/*     */   public void addToAttachmemts(String path, String name, String filename) {
/* 261 */     List list = getAttachments();
/* 262 */     if (list == null) {
/* 263 */       list = new ArrayList();
/* 264 */       setAttachments(list);
/*     */     }
/* 266 */     ContentAttachment ca = new ContentAttachment(path, name, Integer.valueOf(0));
/* 267 */     if (!StringUtils.isBlank(filename)) {
/* 268 */       ca.setFilename(filename);
/*     */     }
/* 270 */     list.add(ca);
/*     */   }
/*     */ 
/*     */   public void addToPictures(String path, String desc) {
/* 274 */     List list = getPictures();
/* 275 */     if (list == null) {
/* 276 */       list = new ArrayList();
/* 277 */       setPictures(list);
/*     */     }
/* 279 */     ContentPicture cp = new ContentPicture();
/* 280 */     cp.setImgPath(path);
/* 281 */     cp.setDescription(desc);
/* 282 */     list.add(cp);
/*     */   }
/*     */ 
/*     */   public String getTagStr() {
/* 286 */     List<ContentTag> tags = getTags();
/* 287 */     if ((tags != null) && (tags.size() > 0)) {
/* 288 */       StringBuilder sb = new StringBuilder();
/* 289 */       for (ContentTag tag : tags) {
/* 290 */         sb.append(tag.getName()).append(',');
/*     */       }
/* 292 */       return sb.substring(0, sb.length() - 1);
/*     */     }
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isDraft()
/*     */   {
/* 304 */     return getStatus().byteValue() == 0;
/*     */   }
/*     */ 
/*     */   public boolean isChecked()
/*     */   {
/* 313 */     return 2 == getStatus().byteValue();
/*     */   }
/*     */ 
/*     */   public Set<CmsGroup> getViewGroupsExt() {
/* 317 */     Set set = getViewGroups();
/* 318 */     if ((set != null) && (set.size() > 0)) {
/* 319 */       return set;
/*     */     }
/* 321 */     return getChannel().getViewGroups();
/*     */   }
/*     */ 
/*     */   public String getTplContentOrDef()
/*     */   {
/* 326 */     String tpl = getTplContent();
/* 327 */     if (!StringUtils.isBlank(tpl)) {
/* 328 */       return tpl;
/*     */     }
/* 330 */     return getChannel().getTplContentOrDef(getModel());
/*     */   }
/*     */ 
/*     */   public boolean isHasUpdateRight()
/*     */   {
/* 340 */     CmsUser user = CmsThreadVariable.getUser();
/* 341 */     if (user == null) {
/* 342 */       throw new IllegalStateException("CmsUser not found in CmsThread");
/*     */     }
/* 344 */     return isHasUpdateRight(user);
/*     */   }
/*     */ 
/*     */   public boolean isHasUpdateRight(CmsUser user)
/*     */   {
/* 354 */     Channel.AfterCheckEnum after = getChannel().getAfterCheckEnum();
/* 355 */     if (Channel.AfterCheckEnum.CANNOT_UPDATE == after) {
/* 356 */       CmsSite site = getSite();
/* 357 */       Byte userStep = user.getCheckStep(site.getId());
/* 358 */       Byte channelStep = getChannel().getFinalStepExtends();
/* 359 */       boolean checked = getStatus().byteValue() == 2;
/*     */ 
/* 363 */       return (getCheckStep().byteValue() <= userStep.byteValue()) && (
/* 362 */         (!checked) || (userStep.byteValue() >= channelStep.byteValue()));
/*     */     }
/*     */ 
/* 367 */     if ((Channel.AfterCheckEnum.BACK_UPDATE == after) || 
/* 368 */       (Channel.AfterCheckEnum.KEEP_UPDATE == after)) {
/* 369 */       return true;
/*     */     }
/* 371 */     throw new RuntimeException("AfterCheckEnum '" + after + 
/* 372 */       "' did not handled");
/*     */   }
/*     */ 
/*     */   public boolean isHasDeleteRight()
/*     */   {
/* 382 */     CmsUser user = CmsThreadVariable.getUser();
/* 383 */     if (user == null) {
/* 384 */       throw new IllegalStateException("CmsUser not found in CmsThread");
/*     */     }
/* 386 */     return isHasDeleteRight(user);
/*     */   }
/*     */ 
/*     */   public boolean isHasDeleteRight(CmsUser user)
/*     */   {
/* 396 */     Channel.AfterCheckEnum after = getChannel().getAfterCheckEnum();
/* 397 */     if (Channel.AfterCheckEnum.CANNOT_UPDATE == after) {
/* 398 */       CmsSite site = getSite();
/* 399 */       Byte userStep = user.getCheckStep(site.getId());
/* 400 */       Byte channelStep = getChannel().getFinalStepExtends();
/* 401 */       boolean checked = getStatus().byteValue() == 2;
/*     */ 
/* 405 */       return (getCheckStep().byteValue() <= userStep.byteValue()) && (
/* 404 */         (!checked) || (userStep.byteValue() >= channelStep.byteValue()));
/*     */     }
/*     */ 
/* 409 */     if ((Channel.AfterCheckEnum.BACK_UPDATE == after) || 
/* 410 */       (Channel.AfterCheckEnum.KEEP_UPDATE == after)) {
/* 411 */       return true;
/*     */     }
/* 413 */     throw new RuntimeException("AfterCheckEnum '" + after + 
/* 414 */       "' did not handled");
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/* 420 */     short zero = 0;
/* 421 */     byte bzero = 0;
/* 422 */     if (getViewsDay() == null) {
/* 423 */       setViewsDay(Integer.valueOf(0));
/*     */     }
/* 425 */     if (getCommentsDay() == null) {
/* 426 */       setCommentsDay(Short.valueOf(zero));
/*     */     }
/* 428 */     if (getDownloadsDay() == null) {
/* 429 */       setDownloadsDay(Short.valueOf(zero));
/*     */     }
/* 431 */     if (getUpsDay() == null) {
/* 432 */       setUpsDay(Short.valueOf(zero));
/*     */     }
/* 434 */     if (getHasTitleImg() == null) {
/* 435 */       setHasTitleImg(Boolean.valueOf(false));
/*     */     }
/* 437 */     if (getRecommend() == null) {
/* 438 */       setRecommend(Boolean.valueOf(false));
/*     */     }
/* 440 */     if (getSortDate() == null) {
/* 441 */       setSortDate(new Timestamp(System.currentTimeMillis()));
/*     */     }
/* 443 */     if (getTopLevel() == null) {
/* 444 */       setTopLevel(Byte.valueOf(bzero));
/*     */     }
/*     */ 
/* 447 */     if (getChannels() == null) {
/* 448 */       setChannels(new HashSet());
/*     */     }
/* 450 */     if (getTopics() == null) {
/* 451 */       setTopics(new HashSet());
/*     */     }
/* 453 */     if (getViewGroups() == null) {
/* 454 */       setViewGroups(new HashSet());
/*     */     }
/* 456 */     if (getTags() == null) {
/* 457 */       setTags(new ArrayList());
/*     */     }
/* 459 */     if (getPictures() == null) {
/* 460 */       setPictures(new ArrayList());
/*     */     }
/* 462 */     if (getAttachments() == null)
/* 463 */       setAttachments(new ArrayList());
/*     */   }
/*     */ 
/*     */   public int getPageCount()
/*     */   {
/* 468 */     int txtCount = getTxtCount();
/* 469 */     if (txtCount <= 1) {
/* 470 */       List pics = getPictures();
/* 471 */       if (pics != null) {
/* 472 */         int picCount = pics.size();
/* 473 */         if (picCount > 1) {
/* 474 */           return picCount;
/*     */         }
/*     */       }
/*     */     }
/* 478 */     return txtCount;
/*     */   }
/*     */ 
/*     */   public int getTxtCount() {
/* 482 */     ContentTxt txt = getContentTxt();
/* 483 */     if (txt != null) {
/* 484 */       return txt.getTxtCount();
/*     */     }
/* 486 */     return 1;
/*     */   }
/*     */ 
/*     */   public ContentPicture getPictureByNo(int pageNo)
/*     */   {
/* 491 */     List list = getPictures();
/* 492 */     if ((pageNo >= 1) && (list != null) && (list.size() >= pageNo)) {
/* 493 */       return (ContentPicture)list.get(pageNo - 1);
/*     */     }
/* 495 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxtByNo(int pageNo)
/*     */   {
/* 500 */     ContentTxt txt = getContentTxt();
/* 501 */     if (txt != null) {
/* 502 */       return txt.getTxtByNo(pageNo);
/*     */     }
/* 504 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitleByNo(int pageNo)
/*     */   {
/* 509 */     ContentTxt txt = getContentTxt();
/* 510 */     if (txt != null) {
/* 511 */       return txt.getTitleByNo(pageNo);
/*     */     }
/* 513 */     return getTitle();
/*     */   }
/*     */ 
/*     */   public String getStitle()
/*     */   {
/* 518 */     ContentExt ext = getContentExt();
/* 519 */     if (ext != null) {
/* 520 */       return ext.getStitle();
/*     */     }
/* 522 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 527 */     ContentExt ext = getContentExt();
/* 528 */     if (ext != null) {
/* 529 */       return ext.getTitle();
/*     */     }
/* 531 */     return null;
/*     */   }
/*     */ 
/*     */   public String getShortTitle()
/*     */   {
/* 536 */     ContentExt ext = getContentExt();
/* 537 */     if (ext != null) {
/* 538 */       return ext.getShortTitle();
/*     */     }
/* 540 */     return null;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 545 */     ContentExt ext = getContentExt();
/* 546 */     if (ext != null) {
/* 547 */       return ext.getDescription();
/*     */     }
/* 549 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/* 554 */     ContentExt ext = getContentExt();
/* 555 */     if (ext != null) {
/* 556 */       return ext.getAuthor();
/*     */     }
/* 558 */     return null;
/*     */   }
/*     */ 
/*     */   public String getOrigin()
/*     */   {
/* 563 */     ContentExt ext = getContentExt();
/* 564 */     if (ext != null) {
/* 565 */       return ext.getOrigin();
/*     */     }
/* 567 */     return null;
/*     */   }
/*     */ 
/*     */   public String getOriginUrl()
/*     */   {
/* 572 */     ContentExt ext = getContentExt();
/* 573 */     if (ext != null) {
/* 574 */       return ext.getOriginUrl();
/*     */     }
/* 576 */     return null;
/*     */   }
/*     */ 
/*     */   public Date getReleaseDate()
/*     */   {
/* 581 */     ContentExt ext = getContentExt();
/* 582 */     if (ext != null) {
/* 583 */       return ext.getReleaseDate();
/*     */     }
/* 585 */     return null;
/*     */   }
/*     */ 
/*     */   public String getMediaPath()
/*     */   {
/* 590 */     ContentExt ext = getContentExt();
/* 591 */     if (ext != null) {
/* 592 */       return ext.getMediaPath();
/*     */     }
/* 594 */     return null;
/*     */   }
/*     */ 
/*     */   public String getMediaType()
/*     */   {
/* 599 */     ContentExt ext = getContentExt();
/* 600 */     if (ext != null) {
/* 601 */       return ext.getMediaType();
/*     */     }
/* 603 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitleColor()
/*     */   {
/* 608 */     ContentExt ext = getContentExt();
/* 609 */     if (ext != null) {
/* 610 */       return ext.getTitleColor();
/*     */     }
/* 612 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getBold()
/*     */   {
/* 617 */     ContentExt ext = getContentExt();
/* 618 */     if (ext != null) {
/* 619 */       return ext.getBold();
/*     */     }
/* 621 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitleImg()
/*     */   {
/* 626 */     ContentExt ext = getContentExt();
/* 627 */     if (ext != null) {
/* 628 */       return ext.getTitleImg();
/*     */     }
/* 630 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContentImg()
/*     */   {
/* 635 */     ContentExt ext = getContentExt();
/* 636 */     if (ext != null) {
/* 637 */       return ext.getContentImg();
/*     */     }
/* 639 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTypeImg()
/*     */   {
/* 644 */     ContentExt ext = getContentExt();
/* 645 */     if (ext != null) {
/* 646 */       return ext.getTypeImg();
/*     */     }
/* 648 */     return null;
/*     */   }
/*     */ 
/*     */   public String getLink()
/*     */   {
/* 653 */     ContentExt ext = getContentExt();
/* 654 */     if (ext != null) {
/* 655 */       return ext.getLink();
/*     */     }
/* 657 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTplContent()
/*     */   {
/* 662 */     ContentExt ext = getContentExt();
/* 663 */     if (ext != null) {
/* 664 */       return ext.getTplContent();
/*     */     }
/* 666 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getNeedRegenerate()
/*     */   {
/* 671 */     ContentExt ext = getContentExt();
/* 672 */     if (ext != null) {
/* 673 */       return ext.getNeedRegenerate();
/*     */     }
/* 675 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNeedRegenerate(Boolean isNeed)
/*     */   {
/* 680 */     ContentExt ext = getContentExt();
/* 681 */     if (ext != null)
/* 682 */       ext.setNeedRegenerate(isNeed);
/*     */   }
/*     */ 
/*     */   public String getTxt()
/*     */   {
/* 687 */     ContentTxt txt = getContentTxt();
/* 688 */     if (txt != null) {
/* 689 */       return txt.getTxt();
/*     */     }
/* 691 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt1()
/*     */   {
/* 696 */     ContentTxt txt = getContentTxt();
/* 697 */     if (txt != null) {
/* 698 */       return txt.getTxt1();
/*     */     }
/* 700 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt2()
/*     */   {
/* 705 */     ContentTxt txt = getContentTxt();
/* 706 */     if (txt != null) {
/* 707 */       return txt.getTxt2();
/*     */     }
/* 709 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt3()
/*     */   {
/* 714 */     ContentTxt txt = getContentTxt();
/* 715 */     if (txt != null) {
/* 716 */       return txt.getTxt3();
/*     */     }
/* 718 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getViews()
/*     */   {
/* 723 */     ContentCount count = getContentCount();
/* 724 */     if (count != null) {
/* 725 */       return count.getViews();
/*     */     }
/* 727 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getCommentsCount()
/*     */   {
/* 732 */     ContentCount count = getContentCount();
/* 733 */     if (count != null) {
/* 734 */       return count.getComments();
/*     */     }
/* 736 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getCommentsCheckedNum()
/*     */   {
/* 741 */     Set<CmsComment> comments = getComments();
/* 742 */     int num = 0;
/* 743 */     if (comments != null) {
/* 744 */       for (CmsComment comment : comments) {
/* 745 */         if (comment.getChecked().booleanValue()) {
/* 746 */           num++;
/*     */         }
/*     */       }
/* 749 */       return Integer.valueOf(num);
/*     */     }
/* 751 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   public Integer getUps()
/*     */   {
/* 756 */     ContentCount count = getContentCount();
/* 757 */     if (count != null) {
/* 758 */       return count.getUps();
/*     */     }
/* 760 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getDowns()
/*     */   {
/* 765 */     ContentCount count = getContentCount();
/* 766 */     if (count != null) {
/* 767 */       return count.getDowns();
/*     */     }
/* 769 */     return null;
/*     */   }
/*     */ 
/*     */   public Byte getCheckStep()
/*     */   {
/* 774 */     ContentCheck check = getContentCheck();
/* 775 */     if (check != null) {
/* 776 */       return check.getCheckStep();
/*     */     }
/* 778 */     return null;
/*     */   }
/*     */ 
/*     */   public String getCheckOpinion()
/*     */   {
/* 783 */     ContentCheck check = getContentCheck();
/* 784 */     if (check != null) {
/* 785 */       return check.getCheckOpinion();
/*     */     }
/* 787 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getRejected()
/*     */   {
/* 792 */     ContentCheck check = getContentCheck();
/* 793 */     if (check != null) {
/* 794 */       return check.getRejected();
/*     */     }
/* 796 */     return null;
/*     */   }
/*     */ 
/*     */   public ContentTxt getContentTxt()
/*     */   {
/* 801 */     Set set = getContentTxtSet();
/* 802 */     if ((set != null) && (set.size() > 0)) {
/* 803 */       return (ContentTxt)set.iterator().next();
/*     */     }
/* 805 */     return null;
/*     */   }
/*     */ 
/*     */   public ContentCheck getContentCheck()
/*     */   {
/* 810 */     Set set = getContentCheckSet();
/* 811 */     if ((set != null) && (set.size() > 0)) {
/* 812 */       return (ContentCheck)set.iterator().next();
/*     */     }
/* 814 */     return null;
/*     */   }
/*     */ 
/*     */   public String getDesc()
/*     */   {
/* 819 */     return getDescription();
/*     */   }
/*     */ 
/*     */   public String getCtgName() {
/* 823 */     return getChannel().getName();
/*     */   }
/*     */ 
/*     */   public String getCtgUrl() {
/* 827 */     return getChannel().getUrl();
/*     */   }
/*     */ 
/*     */   public String getImgUrl() {
/* 831 */     return getTitleImg();
/*     */   }
/*     */ 
/*     */   public String getImgUrl2() {
/* 835 */     return getTypeImg();
/*     */   }
/*     */ 
/*     */   public String getStit() {
/* 839 */     String stit = getShortTitle();
/* 840 */     if (!StringUtils.isBlank(stit)) {
/* 841 */       return stit;
/*     */     }
/* 843 */     return getTit();
/*     */   }
/*     */ 
/*     */   public String getTit()
/*     */   {
/* 848 */     return getTitle();
/*     */   }
/*     */ 
/*     */   public String getTitCol() {
/* 852 */     return getTitleColor();
/*     */   }
/*     */ 
/*     */   public String getSiteName() {
/* 856 */     return getSite().getName();
/*     */   }
/*     */ 
/*     */   public String getSiteUrl() {
/* 860 */     return getSite().getUrl();
/*     */   }
/*     */ 
/*     */   public String getCompanyName() {
/* 864 */     return getSite().getSiteCompany().getName();
/*     */   }
/*     */ 
/*     */   public String getCompanyAddr() {
/* 868 */     return getSite().getSiteCompany().getAddress();
/*     */   }
/*     */ 
/*     */   public String getCompanyScale() {
/* 872 */     return getSite().getSiteCompany().getScale();
/*     */   }
/*     */ 
/*     */   public String getCompanyNature() {
/* 876 */     return getSite().getSiteCompany().getNature();
/*     */   }
/*     */ 
/*     */   public String getCompanyIndustry() {
/* 880 */     return getSite().getSiteCompany().getIndustry();
/*     */   }
/*     */ 
/*     */   public String getCompanyDesc() {
/* 884 */     return getSite().getSiteCompany().getDescription();
/*     */   }
/*     */ 
/*     */   public String getCompanyContact() {
/* 888 */     return getSite().getSiteCompany().getContact();
/*     */   }
/*     */ 
/*     */   public boolean isTitBold() {
/* 892 */     return getBold().booleanValue();
/*     */   }
/*     */ 
/*     */   public Date getDate() {
/* 896 */     return getReleaseDate();
/*     */   }
/*     */ 
/*     */   public Boolean getTarget() {
/* 900 */     return null;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 904 */     getCollectUsers().clear();
/*     */   }
/*     */ 
/*     */   public Content()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Content(Integer id)
/*     */   {
/* 916 */     super(id);
/*     */   }
/*     */ 
/*     */   public Content(Integer id, CmsSite site, Date sortDate, Byte topLevel, Boolean hasTitleImg, Boolean recommend, Byte status, Integer viewsDay, Short commentsDay, Short downloadsDay, Short upsDay)
/*     */   {
/* 930 */     super(id, site, sortDate, topLevel, hasTitleImg, recommend, status, 
/* 930 */       viewsDay, commentsDay, downloadsDay, upsDay);
/*     */   }
/*     */ 
/*     */   public static enum ContentStatus
/*     */   {
/*  33 */     all, 
/*     */ 
/*  37 */     draft, 
/*     */ 
/*  41 */     prepared, 
/*     */ 
/*  45 */     passed, 
/*     */ 
/*  49 */     checked, 
/*     */ 
/*  53 */     rejected, 
/*     */ 
/*  57 */     recycle;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.Content
 * JD-Core Version:    0.6.0
 */