package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsSiteCompany;

public abstract interface CmsSiteCompanyMng
{
  public abstract CmsSiteCompany save(CmsSite paramCmsSite, CmsSiteCompany paramCmsSiteCompany);

  public abstract CmsSiteCompany update(CmsSiteCompany paramCmsSiteCompany);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsSiteCompanyMng
 * JD-Core Version:    0.6.0
 */