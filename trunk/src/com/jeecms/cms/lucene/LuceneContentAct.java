/*    */ package com.jeecms.cms.lucene;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.lucene.index.CorruptIndexException;
/*    */ import org.apache.lucene.queryParser.ParseException;
/*    */ import org.apache.lucene.store.LockObtainFailedException;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class LuceneContentAct
/*    */ {
/* 31 */   private static final Logger log = LoggerFactory.getLogger(LuceneContentAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LuceneContentSvc luceneContentSvc;
/*    */ 
/*    */   @Autowired
/*    */   private ChannelMng channelMng;
/*    */ 
/* 35 */   @RequestMapping({"/lucene/v_index.do"})
/*    */   public String index(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*    */ 
/* 37 */     List topList = this.channelMng.getTopList(site.getId(), true);
/* 38 */     List channelList = Channel.getListForSelect(topList, null, 
/* 39 */       true);
/* 40 */     model.addAttribute("site", site);
/* 41 */     model.addAttribute("channelList", channelList);
/* 42 */     return "lucene/index"; }
/*    */ 
/*    */   @RequestMapping({"/lucene/o_create.do"})
/*    */   public void create(Integer siteId, Integer channelId, Date startDate, Date endDate, Integer startId, Integer max, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws JSONException
/*    */   {
/*    */     try
/*    */     {
/* 51 */       Integer lastId = this.luceneContentSvc.createIndex(siteId, channelId, 
/* 52 */         startDate, endDate, startId, max);
/* 53 */       JSONObject json = new JSONObject();
/* 54 */       json.put("success", true);
/* 55 */       if (lastId != null) {
/* 56 */         json.put("lastId", lastId);
/*    */       }
/* 58 */       ResponseUtils.renderJson(response, json.toString());
/*    */     } catch (CorruptIndexException e) {
/* 60 */       JSONObject json = new JSONObject();
/* 61 */       json.put("success", false).put("msg", e.getMessage());
/* 62 */       ResponseUtils.renderJson(response, json.toString());
/* 63 */       log.error("", e);
/*    */     } catch (LockObtainFailedException e) {
/* 65 */       JSONObject json = new JSONObject();
/* 66 */       json.put("success", false).put("msg", e.getMessage());
/* 67 */       ResponseUtils.renderJson(response, json.toString());
/* 68 */       log.error("", e);
/*    */     } catch (IOException e) {
/* 70 */       JSONObject json = new JSONObject();
/* 71 */       json.put("success", false).put("msg", e.getMessage());
/* 72 */       ResponseUtils.renderJson(response, json.toString());
/* 73 */       log.error("", e);
/*    */     } catch (ParseException e) {
/* 75 */       JSONObject json = new JSONObject();
/* 76 */       json.put("success", false).put("msg", e.getMessage());
/* 77 */       ResponseUtils.renderJson(response, json.toString());
/* 78 */       log.error("", e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContentAct
 * JD-Core Version:    0.6.0
 */