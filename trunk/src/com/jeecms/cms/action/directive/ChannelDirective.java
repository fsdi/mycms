/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
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
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ChannelDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_ID = "id";
/*    */   public static final String PARAM_PATH = "path";
/*    */   public static final String PARAM_SITE_ID = "siteId";
/*    */ 
/*    */   @Autowired
/*    */   private ChannelMng channelMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 46 */     CmsSite site = FrontUtils.getSite(env);
/* 47 */     Integer id = DirectiveUtils.getInt("id", params);
/*    */     Channel channel;
/* 49 */     if (id != null) {
/* 50 */       channel = this.channelMng.findById(id);
/*    */     } else {
/* 52 */       String path = DirectiveUtils.getString("path", params);
/* 53 */       if (StringUtils.isBlank(path))
/*    */       {
/* 55 */         throw new ParamsRequiredException("id");
/*    */       }
/* 57 */       Integer siteId = DirectiveUtils.getInt("siteId", params);
/* 58 */       if (siteId == null) {
/* 59 */         siteId = site.getId();
/*    */       }
/* 61 */       channel = this.channelMng.findByPathForTag(path, siteId);
/*    */     }
/*    */ 
/* 64 */     Map paramWrap = new HashMap(
/* 65 */       params);
/* 66 */     paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(channel));
/* 67 */     Map origMap = 
/* 68 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 69 */     body.render(env.getOut());
/* 70 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ChannelDirective
 * JD-Core Version:    0.6.0
 */