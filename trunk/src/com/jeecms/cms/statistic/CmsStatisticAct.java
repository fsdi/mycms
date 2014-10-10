/*     */ package com.jeecms.cms.statistic;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsStatisticAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsStatisticSvc cmsStatisticSvc;
/*     */ 
/*     */   @RequestMapping({"/statistic_member/v_list.do"})
/*     */   public String memberList(String queryModel, Integer year, Integer month, Integer day, ModelMap model)
/*     */   {
/*  50 */     CmsStatistic.CmsStatisticModel statisticModel = getStatisticModel(queryModel);
/*  51 */     List list = this.cmsStatisticSvc.statisticByModel(1, 
/*  52 */       statisticModel, year, month, day, null);
/*  53 */     putCommonData(statisticModel, list, year, month, day, model);
/*  54 */     return "statistic/member/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/statistic_content/v_list.do"})
/*     */   public String contentList(HttpServletRequest request, String queryModel, Integer channelId, Integer year, Integer month, Integer day, ModelMap model)
/*     */   {
/*  61 */     String queryInputUsername = RequestUtils.getQueryParam(request, 
/*  62 */       "queryInputUsername");
/*  63 */     Integer queryInputUserId = null;
/*  64 */     if (!StringUtils.isBlank(queryInputUsername)) {
/*  65 */       CmsUser u = this.cmsUserMng.findByUsername(queryInputUsername);
/*  66 */       if (u != null) {
/*  67 */         queryInputUserId = u.getId();
/*     */       }
/*     */       else {
/*  70 */         queryInputUsername = null;
/*     */       }
/*     */     }
/*  73 */     Map restrictions = new HashMap();
/*  74 */     Integer siteId = CmsUtils.getSiteId(request);
/*  75 */     restrictions.put("siteId", siteId);
/*  76 */     restrictions.put("userId", queryInputUserId);
/*  77 */     restrictions.put("channelId", channelId);
/*  78 */     CmsStatistic.CmsStatisticModel statisticModel = getStatisticModel(queryModel);
/*  79 */     List list = this.cmsStatisticSvc.statisticByModel(2, 
/*  80 */       statisticModel, year, month, day, restrictions);
/*  81 */     List topList = this.channelMng.getTopList(siteId, true);
/*  82 */     List channelList = Channel.getListForSelect(topList, null, 
/*  83 */       true);
/*  84 */     putCommonData(statisticModel, list, year, month, day, model);
/*  85 */     model.addAttribute("queryInputUsername", queryInputUsername);
/*  86 */     model.addAttribute("channelList", channelList);
/*  87 */     model.addAttribute("channelId", channelId);
/*  88 */     return "statistic/content/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/statistic_comment/v_list.do"})
/*     */   public String commentList(HttpServletRequest request, String queryModel, Integer year, Integer month, Integer day, Boolean isReplyed, ModelMap model)
/*     */   {
/*  95 */     Map restrictions = new HashMap();
/*  96 */     Integer siteId = CmsUtils.getSiteId(request);
/*  97 */     restrictions.put("siteId", siteId);
/*  98 */     restrictions.put("isReplyed", isReplyed);
/*  99 */     CmsStatistic.CmsStatisticModel statisticModel = getStatisticModel(queryModel);
/* 100 */     List list = this.cmsStatisticSvc.statisticByModel(3, 
/* 101 */       statisticModel, year, month, day, restrictions);
/* 102 */     putCommonData(statisticModel, list, year, month, day, model);
/* 103 */     model.addAttribute("isReplyed", isReplyed);
/* 104 */     return "statistic/comment/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/statistic_guestbook/v_list.do"})
/*     */   public String guestbookList(HttpServletRequest request, String queryModel, Integer year, Integer month, Integer day, Boolean isReplyed, ModelMap model)
/*     */   {
/* 111 */     Map restrictions = new HashMap();
/* 112 */     Integer siteId = CmsUtils.getSiteId(request);
/* 113 */     restrictions.put("siteId", siteId);
/* 114 */     restrictions.put("isReplyed", isReplyed);
/* 115 */     CmsStatistic.CmsStatisticModel statisticModel = getStatisticModel(queryModel);
/* 116 */     List list = this.cmsStatisticSvc.statisticByModel(4, 
/* 117 */       statisticModel, year, month, day, restrictions);
/* 118 */     putCommonData(statisticModel, list, year, month, day, model);
/* 119 */     model.addAttribute("isReplyed", isReplyed);
/* 120 */     return "statistic/guestbook/list";
/*     */   }
/*     */   @RequestMapping({"/flow_pv/v_list.do"})
/*     */   public String pageViewList(HttpServletRequest request, ModelMap model) {
/* 125 */     Integer siteId = CmsUtils.getSiteId(request);
/* 126 */     List list = this.cmsStatisticSvc.flowStatistic(11, siteId);
/* 127 */     model.addAttribute("list", list);
/* 128 */     return "statistic/pv/list";
/*     */   }
/*     */   @RequestMapping({"/flow_uniqueIp/v_list.do"})
/*     */   public String uniqueIpList(HttpServletRequest request, ModelMap model) {
/* 133 */     Integer siteId = CmsUtils.getSiteId(request);
/* 134 */     List list = this.cmsStatisticSvc.flowStatistic(12, 
/* 135 */       siteId);
/* 136 */     model.addAttribute("list", list);
/* 137 */     return "statistic/uniqueIp/list";
/*     */   }
/*     */   @RequestMapping({"/flow_uniqueVisitor/v_list.do"})
/*     */   public String uniqueVisitorList(HttpServletRequest request, ModelMap model) {
/* 142 */     Integer siteId = CmsUtils.getSiteId(request);
/* 143 */     List list = this.cmsStatisticSvc.flowStatistic(13, 
/* 144 */       siteId);
/* 145 */     model.addAttribute("list", list);
/* 146 */     return "statistic/uniqueVisitor/list";
/*     */   }
/*     */   @RequestMapping({"/flow_avgViews/v_list.do"})
/*     */   public String avgViewsList(HttpServletRequest request, ModelMap model) {
/* 151 */     Integer siteId = CmsUtils.getSiteId(request);
/* 152 */     List list = this.cmsStatisticSvc.flowStatistic(14, 
/* 153 */       siteId);
/* 154 */     model.addAttribute("list", list);
/* 155 */     return "statistic/avgViews/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/flow_refererWebSite/v_list.do"})
/*     */   public String refererWebSite(HttpServletRequest request, Integer pageNo, ModelMap model)
/*     */   {
/* 162 */     Integer siteId = CmsUtils.getSiteId(request);
/* 163 */     Pagination pagination = this.cmsStatisticSvc.flowAnalysisPage(
/* 164 */       "refererWebSite", siteId, Integer.valueOf(SimplePage.cpn(pageNo)), 
/* 165 */       Integer.valueOf(CookieUtils.getPageSize(request)));
/* 166 */     model.addAttribute("pagination", pagination);
/* 167 */     model.addAttribute("total", getTotal((List<CmsStatistic>)pagination.getList()));
/* 169 */     return "statistic/refererWebSite/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/flow_refererPage/v_list.do"})
/*     */   public String refererPage(HttpServletRequest request, Integer pageNo, ModelMap model)
/*     */   {
/* 176 */     Integer siteId = CmsUtils.getSiteId(request);
/* 177 */     Pagination pagination = this.cmsStatisticSvc.flowAnalysisPage("refererPage", 
/* 178 */       siteId, Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(CookieUtils.getPageSize(request)));
/* 179 */     model.addAttribute("pagination", pagination);
/* 180 */     model.addAttribute("total", 
/* 181 */       getTotal((List<CmsStatistic>)pagination.getList()));
/* 182 */     return "statistic/refererPage/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/flow_accessPage/v_list.do"})
/*     */   public String accessPage(HttpServletRequest request, Integer pageNo, ModelMap model)
/*     */   {
/* 189 */     Integer siteId = CmsUtils.getSiteId(request);
/* 190 */     Pagination pagination = this.cmsStatisticSvc.flowAnalysisPage("accessPage", 
/* 191 */       siteId, Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(CookieUtils.getPageSize(request)));
/* 192 */     model.addAttribute("pagination", pagination);
/* 193 */     model.addAttribute("total", 
/* 194 */       getTotal((List<CmsStatistic>)pagination.getList()));
/* 195 */     return "statistic/accessPage/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/flow_area/v_list.do"})
/*     */   public String area(HttpServletRequest request, Integer pageNo, ModelMap model)
/*     */   {
/* 202 */     Integer siteId = CmsUtils.getSiteId(request);
/* 203 */     Pagination pagination = this.cmsStatisticSvc.flowAnalysisPage("area", siteId, 
/* 204 */       Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(CookieUtils.getPageSize(request)));
/* 205 */     model.addAttribute("pagination", pagination);
/* 206 */     model.addAttribute("total", 
/* 207 */       getTotal((List<CmsStatistic>)pagination.getList()));
/* 208 */     return "statistic/area/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/flow_refererKeyword/v_list.do"})
/*     */   public String refererKeyword(HttpServletRequest request, Integer pageNo, ModelMap model)
/*     */   {
/* 215 */     Integer siteId = CmsUtils.getSiteId(request);
/* 216 */     Pagination pagination = this.cmsStatisticSvc.flowAnalysisPage(
/* 217 */       "refererKeyword", siteId, Integer.valueOf(SimplePage.cpn(pageNo)), 
/* 218 */       Integer.valueOf(CookieUtils.getPageSize(request)));
/* 219 */     model.addAttribute("pagination", pagination);
/* 220 */     model.addAttribute("total", 
/* 221 */       getTotal((List<CmsStatistic>)pagination.getList()));
/* 222 */     return "statistic/refererKeyword/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/statistic_flow/v_init.do"})
/*     */   public String flowInitView(HttpServletRequest request, Integer pageNo, ModelMap model) {
/* 228 */     return "statistic/init";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/statistic_flow/o_init.do"})
/*     */   public String flowInit(HttpServletRequest request, Date startDate, Date endDate, Integer pageNo, ModelMap model) {
/* 234 */     Integer siteId = CmsUtils.getSiteId(request);
/* 235 */     this.cmsStatisticSvc.flowInit(siteId, startDate, endDate);
/* 236 */     model.addAttribute("message", "global.success");
/* 237 */     return "statistic/init";
/*     */   }
/*     */ 
/*     */   private CmsStatistic.CmsStatisticModel getStatisticModel(String queryModel) {
/* 241 */     if (!StringUtils.isBlank(queryModel)) {
/* 242 */       return CmsStatistic.CmsStatisticModel.valueOf(queryModel);
/*     */     }
/* 244 */     return CmsStatistic.CmsStatisticModel.year;
/*     */   }
/*     */ 
/*     */   private void putCommonData(CmsStatistic.CmsStatisticModel statisticModel, List<CmsStatistic> list, Integer year, Integer month, Integer day, ModelMap model)
/*     */   {
/* 250 */     model.addAttribute("list", list);
/* 251 */     model.addAttribute("total", getTotal(list));
/* 252 */     model.addAttribute("statisticModel", statisticModel.name());
/* 253 */     model.addAttribute("year", year);
/* 254 */     model.addAttribute("month", month);
/* 255 */     model.addAttribute("day", day);
/*     */   }
/*     */ 
/*     */   private Long getTotal(List<CmsStatistic> list) {
/* 259 */     return Long.valueOf(list.size() > 0 ? ((CmsStatistic)list.iterator().next()).getTotal().longValue() : 0L);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatisticAct
 * JD-Core Version:    0.6.0
 */