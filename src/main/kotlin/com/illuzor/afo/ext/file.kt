package com.illuzor.afo.ext

import java.io.File

fun File.notExists(): Boolean = !this.exists()
