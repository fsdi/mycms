package com.jeecms.core.web;

import java.util.Date;

public abstract interface ContentInterface
{
  public abstract String getTit();

  public abstract String getStit();

  public abstract String getDesc();

  public abstract String getImgUrl();

  public abstract String getImgUrl2();

  public abstract String getUrl();

  public abstract Boolean getTarget();

  public abstract String getTitCol();

  public abstract boolean isTitBold();

  public abstract String getCtgName();

  public abstract String getCtgUrl();

  public abstract String getSiteName();

  public abstract String getSiteUrl();

  public abstract Date getDate();
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.web.ContentInterface
 * JD-Core Version:    0.6.0
 */