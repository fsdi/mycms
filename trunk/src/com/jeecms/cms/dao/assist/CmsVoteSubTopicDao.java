package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsVoteSubTopicDao
{
  public abstract List<CmsVoteSubTopic> findByVoteTopic(Integer paramInteger);

  public abstract CmsVoteSubTopic findById(Integer paramInteger);

  public abstract CmsVoteSubTopic save(CmsVoteSubTopic paramCmsVoteSubTopic);

  public abstract CmsVoteSubTopic updateByUpdater(Updater<CmsVoteSubTopic> paramUpdater);

  public abstract CmsVoteSubTopic deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsVoteSubTopicDao
 * JD-Core Version:    0.6.0
 */