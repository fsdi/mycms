package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsGroup;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsGroupDao
{
  public abstract List<CmsGroup> getList();

  public abstract CmsGroup getRegDef();

  public abstract CmsGroup findById(Integer paramInteger);

  public abstract CmsGroup save(CmsGroup paramCmsGroup);

  public abstract CmsGroup updateByUpdater(Updater<CmsGroup> paramUpdater);

  public abstract CmsGroup deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsGroupDao
 * JD-Core Version:    0.6.0
 */