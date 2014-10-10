package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsFile;
import com.jeecms.cms.entity.main.Content;
import java.util.List;

public abstract interface CmsFileMng
{
  public abstract List<CmsFile> getList(Boolean paramBoolean);

  public abstract CmsFile findById(Integer paramInteger);

  public abstract CmsFile findByPath(String paramString);

  public abstract CmsFile save(CmsFile paramCmsFile);

  public abstract CmsFile update(CmsFile paramCmsFile);

  public abstract CmsFile deleteById(Integer paramInteger);

  public abstract CmsFile deleteByPath(String paramString);

  public abstract void deleteByContentId(Integer paramInteger);

  public abstract void saveFileByPath(String paramString1, String paramString2, Boolean paramBoolean);

  public abstract void updateFileByPath(String paramString, Boolean paramBoolean, Content paramContent);

  public abstract void updateFileByPaths(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, Content paramContent);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsFileMng
 * JD-Core Version:    0.6.0
 */