package com.illuzor.afo.utils

import com.illuzor.afo.utils.ModulesUtil.getModulesFromString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModulesUtilTest {

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
