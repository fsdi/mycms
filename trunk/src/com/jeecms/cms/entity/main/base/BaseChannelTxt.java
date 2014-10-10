/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.ChannelTxt;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseChannelTxt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ChannelTxt";
/*  18 */   public static String PROP_TXT2 = "txt2";
/*  19 */   public static String PROP_TXT = "txt";
/*  20 */   public static String PROP_CHANNEL = "channel";
/*  21 */   public static String PROP_TXT1 = "txt1";
/*  22 */   public static String PROP_ID = "id";
/*  23 */   public static String PROP_TXT3 = "txt3";
/*     */ 
/*  43 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String txt;
/*     */   private String txt1;
/*     */   private String txt2;
/*     */   private String txt3;
/*     */   private Channel channel;
/*     */ 
/*     */   public BaseChannelTxt()
/*     */   {
/*  28 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseChannelTxt(Integer id)
/*     */   {
/*  35 */     setId(id);
/*  36 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  74 */     this.id = id;
/*  75 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTxt()
/*     */   {
/*  85 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(String txt)
/*     */   {
/*  93 */     this.txt = txt;
/*     */   }
/*     */ 
/*     */   public String getTxt1()
/*     */   {
/* 101 */     return this.txt1;
/*     */   }
/*     */ 
/*     */   public void setTxt1(String txt1)
/*     */   {
/* 109 */     this.txt1 = txt1;
/*     */   }
/*     */ 
/*     */   public String getTxt2()
/*     */   {
/* 117 */     return this.txt2;
/*     */   }
/*     */ 
/*     */   public void setTxt2(String txt2)
/*     */   {
/* 125 */     this.txt2 = txt2;
/*     */   }
/*     */ 
/*     */   public String getTxt3()
/*     */   {
/* 133 */     return this.txt3;
/*     */   }
/*     */ 
/*     */   public void setTxt3(String txt3)
/*     */   {
/* 141 */     this.txt3 = txt3;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/* 149 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel)
/*     */   {
/* 157 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 163 */     if (obj == null) return false;
/* 164 */     if (!(obj instanceof ChannelTxt)) return false;
/*     */ 
/* 166 */     ChannelTxt channelTxt = (ChannelTxt)obj;
/* 167 */     if ((getId() == null) || (channelTxt.getId() == null)) return false;
/* 168 */     return getId().equals(channelTxt.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 173 */     if (-2147483648 == this.hashCode) {
/* 174 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 176 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 177 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 180 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 185 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseChannelTxt
 * JD-Core Version:    0.6.0
 */