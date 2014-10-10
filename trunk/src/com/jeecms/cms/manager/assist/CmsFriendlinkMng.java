package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsFriendlink;
import java.util.List;

public abstract interface CmsFriendlinkMng
{
  public abstract List<CmsFriendlink> getList(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean);

  public abstract int countByCtgId(Integer paramInteger);

  public abstract CmsFriendlink findById(Integer paramInteger);

  public abstract int updateViews(Integer paramInteger);

  public abstract CmsFriendlink save(CmsFriendlink paramCmsFriendlink, Integer paramInteger);

  public abstract CmsFriendlink update(CmsFriendlink paramCmsFriendlink, Integer paramInteger);

  public abstract void updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);

  public abstract CmsFriendlink deleteById(Integer paramInteger);

  public abstract CmsFriendlink[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsFriendlinkMng
 * JD-Core Version:    0.6.0
 */