package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsGuestbookExtDao
{
  public abstract CmsGuestbookExt findById(Integer paramInteger);

  public abstract CmsGuestbookExt save(CmsGuestbookExt paramCmsGuestbookExt);

  public abstract CmsGuestbookExt updateByUpdater(Updater<CmsGuestbookExt> paramUpdater);

  public abstract CmsGuestbookExt deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsGuestbookExtDao
 * JD-Core Version:    0.6.0
 */