package com.company.product.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.RandomStringUtils;

@UtilityClass
public class RandomFileNameGenerator {

    private static final int LIMIT = 10;

    private static final boolean USE_LETTERS = true;

    private static final boolean USE_NUMBERS = false;


    public static String randomFileNameGenerator() {
        return RandomStringUtils.random(LIMIT, USE_LETTERS, USE_NUMBERS);
    }

}
