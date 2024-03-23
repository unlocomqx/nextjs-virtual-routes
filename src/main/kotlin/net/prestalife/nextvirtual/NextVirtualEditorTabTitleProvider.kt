package net.prestalife.nextvirtual

import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import net.prestalife.nextvirtual.helpers.NextVirtualFile
import net.prestalife.nextvirtual.settings.AppSettingsState

class NextVirtualEditorTabTitleProvider : EditorTabTitleProvider {
    override fun getEditorTabTitle(project: Project, file: VirtualFile): String? {
        val settings = AppSettingsState.instance
        if (!settings.modifyTabsTitles) {
            return null
        }

        return NextVirtualFile.generateNameFromFile(file)
    }
}
