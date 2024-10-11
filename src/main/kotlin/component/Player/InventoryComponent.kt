package com.ensimag.component.Player

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.inventory.Inventory
import com.almasb.fxgl.inventory.ItemConfig
import com.ensimag.items.Items
import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text

class InventoryComponent : Component() {
    private val inventory = Inventory<Items>(100)
    public val items = listOf(
        Items.WOOD,
        Items.NAIL,
        Items.STRING,
        Items.TOOLS,
        Items.MAP,
        Items.MANUAL
    )

    init {
        items.forEach { addItem(it) }
    }

    private fun addItem(item: Items) {
        inventory.add(item, ItemConfig(
            item.description,
            item.description,
            item.goalValue,
        ), quantity = 1)
    }

    fun removeItem(item: Items) {
        inventory.remove(item)
    }

    fun getInventory(): Inventory<Items> {
        return inventory
    }

    fun add(item: Items) {
        inventory.add(item)
        if(item == Items.WOOD) {
            val m = inventory.getItemQuantity(Items.WOOD)
            val n = inventory.getData(Items.WOOD).get().maxStackQuantity

            val text = (inventory.getData(Items.WOOD).get().view as Group).children[2] as Text

            text.text = "$m/$n"
            val colorGreen = Color.web("#00FF00")
            val colorRed = Color.web("#FF0000")
            if (m >= n) {
                text.fill = colorGreen
                ((inventory.getData(Items.WOOD).get().view as Group).children[0] as Rectangle).stroke = colorGreen
            } else {
                text.fill = colorRed
                ((inventory.getData(Items.WOOD).get().view as Group).children[0] as Rectangle).stroke = colorRed
            }
        }
    }

}

