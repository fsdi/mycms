package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.common.hibernate3.Updater;

public abstract interface ChannelExtDao
{
  public abstract ChannelExt save(ChannelExt paramChannelExt);

  public abstract ChannelExt updateByUpdater(Updater<ChannelExt> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ChannelExtDao
 * JD-Core Version:    0.6.0
 */