package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.CmsSiteCompany;
import com.jeecms.common.hibernate3.Updater;

public abstract interface CmsSiteCompanyDao
{
  public abstract CmsSiteCompany save(CmsSiteCompany paramCmsSiteCompany);

  public abstract CmsSiteCompany updateByUpdater(Updater<CmsSiteCompany> paramUpdater);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.CmsSiteCompanyDao
 * JD-Core Version:    0.6.0
 */