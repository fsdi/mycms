package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsKeyword;
import com.jeecms.common.hibernate3.Updater;
import java.util.List;

public abstract interface CmsKeywordDao
{
  public abstract List<CmsKeyword> getList(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2);

  public abstract List<CmsKeyword> getListGlobal(boolean paramBoolean1, boolean paramBoolean2);

  public abstract CmsKeyword findById(Integer paramInteger);

  public abstract CmsKeyword save(CmsKeyword paramCmsKeyword);

  public abstract CmsKeyword updateByUpdater(Updater<CmsKeyword> paramUpdater);

  public abstract CmsKeyword deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.CmsKeywordDao
 * JD-Core Version:    0.6.0
 */