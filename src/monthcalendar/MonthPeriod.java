package monthcalendar;

import java.time.YearMonth;
import java.util.Arrays;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public class MonthPeriod extends Period {

    public MonthPeriod(YearMonth yearMonth) {
        super(yearMonth);
        yearMonths = Arrays.asList(yearMonth);
    }

    @Override
    public Period next() {
        return new MonthPeriod(yearMonths.get(0).plusMonths(1));
    }

    @Override
    public Period previous() {
        return new MonthPeriod(yearMonths.get(0).minusMonths(1));
    }


}
