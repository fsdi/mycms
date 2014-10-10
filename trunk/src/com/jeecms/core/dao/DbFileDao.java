package com.jeecms.core.dao;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.entity.DbFile;

public abstract interface DbFileDao
{
  public abstract DbFile findById(String paramString);

  public abstract DbFile save(DbFile paramDbFile);

  public abstract DbFile updateByUpdater(Updater<DbFile> paramUpdater);

  public abstract DbFile deleteById(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.DbFileDao
 * JD-Core Version:    0.6.0
 */