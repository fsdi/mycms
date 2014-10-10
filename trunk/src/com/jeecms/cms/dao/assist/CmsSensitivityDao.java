package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsSensitivity;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsSensitivityDao
{
  public abstract List<CmsSensitivity> getList(boolean paramBoolean);

  public abstract CmsSensitivity findById(Integer paramInteger);

  public abstract CmsSensitivity save(CmsSensitivity paramCmsSensitivity);

  public abstract CmsSensitivity updateByUpdater(Updater<CmsSensitivity> paramUpdater);

  public abstract CmsSensitivity deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsSensitivityDao
 * JD-Core Version:    0.6.0
 */