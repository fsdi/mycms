package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteTopic;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsVoteTopicDao
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract List<CmsVoteTopic> getList(Boolean paramBoolean, Integer paramInteger, int paramInt);

  public abstract CmsVoteTopic getDefTopic(Integer paramInteger);

  public abstract CmsVoteTopic findById(Integer paramInteger);

  public abstract CmsVoteTopic save(CmsVoteTopic paramCmsVoteTopic);

  public abstract CmsVoteTopic updateByUpdater(Updater<CmsVoteTopic> paramUpdater);

  public abstract CmsVoteTopic deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsVoteTopicDao
 * JD-Core Version:    0.6.0
 */