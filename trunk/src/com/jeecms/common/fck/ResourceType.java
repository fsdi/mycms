/*     */ package com.jeecms.common.fck;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ResourceType
/*     */ {
/*     */   private String name;
/*     */   private String path;
/*     */   private Set<String> allowedEextensions;
/*     */   private Set<String> deniedExtensions;
/*  35 */   private static Map<String, ResourceType> types = new HashMap(
/*  36 */     4);
/*     */ 
/*  39 */   public static final ResourceType FILE = new ResourceType("File", 
/*  40 */     PropertiesLoader.getFileResourceTypePath(), 
/*  41 */     Utils.getSet(
/*  42 */     PropertiesLoader.getFileResourceTypeAllowedExtensions()), 
/*  43 */     Utils.getSet(
/*  44 */     PropertiesLoader.getFileResourceTypeDeniedExtensions()));
/*     */ 
/*  46 */   public static final ResourceType FLASH = new ResourceType("Flash", 
/*  47 */     PropertiesLoader.getFlashResourceTypePath(), 
/*  48 */     Utils.getSet(
/*  49 */     PropertiesLoader.getFlashResourceTypeAllowedExtensions()), 
/*  50 */     Utils.getSet(
/*  51 */     PropertiesLoader.getFlashResourceTypeDeniedExtensions()));
/*     */ 
/*  53 */   public static final ResourceType IMAGE = new ResourceType("Image", 
/*  54 */     PropertiesLoader.getImageResourceTypePath(), 
/*  55 */     Utils.getSet(
/*  56 */     PropertiesLoader.getImageResourceTypeAllowedExtensions()), 
/*  57 */     Utils.getSet(
/*  58 */     PropertiesLoader.getImageResourceTypeDeniedExtensions()));
/*     */ 
/*  60 */   public static final ResourceType MEDIA = new ResourceType("Media", 
/*  61 */     PropertiesLoader.getMediaResourceTypePath(), 
/*  62 */     Utils.getSet(
/*  63 */     PropertiesLoader.getMediaResourceTypeAllowedExtensions()), 
/*  64 */     Utils.getSet(
/*  65 */     PropertiesLoader.getMediaResourceTypeDeniedExtensions()));
/*     */ 
/*     */   static
/*     */   {
/*  68 */     types.put(FILE.getName(), FILE);
/*  69 */     types.put(FLASH.getName(), FLASH);
/*  70 */     types.put(IMAGE.getName(), IMAGE);
/*  71 */     types.put(MEDIA.getName(), MEDIA);
/*     */   }
/*     */ 
/*     */   private ResourceType(String name, String path, Set<String> allowedEextensions, Set<String> deniedExtensions)
/*     */   {
/*  94 */     this.name = name;
/*  95 */     this.path = path;
/*     */ 
/*  97 */     if ((allowedEextensions.isEmpty()) && (deniedExtensions.isEmpty())) {
/*  98 */       throw new IllegalArgumentException(
/*  99 */         "Both sets are empty, one has always to be filled");
/*     */     }
/* 101 */     if ((!allowedEextensions.isEmpty()) && (!deniedExtensions.isEmpty())) {
/* 102 */       throw new IllegalArgumentException(
/* 103 */         "Both sets contain extensions, only one can be filled at the same time");
/*     */     }
/* 105 */     this.allowedEextensions = allowedEextensions;
/* 106 */     this.deniedExtensions = deniedExtensions;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 115 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 126 */     return this.path;
/*     */   }
/*     */ 
/*     */   public Set<String> getAllowedEextensions()
/*     */   {
/* 135 */     return Collections.unmodifiableSet(this.allowedEextensions);
/*     */   }
/*     */ 
/*     */   public Set<String> getDeniedExtensions()
/*     */   {
/* 144 */     return Collections.unmodifiableSet(this.deniedExtensions);
/*     */   }
/*     */ 
/*     */   public static ResourceType valueOf(String name)
/*     */   {
/* 159 */     if (Utils.isEmpty(name)) {
/* 160 */       throw new NullPointerException("Name is null or empty");
/*     */     }
/* 162 */     ResourceType rt = (ResourceType)types.get(name);
/* 163 */     if (rt == null)
/* 164 */       throw new IllegalArgumentException("No resource type const " + name);
/* 165 */     return rt;
/*     */   }
/*     */ 
/*     */   public static boolean isValidType(String name)
/*     */   {
/* 179 */     return types.containsKey(name);
/*     */   }
/*     */ 
/*     */   public static ResourceType getResourceType(String name)
/*     */   {
/*     */     try
/*     */     {
/* 194 */       return valueOf(name); } catch (Exception e) {
/*     */     }
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */   public static ResourceType getDefaultResourceType(String name)
/*     */   {
/* 211 */     ResourceType rt = getResourceType(name);
/* 212 */     return rt == null ? FILE : rt;
/*     */   }
/*     */ 
/*     */   public boolean isAllowedExtension(String extension)
/*     */   {
/* 227 */     if (Utils.isEmpty(extension))
/* 228 */       return false;
/* 229 */     String ext = extension.toLowerCase();
/* 230 */     if (this.allowedEextensions.isEmpty())
/* 231 */       return !this.deniedExtensions.contains(ext);
/* 232 */     if (this.deniedExtensions.isEmpty())
/* 233 */       return this.allowedEextensions.contains(ext);
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean isNotAllowedExtension(String extension)
/*     */   {
/* 251 */     return !isAllowedExtension(extension);
/*     */   }
/*     */ 
/*     */   public boolean isDeniedExtension(String extension)
/*     */   {
/* 263 */     return !isAllowedExtension(extension);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 277 */     if (this == obj) {
/* 278 */       return true;
/*     */     }
/* 280 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 281 */       return false;
/*     */     }
/* 283 */     ResourceType rt = (ResourceType)obj;
/* 284 */     return this.name.equals(rt.getName());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 295 */     return this.name.hashCode();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.fck.ResourceType
 * JD-Core Version:    0.6.0
 */