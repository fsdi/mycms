/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSiteModel;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsSiteModel
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsSiteModel";
/*  18 */   public static String PROP_SINGLE = "single";
/*  19 */   public static String PROP_COLS = "cols";
/*  20 */   public static String PROP_DATA_TYPE = "dataType";
/*  21 */   public static String PROP_HELP_POSITION = "helpPosition";
/*  22 */   public static String PROP_ROWS = "rows";
/*  23 */   public static String PROP_HELP = "help";
/*  24 */   public static String PROP_SIZE = "size";
/*  25 */   public static String PROP_UPLOAD_PATH = "uploadPath";
/*  26 */   public static String PROP_PRIORITY = "priority";
/*  27 */   public static String PROP_LABEL = "label";
/*  28 */   public static String PROP_FIELD = "field";
/*  29 */   public static String PROP_ID = "id";
/*     */ 
/*  65 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String field;
/*     */   private String label;
/*     */   private Integer priority;
/*     */   private String uploadPath;
/*     */   private String size;
/*     */   private String rows;
/*     */   private String cols;
/*     */   private String help;
/*     */   private String helpPosition;
/*     */   private Integer dataType;
/*     */   private Boolean single;
/*     */ 
/*     */   public BaseCmsSiteModel()
/*     */   {
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteModel(Integer id)
/*     */   {
/*  41 */     setId(id);
/*  42 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteModel(Integer id, String field, String label, Integer priority)
/*     */   {
/*  54 */     setId(id);
/*  55 */     setField(field);
/*  56 */     setLabel(label);
/*  57 */     setPriority(priority);
/*  58 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 100 */     this.id = id;
/* 101 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getField()
/*     */   {
/* 111 */     return this.field;
/*     */   }
/*     */ 
/*     */   public void setField(String field)
/*     */   {
/* 119 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 127 */     return this.label;
/*     */   }
/*     */ 
/*     */   public void setLabel(String label)
/*     */   {
/* 135 */     this.label = label;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 143 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 151 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public String getUploadPath()
/*     */   {
/* 159 */     return this.uploadPath;
/*     */   }
/*     */ 
/*     */   public void setUploadPath(String uploadPath)
/*     */   {
/* 167 */     this.uploadPath = uploadPath;
/*     */   }
/*     */ 
/*     */   public String getSize()
/*     */   {
/* 175 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(String size)
/*     */   {
/* 183 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public String getRows()
/*     */   {
/* 191 */     return this.rows;
/*     */   }
/*     */ 
/*     */   public void setRows(String rows)
/*     */   {
/* 199 */     this.rows = rows;
/*     */   }
/*     */ 
/*     */   public String getCols()
/*     */   {
/* 207 */     return this.cols;
/*     */   }
/*     */ 
/*     */   public void setCols(String cols)
/*     */   {
/* 215 */     this.cols = cols;
/*     */   }
/*     */ 
/*     */   public String getHelp()
/*     */   {
/* 223 */     return this.help;
/*     */   }
/*     */ 
/*     */   public void setHelp(String help)
/*     */   {
/* 231 */     this.help = help;
/*     */   }
/*     */ 
/*     */   public String getHelpPosition()
/*     */   {
/* 239 */     return this.helpPosition;
/*     */   }
/*     */ 
/*     */   public void setHelpPosition(String helpPosition)
/*     */   {
/* 247 */     this.helpPosition = helpPosition;
/*     */   }
/*     */ 
/*     */   public Integer getDataType()
/*     */   {
/* 255 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(Integer dataType)
/*     */   {
/* 263 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   public Boolean getSingle()
/*     */   {
/* 271 */     return this.single;
/*     */   }
/*     */ 
/*     */   public void setSingle(Boolean single)
/*     */   {
/* 279 */     this.single = single;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 285 */     if (obj == null) return false;
/* 286 */     if (!(obj instanceof CmsSiteModel)) return false;
/*     */ 
/* 288 */     CmsSiteModel cmsSiteModel = (CmsSiteModel)obj;
/* 289 */     if ((getId() == null) || (cmsSiteModel.getId() == null)) return false;
/* 290 */     return getId().equals(cmsSiteModel.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 295 */     if (-2147483648 == this.hashCode) {
/* 296 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 298 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 299 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 302 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 307 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsSiteModel
 * JD-Core Version:    0.6.0
 */