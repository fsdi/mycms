package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsModelItem;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsModelItemDao
{
  public abstract List<CmsModelItem> getList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2);

  public abstract CmsModelItem findById(Integer paramInteger);

  public abstract CmsModelItem save(CmsModelItem paramCmsModelItem);

  public abstract CmsModelItem updateByUpdater(Updater<CmsModelItem> paramUpdater);

  public abstract CmsModelItem deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsModelItemDao
 * JD-Core Version:    0.6.0
 */