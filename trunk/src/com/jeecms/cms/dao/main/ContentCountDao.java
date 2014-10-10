package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentCount;
import com.jeecms.common.hibernate3.Updater;
import net.sf.ehcache.Ehcache;

public abstract interface ContentCountDao
{
  public abstract int clearCount(boolean paramBoolean1, boolean paramBoolean2);

  public abstract int copyCount();

  public abstract int freshCacheToDB(Ehcache paramEhcache);

  public abstract ContentCount findById(Integer paramInteger);

  public abstract ContentCount save(ContentCount paramContentCount);

  public abstract ContentCount updateByUpdater(Updater<ContentCount> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentCountDao
 * JD-Core Version:    0.6.0
 */