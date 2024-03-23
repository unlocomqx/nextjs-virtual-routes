package net.prestalife.nextvirtual

import com.intellij.ide.projectView.ProjectViewNestingRulesProvider
import net.prestalife.nextvirtual.settings.AppSettingsState

class NextVirtualProjectViewNestingRulesProvider : ProjectViewNestingRulesProvider {
    override fun addFileNestingRules(consumer: ProjectViewNestingRulesProvider.Consumer) {
        val settings = AppSettingsState.instance
        if (!settings.nestRouteFiles) {
            return
        }
        consumer.addNestingRule("page.tsx", "page.ts")
        consumer.addNestingRule("page.jsx", "page.js")

        consumer.addNestingRule("layout.tsx", "layout.ts")
        consumer.addNestingRule("layout.jsx", "layout.js")
    }

}
