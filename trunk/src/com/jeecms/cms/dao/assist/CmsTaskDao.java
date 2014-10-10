package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsTask;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsTaskDao
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract List<CmsTask> getList();

  public abstract CmsTask findById(Integer paramInteger);

  public abstract CmsTask save(CmsTask paramCmsTask);

  public abstract CmsTask updateByUpdater(Updater<CmsTask> paramUpdater);

  public abstract CmsTask deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsTaskDao
 * JD-Core Version:    0.6.0
 */