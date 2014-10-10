package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsAdvertisingSpaceDao
{
  public abstract List<CmsAdvertisingSpace> getList(Integer paramInteger);

  public abstract CmsAdvertisingSpace findById(Integer paramInteger);

  public abstract CmsAdvertisingSpace save(CmsAdvertisingSpace paramCmsAdvertisingSpace);

  public abstract CmsAdvertisingSpace updateByUpdater(Updater<CmsAdvertisingSpace> paramUpdater);

  public abstract CmsAdvertisingSpace deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsAdvertisingSpaceDao
 * JD-Core Version:    0.6.0
 */