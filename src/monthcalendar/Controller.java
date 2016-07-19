package monthcalendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static monthcalendar.ButtonCommands.*;

/**
 * Created by Mr_Blame on 17.07.2016.
 */
public class Controller {
    private List<YearMonth> yearMonth;
    private LocalDate localDate;
    private String format;
    private Period monthPeriod;
    private Calendar calendar;
    private CommandHandler commandHandler;
    private int columns;
    private int rows;

    public Controller(Calendar calendar, LocalDate date) throws IOException {
        localDate = date;
        this.calendar = calendar;
        this.yearMonth = Arrays.asList(YearMonth.of(localDate.getYear(), localDate.getMonth()));
        format = "ansii";
        monthPeriod = new MonthPeriod(yearMonth.get(0));
        commandHandler = new CommandHandler();
        columns = 3;
        rows = 4;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Period getPeriod() {
        return monthPeriod;
    }

    protected void setMonthPeriod(Period period) {
        monthPeriod = period;
    }

    private void setMonthPeriod(YearPeriod period) {
        monthPeriod = period;
    }

    private void setMonthPeriod(MonthPeriod period) {
        monthPeriod = period;
    }

    private void setYearMonth() {
        yearMonth = monthPeriod.getMonths();
    }

    public void showInterface() throws IOException {
        System.out.println("w-increase period, s-degrees period, " +
                "d-show next, a-show previous, e-exit");
        handleCalendar();
    }

    public void setMonthsInRow(int columns) {
        this.columns = columns;
    }

    public void setMonthsInColumn(int rows) {
        this.rows = rows;
    }

    private void handleCalendar() throws IOException {
        boolean flag = true;
        while (flag) {
            ButtonCommands command = commandHandler.handle(buttonPress());
            if (command.equals(EXIT)) {
                flag = false;
                break;
            }
            printCalendar(performCalendar(performCommand(command)));
        }
    }

    private void printCalendar(String calendar) throws IOException {
        System.out.println(calendar);

    }

    protected String buttonPress() {
        String result;
        Scanner scanner = new Scanner(System.in);
        result = scanner.next();
        return result;
    }

    protected Period performCommand(ButtonCommands command) throws IOException {
        switch (command) {
            case MOVE_LEFT:
                return monthPeriod.previous();
            case MOVE_RIGHT:
                return monthPeriod.next();
            case MOVE_UP:
                return new YearPeriod(yearMonth.get(0));
            case MOVE_DOWN:
                return new MonthPeriod(yearMonth.get(0));
            default:
                return monthPeriod;
        }
    }

    protected String performCalendar(Period period) throws IOException {
        setMonthPeriod(period);
        setYearMonth();
        return chooseTableFormat(createCalendar());
    }

    protected String chooseTableFormat(List<String> calendar) {
        if (calendar.size() == 1) {
            return calendar.get(0);
        } else {
            return formatCalendarToTable(calendar);
        }
    }

    protected String formatCalendarToTable(List<String> calendar) {
        String result = "";
        for (int i = 0; i < rows; i++) {
            while (calendar.get(i * columns).length() > 2 ||
                    calendar.get(i * columns + 1).length() > 2 ||
                    calendar.get(i * columns + 2).length() > 2) {

                for (int j = 0; j < columns; j++) {
                    if (calendar.get(j + i * columns).length() > 2) {
                        int endLineIndex = calendar.get(j + i * columns).indexOf("\n");
                        result += calendar.get(j + i * columns).substring(0, endLineIndex) + "\t";
                        calendar.set(j + i * columns, calendar.get(j + i * columns)
                                .substring(endLineIndex + 1));
                    } else {
                        result += String.format("%35s", "") + "\t";
                    }
                }
                result += "\n";
            }
            result += "\n";
        }
        return result;
    }

    protected List<String> createCalendar() throws IOException {
        List<String> result = new ArrayList<>(12);
        for (YearMonth date :
                yearMonth) {
            result.add(calendar.generate(date));
        }
        return result;
    }

}
