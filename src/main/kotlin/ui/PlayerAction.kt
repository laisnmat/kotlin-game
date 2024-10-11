package com.ensimag.ui

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.getWorldProperties
import com.ensimag.EntityType
import com.ensimag.component.ActionsComponent
import com.ensimag.items.Actions
import com.ensimag.items.Items
import com.ensimag.logInfo
import javafx.scene.Group
import javafx.scene.Parent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import kotlin.math.min


class PlayerActionView : Parent() {

    val player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
    val actions = player.getComponent(ActionsComponent::class.java).getActions()
    val actionsComponent = player.getComponent(ActionsComponent::class.java)

    // constants
    private val GAP = 1
    private val nbItem = actions.size
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


            val itemBg = Rectangle(itemSize, itemSize)
            itemBg.translateX = i * (itemSize + GAP) + GAP
            itemBg.fill = Color.web(itemColor)
            itemBg.opacity = 0.6
            itemBg.stroke = Color.web(itemBorderColor)
            itemBg.strokeWidth = itemBorderWidth

            if(i == 0){
                itemBg.stroke = Color.RED
                itemBg.strokeWidth = itemBorderWidth + 2
                selectedItem = itemBg
            }

            val itemTexture = actions[i].image
            itemTexture.translateX = i * (itemSize + GAP) + GAP + itemSize*0.1
            itemTexture.translateY = itemSize*0.1
            itemTexture.fitHeight = 0.8 * itemSize
            itemTexture.fitWidth = 0.8 * itemSize






            val node : Group = Group()
            node.children.add(itemBg)
            node.children.add(itemTexture)

            if (actions[i] == Actions.FOOD || actions[i] == Actions.POTIONS){
                val nb = if (actions[i] == Actions.FOOD) actionsComponent.nbFood else actionsComponent.nbPotions
                val text = Text("$nb")
                text.x = itemBg.x + itemBg.translateX + GAP
                text.y = itemBg.y + itemSize - 3* GAP
                text.wrappingWidth = itemSize - GAP*2
                text.font = Font.font(10.0)
                text.fill = Color.web("white")
                text.textAlignment = javafx.scene.text.TextAlignment.RIGHT
                node.children.add(text)
            }


            if((actions[i] == Actions.FOOD_DISABLED || actions[i] == Actions.POTIONS_DISABLED || actions[i] == Actions.AXE_DISABLED).not()) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED) {

                    if (selectedItem != null) {
                        selectedItem!!.fill = Color.web(itemColor)
                        selectedItem!!.stroke = Color.web(itemBorderColor)
                        selectedItem!!.strokeWidth = itemBorderWidth
                    }
                    itemBg.stroke = Color.RED
                    itemBg.strokeWidth = itemBorderWidth + 2
                    selectedItem = itemBg
                    logInfo("${actions[i].name} selected")
                    getWorldProperties().setValue("selectedItem", actions[i])

                }
            }

            children.add(node)

        }



    }
}





