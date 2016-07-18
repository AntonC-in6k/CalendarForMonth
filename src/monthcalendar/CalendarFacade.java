package monthcalendar;

import monthcalendar.model.CreateCalendarForMonth;
import monthcalendar.view.AnsiiCalendarForMonth;
import monthcalendar.view.CalendarForMonth;
import monthcalendar.view.HtmlCalendarForMonth;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Mr_Blame on 14.07.2016.
 */
public class CalendarFacade implements Calendar {
    private CreateCalendarForMonth createCalendarForMonth;
    private CalendarForMonth calendarForMonth;
    private List<LocalDate> monthDays;

    public CalendarFacade(LocalDate date, String format) throws IOException {
        if (format.equals("ansii")) {
            calendarForMonth = new AnsiiCalendarForMonth(date);
        } else {
            calendarForMonth = new HtmlCalendarForMonth(date);
        }

    }

    @Override
    public String generate(YearMonth yearMonth) throws IOException {
        createCalendarForMonth = new CreateCalendarForMonth(yearMonth.getMonthValue(), yearMonth.getYear());
        this.monthDays = createCalendarForMonth.getMonthDays();
        return calendarForMonth.getCalendar(monthDays, yearMonth);
    }

//    @Override
//    public String generate(Supplier<LocalDate> dateSupplier) throws IOException {
//        return generate(dateSupplier.get());
//    }
}