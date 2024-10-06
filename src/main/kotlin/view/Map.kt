package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.ensimag.Config
import com.ensimag.Vars

class Map {

    fun initMap(){
        val level = FXGL.setLevelFromMap(Config.MAP)
        Vars.MAP_WIDTH = level.width
        Vars.MAP_HEIGHT = level.height

    }

}