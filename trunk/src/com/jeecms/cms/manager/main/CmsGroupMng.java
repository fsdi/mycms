package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsGroup;
import java.util.List;

public abstract interface CmsGroupMng
{
  public abstract List<CmsGroup> getList();

  public abstract CmsGroup getRegDef();

  public abstract CmsGroup findById(Integer paramInteger);

  public abstract void updateRegDef(Integer paramInteger);

  public abstract CmsGroup save(CmsGroup paramCmsGroup);

  public abstract CmsGroup save(CmsGroup paramCmsGroup, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);

  public abstract CmsGroup update(CmsGroup paramCmsGroup);

  public abstract CmsGroup update(CmsGroup paramCmsGroup, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);

  public abstract CmsGroup deleteById(Integer paramInteger);

  public abstract CmsGroup[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract CmsGroup[] updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsGroupMng
 * JD-Core Version:    0.6.0
 */