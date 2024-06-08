package com.company.product.util;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class URLGeneratorUtility {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");

    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private static final Pattern EDGES_DASHES = Pattern.compile("(^-|-$)");

    private static final int LIMIT = 20;

    public static String toUrlAddress(String input) {

        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");

        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);

        String urlAddress = NON_LATIN.matcher(normalized).replaceAll("");

        urlAddress = EDGES_DASHES.matcher(urlAddress).replaceAll("");

        String urlAddressLengthLimit = urlAddress.length() > LIMIT ? urlAddress.substring(0, LIMIT) : urlAddress;

        return urlAddressLengthLimit.toLowerCase(Locale.ENGLISH);

    }

}
