package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsComment;
import com.jeecms.cms.entity.assist.CmsCommentExt;

public abstract interface CmsCommentExtMng
{
  public abstract CmsCommentExt save(String paramString1, String paramString2, CmsComment paramCmsComment);

  public abstract CmsCommentExt update(CmsCommentExt paramCmsCommentExt);

  public abstract int deleteByContentId(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsCommentExtMng
 * JD-Core Version:    0.6.0
 */