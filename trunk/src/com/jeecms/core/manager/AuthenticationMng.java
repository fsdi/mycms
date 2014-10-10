package com.jeecms.core.manager;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.UnifiedUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface AuthenticationMng
{
  public static final String AUTH_KEY = "auth_key";

  public abstract Integer retrieveUserIdFromSession(SessionProvider paramSessionProvider, HttpServletRequest paramHttpServletRequest);

  public abstract void storeAuthIdToSession(SessionProvider paramSessionProvider, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString);

  public abstract Authentication retrieve(String paramString);

  public abstract Authentication login(String paramString1, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, SessionProvider paramSessionProvider)
    throws UsernameNotFoundException, BadCredentialsException;

  public abstract Authentication activeLogin(UnifiedUser paramUnifiedUser, String paramString, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, SessionProvider paramSessionProvider);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Authentication findById(String paramString);

  public abstract Authentication save(Authentication paramAuthentication);

  public abstract Authentication deleteById(String paramString);

  public abstract Authentication[] deleteByIds(String[] paramArrayOfString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.AuthenticationMng
 * JD-Core Version:    0.6.0
 */