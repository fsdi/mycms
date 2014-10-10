package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsUserDao
{
  public abstract Pagination getPage(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger3, int paramInt1, int paramInt2);

  public abstract List<CmsUser> getList(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger3);

  public abstract List<CmsUser> getAdminList(Integer paramInteger1, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger2);

  public abstract CmsUser findById(Integer paramInteger);

  public abstract CmsUser findByUsername(String paramString);

  public abstract int countByUsername(String paramString);

  public abstract int countMemberByUsername(String paramString);

  public abstract int countByEmail(String paramString);

  public abstract CmsUser save(CmsUser paramCmsUser);

  public abstract CmsUser updateByUpdater(Updater<CmsUser> paramUpdater);

  public abstract CmsUser deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsUserDao
 * JD-Core Version:    0.6.0
 */