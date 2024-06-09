package com.illuzor.afo.ext

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File

internal class FileTest {
    companion object {
        private val testFolder = File("test_files")

        @BeforeAll
        @JvmStatic
        fun createTestFolder() {
            if (testFolder.exists()) {
                testFolder.delete()
            }
            testFolder.mkdir()
        }

        @JvmStatic
        @AfterAll
        fun deleteTestFolder() {
            testFolder.delete()
        }
    }

    @AfterEach
    fun cleanupTestFolder() {
        testFolder.listFiles()?.forEach(File::delete)
    }

    @Test
    fun `isGradleModule for not existed file - false`() {
        val file = testFolder.resolve("ghost")

        assertFalse(file.isGradleModule)
    }

    @Test
    fun `isGradleModule for empty folder - false`() {
        val folder = testFolder.resolve("empty")
        folder.mkdir()

        assertTrue(folder.exists())
        assertFalse(testFolder.isGradleModule)
    }

    @Test
    fun `isGradleModule for file - false`() {
        val file = testFolder.resolve("text.txt")
        file.writeText("hello")

        assertTrue(file.exists())
        assertFalse(testFolder.isGradleModule)
    }

    @Test
    fun `isGradleModule for folder with build_gradle folder - false`() {
        val file = testFolder.resolve("build.gradle")
        file.mkdir()

        assertTrue(file.exists())
        assertFalse(testFolder.isGradleModule)
    }

    @Test
    fun `isGradleModule for folder with build_gradle_kts folder - false`() {
        val file = testFolder.resolve("build.gradle.kts")
        file.mkdir()

        assertTrue(file.exists())
        assertFalse(testFolder.isGradleModule)
    }

    @Test
    fun `isGradleModule for folder with build_gradle file - true`() {
        val file = testFolder.resolve("build.gradle")
        file.writeText("hello")

        assertTrue(file.exists())
        assertTrue(testFolder.isGradleModule)
    }

    @Test
    fun `isGradleModule for folder with build_gradle_kts file - true`() {
        val file = testFolder.resolve("build.gradle.kts")
        file.writeText("hello")

        assertTrue(file.exists())
        assertTrue(testFolder.isGradleModule)
    }

    @Test
    fun `gradleFile for not existed file - null`() {
        val file = testFolder.resolve("empty")

        assertNull(file.gradleFile)
    }

    @Test
    fun `gradleFile for empty folder - null`() {
        assertTrue(testFolder.listFiles().isNullOrEmpty())
        assertNull(testFolder.gradleFile)
    }

    @Test
    fun `gradleFile for file - null`() {
        val file = testFolder.resolve("empty")
        file.writeText("hello")

        assertTrue(file.exists())
        assertNull(file.gradleFile)
    }

    @Test
    fun `gradleFile for folder with build_gradle folder - null`() {
        val file = testFolder.resolve("build.gradle")
        file.mkdir()

        assertTrue(file.exists())
        assertNull(testFolder.gradleFile)
    }

    @Test
    fun `gradleFile for folder with build_gradle_kts folder - null`() {
        val file = testFolder.resolve("build.gradle.kts")
        file.mkdir()

        assertTrue(file.exists())
        assertNull(testFolder.gradleFile)
    }

    @Test
    fun `gradleFile for folder with build_gradle file - not null`() {
        val file = testFolder.resolve("build.gradle")
        file.writeText("hello")

        assertTrue(file.exists())
        assertEquals(file, testFolder.gradleFile)
    }

    @Test
    fun `gradleFile for folder with build_gradle_kts file - not null`() {
        val file = testFolder.resolve("build.gradle.kts")
        file.writeText("hello")

        assertTrue(file.exists())
        assertEquals(file, testFolder.gradleFile)
    }

    @Test
    fun `settingsGradleFile for not existed file - null`() {
        val file = testFolder.resolve("empty")

        assertNull(file.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for empty folder - null`() {
        assertTrue(testFolder.listFiles().isNullOrEmpty())
        assertNull(testFolder.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for file - null`() {
        val file = testFolder.resolve("empty")
        file.writeText("hello")

        assertTrue(file.exists())
        assertNull(file.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for folder with build_gradle folder - null`() {
        val file = testFolder.resolve("settings.gradle")
        file.mkdir()

        assertTrue(file.exists())
        assertNull(testFolder.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for folder with build_gradle_kts folder - null`() {
        val file = testFolder.resolve("settings.gradle.kts")
        file.mkdir()

        assertTrue(file.exists())
        assertNull(testFolder.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for folder with build_gradle file - not null`() {
        val file = testFolder.resolve("settings.gradle")
        file.writeText("hello")

        assertTrue(file.exists())
        assertEquals(file, testFolder.settingsGradleFile)
    }

    @Test
    fun `settingsGradleFile for folder with build_gradle_kts file - not null`() {
        val file = testFolder.resolve("settings.gradle.kts")
        file.writeText("hello")

        assertTrue(file.exists())
        assertEquals(file, testFolder.settingsGradleFile)
    }

    @Test
    fun `notExists true`() {
        val file = testFolder.resolve("test")

        assertTrue(file.notExists())
    }

    @Test
    fun `notExists false`() {
        val file = testFolder.resolve("test")
        file.mkdir()

        assertFalse(file.notExists())
    }
}
