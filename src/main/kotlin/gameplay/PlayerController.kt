package com.ensimag.gameplay

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.getInput
import com.almasb.fxgl.input.UserAction
import com.ensimag.EntityType
import com.ensimag.entity.PlayerEntity
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

    }

}