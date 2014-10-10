/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkCtgMng;
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
/*    */ public class CmsFriendlinkCtgListDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private CmsFriendlinkCtgMng cmsFriendlinkCtgMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 36 */     Integer siteId = getSiteId(params);
/* 37 */     if (siteId == null) {
/* 38 */       siteId = FrontUtils.getSite(env).getId();
/*    */     }
/* 40 */     List list = this.cmsFriendlinkCtgMng.getList(siteId);
/*    */ 
/* 42 */     Map paramWrap = new HashMap(
/* 43 */       params);
/* 44 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 45 */     Map origMap = 
/* 46 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 47 */     body.render(env.getOut());
/* 48 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getSiteId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 53 */     return DirectiveUtils.getInt("siteId", params);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsFriendlinkCtgListDirective
 * JD-Core Version:    0.6.0
 */