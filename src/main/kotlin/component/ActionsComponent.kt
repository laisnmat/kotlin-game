package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.ActionConfig
import com.ensimag.items.Actions
import com.ensimag.items.Items

class ActionsComponent : Component() {
    private val actions = mutableListOf<ActionConfig>()

    init {
        actions.add(ActionConfig(
            action = Actions.HAND,
            isDefault = true,
        ))
        actions.add(ActionConfig(
            action = Actions.SWORD,
        ))
        actions.add(ActionConfig(
            action = Actions.AXE,
        ))
        actions.add(ActionConfig(
            action = Actions.FOOD,
            isDisabled = true,
            quantity = 0,
            maxQuantity = 5,
        ))
        actions.add(ActionConfig(
            action = Actions.POTIONS,
            isDisabled = true,
            quantity = 0,
            maxQuantity = 5,
        ))
    }

    fun getActions(): List<ActionConfig> {
        return actions
    }

    fun increaseQuantity(action: Actions) : Boolean?{
        return actions.find { it.action == action }?.incrementQuantity()
    }

    fun decreaseQuantity(action: Actions) {
        actions.find { it.action == action }?.decrementQuantity()
    }

    fun hasFood(): Boolean {
        return (actions.find { it.action == Actions.FOOD }?.quantity ?: 0) > 0
    }


}