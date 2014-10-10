package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.page.Pagination;
import java.util.List;
import java.util.Map;

public abstract interface ChannelMng
{
  public abstract List<Channel> getTopList(Integer paramInteger, boolean paramBoolean);

  public abstract List<Channel> getTopListByRigth(Integer paramInteger1, Integer paramInteger2, boolean paramBoolean);

  public abstract List<Channel> getTopListForTag(Integer paramInteger, boolean paramBoolean);

  public abstract Pagination getTopPageForTag(Integer paramInteger, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract List<Channel> getChildList(Integer paramInteger, boolean paramBoolean);

  public abstract List<Channel> getChildListByRight(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean);

  public abstract List<Channel> getChildListForTag(Integer paramInteger, boolean paramBoolean);

  public abstract Pagination getChildPageForTag(Integer paramInteger, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract Channel findByPath(String paramString, Integer paramInteger);

  public abstract Channel findByPathForTag(String paramString, Integer paramInteger);

  public abstract Channel findById(Integer paramInteger);

  public abstract Channel save(Channel paramChannel, ChannelExt paramChannelExt, ChannelTxt paramChannelTxt, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer[] paramArrayOfInteger4, String[] paramArrayOfString);

  public abstract Channel update(Channel paramChannel, ChannelExt paramChannelExt, ChannelTxt paramChannelTxt, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer paramInteger, Map<String, String> paramMap, Integer[] paramArrayOfInteger4, String[] paramArrayOfString);

  public abstract Channel deleteById(Integer paramInteger);

  public abstract Channel[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract Channel[] updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);

  public abstract String checkDelete(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ChannelMng
 * JD-Core Version:    0.6.0
 */