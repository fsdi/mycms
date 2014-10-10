package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentCount;
import net.sf.ehcache.Ehcache;

public abstract interface ContentCountMng
{
  public abstract int contentUp(Integer paramInteger);

  public abstract int contentDown(Integer paramInteger);

  public abstract void downloadCount(Integer paramInteger);

  public abstract void commentCount(Integer paramInteger);

  public abstract int freshCacheToDB(Ehcache paramEhcache);

  public abstract ContentCount findById(Integer paramInteger);

  public abstract ContentCount save(ContentCount paramContentCount, Content paramContent);

  public abstract ContentCount update(ContentCount paramContentCount);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentCountMng
 * JD-Core Version:    0.6.0
 */