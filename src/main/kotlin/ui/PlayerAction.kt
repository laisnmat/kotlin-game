package com.ensimag.ui

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.EntityType
import com.ensimag.entity.PlayerEntity
import javafx.geometry.Point2D
import javafx.scene.Parent
import javafx.scene.paint.Color

import kotlin.math.min


class PlayerActionView : Parent() {
    private val actions = (FXGL.getGameWorld().getSingleton(EntityType.PLAYER) as PlayerEntity).actionsComponent.getActions()

    // constants
    private val GAP = 1
    private val nbItem = actions.size
    private val HEIGHT = 100.0
    private val WIDTH = nbItem * HEIGHT + (nbItem + 1) * GAP

    // colors and sizes
    private val itemColor = "#262626"
    private val itemBorderColor = "#979797"
    private val itemBorderWidth = 1.0
    
    // computed values
    private val itemWidth = (WIDTH - (nbItem + 2) * GAP) / nbItem
    private val itemHeight = HEIGHT
    
    private val itemSize = min(itemWidth, itemHeight)

    init{
        layoutX = FXGL.getAppWidth() / 2.0 - WIDTH / 2.0
        layoutY = FXGL.getAppHeight() - HEIGHT
        for((i, action) in actions.withIndex()){
            children.add(action.initView(
                position = Point2D(i * (itemSize + GAP) + GAP, 0.0),
                size = itemSize,
                gap = GAP.toDouble(),
                borderWidth = itemBorderWidth,
                borderColor = Color.web(itemBorderColor),
                backGroundColor = Color.web(itemColor)
            ))
        }
    }
}





