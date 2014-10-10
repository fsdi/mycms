/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsModelItem;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsModelItem
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsModelItem";
/*  18 */   public static String PROP_SINGLE = "single";
/*  19 */   public static String PROP_DATA_TYPE = "dataType";
/*  20 */   public static String PROP_CUSTOM = "custom";
/*  21 */   public static String PROP_OPT_VALUE = "optValue";
/*  22 */   public static String PROP_HELP = "help";
/*  23 */   public static String PROP_PRIORITY = "priority";
/*  24 */   public static String PROP_FIELD = "field";
/*  25 */   public static String PROP_LABEL = "label";
/*  26 */   public static String PROP_COLS = "cols";
/*  27 */   public static String PROP_MODEL = "model";
/*  28 */   public static String PROP_DEF_VALUE = "defValue";
/*  29 */   public static String PROP_HELP_POSITION = "helpPosition";
/*  30 */   public static String PROP_ROWS = "rows";
/*  31 */   public static String PROP_SIZE = "size";
/*  32 */   public static String PROP_DISPLAY = "display";
/*  33 */   public static String PROP_CHANNEL = "channel";
/*  34 */   public static String PROP_ID = "id";
/*     */ 
/*  80 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String field;
/*     */   private String label;
/*     */   private Integer priority;
/*     */   private String defValue;
/*     */   private String optValue;
/*     */   private String size;
/*     */   private String rows;
/*     */   private String cols;
/*     */   private String help;
/*     */   private String helpPosition;
/*     */   private Integer dataType;
/*     */   private Boolean single;
/*     */   private Boolean channel;
/*     */   private Boolean custom;
/*     */   private Boolean display;
/*     */   private CmsModel model;
/*     */ 
/*     */   public BaseCmsModelItem()
/*     */   {
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsModelItem(Integer id)
/*     */   {
/*  46 */     setId(id);
/*  47 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsModelItem(Integer id, CmsModel model, String field, String label, Integer dataType, Boolean single, Boolean channel, Boolean custom, Boolean display)
/*     */   {
/*  64 */     setId(id);
/*  65 */     setModel(model);
/*  66 */     setField(field);
/*  67 */     setLabel(label);
/*  68 */     setDataType(dataType);
/*  69 */     setSingle(single);
/*  70 */     setChannel(channel);
/*  71 */     setCustom(custom);
/*  72 */     setDisplay(display);
/*  73 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 114 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 122 */     this.id = id;
/* 123 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getField()
/*     */   {
/* 133 */     return this.field;
/*     */   }
/*     */ 
/*     */   public void setField(String field)
/*     */   {
/* 141 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 149 */     return this.label;
/*     */   }
/*     */ 
/*     */   public void setLabel(String label)
/*     */   {
/* 157 */     this.label = label;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 165 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 173 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public String getDefValue()
/*     */   {
/* 181 */     return this.defValue;
/*     */   }
/*     */ 
/*     */   public void setDefValue(String defValue)
/*     */   {
/* 189 */     this.defValue = defValue;
/*     */   }
/*     */ 
/*     */   public String getOptValue()
/*     */   {
/* 197 */     return this.optValue;
/*     */   }
/*     */ 
/*     */   public void setOptValue(String optValue)
/*     */   {
/* 205 */     this.optValue = optValue;
/*     */   }
/*     */ 
/*     */   public String getSize()
/*     */   {
/* 213 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(String size)
/*     */   {
/* 221 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public String getRows()
/*     */   {
/* 229 */     return this.rows;
/*     */   }
/*     */ 
/*     */   public void setRows(String rows)
/*     */   {
/* 237 */     this.rows = rows;
/*     */   }
/*     */ 
/*     */   public String getCols()
/*     */   {
/* 245 */     return this.cols;
/*     */   }
/*     */ 
/*     */   public void setCols(String cols)
/*     */   {
/* 253 */     this.cols = cols;
/*     */   }
/*     */ 
/*     */   public String getHelp()
/*     */   {
/* 261 */     return this.help;
/*     */   }
/*     */ 
/*     */   public void setHelp(String help)
/*     */   {
/* 269 */     this.help = help;
/*     */   }
/*     */ 
/*     */   public String getHelpPosition()
/*     */   {
/* 277 */     return this.helpPosition;
/*     */   }
/*     */ 
/*     */   public void setHelpPosition(String helpPosition)
/*     */   {
/* 285 */     this.helpPosition = helpPosition;
/*     */   }
/*     */ 
/*     */   public Integer getDataType()
/*     */   {
/* 293 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(Integer dataType)
/*     */   {
/* 301 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   public Boolean getSingle()
/*     */   {
/* 309 */     return this.single;
/*     */   }
/*     */ 
/*     */   public void setSingle(Boolean single)
/*     */   {
/* 317 */     this.single = single;
/*     */   }
/*     */ 
/*     */   public Boolean getChannel()
/*     */   {
/* 325 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Boolean channel)
/*     */   {
/* 333 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public Boolean getCustom()
/*     */   {
/* 341 */     return this.custom;
/*     */   }
/*     */ 
/*     */   public void setCustom(Boolean custom)
/*     */   {
/* 349 */     this.custom = custom;
/*     */   }
/*     */ 
/*     */   public Boolean getDisplay()
/*     */   {
/* 357 */     return this.display;
/*     */   }
/*     */ 
/*     */   public void setDisplay(Boolean display)
/*     */   {
/* 365 */     this.display = display;
/*     */   }
/*     */ 
/*     */   public CmsModel getModel()
/*     */   {
/* 373 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(CmsModel model)
/*     */   {
/* 381 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 387 */     if (obj == null) return false;
/* 388 */     if (!(obj instanceof CmsModelItem)) return false;
/*     */ 
/* 390 */     CmsModelItem cmsModelItem = (CmsModelItem)obj;
/* 391 */     if ((getId() == null) || (cmsModelItem.getId() == null)) return false;
/* 392 */     return getId().equals(cmsModelItem.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 397 */     if (-2147483648 == this.hashCode) {
/* 398 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 400 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 401 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 404 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 409 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsModelItem
 * JD-Core Version:    0.6.0
 */