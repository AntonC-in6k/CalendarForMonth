package monthcalendar.model;

/**
 * Created by Mr_Blame on 13.07.2016.
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateCalendarForMonth {
    private int daysInMonth;
    private int month;
    private int year;

    private List<LocalDate> monthDays;
    private ParamHandler paramHandler;

    public CreateCalendarForMonth(String[] parameter) {
        paramHandler = new ParamHandler(parameter);
        month = paramHandler.getMonth();
        year = paramHandler.getYear();
        daysInMonth = getNumberOfDays();

        monthDays = createMonthDays();
    }

    public List<LocalDate> getMonthDays() {
        return monthDays;
    }

    public LocalDate getDateForView() {
        LocalDate result = LocalDate.of(year, month, LocalDate.now().getDayOfMonth());
        return result;
    }

    private int getNumberOfDays() {
        int result = getDate().lengthOfMonth();
        return result;
    }

    private LocalDate getDate() {
        LocalDate result = LocalDate.of(year, month, 1);
        return result;
    }

    private List<LocalDate> createMonthDays() {
        List<LocalDate> result = new ArrayList<>();
        for (int i = 1; i <= daysInMonth; i++) {
            result.add(LocalDate.of(year, month, i));
        }
        return result;
    }

}
