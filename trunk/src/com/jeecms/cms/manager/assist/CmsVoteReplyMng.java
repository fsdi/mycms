package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteReply;
import com.jeecms.common.page.Pagination;

public abstract interface CmsVoteReplyMng
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract CmsVoteReply findById(Integer paramInteger);

  public abstract CmsVoteReply save(CmsVoteReply paramCmsVoteReply);

  public abstract CmsVoteReply update(CmsVoteReply paramCmsVoteReply);

  public abstract CmsVoteReply deleteById(Integer paramInteger);

  public abstract CmsVoteReply[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsVoteReplyMng
 * JD-Core Version:    0.6.0
 */