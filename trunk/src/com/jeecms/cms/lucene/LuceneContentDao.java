package com.jeecms.cms.lucene;

import java.io.IOException;
import java.util.Date;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

public abstract interface LuceneContentDao
{
  public abstract Integer index(IndexWriter paramIndexWriter, Integer paramInteger1, Integer paramInteger2, Date paramDate1, Date paramDate2, Integer paramInteger3, Integer paramInteger4)
    throws CorruptIndexException, IOException;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.lucene.LuceneContentDao
 * JD-Core Version:    0.6.0
 */