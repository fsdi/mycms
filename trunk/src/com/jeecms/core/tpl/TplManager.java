package com.jeecms.core.tpl;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public abstract interface TplManager
{
  public abstract List<? extends Tpl> getListByPrefix(String paramString);

  public abstract List<String> getNameListByPrefix(String paramString);

  public abstract List<? extends Tpl> getChild(String paramString);

  public abstract void save(String paramString1, String paramString2, boolean paramBoolean);

  public abstract void save(String paramString, MultipartFile paramMultipartFile);

  public abstract Tpl get(String paramString);

  public abstract void update(String paramString1, String paramString2);

  public abstract void rename(String paramString1, String paramString2);

  public abstract int delete(String[] paramArrayOfString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.tpl.TplManager
 * JD-Core Version:    0.6.0
 */