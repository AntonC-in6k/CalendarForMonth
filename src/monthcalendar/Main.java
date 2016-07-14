package monthcalendar;

import monthcalendar.model.CreateCalendarForMonth;
import monthcalendar.model.ParamHandler;
import monthcalendar.view.AnsiiCalendarForMonth;
import monthcalendar.view.HtmlCalendarForMonth;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) throws IOException {
        String[] parameter = new String[]{"-m=6", "-y=2016", "-o=ansii"};
        List<DayOfWeek> weekendDays = Arrays.asList(DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY);
        ParamHandler paramHandler = new ParamHandler(parameter);
        CreateCalendarForMonth calendarForMonth = new CreateCalendarForMonth(parameter);
        if (paramHandler.getFormat().equals("html")) {
            HtmlCalendarForMonth htmlCalendar = new HtmlCalendarForMonth();
            htmlCalendar.setWeekendDays(weekendDays);
            htmlCalendar.printCalendar(calendarForMonth.getMonthDays(),
                    calendarForMonth.getDateForView());
        } else {
            AnsiiCalendarForMonth ansiiCalendar = new AnsiiCalendarForMonth(DayOfWeek.SATURDAY);
            ansiiCalendar.setWeekendDays(weekendDays);
            ansiiCalendar.printCalendar(calendarForMonth.getMonthDays(),
                    calendarForMonth.getDateForView());
        }
        System.exit(0);
    }
}