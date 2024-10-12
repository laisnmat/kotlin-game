package com.ensimag.component.Player

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.ActionConfig
import com.ensimag.items.Actions

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

    fun hasPotion(): Boolean {
        return (actions.find { it.action == Actions.POTIONS }?.quantity ?: 0) > 0
    }

    fun nextAction() {
        var index = actions.indexOfFirst { it.isSelected }
        actions[index].unSelect()
        while (actions[(index + 1) % actions.size].isDisabled) {
            index++
        }
        actions[(index + 1) % actions.size].select()
    }

    fun previousAction() {
        var index = actions.indexOfFirst { it.isSelected }
        actions[index].unSelect()
        while (actions[(index - 1 + actions.size) % actions.size].isDisabled) {
            index--
        }
        actions[(index - 1 + actions.size) % actions.size].select()
    }


}