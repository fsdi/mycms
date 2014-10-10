package com.jeecms.core.manager;

import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.core.entity.Config;
import com.jeecms.core.entity.Config.ConfigLogin;
import java.util.Map;

public abstract interface ConfigMng
{
  public abstract Map<String, String> getMap();

  public abstract Config.ConfigLogin getConfigLogin();

  public abstract EmailSender getEmailSender();

  public abstract MessageTemplate getForgotPasswordMessageTemplate();

  public abstract MessageTemplate getRegisterMessageTemplate();

  public abstract String getValue(String paramString);

  public abstract void updateOrSave(Map<String, String> paramMap);

  public abstract Config updateOrSave(String paramString1, String paramString2);

  public abstract Config deleteById(String paramString);

  public abstract Config[] deleteByIds(String[] paramArrayOfString);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.ConfigMng
 * JD-Core Version:    0.6.0
 */