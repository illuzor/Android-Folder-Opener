package com.illuzor.afo.prefs

import com.intellij.ide.util.PropertiesComponent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal fun stringPref(
    props: PropertiesComponent,
    defaultValue: String,
): ReadWriteProperty<Any, String> = StringPrefDelegate(props, defaultValue)

private class StringPrefDelegate(
    private val props: PropertiesComponent,
    private val defaultValue: String,
) : ReadWriteProperty<Any, String> {
    override fun getValue(
        thisRef: Any,
        property: KProperty<*>,
    ): String = props.getValue(property.name, defaultValue)

    override fun setValue(
        thisRef: Any,
        property: KProperty<*>,
        value: String,
    ) = props.setValue(property.name, value)
}
