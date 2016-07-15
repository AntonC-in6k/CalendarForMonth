package monthcalendar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Supplier;

/**
 * Created by employee on 7/14/16.
 */
public interface Calendar {

    String generate(LocalDate date) throws IOException;

    String generate(Supplier<LocalDate> dateSupplier) throws IOException;

}
