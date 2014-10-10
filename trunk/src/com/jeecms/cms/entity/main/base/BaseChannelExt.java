/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.ChannelExt;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseChannelExt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ChannelExt";
/*  18 */   public static String PROP_ACCESS_BY_DIR = "accessByDir";
/*  19 */   public static String PROP_KEYWORDS = "keywords";
/*  20 */   public static String PROP_CHANNEL_RULE = "channelRule";
/*  21 */   public static String PROP_STATIC_CHANNEL = "staticChannel";
/*  22 */   public static String PROP_DESCRIPTION = "description";
/*  23 */   public static String PROP_CONTENT_RULE = "contentRule";
/*  24 */   public static String PROP_STATIC_CONTENT = "staticContent";
/*  25 */   public static String PROP_CHANNEL = "channel";
/*  26 */   public static String PROP_COMMENT_CONTROL = "commentControl";
/*  27 */   public static String PROP_PAGE_SIZE = "pageSize";
/*  28 */   public static String PROP_NAME = "name";
/*  29 */   public static String PROP_LINK = "link";
/*  30 */   public static String PROP_TITLE_IMG_HEIGHT = "titleImgHeight";
/*  31 */   public static String PROP_TPL_CHANNEL = "tplChannel";
/*  32 */   public static String PROP_ALLOW_UPDOWN = "allowUpdown";
/*  33 */   public static String PROP_LIST_CHILD = "listChild";
/*  34 */   public static String PROP_TITLE_IMG_WIDTH = "titleImgWidth";
/*  35 */   public static String PROP_TPL_CONTENT = "tplContent";
/*  36 */   public static String PROP_HAS_TITLE_IMG = "hasTitleImg";
/*  37 */   public static String PROP_CONTENT_IMG_WIDTH = "contentImgWidth";
/*  38 */   public static String PROP_FINAL_STEP = "finalStep";
/*  39 */   public static String PROP_HAS_CONTENT_IMG = "hasContentImg";
/*  40 */   public static String PROP_BLANK = "blank";
/*  41 */   public static String PROP_TITLE_IMG = "titleImg";
/*  42 */   public static String PROP_AFTER_CHECK = "afterCheck";
/*  43 */   public static String PROP_TITLE = "title";
/*  44 */   public static String PROP_CONTENT_IMG_HEIGHT = "contentImgHeight";
/*  45 */   public static String PROP_ID = "id";
/*  46 */   public static String PROP_CONTENT_IMG = "contentImg";
/*     */ 
/* 104 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Byte finalStep;
/*     */   private Byte afterCheck;
/*     */   private Boolean staticChannel;
/*     */   private Boolean staticContent;
/*     */   private Boolean accessByDir;
/*     */   private Boolean listChild;
/*     */   private Integer pageSize;
/*     */   private String channelRule;
/*     */   private String contentRule;
/*     */   private String link;
/*     */   private String tplChannel;
/*     */   private String tplContent;
/*     */   private String titleImg;
/*     */   private String contentImg;
/*     */   private Boolean hasTitleImg;
/*     */   private Boolean hasContentImg;
/*     */   private Integer titleImgWidth;
/*     */   private Integer titleImgHeight;
/*     */   private Integer contentImgWidth;
/*     */   private Integer contentImgHeight;
/*     */   private Integer commentControl;
/*     */   private Boolean allowUpdown;
/*     */   private Boolean blank;
/*     */   private String title;
/*     */   private String keywords;
/*     */   private String description;
/*     */   private Channel channel;
/*     */ 
/*     */   public BaseChannelExt()
/*     */   {
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseChannelExt(Integer id)
/*     */   {
/*  58 */     setId(id);
/*  59 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseChannelExt(Integer id, String name, Boolean staticChannel, Boolean staticContent, Boolean accessByDir, Boolean listChild, Integer pageSize, Boolean hasTitleImg, Boolean hasContentImg, Integer titleImgWidth, Integer titleImgHeight, Integer contentImgWidth, Integer contentImgHeight, Integer commentControl, Boolean blank)
/*     */   {
/*  82 */     setId(id);
/*  83 */     setName(name);
/*  84 */     setStaticChannel(staticChannel);
/*  85 */     setStaticContent(staticContent);
/*  86 */     setAccessByDir(accessByDir);
/*  87 */     setListChild(listChild);
/*  88 */     setPageSize(pageSize);
/*  89 */     setHasTitleImg(hasTitleImg);
/*  90 */     setHasContentImg(hasContentImg);
/*  91 */     setTitleImgWidth(titleImgWidth);
/*  92 */     setTitleImgHeight(titleImgHeight);
/*  93 */     setContentImgWidth(contentImgWidth);
/*  94 */     setContentImgHeight(contentImgHeight);
/*  95 */     setCommentControl(commentControl);
/*  96 */     setBlank(blank);
/*  97 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 150 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 158 */     this.id = id;
/* 159 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 169 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 177 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Byte getFinalStep()
/*     */   {
/* 185 */     return this.finalStep;
/*     */   }
/*     */ 
/*     */   public void setFinalStep(Byte finalStep)
/*     */   {
/* 193 */     this.finalStep = finalStep;
/*     */   }
/*     */ 
/*     */   public Byte getAfterCheck()
/*     */   {
/* 201 */     return this.afterCheck;
/*     */   }
/*     */ 
/*     */   public void setAfterCheck(Byte afterCheck)
/*     */   {
/* 209 */     this.afterCheck = afterCheck;
/*     */   }
/*     */ 
/*     */   public Boolean getStaticChannel()
/*     */   {
/* 217 */     return this.staticChannel;
/*     */   }
/*     */ 
/*     */   public void setStaticChannel(Boolean staticChannel)
/*     */   {
/* 225 */     this.staticChannel = staticChannel;
/*     */   }
/*     */ 
/*     */   public Boolean getStaticContent()
/*     */   {
/* 233 */     return this.staticContent;
/*     */   }
/*     */ 
/*     */   public void setStaticContent(Boolean staticContent)
/*     */   {
/* 241 */     this.staticContent = staticContent;
/*     */   }
/*     */ 
/*     */   public Boolean getAccessByDir()
/*     */   {
/* 249 */     return this.accessByDir;
/*     */   }
/*     */ 
/*     */   public void setAccessByDir(Boolean accessByDir)
/*     */   {
/* 257 */     this.accessByDir = accessByDir;
/*     */   }
/*     */ 
/*     */   public Boolean getListChild()
/*     */   {
/* 265 */     return this.listChild;
/*     */   }
/*     */ 
/*     */   public void setListChild(Boolean listChild)
/*     */   {
/* 273 */     this.listChild = listChild;
/*     */   }
/*     */ 
/*     */   public Integer getPageSize()
/*     */   {
/* 281 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(Integer pageSize)
/*     */   {
/* 289 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public String getChannelRule()
/*     */   {
/* 297 */     return this.channelRule;
/*     */   }
/*     */ 
/*     */   public void setChannelRule(String channelRule)
/*     */   {
/* 305 */     this.channelRule = channelRule;
/*     */   }
/*     */ 
/*     */   public String getContentRule()
/*     */   {
/* 313 */     return this.contentRule;
/*     */   }
/*     */ 
/*     */   public void setContentRule(String contentRule)
/*     */   {
/* 321 */     this.contentRule = contentRule;
/*     */   }
/*     */ 
/*     */   public String getLink()
/*     */   {
/* 329 */     return this.link;
/*     */   }
/*     */ 
/*     */   public void setLink(String link)
/*     */   {
/* 337 */     this.link = link;
/*     */   }
/*     */ 
/*     */   public String getTplChannel()
/*     */   {
/* 345 */     return this.tplChannel;
/*     */   }
/*     */ 
/*     */   public void setTplChannel(String tplChannel)
/*     */   {
/* 353 */     this.tplChannel = tplChannel;
/*     */   }
/*     */ 
/*     */   public String getTplContent()
/*     */   {
/* 361 */     return this.tplContent;
/*     */   }
/*     */ 
/*     */   public void setTplContent(String tplContent)
/*     */   {
/* 369 */     this.tplContent = tplContent;
/*     */   }
/*     */ 
/*     */   public String getTitleImg()
/*     */   {
/* 377 */     return this.titleImg;
/*     */   }
/*     */ 
/*     */   public void setTitleImg(String titleImg)
/*     */   {
/* 385 */     this.titleImg = titleImg;
/*     */   }
/*     */ 
/*     */   public String getContentImg()
/*     */   {
/* 393 */     return this.contentImg;
/*     */   }
/*     */ 
/*     */   public void setContentImg(String contentImg)
/*     */   {
/* 401 */     this.contentImg = contentImg;
/*     */   }
/*     */ 
/*     */   public Boolean getHasTitleImg()
/*     */   {
/* 409 */     return this.hasTitleImg;
/*     */   }
/*     */ 
/*     */   public void setHasTitleImg(Boolean hasTitleImg)
/*     */   {
/* 417 */     this.hasTitleImg = hasTitleImg;
/*     */   }
/*     */ 
/*     */   public Boolean getHasContentImg()
/*     */   {
/* 425 */     return this.hasContentImg;
/*     */   }
/*     */ 
/*     */   public void setHasContentImg(Boolean hasContentImg)
/*     */   {
/* 433 */     this.hasContentImg = hasContentImg;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgWidth()
/*     */   {
/* 441 */     return this.titleImgWidth;
/*     */   }
/*     */ 
/*     */   public void setTitleImgWidth(Integer titleImgWidth)
/*     */   {
/* 449 */     this.titleImgWidth = titleImgWidth;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgHeight()
/*     */   {
/* 457 */     return this.titleImgHeight;
/*     */   }
/*     */ 
/*     */   public void setTitleImgHeight(Integer titleImgHeight)
/*     */   {
/* 465 */     this.titleImgHeight = titleImgHeight;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgWidth()
/*     */   {
/* 473 */     return this.contentImgWidth;
/*     */   }
/*     */ 
/*     */   public void setContentImgWidth(Integer contentImgWidth)
/*     */   {
/* 481 */     this.contentImgWidth = contentImgWidth;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgHeight()
/*     */   {
/* 489 */     return this.contentImgHeight;
/*     */   }
/*     */ 
/*     */   public void setContentImgHeight(Integer contentImgHeight)
/*     */   {
/* 497 */     this.contentImgHeight = contentImgHeight;
/*     */   }
/*     */ 
/*     */   public Integer getCommentControl()
/*     */   {
/* 505 */     return this.commentControl;
/*     */   }
/*     */ 
/*     */   public void setCommentControl(Integer commentControl)
/*     */   {
/* 513 */     this.commentControl = commentControl;
/*     */   }
/*     */ 
/*     */   public Boolean getAllowUpdown()
/*     */   {
/* 521 */     return this.allowUpdown;
/*     */   }
/*     */ 
/*     */   public void setAllowUpdown(Boolean allowUpdown)
/*     */   {
/* 529 */     this.allowUpdown = allowUpdown;
/*     */   }
/*     */ 
/*     */   public Boolean getBlank()
/*     */   {
/* 537 */     return this.blank;
/*     */   }
/*     */ 
/*     */   public void setBlank(Boolean blank)
/*     */   {
/* 545 */     this.blank = blank;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 553 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 561 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 569 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords)
/*     */   {
/* 577 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 585 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 593 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/* 601 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel)
/*     */   {
/* 609 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 615 */     if (obj == null) return false;
/* 616 */     if (!(obj instanceof ChannelExt)) return false;
/*     */ 
/* 618 */     ChannelExt channelExt = (ChannelExt)obj;
/* 619 */     if ((getId() == null) || (channelExt.getId() == null)) return false;
/* 620 */     return getId().equals(channelExt.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 625 */     if (-2147483648 == this.hashCode) {
/* 626 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 628 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 629 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 632 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 637 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseChannelExt
 * JD-Core Version:    0.6.0
 */