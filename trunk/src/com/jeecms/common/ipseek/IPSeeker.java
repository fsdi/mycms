package com.jeecms.common.ipseek;

public abstract interface IPSeeker
{
  public static final int IP_RECORD_LENGTH = 7;
  public static final byte REDIRECT_MODE_1 = 1;
  public static final byte REDIRECT_MODE_2 = 2;

  public abstract IPLocation getIPLocation(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.ipseek.IPSeeker
 * JD-Core Version:    0.6.0
 */