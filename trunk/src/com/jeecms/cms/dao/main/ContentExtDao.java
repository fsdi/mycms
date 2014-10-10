package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface ContentExtDao
{
  public abstract ContentExt findById(Integer paramInteger);

  public abstract ContentExt save(ContentExt paramContentExt);

  public abstract ContentExt updateByUpdater(Updater<ContentExt> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentExtDao
 * JD-Core Version:    0.6.0
 */