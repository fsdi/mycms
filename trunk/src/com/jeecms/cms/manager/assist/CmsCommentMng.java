package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsComment;
import com.jeecms.cms.entity.assist.CmsCommentExt;
import com.jeecms.common.page.Pagination;
import java.util.List;

public abstract interface CmsCommentMng
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2);

  public abstract Pagination getPageForTag(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2);

  public abstract Pagination getPageForMember(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract List<CmsComment> getListForDel(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString);

  public abstract List<CmsComment> getListForTag(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean, boolean paramBoolean1, boolean paramBoolean2, int paramInt);

  public abstract CmsComment findById(Integer paramInteger);

  public abstract CmsComment comment(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, boolean paramBoolean1, boolean paramBoolean2);

  public abstract CmsComment update(CmsComment paramCmsComment, CmsCommentExt paramCmsCommentExt);

  public abstract int deleteByContentId(Integer paramInteger);

  public abstract CmsComment deleteById(Integer paramInteger);

  public abstract CmsComment[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract void ups(Integer paramInteger);

  public abstract void downs(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsCommentMng
 * JD-Core Version:    0.6.0
 */