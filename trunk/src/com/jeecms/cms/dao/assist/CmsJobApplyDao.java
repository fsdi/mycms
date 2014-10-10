package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsJobApply;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public abstract interface CmsJobApplyDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract CmsJobApply findById(Integer paramInteger);

  public abstract CmsJobApply save(CmsJobApply paramCmsJobApply);

  public abstract CmsJobApply updateByUpdater(Updater<CmsJobApply> paramUpdater);

  public abstract CmsJobApply deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsJobApplyDao
 * JD-Core Version:    0.6.0
 */