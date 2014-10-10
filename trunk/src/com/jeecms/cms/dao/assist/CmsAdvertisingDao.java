package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAdvertising;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsAdvertisingDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract List<CmsAdvertising> getList(Integer paramInteger, Boolean paramBoolean);

  public abstract CmsAdvertising findById(Integer paramInteger);

  public abstract CmsAdvertising save(CmsAdvertising paramCmsAdvertising);

  public abstract CmsAdvertising updateByUpdater(Updater<CmsAdvertising> paramUpdater);

  public abstract CmsAdvertising deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsAdvertisingDao
 * JD-Core Version:    0.6.0
 */