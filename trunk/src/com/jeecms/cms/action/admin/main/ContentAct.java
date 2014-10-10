/*      */ package com.jeecms.cms.action.admin.main;
/*      */ 
/*      */ import com.jeecms.cms.entity.main.Channel;
/*      */ import com.jeecms.cms.entity.main.CmsConfig;
/*      */ import com.jeecms.cms.entity.main.CmsGroup;
/*      */ import com.jeecms.cms.entity.main.CmsModel;
/*      */ import com.jeecms.cms.entity.main.CmsSite;
/*      */ import com.jeecms.cms.entity.main.CmsTopic;
/*      */ import com.jeecms.cms.entity.main.CmsUser;
/*      */ import com.jeecms.cms.entity.main.CmsUserSite;
/*      */ import com.jeecms.cms.entity.main.Content;
/*      */ import com.jeecms.cms.entity.main.Content.ContentStatus;
/*      */ import com.jeecms.cms.entity.main.ContentExt;
/*      */ import com.jeecms.cms.entity.main.ContentTxt;
/*      */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*      */ import com.jeecms.cms.manager.main.ChannelMng;
/*      */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*      */ import com.jeecms.cms.manager.main.CmsLogMng;
/*      */ import com.jeecms.cms.manager.main.CmsModelItemMng;
/*      */ import com.jeecms.cms.manager.main.CmsModelMng;
/*      */ import com.jeecms.cms.manager.main.CmsTopicMng;
/*      */ import com.jeecms.cms.manager.main.CmsUserMng;
/*      */ import com.jeecms.cms.manager.main.ContentMng;
/*      */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*      */ import com.jeecms.cms.staticpage.exception.ContentNotCheckedException;
/*      */ import com.jeecms.cms.staticpage.exception.GeneratedZeroStaticPageException;
/*      */ import com.jeecms.cms.staticpage.exception.StaticPageNotOpenException;
/*      */ import com.jeecms.cms.staticpage.exception.TemplateNotFoundException;
/*      */ import com.jeecms.cms.staticpage.exception.TemplateParseException;
/*      */ import com.jeecms.cms.web.CmsUtils;
/*      */ import com.jeecms.cms.web.WebErrors;
/*      */ import com.jeecms.common.page.Pagination;
/*      */ import com.jeecms.common.page.SimplePage;
/*      */ import com.jeecms.common.upload.FileRepository;
/*      */ import com.jeecms.common.util.StrUtils;
/*      */ import com.jeecms.common.web.CookieUtils;
/*      */ import com.jeecms.common.web.RequestUtils;
/*      */ import com.jeecms.common.web.ResponseUtils;
/*      */ import com.jeecms.common.web.springmvc.MessageResolver;
/*      */ import com.jeecms.core.entity.Ftp;
/*      */ import com.jeecms.core.manager.DbFileMng;
/*      */ import com.jeecms.core.tpl.TplManager;
/*      */ import com.jeecms.core.web.CoreUtils;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import org.apache.commons.io.FilenameUtils;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Controller;
/*      */ import org.springframework.ui.ModelMap;
/*      */ import org.springframework.web.bind.annotation.RequestMapping;
/*      */ import org.springframework.web.bind.annotation.RequestParam;
/*      */ import org.springframework.web.multipart.MultipartFile;
/*      */ 
/*      */ @Controller
/*      */ public class ContentAct
/*      */ {
/*   72 */   private static final Logger log = LoggerFactory.getLogger(ContentAct.class);
/*      */ 
/*      */   @Autowired
/*      */   private ChannelMng channelMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsUserMng cmsUserMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsModelMng cmsModelMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsModelItemMng cmsModelItemMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsTopicMng cmsTopicMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsGroupMng cmsGroupMng;
/*      */ 
/*      */   @Autowired
/*      */   private ContentTypeMng contentTypeMng;
/*      */ 
/*      */   @Autowired
/*      */   private TplManager tplManager;
/*      */ 
/*      */   @Autowired
/*      */   private FileRepository fileRepository;
/*      */ 
/*      */   @Autowired
/*      */   private DbFileMng dbFileMng;
/*      */ 
/*      */   @Autowired
/*      */   private CmsLogMng cmsLogMng;
/*      */ 
/*      */   @Autowired
/*      */   private ContentMng manager;
/*      */ 
/*      */   @Autowired
/*      */   private CmsFileMng fileMng;
/*      */ 
/*   76 */   @RequestMapping({"/content/v_left.do"})
/*      */   public String left() { return "content/left";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_tree.do"})
/*      */   public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*      */   {
/*   91 */     log.debug("tree path={}", root);
/*      */     boolean isRoot;
/*   94 */     if ((StringUtils.isBlank(root)) || ("source".equals(root)))
/*   95 */       isRoot = true;
/*      */     else {
/*   97 */       isRoot = false;
/*      */     }
/*   99 */     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  100 */     WebErrors errors = validateTree(root, request);
/*  101 */     if (errors.hasErrors()) {
/*  102 */       log.error((String)errors.getErrors().get(0));
/*  103 */       ResponseUtils.renderJson(response, "[]");
/*  104 */       return null;
/*      */     }
/*  106 */     Integer siteId = CmsUtils.getSiteId(request);
/*  107 */     Integer userId = CmsUtils.getUserId(request);
/*      */     List list;
/*  109 */     if (isRoot)
/*  110 */       list = this.channelMng.getTopListByRigth(userId, siteId, true);
/*      */     else {
/*  112 */       list = this.channelMng.getChildListByRight(userId, siteId, 
/*  113 */         Integer.valueOf(Integer.parseInt(root)), true);
/*      */     }
/*  115 */     model.addAttribute("list", list);
/*  116 */     response.setHeader("Cache-Control", "no-cache");
/*  117 */     response.setContentType("text/json;charset=UTF-8");
/*  118 */     return "content/tree";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_tree_channels.do"})
/*      */   public String treeChannels(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*      */   {
/*  133 */     tree(root, request, response, model);
/*  134 */     return "content/tree_channels";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_list.do"})
/*      */   public String list(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  142 */     String queryTitle = RequestUtils.getQueryParam(request, "queryTitle");
/*  143 */     queryTitle = StringUtils.trim(queryTitle);
/*  144 */     String queryInputUsername = RequestUtils.getQueryParam(request, 
/*  145 */       "queryInputUsername");
/*  146 */     queryInputUsername = StringUtils.trim(queryInputUsername);
/*  147 */     if (queryTopLevel == null) {
/*  148 */       queryTopLevel = Boolean.valueOf(false);
/*      */     }
/*  150 */     if (queryRecommend == null) {
/*  151 */       queryRecommend = Boolean.valueOf(false);
/*      */     }
/*  153 */     if (queryOrderBy == null)
/*  154 */       queryOrderBy = Integer.valueOf(0);
/*      */     Content.ContentStatus status;
/*  157 */     if (!StringUtils.isBlank(queryStatus))
/*  158 */       status = Content.ContentStatus.valueOf(queryStatus);
/*      */     else {
/*  160 */       status = Content.ContentStatus.all;
/*      */     }
/*  162 */     Integer queryInputUserId = null;
/*  163 */     if (!StringUtils.isBlank(queryInputUsername)) {
/*  164 */       CmsUser u = this.cmsUserMng.findByUsername(queryInputUsername);
/*  165 */       if (u != null) {
/*  166 */         queryInputUserId = u.getId();
/*      */       }
/*      */       else {
/*  169 */         queryInputUsername = null;
/*      */       }
/*      */     }
/*  172 */     CmsSite site = CmsUtils.getSite(request);
/*  173 */     Integer siteId = site.getId();
/*  174 */     CmsUser user = CmsUtils.getUser(request);
/*  175 */     Integer userId = user.getId();
/*  176 */     byte currStep = user.getCheckStep(siteId).byteValue();
/*  177 */     Pagination p = this.manager.getPageByRight(queryTitle, queryTypeId, 
/*  178 */       queryInputUserId, queryTopLevel.booleanValue(), queryRecommend.booleanValue(), status, 
/*  179 */       user.getCheckStep(siteId), siteId, cid, userId, 
/*  180 */       queryOrderBy.intValue(), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  181 */     List typeList = this.contentTypeMng.getList(true);
/*  182 */     List models = this.cmsModelMng.getList(false, Boolean.valueOf(true));
/*  183 */     if (cid != null) {
/*  184 */       Channel c = this.channelMng.findById(cid);
/*  185 */       models = c.getModels(models);
/*      */     }
/*  187 */     model.addAttribute("pagination", p);
/*  188 */     model.addAttribute("cid", cid);
/*  189 */     model.addAttribute("typeList", typeList);
/*  190 */     model.addAttribute("currStep", Byte.valueOf(currStep));
/*  191 */     model.addAttribute("site", site);
/*  192 */     model.addAttribute("models", models);
/*  193 */     addAttibuteForQuery(model, queryTitle, queryInputUsername, queryStatus, 
/*  194 */       queryTypeId, queryTopLevel, queryRecommend, queryOrderBy, 
/*  195 */       pageNo);
/*      */ 
/*  197 */     return "content/list";
/*      */   }
/*      */   @RequestMapping({"/content/v_add.do"})
/*      */   public String add(Integer cid, Integer modelId, HttpServletRequest request, ModelMap model) {
/*  202 */     WebErrors errors = validateAdd(cid, modelId, request);
/*  203 */     if (errors.hasErrors()) {
/*  204 */       return errors.showErrorPage(model);
/*      */     }
/*  206 */     CmsSite site = CmsUtils.getSite(request);
/*  207 */     Integer siteId = site.getId();
/*  208 */     CmsUser user = CmsUtils.getUser(request);
/*  209 */     Integer userId = user.getId();
/*      */     Channel c;
/*  212 */     if (cid != null)
/*  213 */       c = this.channelMng.findById(cid);
/*      */     else
/*  215 */       c = null;
/*      */     CmsModel m;
/*  219 */     if (modelId == null)
/*      */     {
/*  220 */       if (c != null) {
/*  221 */         m = c.getModel();
/*      */       } else {
/*  223 */         m = this.cmsModelMng.getDefModel();
/*      */ 
/*  225 */         if (m == null)
/*  226 */           throw new RuntimeException("default model not found!");
/*      */       }
/*      */     }
/*      */     else {
/*  230 */       m = this.cmsModelMng.findById(modelId);
/*      */     }
/*      */ 
/*  233 */     List itemList = this.cmsModelItemMng.getList(m.getId(), false, 
/*  234 */       false);
/*      */     Set rights;
/*  238 */     if (user.getUserSite(siteId).getAllChannel().booleanValue())
/*      */     {
/*  240 */       rights = null;
/*      */     }
/*  242 */     else rights = user.getChannels(siteId);
/*      */     List channelList;
/*  244 */     if (c != null) {
/*  245 */       channelList = c.getListForSelect(rights, true);
/*      */     } else {
/*  247 */       List topList = this.channelMng.getTopListByRigth(userId, 
/*  248 */         siteId, true);
/*  249 */       channelList = Channel.getListForSelect(topList, rights, true);
/*      */     }
/*      */     List topicList;
/*  254 */     if (c != null)
/*  255 */       topicList = this.cmsTopicMng.getListByChannel(c.getId());
/*      */     else {
/*  257 */       topicList = new ArrayList();
/*      */     }
/*      */ 
/*  260 */     List tplList = getTplContent(site, m, null);
/*      */ 
/*  262 */     List groupList = this.cmsGroupMng.getList();
/*      */ 
/*  264 */     List typeList = this.contentTypeMng.getList(false);
/*      */ 
/*  266 */     model.addAttribute("model", m);
/*  267 */     model.addAttribute("itemList", itemList);
/*  268 */     model.addAttribute("channelList", channelList);
/*  269 */     model.addAttribute("topicList", topicList);
/*  270 */     model.addAttribute("tplList", tplList);
/*  271 */     model.addAttribute("groupList", groupList);
/*  272 */     model.addAttribute("typeList", typeList);
/*  273 */     if (cid != null) {
/*  274 */       model.addAttribute("cid", cid);
/*      */     }
/*  276 */     if (c != null) {
/*  277 */       model.addAttribute("channel", c);
/*      */     }
/*  279 */     return "content/add";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_view.do"})
/*      */   public String view(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer pageNo, Integer cid, Integer id, HttpServletRequest request, ModelMap model)
/*      */   {
/*  287 */     WebErrors errors = validateView(id, request);
/*  288 */     if (errors.hasErrors()) {
/*  289 */       return errors.showErrorPage(model);
/*      */     }
/*  291 */     CmsSite site = CmsUtils.getSite(request);
/*  292 */     CmsUser user = CmsUtils.getUser(request);
/*  293 */     byte currStep = user.getCheckStep(site.getId()).byteValue();
/*  294 */     Content content = this.manager.findById(id);
/*      */ 
/*  296 */     model.addAttribute("content", content);
/*  297 */     model.addAttribute("currStep", Byte.valueOf(currStep));
/*      */ 
/*  299 */     if (cid != null) {
/*  300 */       model.addAttribute("cid", cid);
/*      */     }
/*  302 */     String queryTitle = RequestUtils.getQueryParam(request, "queryTitle");
/*  303 */     String queryInputUsername = RequestUtils.getQueryParam(request, 
/*  304 */       "queryInputUsername");
/*  305 */     addAttibuteForQuery(model, queryTitle, queryInputUsername, queryStatus, 
/*  306 */       queryTypeId, queryTopLevel, queryRecommend, queryOrderBy, 
/*  307 */       pageNo);
/*  308 */     return "content/view";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_edit.do"})
/*      */   public String edit(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer pageNo, Integer cid, Integer id, HttpServletRequest request, ModelMap model)
/*      */   {
/*  316 */     WebErrors errors = validateEdit(id, request);
/*  317 */     if (errors.hasErrors()) {
/*  318 */       return errors.showErrorPage(model);
/*      */     }
/*  320 */     CmsSite site = CmsUtils.getSite(request);
/*  321 */     Integer siteId = site.getId();
/*  322 */     CmsUser user = CmsUtils.getUser(request);
/*      */ 
/*  324 */     Content content = this.manager.findById(id);
/*      */ 
/*  326 */     Channel channel = content.getChannel();
/*      */ 
/*  328 */     CmsModel m = content.getModel();
/*      */ 
/*  334 */     List itemList = this.cmsModelItemMng.getList(m.getId(), false, 
/*  335 */       false);
/*      */     Set rights;
/*  338 */     if (user.getUserSite(siteId).getAllChannel().booleanValue())
/*      */     {
/*  340 */       rights = null;
/*      */     }
/*  342 */     else rights = user.getChannels(siteId);
/*      */ 
/*  345 */     List topList = this.channelMng.getTopList(site.getId(), true);
/*  346 */     List channelList = Channel.getListForSelect(topList, rights, 
/*  347 */       true);
/*      */ 
/*  350 */     List topicList = this.cmsTopicMng
/*  351 */       .getListByChannel(channel.getId());
/*  352 */     Set<CmsTopic> topics = content.getTopics();
/*  353 */     for (CmsTopic t : topics) {
/*  354 */       if (!topicList.contains(t)) {
/*  355 */         topicList.add(t);
/*      */       }
/*      */     }
/*  358 */     Integer[] topicIds = CmsTopic.fetchIds(content.getTopics());
/*      */ 
/*  360 */     List tplList = getTplContent(site, m, content.getTplContent());
/*      */ 
/*  362 */     List groupList = this.cmsGroupMng.getList();
/*  363 */     Integer[] groupIds = CmsGroup.fetchIds(content.getViewGroups());
/*      */ 
/*  365 */     List typeList = this.contentTypeMng.getList(false);
/*      */ 
/*  367 */     int tplPathLength = site.getTplPath().length();
/*  368 */     String tplContent = content.getTplContent();
/*  369 */     if (!StringUtils.isBlank(tplContent)) {
/*  370 */       tplContent = tplContent.substring(tplPathLength);
/*      */     }
/*      */ 
/*  373 */     model.addAttribute("content", content);
/*  374 */     model.addAttribute("channel", channel);
/*  375 */     model.addAttribute("model", m);
/*  376 */     model.addAttribute("itemList", itemList);
/*  377 */     model.addAttribute("channelList", channelList);
/*  378 */     model.addAttribute("topicList", topicList);
/*  379 */     model.addAttribute("topicIds", topicIds);
/*  380 */     model.addAttribute("tplList", tplList);
/*  381 */     model.addAttribute("groupList", groupList);
/*  382 */     model.addAttribute("groupIds", groupIds);
/*  383 */     model.addAttribute("typeList", typeList);
/*  384 */     model.addAttribute("tplContent", tplContent);
/*  385 */     if (cid != null) {
/*  386 */       model.addAttribute("cid", cid);
/*      */     }
/*      */ 
/*  389 */     String queryTitle = RequestUtils.getQueryParam(request, "queryTitle");
/*  390 */     String queryInputUsername = RequestUtils.getQueryParam(request, 
/*  391 */       "queryInputUsername");
/*  392 */     addAttibuteForQuery(model, queryTitle, queryInputUsername, queryStatus, 
/*  393 */       queryTypeId, queryTopLevel, queryRecommend, queryOrderBy, 
/*  394 */       pageNo);
/*      */ 
/*  396 */     return "content/edit";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_save.do"})
/*      */   public String save(Content bean, ContentExt ext, ContentTxt txt, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] attachmentPaths, String[] attachmentNames, String[] attachmentFilenames, String[] picPaths, String[] picDescs, Integer channelId, Integer typeId, String tagStr, Boolean draft, Integer cid, Integer modelId, HttpServletRequest request, ModelMap model)
/*      */   {
/*  406 */     WebErrors errors = validateSave(bean, channelId, request);
/*  407 */     if (errors.hasErrors()) {
/*  408 */       return errors.showErrorPage(model);
/*      */     }
/*      */ 
/*  411 */     CmsSite site = CmsUtils.getSite(request);
/*  412 */     CmsUser user = CmsUtils.getUser(request);
/*  413 */     String tplPath = site.getTplPath();
/*  414 */     if (!StringUtils.isBlank(ext.getTplContent())) {
/*  415 */       ext.setTplContent(tplPath + ext.getTplContent());
/*      */     }
/*  417 */     bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
/*  418 */     String[] tagArr = StrUtils.splitAndTrim(tagStr, ",", 
/*  419 */       MessageResolver.getMessage(request, "content.tagStr.split", new Object[0]));
/*  420 */     bean = this.manager.save(bean, ext, txt, channelIds, topicIds, viewGroupIds, 
/*  421 */       tagArr, attachmentPaths, attachmentNames, attachmentFilenames, 
/*  422 */       picPaths, picDescs, channelId, typeId, draft, user, false);
/*      */ 
/*  424 */     this.fileMng.updateFileByPaths(attachmentPaths, picPaths, ext.getMediaPath(), ext.getTitleImg(), ext.getTypeImg(), ext.getContentImg(), Boolean.valueOf(true), bean);
/*  425 */     log.info("save Content id={}", bean.getId());
/*  426 */     this.cmsLogMng.operating(request, "content.log.save", "id=" + bean.getId() + 
/*  427 */       ";title=" + bean.getTitle());
/*  428 */     if (cid != null) {
/*  429 */       model.addAttribute("cid", cid);
/*      */     }
/*  431 */     model.addAttribute("message", "global.success");
/*  432 */     return add(cid, modelId, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_update.do"})
/*      */   public String update(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Content bean, ContentExt ext, ContentTxt txt, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] attachmentPaths, String[] attachmentNames, String[] attachmentFilenames, String[] picPaths, String[] picDescs, Integer channelId, Integer typeId, String tagStr, Boolean draft, Integer cid, String[] oldattachmentPaths, String[] oldpicPaths, String oldTitleImg, String oldContentImg, String oldTypeImg, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  447 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  448 */     if (errors.hasErrors()) {
/*  449 */       return errors.showErrorPage(model);
/*      */     }
/*      */ 
/*  452 */     CmsSite site = CmsUtils.getSite(request);
/*  453 */     CmsUser user = CmsUtils.getUser(request);
/*  454 */     String tplPath = site.getTplPath();
/*  455 */     if (!StringUtils.isBlank(ext.getTplContent())) {
/*  456 */       ext.setTplContent(tplPath + ext.getTplContent());
/*      */     }
/*  458 */     String[] tagArr = StrUtils.splitAndTrim(tagStr, ",", 
/*  459 */       MessageResolver.getMessage(request, "content.tagStr.split", new Object[0]));
/*  460 */     Map attr = RequestUtils.getRequestMap(request, "attr_");
/*  461 */     bean = this.manager.update(bean, ext, txt, tagArr, channelIds, topicIds, 
/*  462 */       viewGroupIds, attachmentPaths, attachmentNames, 
/*  463 */       attachmentFilenames, picPaths, picDescs, attr, channelId, 
/*  464 */       typeId, draft, user, false);
/*      */ 
/*  466 */     this.fileMng.updateFileByPaths(oldattachmentPaths, oldpicPaths, null, oldTitleImg, oldTypeImg, oldContentImg, Boolean.valueOf(false), bean);
/*      */ 
/*  468 */     this.fileMng.updateFileByPaths(attachmentPaths, picPaths, ext.getMediaPath(), ext.getTitleImg(), ext.getTypeImg(), ext.getContentImg(), Boolean.valueOf(true), bean);
/*  469 */     log.info("update Content id={}.", bean.getId());
/*  470 */     this.cmsLogMng.operating(request, "content.log.update", "id=" + bean.getId() + 
/*  471 */       ";title=" + bean.getTitle());
/*  472 */     return list(queryStatus, queryTypeId, queryTopLevel, queryRecommend, 
/*  473 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_delete.do"})
/*      */   public String delete(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  481 */     CmsSite site = CmsUtils.getSite(request);
/*  482 */     WebErrors errors = validateDelete(ids, request);
/*  483 */     if (errors.hasErrors()) {
/*  484 */       return errors.showErrorPage(model);
/*      */     }
/*      */ 
/*  488 */     if (site.getResycleOn().booleanValue()) {
/*  489 */       Content[] beans = this.manager.cycle(ids);
/*  490 */       for (Content bean : beans)
/*  491 */         log.info("delete to cycle, Content id={}", bean.getId());
/*      */     }
/*      */     else {
/*  494 */       for (Integer id : ids) {
/*  495 */         Content c = this.manager.findById(id);
/*      */ 
/*  497 */         this.manager.updateFileByContent(c, Boolean.valueOf(false));
/*      */       }
/*  499 */       Content[] beans = this.manager.deleteByIds(ids);
/*  500 */       for (Content bean : beans) {
/*  501 */         log.info("delete Content id={}", bean.getId());
/*  502 */         this.cmsLogMng.operating(request, "content.log.delete", "id=" + 
/*  503 */           bean.getId() + ";title=" + bean.getTitle());
/*      */       }
/*      */     }
/*  506 */     return (String)list(queryStatus, queryTypeId, queryTopLevel, queryRecommend, 
/*  507 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_check.do"})
/*      */   public String check(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  515 */     WebErrors errors = validateCheck(ids, request);
/*  516 */     if (errors.hasErrors()) {
/*  517 */       return errors.showErrorPage(model);
/*      */     }
/*  519 */     CmsUser user = CmsUtils.getUser(request);
/*  520 */     Content[] beans = this.manager.check(ids, user);
/*  521 */     for (Content bean : beans) {
/*  522 */       log.info("check Content id={}", bean.getId());
/*      */     }
/*  524 */     return list(queryStatus, queryTypeId, queryTopLevel, queryRecommend, 
/*  525 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_static.do"})
/*      */   public String contentStatic(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  533 */     WebErrors errors = validateStatic(ids, request);
/*  534 */     if (errors.hasErrors())
/*  535 */       return errors.showErrorPage(model);
/*      */     try
/*      */     {
/*  538 */       Content[] beans = this.manager.contentStatic(ids);
/*  539 */       for (Content bean : beans) {
/*  540 */         log.info("static Content id={}", bean.getId());
/*      */       }
/*  542 */       model.addAttribute("message", errors
/*  543 */         .getMessage("content.staticGenerated", new Object[] { Integer.valueOf(beans.length) }));
/*      */     } catch (TemplateNotFoundException e) {
/*  545 */       model.addAttribute("message", errors
/*  546 */         .getMessage(e.getMessage(), 
/*  546 */         new Object[] { e.getErrorTitle(), e.getGenerated() }));
/*      */     } catch (TemplateParseException e) {
/*  548 */       model.addAttribute("message", errors
/*  549 */         .getMessage(e.getMessage(), 
/*  549 */         new Object[] { e.getErrorTitle(), e.getGenerated() }));
/*      */     } catch (GeneratedZeroStaticPageException e) {
/*  551 */       model.addAttribute("message", errors
/*  552 */         .getMessage(e.getMessage(), new Object[] { 
/*  552 */         e.getGenerated() }));
/*      */     } catch (StaticPageNotOpenException e) {
/*  554 */       model.addAttribute("message", errors
/*  555 */         .getMessage(e.getMessage(), 
/*  555 */         new Object[] { e.getErrorTitle(), e.getGenerated() }));
/*      */     } catch (ContentNotCheckedException e) {
/*  557 */       model.addAttribute("message", errors
/*  558 */         .getMessage(e.getMessage(), 
/*  558 */         new Object[] { e.getErrorTitle(), e.getGenerated() }));
/*      */     }
/*  560 */     return list(queryStatus, queryTypeId, queryTopLevel, queryRecommend, 
/*  561 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_reject.do"})
/*      */   public String reject(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Byte rejectStep, String rejectOpinion, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  570 */     WebErrors errors = validateReject(ids, request);
/*  571 */     if (errors.hasErrors()) {
/*  572 */       return errors.showErrorPage(model);
/*      */     }
/*  574 */     CmsUser user = CmsUtils.getUser(request);
/*  575 */     Content[] beans = this.manager.reject(ids, user, rejectStep, rejectOpinion);
/*  576 */     for (Content bean : beans) {
/*  577 */       log.info("reject Content id={}", bean.getId());
/*      */     }
/*  579 */     return list(queryStatus, queryTypeId, queryTopLevel, queryRecommend, 
/*  580 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/v_tree_move.do"})
/*      */   public String move_tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*  586 */     log.debug("tree path={}", root);
/*      */     boolean isRoot;
/*  589 */     if ((StringUtils.isBlank(root)) || ("source".equals(root)))
/*  590 */       isRoot = true;
/*      */     else {
/*  592 */       isRoot = false;
/*      */     }
/*  594 */     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  595 */     WebErrors errors = validateTree(root, request);
/*  596 */     if (errors.hasErrors()) {
/*  597 */       log.error((String)errors.getErrors().get(0));
/*  598 */       ResponseUtils.renderJson(response, "[]");
/*  599 */       return null;
/*      */     }
/*  601 */     Integer siteId = CmsUtils.getSiteId(request);
/*  602 */     Integer userId = CmsUtils.getUserId(request);
/*      */     List list;
/*  604 */     if (isRoot)
/*  605 */       list = this.channelMng.getTopListByRigth(userId, siteId, true);
/*      */     else {
/*  607 */       list = this.channelMng.getChildListByRight(userId, siteId, 
/*  608 */         Integer.valueOf(Integer.parseInt(root)), true);
/*      */     }
/*  610 */     model.addAttribute("list", list);
/*  611 */     response.setHeader("Cache-Control", "no-cache");
/*  612 */     response.setContentType("text/json;charset=UTF-8");
/*  613 */     return "content/tree_move";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_move.do"})
/*      */   public void move(Integer[] contentIds, Integer channelId, HttpServletResponse response) throws JSONException {
/*  619 */     JSONObject json = new JSONObject();
/*  620 */     Boolean pass = Boolean.valueOf(true);
/*  621 */     if ((contentIds != null) && (channelId != null)) {
/*  622 */       Channel channel = this.channelMng.findById(channelId);
/*  623 */       for (Integer contentId : contentIds) {
/*  624 */         Content bean = this.manager.findById(contentId);
/*  625 */         if ((bean != null) && (channel != null)) {
/*  626 */           bean.setChannel(channel);
/*  627 */           this.manager.update(bean);
/*      */         }
/*      */       }
/*      */     }
/*  631 */     json.put("pass", pass);
/*  632 */     ResponseUtils.renderJson(response, json.toString());
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_upload_attachment.do"})
/*      */   public String uploadAttachment(@RequestParam(value="attachmentFile", required=false) MultipartFile file, String attachmentNum, HttpServletRequest request, ModelMap model)
/*      */   {
/*  639 */     WebErrors errors = validateUpload(file, request);
/*  640 */     if (errors.hasErrors()) {
/*  641 */       model.addAttribute("error", errors.getErrors().get(0));
/*  642 */       return "content/attachment_iframe";
/*      */     }
/*  644 */     CmsSite site = CmsUtils.getSite(request);
/*  645 */     String origName = file.getOriginalFilename();
/*  646 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/*  647 */       Locale.ENGLISH);
/*      */     try
/*      */     {
/*      */       String fileUrl;
/*  651 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/*  652 */         String dbFilePath = site.getConfig().getDbFileUri();
/*  653 */         fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/*  654 */           file.getInputStream());
/*      */ 
/*  656 */         fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/*  657 */       } else if (site.getUploadFtp() != null) {
/*  658 */         Ftp ftp = site.getUploadFtp();
/*  659 */         String ftpUrl = ftp.getUrl();
/*  660 */         fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/*  661 */           file.getInputStream());
/*      */ 
/*  663 */         fileUrl = ftpUrl + fileUrl;
/*      */       } else {
/*  665 */         String ctx = request.getContextPath();
/*  666 */         fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), ext, 
/*  667 */           file);
/*      */ 
/*  669 */         fileUrl = ctx + fileUrl;
/*      */       }
/*  671 */       this.fileMng.saveFileByPath(fileUrl, origName, Boolean.valueOf(false));
/*  672 */       model.addAttribute("attachmentPath", fileUrl);
/*  673 */       model.addAttribute("attachmentName", origName);
/*  674 */       model.addAttribute("attachmentNum", attachmentNum);
/*      */     } catch (IllegalStateException e) {
/*  676 */       model.addAttribute("error", e.getMessage());
/*  677 */       log.error("upload file error!", e);
/*      */     } catch (IOException e) {
/*  679 */       model.addAttribute("error", e.getMessage());
/*  680 */       log.error("upload file error!", e);
/*      */     }
/*  682 */     return "content/attachment_iframe";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content/o_upload_media.do"})
/*      */   public String uploadMedia(@RequestParam(value="mediaFile", required=false) MultipartFile file, String filename, HttpServletRequest request, ModelMap model)
/*      */   {
/*  689 */     WebErrors errors = validateUpload(file, request);
/*  690 */     if (errors.hasErrors()) {
/*  691 */       model.addAttribute("error", errors.getErrors().get(0));
/*  692 */       return "content/media_iframe";
/*      */     }
/*  694 */     CmsSite site = CmsUtils.getSite(request);
/*  695 */     String origName = file.getOriginalFilename();
/*  696 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/*  697 */       Locale.ENGLISH);
/*      */     try
/*      */     {
/*      */       String fileUrl;
/*  701 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/*  702 */         String dbFilePath = site.getConfig().getDbFileUri();
/*  703 */         if ((!StringUtils.isBlank(filename)) && 
/*  704 */           (FilenameUtils.getExtension(filename).equals(ext))) {
/*  705 */           filename = filename.substring(dbFilePath.length());
/*  706 */           fileUrl = this.dbFileMng.storeByFilename(filename, 
/*  707 */             file.getInputStream());
/*      */         } else {
/*  709 */           fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/*  710 */             file.getInputStream());
/*      */ 
/*  712 */           fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/*      */         }
/*  714 */       } else if (site.getUploadFtp() != null) {
/*  715 */         Ftp ftp = site.getUploadFtp();
/*  716 */         String ftpUrl = ftp.getUrl();
/*  717 */         if ((!StringUtils.isBlank(filename)) && 
/*  718 */           (FilenameUtils.getExtension(filename).equals(ext))) {
/*  719 */           filename = filename.substring(ftpUrl.length());
/*  720 */           fileUrl = ftp.storeByFilename(filename, 
/*  721 */             file.getInputStream());
/*      */         } else {
/*  723 */           fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/*  724 */             file.getInputStream());
/*      */ 
/*  726 */           fileUrl = ftpUrl + fileUrl;
/*      */         }
/*      */       } else {
/*  729 */         String ctx = request.getContextPath();
/*  730 */         if ((!StringUtils.isBlank(filename)) && 
/*  731 */           (FilenameUtils.getExtension(filename).equals(ext))) {
/*  732 */           filename = filename.substring(ctx.length());
/*  733 */           fileUrl = this.fileRepository.storeByFilename(filename, file);
/*      */         } else {
/*  735 */           fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), 
/*  736 */             ext, file);
/*      */ 
/*  738 */           fileUrl = ctx + fileUrl;
/*      */         }
/*      */       }
/*  741 */       this.fileMng.saveFileByPath(fileUrl, fileUrl, Boolean.valueOf(false));
/*  742 */       model.addAttribute("mediaPath", fileUrl);
/*  743 */       model.addAttribute("mediaExt", ext);
/*      */     } catch (IllegalStateException e) {
/*  745 */       model.addAttribute("error", e.getMessage());
/*  746 */       log.error("upload file error!", e);
/*      */     } catch (IOException e) {
/*  748 */       model.addAttribute("error", e.getMessage());
/*  749 */       log.error("upload file error!", e);
/*      */     }
/*  751 */     return "content/media_iframe";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content_cycle/v_list.do"})
/*      */   public String cycleList(Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  759 */     list(Content.ContentStatus.recycle.toString(), queryTypeId, queryTopLevel, 
/*  760 */       queryRecommend, queryOrderBy, cid, pageNo, request, model);
/*  761 */     return "content/cycle_list";
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content_cycle/o_recycle.do"})
/*      */   public String cycleRecycle(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  769 */     WebErrors errors = validateDelete(ids, request);
/*  770 */     if (errors.hasErrors()) {
/*  771 */       return errors.showErrorPage(model);
/*      */     }
/*  773 */     Content[] beans = this.manager.recycle(ids);
/*  774 */     for (Content bean : beans) {
/*  775 */       log.info("delete Content id={}", bean.getId());
/*      */     }
/*  777 */     return cycleList(queryTypeId, queryTopLevel, queryRecommend, 
/*  778 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */ 
/*      */   @RequestMapping({"/content_cycle/o_delete.do"})
/*      */   public String cycleDelete(String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer[] ids, Integer cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      */   {
/*  786 */     WebErrors errors = validateDelete(ids, request);
/*  787 */     if (errors.hasErrors())
/*  788 */       return errors.showErrorPage(model);
/*      */     Content c;
/*  790 */     for (Integer id : ids) {
/*  791 */       c = this.manager.findById(id);
/*      */ 
/*  793 */       this.manager.updateFileByContent(c, Boolean.valueOf(false));
/*      */     }
/*  795 */     Content[] beans = this.manager.deleteByIds(ids);
/*  796 */     for (Content bean : beans) {
/*  797 */       log.info("delete Content id={}", bean.getId());
/*      */     }
/*  799 */     return cycleList(queryTypeId, queryTopLevel, queryRecommend, 
/*  800 */       queryOrderBy, cid, pageNo, request, model);
/*      */   }
/*      */   @RequestMapping({"/content/o_generateTags.do"})
/*      */   public void generateTags(String title, HttpServletResponse response) throws JSONException {
/*  805 */     JSONObject json = new JSONObject();
/*  806 */     String tags = "";
/*  807 */     if (StringUtils.isNotBlank(title)) {
/*  808 */       tags = StrUtils.getKeywords(title, true);
/*      */     }
/*  810 */     json.put("tags", tags);
/*  811 */     ResponseUtils.renderJson(response, json.toString());
/*      */   }
/*      */ 
/*      */   private void addAttibuteForQuery(ModelMap model, String queryTitle, String queryInputUsername, String queryStatus, Integer queryTypeId, Boolean queryTopLevel, Boolean queryRecommend, Integer queryOrderBy, Integer pageNo)
/*      */   {
/*  819 */     if (!StringUtils.isBlank(queryTitle)) {
/*  820 */       model.addAttribute("queryTitle", queryTitle);
/*      */     }
/*  822 */     if (!StringUtils.isBlank(queryInputUsername)) {
/*  823 */       model.addAttribute("queryInputUsername", queryInputUsername);
/*      */     }
/*  825 */     if (queryTypeId != null) {
/*  826 */       model.addAttribute("queryTypeId", queryTypeId);
/*      */     }
/*  828 */     if (queryStatus != null) {
/*  829 */       model.addAttribute("queryStatus", queryStatus);
/*      */     }
/*  831 */     if (queryTopLevel != null) {
/*  832 */       model.addAttribute("queryTopLevel", queryTopLevel);
/*      */     }
/*  834 */     if (queryRecommend != null) {
/*  835 */       model.addAttribute("queryRecommend", queryRecommend);
/*      */     }
/*  837 */     if (queryOrderBy != null) {
/*  838 */       model.addAttribute("queryOrderBy", queryOrderBy);
/*      */     }
/*  840 */     if (pageNo != null)
/*  841 */       model.addAttribute("pageNo", pageNo);
/*      */   }
/*      */ 
/*      */   private List<String> getTplContent(CmsSite site, CmsModel model, String tpl)
/*      */   {
/*  846 */     String sol = site.getSolutionPath();
/*  847 */     String tplPath = site.getTplPath();
/*  848 */     List tplList = this.tplManager.getNameListByPrefix(
/*  849 */       model.getTplContent(sol, false));
/*  850 */     tplList = CoreUtils.tplTrim(tplList, tplPath, tpl, new String[0]);
/*  851 */     return tplList;
/*      */   }
/*      */ 
/*      */   private WebErrors validateTree(String path, HttpServletRequest request)
/*      */   {
/*  857 */     WebErrors errors = WebErrors.create(request);
/*      */ 
/*  861 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateAdd(Integer cid, Integer modelId, HttpServletRequest request) {
/*  865 */     WebErrors errors = WebErrors.create(request);
/*  866 */     if (cid == null) {
/*  867 */       return errors;
/*      */     }
/*  869 */     Channel c = this.channelMng.findById(cid);
/*  870 */     if (errors.ifNotExist(c, Channel.class, cid)) {
/*  871 */       return errors;
/*      */     }
/*      */ 
/*  874 */     if (modelId != null) {
/*  875 */       CmsModel m = this.cmsModelMng.findById(modelId);
/*  876 */       if (errors.ifNotExist(m, CmsModel.class, modelId)) {
/*  877 */         return errors;
/*      */       }
/*      */ 
/*  880 */       if ((c.getModelIds().size() > 0) && (!c.getModelIds().contains(modelId.toString()))) {
/*  881 */         errors.addErrorCode("channel.modelError", new Object[] { c.getName(), m.getName() });
/*      */       }
/*      */     }
/*  884 */     Integer siteId = CmsUtils.getSiteId(request);
/*  885 */     if (!c.getSite().getId().equals(siteId)) {
/*  886 */       errors.notInSite(Channel.class, cid);
/*  887 */       return errors;
/*      */     }
/*  889 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateSave(Content bean, Integer channelId, HttpServletRequest request)
/*      */   {
/*  894 */     WebErrors errors = WebErrors.create(request);
/*  895 */     CmsSite site = CmsUtils.getSite(request);
/*  896 */     bean.setSite(site);
/*  897 */     if (errors.ifNull(channelId, "channelId")) {
/*  898 */       return errors;
/*      */     }
/*  900 */     Channel channel = this.channelMng.findById(channelId);
/*  901 */     if (errors.ifNotExist(channel, Channel.class, channelId)) {
/*  902 */       return errors;
/*      */     }
/*  904 */     if (channel.getChild().size() > 0) {
/*  905 */       errors.addErrorCode("content.error.notLeafChannel");
/*      */     }
/*      */ 
/*  908 */     if (bean.getModel().getId() != null) {
/*  909 */       CmsModel m = bean.getModel();
/*  910 */       if (errors.ifNotExist(m, CmsModel.class, bean.getModel().getId())) {
/*  911 */         return errors;
/*      */       }
/*      */ 
/*  914 */       if ((channel.getModelIds().size() > 0) && (!channel.getModelIds().contains(bean.getModel().getId().toString()))) {
/*  915 */         errors.addErrorCode("channel.modelError", new Object[] { channel.getName(), m.getName() });
/*      */       }
/*      */     }
/*  918 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateView(Integer id, HttpServletRequest request) {
/*  922 */     WebErrors errors = WebErrors.create(request);
/*  923 */     CmsSite site = CmsUtils.getSite(request);
/*  924 */     if (vldExist(id, site.getId(), errors)) {
/*  925 */       return errors;
/*      */     }
/*  927 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/*  931 */     WebErrors errors = WebErrors.create(request);
/*  932 */     CmsSite site = CmsUtils.getSite(request);
/*  933 */     if (vldExist(id, site.getId(), errors)) {
/*  934 */       return errors;
/*      */     }
/*      */ 
/*  938 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/*  942 */     WebErrors errors = WebErrors.create(request);
/*  943 */     CmsSite site = CmsUtils.getSite(request);
/*  944 */     if (vldExist(id, site.getId(), errors)) {
/*  945 */       return errors;
/*      */     }
/*  947 */     Content content = this.manager.findById(id);
/*      */ 
/*  950 */     if (!content.isHasUpdateRight()) {
/*  951 */       errors.addErrorCode("content.error.afterCheckUpdate");
/*  952 */       return errors;
/*      */     }
/*  954 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/*  958 */     WebErrors errors = WebErrors.create(request);
/*  959 */     CmsSite site = CmsUtils.getSite(request);
/*  960 */     errors.ifEmpty(ids, "ids");
/*  961 */     for (Integer id : ids) {
/*  962 */       if (vldExist(id, site.getId(), errors)) {
/*  963 */         return errors;
/*      */       }
/*  965 */       Content content = this.manager.findById(id);
/*      */ 
/*  968 */       if (!content.isHasDeleteRight()) {
/*  969 */         errors.addErrorCode("content.error.afterCheckDelete");
/*  970 */         return errors;
/*      */       }
/*      */     }
/*      */ 
/*  974 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateCheck(Integer[] ids, HttpServletRequest request) {
/*  978 */     WebErrors errors = WebErrors.create(request);
/*  979 */     CmsSite site = CmsUtils.getSite(request);
/*  980 */     errors.ifEmpty(ids, "ids");
/*  981 */     for (Integer id : ids) {
/*  982 */       vldExist(id, site.getId(), errors);
/*      */     }
/*  984 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateStatic(Integer[] ids, HttpServletRequest request) {
/*  988 */     WebErrors errors = WebErrors.create(request);
/*  989 */     CmsSite site = CmsUtils.getSite(request);
/*  990 */     errors.ifEmpty(ids, "ids");
/*  991 */     for (Integer id : ids) {
/*  992 */       vldExist(id, site.getId(), errors);
/*      */     }
/*  994 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateReject(Integer[] ids, HttpServletRequest request) {
/*  998 */     WebErrors errors = WebErrors.create(request);
/*  999 */     CmsSite site = CmsUtils.getSite(request);
/* 1000 */     errors.ifEmpty(ids, "ids");
/* 1001 */     for (Integer id : ids) {
/* 1002 */       vldExist(id, site.getId(), errors);
/*      */     }
/* 1004 */     return errors;
/*      */   }
/*      */ 
/*      */   private WebErrors validateUpload(MultipartFile file, HttpServletRequest request)
/*      */   {
/* 1009 */     WebErrors errors = WebErrors.create(request);
/* 1010 */     if (errors.ifNull(file, "file")) {
/* 1011 */       return errors;
/*      */     }
/* 1013 */     return errors;
/*      */   }
/*      */ 
/*      */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 1017 */     if (errors.ifNull(id, "id")) {
/* 1018 */       return true;
/*      */     }
/* 1020 */     Content entity = this.manager.findById(id);
/* 1021 */     if (errors.ifNotExist(entity, Content.class, id)) {
/* 1022 */       return true;
/*      */     }
/* 1024 */     if (!entity.getSite().getId().equals(siteId)) {
/* 1025 */       errors.notInSite(Content.class, id);
/* 1026 */       return true;
/*      */     }
/* 1028 */     return false;
/*      */   }
/*      */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.ContentAct
 * JD-Core Version:    0.6.0
 */