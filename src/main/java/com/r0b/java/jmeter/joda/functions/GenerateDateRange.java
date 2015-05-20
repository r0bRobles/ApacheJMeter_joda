package com.r0b.java.jmeter.joda.functions;

import org.apache.commons.collections.CollectionUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by r0bRobles on 5/18/2015.
 */
public class GenerateDateRange extends AbstractFunction {

    private static final Logger LOG = LoggingManager.getLoggerForClass();

    private static final String KEY = "__GenerateDateRange";
    private static final int MIN_PARAM_COUNT = 3;
    private static final int MAX_PARAM_COUNT = 10;
    private static final int FROM_DATE_PARAM = 0;
    private static final int TO_DATE_PARAM = 1;
    private static final int PREFIX_PARAM = 2;
    private static final int SKIP_DAY_OF_WEEK_PARAM = 3;

    private final List<String> values = new ArrayList<String>(1);



    private enum DayOfWeek{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;
        public static DayOfWeek fromName(String name) {
            return DayOfWeek.valueOf(name);
        }
    }


    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        LocalDate fromDate = new LocalDate(values.get(FROM_DATE_PARAM));
        final LocalDate toDate = new LocalDate(values.get(TO_DATE_PARAM));
        final String prefix = values.get(PREFIX_PARAM);
        final List<DayOfWeek> daysOfWeekToSkip = new ArrayList<DayOfWeek>();

        if (values.size() > 3) {
            for (String dayName : values.subList(SKIP_DAY_OF_WEEK_PARAM,values.size())) {
                daysOfWeekToSkip.add(DayOfWeek.fromName(dayName.trim()));
            }
        }
        int counter = 0;
        while (fromDate.isBefore(toDate)) {
            if (!daysOfWeekToSkip.contains(DayOfWeek.fromName(fromDate.dayOfWeek().getAsText().toUpperCase()))) {
                getVariables().put(prefix + counter,fromDate.toString());
                counter++;
            }
            fromDate = fromDate.plusDays(1);
        }

        return "true";
    }

    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection, MIN_PARAM_COUNT, MAX_PARAM_COUNT);
        if (CollectionUtils.isNotEmpty(collection)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Received %s parameters",collection.size()));
            }
            for (CompoundVariable compoundVariable : collection) {
                values.add(compoundVariable.execute());
            }
        }
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return Arrays.asList(
                "From Date: string format (yyyy-MM-dd)",
                "To Date: string format (yyyy-MM-dd)",
                "Prefix for the variables name",
                "Comma separated list of days of the week to skip (MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)");
    }
}
