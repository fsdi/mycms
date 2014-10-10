package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentCheck;
import com.jeecms.common.hibernate3.Updater;

public abstract interface ContentCheckDao
{
  public abstract ContentCheck findById(Long paramLong);

  public abstract ContentCheck save(ContentCheck paramContentCheck);

  public abstract ContentCheck updateByUpdater(Updater<ContentCheck> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentCheckDao
 * JD-Core Version:    0.6.0
 */