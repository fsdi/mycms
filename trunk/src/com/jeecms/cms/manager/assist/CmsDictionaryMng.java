package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsDictionary;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsDictionaryMng
{
  public abstract Pagination getPage(String paramString, int paramInt1, int paramInt2);

  public abstract List<CmsDictionary> getList(String paramString);

  public abstract List<String> getTypeList();

  public abstract CmsDictionary findById(Integer paramInteger);

  public abstract CmsDictionary findByValue(String paramString1, String paramString2);

  public abstract CmsDictionary save(CmsDictionary paramCmsDictionary);

  public abstract CmsDictionary update(CmsDictionary paramCmsDictionary);

  public abstract CmsDictionary deleteById(Integer paramInteger);

  public abstract CmsDictionary[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract boolean dicDeplicateValue(String paramString1, String paramString2);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsDictionaryMng
 * JD-Core Version:    0.6.0
 */