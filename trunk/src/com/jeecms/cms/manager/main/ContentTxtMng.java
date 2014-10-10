package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentTxt;

public abstract interface ContentTxtMng
{
  public abstract ContentTxt save(ContentTxt paramContentTxt, Content paramContent);

  public abstract ContentTxt update(ContentTxt paramContentTxt, Content paramContent);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentTxtMng
 * JD-Core Version:    0.6.0
 */