package com.illuzor.afo.ext

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments.of as arguments
import java.util.stream.Stream.of as stream

internal class StringTest {

    @ParameterizedTest
    @MethodSource("provideStrings")
    fun `extractModules test`(input: String, expected: List<String>) {
        assertEquals(expected, input.extractModules())
    }

    companion object {

        @JvmStatic
        fun provideStrings(): Stream<Arguments> =
            stream(
                arguments(
                    "",
                    emptyList<String>(),
                ),
                arguments(
                    """":module"""",
                    listOf("module"),
                ),
                arguments(
                    """':module'""",
                    listOf("module"),
                ),
                arguments(
                    """":module",
                        ":module:submodule"
                    """.trimIndent(),
                    listOf("module", "module/submodule"),
                ),
                arguments(
                    """
                      include(
                          ":app",
                          ":features",
                          ":domain",
                          ":presentation",
                          ":ui",
                          ":utils",)
                    """.trimIndent(),
                    listOf("app", "features", "domain", "presentation", "ui", "utils"),
                ),
                arguments(
                    """
                      include(
                          ":app",
                          ":features:feature1",
                          ":features:feature2",
                          ":features:feature2:feature3:feature4",
                          ":domain",
                          ":presentation",
                          ":ui",
                          ":utils",
                      )
                    """.trimIndent(),
                    listOf(
                        "app",
                        "features/feature1",
                        "features/feature2",
                        "features/feature2/feature3/feature4",
                        "domain",
                        "presentation",
                        "ui",
                        "utils",
                    ),
                ),
            )
    }
}
