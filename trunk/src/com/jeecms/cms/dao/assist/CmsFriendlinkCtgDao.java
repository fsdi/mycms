package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsFriendlinkCtgDao
{
  public abstract List<CmsFriendlinkCtg> getList(Integer paramInteger);

  public abstract int countBySiteId(Integer paramInteger);

  public abstract CmsFriendlinkCtg findById(Integer paramInteger);

  public abstract CmsFriendlinkCtg save(CmsFriendlinkCtg paramCmsFriendlinkCtg);

  public abstract CmsFriendlinkCtg updateByUpdater(Updater<CmsFriendlinkCtg> paramUpdater);

  public abstract CmsFriendlinkCtg deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsFriendlinkCtgDao
 * JD-Core Version:    0.6.0
 */