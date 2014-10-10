package com.jeecms.cms.lucene;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.common.page.Pagination;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.Directory;

public abstract interface LuceneContentSvc
{
  public abstract Integer createIndex(Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, Integer paramInteger3, Integer paramInteger4)
    throws IOException, ParseException;

  public abstract Integer createIndex(Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, Integer paramInteger3, Integer paramInteger4, Directory paramDirectory)
    throws IOException, ParseException;

  public abstract void createIndex(Content paramContent, Directory paramDirectory)
    throws IOException;

  public abstract void createIndex(Content paramContent)
    throws IOException;

  public abstract void deleteIndex(Integer paramInteger)
    throws IOException, ParseException;

  public abstract void deleteIndex(Integer paramInteger, Directory paramDirectory)
    throws IOException, ParseException;

  public abstract void updateIndex(Content paramContent)
    throws IOException, ParseException;

  public abstract void updateIndex(Content paramContent, Directory paramDirectory)
    throws IOException, ParseException;

  public abstract Pagination searchPage(String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
    throws CorruptIndexException, IOException, ParseException;

  public abstract Pagination searchPage(Directory paramDirectory, String paramString1, String paramString2, String paramString3, Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
    throws CorruptIndexException, IOException, ParseException;

  public abstract List<Content> searchList(String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
    throws CorruptIndexException, IOException, ParseException;

  public abstract List<Content> searchList(Directory paramDirectory, String paramString1, String paramString2, String paramString3, Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
    throws CorruptIndexException, IOException, ParseException;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContentSvc
 * JD-Core Version:    0.6.0
 */