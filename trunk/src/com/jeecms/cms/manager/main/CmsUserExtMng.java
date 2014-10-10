package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;

public abstract interface CmsUserExtMng
{
  public abstract CmsUserExt save(CmsUserExt paramCmsUserExt, CmsUser paramCmsUser);

  public abstract CmsUserExt update(CmsUserExt paramCmsUserExt, CmsUser paramCmsUser);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsUserExtMng
 * JD-Core Version:    0.6.0
 */