package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsAcquisitionTempDao
{
  public abstract List<CmsAcquisitionTemp> getList(Integer paramInteger);

  public abstract CmsAcquisitionTemp findById(Integer paramInteger);

  public abstract CmsAcquisitionTemp save(CmsAcquisitionTemp paramCmsAcquisitionTemp);

  public abstract CmsAcquisitionTemp updateByUpdater(Updater<CmsAcquisitionTemp> paramUpdater);

  public abstract CmsAcquisitionTemp deleteById(Integer paramInteger);

  public abstract Integer getPercent(Integer paramInteger);

  public abstract void clear(Integer paramInteger, String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsAcquisitionTempDao
 * JD-Core Version:    0.6.0
 */