package monthcalendar; /**
 * Created by Mr_Blame on 17.07.2016.
 */

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class ControllerTest implements Calendar {

    private final static List<YearMonth> YEAR_OF_2016 = Arrays.asList(
            YearMonth.of(2016, 1), YearMonth.of(2016, 2),
            YearMonth.of(2016, 3), YearMonth.of(2016, 4),
            YearMonth.of(2016, 5), YearMonth.of(2016, 6),
            YearMonth.of(2016, 7), YearMonth.of(2016, 8),
            YearMonth.of(2016, 9), YearMonth.of(2016, 10),
            YearMonth.of(2016, 11), YearMonth.of(2016, 12)
            );

    private Controller controller;
    private Controller spy;
    private LocalDate testDate;
    private Integer numberOfGenerateCalls;

    @Override
    public String generate(YearMonth yearMonth) throws IOException{
        numberOfGenerateCalls+=1;
        return "";
    }


    @Before
    public void setup() throws Exception {
        numberOfGenerateCalls=0;
        testDate = LocalDate.of(2016,7,14);
        controller = new Controller(this, testDate);
        spy = spy(controller);
    }

    @Test
    public void numberOfCallsCreateForNext() throws Exception {
        spy.showNext();
        spy.showNext();
        verify(spy, times(2)).createCalendar();
    }

    @Test
    public void numberOfCalls() throws Exception {
        spy.showNext();
        spy.showPrevious();
        YearMonth yearMonth = YearMonth.of(2016,7);
        assertThat(spy.getPeriod().getMonths().get(0),is(yearMonth));
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

    @Test
    public void rightOrderOfParamInNext() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        controller.showNext();
        parameterCallOrder.add(controller.getPeriod().getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth().plus(1));
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void rightOrderOfParamInPrevious() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        controller.showPrevious();
        parameterCallOrder.add(controller.getPeriod().getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth().minus(1));
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void rightOrderOfParamInIncrease() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(12);
        controller.increasePeriod();
        parameterCallOrder.addAll(controller.getPeriod().getMonths());
        assertThat(parameterCallOrder, is(YEAR_OF_2016));
    }

    @Test
    public void rightOrderOfParamInDegrees() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        controller.degreesPeriod();
        parameterCallOrder.add(controller.getPeriod().getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth());
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void numberOfGenerateCalls() throws Exception {
        controller.showNext();
        controller.increasePeriod();
        controller.degreesPeriod();
        controller.showPrevious();
        int expected = 15;
        assertThat(numberOfGenerateCalls, is(expected));
    }

}
