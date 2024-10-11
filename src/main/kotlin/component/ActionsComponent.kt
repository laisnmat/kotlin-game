package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.Actions
import com.ensimag.items.Items

class ActionsComponent : Component() {
    private val actions = mutableListOf<Actions>()

    init {
        actions.add(Actions.HAND)
        actions.add(Actions.SWORD)
        actions.add(Actions.AXE)
        actions.add(Actions.FOOD)
        actions.add(Actions.POTIONS)

    }

    fun addAction(action: Actions) {
        actions.add(action)
    }

    fun removeAction(action: Actions) {
        actions.remove(action)
    }

    fun getActions(): List<Actions> {
        return actions
    }

    fun clearActions() {
        actions.clear()
    }

}