/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsKeywordDao;
/*     */ import com.jeecms.cms.entity.assist.CmsKeyword;
/*     */ import com.jeecms.cms.manager.assist.CmsKeywordMng;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.htmlparser.Node;
/*     */ import org.htmlparser.lexer.Lexer;
/*     */ import org.htmlparser.nodes.TextNode;
/*     */ import org.htmlparser.util.ParserException;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsKeywordMngImpl
/*     */   implements CmsKeywordMng
/*     */ {
/*     */   private CmsKeywordDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsKeyword> getListBySiteId(Integer siteId, boolean onlyEnabled, boolean cacheable)
/*     */   {
/*  25 */     List list = this.dao.getListGlobal(onlyEnabled, cacheable);
/*     */ 
/*  28 */     return list;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public String attachKeyword(Integer siteId, String txt) {
/*  33 */     if (StringUtils.isBlank(txt)) {
/*  34 */       return txt;
/*     */     }
/*  36 */     List<CmsKeyword> list = getListBySiteId(siteId, true, true);
/*  37 */     int len = list.size();
/*  38 */     if (len <= 0) {
/*  39 */       return txt;
/*     */     }
/*  41 */     String[] searchArr = new String[len];
/*  42 */     String[] replacementArr = new String[len];
/*  43 */     int i = 0;
/*  44 */     for (CmsKeyword k : list) {
/*  45 */       searchArr[i] = k.getName();
/*  46 */       replacementArr[i] = k.getUrl();
/*  47 */       i++;
/*     */     }
/*     */     try {
/*  50 */       Lexer lexer = new Lexer(txt);
/*     */ 
/*  52 */       StringBuilder sb = new StringBuilder((int)(txt.length() * 1.2D));
/*     */       Node node;
/*  53 */       while ((node = lexer.nextNode()) != null)
/*     */       {
/*  54 */         if ((node instanceof TextNode))
/*  55 */           sb.append(
/*  56 */             StringUtils.replaceEach(node.toHtml(), searchArr, 
/*  56 */             replacementArr));
/*     */         else {
/*  58 */           sb.append(node.toHtml());
/*     */         }
/*     */       }
/*  61 */       return sb.toString(); } catch (ParserException e) {
/*     */     }
/*  63 */     throw new RuntimeException();
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public CmsKeyword findById(Integer id) {
/*  69 */     CmsKeyword entity = this.dao.findById(id);
/*  70 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsKeyword save(CmsKeyword bean) {
/*  74 */     bean.init();
/*  75 */     this.dao.save(bean);
/*  76 */     return bean;
/*     */   }
/*     */ 
/*     */   public void updateKeywords(Integer[] ids, String[] names, String[] urls, Boolean[] disableds)
/*     */   {
/*  82 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  83 */       CmsKeyword keyword = findById(ids[i]);
/*  84 */       keyword.setName(names[i]);
/*  85 */       keyword.setUrl(urls[i]);
/*  86 */       keyword.setDisabled(disableds[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public CmsKeyword deleteById(Integer id) {
/*  91 */     CmsKeyword bean = this.dao.deleteById(id);
/*  92 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsKeyword[] deleteByIds(Integer[] ids) {
/*  96 */     CmsKeyword[] beans = new CmsKeyword[ids.length];
/*  97 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  98 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 100 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsKeywordDao dao)
/*     */   {
/* 107 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsKeywordMngImpl
 * JD-Core Version:    0.6.0
 */