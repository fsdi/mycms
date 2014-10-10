/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsAdvertisingDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */ 
/*    */   @Autowired
/*    */   private CmsAdvertisingMng cmsAdvertisingMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 34 */     Integer id = DirectiveUtils.getInt("id", params);
/* 35 */     CmsAdvertising ad = null;
/* 36 */     if (id != null) {
/* 37 */       ad = this.cmsAdvertisingMng.findById(id);
/*    */     }
/* 39 */     Map paramWrap = new HashMap(
/* 40 */       params);
/* 41 */     paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(ad));
/* 42 */     Map origMap = 
/* 43 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 44 */     body.render(env.getOut());
/* 45 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsAdvertisingDirective
 * JD-Core Version:    0.6.0
 */