package monthcalendar;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonthPeriod {
    public final static  Integer NUMBER_OF_MONTHS_IN_YEAR=12;
    private List<YearMonth> yearMonths;
    private List<Integer> step;
    private int currentStepIndex;

    public MonthPeriod(YearMonth yearMonth) {
        yearMonths = Arrays.asList(yearMonth);
        step = Arrays.asList(1, NUMBER_OF_MONTHS_IN_YEAR);
        currentStepIndex = 0;
    }

    public MonthPeriod(List<YearMonth> yearMonths, int currentStepIndex) {
        this.yearMonths = yearMonths;
        step = Arrays.asList(1, NUMBER_OF_MONTHS_IN_YEAR);
        this.currentStepIndex = currentStepIndex;
    }

    public MonthPeriod next() {
        if (step.get(currentStepIndex) < 0) {
            step.set(currentStepIndex, step.get(currentStepIndex) * (-1));
        }
        return new MonthPeriod(changePeriod(step.get(currentStepIndex)), currentStepIndex);
    }

    public MonthPeriod previous() {
        if (step.get(currentStepIndex) > 0) {
            step.set(currentStepIndex, step.get(currentStepIndex) * (-1));
        }
        return new MonthPeriod(changePeriod(step.get(currentStepIndex)), currentStepIndex);
    }

    public List<YearMonth> getMonths() {
        return yearMonths;
    }

    public void increase() {
        if (currentStepIndex != step.size() - 1)
            currentStepIndex++;
    }

    public void degrees() {
        if (currentStepIndex != 0)
            currentStepIndex--;
    }

    private List<YearMonth> changePeriod(int step) {
        List<YearMonth> result = new ArrayList<>(Math.abs(step));
        if (Math.abs(step) == NUMBER_OF_MONTHS_IN_YEAR) {
            result.addAll(getYear(yearMonths.get(0).plusYears(1).getYear()));
            return result;
        }
        result.addAll(yearMonths);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).plusMonths(step));
        }
        return result;
    }

    private List<YearMonth> getYear(int year) {
        List<YearMonth> result = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_MONTHS_IN_YEAR; i++) {
            result.add(YearMonth.of(year, i));
        }
        return result;
    }
}
