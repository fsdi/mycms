package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteItem;
import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
import com.jeecms.common.page.Pagination;
import java.util.Collection;

public abstract interface CmsVoteItemMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract CmsVoteItem findById(Integer paramInteger);

  public abstract Collection<CmsVoteItem> save(Collection<CmsVoteItem> paramCollection, CmsVoteSubTopic paramCmsVoteSubTopic);

  public abstract Collection<CmsVoteItem> update(Collection<CmsVoteItem> paramCollection, CmsVoteSubTopic paramCmsVoteSubTopic);

  public abstract CmsVoteItem deleteById(Integer paramInteger);

  public abstract CmsVoteItem[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsVoteItemMng
 * JD-Core Version:    0.6.0
 */