package com.jeecms.core.dao;

import com.jeecms.core.entity.Config;
import java.util.List;

public abstract interface ConfigDao
{
  public abstract List<Config> getList();

  public abstract Config findById(String paramString);

  public abstract Config save(Config paramConfig);

  public abstract Config deleteById(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.ConfigDao
 * JD-Core Version:    0.6.0
 */