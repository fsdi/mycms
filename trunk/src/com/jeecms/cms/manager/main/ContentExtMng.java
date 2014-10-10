package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentExt;

public abstract interface ContentExtMng
{
  public abstract ContentExt save(ContentExt paramContentExt, Content paramContent);

  public abstract ContentExt update(ContentExt paramContentExt);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentExtMng
 * JD-Core Version:    0.6.0
 */