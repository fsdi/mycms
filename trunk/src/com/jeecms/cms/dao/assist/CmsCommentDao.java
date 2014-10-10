package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsComment;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsCommentDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3);

  public abstract List<CmsComment> getList(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean, boolean paramBoolean1, boolean paramBoolean2, int paramInt, boolean paramBoolean3);

  public abstract CmsComment findById(Integer paramInteger);

  public abstract int deleteByContentId(Integer paramInteger);

  public abstract CmsComment save(CmsComment paramCmsComment);

  public abstract CmsComment updateByUpdater(Updater<CmsComment> paramUpdater);

  public abstract CmsComment deleteById(Integer paramInteger);

  public abstract Pagination getPageForMember(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean3, int paramInt1, int paramInt2, boolean paramBoolean4);

  public abstract List<CmsComment> getListForDel(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsCommentDao
 * JD-Core Version:    0.6.0
 */