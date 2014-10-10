package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
import com.jeecms.cms.entity.assist.CmsVoteTopic;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;
import java.util.List;
import java.util.Set;

public abstract interface CmsVoteTopicMng
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract List<CmsVoteTopic> getList(Boolean paramBoolean, Integer paramInteger, int paramInt);

  public abstract CmsVoteTopic findById(Integer paramInteger);

  public abstract CmsVoteTopic getDefTopic(Integer paramInteger);

  public abstract CmsVoteTopic vote(Integer paramInteger, Integer[] paramArrayOfInteger, List<Integer[]> paramList, String[] paramArrayOfString, CmsUser paramCmsUser, String paramString1, String paramString2);

  public abstract CmsVoteTopic save(CmsVoteTopic paramCmsVoteTopic, Set<CmsVoteSubTopic> paramSet);

  public abstract CmsVoteTopic update(CmsVoteTopic paramCmsVoteTopic);

  public abstract CmsVoteTopic deleteById(Integer paramInteger);

  public abstract CmsVoteTopic[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsVoteTopicMng
 * JD-Core Version:    0.6.0
 */