package com.company.product.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.RandomStringUtils;

@UtilityClass
public class FileRandomizerGeneratorNameUtility {

    private static final int LIMIT = 15;

    private static final boolean USE_LETTERS = true;

    private static final boolean USE_NUMBERS = true;


    public static String generateRandomFileName() {
        return RandomStringUtils.random(LIMIT, USE_LETTERS, USE_NUMBERS);
    }

}
