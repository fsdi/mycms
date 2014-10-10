package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.file.FileWrap;
import com.jeecms.common.util.Zipper.FileEntry;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public abstract interface CmsResourceMng
{
  public abstract List<FileWrap> listFile(String paramString, boolean paramBoolean);

  public abstract List<FileWrap> listFileValid(String paramString, boolean paramBoolean);

  public abstract List<FileWrap> queryFiles(String paramString, Boolean paramBoolean);

  public abstract void saveFile(String paramString, MultipartFile paramMultipartFile)
    throws IllegalStateException, IOException;

  public abstract boolean createDir(String paramString1, String paramString2);

  public abstract void createFile(String paramString1, String paramString2, String paramString3)
    throws IOException;

  public abstract String readFile(String paramString)
    throws IOException;

  public abstract void updateFile(String paramString1, String paramString2)
    throws IOException;

  public abstract void rename(String paramString1, String paramString2);

  public abstract int delete(String[] paramArrayOfString);

  public abstract void copyTplAndRes(CmsSite paramCmsSite1, CmsSite paramCmsSite2)
    throws IOException;

  public abstract void delTplAndRes(CmsSite paramCmsSite)
    throws IOException;

  public abstract String[] getSolutions(String paramString);

  public abstract List<FileEntry> export(CmsSite paramCmsSite, String paramString);

  public abstract void imoport(File paramFile, CmsSite paramCmsSite)
    throws IOException;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsResourceMng
 * JD-Core Version:    0.6.0
 */