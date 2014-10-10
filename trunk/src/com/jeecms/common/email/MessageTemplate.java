package com.jeecms.common.email;

public abstract interface MessageTemplate
{
  public abstract String getForgotPasswordSubject();

  public abstract String getForgotPasswordText();

  public abstract String getRegisterSubject();

  public abstract String getRegisterText();
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.email.MessageTemplate
 * JD-Core Version:    0.6.0
 */