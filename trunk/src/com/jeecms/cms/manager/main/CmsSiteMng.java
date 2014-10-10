package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import java.io.IOException;
import java.util.List;

public abstract interface CmsSiteMng
{
  public abstract List<CmsSite> getList();

  public abstract List<CmsSite> getListFromCache();

  public abstract CmsSite findByDomain(String paramString, boolean paramBoolean);

  public abstract CmsSite findById(Integer paramInteger);

  public abstract CmsSite save(CmsSite paramCmsSite1, CmsUser paramCmsUser, CmsSite paramCmsSite2, Integer paramInteger)
    throws IOException;

  public abstract CmsSite update(CmsSite paramCmsSite, Integer paramInteger);

  public abstract void updateTplSolution(Integer paramInteger, String paramString);

  public abstract CmsSite deleteById(Integer paramInteger);

  public abstract CmsSite[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsSiteMng
 * JD-Core Version:    0.6.0
 */