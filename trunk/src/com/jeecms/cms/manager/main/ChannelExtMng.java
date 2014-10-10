package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;

public abstract interface ChannelExtMng
{
  public abstract ChannelExt save(ChannelExt paramChannelExt, Channel paramChannel);

  public abstract ChannelExt update(ChannelExt paramChannelExt);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ChannelExtMng
 * JD-Core Version:    0.6.0
 */