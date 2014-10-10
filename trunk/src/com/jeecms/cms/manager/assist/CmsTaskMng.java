package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsTask;
import com.jeecms.common.page.Pagination;
import java.util.List;
import java.util.Map;

public abstract interface CmsTaskMng
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract List<CmsTask> getList();

  public abstract CmsTask findById(Integer paramInteger);

  public abstract CmsTask save(CmsTask paramCmsTask);

  public abstract CmsTask update(CmsTask paramCmsTask, Map<String, String> paramMap);

  public abstract CmsTask deleteById(Integer paramInteger);

  public abstract CmsTask[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract String getCronExpressionFromDB(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsTaskMng
 * JD-Core Version:    0.6.0
 */