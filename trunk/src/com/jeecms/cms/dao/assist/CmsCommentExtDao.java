package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsCommentExt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public abstract interface CmsCommentExtDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract CmsCommentExt findById(Integer paramInteger);

  public abstract CmsCommentExt save(CmsCommentExt paramCmsCommentExt);

  public abstract CmsCommentExt updateByUpdater(Updater<CmsCommentExt> paramUpdater);

  public abstract int deleteByContentId(Integer paramInteger);

  public abstract CmsCommentExt deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsCommentExtDao
 * JD-Core Version:    0.6.0
 */