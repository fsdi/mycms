package com.jeecms.common.email;

public abstract interface EmailSender
{
  public abstract String getHost();

  public abstract Integer getPort();

  public abstract String getEncoding();

  public abstract String getUsername();

  public abstract String getPassword();

  public abstract String getPersonal();
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.email.EmailSender
 * JD-Core Version:    0.6.0
 */