package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentCheck;

public abstract interface ContentCheckMng
{
  public abstract ContentCheck save(ContentCheck paramContentCheck, Content paramContent);

  public abstract ContentCheck update(ContentCheck paramContentCheck);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentCheckMng
 * JD-Core Version:    0.6.0
 */