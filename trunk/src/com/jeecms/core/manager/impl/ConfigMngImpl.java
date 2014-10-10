/*     */ package com.jeecms.core.manager.impl;
/*     */ 
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.core.dao.ConfigDao;
/*     */ import com.jeecms.core.entity.Config;
/*     */ import com.jeecms.core.entity.Config.ConfigEmailSender;
/*     */ import com.jeecms.core.entity.Config.ConfigLogin;
/*     */ import com.jeecms.core.entity.Config.ConfigMessageTemplate;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ConfigMngImpl
/*     */   implements ConfigMng
/*     */ {
/*     */   private ConfigDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Map<String, String> getMap()
/*     */   {
/*  26 */     List<Config> list = this.dao.getList();
/*  27 */     Map map = new HashMap(list.size());
/*  28 */     for (Config config : list) {
/*  29 */       map.put(config.getId(), config.getValue());
/*     */     }
/*  31 */     return map;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public String getValue(String id) {
/*  36 */     Config entity = this.dao.findById(id);
/*  37 */     if (entity != null) {
/*  38 */       return entity.getValue();
/*     */     }
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Config.ConfigLogin getConfigLogin() {
/*  46 */     return Config.ConfigLogin.create(getMap());
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public EmailSender getEmailSender() {
/*  51 */     return Config.ConfigEmailSender.create(getMap());
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public MessageTemplate getForgotPasswordMessageTemplate() {
/*  56 */     return Config.ConfigMessageTemplate.createForgotPasswordMessageTemplate(getMap());
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public MessageTemplate getRegisterMessageTemplate() {
/*  61 */     return Config.ConfigMessageTemplate.createRegisterMessageTemplate(getMap());
/*     */   }
/*     */ 
/*     */   public void updateOrSave(Map<String, String> map) {
/*  65 */     if ((map != null) && (map.size() > 0))
/*  66 */       for (Map.Entry entry : map.entrySet())
/*  67 */         updateOrSave((String)entry.getKey(), (String)entry.getValue());
/*     */   }
/*     */ 
/*     */   public Config updateOrSave(String key, String value)
/*     */   {
/*  73 */     Config config = this.dao.findById(key);
/*  74 */     if (config != null) {
/*  75 */       config.setValue(value);
/*     */     } else {
/*  77 */       config = new Config(key);
/*  78 */       config.setValue(value);
/*  79 */       this.dao.save(config);
/*     */     }
/*  81 */     return config;
/*     */   }
/*     */ 
/*     */   public Config deleteById(String id) {
/*  85 */     Config bean = this.dao.deleteById(id);
/*  86 */     return bean;
/*     */   }
/*     */ 
/*     */   public Config[] deleteByIds(String[] ids) {
/*  90 */     Config[] beans = new Config[ids.length];
/*  91 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  92 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  94 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ConfigDao dao)
/*     */   {
/* 101 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.impl.ConfigMngImpl
 * JD-Core Version:    0.6.0
 */