package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CmsConfig;
import com.jeecms.cms.entity.main.MarkConfig;
import com.jeecms.cms.entity.main.MemberConfig;
import java.util.Date;

public abstract interface CmsConfigMng
{
  public abstract CmsConfig get();

  public abstract void updateCountCopyTime(Date paramDate);

  public abstract void updateCountClearTime(Date paramDate);

  public abstract CmsConfig update(CmsConfig paramCmsConfig);

  public abstract MarkConfig updateMarkConfig(MarkConfig paramMarkConfig);

  public abstract void updateMemberConfig(MemberConfig paramMemberConfig);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.CmsConfigMng
 * JD-Core Version:    0.6.0
 */