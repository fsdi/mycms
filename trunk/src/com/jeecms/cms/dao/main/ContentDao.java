package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.Content.ContentStatus;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface ContentDao
{
  public abstract Pagination getPage(String paramString, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean1, boolean paramBoolean2, Content.ContentStatus paramContentStatus, Byte paramByte, Integer paramInteger3, Integer paramInteger4, int paramInt1, int paramInt2, int paramInt3);

  public abstract Pagination getPageBySelf(String paramString, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean1, boolean paramBoolean2, Content.ContentStatus paramContentStatus, Byte paramByte, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, int paramInt1, int paramInt2, int paramInt3);

  public abstract Pagination getPageByRight(String paramString, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean1, boolean paramBoolean2, Content.ContentStatus paramContentStatus, Byte paramByte, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, boolean paramBoolean3, int paramInt1, int paramInt2, int paramInt3);

  public abstract Content getSide(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean1, boolean paramBoolean2);

  public abstract List<Content> getListByIdsForTag(Integer[] paramArrayOfInteger, int paramInt);

  public abstract Pagination getPageBySiteIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListBySiteIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByChannelIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract List<Content> getListByChannelIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByChannelPathsForTag(String[] paramArrayOfString, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByChannelPathsForTag(String[] paramArrayOfString, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByTopicIdForTag(Integer paramInteger, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByTopicIdForTag(Integer paramInteger1, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger2, Integer paramInteger3);

  public abstract Pagination getPageByTagIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer[] paramArrayOfInteger4, Integer paramInteger, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByTagIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer[] paramArrayOfInteger4, Integer paramInteger1, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger2, Integer paramInteger3);

  public abstract Pagination getPageForCollection(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);

  public abstract int countByChannelId(int paramInt);

  public abstract Content findById(Integer paramInteger);

  public abstract Content save(Content paramContent);

  public abstract Content updateByUpdater(Updater<Content> paramUpdater);

  public abstract Content deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.ContentDao
 * JD-Core Version:    0.6.0
 */