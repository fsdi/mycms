/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.manager.main.ContentMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
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
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ContentDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PRAMA_NEXT = "next";
/*    */   public static final String PARAM_CHANNEL_ID = "channelId";
/*    */ 
/*    */   @Autowired
/*    */   private ContentMng contentMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 45 */     Integer id = getId(params);
/* 46 */     Boolean next = DirectiveUtils.getBool("next", params);
/*    */     Content content;
/* 48 */     if (next == null) {
/* 49 */       content = this.contentMng.findById(id);
/*    */     } else {
/* 51 */       CmsSite site = FrontUtils.getSite(env);
/* 52 */       Integer channelId = DirectiveUtils.getInt("channelId", params);
/* 53 */       content = this.contentMng.getSide(id, site.getId(), channelId, next.booleanValue());
/*    */     }
/*    */ 
/* 56 */     Map paramWrap = new HashMap(
/* 57 */       params);
/* 58 */     paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(content));
/* 59 */     Map origMap = 
/* 60 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 61 */     body.render(env.getOut());
/* 62 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private Integer getId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 67 */     Integer id = DirectiveUtils.getInt("id", params);
/* 68 */     if (id != null) {
/* 69 */       return id;
/*    */     }
/* 71 */     throw new ParamsRequiredException("id");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ContentDirective
 * JD-Core Version:    0.6.0
 */