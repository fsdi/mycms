package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsAcquisitionHistoryDao
{
  public abstract List<CmsAcquisitionHistory> getList(Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4);

  public abstract CmsAcquisitionHistory findById(Integer paramInteger);

  public abstract CmsAcquisitionHistory save(CmsAcquisitionHistory paramCmsAcquisitionHistory);

  public abstract CmsAcquisitionHistory updateByUpdater(Updater<CmsAcquisitionHistory> paramUpdater);

  public abstract CmsAcquisitionHistory deleteById(Integer paramInteger);

  public abstract Boolean checkExistByProperties(Boolean paramBoolean, String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsAcquisitionHistoryDao
 * JD-Core Version:    0.6.0
 */