package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserResume;

public abstract interface CmsUserResumeMng
{
  public abstract CmsUserResume save(CmsUserResume paramCmsUserResume, CmsUser paramCmsUser);

  public abstract CmsUserResume update(CmsUserResume paramCmsUserResume, CmsUser paramCmsUser);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsUserResumeMng
 * JD-Core Version:    0.6.0
 */