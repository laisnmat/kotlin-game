package com.ensimag.component

import com.almasb.fxgl.dsl.components.HealthDoubleComponent
import com.almasb.fxgl.dsl.components.view.ChildViewComponent
import com.almasb.fxgl.ui.Position
import com.almasb.fxgl.ui.ProgressBar
import com.ensimag.Config
import com.ensimag.Vars
import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeType

class PlayerChildViewComponent : ChildViewComponent(0.0, 30.0, isTransformApplied = true) {

    private lateinit var player: PlayerComponent

    override fun onAdded() {
        super.onAdded()
        createView()
    }

    private fun createView() {
        var hpBar = createHPBar()
        var hungerBar = createHungerBar()
        hungerBar.translateY = hpBar.translateY+2.6
        viewRoot.children += Group(hpBar, hungerBar)
    }

    private fun createHPBar() : ProgressBar{
        val hpBar = ProgressBar()
        with(hpBar){
            setMinValue(0.0)
            maxValueProperty().bind(player.hp.maxValueProperty())
            currentValueProperty().bind(player.hp.valueProperty())
            setFill(Color.RED)
            setTraceFill(null)
            isLabelVisible = false
            innerBar.arcWidthProperty().unbind()
            innerBar.arcHeightProperty().unbind()
            innerBar.arcWidthProperty().value = 0.0
            innerBar.arcHeightProperty().value = 0.0
            innerBar.effect = null
            backgroundBar.effect = null
            backgroundBar.arcWidthProperty().unbind()
            backgroundBar.arcHeightProperty().unbind()
            backgroundBar.arcWidthProperty().value = 0.0
            backgroundBar.arcHeightProperty().value = 0.0
            backgroundBar.widthProperty().unbind()
            backgroundBar.heightProperty().unbind()
            backgroundBar.stroke = Color.BLACK
            backgroundBar.width = Vars.WIDTH_HEALTH_BAR - 9.4
            backgroundBar.height = 2.6
            backgroundBar.fill = null
            innerBar.stroke = null
            backgroundBar.translateY += 2.7
            backgroundBar.translateX += 4.6
            backgroundBar.strokeWidth = 0.4
            translateX -= Config.SPRITE_SIZE_X.toDouble() - Config.TILE_SIZE.toDouble()/2.0 - 0.85
            translateY -= 3.0
            setWidth(Vars.WIDTH_HEALTH_BAR)
            setHeight(Vars.HEIGHT_HEALTH_BAR)
        }
        return hpBar
    }

    private fun createHungerBar() : ProgressBar{
        val hungerBar = ProgressBar()
        with(hungerBar){
            setMinValue(0.0)
            maxValueProperty().bind(player.hungerPoint.maxValueProperty())
            currentValueProperty().bind(player.hungerPoint.valueProperty())
            setFill(Color.BROWN)
            setTraceFill(null)
            isLabelVisible = false
            innerBar.arcWidthProperty().unbind()
            innerBar.arcHeightProperty().unbind()
            innerBar.arcWidthProperty().value = 0.0
            innerBar.arcHeightProperty().value = 0.0
            innerBar.effect = null
            backgroundBar.effect = null
            backgroundBar.arcWidthProperty().unbind()
            backgroundBar.arcHeightProperty().unbind()
            backgroundBar.arcWidthProperty().value = 0.0
            backgroundBar.arcHeightProperty().value = 0.0
            backgroundBar.widthProperty().unbind()
            backgroundBar.heightProperty().unbind()
            backgroundBar.stroke = Color.BLACK
            backgroundBar.width = Vars.WIDTH_HEALTH_BAR - 9.4
            backgroundBar.height = 2.6
            backgroundBar.fill = null
            innerBar.stroke = null
            backgroundBar.translateY += 2.7
            backgroundBar.translateX += 4.6
            backgroundBar.strokeWidth = 0.4
            translateX -= Config.SPRITE_SIZE_X.toDouble() - Config.TILE_SIZE.toDouble()/2.0 - 0.85
            setWidth(Vars.WIDTH_HEALTH_BAR)
            setHeight(Vars.HEIGHT_HEALTH_BAR)
        }
        return hungerBar
    }
}