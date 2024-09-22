// MainApp.kt
package com.ensimag

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.onKeyDown
import com.ensimag.controller.GameController
import com.ensimag.model.GameModel
import com.ensimag.view.GameView
import javafx.scene.input.KeyCode
import java.awt.Dimension
import java.awt.Toolkit

class MainApp : GameApplication() {

    private lateinit var model: GameModel
    private lateinit var view: GameView
    private lateinit var controller: GameController

    override fun initSettings(settings: GameSettings) {
        settings.apply {
            width = 800;
            height = 600;
            title = "Escape the Chicken Island"
            version = "1.0"
        }
    }

    override fun initGame() {
        model = GameModel()
        view = GameView(model)
        controller = GameController(model, view)

        view.initialize()
    }

    override fun onUpdate(tpf: Double) {
        view.update()
    }


    // Handle key events, collisions, etc.
    override fun initInput() {
        onKeyDown(KeyCode.UP, "Increase Score") {
            controller.onPlayerScored(10)
        }


    }
}

fun main() {
    GameApplication.launch(MainApp::class.java, emptyArray())
}
