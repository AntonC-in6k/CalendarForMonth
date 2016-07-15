package monthcalendar;

import monthcalendar.model.CreateCalendarForMonth;
import monthcalendar.view.AnsiiCalendarForMonth;
import monthcalendar.view.CalendarForMonth;
import monthcalendar.view.HtmlCalendarForMonth;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public CalendarFacade(int month, int year, String format) throws IOException {
        createCalendarForMonth = new CreateCalendarForMonth(month, year);
        this.monthDays = createCalendarForMonth.getMonthDays();
        if (format.equals("ansii")) {
            calendarForMonth = new AnsiiCalendarForMonth(DayOfWeek.SUNDAY);
        } else {
            calendarForMonth = new HtmlCalendarForMonth();
        }

    }

    @Override
    public String generate(LocalDate date) throws IOException {
        return calendarForMonth.getCalendar(monthDays, date);
    }

    @Override
    public String generate(Supplier<LocalDate> dateSupplier) throws IOException {
        return generate(dateSupplier.get());
    }
}