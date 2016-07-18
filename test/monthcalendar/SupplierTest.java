package monthcalendar;

/**
 * Created by employee on 7/15/16.
 */

import monthcalendar.view.AnsiiCalendarForMonth;
import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SupplierTest {

    @Test
    public void checkDayForTracking() throws Exception {
        CalendarFacade calendarFacade = new CalendarFacade(LocalDate.of(2016,7,13), "ansii");
        int day = 13;
        assertThat(calendarFacade.generate(YearMonth.of(2016,7)),
                containsString(AnsiiCalendarForMonth.COLOR_FOR_CURRENT_DAY + "   " + day));
    }
}
