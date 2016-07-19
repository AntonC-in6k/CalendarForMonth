package monthcalendar;

import monthcalendar.model.ParamHandler;

import java.io.IOException;
import java.time.LocalDate;


/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) throws IOException {
        String[] parameter = new String[]{"-m=7", "-y=2016", "-o=ansii"};
        ParamHandler paramHandler = new ParamHandler(parameter);
        LocalDate date = LocalDate.of(paramHandler.getYear(),paramHandler.getMonth(),14);
        CalendarFacade calendarFacade = new CalendarFacade(date,paramHandler.getFormat());
        Controller controller = new Controller(calendarFacade,date);
        controller.setMonthsInColumn(3);
        controller.setMonthsInRow(4);
        controller.showInterface();
    }
}