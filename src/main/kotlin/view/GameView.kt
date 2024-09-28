package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.ensimag.model.GameModel
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import java.io.FileReader

class GameView(private val model: GameModel) {


    private var texture: MyTexture = MyTexture()


    fun initialize() {
        println("Initializing view") // Message de débogage
        renderMap()
        initPlayer()
    }

    private fun renderMap() {
        val lines = FileReader("src/main/resources/grass_island3.csv").readLines()
        for (i in lines.indices){
            val line = lines[i].split(",")
            for (j in line.indices){
                val n = line[j].toInt()
                if(n != -1){
                    texture.draw(n, j.toDouble()*16, i.toDouble()*16)
                }
            }
        }
    }

    fun initPlayer() {
        player = FXGL.entityBuilder()
            .at(404.0, 308.0) // Position de départ
            .viewWithBBox(Rectangle(8.0, 8.0, Color.RED)) // Exemple de visuel
            .buildAndAttach()

        // Placer la caméra pour suivre le joueur
        FXGL.getGameScene().viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0)
        FXGL.getGameScene().viewport.setZoom(3.0)
        println("Player initialized and camera bound")
    }

    fun movePlayer(x: Double, y: Double) {
        println("Moving player by x: $x, y: $y")
        player.translateX(x)
        player.translateY(y)
    }

    fun update() {
        // Tout ce qui doit être mis à jour chaque frame
    }
}