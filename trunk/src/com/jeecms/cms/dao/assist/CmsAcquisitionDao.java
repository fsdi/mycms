package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAcquisition;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsAcquisitionDao
{
  public abstract List<CmsAcquisition> getList(Integer paramInteger);

  public abstract CmsAcquisition findById(Integer paramInteger);

  public abstract CmsAcquisition save(CmsAcquisition paramCmsAcquisition);

  public abstract CmsAcquisition updateByUpdater(Updater<CmsAcquisition> paramUpdater);

  public abstract CmsAcquisition deleteById(Integer paramInteger);

  public abstract int countByChannelId(Integer paramInteger);

  public abstract CmsAcquisition getStarted(Integer paramInteger);

  public abstract Integer getMaxQueue(Integer paramInteger);

  public abstract List<CmsAcquisition> getLargerQueues(Integer paramInteger1, Integer paramInteger2);

  public abstract CmsAcquisition popAcquFromQueue(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsAcquisitionDao
 * JD-Core Version:    0.6.0
 */