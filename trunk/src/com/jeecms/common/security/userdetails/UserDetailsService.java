package com.jeecms.common.security.userdetails;

import com.jeecms.common.security.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public abstract interface UserDetailsService
{
  public abstract UserDetails loadUser(Long paramLong, String paramString)
    throws UsernameNotFoundException, DataAccessException;
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.userdetails.UserDetailsService
 * JD-Core Version:    0.6.0
 */