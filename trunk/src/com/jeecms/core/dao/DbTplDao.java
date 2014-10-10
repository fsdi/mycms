package com.jeecms.core.dao;

import com.jeecms.core.entity.DbTpl;
import java.util.List;

public abstract interface DbTplDao
{
  public abstract List<DbTpl> getStartWith(String paramString);

  public abstract List<DbTpl> getChild(String paramString, boolean paramBoolean);

  public abstract DbTpl findById(String paramString);

  public abstract DbTpl save(DbTpl paramDbTpl);

  public abstract DbTpl deleteById(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.DbTplDao
 * JD-Core Version:    0.6.0
 */