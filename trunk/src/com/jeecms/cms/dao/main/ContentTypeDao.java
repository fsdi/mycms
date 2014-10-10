package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentType;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface ContentTypeDao
{
  public abstract List<ContentType> getList(boolean paramBoolean);

  public abstract ContentType getDef();

  public abstract ContentType findById(Integer paramInteger);

  public abstract ContentType save(ContentType paramContentType);

  public abstract ContentType updateByUpdater(Updater<ContentType> paramUpdater);

  public abstract ContentType deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentTypeDao
 * JD-Core Version:    0.6.0
 */