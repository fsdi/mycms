/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsModel;
/*    */ import com.jeecms.cms.manager.main.CmsModelMng;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import com.jeecms.common.web.freemarker.ParamsRequiredException;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class CmsModelDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PARAM_PATH = "path";
/*    */ 
/*    */   @Autowired
/*    */   private CmsModelMng modelMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 41 */     Integer id = DirectiveUtils.getInt("id", params);
/*    */     CmsModel model;
/* 43 */     if (id != null) {
/* 44 */       model = this.modelMng.findById(id);
/*    */     } else {
/* 46 */       String path = DirectiveUtils.getString("path", params);
/* 47 */       if (StringUtils.isBlank(path))
/*    */       {
/* 49 */         throw new ParamsRequiredException("id");
/*    */       }
/* 51 */       model = this.modelMng.findByPath(path);
/*    */     }
/*    */ 
/* 54 */     Map paramWrap = new HashMap(
/* 55 */       params);
/* 56 */     paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(model));
/* 57 */     Map origMap = 
/* 58 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 59 */     body.render(env.getOut());
/* 60 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.CmsModelDirective
 * JD-Core Version:    0.6.0
 */