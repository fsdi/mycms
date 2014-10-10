/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteItemMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteSubTopicMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsVoteTopicAct
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(CmsVoteTopicAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteTopicMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteSubTopicMng subTopicMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsVoteItemMng voteItemMng;
/*     */ 
/*  44 */   @RequestMapping({"/vote_topic/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  45 */     Pagination pagination = this.manager.getPage(site.getId(), SimplePage.cpn(pageNo), 
/*  46 */       CookieUtils.getPageSize(request));
/*  47 */     model.addAttribute("pagination", pagination);
/*  48 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  49 */     return "vote_topic/list"; }
/*     */ 
/*     */   @RequestMapping({"/vote_topic/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  54 */     return "vote_topic/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/vote_topic/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  60 */     WebErrors errors = validateEdit(id, request);
/*  61 */     if (errors.hasErrors()) {
/*  62 */       return errors.showErrorPage(model);
/*     */     }
/*  64 */     model.addAttribute("cmsVoteTopic", this.manager.findById(id));
/*  65 */     model.addAttribute("pageNo", pageNo);
/*  66 */     return "vote_topic/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/vote_topic/o_save.do"})
/*     */   public String save(CmsVoteTopic bean, Integer[] subPriority, String[] itemTitle, Integer[] itemVoteCount, Integer[] itemPriority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  74 */     WebErrors errors = validateSave(bean, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*  78 */     ArrayUtils.reverse(subPriority);
/*  79 */     List subTitleList = getSubTitlesParam(request);
/*  80 */     List subTypeIds = getSubTypeIdsParam(request);
/*     */ 
/*  83 */     Set subTopics = getSubTopics(null, subTitleList, subPriority, subTypeIds);
/*  84 */     bean = this.manager.save(bean, subTopics);
/*  85 */     List voteItems = getSubtopicItems(itemTitle, itemVoteCount, itemPriority);
/*  86 */     List subTopicSet = this.subTopicMng.findByVoteTopic(bean.getId());
/*  87 */     for (int i = 0; i < voteItems.size(); i++) {
/*  88 */       if (((List)voteItems.get(i)).size() <= 0) {
/*  89 */         voteItems.remove(i);
/*     */       }
/*     */     }
/*  92 */     for (int i = 0; i < subTopicSet.size(); i++) {
/*  93 */       this.voteItemMng.save((Collection)voteItems.get(i), (CmsVoteSubTopic)subTopicSet.get(i));
/*     */     }
/*  95 */     log.info("save CmsVoteTopic id={}", bean.getId());
/*  96 */     this.cmsLogMng.operating(request, "cmsVoteTopic.log.save", "id=" + 
/*  97 */       bean.getId() + ";title=" + bean.getTitle());
/*  98 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/vote_topic/o_update.do"})
/*     */   public String update(CmsVoteTopic bean, Integer[] subPriority, Integer[] subTopicId, String[] itemTitle, Integer[] itemVoteCount, Integer[] itemPriority, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 107 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 108 */     if (errors.hasErrors()) {
/* 109 */       return errors.showErrorPage(model);
/*     */     }
/* 111 */     ArrayUtils.reverse(subPriority);
/* 112 */     ArrayUtils.reverse(subTopicId);
/* 113 */     List subTitleList = getSubTitlesParam(request);
/* 114 */     List subTypeIds = getSubTypeIdsParam(request);
/*     */ 
/* 116 */     Set subTopics = getSubTopics(subTopicId, subTitleList, subPriority, subTypeIds);
/* 117 */     bean = this.manager.update(bean);
/* 118 */     this.subTopicMng.update(subTopics, bean);
/* 119 */     List voteItems = getSubtopicItems(itemTitle, itemVoteCount, itemPriority);
/* 120 */     List subTopicSet = this.subTopicMng.findByVoteTopic(bean.getId());
/* 121 */     for (int i = 0; i < voteItems.size(); i++) {
/* 122 */       if (((List)voteItems.get(i)).size() <= 0) {
/* 123 */         voteItems.remove(i);
/*     */       }
/*     */     }
/* 126 */     for (int i = 0; i < subTopicSet.size(); i++) {
/* 127 */       CmsVoteSubTopic voteSubTopic = (CmsVoteSubTopic)subTopicSet.get(i);
/* 128 */       if ((voteSubTopic.getType().intValue() != 3) && (voteItems.size() >= subTopicSet.size())) {
/* 129 */         this.voteItemMng.update((Collection)voteItems.get(i), voteSubTopic);
/*     */       }
/*     */     }
/* 132 */     log.info("update CmsVoteTopic id={}.", bean.getId());
/* 133 */     this.cmsLogMng.operating(request, "cmsVoteTopic.log.update", "id=" + 
/* 134 */       bean.getId() + ";title=" + bean.getTitle());
/* 135 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/vote_topic/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 141 */     WebErrors errors = validateDelete(ids, request);
/* 142 */     if (errors.hasErrors()) {
/* 143 */       return errors.showErrorPage(model);
/*     */     }
/* 145 */     CmsVoteTopic[] beans = this.manager.deleteByIds(ids);
/* 146 */     for (CmsVoteTopic bean : beans) {
/* 147 */       log.info("delete CmsVoteTopic id={}", bean.getId());
/* 148 */       this.cmsLogMng.operating(request, "cmsVoteTopic.log.delete", "id=" + 
/* 149 */         bean.getId() + ";title=" + bean.getTitle());
/*     */     }
/* 151 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private List getSubTitlesParam(HttpServletRequest request)
/*     */   {
/* 156 */     Enumeration paramNames = request.getParameterNames();
/* 157 */     List subTitleList = new ArrayList();
/*     */ 
/* 159 */     while (paramNames.hasMoreElements()) {
/* 160 */       String paramName = (String)paramNames.nextElement();
/* 161 */       if (paramName.startsWith("subtitle")) {
/* 162 */         subTitleList.add(request.getParameter(paramName));
/*     */       }
/*     */     }
/* 165 */     return subTitleList;
/*     */   }
/*     */ 
/*     */   private List getSubTypeIdsParam(HttpServletRequest request) {
/* 169 */     Enumeration paramNames = request.getParameterNames();
/* 170 */     List subTypeIds = new ArrayList();
/*     */ 
/* 172 */     while (paramNames.hasMoreElements()) {
/* 173 */       String paramName = (String)paramNames.nextElement();
/* 174 */       if (paramName.startsWith("typeId")) {
/* 175 */         subTypeIds.add(Integer.valueOf(Integer.parseInt(request.getParameter(paramName))));
/*     */       }
/*     */     }
/* 178 */     return subTypeIds;
/*     */   }
/*     */ 
/*     */   private Integer[] getSubPrioritysParam(HttpServletRequest request)
/*     */   {
/* 183 */     Enumeration paramNames = request.getParameterNames();
/* 184 */     List subPrioritys = new ArrayList();
/*     */ 
/* 186 */     while (paramNames.hasMoreElements()) {
/* 187 */       String paramName = (String)paramNames.nextElement();
/* 188 */       if (paramName.startsWith("subPriority")) {
/* 189 */         subPrioritys.add(Integer.valueOf(Integer.parseInt(request.getParameter(paramName))));
/*     */       }
/*     */     }
/* 192 */     Integer[] prioritys = (Integer[])subPrioritys.toArray();
/* 193 */     return prioritys;
/*     */   }
/*     */ 
/*     */   private List<CmsVoteItem> getItems(Integer[] itemId, String[] itemTitle, Integer[] itemVoteCount, Integer[] itemPriority)
/*     */   {
/* 199 */     List items = new ArrayList();
/*     */ 
/* 201 */     int i = 0; for (int len = itemTitle.length; i < len; i++) {
/* 202 */       if (!StringUtils.isBlank(itemTitle[i])) {
/* 203 */         CmsVoteItem item = new CmsVoteItem();
/* 204 */         if ((itemId != null) && (itemId[i] != null)) {
/* 205 */           item.setId(itemId[i]);
/*     */         }
/* 207 */         item.setTitle(itemTitle[i]);
/* 208 */         item.setVoteCount(itemVoteCount[i]);
/* 209 */         item.setPriority(itemPriority[i]);
/* 210 */         items.add(item);
/*     */       }
/*     */     }
/* 213 */     return items;
/*     */   }
/*     */ 
/*     */   private List<List<CmsVoteItem>> getSubtopicItems(String[] itemTitle, Integer[] itemVoteCount, Integer[] itemPriority)
/*     */   {
/* 218 */     List subTopicItems = new ArrayList();
/*     */ 
/* 220 */     List splitCharIndexList = new ArrayList();
/* 221 */     if (itemTitle != null) {
/* 222 */       int i = 0; for (int len = itemTitle.length; i < len; i++) {
/* 223 */         if (itemTitle[i].equals(",")) {
/* 224 */           splitCharIndexList.add(Integer.valueOf(i));
/*     */         }
/*     */       }
/* 227 */       for (i = 0; i < splitCharIndexList.size() - 1; i++) {
/* 228 */         List items = new ArrayList();
/*     */ 
/* 230 */         if (((Integer)splitCharIndexList.get(i + 1)).intValue() - ((Integer)splitCharIndexList.get(i)).intValue() != 1) {
/* 231 */           for (int index = ((Integer)splitCharIndexList.get(i)).intValue(); index < itemTitle.length; index++) {
/* 232 */             if ((index <= ((Integer)splitCharIndexList.get(i)).intValue()) || (index >= ((Integer)splitCharIndexList.get(i + 1)).intValue()) || 
/* 233 */               (StringUtils.isBlank(itemTitle[index]))) continue;
/* 234 */             CmsVoteItem item = new CmsVoteItem();
/* 235 */             item.setTitle(itemTitle[index]);
/* 236 */             item.setVoteCount(itemVoteCount[(index - i - 1)]);
/* 237 */             item.setPriority(itemPriority[(index - i - 1)]);
/* 238 */             items.add(item);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 243 */         subTopicItems.add(items);
/*     */       }
/*     */     }
/* 246 */     return subTopicItems;
/*     */   }
/*     */ 
/*     */   private Set<CmsVoteSubTopic> getSubTopics(Integer[] subTopicId, List<String> title, Integer[] subPriority, List<Integer> typeId) {
/* 250 */     SortedSet subTopics = new TreeSet();
/*     */ 
/* 252 */     int i = 0; for (int len = title.size(); i < len; i++) {
/* 253 */       if (!StringUtils.isBlank((String)title.get(i))) {
/* 254 */         CmsVoteSubTopic subTopic = new CmsVoteSubTopic();
/* 255 */         if ((subTopicId != null) && (subTopicId[i] != null)) {
/* 256 */           subTopic.setId(subTopicId[i]);
/*     */         }
/* 258 */         subTopic.setTitle((String)title.get(i));
/* 259 */         subTopic.setType((Integer)typeId.get(i));
/* 260 */         subTopic.setPriority(subPriority[i]);
/* 261 */         subTopics.add(subTopic);
/*     */       }
/*     */     }
/* 264 */     return subTopics;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsVoteTopic bean, HttpServletRequest request) {
/* 268 */     WebErrors errors = WebErrors.create(request);
/* 269 */     CmsSite site = CmsUtils.getSite(request);
/* 270 */     bean.setSite(site);
/* 271 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 275 */     WebErrors errors = WebErrors.create(request);
/* 276 */     CmsSite site = CmsUtils.getSite(request);
/* 277 */     if (vldExist(id, site.getId(), errors)) {
/* 278 */       return errors;
/*     */     }
/* 280 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 284 */     WebErrors errors = WebErrors.create(request);
/* 285 */     CmsSite site = CmsUtils.getSite(request);
/* 286 */     if (vldExist(id, site.getId(), errors)) {
/* 287 */       return errors;
/*     */     }
/* 289 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 293 */     WebErrors errors = WebErrors.create(request);
/* 294 */     CmsSite site = CmsUtils.getSite(request);
/* 295 */     if (errors.ifEmpty(ids, "ids")) {
/* 296 */       return errors;
/*     */     }
/* 298 */     for (Integer id : ids) {
/* 299 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 301 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 305 */     if (errors.ifNull(id, "id")) {
/* 306 */       return true;
/*     */     }
/* 308 */     CmsVoteTopic entity = this.manager.findById(id);
/* 309 */     if (errors.ifNotExist(entity, CmsVoteTopic.class, id)) {
/* 310 */       return true;
/*     */     }
/* 312 */     if (!entity.getSite().getId().equals(siteId)) {
/* 313 */       errors.notInSite(CmsVoteTopic.class, id);
/* 314 */       return true;
/*     */     }
/* 316 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsVoteTopicAct
 * JD-Core Version:    0.6.0
 */