package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsRole;
import java.util.List;
import java.util.Set;

public abstract interface CmsRoleMng
{
  public abstract List<CmsRole> getList();

  public abstract CmsRole findById(Integer paramInteger);

  public abstract CmsRole save(CmsRole paramCmsRole, Set<String> paramSet);

  public abstract CmsRole update(CmsRole paramCmsRole, Set<String> paramSet);

  public abstract CmsRole deleteById(Integer paramInteger);

  public abstract CmsRole[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsRoleMng
 * JD-Core Version:    0.6.0
 */