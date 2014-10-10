package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface ChannelTxtDao
{
  public abstract ChannelTxt findById(Integer paramInteger);

  public abstract ChannelTxt save(ChannelTxt paramChannelTxt);

  public abstract ChannelTxt updateByUpdater(Updater<ChannelTxt> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ChannelTxtDao
 * JD-Core Version:    0.6.0
 */