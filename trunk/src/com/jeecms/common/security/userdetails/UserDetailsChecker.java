package com.jeecms.common.security.userdetails;

import com.jeecms.common.security.AccountStatusException;

public abstract interface UserDetailsChecker
{
  public abstract void check(UserDetails paramUserDetails)
    throws AccountStatusException;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.userdetails.UserDetailsChecker
 * JD-Core Version:    0.6.0
 */