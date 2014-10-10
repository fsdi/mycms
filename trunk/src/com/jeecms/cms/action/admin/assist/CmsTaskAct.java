/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsTask;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionMng;
/*     */ import com.jeecms.cms.manager.assist.CmsTaskMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.core.manager.FtpMng;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsTaskAct
/*     */ {
/*  44 */   private static final Logger log = LoggerFactory.getLogger(CmsTaskAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private Scheduler scheduler;
/*     */ 
/*     */   @Autowired
/*     */   private CmsTaskMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private FtpMng ftpMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAcquisitionMng acquisitionMng;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*  48 */   @RequestMapping({"/task/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(CmsUtils.getSiteId(request), SimplePage.cpn(pageNo), 
/*  49 */       CookieUtils.getPageSize(request));
/*  50 */     model.addAttribute("pagination", pagination);
/*  51 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  52 */     return "task/list"; }
/*     */ 
/*     */   @RequestMapping({"/task/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  57 */     List ftpList = this.ftpMng.getList();
/*  58 */     List acquList = this.acquisitionMng.getList(CmsUtils.getSiteId(request));
/*     */ 
/*  60 */     CmsSite site = CmsUtils.getSite(request);
/*  61 */     List topList = this.channelMng.getTopList(site.getId(), false);
/*  62 */     List channelList = Channel.getListForSelect(topList, null, 
/*  63 */       null, false);
/*  64 */     model.addAttribute("ftpList", ftpList);
/*  65 */     model.addAttribute("acquList", acquList);
/*  66 */     model.addAttribute("channelList", channelList);
/*  67 */     return "task/add";
/*     */   }
/*     */   @RequestMapping({"/task/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  72 */     WebErrors errors = validateEdit(id, request);
/*  73 */     if (errors.hasErrors()) {
/*  74 */       return errors.showErrorPage(model);
/*     */     }
/*  76 */     CmsTask task = this.manager.findById(id);
/*  77 */     List ftpList = this.ftpMng.getList();
/*  78 */     List acquList = this.acquisitionMng.getList(CmsUtils.getSiteId(request));
/*     */ 
/*  80 */     CmsSite site = CmsUtils.getSite(request);
/*  81 */     List topList = this.channelMng.getTopList(site.getId(), false);
/*  82 */     List channelList = Channel.getListForSelect(topList, null, null, false);
/*  83 */     Map params = task.getAttr();
/*     */ 
/*  85 */     List folders = getFolderParams(params);
/*  86 */     model.addAttribute("ftpList", ftpList);
/*  87 */     model.addAttribute("acquList", acquList);
/*  88 */     model.addAttribute("channelList", channelList);
/*  89 */     model.addAttribute("task", task);
/*  90 */     model.addAttribute("folders", folders);
/*  91 */     model.addAttribute("pageNo", pageNo);
/*  92 */     return "task/edit";
/*     */   }
/*     */   @RequestMapping({"/task/o_save.do"})
/*     */   public String save(CmsTask bean, HttpServletRequest request, ModelMap model) throws ParseException, SchedulerException, ClassNotFoundException {
/*  97 */     WebErrors errors = validateSave(bean, request);
/*  98 */     if (errors.hasErrors()) {
/*  99 */       return errors.showErrorPage(model);
/*     */     }
/* 101 */     bean.init(CmsUtils.getSite(request), CmsUtils.getUser(request));
/* 102 */     Map attrs = RequestUtils.getRequestMap(request, "attr_");
/* 103 */     UUID uuid = UUID.randomUUID();
/* 104 */     bean.setTaskCode(uuid.toString());
/*     */ 
/* 106 */     String siteId = CmsUtils.getSite(request).getId().toString();
/* 107 */     if (bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_INDEX))) {
/* 108 */       attrs.put(CmsTask.TASK_PARAM_SITE_ID, siteId);
/* 109 */       bean.setAttr(attrs);
/* 110 */     } else if ((bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_CHANNEL))) || (bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_CONTENT)))) {
/* 111 */       attrs.put(CmsTask.TASK_PARAM_SITE_ID, siteId);
/*     */     }
/* 113 */     bean.setAttr(attrs);
/* 114 */     bean = this.manager.save(bean);
/*     */ 
/* 116 */     if (bean.getEnable()) {
/* 117 */       startTask(bean, uuid.toString());
/*     */     }
/* 119 */     log.info("save CmsTask id={}", bean.getId());
/* 120 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/task/o_update.do"})
/*     */   public String update(CmsTask bean, Integer pageNo, HttpServletRequest request, ModelMap model) throws SchedulerException, ParseException, ClassNotFoundException
/*     */   {
/* 127 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 128 */     if (errors.hasErrors()) {
/* 129 */       return errors.showErrorPage(model);
/*     */     }
/* 131 */     if (bean.getExecycle().equals(Integer.valueOf(CmsTask.EXECYCLE_DEFINE)))
/* 132 */       bean.setCronExpression(null);
/* 133 */     else if (bean.getExecycle().equals(Integer.valueOf(CmsTask.EXECYCLE_CRON))) {
/* 134 */       bean.setIntervalUnit(null);
/*     */     }
/* 136 */     Map attr = RequestUtils.getRequestMap(request, "attr_");
/*     */ 
/* 138 */     String siteId = CmsUtils.getSite(request).getId().toString();
/* 139 */     if (bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_INDEX))) {
/* 140 */       attr.put(CmsTask.TASK_PARAM_SITE_ID, siteId);
/* 141 */       bean.setAttr(attr);
/* 142 */     } else if ((bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_CHANNEL))) || (bean.getType().equals(Integer.valueOf(CmsTask.TASK_STATIC_CONTENT)))) {
/* 143 */       attr.put(CmsTask.TASK_PARAM_SITE_ID, siteId);
/*     */     }
/*     */ 
/* 146 */     String oldTaskCode = this.manager.findById(bean.getId()).getTaskCode();
/* 147 */     endTask(oldTaskCode);
/* 148 */     UUID uuid = UUID.randomUUID();
/* 149 */     bean.setTaskCode(uuid.toString());
/* 150 */     bean = this.manager.update(bean, attr);
/* 151 */     if (bean.getEnable()) {
/* 152 */       startTask(bean, uuid.toString());
/*     */     }
/* 154 */     log.info("update CmsTask id={}.", bean.getId());
/* 155 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/task/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) throws SchedulerException {
/* 161 */     WebErrors errors = validateDelete(ids, request);
/* 162 */     if (errors.hasErrors()) {
/* 163 */       return errors.showErrorPage(model);
/*     */     }
/* 165 */     CmsTask[] beans = this.manager.deleteByIds(ids);
/* 166 */     for (CmsTask bean : beans)
/*     */     {
/* 168 */       endTask(bean.getTaskCode());
/* 169 */       log.info("delete CmsTask id={}", bean.getId());
/*     */     }
/* 171 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private void startTask(CmsTask task, String taskCode)
/*     */     throws ParseException, SchedulerException, ClassNotFoundException
/*     */   {
/* 183 */     String cronExpress = this.manager.getCronExpressionFromDB(task.getId());
/* 184 */     System.out.println(cronExpress);
/* 185 */     if (cronExpress.indexOf("null") == -1) {
/* 186 */       JobDetail jobDetail = new JobDetail();
/* 187 */       jobDetail.setName(taskCode);
/* 188 */       jobDetail.setGroup("DEFAULT");
/* 189 */       jobDetail.setJobClass(getClassByTask(task.getJobClass()));
/*     */ 
/* 191 */       jobDetail.setJobDataMap(getJobDataMap(task.getAttr()));
/* 192 */       CronTrigger cronTrigger = new CronTrigger("cron_" + taskCode, "DEFAULT", jobDetail.getName(), "DEFAULT");
/* 193 */       cronTrigger.setCronExpression(cronExpress);
/* 194 */       this.scheduler.scheduleJob(jobDetail, cronTrigger);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void endTask(String taskName)
/*     */     throws SchedulerException
/*     */   {
/* 203 */     this.scheduler.deleteJob(taskName, "DEFAULT");
/*     */   }
/*     */ 
/*     */   private JobDataMap getJobDataMap(Map<String, String> params) {
/* 207 */     JobDataMap jdm = new JobDataMap();
/* 208 */     Set keySet = params.keySet();
/* 209 */     Iterator it = keySet.iterator();
/* 210 */     while (it.hasNext()) {
/* 211 */       String key = (String)it.next();
/* 212 */       jdm.put(key, (String)params.get(key));
/*     */     }
/* 214 */     return jdm;
/*     */   }
/*     */ 
/*     */   private Class getClassByTask(String taskClassName) throws ClassNotFoundException
/*     */   {
/* 219 */     return Class.forName(taskClassName);
/*     */   }
/*     */ 
/*     */   private List<String> getFolderParams(Map<String, String> params) {
/* 223 */     List folders = new ArrayList();
/* 224 */     Set keySet = params.keySet();
/* 225 */     Iterator it = keySet.iterator();
/* 226 */     while (it.hasNext()) {
/* 227 */       String key = (String)it.next();
/* 228 */       if (key.startsWith(CmsTask.TASK_PARAM_FOLDER_PREFIX)) {
/* 229 */         folders.add((String)params.get(key));
/*     */       }
/*     */     }
/* 232 */     return folders;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsTask bean, HttpServletRequest request) {
/* 236 */     WebErrors errors = WebErrors.create(request);
/* 237 */     CmsSite site = CmsUtils.getSite(request);
/* 238 */     bean.setSite(site);
/* 239 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 243 */     WebErrors errors = WebErrors.create(request);
/* 244 */     CmsSite site = CmsUtils.getSite(request);
/* 245 */     if (vldExist(id, site.getId(), errors)) {
/* 246 */       return errors;
/*     */     }
/* 248 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 252 */     WebErrors errors = WebErrors.create(request);
/* 253 */     CmsSite site = CmsUtils.getSite(request);
/* 254 */     if (vldExist(id, site.getId(), errors)) {
/* 255 */       return errors;
/*     */     }
/* 257 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 261 */     WebErrors errors = WebErrors.create(request);
/* 262 */     CmsSite site = CmsUtils.getSite(request);
/* 263 */     if (errors.ifEmpty(ids, "ids")) {
/* 264 */       return errors;
/*     */     }
/* 266 */     for (Integer id : ids) {
/* 267 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 269 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 273 */     if (errors.ifNull(id, "id")) {
/* 274 */       return true;
/*     */     }
/* 276 */     CmsTask entity = this.manager.findById(id);
/* 277 */     if (errors.ifNotExist(entity, CmsTask.class, id)) {
/* 278 */       return true;
/*     */     }
/* 280 */     if (!entity.getSite().getId().equals(siteId)) {
/* 281 */       errors.notInSite(CmsTask.class, id);
/* 282 */       return true;
/*     */     }
/* 284 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsTaskAct
 * JD-Core Version:    0.6.0
 */