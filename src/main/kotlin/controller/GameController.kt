// controller/GameController.kt
package com.ensimag.controller

import com.ensimag.model.GameModel
import com.ensimag.view.GameView

class GameController(private val model: GameModel, private val view: GameView) {

    fun onPlayerScored(points: Int) {
        model.increaseScore(points)
        view.update()
    }

    fun onPlayerHit() {
        model.loseLife()
        if (model.isGameOver()) {
            onGameOver()
        }
        view.update()
    }

    private fun onGameOver() {
        // Handle game over logic
        println("Game Over")
    }
}
