package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsConfig;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsConfigDao
{
  public abstract CmsConfig findById(Integer paramInteger);

  public abstract CmsConfig updateByUpdater(Updater<CmsConfig> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsConfigDao
 * JD-Core Version:    0.6.0
 */