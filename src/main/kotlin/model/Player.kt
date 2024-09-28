package com.ensimag.model

import com.almasb.fxgl.entity.Entity


class Player{
    private lateinit var player: Entity

    fun setPlayer(player: Entity){
        this.player = player
    }

    fun getPlayer(): Entity{
        return player
    }

    fun move(x: Double, y: Double){
        player.translateX(x)
        player.translateY(y)
    }

    fun initPlayer(){
        player = Entity()
    }

    fun update(){
        // Tout ce qui doit être mis à jour chaque frame
    }

    fun getPlayerX(): Double{
        return player.x
    }

    fun getPlayerY(): Double{
        return player.y
    }
}