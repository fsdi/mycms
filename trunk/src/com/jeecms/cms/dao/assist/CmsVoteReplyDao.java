package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteReply;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public abstract interface CmsVoteReplyDao
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract CmsVoteReply findById(Integer paramInteger);

  public abstract CmsVoteReply save(CmsVoteReply paramCmsVoteReply);

  public abstract CmsVoteReply updateByUpdater(Updater<CmsVoteReply> paramUpdater);

  public abstract CmsVoteReply deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsVoteReplyDao
 * JD-Core Version:    0.6.0
 */