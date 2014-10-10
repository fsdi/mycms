package com.jeecms.core.manager;

import com.jeecms.core.entity.DbFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public abstract interface DbFileMng
{
  public abstract DbFile findById(String paramString);

  public abstract String storeByExt(String paramString1, String paramString2, InputStream paramInputStream)
    throws IOException;

  public abstract String storeByFilename(String paramString, InputStream paramInputStream)
    throws IOException;

  public abstract File retrieve(String paramString)
    throws IOException;

  public abstract boolean restore(String paramString, File paramFile)
    throws FileNotFoundException, IOException;

  public abstract DbFile deleteById(String paramString);

  public abstract DbFile[] deleteByIds(String[] paramArrayOfString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.DbFileMng
 * JD-Core Version:    0.6.0
 */