package monthcalendar.view;

/**
 * Created by employee on 7/14/16.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HtmlCalendarForMonth extends CalendarForMonth {
    public static final String STYLE =
            "\ttd{\n" +
                    "\tpadding:5 px;\n" +
                    "\tmin-width: 30px;\n" +
                    "\t}\n" +
                    "\t.current-day{\n" +
                    "\tcolor: #7CFC00;\n" +
                    "\t}\n" +
                    "\t.weekend-day{\n" +
                    "\tcolor: #FF8C00;\n" +
                    "\t}\n";

    public static final String CONNECT_CSS =
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"\n>";

    public static final String HEADER =
            "<html>\n" +
                    "<head> " +
                    CONNECT_CSS +
                    "<title>CalendarForMonth for month</title>\n" +
                    "</head>\n" +
                    "<body>\n";
    public static final String FOOTER =
            "</body>\n" +
                    "</html>";

    public HtmlCalendarForMonth() {
        super();
    }

    @Override
    protected String formatMonthTitle() {
        return "<table>\n" +
                "<tr>\n" +
                "<td>" + createMonthTitle() + "</td>\n" +
                "</tr>\n" +
                "</table>\n";
    }


    @Override
    protected String formatDayTitle(String dayName) {
        String result = "<td";
        if (chooseDayTitle(dayName) == "weekend") {
            result += " class=\"weekend-day\"";
        }
        result += "> " + dayName + " </td>";
        return result;
    }

    @Override
    protected String formatDayTitleLine(String dayTitles) {
        return "<table>\n" +
                "<tr>\n" +
                dayTitles +
                "</tr>\n" +
                "</table>";
    }

    @Override
    protected String formatDay(LocalDate day) {
        String result = "";
        if (chooseColorForDayPrinting(day) == "weekday") {
            result += "<td>";
        }
        if (chooseColorForDayPrinting(day) == "weekend") {
            result += "<td class=\"weekend-day\">";
        }
        if (chooseColorForDayPrinting(day) == "current") {
            result += "<td class=\"current-day\">";
        }
        result += day.getDayOfMonth() + " </td>";

        if (gotoNewLineInTable(day)) {
            result += "\n</tr>\n<tr>";
        }
        return result;
    }

    @Override
    protected String printEmptySpace() {
        return "<td> </td>";
    }

    @Override
    protected String additionalForMonthDays(String tableDays) {
        return "<table>\n" +
                "<tr>\n" +
                tableDays +
                "</tr>\n" +
                "</table>";
    }

    @Override
    public void printCalendar(List<LocalDate> monthDays, LocalDate date) throws IOException {
        baseInitialization(monthDays, date);
        writeCalendarInfile();
        writeStyleSheetInFile();
    }

    private void writeCalendarInfile() throws IOException {
        FileWriter f = new FileWriter("index.html", false);
        f.append(HEADER +
                formatMonthTitle() +
                makeTitle() +
                monthDaysToString() +
                FOOTER);
        f.close();
    }

    private void writeStyleSheetInFile() throws IOException {
        FileWriter f = new FileWriter("style.css", false);
        f.append(STYLE);
        f.close();
    }

}
