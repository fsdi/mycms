package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsSiteFlow;
import com.jeecms.cms.entity.main.CmsSite;
import net.sf.ehcache.Ehcache;

public abstract interface CmsSiteFlowMng
{
  public abstract CmsSiteFlow save(CmsSite paramCmsSite, String paramString1, String paramString2, String paramString3);

  public abstract CmsSiteFlow findUniqueByProperties(Integer paramInteger, String paramString1, String paramString2, String paramString3);

  public abstract int freshCacheToDB(Ehcache paramEhcache);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsSiteFlowMng
 * JD-Core Version:    0.6.0
 */