package monthcalendar.view;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Created by Mr_Blame on 13.07.2016.
 */

public abstract class Calendar {
    public static final int DAYS_IN_WEAK = 7;
    public static final String STYLE_OF_SHORT_DAYS_NAMES = "en";
    public static final List<String> WEEKEND_SHORT_NAMES =
            Arrays.asList(DayOfWeek.SATURDAY.getDisplayName(TextStyle.SHORT,
                    new Locale(STYLE_OF_SHORT_DAYS_NAMES)),
                    DayOfWeek.SUNDAY.getDisplayName(TextStyle.SHORT,
                            new Locale(STYLE_OF_SHORT_DAYS_NAMES)));

    private List<LocalDate> monthDays;
    private int month;
    private int year;
    private LocalDate dayForTracking;

    public Calendar(List<LocalDate> monthDays, LocalDate date) {
        this.monthDays = monthDays;
        this.month = date.getMonthValue();
        this.year = date.getYear();
        dayForTracking = LocalDate.now();
    }

    protected abstract String formatMonthTitle();

    protected String createMonthTitle() {
        LocalDate dateForTitle = LocalDate.of(year, month, 1);
        String result = dateForTitle.getMonth().toString() + " " + dateForTitle.getYear();
        return result;
    }

    protected abstract String formatDayTitleLine(String dayTitles);

    protected String makeTitle() {
        String result = new String();
        for (String dayName :
                createDaysTitle()) {
            result += formatDayTitle(dayName);
        }
        return formatDayTitleLine(result);
    }


    protected abstract String formatDayTitle(String dayName);

    protected String chooseDayTitle(String dayName) {
        String result = new String();
        if (dayName.equals(WEEKEND_SHORT_NAMES.get(0)) || dayName.equals(WEEKEND_SHORT_NAMES.get(1))) {
            result = "weekend";
        }
        return result;
    }

    protected List<String> createDaysTitle() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= DAYS_IN_WEAK; i++) {
            result.add(i - 1, DayOfWeek.of(i).getDisplayName(TextStyle.SHORT,
                    new Locale(STYLE_OF_SHORT_DAYS_NAMES)));
        }
        return result;
    }

    protected String monthDaysToString() {
        String result = "";
        for (int i = 0; i < formatMonthDays().size(); i++) {
            result += formatMonthDays().get(i);
        }
        return additionalForMonthDays(result);
    }

    protected abstract String additionalForMonthDays(String tableDays);

    protected List<String> formatMonthDays() {
        List<String> result = new ArrayList<>();
        int numberOfEmptySpaces = monthDays.get(0).getDayOfWeek().getValue() - 1;
        for (int i = 0; i < numberOfEmptySpaces; i++) {
            result.add(printEmptySpace());
        }
        for (int i = 0; i < monthDays.size(); i++) {
            result.add(formatDay(monthDays.get(i)));
        }
        return result;
    }

    protected abstract String formatDay(LocalDate day);

    protected String chooseColorForDayPrinting(LocalDate day) {
        if (day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return "weekend";
        }
        if (day.isEqual(dayForTracking)) {
            return "current";
        }
        return "weekday";
    }

    protected abstract String printEmptySpace();

    protected boolean gotoNewLineInTable(LocalDate day) {
        return day.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public abstract void printCalendar() throws IOException;
}
