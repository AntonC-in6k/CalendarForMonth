package monthcalendar;

import org.junit.Test;

import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public class PeriodTest {


    @Test
    public void returnNextMonth() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 12);
        YearMonth expected = YearMonth.of(2017, 1);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.next().getMonths().get(0), is(expected));
    }


    @Test
    public void returnPreviousMonth() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 8);
        YearMonth expected = YearMonth.of(2016, 7);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.previous().getMonths().get(0), is(expected));
    }

    @Test
    public void nextPrevious() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 3);
        YearMonth expected = YearMonth.of(2016, 3);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.next().previous().getMonths().get(0), is(expected));
    }

    @Test
    public void increasePeriod() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2017, 12);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.increase().next().getMonths().get(11), is(expected));
    }


    @Test
    public void degreesPeriod() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2016, 2);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.increase().degrees().next().getMonths().get(0), is(expected));
    }

    @Test
    public void increasePeriodMoreThanItCanBe() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2017, 12);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.increase().increase().next().getMonths().get(11), is(expected));
    }

    @Test
    public void nextYear() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2018, 12);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.increase().next().next().getMonths().get(11), is(expected));
    }

    @Test
    public void previousYear() throws Exception {
        YearMonth yearMonth = YearMonth.of(2016, 1);
        YearMonth expected = YearMonth.of(2014, 12);
        MonthPeriod monthPeriodPeriod = new MonthPeriod(yearMonth);
        assertThat(monthPeriodPeriod.increase().previous().previous().getMonths().get(11), is(expected));
    }

}
