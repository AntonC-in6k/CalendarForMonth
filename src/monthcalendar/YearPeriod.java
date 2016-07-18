package monthcalendar;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public class YearPeriod extends Period {

    public YearPeriod(YearMonth yearMonth) {
        super(yearMonth);
        yearMonths = new ArrayList<>(12);
        for (int i = 1; i <= 12; i++) {
            yearMonths.add(YearMonth.of(yearMonth.getYear(), i));
        }
    }

    @Override
    public Period next() {
        return new YearPeriod(yearMonths.get(0).plusYears(1));
    }

    @Override
    public Period previous() {
        return new YearPeriod(yearMonths.get(0).minusYears(1));
    }

}
