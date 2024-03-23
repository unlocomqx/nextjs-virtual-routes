package net.prestalife.nextvirtual

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator
import net.prestalife.nextvirtual.helpers.NextVirtualFile
import net.prestalife.nextvirtual.settings.AppSettingsState

class NextVirtualProjectViewNodeDecorator : ProjectViewNodeDecorator {
    override fun decorate(node: ProjectViewNode<*>, presentation: PresentationData) {
        val settings = AppSettingsState.instance
        if (!settings.modifyProjectTree) {
            return
        }

        val newName = NextVirtualFile.generateNameFromFile(node.virtualFile!!)

        if (newName != null) {
            presentation.presentableText = newName
        }

        if (settings.modifyFileIcons) {
            val icon = NextVirtualFile.generateIcon(node.virtualFile!!.name)
            if (icon != null) {
                presentation.setIcon(icon)
            }
        }
    }
}
