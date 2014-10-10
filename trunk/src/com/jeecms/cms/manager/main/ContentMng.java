package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.Content.ContentStatus;
import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.cms.entity.main.ContentTxt;
import com.jeecms.cms.staticpage.exception.ContentNotCheckedException;
import com.jeecms.cms.staticpage.exception.GeneratedZeroStaticPageException;
import com.jeecms.cms.staticpage.exception.StaticPageNotOpenException;
import com.jeecms.cms.staticpage.exception.TemplateNotFoundException;
import com.jeecms.cms.staticpage.exception.TemplateParseException;
import com.jeecms.common.page.Pagination;
import java.util.List;
import java.util.Map;

public abstract interface ContentMng
{
  public abstract Pagination getPageByRight(String paramString, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean1, boolean paramBoolean2, Content.ContentStatus paramContentStatus, Byte paramByte, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, int paramInt1, int paramInt2, int paramInt3);

  public abstract Pagination getPageForMember(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, int paramInt1, int paramInt2);

  public abstract List<Content> getListByIdsForTag(Integer[] paramArrayOfInteger, int paramInt);

  public abstract Content getSide(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean);

  public abstract Pagination getPageBySiteIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListBySiteIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByChannelIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract List<Content> getListByChannelIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByChannelPathsForTag(String[] paramArrayOfString, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByChannelPathsForTag(String[] paramArrayOfString, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger1, Integer paramInteger2);

  public abstract Pagination getPageByTopicIdForTag(Integer paramInteger, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByTopicIdForTag(Integer paramInteger1, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger2, Integer paramInteger3);

  public abstract Pagination getPageByTagIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer[] paramArrayOfInteger4, Integer paramInteger, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt1, int paramInt2, int paramInt3);

  public abstract List<Content> getListByTagIdsForTag(Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Integer[] paramArrayOfInteger4, Integer paramInteger1, Boolean paramBoolean1, Boolean paramBoolean2, String paramString, int paramInt, Integer paramInteger2, Integer paramInteger3);

  public abstract Content findById(Integer paramInteger);

  public abstract Content save(Content paramContent, ContentExt paramContentExt, ContentTxt paramContentTxt, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean, CmsUser paramCmsUser, boolean paramBoolean1);

  public abstract Content update(Content paramContent, ContentExt paramContentExt, ContentTxt paramContentTxt, String[] paramArrayOfString1, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, Map<String, String> paramMap, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean, CmsUser paramCmsUser, boolean paramBoolean1);

  public abstract Content check(Integer paramInteger, CmsUser paramCmsUser);

  public abstract Content[] check(Integer[] paramArrayOfInteger, CmsUser paramCmsUser);

  public abstract Content update(Content paramContent);

  public abstract Content reject(Integer paramInteger, CmsUser paramCmsUser, Byte paramByte, String paramString);

  public abstract Content[] reject(Integer[] paramArrayOfInteger, CmsUser paramCmsUser, Byte paramByte, String paramString);

  public abstract Content cycle(Integer paramInteger);

  public abstract Content[] cycle(Integer[] paramArrayOfInteger);

  public abstract Content recycle(Integer paramInteger);

  public abstract Content[] recycle(Integer[] paramArrayOfInteger);

  public abstract Content deleteById(Integer paramInteger);

  public abstract Content[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract Content[] contentStatic(Integer[] paramArrayOfInteger)
    throws TemplateNotFoundException, TemplateParseException, GeneratedZeroStaticPageException, StaticPageNotOpenException, ContentNotCheckedException;

  public abstract Pagination getPageForCollection(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);

  public abstract void updateFileByContent(Content paramContent, Boolean paramBoolean);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.ContentMng
 * JD-Core Version:    0.6.0
 */