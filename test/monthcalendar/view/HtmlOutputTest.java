package monthcalendar.view;

/**
 * Created by employee on 7/14/16.
 */

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class HtmlOutputTest {
    private HtmlCalendarForMonth htmlCalendar;

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
        htmlCalendar = new HtmlCalendarForMonth();
        htmlCalendar.baseInitialization(getTableForJulyMonth(year, month, day), date);
    }

    @Test
    public void firstDayInOutput() {
        loadCalendarForMonth(2016, 7, 14);
        String firstDay = "<td> Mon </td>";
        assertThat(htmlCalendar.dayTitleToString().toString().trim(), is(containsString(firstDay)));
    }

    @Test
    public void tableOutputTitle() {
        loadCalendarForMonth(2016, 7, 14);
        String[] daysTitle = new String[]{
                "<td> Mon </td>", "<td> Tue </td>", "<td> Wed </td>",
                "<td> Thu </td>", "<td> Fri </td>",
                "<td class=\"weekend-day\"> Sat </td>",
                "<td class=\"weekend-day\"> Sun </td>"
        };
        String title = htmlCalendar.dayTitleToString().toString().trim();
        assertThat(title, allOf(
                containsString(daysTitle[0]),
                containsString(daysTitle[1]),
                containsString(daysTitle[2]),
                containsString(daysTitle[3]),
                containsString(daysTitle[4]),
                containsString(daysTitle[5]),
                containsString(daysTitle[6])));
    }

    @Test
    public void tableOutputMonthTitle() {
        loadCalendarForMonth(2016, 7, 14);
        String outputLine;
        String expectedMonthAndYear = "JULY 2016";
        outputLine = htmlCalendar.formatMonthTitle().toString().trim();
        assertThat(outputLine, is(containsString(expectedMonthAndYear)));
    }

    @Test
    public void tableOutputFormat() {
        loadCalendarForMonth(2016, 7, 14);
        int[] sundayDayIndex = {3, 10, 17, 24};
        String line = htmlCalendar.monthDaysToString();
        String endOfLine = " </td>\n</tr>";
        assertThat(line, allOf(containsString(sundayDayIndex[0] + endOfLine),
                containsString(sundayDayIndex[1] + endOfLine),
                containsString(sundayDayIndex[2] + endOfLine),
                containsString(sundayDayIndex[3] + endOfLine)));
    }

    @Test
    public void anotherColorForWeekend() {
        loadCalendarForMonth(2016, 7, 14);
        int[] weekendDaysIndex = {2, 3};
        String line = htmlCalendar.monthDaysToString().toString();
        assertThat(line, allOf(
                containsString("<td class=\"weekend-day\">" + (weekendDaysIndex[0])),
                containsString("<td class=\"weekend-day\">" + (weekendDaysIndex[1]))));
    }

    @Test
    public void currentDayColor() {
        int day = 14;
        int year = 2016;
        int month = 7;
        int expectedDay = 19;
        LocalDate date = LocalDate.of(year, month, day);
        htmlCalendar = new HtmlCalendarForMonth(DayOfWeek.WEDNESDAY, LocalDate.of(year, month, expectedDay));
        htmlCalendar.baseInitialization(getTableForJulyMonth(year, month, day), date);
        String line = htmlCalendar.monthDaysToString();
        assertThat(line, containsString("<td class=\"current-day\">" + expectedDay));
    }
}
