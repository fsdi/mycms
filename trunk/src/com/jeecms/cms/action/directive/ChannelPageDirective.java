/*    */ package com.jeecms.cms.action.directive;
/*    */ 
/*    */ import com.jeecms.cms.action.directive.abs.AbstractChannelDirective;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.page.Pagination;
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
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ChannelPageDirective extends AbstractChannelDirective
/*    */ {
/*    */   public static final String TPL_NAME = "channel_page";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 41 */     CmsSite site = FrontUtils.getSite(env);
/* 42 */     Integer parentId = DirectiveUtils.getInt("parentId", params);
/* 43 */     Integer siteId = DirectiveUtils.getInt("siteId", params);
/* 44 */     boolean hasContentOnly = getHasContentOnly(params);
/*    */     Pagination page;
/* 47 */     if (parentId != null) {
/* 48 */       page = this.channelMng.getChildPageForTag(parentId, hasContentOnly, 
/* 49 */         FrontUtils.getPageNo(env), FrontUtils.getCount(params));
/*    */     } else {
/* 51 */       if (siteId == null) {
/* 52 */         siteId = site.getId();
/*    */       }
/* 54 */       page = this.channelMng.getTopPageForTag(siteId, hasContentOnly, 
/* 55 */         FrontUtils.getPageNo(env), FrontUtils.getCount(params));
/*    */     }
/*    */ 
/* 58 */     Map paramWrap = new HashMap(
/* 59 */       params);
/* 60 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(page));
/* 61 */     Map origMap = 
/* 62 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 63 */     DirectiveUtils.InvokeType type = DirectiveUtils.getInvokeType(params);
/* 64 */     String listStyle = DirectiveUtils.getString("styleList", params);
/* 65 */     if (DirectiveUtils.InvokeType.sysDefined == type) {
/* 66 */       if (StringUtils.isBlank(listStyle)) {
/* 67 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 69 */       env.include("/WEB-INF/t/cms_sys_defined/style_list/style_" + listStyle + ".html", "UTF-8", true);
/* 70 */     } else if (DirectiveUtils.InvokeType.userDefined == type) {
/* 71 */       if (StringUtils.isBlank(listStyle)) {
/* 72 */         throw new ParamsRequiredException("styleList");
/*    */       }
/* 74 */       FrontUtils.includeTpl("/WEB-INF/t/cms_sys_defined/style_list/style_", site, env);
/* 75 */     } else if (DirectiveUtils.InvokeType.custom == type) {
/* 76 */       FrontUtils.includeTpl("channel_page", site, params, env);
/* 77 */     } else if (DirectiveUtils.InvokeType.body == type) {
/* 78 */       body.render(env.getOut());
/*    */     } else {
/* 80 */       throw new RuntimeException("invoke type not handled: " + type);
/*    */     }
/* 82 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.directive.ChannelPageDirective
 * JD-Core Version:    0.6.0
 */