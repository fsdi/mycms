package com.jeecms.core.dao;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import java.util.List;

public abstract interface UnifiedUserDao
{
  public abstract UnifiedUser getByUsername(String paramString);

  public abstract List<UnifiedUser> getByEmail(String paramString);

  public abstract int countByEmail(String paramString);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract UnifiedUser findById(Integer paramInteger);

  public abstract UnifiedUser save(UnifiedUser paramUnifiedUser);

  public abstract UnifiedUser updateByUpdater(Updater<UnifiedUser> paramUpdater);

  public abstract UnifiedUser deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.UnifiedUserDao
 * JD-Core Version:    0.6.0
 */