package com.jeecms.common.page;

public abstract interface Paginable
{
  public abstract int getTotalCount();

  public abstract int getTotalPage();

  public abstract int getPageSize();

  public abstract int getPageNo();

  public abstract boolean isFirstPage();

  public abstract boolean isLastPage();

  public abstract int getNextPage();

  public abstract int getPrePage();
}

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.page.Paginable
 * JD-Core Version:    0.6.0
 */