package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public abstract interface CmsGuestbookMng
{
  public abstract Pagination getPage(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, int paramInt2);

  @Transactional(readOnly=true)
  public abstract List<CmsGuestbook> getList(Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, int paramInt2);

  public abstract CmsGuestbook findById(Integer paramInteger);

  public abstract CmsGuestbook save(CmsGuestbook paramCmsGuestbook, CmsGuestbookExt paramCmsGuestbookExt, Integer paramInteger, String paramString);

  public abstract CmsGuestbook save(CmsUser paramCmsUser, Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract CmsGuestbook update(CmsGuestbook paramCmsGuestbook, CmsGuestbookExt paramCmsGuestbookExt, Integer paramInteger);

  public abstract CmsGuestbook deleteById(Integer paramInteger);

  public abstract CmsGuestbook[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.CmsGuestbookMng
 * JD-Core Version:    0.6.0
 */