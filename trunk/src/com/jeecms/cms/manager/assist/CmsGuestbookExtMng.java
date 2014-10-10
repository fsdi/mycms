package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;

public abstract interface CmsGuestbookExtMng
{
  public abstract CmsGuestbookExt save(CmsGuestbookExt paramCmsGuestbookExt, CmsGuestbook paramCmsGuestbook);

  public abstract CmsGuestbookExt update(CmsGuestbookExt paramCmsGuestbookExt);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsGuestbookExtMng
 * JD-Core Version:    0.6.0
 */