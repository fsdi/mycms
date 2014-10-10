package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsModel;
import java.util.List;

public abstract interface CmsModelMng
{
  public abstract List<CmsModel> getList(boolean paramBoolean, Boolean paramBoolean1);

  public abstract CmsModel getDefModel();

  public abstract CmsModel findById(Integer paramInteger);

  public abstract CmsModel findByPath(String paramString);

  public abstract CmsModel save(CmsModel paramCmsModel);

  public abstract CmsModel update(CmsModel paramCmsModel);

  public abstract CmsModel deleteById(Integer paramInteger);

  public abstract CmsModel[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract CmsModel[] updatePriority(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean[] paramArrayOfBoolean, Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsModelMng
 * JD-Core Version:    0.6.0
 */