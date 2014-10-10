package com.jeecms.cms.statistic;

import com.jeecms.common.page.Pagination;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract interface CmsStatisticDao
{
  public abstract long memberStatistic(CmsStatistic.TimeRange paramTimeRange);

  public abstract long contentStatistic(CmsStatistic.TimeRange paramTimeRange, Map<String, Object> paramMap);

  public abstract long commentStatistic(CmsStatistic.TimeRange paramTimeRange, Map<String, Object> paramMap);

  public abstract long guestbookStatistic(CmsStatistic.TimeRange paramTimeRange, Map<String, Object> paramMap);

  public abstract long getPvCountByTimeRange(Integer paramInteger, CmsStatistic.TimeRange paramTimeRange);

  public abstract long getPvCount(Integer paramInteger);

  public abstract List<Object[]> getPvCountByGroup(Integer paramInteger);

  public abstract long getUniqueIpCountByTimeRange(Integer paramInteger, CmsStatistic.TimeRange paramTimeRange);

  public abstract long getUniqueIpCount(Integer paramInteger);

  public abstract List<Object[]> getUniqueIpCountByGroup(Integer paramInteger);

  public abstract long getUniqueVisitorCountByTimeRange(Integer paramInteger, CmsStatistic.TimeRange paramTimeRange);

  public abstract long getUniqueVisitorCount(Integer paramInteger);

  public abstract List<Object[]> getUniqueVisitorCountByGroup(Integer paramInteger);

  public abstract Pagination flowAnalysisPage(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3);

  public abstract long flowAnalysisTotal(Integer paramInteger);

  public abstract void flowInit(Integer paramInteger, Date paramDate1, Date paramDate2);
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatisticDao
 * JD-Core Version:    0.6.0
 */