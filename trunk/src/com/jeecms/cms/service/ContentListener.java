package com.jeecms.cms.service;

import com.jeecms.cms.entity.main.Content;
import java.util.Map;

public abstract interface ContentListener
{
  public abstract void preSave(Content paramContent);

  public abstract void afterSave(Content paramContent);

  public abstract Map<String, Object> preChange(Content paramContent);

  public abstract void afterChange(Content paramContent, Map<String, Object> paramMap);

  public abstract void preDelete(Content paramContent);

  public abstract void afterDelete(Content paramContent);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.service.ContentListener
 * JD-Core Version:    0.6.0
 */