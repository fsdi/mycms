/*     */ package com.jeecms.common.web.freemarker;
/*     */ 
/*     */ import com.jeecms.common.web.springmvc.DateTypeEditor;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.AdapterTemplateModel;
/*     */ import freemarker.template.TemplateBooleanModel;
/*     */ import freemarker.template.TemplateDateModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import freemarker.template.TemplateScalarModel;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.support.RequestContext;
/*     */ 
/*     */ public abstract class DirectiveUtils
/*     */ {
/*     */   public static final String OUT_BEAN = "tag_bean";
/*     */   public static final String OUT_LIST = "tag_list";
/*     */   public static final String OUT_PAGINATION = "tag_pagination";
/*     */   public static final String PARAM_TPL = "tpl";
/*     */   public static final String PARAM_TPL_SUB = "tplSub";
/*     */ 
/*     */   public static Map<String, TemplateModel> addParamsToVariable(Environment env, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/*  61 */     Map origMap = new HashMap();
/*  62 */     if (params.size() <= 0) {
/*  63 */       return origMap;
/*     */     }
/*  65 */     Set<Entry<String, TemplateModel>> entrySet = params.entrySet();
/*     */ 
/*  68 */     for (Map.Entry entry : entrySet) {
/*  69 */       String key = (String)entry.getKey();
/*  70 */       TemplateModel value = env.getVariable(key);
/*  71 */       if (value != null) {
/*  72 */         origMap.put(key, value);
/*     */       }
/*  74 */       env.setVariable(key, (TemplateModel)entry.getValue());
/*     */     }
/*  76 */     return origMap;
/*     */   }
/*     */ 
/*     */   public static void removeParamsFromVariable(Environment env, Map<String, TemplateModel> params, Map<String, TemplateModel> origMap)
/*     */     throws TemplateException
/*     */   {
/*  90 */     if (params.size() <= 0) {
/*  91 */       return;
/*     */     }
/*  93 */     for (String key : params.keySet())
/*  94 */       env.setVariable(key, (TemplateModel)origMap.get(key));
/*     */   }
/*     */ 
/*     */   public static RequestContext getContext(Environment env)
/*     */     throws TemplateException
/*     */   {
/* 109 */     TemplateModel ctx = env
/* 110 */       .getGlobalVariable("springMacroRequestContext");
/* 111 */     if ((ctx instanceof AdapterTemplateModel)) {
/* 112 */       return (RequestContext)((AdapterTemplateModel)ctx)
/* 113 */         .getAdaptedObject(RequestContext.class);
/*     */     }
/* 115 */     throw new TemplateModelException("RequestContext 'springMacroRequestContext' not found in DataModel.");
/*     */   }
/*     */ 
/*     */   public static String getString(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 123 */     TemplateModel model = (TemplateModel)params.get(name);
/* 124 */     if (model == null) {
/* 125 */       return null;
/*     */     }
/* 127 */     if ((model instanceof TemplateScalarModel))
/* 128 */       return ((TemplateScalarModel)model).getAsString();
/* 129 */     if ((model instanceof TemplateNumberModel)) {
/* 130 */       return ((TemplateNumberModel)model).getAsNumber().toString();
/*     */     }
/* 132 */     throw new MustStringException(name);
/*     */   }
/*     */ 
/*     */   public static Long getLong(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 138 */     TemplateModel model = (TemplateModel)params.get(name);
/* 139 */     if (model == null) {
/* 140 */       return null;
/*     */     }
/* 142 */     if ((model instanceof TemplateScalarModel)) {
/* 143 */       String s = ((TemplateScalarModel)model).getAsString();
/* 144 */       if (StringUtils.isBlank(s))
/* 145 */         return null;
/*     */       try
/*     */       {
/* 148 */         return Long.valueOf(Long.parseLong(s));
/*     */       } catch (NumberFormatException e) {
/* 150 */         throw new MustNumberException(name);
/*     */       }
/*     */     }
/* 152 */     if ((model instanceof TemplateNumberModel)) {
/* 153 */       return Long.valueOf(((TemplateNumberModel)model).getAsNumber().longValue());
/*     */     }
/* 155 */     throw new MustNumberException(name);
/*     */   }
/*     */ 
/*     */   public static Integer getInt(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 161 */     TemplateModel model = (TemplateModel)params.get(name);
/* 162 */     if (model == null) {
/* 163 */       return null;
/*     */     }
/* 165 */     if ((model instanceof TemplateScalarModel)) {
/* 166 */       String s = ((TemplateScalarModel)model).getAsString();
/* 167 */       if (StringUtils.isBlank(s))
/* 168 */         return null;
/*     */       try
/*     */       {
/* 171 */         return Integer.valueOf(Integer.parseInt(s));
/*     */       } catch (NumberFormatException e) {
/* 173 */         throw new MustNumberException(name);
/*     */       }
/*     */     }
/* 175 */     if ((model instanceof TemplateNumberModel)) {
/* 176 */       return Integer.valueOf(((TemplateNumberModel)model).getAsNumber().intValue());
/*     */     }
/* 178 */     throw new MustNumberException(name);
/*     */   }
/*     */ 
/*     */   public static Integer[] getIntArray(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 184 */     String str = getString(name, params);
/* 185 */     if (StringUtils.isBlank(str)) {
/* 186 */       return null;
/*     */     }
/* 188 */     String[] arr = StringUtils.split(str, ',');
/* 189 */     Integer[] ids = new Integer[arr.length];
/* 190 */     int i = 0;
/*     */     try {
/* 192 */       for (String s : arr) {
/* 193 */         ids[(i++)] = Integer.valueOf(s);
/*     */       }
/* 195 */       return ids; } catch (NumberFormatException e) {
/*     */     }
/* 197 */     throw new MustSplitNumberException(name);
/*     */   }
/*     */ 
/*     */   public static Boolean getBool(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 203 */     TemplateModel model = (TemplateModel)params.get(name);
/* 204 */     if (model == null) {
/* 205 */       return null;
/*     */     }
/* 207 */     if ((model instanceof TemplateBooleanModel))
/* 208 */       return Boolean.valueOf(((TemplateBooleanModel)model).getAsBoolean());
/* 209 */     if ((model instanceof TemplateNumberModel))
/* 210 */       return Boolean.valueOf(((TemplateNumberModel)model).getAsNumber().intValue() != 0);
/* 211 */     if ((model instanceof TemplateScalarModel)) {
/* 212 */       String s = ((TemplateScalarModel)model).getAsString();
/*     */ 
/* 214 */       if (!StringUtils.isBlank(s)) {
/* 215 */         return Boolean.valueOf((!s.equals("0")) && (!s.equalsIgnoreCase("false")) && (!
/* 216 */           s.equalsIgnoreCase("f")));
/*     */       }
/* 218 */       return null;
/*     */     }
/*     */ 
/* 221 */     throw new MustBooleanException(name);
/*     */   }
/*     */ 
/*     */   public static Date getDate(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 227 */     TemplateModel model = (TemplateModel)params.get(name);
/* 228 */     if (model == null) {
/* 229 */       return null;
/*     */     }
/* 231 */     if ((model instanceof TemplateDateModel))
/* 232 */       return ((TemplateDateModel)model).getAsDate();
/* 233 */     if ((model instanceof TemplateScalarModel)) {
/* 234 */       DateTypeEditor editor = new DateTypeEditor();
/* 235 */       editor.setAsText(((TemplateScalarModel)model).getAsString());
/* 236 */       return (Date)editor.getValue();
/*     */     }
/* 238 */     throw new MustDateException(name);
/*     */   }
/*     */ 
/*     */   public static InvokeType getInvokeType(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 260 */     String tpl = getString("tpl", params);
/* 261 */     if ("3".equals(tpl))
/* 262 */       return InvokeType.userDefined;
/* 263 */     if ("2".equals(tpl))
/* 264 */       return InvokeType.sysDefined;
/* 265 */     if ("1".equals(tpl)) {
/* 266 */       return InvokeType.custom;
/*     */     }
/* 268 */     return InvokeType.body;
/*     */   }
/*     */ 
/*     */   public static enum InvokeType
/*     */   {
/* 246 */     body, custom, sysDefined, userDefined;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.DirectiveUtils
 * JD-Core Version:    0.6.0
 */