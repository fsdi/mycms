/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsFriendlinkListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */   public static final String PARAM_CTG_ID = "ctgId";
/*    */   public static final String PARAM_ENABLED = "enabled";
/*    */ 
/*    */   @Autowired
/*    */   private CmsFriendlinkMng cmsFriendlinkMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 44 */     Integer siteId = getSiteId(params);
/* 45 */     if (siteId == null) {
/* 46 */       siteId = FrontUtils.getSite(env).getId();
/*    */     }
/* 48 */     Integer ctgId = getCtgId(params);
/* 49 */     Boolean enabled = getEnabled(params);
/* 50 */     if (enabled == null) {
/* 51 */       enabled = Boolean.valueOf(true);
/*    */     }
/* 53 */     List list = this.cmsFriendlinkMng.getList(siteId, ctgId, 
/* 54 */       enabled);
/*    */ 
/* 56 */     Map paramWrap = new HashMap(
/* 57 */       params);
/* 58 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 59 */     Map origMap = 
/* 60 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 61 */     body.render(env.getOut());
/* 62 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getSiteId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 67 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ 
/*    */   private Integer getCtgId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 72 */     return DirectiveUtils.getInt("ctgId", params);
/*    */   }
/*    */ 
/*    */   private Boolean getEnabled(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 77 */     return DirectiveUtils.getBool("enabled", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsFriendlinkListDirective
 * JD-Core Version:    0.6.0
 */