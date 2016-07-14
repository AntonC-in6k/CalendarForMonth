package monthcalendar.view;

import java.io.IOException;
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

    @Override
    protected String formatMonthTitle() {
        return COLOR_FOR_WEEKENDS
                + String.format("%35s", createMonthTitle())
                + COLOR_RESET;
    }

    @Override
    protected String formatDayTitle(String dayName) {
        String result = new String();
        if (chooseDayTitle(dayName) == "weekend") {
            result += COLOR_FOR_WEEKENDS;
        }
        result += String.format("%5s", dayName);

        result += COLOR_RESET;
        return result;
    }

    @Override
    protected String formatDayTitleLine(String dayTitles) {
        return dayTitles + "\n";
    }

    @Override
    protected String formatDay(LocalDate day) {
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
        System.out.println(formatMonthTitle());
        System.out.println(makeTitle());
        System.out.println(monthDaysToString());
    }

}
