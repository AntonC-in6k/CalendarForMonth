package monthcalendar.view;


import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class AnsiiOutputTest {

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
        LocalDate date = LocalDate.of(year, month, day);
        ansiiCalendar = new AnsiiCalendarForMonth();
        ansiiCalendar.baseInitialization(getTableForJulyMonth(year, month, day), date);
    }

    @Test
    public void firstDayInOutput() {
        loadCalendarForMonth(2016, 7, 14);
        String firstDay = "Mon";
        assertThat(ansiiCalendar.makeTitle().toString().trim().substring(0, 3), is(firstDay));
    }

    @Test
    public void tableOutputTitle() {
        loadCalendarForMonth(2016, 7, 14);
        String[] daysTitle = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String title = ansiiCalendar.makeTitle().toString().trim();
        title = title.replace(AnsiiCalendarForMonth.COLOR_FOR_WEEKENDS, "");
        title = title.replace(AnsiiCalendarForMonth.COLOR_RESET, "");
        String[] days = title.split("  ");
        assertThat(days, is(daysTitle));
    }

    @Test
    public void tableOutputMonthTitle() {
        loadCalendarForMonth(2016, 7, 14);
        String outputLine;
        String expectedMonthAndYear = "JULY 2016";
        outputLine = ansiiCalendar.formatMonthTitle().toString();
        assertThat(outputLine, is(containsString(expectedMonthAndYear)));
    }

    @Test
    public void tableOutputFormat() {
        loadCalendarForMonth(2016, 7, 14);
        int[] mondayDayIndex = {3, 10, 17, 24};
        String line = ansiiCalendar.monthDaysToString();
        assertThat(line, allOf(containsString(mondayDayIndex[0] + "\n"),
                containsString(mondayDayIndex[1] + "\n"),
                containsString(mondayDayIndex[2] + "\n"),
                containsString(mondayDayIndex[3] + "\n")));
    }

    @Test
    public void anotherColorForWeekend() {
        loadCalendarForMonth(2016, 7, 14);
        int[] weekendsDaysIndex = {2, 3};
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
        LocalDate date = LocalDate.of(year, month, day);
        ansiiCalendar = new AnsiiCalendarForMonth(DayOfWeek.WEDNESDAY, LocalDate.of(year, month, expectedDay));
        ansiiCalendar.baseInitialization(getTableForJulyMonth(year, month, day), date);
        String line = ansiiCalendar.monthDaysToString();
        assertThat(line, containsString(AnsiiCalendarForMonth.COLOR_FOR_CURRENT_DAY + "   " + expectedDay));
    }

}