package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface ChannelDao
{
  public abstract List<Channel> getTopList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);

  public abstract Pagination getTopPage(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt1, int paramInt2);

  public abstract List<Channel> getTopListByRigth(Integer paramInteger1, Integer paramInteger2, boolean paramBoolean);

  public abstract List<Channel> getChildList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);

  public abstract Pagination getChildPage(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt1, int paramInt2);

  public abstract List<Channel> getChildListByRight(Integer paramInteger1, Integer paramInteger2, boolean paramBoolean);

  public abstract Channel findByPath(String paramString, Integer paramInteger, boolean paramBoolean);

  public abstract Channel findById(Integer paramInteger);

  public abstract Channel save(Channel paramChannel);

  public abstract Channel updateByUpdater(Updater<Channel> paramUpdater);

  public abstract Channel deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ChannelDao
 * JD-Core Version:    0.6.0
 */