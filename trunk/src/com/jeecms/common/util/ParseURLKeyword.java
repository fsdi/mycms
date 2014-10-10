/*    */ package com.jeecms.common.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLDecoder;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ParseURLKeyword
/*    */ {
/*    */   public static String getKeyword(String url)
/*    */   {
/* 12 */     String keywordReg = "(?:yahoo.+?[\\?|&]q=|openfind.+?q=|google.+?q=|lycos.+?query=|onseek.+?keyword=|search\\.tom.+?word=|search\\.qq\\.com.+?word=|zhongsou\\.com.+?word=|search\\.msn\\.com.+?q=|yisou\\.com.+?p=|sina.+?word=|sina.+?query=|sina.+?_searchkey=|sohu.+?word=|sohu.+?key_word=|sohu.+?query=|163.+?q=|baidu.+?wd=|soso.+?w=|3721\\.com.+?p=|Alltheweb.+?q=)([^&]*)";
/* 13 */     String encodeReg = "^(?:[\\x00-\\x7f]|[\\xfc-\\xff][\\x80-\\xbf]{5}|[\\xf8-\\xfb][\\x80-\\xbf]{4}|[\\xf0-\\xf7][\\x80-\\xbf]{3}|[\\xe0-\\xef][\\x80-\\xbf]{2}|[\\xc0-\\xdf][\\x80-\\xbf])+$";
/* 14 */     Pattern keywordPattern = Pattern.compile(keywordReg);
/* 15 */     StringBuffer keywordBuff = new StringBuffer(20);
/* 16 */     Matcher keywordMat = keywordPattern.matcher(url);
/* 17 */     while (keywordMat.find()) {
/* 18 */       keywordMat.appendReplacement(keywordBuff, "$1");
/*    */     }
/* 20 */     String keyword = keywordBuff.toString();
/* 21 */     if (StringUtils.isNotBlank(keyword.toString())) {
/* 22 */       keyword = StringUtils.remove(keyword, keyword.substring(0, 
/* 23 */         keyword.indexOf(".") + 1));
/* 24 */       Pattern encodePatt = Pattern.compile(encodeReg);
/* 25 */       String unescapeString = unescape(keyword);
/* 26 */       Matcher encodeMat = encodePatt.matcher(unescapeString);
/* 27 */       String encode = "gbk";
/* 28 */       if (encodeMat.matches())
/* 29 */         encode = "utf-8";
/*    */       try {
/* 31 */         return URLDecoder.decode(keyword, encode);
/*    */       } catch (UnsupportedEncodingException e) {
/* 33 */         return "";
/*    */       }
/*    */     }
/* 36 */     return "";
/*    */   }
/*    */ 
/*    */   public static String unescape(String src) {
/* 40 */     StringBuffer result = new StringBuffer();
/* 41 */     result.ensureCapacity(src.length());
/* 42 */     int lastPos = 0; int pos = 0;
/*    */ 
/* 44 */     while (lastPos < src.length()) {
/* 45 */       pos = src.indexOf("%", lastPos);
/* 46 */       if (pos == lastPos) {
/* 47 */         if (src.charAt(pos + 1) == 'u') {
/* 48 */           char ch = (char)Integer.parseInt(src
/* 49 */             .substring(pos + 2, pos + 6), 16);
/* 50 */           result.append(ch);
/* 51 */           lastPos = pos + 6;
/*    */         } else {
/* 53 */           char ch = (char)Integer.parseInt(src
/* 54 */             .substring(pos + 1, pos + 3), 16);
/* 55 */           result.append(ch);
/* 56 */           lastPos = pos + 3;
/*    */         }
/*    */       }
/* 59 */       else if (pos == -1) {
/* 60 */         result.append(src.substring(lastPos));
/* 61 */         lastPos = src.length();
/*    */       } else {
/* 63 */         result.append(src.substring(lastPos, pos));
/* 64 */         lastPos = pos;
/*    */       }
/*    */     }
/*    */ 
/* 68 */     return result.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.ParseURLKeyword
 * JD-Core Version:    0.6.0
 */