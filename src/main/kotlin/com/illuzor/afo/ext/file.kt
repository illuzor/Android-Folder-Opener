package com.illuzor.afo.ext

import java.io.File

internal fun File.notExists(): Boolean = !this.exists()
