package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
import java.util.List;

public abstract interface CmsAdvertisingSpaceMng
{
  public abstract List<CmsAdvertisingSpace> getList(Integer paramInteger);

  public abstract CmsAdvertisingSpace findById(Integer paramInteger);

  public abstract CmsAdvertisingSpace save(CmsAdvertisingSpace paramCmsAdvertisingSpace);

  public abstract CmsAdvertisingSpace update(CmsAdvertisingSpace paramCmsAdvertisingSpace);

  public abstract CmsAdvertisingSpace deleteById(Integer paramInteger);

  public abstract CmsAdvertisingSpace[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng
 * JD-Core Version:    0.6.0
 */