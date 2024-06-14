package com.company.product.util;

import lombok.experimental.UtilityClass;
import org.hibernate.validator.constraints.URL;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@UtilityClass
public class FileUtility {

    public static String encodeFile(String filePath) throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(filePath)));
    }

    public static  String decodeAndWriteFile(String encodedFile, String filePath) throws IOException {
        Files.write(Paths.get(filePath), Base64.getDecoder().decode(encodedFile));
        return encodedFile;
    }

}
