/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractChannelDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils;
/*    */ import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
/*    */ import com.jeecms.common.web.freemarker.ParamsRequiredException;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ChannelListDirective extends AbstractChannelDirective
/*    */ {
/*    */   public static final String TPL_NAME = "channel_list";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 42 */     CmsSite site = FrontUtils.getSite(env);
/* 43 */     Integer parentId = DirectiveUtils.getInt("parentId", params);
/* 44 */     Integer siteId = DirectiveUtils.getInt("siteId", params);
/* 45 */     boolean hasContentOnly = getHasContentOnly(params);
/*    */     List list;
/* 48 */     if (parentId != null) {
/* 49 */       list = this.channelMng.getChildListForTag(parentId, hasContentOnly);
/*    */     } else {
/* 51 */       if (siteId == null) {
/* 52 */         siteId = site.getId();
/*    */       }
/* 54 */       list = this.channelMng.getTopListForTag(siteId, hasContentOnly);
/*    */     }
/*    */ 
/* 57 */     Map paramWrap = new HashMap(
/* 58 */       params);
/* 59 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 60 */     Map origMap = 
/* 61 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 62 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 63 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 64 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 65 */       if (StringUtils.isBlank(listStyle)) {
/* 66 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 68 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 69 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 70 */       if (StringUtils.isBlank(listStyle)) {
/* 71 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 73 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 74 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 75 */       FrontUtils.includeTpl("channel_list", site, params, env);
/* 76 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 77 */       body.render(env.getOut());
/*    */     } else {
/* 79 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 81 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ChannelListDirective
 * JD-Core Version:    0.6.0
 */