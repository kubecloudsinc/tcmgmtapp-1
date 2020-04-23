package com.onecloud.tcmgmt.semantic.utils;

import com.onecloud.tcmgmt.domain.appdb.TestStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class SortByStepOrder implements Comparator<TestStep> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public int compare(TestStep o1, TestStep o2) {
        return o1.getTestStepOrder().intValue()-o2.getTestStepOrder().intValue();
    }
}
