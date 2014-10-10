package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsModelItem;
import java.util.List;

public abstract interface CmsModelItemMng
{
  public abstract List<CmsModelItem> getList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2);

  public abstract CmsModelItem findById(Integer paramInteger);

  public abstract CmsModelItem save(CmsModelItem paramCmsModelItem);

  public abstract CmsModelItem save(CmsModelItem paramCmsModelItem, Integer paramInteger);

  public abstract void saveList(List<CmsModelItem> paramList);

  public abstract void updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, String[] paramArrayOfString, Boolean[] paramArrayOfBoolean1, Boolean[] paramArrayOfBoolean2);

  public abstract CmsModelItem update(CmsModelItem paramCmsModelItem);

  public abstract CmsModelItem deleteById(Integer paramInteger);

  public abstract CmsModelItem[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsModelItemMng
 * JD-Core Version:    0.6.0
 */