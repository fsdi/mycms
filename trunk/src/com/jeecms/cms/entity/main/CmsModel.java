/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseCmsModel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class CmsModel extends BaseCmsModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getTplChannel(String solution, boolean def)
/*     */   {
/*  22 */     StringBuilder t = new StringBuilder();
/*  23 */     t.append(solution).append("/");
/*  24 */     if (getHasContent().booleanValue())
/*  25 */       t.append("channel");
/*     */     else {
/*  27 */       t.append("alone");
/*     */     }
/*  29 */     t.append("/");
/*  30 */     String prefix = getTplChannelPrefix();
/*  31 */     if (def) {
/*  32 */       if (!StringUtils.isBlank(prefix))
/*  33 */         t.append(prefix);
/*     */       else {
/*  35 */         t.append("default");
/*     */       }
/*  37 */       t.append(".html");
/*     */     }
/*  39 */     else if (!StringUtils.isBlank(prefix)) {
/*  40 */       t.append(prefix);
/*     */     }
/*     */ 
/*  43 */     return t.toString();
/*     */   }
/*     */ 
/*     */   public String getTplContent(String solution, boolean def) {
/*  47 */     StringBuilder t = new StringBuilder();
/*  48 */     t.append(solution).append("/");
/*  49 */     t.append("content");
/*  50 */     t.append("/");
/*  51 */     String prefix = getTplContentPrefix();
/*  52 */     if (def) {
/*  53 */       if (!StringUtils.isBlank(prefix))
/*  54 */         t.append(prefix);
/*     */       else {
/*  56 */         t.append("default");
/*     */       }
/*  58 */       t.append(".html");
/*     */     }
/*  60 */     else if (!StringUtils.isBlank(prefix)) {
/*  61 */       t.append(prefix);
/*     */     }
/*     */ 
/*  64 */     return t.toString();
/*     */   }
/*     */ 
/*     */   public List<String> getModelItems()
/*     */   {
/*  69 */     Set items = getItems();
/*  70 */     List fileList = new ArrayList();
/*  71 */     Iterator it = items.iterator();
/*  72 */     while (it.hasNext()) {
/*  73 */       fileList.add(((CmsModelItem)it.next()).getField());
/*     */     }
/*  75 */     return fileList;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  80 */     if (getDisabled() == null) {
/*  81 */       setDisabled(Boolean.valueOf(false));
/*     */     }
/*  83 */     if (getDef() == null)
/*  84 */       setDef(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public CmsModel()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsModel(Integer id)
/*     */   {
/*  97 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsModel(Integer id, String name, String path, Integer titleImgWidth, Integer titleImgHeight, Integer contentImgWidth, Integer contentImgHeight, Integer priority, Boolean hasContent, Boolean disabled, Boolean def)
/*     */   {
/* 112 */     super(id, name, path, titleImgWidth, titleImgHeight, contentImgWidth, 
/* 112 */       contentImgHeight, priority, hasContent, disabled, def);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsModel
 * JD-Core Version:    0.6.0
 */