package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.back.CmsConstraints;
import com.jeecms.cms.entity.back.CmsField;
import com.jeecms.cms.entity.back.CmsTable;
import java.util.List;

public abstract interface CmsDataMng
{
  public abstract List<CmsTable> listTabels();

  public abstract CmsTable findTable(String paramString);

  public abstract List<CmsField> listFields(String paramString);

  public abstract List<CmsConstraints> listConstraints(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsDataMng
 * JD-Core Version:    0.6.0
 */