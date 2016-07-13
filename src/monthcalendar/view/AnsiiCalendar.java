package monthcalendar.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mr_Blame on 13.07.2016.
 */
public class AnsiiCalendar extends Calendar {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_FOR_WEEKENDS = "\u001B[33m";
    public static final String COLOR_FOR_CURRENT_DAY = "\u001B[34m";

    public AnsiiCalendar(List<LocalDate> monthDays, LocalDate date) {
        super(monthDays, date);
    }

    @Override
    protected String formatMonthTitle() {
        return COLOR_FOR_WEEKENDS
                + String.format("%35s", createMonthTitle())
                + COLOR_RESET;
    }

    @Override
    protected String formatDaysTitle() {
        String result = new String();
        for (String day :
                createDaysTitle()) {
            if (day.equals(DayOfWeek.SATURDAY
                    .getDisplayName(TextStyle.SHORT
                            , new Locale(STYLE_OF_SHORT_DAYS_NAMES)))) {
                result += COLOR_FOR_WEEKENDS;
            }
            result += String.format("%5s", day);
        }
        result += COLOR_RESET;
        return result;
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
    public void printCalendar() {
        System.out.println(formatMonthTitle());
        System.out.println(formatDaysTitle());
        System.out.println(monthDaysToString());
    }

}
