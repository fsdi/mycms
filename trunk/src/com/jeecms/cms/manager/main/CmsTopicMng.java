package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsTopic;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsTopicMng
{
  public abstract List<CmsTopic> getListForTag(Integer paramInteger1, boolean paramBoolean, Integer paramInteger2);

  public abstract Pagination getPageForTag(Integer paramInteger, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract List<CmsTopic> getListByChannel(Integer paramInteger);

  public abstract CmsTopic findById(Integer paramInteger);

  public abstract CmsTopic save(CmsTopic paramCmsTopic, Integer paramInteger);

  public abstract CmsTopic update(CmsTopic paramCmsTopic, Integer paramInteger);

  public abstract CmsTopic deleteById(Integer paramInteger);

  public abstract CmsTopic[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract CmsTopic[] updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsTopicMng
 * JD-Core Version:    0.6.0
 */