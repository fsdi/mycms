package com.jeecms.cms.statistic.workload;

import java.util.Date;

public abstract interface CmsWorkLoadStatisticDao
{
  public abstract Long statistic(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Date paramDate1, Date paramDate2, CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind paramCmsWorkLoadStatisticDateKind);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatisticDao
 * JD-Core Version:    0.6.0
 */