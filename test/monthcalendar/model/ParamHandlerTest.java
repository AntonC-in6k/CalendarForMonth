package monthcalendar.model;

/**
 * Created by employee on 7/12/16.
 */

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParamHandlerTest {

    @Test
    public void emptyParameterForMonth() {
        ParamHandler paramHandler=new ParamHandler(null);
        assertThat(paramHandler.getMonth(), is(LocalDate.now().getMonthValue()));
    }

    @Test
    public void wrongParameterForMonth() throws Exception {
        String[] parameter = new String[]{"-m=13","-y=2016","-o=html"};
        ParamHandler paramHandler=new ParamHandler(parameter);
        assertThat(paramHandler.getMonth(), is(LocalDate.now().getMonthValue()));
    }

    @Test
    public void parameterForMonth(){
        String[] parameter = new String[]{"-m=5","-y=2016","-o=html"};
        ParamHandler paramHandler=new ParamHandler(parameter);
        assertThat(paramHandler.getMonth(), is(5));
    }

    @Test
    public void emptyParameterForYear() throws Exception {
        String[] parameter = new String[]{};
        ParamHandler paramHandler=new ParamHandler(null);
        assertThat(paramHandler.getYear(), is(LocalDate.now().getYear()));
    }

    @Test
    public void wrongParameterForYear() throws Exception {
        String[] parameter = new String[]{"-m=8","-y=2200","-o=html"};
        ParamHandler paramHandler=new ParamHandler(parameter);
        assertThat(paramHandler.getYear(), is(LocalDate.now().getYear()));
    }

    @Test
    public void parameterForYear(){
        String[] parameter = new String[]{"-m=5","-y=2015","-o=html"};
        ParamHandler paramHandler=new ParamHandler(parameter);
        assertThat(paramHandler.getYear(), is(2015));
    }

    @Test
    public void parameterForFormat(){
        String[] parameter = new String[]{"-m=5", "-y=2016", "-o=html"};
        ParamHandler paramHandler=new ParamHandler(parameter);
        assertThat(paramHandler.getFormat(), is("html"));
    }
}
