/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.ChannelExt;
/*     */ import com.jeecms.cms.entity.main.ChannelModel;
/*     */ import com.jeecms.cms.entity.main.ChannelTxt;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseChannel
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "Channel";
/*  18 */   public static String PROP_MODEL = "model";
/*  19 */   public static String PROP_CHANNEL_EXT = "channelExt";
/*  20 */   public static String PROP_RGT = "rgt";
/*  21 */   public static String PROP_SITE = "site";
/*  22 */   public static String PROP_LFT = "lft";
/*  23 */   public static String PROP_PARENT = "parent";
/*  24 */   public static String PROP_PATH = "path";
/*  25 */   public static String PROP_DISPLAY = "display";
/*  26 */   public static String PROP_PRIORITY = "priority";
/*  27 */   public static String PROP_HAS_CONTENT = "hasContent";
/*  28 */   public static String PROP_ID = "id";
/*     */ 
/*  72 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String path;
/*     */   private Integer lft;
/*     */   private Integer rgt;
/*     */   private Integer priority;
/*     */   private Boolean hasContent;
/*     */   private Boolean display;
/*     */   private ChannelExt channelExt;
/*     */   private CmsSite site;
/*     */   private CmsModel model;
/*     */   private Channel parent;
/*     */   private Set<Channel> child;
/*     */   private Set<CmsGroup> viewGroups;
/*     */   private Set<CmsGroup> contriGroups;
/*     */   private Set<CmsUser> users;
/*     */   private Set<ChannelTxt> channelTxtSet;
/*     */   private List<ChannelModel> channelModels;
/*     */   private Map<String, String> attr;
/*     */ 
/*     */   public BaseChannel()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseChannel(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseChannel(Integer id, CmsSite site, CmsModel model, Integer lft, Integer rgt, Integer priority, Boolean hasContent, Boolean display)
/*     */   {
/*  57 */     setId(id);
/*  58 */     setSite(site);
/*  59 */     setModel(model);
/*  60 */     setLft(lft);
/*  61 */     setRgt(rgt);
/*  62 */     setPriority(priority);
/*  63 */     setHasContent(hasContent);
/*  64 */     setDisplay(display);
/*  65 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 111 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 119 */     this.id = id;
/* 120 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 130 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path)
/*     */   {
/* 138 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public Integer getLft()
/*     */   {
/* 146 */     return this.lft;
/*     */   }
/*     */ 
/*     */   public void setLft(Integer lft)
/*     */   {
/* 154 */     this.lft = lft;
/*     */   }
/*     */ 
/*     */   public Integer getRgt()
/*     */   {
/* 162 */     return this.rgt;
/*     */   }
/*     */ 
/*     */   public void setRgt(Integer rgt)
/*     */   {
/* 170 */     this.rgt = rgt;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 178 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 186 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Boolean getHasContent()
/*     */   {
/* 194 */     return this.hasContent;
/*     */   }
/*     */ 
/*     */   public void setHasContent(Boolean hasContent)
/*     */   {
/* 202 */     this.hasContent = hasContent;
/*     */   }
/*     */ 
/*     */   public Boolean getDisplay()
/*     */   {
/* 210 */     return this.display;
/*     */   }
/*     */ 
/*     */   public void setDisplay(Boolean display)
/*     */   {
/* 218 */     this.display = display;
/*     */   }
/*     */ 
/*     */   public ChannelExt getChannelExt()
/*     */   {
/* 226 */     return this.channelExt;
/*     */   }
/*     */ 
/*     */   public void setChannelExt(ChannelExt channelExt)
/*     */   {
/* 234 */     this.channelExt = channelExt;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 242 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 250 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public CmsModel getModel()
/*     */   {
/* 258 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(CmsModel model)
/*     */   {
/* 266 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public Channel getParent()
/*     */   {
/* 274 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Channel parent)
/*     */   {
/* 282 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getChild()
/*     */   {
/* 290 */     return this.child;
/*     */   }
/*     */ 
/*     */   public void setChild(Set<Channel> child)
/*     */   {
/* 298 */     this.child = child;
/*     */   }
/*     */ 
/*     */   public Set<CmsGroup> getViewGroups()
/*     */   {
/* 306 */     return this.viewGroups;
/*     */   }
/*     */ 
/*     */   public void setViewGroups(Set<CmsGroup> viewGroups)
/*     */   {
/* 314 */     this.viewGroups = viewGroups;
/*     */   }
/*     */ 
/*     */   public Set<CmsGroup> getContriGroups()
/*     */   {
/* 322 */     return this.contriGroups;
/*     */   }
/*     */ 
/*     */   public void setContriGroups(Set<CmsGroup> contriGroups)
/*     */   {
/* 330 */     this.contriGroups = contriGroups;
/*     */   }
/*     */ 
/*     */   public Set<CmsUser> getUsers()
/*     */   {
/* 338 */     return this.users;
/*     */   }
/*     */ 
/*     */   public void setUsers(Set<CmsUser> users)
/*     */   {
/* 346 */     this.users = users;
/*     */   }
/*     */ 
/*     */   public Set<ChannelTxt> getChannelTxtSet()
/*     */   {
/* 354 */     return this.channelTxtSet;
/*     */   }
/*     */ 
/*     */   public void setChannelTxtSet(Set<ChannelTxt> channelTxtSet)
/*     */   {
/* 362 */     this.channelTxtSet = channelTxtSet;
/*     */   }
/*     */ 
/*     */   public List<ChannelModel> getChannelModels() {
/* 366 */     return this.channelModels;
/*     */   }
/*     */ 
/*     */   public void setChannelModels(List<ChannelModel> channelModels)
/*     */   {
/* 371 */     this.channelModels = channelModels;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 378 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 386 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 392 */     if (obj == null) return false;
/* 393 */     if (!(obj instanceof Channel)) return false;
/*     */ 
/* 395 */     Channel channel = (Channel)obj;
/* 396 */     if ((getId() == null) || (channel.getId() == null)) return false;
/* 397 */     return getId().equals(channel.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 402 */     if (-2147483648 == this.hashCode) {
/* 403 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 405 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 406 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 409 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 414 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseChannel
 * JD-Core Version:    0.6.0
 */