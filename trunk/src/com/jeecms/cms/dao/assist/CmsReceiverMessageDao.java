package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsReceiverMessage;
import com.jeecms.common.page.Pagination;
import java.util.Date;
import java.util.List;

public abstract interface CmsReceiverMessageDao
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Date paramDate1, Date paramDate2, Boolean paramBoolean1, Integer paramInteger4, Boolean paramBoolean2, int paramInt1, int paramInt2);

  public abstract List<CmsReceiverMessage> getList(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Date paramDate1, Date paramDate2, Boolean paramBoolean1, Integer paramInteger4, Boolean paramBoolean2);

  public abstract CmsReceiverMessage findById(Integer paramInteger);

  public abstract CmsReceiverMessage save(CmsReceiverMessage paramCmsReceiverMessage);

  public abstract CmsReceiverMessage update(CmsReceiverMessage paramCmsReceiverMessage);

  public abstract CmsReceiverMessage deleteById(Integer paramInteger);

  public abstract CmsReceiverMessage[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsReceiverMessageDao
 * JD-Core Version:    0.6.0
 */