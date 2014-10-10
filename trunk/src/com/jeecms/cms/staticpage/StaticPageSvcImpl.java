/*     */ package com.jeecms.cms.staticpage;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.MessageSource;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.util.Assert;
/*     */ import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
/*     */ 
/*     */ @Service
/*     */ public class StaticPageSvcImpl
/*     */   implements StaticPageSvc, InitializingBean
/*     */ {
/*  40 */   private Logger log = LoggerFactory.getLogger(StaticPageSvcImpl.class);
/*     */   private MessageSource tplMessageSource;
/*     */   private RealPathResolver realPathResolver;
/*     */   private StaticPageDao staticPageDao;
/*     */   private Configuration conf;
/*     */ 
/*     */   @Transactional
/*     */   public int content(Integer siteId, Integer channelId, Date start)
/*     */     throws IOException, TemplateException
/*     */   {
/*  45 */     long time = System.currentTimeMillis();
/*  46 */     Map data = new HashMap();
/*  47 */     int count = this.staticPageDao.contentStatic(siteId, channelId, start, this.conf, 
/*  48 */       data);
/*  49 */     time = System.currentTimeMillis() - time;
/*  50 */     this.log.info("create content page count {}, in {} ms", Integer.valueOf(count), Long.valueOf(time));
/*  51 */     return count;
/*     */   }
/*     */   @Transactional
/*     */   public boolean content(Content content) throws IOException, TemplateException {
/*  56 */     Map data = new HashMap();
/*  57 */     long time = System.currentTimeMillis();
/*  58 */     boolean generated = this.staticPageDao.contentStatic(content, this.conf, data);
/*  59 */     time = System.currentTimeMillis() - time;
/*  60 */     this.log.info("create content page in {} ms", Long.valueOf(time));
/*  61 */     return generated;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void contentRelated(Content content) throws IOException, TemplateException {
/*  67 */     content(content);
/*  68 */     Channel channel = content.getChannel();
/*  69 */     while (channel != null) {
/*  70 */       channel(channel, true);
/*  71 */       channel = channel.getParent();
/*     */     }
/*  73 */     if (content.getSite().getStaticIndex().booleanValue())
/*  74 */       index(content.getSite());
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void deleteContent(Content content)
/*     */   {
/*  82 */     int totalPage = content.getPageCount();
/*  83 */     for (int pageNo = 1; pageNo <= totalPage; pageNo++) {
/*  84 */       String real = this.realPathResolver.get(content.getStaticFilename(pageNo));
/*  85 */       File file = new File(real);
/*  86 */       file.delete();
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public int channel(Integer siteId, Integer channelId, boolean containChild) throws IOException, TemplateException {
/*  93 */     long time = System.currentTimeMillis();
/*  94 */     Map data = new HashMap();
/*  95 */     int count = this.staticPageDao.channelStatic(siteId, channelId, 
/*  96 */       containChild, this.conf, data);
/*  97 */     time = System.currentTimeMillis() - time;
/*  98 */     this.log.info("create channel page count {}, in {} ms", Integer.valueOf(count), Long.valueOf(time));
/*  99 */     return count;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void channel(Channel channel, boolean firstOnly) throws IOException, TemplateException {
/* 105 */     Map data = new HashMap();
/* 106 */     long time = System.currentTimeMillis();
/* 107 */     this.staticPageDao.channelStatic(channel, firstOnly, this.conf, data);
/* 108 */     time = System.currentTimeMillis() - time;
/* 109 */     this.log.info("create channel page in {} ms", Long.valueOf(time));
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void deleteChannel(Channel channel) {
/* 115 */     if ((!StringUtils.isBlank(channel.getLink())) || 
/* 116 */       (!channel.getStaticChannel().booleanValue())) {
/* 117 */       return;
/*     */     }
/*     */ 
/* 120 */     int childs = this.staticPageDao.childsOfChannel(channel.getId());
/*     */     int totalPage;
/* 122 */     if ((!channel.getModel().getHasContent().booleanValue()) || (
/* 123 */       (!channel.getListChild().booleanValue()) && (childs > 0))) {
/* 124 */       totalPage = 1;
/*     */     }
/*     */     else
/*     */     {
/*     */       int quantity;
/* 126 */       if (channel.getListChild().booleanValue())
/* 127 */         quantity = childs;
/*     */       else
/* 129 */         quantity = this.staticPageDao.contentsOfChannel(channel.getId());
/* 131 */       if (quantity <= 0)
/* 132 */         totalPage = 1;
/*     */       else {
/* 134 */         totalPage = (quantity - 1) / channel.getPageSize().intValue() + 1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 139 */     for (int i = 1; i <= totalPage; i++) {
/* 140 */       String filename = channel.getStaticFilename(i);
/* 141 */       String real = this.realPathResolver.get(filename.toString());
/* 142 */       File f = new File(real);
/* 143 */       f.delete();
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void index(CmsSite site) throws IOException, TemplateException {
/* 149 */     Map data = new HashMap();
/* 150 */     FrontUtils.frontData(data, site, null, site.getUrlStatic(), null);
/* 151 */     String tpl = FrontUtils.getTplPath(this.tplMessageSource, 
/* 152 */       site.getLocaleAdmin(), site.getSolutionPath(), "index", 
/* 153 */       "tpl.index");
/* 154 */     index(site, tpl, data);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public void index(CmsSite site, String tpl, Map<String, Object> data) throws IOException, TemplateException {
/* 160 */     long time = System.currentTimeMillis();
/* 161 */     File f = new File(getIndexPath(site));
/* 162 */     File parent = f.getParentFile();
/* 163 */     if (!parent.exists()) {
/* 164 */       parent.mkdirs();
/*     */     }
/* 166 */     Writer out = null;
/*     */     try
/*     */     {
/* 169 */       out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
/* 170 */       Template template = this.conf.getTemplate(tpl);
/* 171 */       template.process(data, out);
/*     */     } finally {
/* 173 */       if (out != null) {
/* 174 */         out.flush();
/* 175 */         out.close();
/*     */       }
/*     */     }
/* 178 */     time = System.currentTimeMillis() - time;
/* 179 */     this.log.info("create index page, in {} ms", Long.valueOf(time));
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public boolean deleteIndex(CmsSite site) {
/* 184 */     File f = new File(getIndexPath(site));
/* 185 */     return f.delete();
/*     */   }
/*     */ 
/*     */   private String getIndexPath(CmsSite site) {
/* 189 */     StringBuilder pathBuff = new StringBuilder();
/* 190 */     if ((!site.getIndexToRoot().booleanValue()) && 
/* 191 */       (!StringUtils.isBlank(site.getStaticDir()))) {
/* 192 */       pathBuff.append(site.getStaticDir());
/*     */     }
/*     */ 
/* 195 */     pathBuff.append("/").append("index").append(
/* 196 */       site.getStaticSuffix());
/* 197 */     return this.realPathResolver.get(pathBuff.toString());
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/* 206 */     Assert.notNull(this.conf, "freemarker configuration cannot be null!");
/* 207 */     Assert.notNull(this.tplMessageSource, 
/* 208 */       "tplMessageSource configuration cannot be null!");
/*     */   }
/*     */ 
/*     */   public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer)
/*     */   {
/* 213 */     this.conf = freeMarkerConfigurer.getConfiguration();
/*     */   }
/*     */ 
/*     */   public void setTplMessageSource(MessageSource tplMessageSource) {
/* 217 */     this.tplMessageSource = tplMessageSource;
/*     */   }
/*     */   @Autowired
/*     */   public void setStaticPageDao(StaticPageDao staticPageDao) {
/* 222 */     this.staticPageDao = staticPageDao;
/*     */   }
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver) {
/* 227 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticPageSvcImpl
 * JD-Core Version:    0.6.0
 */