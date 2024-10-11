package com.ensimag.gameplay

import com.almasb.fxgl.dsl.*
import com.ensimag.items.Items
import com.almasb.fxgl.entity.Entity
import com.ensimag.EntityType
import com.ensimag.entity.PlayerEntity
import com.ensimag.entity.NPCEntity

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
        player.inventoryComponent.add(item)
    }

    fun showMessage(message: String){
        FXGL.showMessage(message)
    }

    fun win(){
        FXGL.showMessage("You won! Press ESC to exit")
    }

    fun spawnPlayer(): Entity{
        val playerEntity = getGameWorld().spawn("player") as PlayerEntity
        playerEntity.teleport()
        playerController.initController()
        FXGL.getGameScene().viewport.bindToEntity(player, getAppWidth() /2.0, getAppHeight() /2.0)
        return playerEntity
    }

    fun spawnChickens(tpf: Double) {
        time += tpf
        val nbChicken = getWorldProperties().getInt("nbChicken")
        if (!init ||(nbChicken < 10 && time > randomTime + (10-nbChicken) * 0.8)){
            time = 0.0
            randomTime = random(1.0, 10.0)
            val chicken = com.almasb.fxgl.dsl.spawn("Chicken") as NPCEntity
            animationBuilder().fadeIn(chicken).buildAndPlay()
            chicken.teleport()
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