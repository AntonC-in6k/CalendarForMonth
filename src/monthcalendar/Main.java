package monthcalendar;

import monthcalendar.model.CreateCalendarForMonth;
import monthcalendar.view.AnsiiCalendar;


/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) {
        String[] parameter = new String[]{"-m=7","-y=2016","-o=html"};
        CreateCalendarForMonth calendarForMonth = new CreateCalendarForMonth(parameter);
        AnsiiCalendar ansiiCalendar = new AnsiiCalendar(calendarForMonth.getMonthDays(),calendarForMonth.getDateForView()); //My program gets parameter in format -m= *, where m - month
        ansiiCalendar.printCalendar();
        System.exit(0);
    }
}