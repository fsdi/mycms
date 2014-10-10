package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsKeyword;
import java.util.List;

public abstract interface CmsKeywordMng
{
  public abstract List<CmsKeyword> getListBySiteId(Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2);

  public abstract String attachKeyword(Integer paramInteger, String paramString);

  public abstract CmsKeyword findById(Integer paramInteger);

  public abstract CmsKeyword save(CmsKeyword paramCmsKeyword);

  public abstract void updateKeywords(Integer[] paramArrayOfInteger, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean[] paramArrayOfBoolean);

  public abstract CmsKeyword deleteById(Integer paramInteger);

  public abstract CmsKeyword[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsKeywordMng
 * JD-Core Version:    0.6.0
 */