package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsGuestbookCtgDao
{
  public abstract List<CmsGuestbookCtg> getList(Integer paramInteger);

  public abstract CmsGuestbookCtg findById(Integer paramInteger);

  public abstract CmsGuestbookCtg save(CmsGuestbookCtg paramCmsGuestbookCtg);

  public abstract CmsGuestbookCtg updateByUpdater(Updater<CmsGuestbookCtg> paramUpdater);

  public abstract CmsGuestbookCtg deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsGuestbookCtgDao
 * JD-Core Version:    0.6.0
 */