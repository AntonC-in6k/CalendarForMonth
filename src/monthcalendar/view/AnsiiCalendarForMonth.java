package monthcalendar.view;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Mr_Blame on 13.07.2016.
 */
public class AnsiiCalendarForMonth extends CalendarForMonth {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_FOR_WEEKENDS = "\u001B[33m";
    public static final String COLOR_FOR_CURRENT_DAY = "\u001B[34m";

    public AnsiiCalendarForMonth() {
        super();
    }

    public AnsiiCalendarForMonth(DayOfWeek weekStart) {
        super(weekStart);
    }

    public AnsiiCalendarForMonth(DayOfWeek weekStart, LocalDate currentDay) {
        super(weekStart, currentDay);
    }

    @Override
    protected String formatMonthTitle() {
        return COLOR_FOR_WEEKENDS
                + String.format("%36s", createMonthTitle() + "\n")
                + COLOR_RESET;
    }

    @Override
    protected String formatWeekendsInTitle(String dayName) {
        String result = new String();
        if (chooseWeekendsInTitle(dayName) == "weekend") {
            result += COLOR_FOR_WEEKENDS;
        }
        result += String.format("%5s", dayName);

        result += COLOR_RESET;
        return result;
    }

    @Override
    protected String formatDayTitle(String dayTitles) {
        return dayTitles + "\n";
    }

    @Override
    protected String formatDayStyle(LocalDate day) {
        String result = "";
        if (chooseColorForDayPrinting(day) == "weekday") {
            result += COLOR_RESET;
        }
        if (chooseColorForDayPrinting(day) == "weekend") {
            result += COLOR_FOR_WEEKENDS;
        }
        if (chooseColorForDayPrinting(day) == "current") {
            result += COLOR_FOR_CURRENT_DAY;
        }
        result += String.format("%5d", day.getDayOfMonth());

        if (gotoNewLineInTable(day)) {
            result += "\n";
        }
        return result;
    }

    @Override
    protected String printEmptySpace() {
        return String.format("%5s", "");
    }

    @Override
    protected String additionalForMonthDays(String tableDays) {
            return tableDays + "\n";
    }

    @Override
    public void printCalendar(List<LocalDate> monthDays, LocalDate date) throws IOException {
        baseInitialization(monthDays, date);
        System.out.print(formatMonthTitle());
        System.out.print(dayTitleToString());
        System.out.print(monthDaysToString());
    }

    @Override
    public String getCalendar(List<LocalDate> monthDays, LocalDate date) throws IOException {
        baseInitialization(monthDays, date);
        setCurrentDay(date);
        return formatMonthTitle() + dayTitleToString() + monthDaysToString();
    }
}
