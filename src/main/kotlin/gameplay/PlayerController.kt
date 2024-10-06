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
        playerEntity = FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER).first() as PlayerEntity
    }

    fun configureInput(){
        getInput().addAction(object : UserAction("Move Right") {
            override fun onAction() {
                playerEntity.moveRight()
            }
        }, KeyCode.D)

        getInput().addAction(object : UserAction("Move Left") {
            override fun onAction() {
                playerEntity.moveLeft()
            }
        }, KeyCode.Q)

        getInput().addAction(object : UserAction("Move Up") {
            override fun onAction() {
                playerEntity.moveUp()
            }
        }, KeyCode.Z)

        getInput().addAction(object : UserAction("Move Down") {
            override fun onAction() {
                playerEntity.moveDown()
            }
        }, KeyCode.S)

    }

}