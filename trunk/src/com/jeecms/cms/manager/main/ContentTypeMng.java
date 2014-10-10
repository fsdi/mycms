package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.ContentType;
import java.util.List;

public abstract interface ContentTypeMng
{
  public abstract List<ContentType> getList(boolean paramBoolean);

  public abstract ContentType getDef();

  public abstract ContentType findById(Integer paramInteger);

  public abstract ContentType save(ContentType paramContentType);

  public abstract ContentType update(ContentType paramContentType);

  public abstract ContentType deleteById(Integer paramInteger);

  public abstract ContentType[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentTypeMng
 * JD-Core Version:    0.6.0
 */