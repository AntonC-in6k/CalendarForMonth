package monthcalendar.view;

import monthcalendar.Calendar;

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

public abstract class CalendarForMonth implements Calendar {
    public static final int DAYS_IN_WEAK = 7;
    public static final String STYLE_OF_SHORT_DAYS_NAMES = "en";

    private List<LocalDate> monthDays;
    private int month;
    private int year;
    private List<DayOfWeek> weekendDays;
    private DayOfWeek weekStart;
    private LocalDate dayForTracking;

    public CalendarForMonth() {
        dayForTracking = LocalDate.now();
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);

    }

    public CalendarForMonth(DayOfWeek weekStart){
        this.weekStart=weekStart;
        dayForTracking = LocalDate.now();
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    public CalendarForMonth(DayOfWeek weekStart, LocalDate currentDay){
        this.weekStart=weekStart;
        dayForTracking = currentDay;
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    protected void setMonthDays(List<LocalDate> monthDays){
        this.monthDays = monthDays;
    }

    protected void setMonth(LocalDate date){
        this.month = date.getMonthValue();
    }

    protected void setYear(LocalDate date){
        this.year = date.getYear();
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
        if (dayName.equals(weekendDays.get(0).getDisplayName(TextStyle.SHORT,
                new Locale(STYLE_OF_SHORT_DAYS_NAMES))) ||
                dayName.equals(weekendDays.get(1).getDisplayName(TextStyle.SHORT,
                new Locale(STYLE_OF_SHORT_DAYS_NAMES)))) {
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
        if (day.getDayOfWeek() == weekendDays.get(0) || day.getDayOfWeek() == weekendDays.get(1)) {
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

    public abstract void printCalendar(List<LocalDate> monthDays, LocalDate date) throws IOException;

    public void baseInitialization(List<LocalDate> monthDays, LocalDate date){
        setMonthDays(monthDays);
        setMonth(date);
        setYear(date);
    }

    @Override
    public void setWeekendDays(List<DayOfWeek> weekendDays){
        this.weekendDays=weekendDays;
    }

}
