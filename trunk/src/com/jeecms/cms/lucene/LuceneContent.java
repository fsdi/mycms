/*     */ package com.jeecms.cms.lucene;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.document.DateTools;
/*     */ import org.apache.lucene.document.DateTools.Resolution;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.document.Field.Index;
/*     */ import org.apache.lucene.document.Field.Store;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.queryParser.MultiFieldQueryParser;
/*     */ import org.apache.lucene.queryParser.ParseException;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.Searcher;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ import org.apache.lucene.search.TermRangeQuery;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.util.Version;
/*     */ 
/*     */ public class LuceneContent
/*     */ {
/*     */   public static final String ID = "id";
/*     */   public static final String SITE_ID = "siteId";
/*     */   public static final String CHANNEL_ID_ARRAY = "channelIdArray";
/*     */   public static final String RELEASE_DATE = "releaseDate";
/*     */   public static final String TITLE = "title";
/*     */   public static final String CONTENT = "content";
/*     */   public static final String WORKPLACE = "workplace";
/*     */   public static final String CATEGORY = "category";
/* 175 */   public static final String[] QUERY_FIELD = { "title", "content" };
/* 176 */   public static final Occur[] QUERY_FLAGS = { Occur.SHOULD, Occur.SHOULD };
/* 177 */   public static final String[] CATEGORY_FIELD = { "category" };
/* 178 */   public static final Occur[] CATEGORY_FLAGS = { Occur.SHOULD };
/* 179 */   public static final String[] WORKPLACE_FIELD = { "workplace" };
/* 180 */   public static final Occur[] WORKPLACE_FLAGS = { Occur.SHOULD };
/*     */ 
/*     */   public static Document createDocument(Content c)
/*     */   {
/*  43 */     Document doc = new Document();
/*  44 */     doc.add(
/*  45 */       new Field("id", c.getId().toString(), Field.Store.YES, 
/*  45 */       Field.Index.NOT_ANALYZED));
/*  46 */     doc.add(
/*  47 */       new Field("siteId", c.getSite().getId().toString(), 
/*  47 */       Field.Store.NO, Field.Index.NOT_ANALYZED));
/*  48 */     doc.add(
/*  50 */       new Field("releaseDate", DateTools.dateToString(
/*  49 */       c.getReleaseDate(), DateTools.Resolution.DAY), Field.Store.NO, 
/*  50 */       Field.Index.NOT_ANALYZED));
/*  51 */     Channel channel = c.getChannel();
/*  52 */     while (channel != null) {
/*  53 */       doc.add(
/*  54 */         new Field("channelIdArray", channel.getId().toString(), 
/*  54 */         Field.Store.NO, Field.Index.NOT_ANALYZED));
/*  55 */       channel = channel.getParent();
/*     */     }
/*  57 */     doc.add(
/*  58 */       new Field("title", c.getTitle(), Field.Store.NO, 
/*  58 */       Field.Index.ANALYZED));
/*  59 */     if (!StringUtils.isBlank(c.getTxt())) {
/*  60 */       doc.add(
/*  61 */         new Field("content", c.getTxt(), Field.Store.NO, 
/*  61 */         Field.Index.ANALYZED));
/*     */     }
/*  63 */     if ((c.getAttr() != null) && (StringUtils.isNotBlank((String)c.getAttr().get("workplace")))) {
/*  64 */       doc.add(
/*  65 */         new Field("workplace", (String)c.getAttr().get("workplace"), Field.Store.NO, 
/*  65 */         Field.Index.ANALYZED));
/*     */     }
/*  67 */     if ((c.getAttr() != null) && (StringUtils.isNotBlank((String)c.getAttr().get("category")))) {
/*  68 */       doc.add(
/*  69 */         new Field("category", (String)c.getAttr().get("category"), Field.Store.NO, 
/*  69 */         Field.Index.ANALYZED));
/*     */     }
/*  71 */     return doc;
/*     */   }
/*     */ 
/*     */   public static Query createQuery(String queryString, String category, String workplace, Integer siteId, Integer channelId, Date startDate, Date endDate, Analyzer analyzer)
/*     */     throws ParseException
/*     */   {
/*  77 */     BooleanQuery bq = new BooleanQuery();
/*     */ 
/*  79 */     if (!StringUtils.isBlank(queryString)) {
/*  80 */       Query q = MultiFieldQueryParser.parse(Version.LUCENE_30, queryString, 
/*  81 */         QUERY_FIELD, QUERY_FLAGS, analyzer);
/*  82 */       bq.add(q, Occur.MUST);
/*     */     }
/*  84 */     if (StringUtils.isNotBlank(category)) {
/*  85 */       Query q = MultiFieldQueryParser.parse(Version.LUCENE_30, category, 
/*  86 */         CATEGORY_FIELD, CATEGORY_FLAGS, analyzer);
/*  87 */       bq.add(q, Occur.MUST);
/*     */     }
/*  89 */     if (StringUtils.isNotBlank(workplace)) {
/*  90 */       Query q = MultiFieldQueryParser.parse(Version.LUCENE_30, workplace, 
/*  91 */         WORKPLACE_FIELD, WORKPLACE_FLAGS, analyzer);
/*  92 */       bq.add(q, Occur.MUST);
/*     */     }
/*  94 */     if (siteId != null) {
/*  95 */       Query q = new TermQuery(new Term("siteId", siteId.toString()));
/*  96 */       bq.add(q, Occur.MUST);
/*     */     }
/*  98 */     if (channelId != null) {
/*  99 */       Query q = new TermQuery(new Term("channelIdArray", channelId.toString()));
/* 100 */       bq.add(q, Occur.MUST);
/*     */     }
/* 102 */     if ((startDate != null) || (endDate != null)) {
/* 103 */       String start = null;
/* 104 */       String end = null;
/* 105 */       if (startDate != null) {
/* 106 */         start = DateTools.dateToString(startDate, DateTools.Resolution.DAY);
/*     */       }
/* 108 */       if (endDate != null) {
/* 109 */         end = DateTools.dateToString(endDate, DateTools.Resolution.DAY);
/*     */       }
/* 111 */       Query q = new TermRangeQuery("releaseDate", start, end, true, true);
/* 112 */       bq.add(q, Occur.MUST);
/*     */     }
/* 114 */     return bq;
/*     */   }
/*     */ 
/*     */   public static void delete(Integer siteId, Integer channelId, Date startDate, Date endDate, IndexWriter writer)
/*     */     throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 120 */     writer.deleteDocuments(
/* 121 */       createQuery(null, null, null, siteId, channelId, startDate, 
/* 121 */       endDate, null));
/*     */   }
/*     */ 
/*     */   public static void delete(Integer contentId, IndexWriter writer) throws CorruptIndexException, IOException, ParseException
/*     */   {
/* 126 */     writer.deleteDocuments(new Term("id", contentId.toString()));
/*     */   }
/*     */ 
/*     */   public static Pagination getResultPage(Searcher searcher, TopDocs docs, int pageNo, int pageSize) throws CorruptIndexException, IOException
/*     */   {
/* 131 */     List list = new ArrayList(pageSize);
/* 132 */     ScoreDoc[] hits = docs.scoreDocs;
/* 133 */     int endIndex = pageNo * pageSize;
/* 134 */     int len = hits.length;
/* 135 */     if (endIndex > len) {
/* 136 */       endIndex = len;
/*     */     }
/* 138 */     for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
/* 139 */       Document d = searcher.doc(hits[i].doc);
/* 140 */       list.add(Integer.valueOf(d.getField("id").stringValue()));
/*     */     }
/* 142 */     return new Pagination(pageNo, pageSize, docs.totalHits, list);
/*     */   }
/*     */ 
/*     */   public static List<Integer> getResultList(Searcher searcher, TopDocs docs, int first, int max) throws CorruptIndexException, IOException
/*     */   {
/* 147 */     List list = new ArrayList(max);
/* 148 */     ScoreDoc[] hits = docs.scoreDocs;
/* 149 */     if (first < 0) {
/* 150 */       first = 0;
/*     */     }
/* 152 */     if (max < 0) {
/* 153 */       max = 0;
/*     */     }
/* 155 */     int last = first + max;
/* 156 */     int len = hits.length;
/* 157 */     if (last > len) {
/* 158 */       last = len;
/*     */     }
/* 160 */     for (int i = first; i < last; i++) {
/* 161 */       Document d = searcher.doc(hits[i].doc);
/* 162 */       list.add(Integer.valueOf(d.getField("id").stringValue()));
/*     */     }
/* 164 */     return list;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContent
 * JD-Core Version:    0.6.0
 */