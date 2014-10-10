/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseChannel;
/*     */ import com.jeecms.cms.staticpage.StaticPageUtils;
/*     */ import com.jeecms.common.hibernate3.HibernateTree;
/*     */ import com.jeecms.common.hibernate3.PriorityComparator;
/*     */ import com.jeecms.common.hibernate3.PriorityInterface;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class Channel extends BaseChannel
/*     */   implements HibernateTree<Integer>, PriorityInterface
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getUrl()
/*     */   {
/*  52 */     if (!StringUtils.isBlank(getLink())) {
/*  53 */       return getLink();
/*     */     }
/*  55 */     if (getStaticChannel().booleanValue()) {
/*  56 */       return getUrlStatic(Boolean.valueOf(false), 1);
/*     */     }
/*  58 */     return getUrlDynamic(null);
/*     */   }
/*     */ 
/*     */   public String getUrlWhole()
/*     */   {
/*  63 */     if (!StringUtils.isBlank(getLink())) {
/*  64 */       return getLink();
/*     */     }
/*  66 */     if (getStaticChannel().booleanValue()) {
/*  67 */       return getUrlStatic(Boolean.valueOf(true), 1);
/*     */     }
/*  69 */     return getUrlDynamic(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public String getUrlStatic()
/*     */   {
/*  79 */     return getUrlStatic(null, 1);
/*     */   }
/*     */ 
/*     */   public String getUrlStatic(int pageNo) {
/*  83 */     return getUrlStatic(null, pageNo);
/*     */   }
/*     */ 
/*     */   public String getUrlStatic(Boolean whole, int pageNo) {
/*  87 */     if (!StringUtils.isBlank(getLink())) {
/*  88 */       return getLink();
/*     */     }
/*  90 */     CmsSite site = getSite();
/*  91 */     StringBuilder url = site.getUrlBuffer(false, whole, false);
/*  92 */     String filename = getStaticFilenameByRule();
/*  93 */     if (!StringUtils.isBlank(filename)) {
/*  94 */       if (pageNo > 1) {
/*  95 */         int index = filename.indexOf(".", filename.lastIndexOf("/"));
/*  96 */         if (index != -1) {
/*  97 */           url.append(filename.substring(0, index));
/*  98 */           url.append("_").append(pageNo);
/*  99 */           url.append(filename.substring(index));
/*     */         } else {
/* 101 */           url.append("_").append(pageNo);
/*     */         }
/*     */       }
/* 104 */       else if (getAccessByDir().booleanValue()) {
/* 105 */         url.append(filename
/* 106 */           .substring(0, 
/* 106 */           filename.lastIndexOf("/") + 1));
/*     */       } else {
/* 108 */         url.append(filename);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 113 */       url.append("/").append(getPath());
/* 114 */       if (pageNo > 1) {
/* 115 */         url.append("_").append(pageNo);
/* 116 */         url.append(site.getStaticSuffix());
/*     */       }
/* 118 */       else if (getHasContent().booleanValue()) {
/* 119 */         url.append("/");
/*     */       } else {
/* 121 */         url.append(site.getStaticSuffix());
/*     */       }
/*     */     }
/*     */ 
/* 125 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public String getStaticFilename(int pageNo) {
/* 129 */     CmsSite site = getSite();
/* 130 */     StringBuilder url = new StringBuilder();
/* 131 */     String staticDir = site.getStaticDir();
/* 132 */     if (!StringUtils.isBlank(staticDir)) {
/* 133 */       url.append(staticDir);
/*     */     }
/* 135 */     String filename = getStaticFilenameByRule();
/* 136 */     if (!StringUtils.isBlank(filename)) {
/* 137 */       int index = filename.indexOf(".", filename.lastIndexOf("/"));
/* 138 */       if (pageNo > 1) {
/* 139 */         if (index != -1)
/* 140 */           url.append(filename.substring(0, index)).append("_")
/* 141 */             .append(pageNo).append(filename.substring(index));
/*     */         else
/* 143 */           url.append(filename).append("_").append(pageNo);
/*     */       }
/*     */       else
/* 146 */         url.append(filename);
/*     */     }
/*     */     else
/*     */     {
/* 150 */       url.append("/").append(getPath());
/* 151 */       String suffix = site.getStaticSuffix();
/* 152 */       if (getHasContent().booleanValue()) {
/* 153 */         url.append("/").append("index");
/* 154 */         if (pageNo > 1) {
/* 155 */           url.append("_").append(pageNo);
/*     */         }
/* 157 */         url.append(suffix);
/*     */       } else {
/* 159 */         if (pageNo > 1) {
/* 160 */           url.append("_").append(pageNo);
/*     */         }
/* 162 */         url.append(suffix);
/*     */       }
/*     */     }
/* 165 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public String getStaticFilenameByRule() {
/* 169 */     String rule = getChannelRule();
/* 170 */     if (StringUtils.isBlank(rule)) {
/* 171 */       return null;
/*     */     }
/* 173 */     CmsModel model = getModel();
/* 174 */     String url = StaticPageUtils.staticUrlRule(rule, model.getId(), 
/* 175 */       model.getPath(), getId(), getPath(), null, null);
/* 176 */     return url;
/*     */   }
/*     */ 
/*     */   public String getUrlDynamic()
/*     */   {
/* 185 */     return getUrlDynamic(null);
/*     */   }
/*     */ 
/*     */   public String getUrlDynamic(Boolean whole) {
/* 189 */     if (!StringUtils.isBlank(getLink())) {
/* 190 */       return getLink();
/*     */     }
/* 192 */     CmsSite site = getSite();
/* 193 */     StringBuilder url = site.getUrlBuffer(true, whole, false);
/* 194 */     url.append("/").append(getPath());
/* 195 */     if (getHasContent().booleanValue()) {
/* 196 */       url.append("/").append("index");
/*     */     }
/* 198 */     url.append(site.getDynamicSuffix());
/* 199 */     return url.toString();
/*     */   }
/*     */ 
/*     */   public List<Channel> getNodeList()
/*     */   {
/* 208 */     LinkedList list = new LinkedList();
/* 209 */     Channel node = this;
/* 210 */     while (node != null) {
/* 211 */       list.addFirst(node);
/* 212 */       node = node.getParent();
/*     */     }
/* 214 */     return list;
/*     */   }
/*     */ 
/*     */   public Integer[] getNodeIds()
/*     */   {
/* 223 */     List<Channel> channels = getNodeList();
/* 224 */     Integer[] ids = new Integer[channels.size()];
/* 225 */     int i = 0;
/* 226 */     for (Channel c : channels) {
/* 227 */       ids[(i++)] = c.getId();
/*     */     }
/* 229 */     return ids;
/*     */   }
/*     */ 
/*     */   public int getDeep()
/*     */   {
/* 238 */     int deep = 0;
/* 239 */     Channel parent = getParent();
/* 240 */     while (parent != null) {
/* 241 */       deep++;
/* 242 */       parent = parent.getParent();
/*     */     }
/* 244 */     return deep;
/*     */   }
/*     */ 
/*     */   public Byte getFinalStepExtends()
/*     */   {
/* 253 */     Byte step = getFinalStep();
/* 254 */     if (step == null) {
/* 255 */       Channel parent = getParent();
/* 256 */       if (parent == null) {
/* 257 */         return getSite().getFinalStep();
/*     */       }
/* 259 */       return parent.getFinalStepExtends();
/*     */     }
/*     */ 
/* 262 */     return step;
/*     */   }
/*     */ 
/*     */   public Byte getAfterCheck()
/*     */   {
/* 267 */     ChannelExt ext = getChannelExt();
/* 268 */     if (ext != null) {
/* 269 */       return ext.getAfterCheck();
/*     */     }
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   public AfterCheckEnum getAfterCheckEnum()
/*     */   {
/* 281 */     Byte after = getChannelExt().getAfterCheck();
/* 282 */     Channel channel = getParent();
/*     */ 
/* 284 */     while ((after == null) && (channel != null)) {
/* 285 */       after = channel.getAfterCheck();
/* 286 */       channel = channel.getParent();
/*     */     }
/*     */ 
/* 289 */     if (after == null) {
/* 290 */       after = getSite().getAfterCheck();
/*     */     }
/* 292 */     if (after.byteValue() == 1)
/* 293 */       return AfterCheckEnum.CANNOT_UPDATE;
/* 294 */     if (after.byteValue() == 2)
/* 295 */       return AfterCheckEnum.BACK_UPDATE;
/* 296 */     if (after.byteValue() == 3) {
/* 297 */       return AfterCheckEnum.KEEP_UPDATE;
/*     */     }
/*     */ 
/* 300 */     return AfterCheckEnum.CANNOT_UPDATE;
/*     */   }
/*     */ 
/*     */   public List<Channel> getListForSelect(Set<Channel> rights, boolean hasContentOnly)
/*     */   {
/* 311 */     return getListForSelect(rights, null, hasContentOnly);
/*     */   }
/*     */ 
/*     */   public List<Channel> getListForSelect(Set<Channel> rights, Channel exclude, boolean hasContentOnly)
/*     */   {
/* 316 */     List list = new ArrayList((getRgt().intValue() - getLft().intValue()) / 2);
/* 317 */     addChildToList(list, this, rights, exclude, hasContentOnly);
/* 318 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<Channel> getListForSelect(List<Channel> topList, Set<Channel> rights, boolean hasContentOnly)
/*     */   {
/* 330 */     return getListForSelect(topList, rights, null, hasContentOnly);
/*     */   }
/*     */ 
/*     */   public static List<Channel> getListForSelect(List<Channel> topList, Set<Channel> rights, Channel exclude, boolean hasContentOnly)
/*     */   {
/* 335 */     List list = new ArrayList();
/* 336 */     for (Channel c : topList) {
/* 337 */       addChildToList(list, c, rights, exclude, hasContentOnly);
/*     */     }
/* 339 */     return list;
/*     */   }
/*     */ 
/*     */   private static void addChildToList(List<Channel> list, Channel channel, Set<Channel> rights, Channel exclude, boolean hasContentOnly)
/*     */   {
/* 354 */     if (((rights != null) && (!rights.contains(channel))) || (
/* 355 */       (exclude != null) && (exclude.equals(channel)))) {
/* 356 */       return;
/*     */     }
/* 358 */     list.add(channel);
/* 359 */     Set<Channel> child = channel.getChild();
/* 360 */     for (Channel c : child)
/* 361 */       if (hasContentOnly) {
/* 362 */         if (c.getHasContent().booleanValue())
/* 363 */           addChildToList(list, c, rights, exclude, hasContentOnly);
/*     */       }
/*     */       else
/* 366 */         addChildToList(list, c, rights, exclude, hasContentOnly);
/*     */   }
/*     */ 
/*     */   public String getTplChannelOrDef()
/*     */   {
/* 372 */     String tpl = getTplChannel();
/* 373 */     if (!StringUtils.isBlank(tpl)) {
/* 374 */       return tpl;
/*     */     }
/* 376 */     String sol = getSite().getSolutionPath();
/* 377 */     return getModel().getTplChannel(sol, true);
/*     */   }
/*     */ 
/*     */   public String getTplContentOrDef(CmsModel contentModel)
/*     */   {
/* 392 */     String tpl = getModelTpl(contentModel);
/* 393 */     if (!StringUtils.isBlank(tpl)) {
/* 394 */       return tpl;
/*     */     }
/* 396 */     String sol = getSite().getSolutionPath();
/* 397 */     return contentModel.getTplContent(sol, true);
/*     */   }
/*     */ 
/*     */   public Integer[] getUserIds()
/*     */   {
/* 403 */     Set users = getUsers();
/* 404 */     return CmsUser.fetchIds(users);
/*     */   }
/*     */ 
/*     */   public void addToViewGroups(CmsGroup group) {
/* 408 */     Set groups = getViewGroups();
/* 409 */     if (groups == null) {
/* 410 */       groups = new TreeSet(new PriorityComparator());
/* 411 */       setViewGroups(groups);
/*     */     }
/* 413 */     groups.add(group);
/* 414 */     group.getViewChannels().add(this);
/*     */   }
/*     */ 
/*     */   public void addToContriGroups(CmsGroup group) {
/* 418 */     Set groups = getContriGroups();
/* 419 */     if (groups == null) {
/* 420 */       groups = new TreeSet(new PriorityComparator());
/* 421 */       setContriGroups(groups);
/*     */     }
/* 423 */     groups.add(group);
/* 424 */     group.getContriChannels().add(this);
/*     */   }
/*     */ 
/*     */   public void addToUsers(CmsUser user) {
/* 428 */     Set set = getUsers();
/* 429 */     if (set == null) {
/* 430 */       set = new TreeSet(new PriorityComparator());
/* 431 */       setUsers(set);
/*     */     }
/* 433 */     set.add(user);
/* 434 */     user.addToChannels(this);
/*     */   }
/*     */ 
/*     */   public void addToChannelModels(CmsModel model, String tpl) {
/* 438 */     List list = getChannelModels();
/* 439 */     if (list == null) {
/* 440 */       list = new ArrayList();
/* 441 */       setChannelModels(list);
/*     */     }
/* 443 */     ChannelModel cm = new ChannelModel();
/* 444 */     cm.setTplContent(tpl);
/* 445 */     cm.setModel(model);
/* 446 */     list.add(cm);
/*     */   }
/*     */ 
/*     */   public List<ChannelModel> getChannelModelsExtend() {
/* 450 */     List list = getChannelModels();
/*     */ 
/* 452 */     if ((list == null) || (list.size() <= 0)) {
/* 453 */       Channel parent = getParent();
/* 454 */       if (parent == null) {
/* 455 */         return null;
/*     */       }
/* 457 */       return parent.getChannelModelsExtend();
/*     */     }
/*     */ 
/* 460 */     return list;
/*     */   }
/*     */ 
/*     */   public List<CmsModel> getModels()
/*     */   {
/* 465 */     List<ChannelModel> list = getChannelModelsExtend();
/* 466 */     if (list == null) {
/* 467 */       return null;
/*     */     }
/* 469 */     List models = new ArrayList();
/* 470 */     for (ChannelModel cm : list) {
/* 471 */       models.add(cm.getModel());
/*     */     }
/* 473 */     return models;
/*     */   }
/*     */ 
/*     */   public List<CmsModel> getModels(List<CmsModel> allModels) {
/* 477 */     List<ChannelModel> list = getChannelModelsExtend();
/*     */ 
/* 479 */     if (list == null) {
/* 480 */       return allModels;
/*     */     }
/* 482 */     List models = new ArrayList();
/* 483 */     for (ChannelModel cm : list) {
/* 484 */       models.add(cm.getModel());
/*     */     }
/* 486 */     return models;
/*     */   }
/*     */ 
/*     */   public List<String> getModelIds() {
/* 490 */     List ids = new ArrayList();
/* 491 */     List<CmsModel> models = getModels();
/* 492 */     if (models != null) {
/* 493 */       for (CmsModel model : models) {
/* 494 */         ids.add(model.getId().toString());
/*     */       }
/*     */     }
/* 497 */     return ids;
/*     */   }
/*     */ 
/*     */   public List<String> getModelTpls() {
/* 501 */     List<ChannelModel> list = getChannelModelsExtend();
/* 502 */     List tpls = new ArrayList();
/*     */ 
/* 504 */     int tplPathLength = getSite().getTplPath().length();
/* 505 */     if (list != null) {
/* 506 */       for (ChannelModel cm : list) {
/* 507 */         String tpl = cm.getTplContent();
/* 508 */         if (StringUtils.isNotBlank(tpl)) {
/* 509 */           tpls.add(tpl.substring(tplPathLength));
/*     */         }
/*     */       }
/*     */     }
/* 513 */     return tpls;
/*     */   }
/*     */ 
/*     */   public String getModelTpl(CmsModel model) {
/* 517 */     List<ChannelModel> list = getChannelModelsExtend();
/* 518 */     if (list != null) {
/* 519 */       for (ChannelModel cm : list) {
/* 520 */         if (cm.getModel().equals(model)) {
/* 521 */           return cm.getTplContent();
/*     */         }
/*     */       }
/*     */     }
/* 525 */     return null;
/*     */   }
/*     */ 
/*     */   public void init() {
/* 529 */     if (getPriority() == null) {
/* 530 */       setPriority(Integer.valueOf(10));
/*     */     }
/* 532 */     if (getDisplay() == null)
/* 533 */       setDisplay(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 538 */     ChannelExt ext = getChannelExt();
/* 539 */     if (ext != null) {
/* 540 */       return ext.getName();
/*     */     }
/* 542 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getStaticChannel()
/*     */   {
/* 547 */     ChannelExt ext = getChannelExt();
/* 548 */     if (ext != null) {
/* 549 */       return ext.getStaticChannel();
/*     */     }
/* 551 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getStaticContent()
/*     */   {
/* 556 */     ChannelExt ext = getChannelExt();
/* 557 */     if (ext != null) {
/* 558 */       return ext.getStaticContent();
/*     */     }
/* 560 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getAccessByDir()
/*     */   {
/* 565 */     ChannelExt ext = getChannelExt();
/* 566 */     if (ext != null) {
/* 567 */       return ext.getAccessByDir();
/*     */     }
/* 569 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getListChild()
/*     */   {
/* 574 */     ChannelExt ext = getChannelExt();
/* 575 */     if (ext != null) {
/* 576 */       return ext.getListChild();
/*     */     }
/* 578 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getPageSize()
/*     */   {
/* 583 */     ChannelExt ext = getChannelExt();
/* 584 */     if (ext != null) {
/* 585 */       return ext.getPageSize();
/*     */     }
/* 587 */     return null;
/*     */   }
/*     */ 
/*     */   public String getChannelRule()
/*     */   {
/* 592 */     ChannelExt ext = getChannelExt();
/* 593 */     if (ext != null) {
/* 594 */       return ext.getChannelRule();
/*     */     }
/* 596 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContentRule()
/*     */   {
/* 601 */     ChannelExt ext = getChannelExt();
/* 602 */     if (ext != null) {
/* 603 */       return ext.getContentRule();
/*     */     }
/* 605 */     return null;
/*     */   }
/*     */ 
/*     */   public Byte getFinalStep()
/*     */   {
/* 610 */     ChannelExt ext = getChannelExt();
/* 611 */     if (ext != null) {
/* 612 */       return ext.getFinalStep();
/*     */     }
/* 614 */     return null;
/*     */   }
/*     */ 
/*     */   public String getLink()
/*     */   {
/* 619 */     ChannelExt ext = getChannelExt();
/* 620 */     if (ext != null) {
/* 621 */       return ext.getLink();
/*     */     }
/* 623 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTplChannel()
/*     */   {
/* 628 */     ChannelExt ext = getChannelExt();
/* 629 */     if (ext != null) {
/* 630 */       return ext.getTplChannel();
/*     */     }
/* 632 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTplContent()
/*     */   {
/* 637 */     ChannelExt ext = getChannelExt();
/* 638 */     if (ext != null) {
/* 639 */       return ext.getTplContent();
/*     */     }
/* 641 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getHasTitleImg()
/*     */   {
/* 646 */     ChannelExt ext = getChannelExt();
/* 647 */     if (ext != null) {
/* 648 */       return ext.getHasTitleImg();
/*     */     }
/* 650 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getHasContentImg()
/*     */   {
/* 655 */     ChannelExt ext = getChannelExt();
/* 656 */     if (ext != null) {
/* 657 */       return ext.getHasContentImg();
/*     */     }
/* 659 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgWidth()
/*     */   {
/* 664 */     ChannelExt ext = getChannelExt();
/* 665 */     if (ext != null) {
/* 666 */       return ext.getTitleImgWidth();
/*     */     }
/* 668 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgHeight()
/*     */   {
/* 673 */     ChannelExt ext = getChannelExt();
/* 674 */     if (ext != null) {
/* 675 */       return ext.getTitleImgHeight();
/*     */     }
/* 677 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgWidth()
/*     */   {
/* 682 */     ChannelExt ext = getChannelExt();
/* 683 */     if (ext != null) {
/* 684 */       return ext.getContentImgWidth();
/*     */     }
/* 686 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgHeight()
/*     */   {
/* 691 */     ChannelExt ext = getChannelExt();
/* 692 */     if (ext != null) {
/* 693 */       return ext.getContentImgHeight();
/*     */     }
/* 695 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitleImg()
/*     */   {
/* 700 */     ChannelExt ext = getChannelExt();
/* 701 */     if (ext != null) {
/* 702 */       return ext.getTitleImg();
/*     */     }
/* 704 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContentImg()
/*     */   {
/* 709 */     ChannelExt ext = getChannelExt();
/* 710 */     if (ext != null) {
/* 711 */       return ext.getContentImg();
/*     */     }
/* 713 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 718 */     ChannelExt ext = getChannelExt();
/* 719 */     if (ext != null) {
/* 720 */       return ext.getTitle();
/*     */     }
/* 722 */     return null;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 727 */     ChannelExt ext = getChannelExt();
/* 728 */     if (ext != null) {
/* 729 */       return ext.getKeywords();
/*     */     }
/* 731 */     return null;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 736 */     ChannelExt ext = getChannelExt();
/* 737 */     if (ext != null) {
/* 738 */       return ext.getDescription();
/*     */     }
/* 740 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer getCommentControl()
/*     */   {
/* 745 */     ChannelExt ext = getChannelExt();
/* 746 */     if (ext != null) {
/* 747 */       return ext.getCommentControl();
/*     */     }
/* 749 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getAllowUpdown()
/*     */   {
/* 754 */     ChannelExt ext = getChannelExt();
/* 755 */     if (ext != null) {
/* 756 */       return ext.getAllowUpdown();
/*     */     }
/* 758 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getBlank()
/*     */   {
/* 763 */     ChannelExt ext = getChannelExt();
/* 764 */     if (ext != null) {
/* 765 */       return ext.getBlank();
/*     */     }
/* 767 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt()
/*     */   {
/* 777 */     ChannelTxt txt = getChannelTxt();
/* 778 */     if (txt != null) {
/* 779 */       return txt.getTxt();
/*     */     }
/* 781 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt1()
/*     */   {
/* 791 */     ChannelTxt txt = getChannelTxt();
/* 792 */     if (txt != null) {
/* 793 */       return txt.getTxt1();
/*     */     }
/* 795 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt2()
/*     */   {
/* 805 */     ChannelTxt txt = getChannelTxt();
/* 806 */     if (txt != null) {
/* 807 */       return txt.getTxt2();
/*     */     }
/* 809 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTxt3()
/*     */   {
/* 819 */     ChannelTxt txt = getChannelTxt();
/* 820 */     if (txt != null) {
/* 821 */       return txt.getTxt3();
/*     */     }
/* 823 */     return null;
/*     */   }
/*     */ 
/*     */   public ChannelTxt getChannelTxt()
/*     */   {
/* 828 */     Set set = getChannelTxtSet();
/* 829 */     if ((set != null) && (set.size() > 0)) {
/* 830 */       return (ChannelTxt)set.iterator().next();
/*     */     }
/* 832 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTreeCondition()
/*     */   {
/* 842 */     return "bean.site.id=" + getSite().getId();
/*     */   }
/*     */ 
/*     */   public Integer getParentId()
/*     */   {
/* 849 */     Channel parent = getParent();
/* 850 */     if (parent != null) {
/* 851 */       return parent.getId();
/*     */     }
/* 853 */     return null;
/*     */   }
/*     */ 
/*     */   public String getLftName()
/*     */   {
/* 861 */     return "lft";
/*     */   }
/*     */ 
/*     */   public String getParentName()
/*     */   {
/* 868 */     return "parent";
/*     */   }
/*     */ 
/*     */   public String getRgtName()
/*     */   {
/* 875 */     return "rgt";
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<Channel> channels) {
/* 879 */     if (channels == null) {
/* 880 */       return null;
/*     */     }
/* 882 */     Integer[] ids = new Integer[channels.size()];
/* 883 */     int i = 0;
/* 884 */     for (Channel c : channels) {
/* 885 */       ids[(i++)] = c.getId();
/*     */     }
/* 887 */     return ids;
/*     */   }
/*     */ 
/*     */   public Channel()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Channel(Integer id)
/*     */   {
/* 899 */     super(id);
/*     */   }
/*     */ 
/*     */   public Channel(Integer id, CmsSite site, CmsModel model, Integer lft, Integer rgt, Integer priority, Boolean hasContent, Boolean display)
/*     */   {
/* 911 */     super(id, site, model, lft, rgt, priority, hasContent, display);
/*     */   }
/*     */ 
/*     */   public static enum AfterCheckEnum
/*     */   {
/*  35 */     CANNOT_UPDATE, 
/*     */ 
/*  39 */     BACK_UPDATE, 
/*     */ 
/*  43 */     KEEP_UPDATE;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.Channel
 * JD-Core Version:    0.6.0
 */