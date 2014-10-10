package com.jeecms.common.security.encoder;

public abstract interface PwdEncoder
{
  public abstract String encodePassword(String paramString);

  public abstract String encodePassword(String paramString1, String paramString2);

  public abstract boolean isPasswordValid(String paramString1, String paramString2);

  public abstract boolean isPasswordValid(String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.encoder.PwdEncoder
 * JD-Core Version:    0.6.0
 */