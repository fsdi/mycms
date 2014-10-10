package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsAcquisition;
import com.jeecms.cms.entity.assist.CmsAcquisition.AcquisitionResultType;
import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
import com.jeecms.cms.entity.main.Content;
import java.util.Date;
import java.util.List;

public abstract interface CmsAcquisitionMng
{
  public abstract List<CmsAcquisition> getList(Integer paramInteger);

  public abstract CmsAcquisition findById(Integer paramInteger);

  public abstract void stop(Integer paramInteger);

  public abstract void pause(Integer paramInteger);

  public abstract CmsAcquisition start(Integer paramInteger);

  public abstract void end(Integer paramInteger);

  public abstract boolean isNeedBreak(Integer paramInteger, int paramInt1, int paramInt2, int paramInt3);

  public abstract CmsAcquisition save(CmsAcquisition paramCmsAcquisition, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4);

  public abstract CmsAcquisition update(CmsAcquisition paramCmsAcquisition, Integer paramInteger1, Integer paramInteger2);

  public abstract CmsAcquisition deleteById(Integer paramInteger);

  public abstract CmsAcquisition[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract Content saveContent(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Date paramDate, Integer paramInteger, CmsAcquisition.AcquisitionResultType paramAcquisitionResultType, CmsAcquisitionTemp paramCmsAcquisitionTemp, CmsAcquisitionHistory paramCmsAcquisitionHistory);

  public abstract CmsAcquisition getStarted(Integer paramInteger);

  public abstract Integer getMaxQueue(Integer paramInteger);

  public abstract Integer hasStarted(Integer paramInteger);

  public abstract void addToQueue(Integer[] paramArrayOfInteger, Integer paramInteger);

  public abstract void cancel(Integer paramInteger1, Integer paramInteger2);

  public abstract List<CmsAcquisition> getLargerQueues(Integer paramInteger1, Integer paramInteger2);

  public abstract CmsAcquisition popAcquFromQueue(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsAcquisitionMng
 * JD-Core Version:    0.6.0
 */