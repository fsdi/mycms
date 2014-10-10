/*     */ package com.jeecms.common.fck;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class UploadResponse
/*     */ {
/*     */   protected Object[] parameters;
/*     */   public static final int EN_OK = 2;
/*     */   public static final int EN_CUSTOM_ERROR = 1;
/*     */   public static final int EN_CUSTOM_WARNING = 101;
/*     */   public static final int EN_FILE_RENAMED_WARNING = 201;
/*     */   public static final int EN_INVALID_FILE_TYPE_ERROR = 202;
/*     */   public static final int EN_SECURITY_ERROR = 203;
/*     */ 
/*     */   public UploadResponse(Object[] arguments)
/*     */   {
/*  75 */     if ((arguments.length < 1) || (arguments.length > 4)) {
/*  76 */       throw new IllegalArgumentException(
/*  77 */         "The amount of arguments has to be between 1 and 4");
/*     */     }
/*  79 */     this.parameters = new Object[arguments.length];
/*     */ 
/*  81 */     if (!(arguments[0] instanceof Integer)) {
/*  82 */       throw new IllegalArgumentException(
/*  83 */         "The first argument has to be an error number (int)");
/*     */     }
/*  85 */     System.arraycopy(arguments, 0, this.parameters, 0, arguments.length);
/*     */   }
/*     */ 
/*     */   public void setCustomMessage(String customMassage)
/*     */   {
/*  98 */     if (!StringUtils.isBlank(customMassage)) {
/*  99 */       if (this.parameters.length == 1) {
/* 100 */         Object errorNumber = this.parameters[0];
/* 101 */         this.parameters = new Object[4];
/* 102 */         this.parameters[0] = errorNumber;
/* 103 */         this.parameters[1] = null;
/* 104 */         this.parameters[2] = null;
/*     */       }
/* 106 */       this.parameters[3] = customMassage;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static UploadResponse getOK(HttpServletRequest request, String fileUrl)
/*     */   {
/* 113 */     String callback = request.getParameter("CKEditorFuncNum");
/* 114 */     return new UploadResponse(new Object[] { Integer.valueOf(Integer.parseInt(callback)), fileUrl });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getFileRenamedWarning(HttpServletRequest request, String fileUrl, String newFileName)
/*     */   {
/* 120 */     return new UploadResponse(new Object[] { Integer.valueOf(201), fileUrl, 
/* 121 */       newFileName, 
/* 122 */       LocalizedMessages.getFileRenamedWarning(request, 
/* 122 */       newFileName) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getInvalidFileTypeError(HttpServletRequest request)
/*     */   {
/* 128 */     return new UploadResponse(new Object[] { Integer.valueOf(202), 
/* 129 */       LocalizedMessages.getInvalidFileTypeSpecified(request) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getInvalidCommandError(HttpServletRequest request)
/*     */   {
/* 135 */     return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, 
/* 136 */       LocalizedMessages.getInvalidCommandSpecified(request) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getInvalidResourceTypeError(HttpServletRequest request)
/*     */   {
/* 142 */     return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, 
/* 143 */       LocalizedMessages.getInvalidResouceTypeSpecified(request) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getInvalidCurrentFolderError(HttpServletRequest request)
/*     */   {
/* 149 */     return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, 
/* 150 */       LocalizedMessages.getInvalidCurrentFolderSpecified(request) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getFileUploadDisabledError(HttpServletRequest request)
/*     */   {
/* 156 */     return new UploadResponse(new Object[] { Integer.valueOf(203), null, null, 
/* 157 */       LocalizedMessages.getFileUploadDisabled(request) });
/*     */   }
/*     */ 
/*     */   public static UploadResponse getFileUploadWriteError(HttpServletRequest request)
/*     */   {
/* 163 */     return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, 
/* 164 */       LocalizedMessages.getFileUploadWriteError(request) });
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 174 */     StringBuffer sb = new StringBuffer(400);
/* 175 */     sb.append("<script type=\"text/javascript\">\n");
/*     */ 
/* 179 */     sb
/* 180 */       .append("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n");
/*     */ 
/* 184 */     sb.append("window.parent.CKEDITOR.tools.callFunction(");
/* 185 */     for (Object parameter : this.parameters) {
/* 186 */       if ((parameter instanceof Integer)) {
/* 187 */         sb.append(parameter);
/*     */       } else {
/* 189 */         sb.append("'");
/* 190 */         if (parameter != null)
/* 191 */           sb.append(parameter);
/* 192 */         sb.append("'");
/*     */       }
/* 194 */       sb.append(",");
/*     */     }
/*     */ 
/* 197 */     sb.deleteCharAt(sb.length() - 1);
/* 198 */     sb.append(");\n");
/* 199 */     sb.append("</script>");
/* 200 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.fck.UploadResponse
 * JD-Core Version:    0.6.0
 */