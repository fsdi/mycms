package com.jeecms.cms.statistic.workload;

import java.util.Date;
import java.util.List;

public abstract interface CmsWorkLoadStatisticSvc
{
  public abstract List<CmsWorkLoadStatistic> statistic(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Date paramDate1, Date paramDate2, CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup paramCmsWorkLoadStatisticGroup, CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind paramCmsWorkLoadStatisticDateKind);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatisticSvc
 * JD-Core Version:    0.6.0
 */