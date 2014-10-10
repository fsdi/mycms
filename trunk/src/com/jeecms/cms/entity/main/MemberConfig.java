/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.common.util.StrUtils;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class MemberConfig
/*     */ {
/*     */   private Map<String, String> attr;
/*  34 */   public static String REGISTER_ON = "register_on";
/*     */ 
/*  38 */   public static String MEMBER_ON = "member_on";
/*     */ 
/*  42 */   public static String USERNAME_RESERVED = "username_reserved";
/*     */ 
/*  46 */   public static String USERNAME_MIN_LEN = "username_min_len";
/*     */ 
/*  50 */   public static String PASSWORD_MIN_LEN = "password_min_len";
/*     */ 
/*  54 */   public static String USERIMG_WIDTH = "user_img_width";
/*     */ 
/*  58 */   public static String USERIMG_HEIGHT = "user_img_height";
/*     */ 
/*     */   public MemberConfig()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MemberConfig(Map<String, String> attr)
/*     */   {
/*  15 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/*  21 */     if (this.attr == null) {
/*  22 */       this.attr = new HashMap();
/*     */     }
/*  24 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr) {
/*  28 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean isRegisterOn()
/*     */   {
/*  66 */     String registerOn = (String)getAttr().get(REGISTER_ON);
/*  67 */     return !"false".equals(registerOn);
/*     */   }
/*     */ 
/*     */   public void setRegisterOn(boolean registerOn)
/*     */   {
/*  76 */     getAttr().put(REGISTER_ON, String.valueOf(registerOn));
/*     */   }
/*     */ 
/*     */   public boolean isMemberOn()
/*     */   {
/*  85 */     String memberOn = (String)getAttr().get(MEMBER_ON);
/*  86 */     return !"false".equals(memberOn);
/*     */   }
/*     */ 
/*     */   public void setMemberOn(boolean memberOn)
/*     */   {
/*  95 */     getAttr().put(MEMBER_ON, String.valueOf(memberOn));
/*     */   }
/*     */ 
/*     */   public String getUsernameReserved()
/*     */   {
/* 106 */     return (String)getAttr().get(USERNAME_RESERVED);
/*     */   }
/*     */ 
/*     */   public void setUsernameReserved(String usernameReserved)
/*     */   {
/* 113 */     getAttr().put(USERNAME_RESERVED, usernameReserved);
/*     */   }
/*     */ 
/*     */   public boolean checkUsernameReserved(String name)
/*     */   {
/* 122 */     if (StringUtils.isBlank(name)) {
/* 123 */       return false;
/*     */     }
/* 125 */     String reserved = getUsernameReserved();
/* 126 */     if (StringUtils.isBlank(reserved)) {
/* 127 */       return true;
/*     */     }
/* 129 */     for (String search : StringUtils.split(reserved)) {
/* 130 */       if (StrUtils.contains(name, search)) {
/* 131 */         return false;
/*     */       }
/*     */     }
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   public int getUsernameMinLen()
/*     */   {
/* 143 */     String len = (String)getAttr().get(USERNAME_MIN_LEN);
/* 144 */     if (!StringUtils.isBlank(len))
/*     */       try {
/* 146 */         return Integer.valueOf(len).intValue();
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException)
/*     */       {
/*     */       }
/* 151 */     return 3;
/*     */   }
/*     */ 
/*     */   public void setUsernameMinLen(int len)
/*     */   {
/* 160 */     getAttr().put(USERNAME_MIN_LEN, String.valueOf(len));
/*     */   }
/*     */ 
/*     */   public int getPasswordMinLen()
/*     */   {
/* 169 */     String len = (String)getAttr().get(PASSWORD_MIN_LEN);
/* 170 */     if (!StringUtils.isBlank(len))
/*     */       try {
/* 172 */         return Integer.valueOf(len).intValue();
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException) {
/*     */       }
/* 176 */     return 3;
/*     */   }
/*     */ 
/*     */   public void setPasswordMinLen(int len)
/*     */   {
/* 185 */     getAttr().put(PASSWORD_MIN_LEN, String.valueOf(len));
/*     */   }
/*     */ 
/*     */   public int getUserImgWidth()
/*     */   {
/* 194 */     String len = (String)getAttr().get(USERIMG_WIDTH);
/* 195 */     if (!StringUtils.isBlank(len))
/*     */       try {
/* 197 */         return Integer.valueOf(len).intValue();
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException)
/*     */       {
/*     */       }
/* 202 */     return 143;
/*     */   }
/*     */ 
/*     */   public void setUserImgWidth(int width)
/*     */   {
/* 211 */     getAttr().put(USERIMG_WIDTH, String.valueOf(width));
/*     */   }
/*     */ 
/*     */   public int getUserImgHeight()
/*     */   {
/* 219 */     String len = (String)getAttr().get(USERIMG_HEIGHT);
/* 220 */     if (!StringUtils.isBlank(len))
/*     */       try {
/* 222 */         return Integer.valueOf(len).intValue();
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException)
/*     */       {
/*     */       }
/* 227 */     return 98;
/*     */   }
/*     */ 
/*     */   public void setUserImgHeight(int height)
/*     */   {
/* 236 */     getAttr().put(USERIMG_HEIGHT, String.valueOf(height));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.MemberConfig
 * JD-Core Version:    0.6.0
 */