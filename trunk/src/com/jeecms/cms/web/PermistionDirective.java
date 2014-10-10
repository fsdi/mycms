/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateHashModel;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import freemarker.template.TemplateScalarModel;
/*    */ import freemarker.template.TemplateSequenceModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class PermistionDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_URL = "url";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 36 */     String url = DirectiveUtils.getString("url", params);
/* 37 */     boolean pass = false;
/* 38 */     if (StringUtils.isBlank(url))
/*    */     {
/* 40 */       pass = true;
/*    */     } else {
/* 42 */       TemplateSequenceModel perms = getPerms(env);
/* 43 */       if (perms == null)
/*    */       {
/* 45 */         pass = true;
/*    */       }
/*    */       else {
/* 48 */         int i = 0; for (int len = perms.size(); i < len; i++) {
/* 49 */           String perm = ((TemplateScalarModel)perms.get(i)).getAsString();
/* 50 */           if (url.startsWith(perm)) {
/* 51 */             pass = true;
/* 52 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 57 */     if (pass)
/* 58 */       body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   private TemplateSequenceModel getPerms(Environment env)
/*    */     throws TemplateModelException
/*    */   {
/* 64 */     TemplateModel model = env.getDataModel().get("_permission_key");
/* 65 */     if (model == null) {
/* 66 */       return null;
/*    */     }
/* 68 */     if ((model instanceof TemplateSequenceModel)) {
/* 69 */       return (TemplateSequenceModel)model;
/*    */     }
/* 71 */     throw new TemplateModelException(
/* 72 */       "'perms' in data model not a TemplateSequenceModel");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.PermistionDirective
 * JD-Core Version:    0.6.0
 */