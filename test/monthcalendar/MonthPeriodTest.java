package monthcalendar;

/**
 * Created by employee on 7/15/16.
 */

import org.junit.Test;

import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MonthPeriodTest {

    @Test
    public void returnNextMonth() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 7);
        YearMonth expected = YearMonth.of(2016, 8);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriod.next().getMonths().get(0), is(expected));
    }


    @Test
    public void returnPreviousMonth() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 8);
        YearMonth expected = YearMonth.of(2016, 7);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriod.previous().getMonths().get(0), is(expected));
    }

    @Test
    public void increasePeriod() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2017, 12);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        monthPeriod.increase();
        assertThat(monthPeriod.next().getMonths().get(11), is(expected));
    }

    @Test
    public void degreesPeriod() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2016, 2);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        monthPeriod.increase();
        monthPeriod.degrees();
        assertThat(monthPeriod.next().getMonths().get(0), is(expected));
    }

    @Test
    public void increasePeriodMoreThanItCanBe() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2017, 12);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        monthPeriod.increase();
        monthPeriod.increase();
        monthPeriod.increase();
        monthPeriod.increase();
        assertThat(monthPeriod.next().getMonths().get(11), is(expected));
    }

    @Test
    public void nextYear() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2018, 12);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        monthPeriod.increase();
        assertThat(monthPeriod.next().next().getMonths().get(11), is(expected));
    }

    @Test
    public void previousYear() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2018, 12);
        MonthPeriod monthPeriod = new MonthPeriod(yearMonth);
        monthPeriod.increase();
        assertThat(monthPeriod.previous().previous().getMonths().get(11), is(expected));
    }

}
