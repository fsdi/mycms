/*    */ package com.jeecms.cms.lucene;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
/*    */ import com.jeecms.common.web.freemarker.ParamsRequiredException;
/*    */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.lucene.queryParser.ParseException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class LuceneDirectiveList extends LuceneDirectiveAbstract
/*    */ {
/*    */   public static final String TPL_NAME = "lucene_list";
/*    */ 
/*    */   @Autowired
/*    */   private LuceneContentSvc luceneContentSvc;
/*    */ 
/*    */   @Autowired
/*    */   private RealPathResolver realPathResolver;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 43 */     CmsSite site = FrontUtils.getSite(env);
/* 44 */     int first = FrontUtils.getFirst(params);
/* 45 */     int count = FrontUtils.getCount(params);
/* 46 */     String query = getQuery(params);
/* 47 */     String workplace = getWorkplace(params);
/* 48 */     String category = getCategory(params);
/* 49 */     Integer siteId = getSiteId(params);
/* 50 */     Integer channelId = getChannelId(params);
/* 51 */     Date startDate = getStartDate(params);
/* 52 */     Date endDate = getEndDate(params);
/*    */     List list;
/*    */     try
/*    */     {
/* 55 */       String path = this.realPathResolver.get("/WEB-INF/lucene");
/* 56 */       list = this.luceneContentSvc.searchList(path, query, category, workplace, siteId, channelId, 
/* 57 */         startDate, endDate, first, count);
/*    */     }
/*    */     catch (ParseException e)
/*    */     {
/* 59 */       throw new RuntimeException(e);
/*    */     }
/* 62 */     Map paramWrap = new HashMap(
/* 63 */       params);
/* 64 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 65 */     Map origMap = 
/* 66 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 67 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 68 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 69 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 70 */       if (StringUtils.isBlank(listStyle)) {
/* 71 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 73 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 74 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 75 */       if (StringUtils.isBlank(listStyle)) {
/* 76 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 78 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 79 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 80 */       FrontUtils.includeTpl("lucene_list", site, params, env);
/* 81 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 82 */       body.render(env.getOut());
/*    */     } else {
/* 84 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 86 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneDirectiveList
 * JD-Core Version:    0.6.0
 */