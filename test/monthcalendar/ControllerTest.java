package monthcalendar; /**
 * Created by Mr_Blame on 17.07.2016.
 */

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class ControllerTest {

    private Controller controller;
    private Controller spy;

    @Before
    public void setup() throws Exception {
        LocalDate date = LocalDate.of(2016, 7, 17);
        controller = new Controller(date, "ansii");
        spy = spy(controller);
    }

    @Test
    public void rightMonthOutput() throws Exception {

        assertThat(controller.createCalendar(), containsString("JULY 2016"));
    }

    @Test
    public void numberOfCallsCreateForNext() throws Exception {
        spy.showNext();
        spy.showNext();
        verify(spy, times(2)).createCalendar();
    }

    @Test
    public void numberOfCallsCreateForPrevious() throws Exception {
        spy.showPrevious();
        verify(spy, times(1)).createCalendar();
    }

    @Test
    public void onDButtonPress() throws Exception {
        spy.handleButton("d");
        spy.handleButton("d");
        verify(spy, times(2)).showNext();
    }

    @Test
    public void onWButtonPress() throws Exception {
        spy.handleButton("w");
        spy.handleButton("w");
        spy.handleButton("w");
        verify(spy, times(3)).increasePeriod();
    }

    @Test
    public void onSButtonPress() throws Exception {
        spy.handleButton("s");
        verify(spy, times(1)).degreesPeriod();
    }

    @Test
    public void onAButtonPress() throws Exception {
        spy.handleButton("a");
        verify(spy, times(1)).showPrevious();
    }

    @Test
    public void onEButtonPress() throws Exception {
        spy.handleButton("e");
        verify(spy, times(0)).createCalendar();
    }

    @Test
    public void checkIncreasePeriod() throws Exception {
        spy.increasePeriod();
        assertTrue(spy.getPeriod() instanceof YearPeriod);
    }

    @Test
    public void checkDegreesPeriod() throws Exception {
        spy.degreesPeriod();
        assertTrue(spy.getPeriod() instanceof MonthPeriod);
    }

}
