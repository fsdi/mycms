package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.back.CmsField;
import java.sql.SQLException;
import java.util.List;

public abstract interface CmsDataBackMng
{
  public abstract List<String> listTabels(String paramString);

  public abstract List<CmsField> listFields(String paramString);

  public abstract List<String> listDataBases();

  public abstract String createTableDDL(String paramString);

  public abstract List<Object[]> createTableData(String paramString);

  public abstract String getDefaultCatalog()
    throws SQLException;

  public abstract void setDefaultCatalog(String paramString)
    throws SQLException;

  public abstract Boolean executeSQL(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsDataBackMng
 * JD-Core Version:    0.6.0
 */