package com.ensimag.component.Player

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.ItemConfig
import com.ensimag.items.Items

class InventoryComponent : Component() {
    private val items = mutableListOf<ItemConfig>()

    init {
        items.add(ItemConfig(
            item = Items.WOOD,
            maxQuantity = 200,
            quantity = 198
        ))

        items.add(ItemConfig(
            item = Items.NAIL,
            maxQuantity = 100,
            quantity = 98
        ))

        items.add(ItemConfig(
            item = Items.STRING,
            maxQuantity = 20,
            quantity = 20
        ))
        items.add(ItemConfig(
            item = Items.TOOLS,
            maxQuantity = 1,
            quantity = 1
        ))
        items.add(ItemConfig(
            item = Items.MAP,
            maxQuantity = 1,
            quantity = 1
        ))

        items.add(ItemConfig(
            item = Items.MANUAL,
            maxQuantity = 1,
            quantity = 1
        ))

    }

    fun getItems(): List<ItemConfig> {
        return items
    }

    fun increaseQuantity(item: Items, amout: Int = 1){
        items.find { it.item == item }?.incrementQuantity(amout)
    }

    fun decreaseQuantity(item: Items) {
        items.find { it.item == item }?.decrementQuantity()
    }

    fun isFull(): Boolean {
        return items.all { it.isFull() }
    }



}