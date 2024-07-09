package com.company.order.util;

import lombok.experimental.UtilityClass;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

@UtilityClass
public class OrderCodeGeneratorUtility {

    private static final Integer START = 0;

    private static final Integer END = 10000;

    public static Integer orderCodeGenerate() {
        return IntStream.range(START, END).iterator().nextInt();
    }

}
