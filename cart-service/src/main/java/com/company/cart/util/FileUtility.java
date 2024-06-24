package com.company.cart.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@UtilityClass
public class FileUtility {

    public static String encodeFile(String filePath) throws IOException {
        return Base64.getEncoder()
                .encodeToString(
                        Files.readAllBytes(
                                Paths.get(filePath)
                        )
                );
    }

}
