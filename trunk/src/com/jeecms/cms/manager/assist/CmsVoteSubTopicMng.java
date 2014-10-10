package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteItem;
import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
import com.jeecms.cms.entity.assist.CmsVoteTopic;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract interface CmsVoteSubTopicMng
{
  public abstract CmsVoteSubTopic findById(Integer paramInteger);

  public abstract List<CmsVoteSubTopic> findByVoteTopic(Integer paramInteger);

  public abstract CmsVoteTopic save(CmsVoteTopic paramCmsVoteTopic, Set<CmsVoteSubTopic> paramSet);

  public abstract CmsVoteSubTopic save(CmsVoteSubTopic paramCmsVoteSubTopic, List<CmsVoteItem> paramList);

  public abstract CmsVoteSubTopic update(CmsVoteSubTopic paramCmsVoteSubTopic, Collection<CmsVoteItem> paramCollection);

  public abstract CmsVoteTopic update(CmsVoteTopic paramCmsVoteTopic, Collection<CmsVoteSubTopic> paramCollection);

  public abstract Collection<CmsVoteSubTopic> update(Collection<CmsVoteSubTopic> paramCollection, CmsVoteTopic paramCmsVoteTopic);

  public abstract CmsVoteSubTopic deleteById(Integer paramInteger);

  public abstract CmsVoteSubTopic[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsVoteSubTopicMng
 * JD-Core Version:    0.6.0
 */