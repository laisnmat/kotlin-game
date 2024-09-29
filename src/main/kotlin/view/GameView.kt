package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.ensimag.model.GameModel
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GameView(private val model: GameModel) {

    private lateinit var player: Entity

    fun initialize() {
        println("Initializing view") // Message de débogage
        FXGL.setLevelFromMap("map.tmx")
        initPlayer()
    }


    fun initPlayer() {
        player = FXGL.entityBuilder()
            .at(400.0, 300.0) // Position de départ
            .viewWithBBox(Rectangle(15.0, 15.0, Color.RED)) // Exemple de visuel
            .buildAndAttach()

        // Placer la caméra pour suivre le joueur
        FXGL.getGameScene().viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0)
        println("Player initialized and camera bound") // Message de débogage
    }

    fun movePlayer(x: Double, y: Double) {
        println("Moving player by x: $x, y: $y") // Message de débogage
        // check if player is moving out of bounds
        if (player.x + x < 0 || player.x + x >= 80*16 || player.y + y < 0 || player.y + y >= 96*16) {
            return
        }
        player.translateX(x)
        player.translateY(y)
    }

    fun update() {
        // Tout ce qui doit être mis à jour chaque frame
    }
}
