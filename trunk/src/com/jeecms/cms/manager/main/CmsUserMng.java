package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;

public abstract interface CmsUserMng
{
  public abstract Pagination getPage(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger3, int paramInt1, int paramInt2);

  public abstract List<CmsUser> getList(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger3);

  public abstract List<CmsUser> getAdminList(Integer paramInteger1, Boolean paramBoolean1, Boolean paramBoolean2, Integer paramInteger2);

  public abstract CmsUser findById(Integer paramInteger);

  public abstract CmsUser findByUsername(String paramString);

  public abstract CmsUser registerMember(String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger, CmsUserExt paramCmsUserExt);

  public abstract CmsUser registerMember(String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger, CmsUserExt paramCmsUserExt, Boolean paramBoolean, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate)
    throws UnsupportedEncodingException, MessagingException;

  public abstract void updateLoginInfo(Integer paramInteger, String paramString);

  public abstract void updateUploadSize(Integer paramInteger1, Integer paramInteger2);

  public abstract void updateUser(CmsUser paramCmsUser);

  public abstract void updatePwdEmail(Integer paramInteger, String paramString1, String paramString2);

  public abstract boolean isPasswordValid(Integer paramInteger, String paramString);

  public abstract CmsUser saveAdmin(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2, int paramInt, Integer paramInteger, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Byte[] paramArrayOfByte, Boolean[] paramArrayOfBoolean, CmsUserExt paramCmsUserExt);

  public abstract CmsUser updateAdmin(CmsUser paramCmsUser, CmsUserExt paramCmsUserExt, String paramString, Integer paramInteger, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer[] paramArrayOfInteger3, Byte[] paramArrayOfByte, Boolean[] paramArrayOfBoolean);

  public abstract CmsUser updateAdmin(CmsUser paramCmsUser, CmsUserExt paramCmsUserExt, String paramString, Integer paramInteger1, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2, Integer paramInteger2, Byte paramByte, Boolean paramBoolean);

  public abstract CmsUser updateMember(Integer paramInteger1, String paramString1, String paramString2, Boolean paramBoolean, CmsUserExt paramCmsUserExt, Integer paramInteger2);

  public abstract CmsUser updateUserConllection(CmsUser paramCmsUser, Integer paramInteger1, Integer paramInteger2);

  public abstract void addSiteToUser(CmsUser paramCmsUser, CmsSite paramCmsSite, Byte paramByte);

  public abstract CmsUser deleteById(Integer paramInteger);

  public abstract CmsUser[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract boolean usernameNotExist(String paramString);

  public abstract boolean usernameNotExistInMember(String paramString);

  public abstract boolean emailNotExist(String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsUserMng
 * JD-Core Version:    0.6.0
 */