package net.prestalife.nextvirtual

import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.TimeoutUtil
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import net.prestalife.nextvirtual.helpers.NextVirtualFile

class NextVirtualFileSearchContributor : ChooseByNameContributorEx {
    companion object {
        val filesMap = mutableMapOf<String, VirtualFile>()
        private val LOG = Logger.getInstance(
            NextVirtualFileSearchContributor::class.java
        )
    }

    override fun processNames(processor: Processor<in String>, scope: GlobalSearchScope, filter: IdFilter?) {
        val start = System.nanoTime()
        filesMap.clear()

        val names = mutableListOf<String>()

        val files = arrayOf(
            "page.tsx",
            "page.jsx",
            "route.ts",
            "route.js",
            "page.ts",
            "page.js",

            "layout.tsx",
            "layout.jsx",
            "layout.ts",
            "layout.js",

            "+error.svelte",
        ).map { FilenameIndex.getVirtualFilesByName(it, scope) }.flatten()

        files.forEach {
            val name = NextVirtualFile.generateNameFromFile(it)
            if (name != null) {
                names.add(name)
                filesMap[name] = it
            }
        }

        names.forEach { if (!processor.process(it)) return@forEach }
        if (LOG.isDebugEnabled) {
            LOG.debug("All Next.js names retrieved:" + TimeoutUtil.getDurationMillis(start))
        }
    }

    override fun processElementsWithName(
        name: String, processor: Processor<in NavigationItem>, parameters: FindSymbolParameters
    ) {
        val files = filesMap.filter { it.key == name }.values

        NextVirtualFile.convertVirtualFilesToPsiFiles(parameters.project, files)
            .forEach { if (!processor.process(it)) return@forEach }
    }
}

