package com.ensimag

import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.localization.Language
import com.almasb.fxgl.pathfinding.CellState
import com.almasb.fxgl.pathfinding.astar.AStarGrid
import com.ensimag.events.EventHandlers
import com.ensimag.gameplay.Gameplay
import com.ensimag.view.GameView


class ETCIApp : GameApplication() {

    private val view : GameView = GameView()
    private var grid: AStarGrid? = null

    fun getGrid(): AStarGrid {
        return grid!!
    }

    override fun onPreInit() {
        EventHandlers.initialize()
    }

    override fun initSettings(settings: GameSettings) {
        settings.apply {
            width = Config.WIDTH
            title = Config.TITLE
            version = Config.VERSION
            defaultLanguage = Language.FRENCH
            isDeveloperMenuEnabled = true
        }
        settings.setHeightFromRatio(16 / 9.0)
        settings.applicationMode = ApplicationMode.DEVELOPER
    }

    override fun initGame() {
        getGameScene().viewport.isLazy = true
        getGameWorld().addWorldListener(ETCIMap())
        getGameWorld().addEntityFactory(ETCIFactory())
        view.initView()
        initGrid()
        Gameplay.spawnPlayer()
    }

    fun initGrid(){
        grid = AStarGrid.fromWorld(getGameWorld(), Vars.MAP_WIDTH/Config.TILE_SIZE, Vars.MAP_HEIGHT/Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE) { type ->
            if (type.equals(EntityType.NAV) || type.equals(EntityType.BRIDGE))
                return@fromWorld CellState.WALKABLE
            else
                return@fromWorld CellState.NOT_WALKABLE
        }
    }

    override fun onUpdate(tpf: Double) {
        view.updateUI(tpf)
        Gameplay.spawnChickens(tpf)
    }

    override fun initInput() {
        Gameplay.configureInput()
    }

    override fun initPhysics() {

    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        super.initGameVars(vars)
        vars?.put("nbChicken", 0)
    }

    override fun initUI() {
        view.initUI()
    }
}

fun main() {
    GameApplication.launch(ETCIApp::class.java, emptyArray())
}
