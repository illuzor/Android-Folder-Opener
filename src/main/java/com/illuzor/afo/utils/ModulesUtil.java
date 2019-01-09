package com.illuzor.afo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModulesUtil {

    static String readFileToString(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        Files.lines(file.toPath(), StandardCharsets.UTF_8).forEach(builder::append);
        return builder.toString().trim();
    }

    static List<String> getModulesFromString(String string) {
        List<String> modules = new ArrayList<>();
        Matcher matcher = Pattern.compile("['\"]:(.+?)['\"]").matcher(string);

        while (matcher.find()) {
            modules.add(matcher.group(1));
        }

        return modules;
    }

    public static List<String> getModulesList(String basePath) throws IOException {
        String path = basePath + "/settings.gradle";
        File file = new File(path);
        if (!file.exists()) {
            file = new File(path + ".kts");
        }
        return getModulesFromString(readFileToString(file));
    }
}
