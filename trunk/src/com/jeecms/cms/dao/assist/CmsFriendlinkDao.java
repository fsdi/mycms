package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsFriendlink;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsFriendlinkDao
{
  public abstract List<CmsFriendlink> getList(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean);

  public abstract int countByCtgId(Integer paramInteger);

  public abstract CmsFriendlink findById(Integer paramInteger);

  public abstract CmsFriendlink save(CmsFriendlink paramCmsFriendlink);

  public abstract CmsFriendlink updateByUpdater(Updater<CmsFriendlink> paramUpdater);

  public abstract CmsFriendlink deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsFriendlinkDao
 * JD-Core Version:    0.6.0
 */