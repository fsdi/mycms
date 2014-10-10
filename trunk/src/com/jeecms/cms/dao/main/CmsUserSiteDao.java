package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsUserSite;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsUserSiteDao
{
  public abstract CmsUserSite findById(Integer paramInteger);

  public abstract CmsUserSite save(CmsUserSite paramCmsUserSite);

  public abstract CmsUserSite updateByUpdater(Updater<CmsUserSite> paramUpdater);

  public abstract int deleteBySiteId(Integer paramInteger);

  public abstract CmsUserSite deleteById(Integer paramInteger);

  public abstract void delete(CmsUserSite paramCmsUserSite);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsUserSiteDao
 * JD-Core Version:    0.6.0
 */