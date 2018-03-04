package com.example.imhashvapahversion1.version1.Entity.enums;


import java.sql.Date;

public class DateRange {



    private Date start;

    private Date end;
    private boolean showAll ;
    public DateRange() {
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }
}
