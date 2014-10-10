package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentTxt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface ContentTxtDao
{
  public abstract ContentTxt findById(Integer paramInteger);

  public abstract ContentTxt save(ContentTxt paramContentTxt);

  public abstract ContentTxt updateByUpdater(Updater<ContentTxt> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentTxtDao
 * JD-Core Version:    0.6.0
 */