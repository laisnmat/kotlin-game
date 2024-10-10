package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.Config
import com.ensimag.ui.PlayerInventoryView
import com.ensimag.Vars
import com.ensimag.ui.PlayerActionView

class GameView {
    private val map = Map()

    fun initView() {
        map.initMap()
        FXGL.getGameScene().viewport.setBounds(0, 0, Vars.MAP_WIDTH, Vars.MAP_HEIGHT)
        FXGL.getGameScene().viewport.setZoom(Config.ZOOM)


    }

    fun initUI() {
        FXGL.addUINode(PlayerInventoryView())
        FXGL.addUINode(PlayerActionView())
    }

    fun updateUI(tpf: Double) {
        FXGL.getGameScene().uiNodes.forEach {
            if (it is PlayerInventoryView) {
                it.update(tpf)
            }
        }
    }
}