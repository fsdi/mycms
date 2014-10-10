package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsModel;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsModelDao
{
  public abstract List<CmsModel> getList(boolean paramBoolean, Boolean paramBoolean1);

  public abstract CmsModel getDefModel();

  public abstract CmsModel findById(Integer paramInteger);

  public abstract CmsModel findByPath(String paramString);

  public abstract CmsModel save(CmsModel paramCmsModel);

  public abstract CmsModel updateByUpdater(Updater<CmsModel> paramUpdater);

  public abstract CmsModel deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsModelDao
 * JD-Core Version:    0.6.0
 */