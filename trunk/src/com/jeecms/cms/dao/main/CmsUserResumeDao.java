package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsUserResume;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsUserResumeDao
{
  public abstract CmsUserResume findById(Integer paramInteger);

  public abstract CmsUserResume save(CmsUserResume paramCmsUserResume);

  public abstract CmsUserResume updateByUpdater(Updater<CmsUserResume> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsUserResumeDao
 * JD-Core Version:    0.6.0
 */