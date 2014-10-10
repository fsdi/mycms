package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteRecord;

public abstract interface CmsVoteRecordDao
{
  public abstract CmsVoteRecord save(CmsVoteRecord paramCmsVoteRecord);

  public abstract int deleteByTopic(Integer paramInteger);

  public abstract CmsVoteRecord findByUserId(Integer paramInteger1, Integer paramInteger2);

  public abstract CmsVoteRecord findByIp(String paramString, Integer paramInteger);

  public abstract CmsVoteRecord findByCookie(String paramString, Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsVoteRecordDao
 * JD-Core Version:    0.6.0
 */