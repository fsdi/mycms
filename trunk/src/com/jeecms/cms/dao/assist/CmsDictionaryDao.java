package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsDictionary;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsDictionaryDao
{
  public abstract Pagination getPage(String paramString, int paramInt1, int paramInt2);

  public abstract List<CmsDictionary> getList(String paramString);

  public abstract List<String> getTypeList();

  public abstract CmsDictionary findById(Integer paramInteger);

  public abstract CmsDictionary findByValue(String paramString1, String paramString2);

  public abstract CmsDictionary save(CmsDictionary paramCmsDictionary);

  public abstract CmsDictionary updateByUpdater(Updater<CmsDictionary> paramUpdater);

  public abstract CmsDictionary deleteById(Integer paramInteger);

  public abstract int countByValue(String paramString1, String paramString2);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsDictionaryDao
 * JD-Core Version:    0.6.0
 */