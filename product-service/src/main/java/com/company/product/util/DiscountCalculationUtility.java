package com.company.product.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscountCalculationUtility {

    public static Double calculateDiscount(Double productPrice, Double discount) {
        Double calculation = productPrice * discount;
        return productPrice - calculation;
    }

}
