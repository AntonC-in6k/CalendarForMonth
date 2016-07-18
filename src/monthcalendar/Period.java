package monthcalendar;

import java.time.YearMonth;
import java.util.List;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public abstract class Period {

    protected List<YearMonth> yearMonths;

    public Period(YearMonth yearMonth) {

    }

    public abstract Period next();

    public abstract Period previous();

    public YearPeriod increase() {
        return new YearPeriod(yearMonths.get(0));
    }

    public MonthPeriod degrees() {
        return new MonthPeriod(yearMonths.get(0));
    }

    public List<YearMonth> getMonths() {
        return yearMonths;
    }

}
