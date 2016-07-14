package monthcalendar;

import monthcalendar.model.CreateCalendarForMonth;
import monthcalendar.model.ParamHandler;
import monthcalendar.view.AnsiiCalendar;
import monthcalendar.view.HtmlCalendar;

import java.io.IOException;


/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) throws IOException {
        String[] parameter = new String[]{"-m=7", "-y=2016", "-o=html"};
        ParamHandler paramHandler = new ParamHandler(parameter);
        CreateCalendarForMonth calendarForMonth = new CreateCalendarForMonth(parameter);
        if (paramHandler.getFormat().equals("html")) {
            HtmlCalendar htmlCalendar = new HtmlCalendar(calendarForMonth.getMonthDays(),
                    calendarForMonth.getDateForView());
            htmlCalendar.printCalendar();
        } else {
            AnsiiCalendar ansiiCalendar = new AnsiiCalendar(calendarForMonth.getMonthDays(),
                    calendarForMonth.getDateForView()); //My program gets parameter in format -m= *, where m - month
            ansiiCalendar.printCalendar();
        }
        System.exit(0);
    }
}