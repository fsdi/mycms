package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
import java.util.List;

public abstract interface CmsFriendlinkCtgMng
{
  public abstract List<CmsFriendlinkCtg> getList(Integer paramInteger);

  public abstract int countBySiteId(Integer paramInteger);

  public abstract CmsFriendlinkCtg findById(Integer paramInteger);

  public abstract CmsFriendlinkCtg save(CmsFriendlinkCtg paramCmsFriendlinkCtg);

  public abstract CmsFriendlinkCtg update(CmsFriendlinkCtg paramCmsFriendlinkCtg);

  public abstract void updateFriendlinkCtgs(Integer[] paramArrayOfInteger1, String[] paramArrayOfString, Integer[] paramArrayOfInteger2);

  public abstract CmsFriendlinkCtg deleteById(Integer paramInteger);

  public abstract CmsFriendlinkCtg[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsFriendlinkCtgMng
 * JD-Core Version:    0.6.0
 */