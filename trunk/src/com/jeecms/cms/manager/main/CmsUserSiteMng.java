package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserSite;

public abstract interface CmsUserSiteMng
{
  public abstract CmsUserSite findById(Integer paramInteger);

  public abstract CmsUserSite save(CmsSite paramCmsSite, CmsUser paramCmsUser, Byte paramByte, Boolean paramBoolean);

  public abstract CmsUserSite update(CmsUserSite paramCmsUserSite);

  public abstract void updateByUser(CmsUser paramCmsUser, Integer paramInteger, Byte paramByte, Boolean paramBoolean);

  public abstract void updateByUser(CmsUser paramCmsUser, Integer[] paramArrayOfInteger, Byte[] paramArrayOfByte, Boolean[] paramArrayOfBoolean);

  public abstract int deleteBySiteId(Integer paramInteger);

  public abstract CmsUserSite deleteById(Integer paramInteger);

  public abstract CmsUserSite[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsUserSiteMng
 * JD-Core Version:    0.6.0
 */