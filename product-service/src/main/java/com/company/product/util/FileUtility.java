package com.company.product.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@UtilityClass
public class FileUtility {

    public static String encodeFile(String filePath) throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(filePath)));
    }

    public static String decodeFile(String randomName, String encodedFile) throws IOException {
       return Files.write(Paths.get(randomName.concat(".jpg")), Base64.getDecoder().decode(encodedFile)).getFileName().toString();
    }

    public static void writeFile(String randomName, String fileRoutes, String encoded) throws IOException {
        Files.write(Paths.get(fileRoutes.concat(randomName).concat(".jpg")), Base64.getDecoder().decode(encoded));
    }

}
