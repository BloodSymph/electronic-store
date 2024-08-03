package com.company.order.util;

import lombok.experimental.UtilityClass;



@UtilityClass
public class OrderCodeGeneratorUtil {

    private final static Integer LIMIT = 10000;

    //todo: Make code generation better
    public static Integer generateOrderCode() {
        return (int) (Math.random() * LIMIT);
    }

}
