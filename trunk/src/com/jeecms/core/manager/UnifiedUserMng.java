package com.jeecms.core.manager;

import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.core.entity.UnifiedUser;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;

public abstract interface UnifiedUserMng
{
  public abstract UnifiedUser passwordForgotten(Integer paramInteger, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate);

  public abstract UnifiedUser resetPassword(Integer paramInteger);

  public abstract Integer errorRemaining(String paramString);

  public abstract UnifiedUser login(String paramString1, String paramString2, String paramString3)
    throws UsernameNotFoundException, BadCredentialsException;

  public abstract boolean usernameExist(String paramString);

  public abstract boolean emailExist(String paramString);

  public abstract UnifiedUser getByUsername(String paramString);

  public abstract List<UnifiedUser> getByEmail(String paramString);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract UnifiedUser findById(Integer paramInteger);

  public abstract UnifiedUser save(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract UnifiedUser save(String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, EmailSender paramEmailSender, MessageTemplate paramMessageTemplate)
    throws UnsupportedEncodingException, MessagingException;

  public abstract UnifiedUser update(Integer paramInteger, String paramString1, String paramString2);

  public abstract boolean isPasswordValid(Integer paramInteger, String paramString);

  public abstract UnifiedUser deleteById(Integer paramInteger);

  public abstract UnifiedUser[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract UnifiedUser active(String paramString1, String paramString2);

  public abstract UnifiedUser activeLogin(UnifiedUser paramUnifiedUser, String paramString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.UnifiedUserMng
 * JD-Core Version:    0.6.0
 */