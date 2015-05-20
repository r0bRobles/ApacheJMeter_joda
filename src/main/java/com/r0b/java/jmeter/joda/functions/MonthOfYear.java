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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by r0bRobles on 5/18/2015.
 */
public class MonthOfYear extends AbstractFunction {

    private static final Logger LOG = LoggingManager.getLoggerForClass();

    private static final String KEY = "__MonthOfYear";
    private static final int MIN_PARAM_COUNT = 0;
    private static final int MAX_PARAM_COUNT = 1;
    private static final int DATE_PARAM = 0;

    private final List<String> values = new ArrayList<String>(1);


    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        final LocalDate date = new LocalDate(values.get(DATE_PARAM));
        return date.monthOfYear().getAsText().toUpperCase();
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
        return Collections.singletonList("Date string format (yyyy-MM-dd)");
    }
}
