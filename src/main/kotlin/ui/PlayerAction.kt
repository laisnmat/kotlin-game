package com.ensimag.ui

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.EntityType
import com.ensimag.component.ActionsComponent
import com.ensimag.logInfo
import javafx.scene.Parent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import kotlin.math.min


class PlayerActionView : Parent() {

    val player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
    val actions = player.getComponent(ActionsComponent::class.java).getActions()

    // constants
    private val GAP = 1
    private val nbItem = 5
    private val HEIGHT = 50.0
    private val WIDTH = nbItem * HEIGHT + (nbItem + 1) * GAP

    // colors and sizes

    private val itemColor = "#262626"
    private val itemBorderColor = "#979797"
    private val itemBorderWidth = 1.0


    // computed values
    private val itemWidth = (WIDTH - (nbItem + 2) * GAP) / nbItem
    private val itemHeight = HEIGHT
    private var selectedItem: Rectangle? = null



    private val itemSize = min(itemWidth, itemHeight)

    init{
        layoutX = FXGL.getAppWidth() / 2.0 - WIDTH / 2.0
        layoutY = FXGL.getAppHeight() - HEIGHT
        for (i in 0 until nbItem){
            val item = Rectangle(itemSize, itemSize)
            item.translateX = i * (itemSize + GAP) + GAP
            item.fill = Color.web(itemColor)
            item.opacity = 0.6
            item.stroke = Color.web(itemBorderColor)
            item.strokeWidth = itemBorderWidth
            item.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED) {
                if (selectedItem != null) {
                    selectedItem!!.fill = Color.web(itemColor)
                    selectedItem!!.stroke = Color.web(itemBorderColor)
                    selectedItem!!.strokeWidth = itemBorderWidth
                }
                item.stroke = Color.RED
                item.strokeWidth = itemBorderWidth + 2
                selectedItem = item
                logInfo("${actions[i].name} selected")
            }

            val itemImage = actions[i].image
            itemImage.translateX = i * (itemSize + GAP) + GAP + itemSize*0.1
            itemImage.translateY = itemSize*0.1
            itemImage.fitHeight = 0.8 * itemSize
            itemImage.fitWidth = 0.8 * itemSize


            itemImage.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED) {
                if (selectedItem != null) {
                    selectedItem!!.fill = Color.web(itemColor)
                    selectedItem!!.stroke = Color.web(itemBorderColor)
                    selectedItem!!.strokeWidth = itemBorderWidth
                }
                item.stroke = Color.RED
                item.strokeWidth = itemBorderWidth + 2
                selectedItem = item
                logInfo("${actions[i].name} selected")
            }

            children.add(item)
            children.add(itemImage)

        }



    }
}





