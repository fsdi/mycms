/*     */ package com.jeecms.common.ipseek;
/*     */ 
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ public class IPSeekerImpl
/*     */   implements IPSeeker
/*     */ {
/*     */   private Map<String, IPLocation> ipCache;
/*     */   private RandomAccessFile ipFile;
/*     */   private long ipBegin;
/*     */   private long ipEnd;
/*     */   private IPLocation loc;
/*     */   private byte[] b4;
/*     */   private byte[] b3;
/*     */   private String dir;
/*     */   private String filename;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     String file = this.realPathResolver.get(this.dir) + File.separator + this.filename;
/*  31 */     this.ipCache = new HashMap();
/*  32 */     this.loc = new IPLocation();
/*  33 */     this.b4 = new byte[4];
/*  34 */     this.b3 = new byte[3];
/*     */     try {
/*  36 */       this.ipFile = new RandomAccessFile(file, "r");
/*     */     } catch (FileNotFoundException e) {
/*  38 */       throw new IPParseException("ip data file not found!", e);
/*     */     }
/*     */ 
/*  41 */     if (this.ipFile != null)
/*     */       try {
/*  43 */         this.ipBegin = readLong4(0L);
/*  44 */         this.ipEnd = readLong4(4L);
/*  45 */         if ((this.ipBegin == -1L) || (this.ipEnd == -1L)) {
/*  46 */           this.ipFile.close();
/*  47 */           this.ipFile = null;
/*     */         }
/*     */       }
/*     */       catch (IOException e) {
/*  51 */         this.ipFile = null;
/*     */       }
/*     */   }
/*     */ 
/*     */   public IPLocation getIPLocation(String ip)
/*     */   {
/*  57 */     return new IPLocation(getCountry(ip), getArea(ip));
/*     */   }
/*     */ 
/*     */   public String getCountry(byte[] ip)
/*     */   {
/*  69 */     if (this.ipFile == null) {
/*  70 */       return "IP地址库文件错误";
/*     */     }
/*  72 */     String ipStr = Util.getIpStringFromBytes(ip);
/*     */ 
/*  74 */     if (this.ipCache.containsKey(ipStr)) {
/*  75 */       IPLocation ipLoc = (IPLocation)this.ipCache.get(ipStr);
/*  76 */       return ipLoc.getCountry();
/*     */     }
/*  78 */     IPLocation ipLoc = getIPLocation(ip);
/*  79 */     this.ipCache.put(ipStr, ipLoc.getCopy());
/*  80 */     return ipLoc.getCountry();
/*     */   }
/*     */ 
/*     */   public String getCountry(String ip)
/*     */   {
/*  92 */     return getCountry(Util.getIpByteArrayFromString(ip));
/*     */   }
/*     */ 
/*     */   public String getArea(byte[] ip)
/*     */   {
/* 104 */     if (this.ipFile == null) {
/* 105 */       return "IP地址库文件错误";
/*     */     }
/* 107 */     String ipStr = Util.getIpStringFromBytes(ip);
/*     */ 
/* 109 */     if (this.ipCache.containsKey(ipStr)) {
/* 110 */       IPLocation ipLoc = (IPLocation)this.ipCache.get(ipStr);
/* 111 */       return ipLoc.getArea();
/*     */     }
/* 113 */     IPLocation ipLoc = getIPLocation(ip);
/* 114 */     this.ipCache.put(ipStr, ipLoc.getCopy());
/* 115 */     return ipLoc.getArea();
/*     */   }
/*     */ 
/*     */   public String getArea(String ip)
/*     */   {
/* 127 */     return getArea(Util.getIpByteArrayFromString(ip));
/*     */   }
/*     */ 
/*     */   private IPLocation getIPLocation(byte[] ip)
/*     */   {
/* 138 */     IPLocation info = null;
/* 139 */     long offset = locateIP(ip);
/* 140 */     if (offset != -1L) {
/* 141 */       info = getIPLocation(offset);
/*     */     }
/* 143 */     if (info == null) {
/* 144 */       info = new IPLocation();
/* 145 */       info.setCountry("未知国家");
/* 146 */       info.setArea("未知地区");
/*     */     }
/* 148 */     return info;
/*     */   }
/*     */ 
/*     */   private long readLong4(long offset)
/*     */   {
/* 158 */     long ret = 0L;
/*     */     try {
/* 160 */       this.ipFile.seek(offset);
/* 161 */       ret |= this.ipFile.readByte() & 0xFF;
/* 162 */       ret |= this.ipFile.readByte() << 8 & 0xFF00;
/* 163 */       ret |= this.ipFile.readByte() << 16 & 0xFF0000;
/* 164 */       ret |= this.ipFile.readByte() << 24 & 0xFF000000;
/* 165 */       return ret; } catch (IOException e) {
/*     */     }
/* 167 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long readLong3(long offset)
/*     */   {
/* 179 */     long ret = 0L;
/*     */     try {
/* 181 */       this.ipFile.seek(offset);
/* 182 */       this.ipFile.readFully(this.b3);
/* 183 */       ret |= this.b3[0] & 0xFF;
/* 184 */       ret |= this.b3[1] << 8 & 0xFF00;
/* 185 */       ret |= this.b3[2] << 16 & 0xFF0000;
/* 186 */       return ret; } catch (IOException e) {
/*     */     }
/* 188 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long readLong3()
/*     */   {
/* 198 */     long ret = 0L;
/*     */     try {
/* 200 */       this.ipFile.readFully(this.b3);
/* 201 */       ret |= this.b3[0] & 0xFF;
/* 202 */       ret |= this.b3[1] << 8 & 0xFF00;
/* 203 */       ret |= this.b3[2] << 16 & 0xFF0000;
/* 204 */       return ret; } catch (IOException e) {
/*     */     }
/* 206 */     return -1L;
/*     */   }
/*     */ 
/*     */   private void readIP(long offset, byte[] ip)
/*     */   {
/*     */     try
/*     */     {
/* 219 */       this.ipFile.seek(offset);
/* 220 */       this.ipFile.readFully(ip);
/* 221 */       byte temp = ip[0];
/* 222 */       ip[0] = ip[3];
/* 223 */       ip[3] = temp;
/* 224 */       temp = ip[1];
/* 225 */       ip[1] = ip[2];
/* 226 */       ip[2] = temp;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private int compareIP(byte[] ip, byte[] beginIp)
/*     */   {
/* 242 */     for (int i = 0; i < 4; i++) {
/* 243 */       int r = compareByte(ip[i], beginIp[i]);
/* 244 */       if (r != 0)
/* 245 */         return r;
/*     */     }
/* 247 */     return 0;
/*     */   }
/*     */ 
/*     */   private int compareByte(byte b1, byte b2)
/*     */   {
/* 258 */     if ((b1 & 0xFF) > (b2 & 0xFF))
/* 259 */       return 1;
/* 260 */     if ((b1 ^ b2) == 0) {
/* 261 */       return 0;
/*     */     }
/* 263 */     return -1;
/*     */   }
/*     */ 
/*     */   private long locateIP(byte[] ip)
/*     */   {
/* 274 */     long m = 0L;
/*     */ 
/* 277 */     readIP(this.ipBegin, this.b4);
/* 278 */     int r = compareIP(ip, this.b4);
/* 279 */     if (r == 0)
/* 280 */       return this.ipBegin;
/* 281 */     if (r < 0) {
/* 282 */       return -1L;
/*     */     }
/* 284 */     long i = this.ipBegin; for (long j = this.ipEnd; i < j; ) {
/* 285 */       m = getMiddleOffset(i, j);
/* 286 */       readIP(m, this.b4);
/* 287 */       r = compareIP(ip, this.b4);
/*     */ 
/* 289 */       if (r > 0)
/* 290 */         i = m;
/* 291 */       else if (r < 0) {
/* 292 */         if (m == j) {
/* 293 */           j -= 7L;
/* 294 */           m = j;
/*     */         } else {
/* 296 */           j = m;
/*     */         }
/*     */       } else return readLong3(m + 4L);
/*     */ 
/*     */     }
/*     */ 
/* 302 */     m = readLong3(m + 4L);
/* 303 */     readIP(m, this.b4);
/* 304 */     r = compareIP(ip, this.b4);
/* 305 */     if (r <= 0) {
/* 306 */       return m;
/*     */     }
/* 308 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long getMiddleOffset(long begin, long end)
/*     */   {
/* 319 */     long records = (end - begin) / 7L;
/* 320 */     records >>= 1;
/* 321 */     if (records == 0L)
/* 322 */       records = 1L;
/* 323 */     return begin + records * 7L;
/*     */   }
/*     */ 
/*     */   private IPLocation getIPLocation(long offset)
/*     */   {
/*     */     try
/*     */     {
/* 336 */       this.ipFile.seek(offset + 4L);
/*     */ 
/* 338 */       byte b = this.ipFile.readByte();
/* 339 */       if (b == 1)
/*     */       {
/* 341 */         long countryOffset = readLong3();
/*     */ 
/* 343 */         this.ipFile.seek(countryOffset);
/*     */ 
/* 345 */         b = this.ipFile.readByte();
/* 346 */         if (b == 2) {
/* 347 */           this.loc.setCountry(readString(readLong3()));
/* 348 */           this.ipFile.seek(countryOffset + 4L);
/*     */         } else {
/* 350 */           this.loc.setCountry(readString(countryOffset));
/*     */         }
/* 352 */         this.loc.setArea(readArea(this.ipFile.getFilePointer()));
/* 353 */       } else if (b == 2) {
/* 354 */         this.loc.setCountry(readString(readLong3()));
/* 355 */         this.loc.setArea(readArea(offset + 8L));
/*     */       } else {
/* 357 */         this.loc.setCountry(readString(this.ipFile.getFilePointer() - 1L));
/* 358 */         this.loc.setArea(readArea(this.ipFile.getFilePointer()));
/*     */       }
/* 360 */       return this.loc; } catch (IOException e) {
/*     */     }
/* 362 */     return null;
/*     */   }
/*     */ 
/*     */   private String readArea(long offset)
/*     */     throws IOException
/*     */   {
/* 375 */     this.ipFile.seek(offset);
/* 376 */     byte b = this.ipFile.readByte();
/* 377 */     if ((b == 1) || (b == 2)) {
/* 378 */       long areaOffset = readLong3(offset + 1L);
/* 379 */       if (areaOffset == 0L) {
/* 380 */         return "未知地区";
/*     */       }
/* 382 */       return readString(areaOffset);
/*     */     }
/* 384 */     return readString(offset);
/*     */   }
/*     */ 
/*     */   private String readString(long offset)
/*     */   {
/*     */     try
/*     */     {
/* 396 */       this.ipFile.seek(offset);
/* 397 */       int i = 0;
/* 398 */       byte[] buf = new byte[100];
/* 399 */       while ((buf[i] = this.ipFile.readByte()) != 0) {
/* 400 */         i++;
/* 401 */         if (i >= buf.length) {
/* 402 */           byte[] tmp = new byte[i + 100];
/* 403 */           System.arraycopy(buf, 0, tmp, 0, i);
/* 404 */           buf = tmp;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 409 */       if (i != 0)
/* 410 */         return Util.getString(buf, 0, i, "GBK");
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/* 415 */     return "";
/*     */   }
/*     */ 
/*     */   public void setDir(String dir)
/*     */   {
/* 422 */     this.dir = dir;
/*     */   }
/*     */ 
/*     */   public void setFilename(String filename) {
/* 426 */     this.filename = filename;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.ipseek.IPSeekerImpl
 * JD-Core Version:    0.6.0
 */