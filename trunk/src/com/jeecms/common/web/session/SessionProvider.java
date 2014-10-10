package com.jeecms.common.web.session;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface SessionProvider
{
  public abstract Serializable getAttribute(HttpServletRequest paramHttpServletRequest, String paramString);

  public abstract void setAttribute(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Serializable paramSerializable);

  public abstract String getSessionId(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.session.SessionProvider
 * JD-Core Version:    0.6.0
 */