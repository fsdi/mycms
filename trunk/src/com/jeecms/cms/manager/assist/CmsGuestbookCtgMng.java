package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import java.util.List;

public abstract interface CmsGuestbookCtgMng
{
  public abstract List<CmsGuestbookCtg> getList(Integer paramInteger);

  public abstract CmsGuestbookCtg findById(Integer paramInteger);

  public abstract CmsGuestbookCtg save(CmsGuestbookCtg paramCmsGuestbookCtg);

  public abstract CmsGuestbookCtg update(CmsGuestbookCtg paramCmsGuestbookCtg);

  public abstract CmsGuestbookCtg deleteById(Integer paramInteger);

  public abstract CmsGuestbookCtg[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsGuestbookCtgMng
 * JD-Core Version:    0.6.0
 */