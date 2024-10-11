package com.ensimag

import com.almasb.fxgl.achievement.Achievement
import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.inventory.ItemConfig
import com.almasb.fxgl.localization.Language
import com.almasb.fxgl.pathfinding.CellState
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarGrid
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.ActionsComponent
import com.ensimag.component.NPCComponent
import com.ensimag.component.PlayerComponent
import com.ensimag.entity.NPCEntity
import com.ensimag.entity.PlayerEntity
import com.ensimag.events.EventHandlers
import com.ensimag.events.Events
import com.ensimag.events.OnAttackEvent
import com.ensimag.events.OnEatingEvent
import com.ensimag.gameplay.Gameplay
import com.ensimag.gameplay.PlayerController
import com.ensimag.items.ActionConfig
import com.ensimag.items.Actions
import com.ensimag.view.GameView


class ETCIApp : GameApplication() {

    private val view : GameView = GameView()
    //private val playerController : PlayerController = PlayerController()
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

//        FXGL.onCollision(EntityType.PLAYER, EntityType.CHICKEN){ player, chicken ->
//            logInfo("Collision detected between player and chicken")
//            val subscribers = onEvent(Events.ON_ATTACK){
//                logInfo("Attack received")
//                val anim = animationBuilder().fadeOut(chicken)
//                fire(OnEatingEvent(player as PlayerEntity))
//                anim.onFinished(Runnable {
//                    chicken.removeFromWorld()
//                })
//                anim.buildAndPlay()
//            }
//            (chicken as NPCEntity).setSubscriber(subscribers)
//
//        }
//
//        onCollisionEnd(EntityType.PLAYER, EntityType.CHICKEN) { player, chicken ->
//            logInfo("Collision ended between player and chicken")
//            (chicken as NPCEntity).getSubscriber()?.unsubscribe()
//        }


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
