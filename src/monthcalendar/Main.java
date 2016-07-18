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
        String[] parameter = new String[]{"-m=6", "-y=2016", "-o=ansii"};
        ParamHandler paramHandler = new ParamHandler(parameter);
        Controller controller = new Controller(LocalDate.of(
                paramHandler.getYear(),paramHandler.getMonth(),14),paramHandler.getFormat());
        controller.showInterface();
    }
}