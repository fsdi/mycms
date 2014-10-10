package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsAdvertising;
import com.jeecms.common.page.Pagination;
import java.util.List;
import java.util.Map;

public abstract interface CmsAdvertisingMng
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract List<CmsAdvertising> getList(Integer paramInteger, Boolean paramBoolean);

  public abstract CmsAdvertising findById(Integer paramInteger);

  public abstract CmsAdvertising save(CmsAdvertising paramCmsAdvertising, Integer paramInteger, Map<String, String> paramMap);

  public abstract CmsAdvertising update(CmsAdvertising paramCmsAdvertising, Integer paramInteger, Map<String, String> paramMap);

  public abstract CmsAdvertising deleteById(Integer paramInteger);

  public abstract CmsAdvertising[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract void display(Integer paramInteger);

  public abstract void click(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsAdvertisingMng
 * JD-Core Version:    0.6.0
 */