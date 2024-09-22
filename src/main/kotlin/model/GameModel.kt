// model/GameModel.kt
package com.ensimag.model

data class Player(var score: Int = 0, var lives: Int = 3)

class GameModel {
    var player = Player()

    fun increaseScore(points: Int) {
        player.score += points
    }

    fun loseLife() {
        if (player.lives > 0) {
            player.lives -= 1
        }
    }

    fun isGameOver(): Boolean {
        return player.lives <= 0
    }
}
