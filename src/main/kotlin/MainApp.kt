package com.ensimag

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.getGameScene
import com.almasb.fxgl.input.UserAction
import com.ensimag.controller.GameController
import com.ensimag.model.GameModel
import com.ensimag.view.GameView
import javafx.scene.input.KeyCode

class MainApp : GameApplication() {

    private lateinit var model: GameModel
    private lateinit var view: GameView
    private lateinit var controller: GameController

    override fun initSettings(settings: GameSettings) {
        settings.apply {
            width = 800
            height = 600
            title = "Escape the Chicken Island"
            version = "1.0"
        }
    }

    override fun initGame() {
        println("initGame called") // Message de débogage
        FXGL.getGameScene().viewport.setBounds(0, 0, 80*16, 96*16) // Définir les limites de la caméra
        model = GameModel()
        view = GameView(model)
        controller = GameController(model, view)
        view.initialize()

        // Appel de la configuration des entrées après l'initialisation du contrôleur
        controller.configureInput()
        println("Input configured") // Message de débogage
    }

    override fun onUpdate(tpf: Double) {
        view.update() // Mise à jour de la vue si nécessaire
    }

    // Supprimer l'appel à configureInput() ici
    override fun initInput() {
        // Supprimez l'appel à configureInput(), car il est maintenant fait dans initGame().
    }
}

fun main() {
    GameApplication.launch(MainApp::class.java, emptyArray())
}
