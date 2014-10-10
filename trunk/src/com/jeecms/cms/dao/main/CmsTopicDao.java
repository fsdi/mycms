package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsTopic;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsTopicDao
{
  public abstract List<CmsTopic> getList(Integer paramInteger1, boolean paramBoolean1, Integer paramInteger2, boolean paramBoolean2);

  public abstract Pagination getPage(Integer paramInteger, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2);

  public abstract List<CmsTopic> getGlobalTopicList();

  public abstract List<CmsTopic> getListByChannelId(Integer paramInteger);

  public abstract List<CmsTopic> getListByChannelIds(Integer[] paramArrayOfInteger);

  public abstract CmsTopic findById(Integer paramInteger);

  public abstract CmsTopic save(CmsTopic paramCmsTopic);

  public abstract CmsTopic updateByUpdater(Updater<CmsTopic> paramUpdater);

  public abstract CmsTopic deleteById(Integer paramInteger);

  public abstract int deleteContentRef(Integer paramInteger);

  public abstract int countByChannelId(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsTopicDao
 * JD-Core Version:    0.6.0
 */