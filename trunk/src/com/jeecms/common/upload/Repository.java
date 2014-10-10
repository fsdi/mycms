package com.jeecms.common.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface Repository
{
  public abstract String storeByExt(String paramString, InputStream paramInputStream)
    throws IOException;

  public abstract String storeByName(String paramString, InputStream paramInputStream)
    throws IOException;

  public abstract boolean retrieve(String paramString, OutputStream paramOutputStream);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.upload.Repository
 * JD-Core Version:    0.6.0
 */