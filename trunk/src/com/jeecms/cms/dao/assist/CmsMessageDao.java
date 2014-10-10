package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsMessage;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.Date;

public abstract interface CmsMessageDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Date paramDate1, Date paramDate2, Boolean paramBoolean1, Integer paramInteger4, Boolean paramBoolean2, int paramInt1, int paramInt2);

  public abstract CmsMessage findById(Integer paramInteger);

  public abstract CmsMessage save(CmsMessage paramCmsMessage);

  public abstract CmsMessage updateByUpdater(Updater<CmsMessage> paramUpdater);

  public abstract CmsMessage update(CmsMessage paramCmsMessage);

  public abstract CmsMessage deleteById(Integer paramInteger);

  public abstract CmsMessage[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsMessageDao
 * JD-Core Version:    0.6.0
 */