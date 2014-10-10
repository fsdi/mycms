/*     */ package com.jeecms.common.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.htmlparser.Node;
/*     */ import org.htmlparser.lexer.Lexer;
/*     */ import org.htmlparser.nodes.TextNode;
/*     */ import org.htmlparser.util.ParserException;
/*     */ import org.wltea.analyzer.core.IKSegmenter;
/*     */ import org.wltea.analyzer.core.Lexeme;
/*     */ 
/*     */ public class StrUtils
/*     */ {
/*     */   public static String handelUrl(String url)
/*     */   {
/*  34 */     if (url == null) {
/*  35 */       return null;
/*     */     }
/*  37 */     url = url.trim();
/*  38 */     if ((url.equals("")) || (url.startsWith("http://")) || 
/*  39 */       (url.startsWith("https://"))) {
/*  40 */       return url;
/*     */     }
/*  42 */     return "http://" + url.trim();
/*     */   }
/*     */ 
/*     */   public static String[] splitAndTrim(String str, String sep, String sep2)
/*     */   {
/*  58 */     if (StringUtils.isBlank(str)) {
/*  59 */       return null;
/*     */     }
/*  61 */     if (!StringUtils.isBlank(sep2)) {
/*  62 */       str = StringUtils.replace(str, sep2, sep);
/*     */     }
/*  64 */     String[] arr = StringUtils.split(str, sep);
/*     */ 
/*  66 */     int i = 0; for (int len = arr.length; i < len; i++) {
/*  67 */       arr[i] = arr[i].trim();
/*     */     }
/*  69 */     return arr;
/*     */   }
/*     */ 
/*     */   public static String txt2htm(String txt)
/*     */   {
/*  79 */     if (StringUtils.isBlank(txt)) {
/*  80 */       return txt;
/*     */     }
/*  82 */     StringBuilder sb = new StringBuilder((int)(txt.length() * 1.2D));
/*     */ 
/*  84 */     boolean doub = false;
/*  85 */     for (int i = 0; i < txt.length(); i++) {
/*  86 */       char c = txt.charAt(i);
/*  87 */       if (c == ' ') {
/*  88 */         if (doub) {
/*  89 */           sb.append(' ');
/*  90 */           doub = false;
/*     */         } else {
/*  92 */           sb.append("&nbsp;");
/*  93 */           doub = true;
/*     */         }
/*     */       } else {
/*  96 */         doub = false;
/*  97 */         switch (c) {
/*     */         case '&':
/*  99 */           sb.append("&amp;");
/* 100 */           break;
/*     */         case '<':
/* 102 */           sb.append("&lt;");
/* 103 */           break;
/*     */         case '>':
/* 105 */           sb.append("&gt;");
/* 106 */           break;
/*     */         case '"':
/* 108 */           sb.append("&quot;");
/* 109 */           break;
/*     */         case '\n':
/* 111 */           sb.append("<br/>");
/* 112 */           break;
/*     */         default:
/* 114 */           sb.append(c);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 119 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String textCut(String s, int len, String append)
/*     */   {
/* 132 */     if (s == null) {
/* 133 */       return null;
/*     */     }
/* 135 */     int slen = s.length();
/* 136 */     if (slen <= len) {
/* 137 */       return s;
/*     */     }
/*     */ 
/* 140 */     int maxCount = len * 2;
/* 141 */     int count = 0;
/* 142 */     int i = 0;
/* 143 */     for (; (count < maxCount) && (i < slen); i++) {
/* 144 */       if (s.codePointAt(i) < 256)
/* 145 */         count++;
/*     */       else {
/* 147 */         count += 2;
/*     */       }
/*     */     }
/* 150 */     if (i < slen) {
/* 151 */       if (count > maxCount) {
/* 152 */         i--;
/*     */       }
/* 154 */       if (!StringUtils.isBlank(append)) {
/* 155 */         if (s.codePointAt(i - 1) < 256)
/* 156 */           i -= 2;
/*     */         else {
/* 158 */           i--;
/*     */         }
/* 160 */         return s.substring(0, i) + append;
/*     */       }
/* 162 */       return s.substring(0, i);
/*     */     }
/*     */ 
/* 165 */     return s;
/*     */   }
/*     */ 
/*     */   public static String htmlCut(String s, int len, String append)
/*     */   {
/* 170 */     String text = html2Text(s, len * 2);
/* 171 */     return textCut(text, len, append);
/*     */   }
/*     */ 
/*     */   public static String html2Text(String html, int len) {
/*     */     try {
/* 176 */       Lexer lexer = new Lexer(html);
/*     */ 
/* 178 */       StringBuilder sb = new StringBuilder(html.length());
/*     */       Node node;
/* 179 */       while ((node = lexer.nextNode()) != null)
/*     */       {
/* 180 */         if ((node instanceof TextNode)) {
/* 181 */           sb.append(node.toHtml());
/*     */         }
/* 183 */         if (sb.length() > len) {
/*     */           break;
/*     */         }
/*     */       }
/* 187 */       return sb.toString(); } catch (ParserException e) {
/*     */     }
/* 189 */     throw new RuntimeException();
/*     */   }
/*     */ 
/*     */   public static String getKeywords(String keyword, boolean smart)
/*     */   {
/* 200 */     StringReader reader = new StringReader(keyword);
/* 201 */     IKSegmenter iks = new IKSegmenter(reader, smart);
/* 202 */     StringBuilder buffer = new StringBuilder();
/*     */     try
/*     */     {
/*     */       Lexeme lexeme;
/* 205 */       while ((lexeme = iks.next()) != null)
/*     */       {
/* 206 */         buffer.append(lexeme.getLexemeText()).append(',');
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException) {
/*     */     }
/* 211 */     if (buffer.length() > 0) {
/* 212 */       buffer.setLength(buffer.length() - 1);
/*     */     }
/* 214 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public static boolean contains(String str, String search)
/*     */   {
/* 225 */     if ((StringUtils.isBlank(str)) || (StringUtils.isBlank(search))) {
/* 226 */       return false;
/*     */     }
/* 228 */     String reg = StringUtils.replace(search, "*", ".*");
/* 229 */     Pattern p = Pattern.compile(reg);
/* 230 */     return p.matcher(str).matches();
/*     */   }
/*     */ 
/*     */   public static boolean containsKeyString(String str) {
/* 234 */     if (StringUtils.isBlank(str)) {
/* 235 */       return false;
/*     */     }
/*     */ 
/* 240 */     return (str.contains("'")) || (str.contains("\"")) || (str.contains("\r")) || 
/* 238 */       (str.contains("\n")) || (str.contains("\t")) || 
/* 239 */       (str.contains("\b")) || (str.contains("\f"));
/*     */   }
/*     */ 
/*     */   public static String replaceKeyString(String str)
/*     */   {
/* 247 */     if (containsKeyString(str)) {
/* 248 */       return str.replace("'", "\\'").replace("\"", "\\\"").replace("\r", 
/* 249 */         "\\r").replace("\n", "\\n").replace("\t", "\\t").replace(
/* 250 */         "\b", "\\b").replace("\f", "\\f");
/*     */     }
/* 252 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getSuffix(String str)
/*     */   {
/* 257 */     int splitIndex = str.lastIndexOf(".");
/* 258 */     return str.substring(splitIndex + 1);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 262 */     System.out.println(replaceKeyString("&nbsp;\r</p>"));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.StrUtils
 * JD-Core Version:    0.6.0
 */