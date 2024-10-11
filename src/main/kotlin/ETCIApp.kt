package com.ensimag

import com.almasb.fxgl.achievement.Achievement
import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.localization.Language
import com.almasb.fxgl.pathfinding.CellState
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarGrid
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.PlayerComponent
import com.ensimag.entity.PlayerEntity
import com.ensimag.events.EventHandlers
import com.ensimag.events.OnEatingEvent
import com.ensimag.gameplay.PlayerController
import com.ensimag.view.GameView


class ETCIApp : GameApplication() {

    private val view : GameView = GameView()
    private val playerController : PlayerController = PlayerController()
    private var grid: AStarGrid? = null
    private var time: Double = 0.0
    private var randomTime: Double = random(1.0, 10.0)
    private var init = false

    fun getGrid(): AStarGrid {
        return grid!!
    }

    override fun onPreInit() {
        EventHandlers.initialize()
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
        settings.applicationMode = ApplicationMode.DEVELOPER
    }

    override fun initGame() {
        getGameWorld().addWorldListener(ETCIMap())
        getGameWorld().addEntityFactory(ETCIFactory())
        view.initView()
        getEventBus().
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
        view.updateUI(tpf)
        time += tpf
        val nbChicken = getWorldProperties().getInt("nbChicken")
        if (nbChicken == 0){
            showMessage("You won! Press ESC to exit")
        }
        if (!init ||(nbChicken < 10 && time > randomTime + (10-nbChicken) * 0.8)){

            time = 0.0
            randomTime = random(1.0, 10.0)
            val chicken = spawn("Chicken")
            animationBuilder().fadeIn(chicken).buildAndPlay()
            val astar = chicken.getComponent(AStarMoveComponent::class.java)
            astar!!.grid
                .getRandomCell { obj: AStarCell -> obj.isWalkable }
                .ifPresent { c: AStarCell -> astar.stopMovementAt(c.x, c.y) }
        }
        if(nbChicken == 10){
            init = true
        }
    }

    override fun initInput() {
        playerController.configureInput()
    }

    override fun initPhysics() {
        onCollisionBegin(EntityType.PLAYER, EntityType.CHICKEN) { player, chicken ->
            val anim = animationBuilder().fadeOut(chicken)
            fire(OnEatingEvent(player as PlayerEntity))
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

    override fun initUI() {
        view.initUI()
    }
}

fun main() {
    GameApplication.launch(ETCIApp::class.java, emptyArray())
}
