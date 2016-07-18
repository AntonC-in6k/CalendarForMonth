package monthcalendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Supplier;

/**
 * Created by employee on 7/14/16.
 */
public interface Calendar {

    String generate(YearMonth yearMonth) throws IOException;

//    String generate(Supplier<LocalDate> dateSupplier) throws IOException;

}
