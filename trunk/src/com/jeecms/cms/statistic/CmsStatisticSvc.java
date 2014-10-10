package com.jeecms.cms.statistic;

import com.jeecms.common.page.Pagination;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract interface CmsStatisticSvc
{
  public abstract List<CmsStatistic> statisticByModel(int paramInt, CmsStatistic.CmsStatisticModel paramCmsStatisticModel, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Map<String, Object> paramMap);

  public abstract List<CmsStatistic> flowStatistic(int paramInt, Integer paramInteger);

  public abstract Pagination flowAnalysisPage(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);

  public abstract void flowInit(Integer paramInteger, Date paramDate1, Date paramDate2);

  public abstract Map<String, List<CmsStatistic>> getWelcomeSiteFlowData(Integer paramInteger);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatisticSvc
 * JD-Core Version:    0.6.0
 */