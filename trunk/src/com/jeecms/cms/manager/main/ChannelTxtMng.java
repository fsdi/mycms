package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelTxt;

public abstract interface ChannelTxtMng
{
  public abstract ChannelTxt save(ChannelTxt paramChannelTxt, Channel paramChannel);

  public abstract ChannelTxt update(ChannelTxt paramChannelTxt, Channel paramChannel);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ChannelTxtMng
 * JD-Core Version:    0.6.0
 */