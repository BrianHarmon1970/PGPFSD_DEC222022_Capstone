package com.harmonengineering.beans;

import java.util.Date;

class DateRange
{
    private Date beginDate ;
    private Date endDate ;

    public DateRange() {}

    public Date getBeginDate() { return beginDate; }
    public Date getEndDate() { return endDate; }

    public void setBeginDate(Date beginDate) { this.beginDate = beginDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
public class ReportCriteriaBean
{
    //private DateRange dateRange = new DateRange() ;
    Date beginDate ;
    Date endDate ;
    private String reportType ;


    public ReportCriteriaBean() {}

    public String getReportType() { return reportType; }
    //public DateRange getDateRange() { return dateRange; }
    public Date getBeginDate() { return this.beginDate ; }
    public Date getEndDate() { return this.endDate ; }


    public void setReportType(String reportType) {  this.reportType = reportType; }
    //public void setDateRange(DateRange dateRange) { this.dateRange = dateRange; }
    public void setBeginDate(Date beginDate) { this.beginDate = beginDate ; }
    public void setEndDate(Date endDate) { this.endDate = endDate ; }


    public String toString() {
        return "ReportCriteriaBean"+this.hashCode();
    }
}
