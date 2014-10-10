/*     */ package com.jeecms.cms.lucene;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.IndexWriter.MaxFieldLength;
/*     */ import org.apache.lucene.queryParser.ParseException;
/*     */ import org.apache.lucene.search.IndexSearcher;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.Searcher;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.SimpleFSDirectory;
/*     */ import org.apache.lucene.util.Version;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class LuceneContentSvcImpl
/*     */   implements LuceneContentSvc
/*     */ {
/*     */   private RealPathResolver realPathResolver;
/*     */   private ContentMng contentMng;
/*     */   private LuceneContentDao luceneContentDao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer createIndex(Integer siteId, Integer channelId, Date startDate, Date endDate, Integer startId, Integer max)
/*     */     throws IOException, ParseException
/*     */   {
/*  39 */     String path = this.realPathResolver.get("/WEB-INF/lucene");
/*  40 */     Directory dir = new SimpleFSDirectory(new File(path));
/*  41 */     return createIndex(siteId, channelId, startDate, endDate, startId, max, 
/*  42 */       dir);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Integer createIndex(Integer siteId, Integer channelId, Date startDate, Date endDate, Integer startId, Integer max, Directory dir) throws IOException, ParseException
/*     */   {
/*  49 */     boolean exist = IndexReader.indexExists(dir);
/*  50 */     IndexWriter writer = new IndexWriter(dir, 
/*  51 */       new StandardAnalyzer(Version.LUCENE_30), !exist, IndexWriter.MaxFieldLength.LIMITED);
/*     */     try {
/*  53 */       if (exist) {
/*  54 */         LuceneContent.delete(siteId, channelId, startDate, endDate, 
/*  55 */           writer);
/*     */       }
/*  57 */       Integer lastId = this.luceneContentDao.index(writer, siteId, channelId, 
/*  58 */         startDate, endDate, startId, max);
/*  59 */       writer.optimize();
/*  60 */       Integer localInteger1 = lastId;
/*     */       return localInteger1;
/*     */     } finally {
/*  62 */       writer.close();
/*  63 */     }
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public void createIndex(Content content) throws IOException {
/*  68 */     String path = this.realPathResolver.get("/WEB-INF/lucene");
/*  69 */     Directory dir = new SimpleFSDirectory(new File(path));
/*  70 */     createIndex(content, dir);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public void createIndex(Content content, Directory dir) throws IOException {
/*  75 */     boolean exist = IndexReader.indexExists(dir);
/*  76 */     IndexWriter writer = new IndexWriter(dir, 
/*  77 */       new StandardAnalyzer(Version.LUCENE_30), !exist, IndexWriter.MaxFieldLength.LIMITED);
/*     */     try {
/*  79 */       writer.addDocument(LuceneContent.createDocument(content));
/*     */     } finally {
/*  81 */       writer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void deleteIndex(Integer contentId) throws IOException, ParseException {
/*  88 */     String path = this.realPathResolver.get("/WEB-INF/lucene");
/*  89 */     Directory dir = new SimpleFSDirectory(new File(path));
/*  90 */     deleteIndex(contentId, dir);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void deleteIndex(Integer contentId, Directory dir) throws IOException, ParseException {
/*  96 */     boolean exist = IndexReader.indexExists(dir);
/*  97 */     if (exist) {
/*  98 */       IndexWriter writer = new IndexWriter(dir, 
/*  99 */         new StandardAnalyzer(Version.LUCENE_30), false, 
/* 100 */         IndexWriter.MaxFieldLength.LIMITED);
/*     */       try {
/* 102 */         LuceneContent.delete(contentId, writer);
/*     */       } finally {
/* 104 */         writer.close();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateIndex(Content content) throws IOException, ParseException {
/* 110 */     String path = this.realPathResolver.get("/WEB-INF/lucene");
/* 111 */     Directory dir = new SimpleFSDirectory(new File(path));
/* 112 */     updateIndex(content, dir);
/*     */   }
/*     */ 
/*     */   public void updateIndex(Content content, Directory dir) throws IOException, ParseException
/*     */   {
/* 117 */     boolean exist = IndexReader.indexExists(dir);
/* 118 */     IndexWriter writer = new IndexWriter(dir, 
/* 119 */       new StandardAnalyzer(Version.LUCENE_30), !exist, IndexWriter.MaxFieldLength.LIMITED);
/*     */     try {
/* 121 */       if (exist) {
/* 122 */         LuceneContent.delete(content.getId(), writer);
/*     */       }
/* 124 */       writer.addDocument(LuceneContent.createDocument(content));
/*     */     } finally {
/* 126 */       writer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination searchPage(String path, String queryString, String category, String workplace, Integer siteId, Integer channelId, Date startDate, Date endDate, int pageNo, int pageSize)
/*     */     throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 135 */     Directory dir = new SimpleFSDirectory(new File(path));
/* 136 */     return searchPage(dir, queryString, category, workplace, siteId, channelId, startDate, 
/* 137 */       endDate, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination searchPage(Directory dir, String queryString, String category, String workplace, Integer siteId, Integer channelId, Date startDate, Date endDate, int pageNo, int pageSize)
/*     */     throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 145 */     Searcher searcher = new IndexSearcher(dir);
/*     */     try {
/* 147 */       Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
/* 148 */       Query query = LuceneContent.createQuery(queryString, category, workplace, siteId, 
/* 149 */         channelId, startDate, endDate, analyzer);
/* 150 */       TopDocs docs = searcher.search(query, pageNo * pageSize);
/* 151 */       Pagination p = LuceneContent.getResultPage(searcher, docs, pageNo, 
/* 152 */         pageSize);
/* 153 */       List ids = p.getList();
/* 154 */       List contents = new ArrayList(ids.size());
/* 155 */       for (Iterator localIterator = ids.iterator(); localIterator.hasNext(); ) { Object id = localIterator.next();
/* 156 */         contents.add(this.contentMng.findById((Integer)id));
/*     */       }
/* 158 */       p.setList(contents);
/* 159 */       Pagination localPagination1 = p;
/*     */       return localPagination1;
/*     */     } finally {
/* 161 */       searcher.close();
/* 162 */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> searchList(String path, String queryString, String category, String workplace, Integer siteId, Integer channelId, Date startDate, Date endDate, int first, int max)
/*     */     throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 170 */     Directory dir = new SimpleFSDirectory(new File(path));
/* 171 */     return searchList(dir, queryString, category, workplace, siteId, channelId, startDate, 
/* 172 */       endDate, first, max);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> searchList(Directory dir, String queryString, String category, String workplace, Integer siteId, Integer channelId, Date startDate, Date endDate, int first, int max)
/*     */     throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 180 */     Searcher searcher = new IndexSearcher(dir);
/*     */     try {
/* 182 */       Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
/* 183 */       Query query = LuceneContent.createQuery(queryString, category, workplace, siteId, 
/* 184 */         channelId, startDate, endDate, analyzer);
/* 185 */       if (first < 0) {
/* 186 */         first = 0;
/*     */       }
/* 188 */       if (max < 0) {
/* 189 */         max = 0;
/*     */       }
/* 191 */       TopDocs docs = searcher.search(query, first + max);
/* 192 */       List ids = LuceneContent.getResultList(searcher, docs, 
/* 193 */         first, max);
/* 194 */       List contents = new ArrayList(ids.size());
/* 195 */       for (Iterator localIterator = ids.iterator(); localIterator.hasNext(); ) { Object id = localIterator.next();
/* 196 */         contents.add(this.contentMng.findById((Integer)id));
/*     */       }
/* 198 */       List localList1 = contents;
/*     */       return localList1;
/*     */     } finally {
/* 200 */       searcher.close();
/* 201 */     }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver)
/*     */   {
/* 210 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */   @Autowired
/*     */   public void setLuceneContentDao(LuceneContentDao luceneContentDao) {
/* 215 */     this.luceneContentDao = luceneContentDao;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentMng(ContentMng contentMng) {
/* 220 */     this.contentMng = contentMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContentSvcImpl
 * JD-Core Version:    0.6.0
 */