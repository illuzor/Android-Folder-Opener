package com.illuzor.afo.utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ModulesUtilTest {

    @Test
    public void readFileToString_test() throws IOException {
        final String testString = "some string for test";
        File stringFile = new File("test.file");

        Files.write(stringFile.toPath(), testString.getBytes(Charset.forName("UTF-8")));
        Assertions.assertEquals(testString, ModulesUtil.readFileToString(stringFile));
        stringFile.delete();
    }

    @Test
    public void readFileToString_noFile_test() {
        Assertions.assertThrows(IOException.class, () -> ModulesUtil.readFileToString(new File("test1.file")));
    }

    @Test
    public void getModulesFromString_test() {
        String modulesString = "include ':app' ':storage' ':net' ':modules:module' ':modules:submodules:module'";
        String wrongString = "sdfs ser3 r3 sefkselrk";
        List<String> modulesList = new ArrayList<>(3);
        modulesList.add("app");
        modulesList.add("storage");
        modulesList.add("net");
        modulesList.add("modules/module");
        modulesList.add("modules/submodules/module");

        Assertions.assertEquals(modulesList, ModulesUtil.getModulesFromString(modulesString));
        Assertions.assertEquals(new ArrayList<String>(0), ModulesUtil.getModulesFromString(""));
        Assertions.assertEquals(new ArrayList<String>(0), ModulesUtil.getModulesFromString(wrongString));
    }
}
