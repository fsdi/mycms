package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsSiteFlow;

public abstract interface CmsSiteFlowDao
{
  public abstract CmsSiteFlow save(CmsSiteFlow paramCmsSiteFlow);

  public abstract CmsSiteFlow findUniqueByProperties(Integer paramInteger, String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsSiteFlowDao
 * JD-Core Version:    0.6.0
 */