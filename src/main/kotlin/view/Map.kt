package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.entity.level.tiled.Layer
import com.ensimag.Config
import com.ensimag.Vars
import java.util.function.Predicate

class Map {

    fun initMap(){
        val level = FXGL.setLevelFromMap(Config.MAP)
        Vars.MAP_WIDTH = level.width
        Vars.MAP_HEIGHT = level.height

        getGameWorld().getEntitiesFiltered(Predicate { it.isType("TiledMapLayer") })
                //it.viewComponent.isVisible = false
            .filter { it.getObject<Layer>("layer").name == "AbovePlayer" }
            .forEach {
                it.viewComponent.parent.isMouseTransparent = true
                it.zIndex = 6000
            }


    }

}