package com.kmpshowcase.buildlogic

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByName
import org.gradle.plugin.use.PluginDependency

internal val Project.libs: LibrariesForLibs
    get() = extensions.getByName<LibrariesForLibs>("libs")

fun plugin(provider: Provider<PluginDependency>) = with(provider.get()) {
    "$pluginId:$pluginId.gradle.plugin:$version"
}
