/*    */ package com.jeecms.cms.entity.main.base;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsModel;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class BaseChannelModel
/*    */   implements Serializable
/*    */ {
/* 19 */   public static String REF = "ChannelModel";
/* 20 */   public static String PROP_TPLCONTENT = "tplContent";
/*    */   private String tplContent;
/*    */   private CmsModel model;
/*    */ 
/*    */   public BaseChannelModel()
/*    */   {
/* 25 */     initialize();
/*    */   }
/*    */ 
/*    */   protected void initialize()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BaseChannelModel(String tplContent, CmsModel model)
/*    */   {
/* 36 */     this.tplContent = tplContent;
/* 37 */     this.model = model;
/*    */   }
/*    */ 
/*    */   public String getTplContent()
/*    */   {
/* 48 */     return this.tplContent;
/*    */   }
/*    */ 
/*    */   public void setTplContent(String tplContent) {
/* 52 */     this.tplContent = tplContent;
/*    */   }
/*    */ 
/*    */   public CmsModel getModel() {
/* 56 */     return this.model;
/*    */   }
/*    */ 
/*    */   public void setModel(CmsModel model) {
/* 60 */     this.model = model;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 64 */     return super.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseChannelModel
 * JD-Core Version:    0.6.0
 */