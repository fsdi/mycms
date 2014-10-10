package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsUserExtDao
{
  public abstract CmsUserExt findById(Integer paramInteger);

  public abstract CmsUserExt save(CmsUserExt paramCmsUserExt);

  public abstract CmsUserExt updateByUpdater(Updater<CmsUserExt> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsUserExtDao
 * JD-Core Version:    0.6.0
 */