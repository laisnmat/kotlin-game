package com.ensimag.controller

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getInput
import com.almasb.fxgl.input.UserAction
import com.ensimag.model.GameModel
import com.ensimag.view.GameView
import javafx.scene.input.KeyCode

class GameController(private var model: GameModel, private val view: GameView) {

    private val playerSpeed = 5.0

    fun configureInput() {
        getInput().addAction(object : UserAction("Move Left") {
            override fun onAction() {
                view.movePlayer(-playerSpeed, 0.0)
            }
        }, KeyCode.LEFT)

        getInput().addAction(object : UserAction("Move Right") {
            override fun onAction() {
                view.movePlayer(playerSpeed, 0.0)
            }
        }, KeyCode.RIGHT)

        getInput().addAction(object : UserAction("Move Up") {
            override fun onAction() {
                view.movePlayer(0.0, -playerSpeed)
            }
        }, KeyCode.UP)

        getInput().addAction(object : UserAction("Move Down") {
            override fun onAction() {
                view.movePlayer(0.0, playerSpeed)
            }
        }, KeyCode.DOWN)
    }
}
