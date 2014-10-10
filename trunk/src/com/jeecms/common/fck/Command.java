/*     */ package com.jeecms.common.fck;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class Command
/*     */ {
/*     */   private String name;
/*  28 */   private static final Map<String, Command> getCommands = new HashMap(
/*  29 */     3);
/*     */ 
/*  30 */   private static final Map<String, Command> postCommands = new HashMap(
/*  31 */     2);
/*     */ 
/*  33 */   public static final Command GET_FOLDERS = new Command("GetFolders");
/*     */ 
/*  35 */   public static final Command GET_FOLDERS_AND_FILES = new Command(
/*  36 */     "GetFoldersAndFiles");
/*     */ 
/*  38 */   public static final Command CREATE_FOLDER = new Command("CreateFolder");
/*     */ 
/*  40 */   public static final Command FILE_UPLOAD = new Command("FileUpload");
/*     */ 
/*  42 */   public static final Command QUICK_UPLOAD = new Command("QuickUpload");
/*     */ 
/*     */   static
/*     */   {
/*  46 */     getCommands.put(GET_FOLDERS.getName(), GET_FOLDERS);
/*  47 */     getCommands.put(GET_FOLDERS_AND_FILES.getName(), GET_FOLDERS_AND_FILES);
/*  48 */     getCommands.put(CREATE_FOLDER.getName(), CREATE_FOLDER);
/*     */ 
/*  51 */     postCommands.put(FILE_UPLOAD.getName(), FILE_UPLOAD);
/*  52 */     postCommands.put(QUICK_UPLOAD.getName(), QUICK_UPLOAD);
/*     */   }
/*     */ 
/*     */   private Command(String name)
/*     */   {
/*  62 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  71 */     return this.name;
/*     */   }
/*     */ 
/*     */   public static Command valueOf(String name)
/*     */   {
/*  86 */     if (Utils.isEmpty(name)) {
/*  87 */       throw new NullPointerException("Name is null or empty");
/*     */     }
/*  89 */     Command command = (Command)getCommands.get(name);
/*  90 */     if (command == null)
/*  91 */       command = (Command)postCommands.get(name);
/*  92 */     if (command == null) {
/*  93 */       throw new IllegalArgumentException("No command const " + name);
/*     */     }
/*  95 */     return command;
/*     */   }
/*     */ 
/*     */   public static boolean isValidForGet(String name)
/*     */   {
/* 108 */     return getCommands.containsKey(name);
/*     */   }
/*     */ 
/*     */   public static boolean isValidForPost(String name)
/*     */   {
/* 121 */     return postCommands.containsKey(name);
/*     */   }
/*     */ 
/*     */   public static Command getCommand(String name)
/*     */   {
/*     */     try
/*     */     {
/* 136 */       return valueOf(name); } catch (Exception e) {
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 153 */     if (this == obj) {
/* 154 */       return true;
/*     */     }
/* 156 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 157 */       return false;
/*     */     }
/* 159 */     Command command = (Command)obj;
/* 160 */     return this.name.equals(command.getName());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 171 */     return this.name.hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 182 */     return this.name;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.fck.Command
 * JD-Core Version:    0.6.0
 */