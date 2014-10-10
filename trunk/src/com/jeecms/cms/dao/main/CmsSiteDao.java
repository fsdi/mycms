package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsSiteDao
{
  public abstract int siteCount(boolean paramBoolean);

  public abstract List<CmsSite> getList(boolean paramBoolean);

  public abstract CmsSite findByDomain(String paramString, boolean paramBoolean);

  public abstract CmsSite findById(Integer paramInteger);

  public abstract CmsSite save(CmsSite paramCmsSite);

  public abstract CmsSite updateByUpdater(Updater<CmsSite> paramUpdater);

  public abstract CmsSite deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsSiteDao
 * JD-Core Version:    0.6.0
 */