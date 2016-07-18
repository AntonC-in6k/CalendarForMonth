package monthcalendar.view;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Created by Mr_Blame on 13.07.2016.
 */

public abstract class CalendarForMonth  {
    public static final int DAYS_IN_WEEK = 7;
    public static final String STYLE_OF_SHORT_DAYS_NAMES = "en";

    private List<LocalDate> monthDays;
    private int month;
    private int year;
    private List<DayOfWeek> weekendDays;
    private DayOfWeek weekStart;
    private LocalDate currentDay;

    public CalendarForMonth() {
        this.weekStart=DayOfWeek.MONDAY;
        currentDay = LocalDate.now();
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    public CalendarForMonth(LocalDate currentDay){
        this.weekStart=DayOfWeek.MONDAY;
        this.currentDay = currentDay;
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    public CalendarForMonth(DayOfWeek weekStart){
        this.weekStart=weekStart;
        currentDay = LocalDate.now();
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    public CalendarForMonth(DayOfWeek weekStart, LocalDate currentDay){
        this.weekStart=weekStart;
        this.currentDay = currentDay;
        weekendDays = Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
    }

    protected void setMonthDays(List<LocalDate> monthDays){
        this.monthDays = monthDays;
    }

    protected void setMonth(YearMonth yearMonth){
        this.month = yearMonth.getMonthValue();
    }

    protected void setYear(YearMonth yearMonth){
        this.year = yearMonth.getYear();
    }


    public void setWeekendDays(List<DayOfWeek> weekendDays){
        this.weekendDays=weekendDays;
    }

    protected abstract String formatMonthTitle();

    protected String createMonthTitle() {
        LocalDate dateForTitle = LocalDate.of(year, month, 1);
        String result = dateForTitle.getMonth().toString() + " " + dateForTitle.getYear();
        return result;
    }

    protected abstract String formatDayTitle(String dayTitles);

    protected String dayTitleToString() {
        String result = new String();
        for (String dayName :
                createDaysTitle()) {
            result += formatWeekendsInTitle(dayName);
        }
        return formatDayTitle(result);
    }

    protected abstract String formatWeekendsInTitle(String dayName);

    protected String chooseWeekendsInTitle(String dayName) {
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
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            result.add(i-1, weekStart.plus(i-1).getDisplayName(TextStyle.SHORT,
                    new Locale(STYLE_OF_SHORT_DAYS_NAMES)));
        }
        return result;
    }

    protected String monthDaysToString() {
        String result = "";
        for (int i = 0; i < createTableForDays().size(); i++) {
            result += createTableForDays().get(i);
        }
        return additionalForMonthDays(result);
    }

    protected abstract String additionalForMonthDays(String tableDays);

    protected List<String> createTableForDays() {
        List<String> result = new ArrayList<>();
        int numberOfEmptySpaces = monthDays.get(0).getDayOfWeek().getValue()-weekStart.getValue();
        if (numberOfEmptySpaces<0){numberOfEmptySpaces=monthDays.get(0).getDayOfWeek().getValue()+(DAYS_IN_WEEK-weekStart.getValue());}
        for (int i = 0; i < numberOfEmptySpaces; i++) {
            result.add(printEmptySpace());
        }
        for (int i = 0; i < monthDays.size(); i++) {
            result.add(formatDayStyle(monthDays.get(i)));
        }
        return result;
    }

    protected abstract String formatDayStyle(LocalDate day);

    protected String chooseColorForDayPrinting(LocalDate day) {
        String result="weekday";
        if (day.getDayOfWeek().equals(weekendDays.get(0)) || day.getDayOfWeek().equals(weekendDays.get(1))) {
            result = "weekend";
        }
        if (day.isEqual(currentDay)) {
            result = "current";
        }
        return result;
    }

    protected abstract String printEmptySpace();

    protected boolean gotoNewLineInTable(LocalDate day) {
        return day.getDayOfWeek() == weekStart.minus(1);
    }

    public abstract void printCalendar(List<LocalDate> monthDays, YearMonth yearMonth) throws IOException;

    protected void baseInitialization(List<LocalDate> monthDays, YearMonth yearMonth){
        setMonthDays(monthDays);
        setMonth(yearMonth);
        setYear(yearMonth);
    }

    public abstract String getCalendar(List<LocalDate> monthDays, YearMonth yearMonth) throws IOException;

}
