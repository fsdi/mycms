package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ContentTag;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface ContentTagDao
{
  public abstract List<ContentTag> getList(Integer paramInteger, boolean paramBoolean);

  public abstract Pagination getPage(String paramString, int paramInt1, int paramInt2, boolean paramBoolean);

  public abstract ContentTag findById(Integer paramInteger);

  public abstract ContentTag findByName(String paramString, boolean paramBoolean);

  public abstract ContentTag save(ContentTag paramContentTag);

  public abstract ContentTag updateByUpdater(Updater<ContentTag> paramUpdater);

  public abstract ContentTag deleteById(Integer paramInteger);

  public abstract int deleteContentRef(Integer paramInteger);

  public abstract int countContentRef(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentTagDao
 * JD-Core Version:    0.6.0
 */