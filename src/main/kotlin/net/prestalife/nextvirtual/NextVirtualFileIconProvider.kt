package net.prestalife.nextvirtual

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import net.prestalife.nextvirtual.helpers.NextVirtualFile
import net.prestalife.nextvirtual.settings.AppSettingsState
import javax.swing.Icon

class NextVirtualFileIconProvider : FileIconProvider {
    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
        val settings = AppSettingsState.instance
        if (!settings.modifyFileIcons) {
            return null
        }

        return NextVirtualFile.generateIcon(file.name)
    }
}
