package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteItem;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public abstract interface CmsVoteItemDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract CmsVoteItem findById(Integer paramInteger);

  public abstract CmsVoteItem save(CmsVoteItem paramCmsVoteItem);

  public abstract CmsVoteItem updateByUpdater(Updater<CmsVoteItem> paramUpdater);

  public abstract CmsVoteItem deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsVoteItemDao
 * JD-Core Version:    0.6.0
 */