package com.ensimag.ui

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.EntityType
import com.ensimag.component.Player.InventoryComponent
import com.ensimag.logInfo
import javafx.geometry.Point2D
import javafx.scene.Group
import javafx.scene.Parent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import kotlin.math.min


class PlayerInventoryView : Parent() {
    val player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
    val inventory = player.getComponent(InventoryComponent::class.java)

    // constants
    private val title = "Inventory"
    private val WIDTH = 350.0
    private val HEIGHT = 300
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
        text.textAlignment = TextAlignment.CENTER
        text.fill = Color.web(textColor)

        children.add(text)
        for (j in 0 until nbLines) {
            for (i in 0 until nbItemsPerLine) {
                logInfo("i = $i, j = $j")
                val item = inventory.getItems()[j * nbItemsPerLine + i              ]
                children.add(item.initView(
                    position = Point2D(itemCenter + GAP + i * (itemSize + GAP), GAP + j * (itemSize + GAP) + fontSize*1.5),
                    size = itemSize,
                    gap = GAP,
                    borderWidth = itemBorderWidth,
                    borderColor = Color.web(itemBorderColor),
                    backGroundColor = Color.web(itemColor)
                ))


            }
        }


    }


    fun update(tpf: Double) {

    }
}





