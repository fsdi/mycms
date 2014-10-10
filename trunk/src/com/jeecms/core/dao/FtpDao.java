package com.jeecms.core.dao;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.entity.Ftp;
import java.util.List;

public abstract interface FtpDao
{
  public abstract List<Ftp> getList();

  public abstract Ftp findById(Integer paramInteger);

  public abstract Ftp save(Ftp paramFtp);

  public abstract Ftp updateByUpdater(Updater<Ftp> paramUpdater);

  public abstract Ftp deleteById(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.FtpDao
 * JD-Core Version:    0.6.0
 */