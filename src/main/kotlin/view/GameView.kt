// view/GameView.kt
package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.ensimag.model.GameModel
import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import javafx.scene.text.Text

class GameView(private val model: GameModel) {

    private lateinit var scoreText: Text
    private lateinit var playerEntity: Entity

    fun initialize() {
        // Initialize the game view, player, and HUD
        playerEntity = FXGL.entityBuilder()
            .at(300.0, 300.0)
            .view(Rectangle(40.0, 40.0, Color.BLUE))
            .buildAndAttach()

        scoreText = FXGL.getUIFactoryService().newText("", Color.BLACK, 24.0)
        FXGL.addUINode(scoreText, 10.0, 10.0)
    }

    fun update() {
        // Update the score display based on the model
        scoreText.text = "Score: ${model.player.score}"
    }
}
