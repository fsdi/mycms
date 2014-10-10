package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsLog;
import com.jeecms.common.page.Pagination;
import java.util.Date;

public abstract interface CmsLogDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract CmsLog findById(Integer paramInteger);

  public abstract CmsLog save(CmsLog paramCmsLog);

  public abstract CmsLog deleteById(Integer paramInteger);

  public abstract int deleteBatch(Integer paramInteger1, Integer paramInteger2, Date paramDate);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsLogDao
 * JD-Core Version:    0.6.0
 */