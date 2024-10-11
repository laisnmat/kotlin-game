package com.ensimag.gameplay

import com.almasb.fxgl.dsl.*
import com.ensimag.items.Items
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.EntityType
import com.ensimag.component.InventoryComponent
import com.ensimag.entity.PlayerEntity
import com.ensimag.component.PlayerComponent
import com.ensimag.gameplay.PlayerController

object Gameplay {
    val player: PlayerEntity by lazy { getGameWorld().getSingleton(EntityType.PLAYER) as PlayerEntity }
    private val playerController : PlayerController = PlayerController()
    var time: Double = 0.0
    var randomTime: Double = random(1.0, 10.0)
    var init = false

    fun removeFromWorld(entity: Entity){
        entity.removeFromWorld()
    }

    fun addItem(item: Items){
        player.getComponent(InventoryComponent::class.java).add(item)
    }

    fun showMessage(message: String){
        FXGL.showMessage(message)
    }

    fun win(){
        FXGL.showMessage("You won! Press ESC to exit")
    }

    fun spawnPlayer(): Entity{
        val playerEntity = getGameWorld().spawn("player")
        playerEntity.getComponent(PlayerComponent::class.java).teleport()
        playerController.initController()
        FXGL.getGameScene().viewport.bindToEntity(player, getAppWidth() /2.0, getAppWidth() /2.0)
        return playerEntity
    }

    fun spawnChickens(tpf: Double) {
        time += tpf
        val nbChicken = getWorldProperties().getInt("nbChicken")
        if (!init ||(nbChicken < 10 && time > randomTime + (10-nbChicken) * 0.8)){
            time = 0.0
            randomTime = random(1.0, 10.0)
            val chicken = com.almasb.fxgl.dsl.spawn("Chicken")
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

    fun spawn(entityName: String): Entity{
        return getGameWorld().spawn(entityName)
    }

    fun configureInput(){
        playerController.configureInput()
    }
}