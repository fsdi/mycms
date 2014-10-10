package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteRecord;
import com.jeecms.cms.entity.assist.CmsVoteTopic;
import com.jeecms.cms.entity.main.CmsUser;
import java.util.Date;

public abstract interface CmsVoteRecordMng
{
  public abstract CmsVoteRecord save(CmsVoteTopic paramCmsVoteTopic, CmsUser paramCmsUser, String paramString1, String paramString2);

  public abstract int deleteByTopic(Integer paramInteger);

  public abstract Date lastVoteTimeByUserId(Integer paramInteger1, Integer paramInteger2);

  public abstract Date lastVoteTimeByIp(String paramString, Integer paramInteger);

  public abstract Date lastVoteTimeByCookie(String paramString, Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsVoteRecordMng
 * JD-Core Version:    0.6.0
 */