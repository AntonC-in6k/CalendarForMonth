package monthcalendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public class Controller {
    private List<YearMonth> yearMonth;
    private LocalDate localDate;
    private String format;
    private Period monthPeriod;
    private Calendar calendar;

    public Controller(Calendar calendar, LocalDate date) throws IOException {
        localDate = date;
        this.calendar = calendar;
        this.yearMonth = Arrays.asList(YearMonth.of(localDate.getYear(), localDate.getMonth()));
        format = "ansii";
        monthPeriod = new MonthPeriod(yearMonth.get(0));
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Period getPeriod() {
        return monthPeriod;
    }

    private void setMonthPeriod(Period period) {
        monthPeriod = period;
    }

    private void setMonthPeriod(YearPeriod period) {
        monthPeriod = period;
    }

    private void setMonthPeriod(MonthPeriod period) {
        monthPeriod = period;
    }

    public void showInterface() throws IOException {
        System.out.println("w-increase period, s-degrees period, " +
                "d-show next, a-show previous, e-exit");
        boolean flag = true;
        String button;
        while (flag) {
            button = buttonPress();
            if (button.equals("e")) {
                flag = false;
                break;
            }
            handleButton(button);
        }
    }

    protected String buttonPress() {
        String result;
        Scanner scanner = new Scanner(System.in);
        result = scanner.next();
        return result;
    }

    public void handleButton(String button) throws IOException {
        switch (button) {
            case "w":
                increasePeriod();
                break;
            case "s":
                degreesPeriod();
                break;
            case "a":
                showPrevious();
                break;
            case "d":
                showNext();
                break;
        }
    }

    protected void showNext() throws IOException {
        setMonthPeriod(monthPeriod.next());
        yearMonth = monthPeriod.getMonths();
        System.out.println(createCalendar());
    }

    protected void showPrevious() throws IOException {
        setMonthPeriod(monthPeriod.previous());
        yearMonth = monthPeriod.getMonths();
        System.out.println(createCalendar());
    }

    protected void increasePeriod() throws IOException {
        setMonthPeriod(new YearPeriod(yearMonth.get(0)));
        yearMonth = monthPeriod.increase().getMonths();
        System.out.println(createCalendar());
    }

    protected void degreesPeriod() throws IOException {
        setMonthPeriod(new MonthPeriod(yearMonth.get(0)));
        yearMonth = monthPeriod.degrees().getMonths();
        System.out.println(createCalendar());
    }

    protected String createCalendar() throws IOException {
        String result = "";
        for (YearMonth date :
                yearMonth) {
            result += calendar.generate(date);
        }
        return result;
    }
}
