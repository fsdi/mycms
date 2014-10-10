package com.jeecms.cms.staticpage;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.Content;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public abstract interface StaticPageSvc
{
  public abstract int content(Integer paramInteger1, Integer paramInteger2, Date paramDate)
    throws IOException, TemplateException;

  public abstract boolean content(Content paramContent)
    throws IOException, TemplateException;

  public abstract void contentRelated(Content paramContent)
    throws IOException, TemplateException;

  public abstract void deleteContent(Content paramContent);

  public abstract int channel(Integer paramInteger1, Integer paramInteger2, boolean paramBoolean)
    throws IOException, TemplateException;

  public abstract void channel(Channel paramChannel, boolean paramBoolean)
    throws IOException, TemplateException;

  public abstract void deleteChannel(Channel paramChannel);

  public abstract void index(CmsSite paramCmsSite)
    throws IOException, TemplateException;

  public abstract void index(CmsSite paramCmsSite, String paramString, Map<String, Object> paramMap)
    throws IOException, TemplateException;

  public abstract boolean deleteIndex(CmsSite paramCmsSite);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticPageSvc
 * JD-Core Version:    0.6.0
 */