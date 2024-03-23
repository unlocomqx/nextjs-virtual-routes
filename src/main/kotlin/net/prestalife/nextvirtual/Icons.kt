package net.prestalife.nextvirtual

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


// I couldn't generate this using gradle!

object Icons {
    private fun load(path: String): Icon {
        return IconLoader.getIcon(
            path,
            Icons.javaClass.classLoader
        )
    }

    val Svelte = load("icons/svelte.svg")

    val Page = load("icons/page.svg")
    val Server = load("icons/server.svg")
    val PageTS = load("icons/page-ts.svg")
    val PageJS = load("icons/page-js.svg")

    val Layout = load("icons/layout.svg")
    val Error = load("icons/error.svg")
}