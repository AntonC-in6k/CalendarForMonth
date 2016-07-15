package monthcalendar;

/**
 * Created by employee on 7/15/16.
 */

import monthcalendar.view.AnsiiCalendarForMonth;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SupplierTest {

    @Test
    public void checkDayForTracking() throws Exception {
        CalendarFacade calendarFacade = new CalendarFacade(7,
                2016, "ansii");

        int day = 13;
        assertThat(calendarFacade.generate(LocalDate.of(2016, 7, day)),
                containsString(AnsiiCalendarForMonth.COLOR_FOR_CURRENT_DAY + "   " + day));
    }
}
