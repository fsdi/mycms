package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
import java.util.List;

public abstract interface CmsAcquisitionTempMng
{
  public abstract List<CmsAcquisitionTemp> getList(Integer paramInteger);

  public abstract CmsAcquisitionTemp findById(Integer paramInteger);

  public abstract CmsAcquisitionTemp save(CmsAcquisitionTemp paramCmsAcquisitionTemp);

  public abstract CmsAcquisitionTemp update(CmsAcquisitionTemp paramCmsAcquisitionTemp);

  public abstract CmsAcquisitionTemp deleteById(Integer paramInteger);

  public abstract CmsAcquisitionTemp[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract Integer getPercent(Integer paramInteger);

  public abstract void clear(Integer paramInteger);

  public abstract void clear(Integer paramInteger, String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsAcquisitionTempMng
 * JD-Core Version:    0.6.0
 */