package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsRole;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsRoleDao
{
  public abstract List<CmsRole> getList();

  public abstract CmsRole findById(Integer paramInteger);

  public abstract CmsRole save(CmsRole paramCmsRole);

  public abstract CmsRole updateByUpdater(Updater<CmsRole> paramUpdater);

  public abstract CmsRole deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsRoleDao
 * JD-Core Version:    0.6.0
 */