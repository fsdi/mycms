package com.jeecms.core.manager;

import com.jeecms.core.entity.Ftp;
import java.util.List;

public abstract interface FtpMng
{
  public abstract List<Ftp> getList();

  public abstract Ftp findById(Integer paramInteger);

  public abstract Ftp save(Ftp paramFtp);

  public abstract Ftp update(Ftp paramFtp);

  public abstract Ftp deleteById(Integer paramInteger);

  public abstract Ftp[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.FtpMng
 * JD-Core Version:    0.6.0
 */