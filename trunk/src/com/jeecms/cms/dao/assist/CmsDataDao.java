package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.back.CmsConstraints;
import com.jeecms.cms.entity.back.CmsField;
import com.jeecms.cms.entity.back.CmsTable;
import java.util.List;

public abstract interface CmsDataDao
{
  public abstract List<CmsTable> listTables();

  public abstract List<CmsField> listFields(String paramString);

  public abstract List<CmsConstraints> listConstraints(String paramString);

  public abstract CmsTable findTable(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsDataDao
 * JD-Core Version:    0.6.0
 */