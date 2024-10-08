package com.ensimag

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.localization.Language
import com.almasb.fxgl.pathfinding.CellState
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarGrid
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.PlayerComponent
import com.ensimag.gameplay.PlayerController
import com.ensimag.view.GameView


class ETCIApp : GameApplication() {

    private val view : GameView = GameView()
    private val playerController : PlayerController = PlayerController()
    private var grid: AStarGrid? = null

    fun getGrid(): AStarGrid {
        return grid!!
    }

    override fun initSettings(settings: GameSettings) {
        settings.apply {
            width = Config.WIDTH
            height = Config.HEIGHT
            title = Config.TITLE
            version = Config.VERSION
            defaultLanguage = Language.FRENCH
            isDeveloperMenuEnabled = true
        }
    }

    override fun initGame() {
        getGameWorld().addWorldListener(ETCIMap())
        getGameWorld().addEntityFactory(ETCIFactory())
        view.initView()

        grid = AStarGrid.fromWorld(getGameWorld(), Vars.MAP_WIDTH/Config.TILE_SIZE, Vars.MAP_HEIGHT/Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE) { type ->
            if (type.equals(EntityType.NAV) || type.equals(EntityType.BRIDGE))
                return@fromWorld CellState.WALKABLE
            else
                return@fromWorld CellState.NOT_WALKABLE
        }

        val player = spawn("player")
        player.getComponent(PlayerComponent::class.java).teleport()

        playerController.initController()
        FXGL.getGameScene().viewport.bindToEntity(player, getAppWidth()/2.0, getAppWidth()/2.0)
    }

    override fun onUpdate(tpf: Double) {
        val nbChicken = getWorldProperties().getInt("nbChicken")
        if (nbChicken == 0){
            showMessage("You won! Press ESC to exit")
        }

        if (nbChicken < 10 ){
            val chicken = spawn("Chicken")
            animationBuilder().fadeIn(chicken).buildAndPlay()
            val astar = chicken.getComponent(AStarMoveComponent::class.java)
            astar!!.grid
                .getRandomCell { obj: AStarCell -> obj.isWalkable }
                .ifPresent { c: AStarCell -> astar.stopMovementAt(c.x, c.y) }
        }
    }

    override fun initInput() {
        playerController.configureInput()
    }

    override fun initPhysics() {
        onCollisionBegin(EntityType.PLAYER, EntityType.CHICKEN) { _, chicken ->
            val anim = animationBuilder().fadeOut(chicken)
            anim.onFinished(Runnable {
                chicken.removeFromWorld()
            })
            anim.buildAndPlay()
        }

    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        super.initGameVars(vars)
        vars?.put("nbChicken", 0)
    }
}

fun main() {
    GameApplication.launch(ETCIApp::class.java, emptyArray())
}
