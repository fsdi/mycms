package com.jeecms.cms.staticpage;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.Content;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public abstract interface StaticPageDao
{
  public abstract int channelStatic(Integer paramInteger1, Integer paramInteger2, boolean paramBoolean, Configuration paramConfiguration, Map<String, Object> paramMap)
    throws IOException, TemplateException;

  public abstract void channelStatic(Channel paramChannel, boolean paramBoolean, Configuration paramConfiguration, Map<String, Object> paramMap)
    throws IOException, TemplateException;

  public abstract int contentStatic(Integer paramInteger1, Integer paramInteger2, Date paramDate, Configuration paramConfiguration, Map<String, Object> paramMap)
    throws IOException, TemplateException;

  public abstract boolean contentStatic(Content paramContent, Configuration paramConfiguration, Map<String, Object> paramMap)
    throws IOException, TemplateException;

  public abstract int contentsOfChannel(Integer paramInteger);

  public abstract int childsOfChannel(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticPageDao
 * JD-Core Version:    0.6.0
 */