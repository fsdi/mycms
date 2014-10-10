package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsLog;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;
import javax.servlet.http.HttpServletRequest;

public abstract interface CmsLogMng
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract CmsLog findById(Integer paramInteger);

  public abstract CmsLog operating(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2);

  public abstract CmsLog loginFailure(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2);

  public abstract CmsLog loginSuccess(HttpServletRequest paramHttpServletRequest, CmsUser paramCmsUser, String paramString);

  public abstract CmsLog save(CmsLog paramCmsLog);

  public abstract CmsLog deleteById(Integer paramInteger);

  public abstract CmsLog[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract int deleteBatch(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsLogMng
 * JD-Core Version:    0.6.0
 */