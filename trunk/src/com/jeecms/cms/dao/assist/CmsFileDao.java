package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsFile;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsFileDao
{
  public abstract List<CmsFile> getList(Boolean paramBoolean);

  public abstract CmsFile findById(Integer paramInteger);

  public abstract CmsFile findByPath(String paramString);

  public abstract CmsFile save(CmsFile paramCmsFile);

  public abstract CmsFile updateByUpdater(Updater<CmsFile> paramUpdater);

  public abstract CmsFile deleteById(Integer paramInteger);

  public abstract CmsFile deleteByPath(String paramString);

  public abstract void deleteByContentId(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsFileDao
 * JD-Core Version:    0.6.0
 */