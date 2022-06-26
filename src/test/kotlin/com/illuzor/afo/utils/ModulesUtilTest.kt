package com.illuzor.afo.utils

import com.illuzor.afo.utils.ModulesUtil.getModulesFromString
import com.illuzor.afo.utils.ModulesUtil.readFileToString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files

class ModulesUtilTest {

    @Test
    @Throws(IOException::class)
    fun readFileToString_test() {
        val testString = "some string for test"
        val stringFile = File("test.file")
        Files.write(stringFile.toPath(), testString.toByteArray(Charset.forName("UTF-8")))
        assertEquals(testString, readFileToString(stringFile))
        stringFile.delete()
    }

    @Test
    fun readFileToString_noFile_test() {
        assertThrows(IOException::class.java) { readFileToString(File("test1.file")) }
    }

    @Test
    fun modulesFromString_test (){
        val modulesString = "include ':app' ':storage' ':net' ':modules:module' ':modules:submodules:module'"
        val wrongString = "sdfs ser3 r3 sefkselrk"
        val modulesList: MutableList<String> = ArrayList(3)
        modulesList.add("app")
        modulesList.add("storage")
        modulesList.add("net")
        modulesList.add("modules/module")
        modulesList.add("modules/submodules/module")
        assertEquals(modulesList, getModulesFromString(modulesString))
        assertEquals(ArrayList<String>(0), getModulesFromString(""))
        assertEquals(ArrayList<String>(0), getModulesFromString(wrongString))
    }
}