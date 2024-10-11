package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.Items

class InventoryComponent : Component() {
    private val inventory = HashMap<Items, Int>()

    init {
        inventory[Items.WOOD] = 0
        inventory[Items.NAIL] = 0
        inventory[Items.STRING] = 0
        inventory[Items.TOOLS] = 0
        inventory[Items.MAP] = 0
        inventory[Items.MANUAL] = 0
    }

    fun addItem(item: Items, quantity: Int) {
        if (inventory.containsKey(item)) {
            inventory[item] = inventory[item]!! + quantity
        } else {
            inventory[item] = quantity
        }
    }

    fun removeItem(item: Items, quantity: Int) {
        if (inventory.containsKey(item)) {
            if (inventory[item]!! - quantity <= 0) {
                inventory.remove(item)
            } else {
                inventory[item] = inventory[item]!! - quantity
            }
        }
    }

    fun getQuantity(item: Items): Int {
        return inventory[item] ?: 0
    }

    fun getExpectedQuantity(item: Items): Int {
        return item.goalValue
    }

    fun isItemComplete(item: Items): Boolean {
        return getQuantity(item) >= getExpectedQuantity(item)
    }

    fun getInventory(): Map<Items, Int> {
        return inventory
    }

    fun clearInventory() {
        inventory.clear()
    }

}