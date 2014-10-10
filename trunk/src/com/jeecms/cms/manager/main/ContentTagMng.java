package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.ContentTag;
import com.jeecms.common.page.Pagination;
import java.util.Collection;
import java.util.List;

public abstract interface ContentTagMng
{
  public abstract List<ContentTag> getListForTag(Integer paramInteger);

  public abstract Pagination getPageForTag(int paramInt1, int paramInt2);

  public abstract Pagination getPage(String paramString, int paramInt1, int paramInt2);

  public abstract ContentTag findById(Integer paramInteger);

  public abstract ContentTag findByName(String paramString);

  public abstract ContentTag findByNameForTag(String paramString);

  public abstract List<ContentTag> saveTags(String[] paramArrayOfString);

  public abstract ContentTag saveTag(String paramString);

  public abstract List<ContentTag> updateTags(List<ContentTag> paramList, String[] paramArrayOfString);

  public abstract void removeTags(Collection<ContentTag> paramCollection);

  public abstract ContentTag save(ContentTag paramContentTag);

  public abstract ContentTag update(ContentTag paramContentTag);

  public abstract ContentTag deleteById(Integer paramInteger);

  public abstract ContentTag[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentTagMng
 * JD-Core Version:    0.6.0
 */