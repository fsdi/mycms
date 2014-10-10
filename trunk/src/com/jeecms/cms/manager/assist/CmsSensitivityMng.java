package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsSensitivity;
import java.util.List;

public abstract interface CmsSensitivityMng
{
  public abstract String replaceSensitivity(String paramString);

  public abstract List<CmsSensitivity> getList(boolean paramBoolean);

  public abstract CmsSensitivity findById(Integer paramInteger);

  public abstract CmsSensitivity save(CmsSensitivity paramCmsSensitivity);

  public abstract void updateEnsitivity(Integer[] paramArrayOfInteger, String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract CmsSensitivity deleteById(Integer paramInteger);

  public abstract CmsSensitivity[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsSensitivityMng
 * JD-Core Version:    0.6.0
 */