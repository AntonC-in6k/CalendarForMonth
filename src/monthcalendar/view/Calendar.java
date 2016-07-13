package monthcalendar.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mr_Blame on 13.07.2016.
 */
public abstract class Calendar {
    public static final int DAYS_IN_WEAK = 7;
    public static final String STYLE_OF_SHORT_DAYS_NAMES = "en";
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

    protected abstract String formatDaysTitle();

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
        return result;
    }

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

    private String printEmptySpace() {
        return String.format("%5s", "");
    }

    protected boolean gotoNewLineInTable(LocalDate day) {
        return day.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public abstract void printCalendar();
}
