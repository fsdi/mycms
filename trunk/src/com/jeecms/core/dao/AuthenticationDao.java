package com.jeecms.core.dao;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.Authentication;
import java.util.Date;

public abstract interface AuthenticationDao
{
  public abstract int deleteExpire(Date paramDate);

  public abstract Authentication getByUserId(Long paramLong);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Authentication findById(String paramString);

  public abstract Authentication save(Authentication paramAuthentication);

  public abstract Authentication updateByUpdater(Updater<Authentication> paramUpdater);

  public abstract Authentication deleteById(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.AuthenticationDao
 * JD-Core Version:    0.6.0
 */