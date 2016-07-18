package monthcalendar.view;

/**
 * Created by employee on 7/14/16.
 */

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    private AnsiiCalendarForMonth ansiiCalendar;

    public List<LocalDate> getTableForJulyMonth(int year, int month, int day) {
        List<LocalDate> result = new ArrayList<>();
        LocalDate dateForTest = LocalDate.of(year, month, day);
        for (int i = 1; i <= dateForTest.lengthOfMonth(); i++) {
            result.add(LocalDate.of(year, month, i));
        }
        return result;
    }

    public void loadCalendarForMonth(int year, int month, int day) {
        YearMonth yearMonth = YearMonth.of(year, month);
        ansiiCalendar = new AnsiiCalendarForMonth();
        ansiiCalendar.baseInitialization(getTableForJulyMonth(year, month, day), yearMonth);
    }

    @Test
    public void paramHandle() {
        loadCalendarForMonth(2016, 7, 14);
        List<DayOfWeek> expected = Arrays.asList(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
        ansiiCalendar.setWeekendDays(expected);
        int[] weekendsDaysIndex = {6, 8};
        String line = ansiiCalendar.monthDaysToString().toString();
        assertThat(line, allOf(
                containsString(AnsiiCalendarForMonth.COLOR_FOR_WEEKENDS + "    " + (weekendsDaysIndex[0])),
                containsString(AnsiiCalendarForMonth.COLOR_FOR_WEEKENDS + "    " + (weekendsDaysIndex[1]))));
    }

    @Test
    public void currentDayColor() {
        int day = 14;
        int year = 2016;
        int month = 7;
        int expectedDay = 19;
        YearMonth yearMonth= YearMonth.of(year, month);
        ansiiCalendar = new AnsiiCalendarForMonth(DayOfWeek.WEDNESDAY, LocalDate.of(year, month, expectedDay));
        ansiiCalendar.baseInitialization(getTableForJulyMonth(year, month, day), yearMonth);
        String line = ansiiCalendar.monthDaysToString();
        assertThat(line, containsString(AnsiiCalendarForMonth.COLOR_FOR_CURRENT_DAY + "   " + expectedDay));
    }

    @Test
    public void firstDayInOutput() {
        int day = 14;
        int year = 2016;
        int month = 7;
        YearMonth yearMonth= YearMonth.of(year, month);
        ansiiCalendar = new AnsiiCalendarForMonth(DayOfWeek.WEDNESDAY);
        ansiiCalendar.baseInitialization(getTableForJulyMonth(year, month, day), yearMonth);
        String firstDay = "Wed";
        assertThat(ansiiCalendar.dayTitleToString().toString().trim().substring(0, 3), is(firstDay));
    }
}

