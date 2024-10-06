package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.Config
import com.ensimag.Vars

class GameView {
    private val map = Map()

    fun initView() {
        map.initMap()
        FXGL.getGameScene().viewport.setBounds(0, 0, Vars.MAP_WIDTH, Vars.MAP_HEIGHT)
        FXGL.getGameScene().viewport.setZoom(Config.ZOOM)

    }
}