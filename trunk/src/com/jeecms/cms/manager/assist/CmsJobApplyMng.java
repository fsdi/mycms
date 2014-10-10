package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsJobApply;
import com.jeecms.common.page.Pagination;

public abstract interface CmsJobApplyMng
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract CmsJobApply findById(Integer paramInteger);

  public abstract CmsJobApply save(CmsJobApply paramCmsJobApply);

  public abstract CmsJobApply update(CmsJobApply paramCmsJobApply);

  public abstract CmsJobApply deleteById(Integer paramInteger);

  public abstract CmsJobApply[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsJobApplyMng
 * JD-Core Version:    0.6.0
 */