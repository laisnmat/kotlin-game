package com.ensimag.gameplay

import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.input.UserAction
import com.ensimag.EntityType
import com.ensimag.component.PlayerAnimationComponent
import com.ensimag.component.PlayerComponent
import com.ensimag.component.TreeAnimationComponent
import com.ensimag.entity.PlayerEntity
import com.ensimag.entity.NPCEntity
import com.ensimag.events.OnAttackEvent
import com.ensimag.events.Events
import com.ensimag.events.OnCuttingEvent
import com.ensimag.events.OnEatingEvent
import com.ensimag.items.ActionConfig
import com.ensimag.items.Actions
import com.ensimag.logInfo
import javafx.scene.input.KeyCode

class PlayerController() {
    private lateinit var playerEntity: PlayerEntity

    fun initController(){
        val entity = FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
        if (entity is PlayerEntity) {
            playerEntity = entity
        }
        else {
            throw IllegalStateException("Expected an entity of type PlayerEntity but got: ${entity.javaClass.name}")
        }
    }

    fun configureInput(){
        getInput().addAction(object : UserAction("Move Right") {
            override fun onAction() {
                playerEntity.moveRight()
            }
        }, KeyCode.RIGHT)

        getInput().addAction(object : UserAction("Move Left") {
            override fun onAction() {
                playerEntity.moveLeft()
            }
        }, KeyCode.LEFT)

        getInput().addAction(object : UserAction("Move Up") {
            override fun onAction() {
                playerEntity.moveUp()
            }
        }, KeyCode.UP)

        getInput().addAction(object : UserAction("Move Down") {
            override fun onAction() {
                playerEntity.moveDown()
            }
        }, KeyCode.DOWN)

        getInput().addAction(object : UserAction("Teleport") {
            override fun onAction() {
                playerEntity.getComponent(PlayerComponent::class.java).teleport()

            }
        }, KeyCode.T)

        getInput().addAction(object : UserAction("Attack") {
            override fun onActionBegin() {
                if (getWorldProperties().getValue<ActionConfig>("selectedItem").action == Actions.FOOD) {
                    fire(OnEatingEvent(playerEntity))
                    return@onActionBegin
                }

                var collidingEntity = getGameWorld().getCollidingEntities(playerEntity)
                if (collidingEntity.isNotEmpty()) {
                    collidingEntity.forEach {
                        if (it.type == EntityType.CHICKEN && getWorldProperties().getValue<ActionConfig>("selectedItem").action == Actions.SWORD) {
                            fire(OnAttackEvent(playerEntity, it as NPCEntity))
                        }
                        if (it.type == EntityType.TREE && getWorldProperties().getValue<ActionConfig>("selectedItem").action == Actions.AXE) {
                            fire(OnCuttingEvent(playerEntity, it))
                        }
                    }
                }
            }
        }, KeyCode.SPACE)


    }

}