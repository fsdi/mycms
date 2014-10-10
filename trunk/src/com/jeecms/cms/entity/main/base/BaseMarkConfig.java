/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseMarkConfig
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "MarkConfig";
/*  18 */   public static String PROP_COLOR = "color";
/*  19 */   public static String PROP_ALPHA = "alpha";
/*  20 */   public static String PROP_POS = "pos";
/*  21 */   public static String PROP_SIZE = "size";
/*  22 */   public static String PROP_OFFSET_X = "offsetX";
/*  23 */   public static String PROP_MIN_WIDTH = "minWidth";
/*  24 */   public static String PROP_IMAGE_PATH = "imagePath";
/*  25 */   public static String PROP_CONTENT = "content";
/*  26 */   public static String PROP_MIN_HEIGHT = "minHeight";
/*  27 */   public static String PROP_OFFSET_Y = "offsetY";
/*  28 */   public static String PROP_ON = "on";
/*     */   private Boolean on;
/*     */   private Integer minWidth;
/*     */   private Integer minHeight;
/*     */   private String imagePath;
/*     */   private String content;
/*     */   private Integer size;
/*     */   private String color;
/*     */   private Integer alpha;
/*     */   private Integer pos;
/*     */   private Integer offsetX;
/*     */   private Integer offsetY;
/*     */ 
/*     */   public BaseMarkConfig()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseMarkConfig(Boolean on, Integer minWidth, Integer minHeight, String content, Integer size, String color, Integer alpha, Integer pos, Integer offsetX, Integer offsetY)
/*     */   {
/*  51 */     setOn(on);
/*  52 */     setMinWidth(minWidth);
/*  53 */     setMinHeight(minHeight);
/*  54 */     setContent(content);
/*  55 */     setSize(size);
/*  56 */     setColor(color);
/*  57 */     setAlpha(alpha);
/*  58 */     setPos(pos);
/*  59 */     setOffsetX(offsetX);
/*  60 */     setOffsetY(offsetY);
/*  61 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Boolean getOn()
/*     */   {
/*  90 */     return this.on;
/*     */   }
/*     */ 
/*     */   public void setOn(Boolean on)
/*     */   {
/*  98 */     this.on = on;
/*     */   }
/*     */ 
/*     */   public Integer getMinWidth()
/*     */   {
/* 106 */     return this.minWidth;
/*     */   }
/*     */ 
/*     */   public void setMinWidth(Integer minWidth)
/*     */   {
/* 114 */     this.minWidth = minWidth;
/*     */   }
/*     */ 
/*     */   public Integer getMinHeight()
/*     */   {
/* 122 */     return this.minHeight;
/*     */   }
/*     */ 
/*     */   public void setMinHeight(Integer minHeight)
/*     */   {
/* 130 */     this.minHeight = minHeight;
/*     */   }
/*     */ 
/*     */   public String getImagePath()
/*     */   {
/* 138 */     return this.imagePath;
/*     */   }
/*     */ 
/*     */   public void setImagePath(String imagePath)
/*     */   {
/* 146 */     this.imagePath = imagePath;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 154 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 162 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Integer getSize()
/*     */   {
/* 170 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(Integer size)
/*     */   {
/* 178 */     this.size = size;
/*     */   }
/*     */ 
/*     */   public String getColor()
/*     */   {
/* 186 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(String color)
/*     */   {
/* 194 */     this.color = color;
/*     */   }
/*     */ 
/*     */   public Integer getAlpha()
/*     */   {
/* 202 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   public void setAlpha(Integer alpha)
/*     */   {
/* 210 */     this.alpha = alpha;
/*     */   }
/*     */ 
/*     */   public Integer getPos()
/*     */   {
/* 218 */     return this.pos;
/*     */   }
/*     */ 
/*     */   public void setPos(Integer pos)
/*     */   {
/* 226 */     this.pos = pos;
/*     */   }
/*     */ 
/*     */   public Integer getOffsetX()
/*     */   {
/* 234 */     return this.offsetX;
/*     */   }
/*     */ 
/*     */   public void setOffsetX(Integer offsetX)
/*     */   {
/* 242 */     this.offsetX = offsetX;
/*     */   }
/*     */ 
/*     */   public Integer getOffsetY()
/*     */   {
/* 250 */     return this.offsetY;
/*     */   }
/*     */ 
/*     */   public void setOffsetY(Integer offsetY)
/*     */   {
/* 258 */     this.offsetY = offsetY;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 267 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseMarkConfig
 * JD-Core Version:    0.6.0
 */