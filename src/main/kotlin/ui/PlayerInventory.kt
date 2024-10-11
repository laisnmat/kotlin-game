package com.ensimag.ui

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.random
import com.ensimag.EntityType
import com.ensimag.component.ActionsComponent
import com.ensimag.component.InventoryComponent
import com.ensimag.logInfo
import javafx.scene.Parent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import kotlin.math.log
import kotlin.math.min


class PlayerInventoryView : Parent() {
    val player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
    val inventory = player.getComponent(InventoryComponent::class.java).getInventory()

    // constants
    private val title = "Inventory"
    private val WIDTH = 250.0
    private val HEIGHT = 200.0
    private val GAP = 5.0
    private val fontSize = 25.0
    private val nbItemsPerLine = 3
    private val nbLines = 2

    // colors and sizes
    private val bgColor = "black"
    private val bgBorderColor = "#2E2E2E"
    private val bgBorderWidth = 3.0

    private val itemColor = "#262626"
    private val itemBorderColor = "#979797"
    private val itemBorderWidth = 1.0

    private val textColor = "white"

    // computed values
    private val itemWidth = (WIDTH - (nbItemsPerLine + 2) * GAP) / nbItemsPerLine
    private val itemHeight = (HEIGHT - (nbLines + 4) * GAP - (fontSize*1.5)) / nbLines


    private val itemSize = min(itemWidth, itemHeight)
    private var itemCenter = (WIDTH - (itemSize + GAP) * nbItemsPerLine - 2* GAP) / 2.0


    var time = 0.0

    init{

        val background = Rectangle(WIDTH - 3.0, HEIGHT - 3.0)
        background.arcWidth = 20.0
        background.arcHeight = 20.0
        background.fill = Color.web(bgColor)
        background.opacity = 0.6
        background.stroke = Color.web(bgBorderColor)
        background.strokeWidth = bgBorderWidth
        children.add(background)

        val text = Text(title)
        text.y = fontSize
        text.wrappingWidth = WIDTH
        text.font = Font.font(fontSize)
        text.textAlignment = javafx.scene.text.TextAlignment.CENTER
        text.fill = Color.web(textColor)

        children.add(text)
        var i = 0
        var j = 0
        for (i in 0 until nbItemsPerLine) {
            for (j in 0 until nbLines) {
                val currentItem = inventory.keys.elementAt(i + j * nbItemsPerLine)
                val n = currentItem.goalValue
                val m = inventory[currentItem]!!


                val item = Rectangle(itemSize, itemSize)
                item.arcWidth = 10.0
                item.arcHeight = 10.0
                item.opacity = 0.7
                item.strokeWidth = itemBorderWidth
                item.x = itemCenter + GAP + i * (itemSize + GAP)
                item.y = GAP + j * (itemSize + GAP) + fontSize*1.5

                val text = Text("$m/$n")
                text.x = item.x + GAP
                text.y = item.y + itemSize - 5
                text.wrappingWidth = itemSize - GAP*2
                text.font = Font.font(8.0)
                text.textAlignment = javafx.scene.text.TextAlignment.RIGHT
                val colorGreen = Color.web("#00FF00")
                val colorRed = Color.web("#FF0000")
                item.fill = Color.web(itemColor)


                if (m == n) {
                    text.fill = colorGreen
                    item.stroke = colorGreen
                } else {
                    text.fill = colorRed
                    item.stroke = colorRed

                }

                val itemImage = currentItem.image
                itemImage.translateX = item.x + itemSize*0.1
                itemImage.translateY = item.y + itemSize*0.1
                itemImage.fitHeight = 0.8 * itemSize
                itemImage.fitWidth = 0.8 * itemSize

                itemImage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED) {
                    logInfo("${currentItem.name} selected")
                }

                item.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED) {
                    logInfo("${currentItem.name} selected")
                }


                children.add(item)
                children.add(itemImage)
                children.add(text)

            }
        }


    }


    fun update(tpf: Double) {

    }
}





