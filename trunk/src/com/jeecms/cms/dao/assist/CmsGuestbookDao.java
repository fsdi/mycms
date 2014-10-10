package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsGuestbookDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, int paramInt2);

  public abstract List<CmsGuestbook> getList(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, int paramInt2);

  public abstract CmsGuestbook findById(Integer paramInteger);

  public abstract CmsGuestbook save(CmsGuestbook paramCmsGuestbook);

  public abstract CmsGuestbook updateByUpdater(Updater<CmsGuestbook> paramUpdater);

  public abstract CmsGuestbook deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsGuestbookDao
 * JD-Core Version:    0.6.0
 */