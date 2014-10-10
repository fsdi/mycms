package com.jeecms.common.image;

import java.awt.Color;
import java.io.File;

public abstract interface ImageScale
{
  public abstract void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2)
    throws Exception;

  public abstract void resizeFix(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws Exception;

  public abstract void imageMark(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, Color paramColor, int paramInt6, int paramInt7)
    throws Exception;

  public abstract void imageMark(File paramFile1, File paramFile2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, File paramFile3)
    throws Exception;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.image.ImageScale
 * JD-Core Version:    0.6.0
 */