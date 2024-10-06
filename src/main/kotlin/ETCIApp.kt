package com.ensimag

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.spawn
import com.ensimag.entity.PlayerEntity
import com.ensimag.gameplay.PlayerController
import com.ensimag.view.GameView

class ETCIApp : GameApplication() {

    private val view : GameView = GameView()
    private val playerController : PlayerController = PlayerController()

    override fun initSettings(settings: GameSettings) {
        settings.apply {
            width = Config.WIDTH
            height = Config.HEIGHT
            title = Config.TITLE
            version = Config.VERSION
        }
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(ETCIFactory())
        view.initView()
        val player = spawn("player", 100.0, 100.0)
        playerController.initController()
    }

    override fun onUpdate(tpf: Double) {

    }

    override fun initInput() {
        playerController.configureInput()
    }

    override fun initPhysics() {
    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        super.initGameVars(vars)
    }
}

fun main() {
    GameApplication.launch(ETCIApp::class.java, emptyArray())
}
