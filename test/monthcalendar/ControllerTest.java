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

import static monthcalendar.ButtonCommands.*;
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
        spy.performCalendar(spy.performCommand(MOVE_RIGHT));
        spy.performCalendar(spy.performCommand(MOVE_RIGHT));
        verify(spy, times(2)).createCalendar();
    }


    @Test
    public void numberOfCallsCreateForPrevious() throws Exception {
        spy.performCalendar(spy.performCommand(MOVE_LEFT));
        verify(spy, times(1)).createCalendar();
    }


    @Test
    public void checkIncreasePeriod() throws Exception {
        assertTrue(spy.performCommand(MOVE_UP) instanceof YearPeriod);
    }

    @Test
    public void checkDegreesPeriod() throws Exception {
        assertTrue(spy.performCommand(MOVE_DOWN) instanceof MonthPeriod);
    }

    @Test
    public void rightOrderOfParamInNext() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        parameterCallOrder.add(spy.performCommand(MOVE_RIGHT).getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth().plus(1));
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void rightOrderOfParamInPrevious() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        parameterCallOrder.add(spy.performCommand(MOVE_LEFT).getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth().minus(1));
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void rightOrderOfParamInIncrease() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(12);
        parameterCallOrder.addAll(spy.performCommand(MOVE_UP).getMonths());
        assertThat(parameterCallOrder, is(YEAR_OF_2016));
    }

    @Test
    public void rightOrderOfParamInDegrees() throws Exception {
        List<YearMonth> parameterCallOrder = new ArrayList<>(1);
        parameterCallOrder.add(spy.performCommand(MOVE_DOWN).getMonths().get(0));
        YearMonth expected = YearMonth.of(testDate.getYear(),testDate.getMonth());
        assertThat(parameterCallOrder.get(0), is(expected));
    }

    @Test
    public void numberOfGenerateCalls() throws Exception {
        spy.performCalendar(spy.performCommand(MOVE_RIGHT));
        spy.performCalendar(spy.performCommand(MOVE_UP));
        spy.performCalendar(spy.performCommand(MOVE_DOWN));
        spy.performCalendar(spy.performCommand(MOVE_LEFT));
        int expected = 15;
        assertThat(numberOfGenerateCalls, is(expected));
    }

    @Test
    public void formatTitleCalendarToTable() throws Exception {
        List<String> testList = Arrays.asList("July 2016\n","March 2016\n", "May 2016\n");
        String expected = "July 2016\tMarch 2016\tMay 2016\t\n\n";
        CalendarFacade calendarFacade = new CalendarFacade(testDate,"ansii");
        Controller controller = new Controller(calendarFacade,testDate);
        controller.setMonthsInColumn(1);
        assertThat(controller.formatCalendarToTable(testList), is(expected));
    }

    @Test
    public void formatDaysTitleCalendarToTable() throws Exception {
        List<String> testList = Arrays.asList(
                "July 2016\n"+
                        "Mon    Tue     Wed     Thu     Fri\n",
                "March 2016\n"+
                        "Mon    Tue     Wed     Thu     Fri\n",
                "May 2016\n"+
                        "Mon    Tue     Wed     Thu     Fri\n");

        String expected =
                "July 2016\tMarch 2016\tMay 2016\t\n"+
                "Mon    Tue     Wed     Thu     Fri\t"+
                "Mon    Tue     Wed     Thu     Fri\t"+
                "Mon    Tue     Wed     Thu     Fri\t"+
                "\n\n";
        CalendarFacade calendarFacade = new CalendarFacade(testDate,"ansii");
        Controller controller = new Controller(calendarFacade,testDate);
        controller.setMonthsInColumn(1);
        assertThat(controller.formatCalendarToTable(testList), is(expected));
    }

    @Test
    public void formatWhen() throws Exception {
        List<String> testList = Arrays.asList(
                "July 2016\n"+
                        "1      2       3       4       5       6       7\n",
                "March 2016\n"+
                        "1      2       3       4       5       6       7\n"+
                "8      9\n",
                "May 2016\n"+
                        "1      2       3       4       5       6       7\n");

        String expected =
                "July 2016\tMarch 2016\tMay 2016\t\n"+
                        "1      2       3       4       5       6       7\t"+
                        "1      2       3       4       5       6       7\t"+
                        "1      2       3       4       5       6       7\t\n"+
                        "                                               \t"+
                        "8      9\t"+
                        "                                               \t\n\n";
        CalendarFacade calendarFacade = new CalendarFacade(testDate,"ansii");
        Controller controller = new Controller(calendarFacade,testDate);
        controller.setMonthsInColumn(1);
        assertThat(controller.formatCalendarToTable(testList), is(expected));
    }



}
