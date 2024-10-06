package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.ensimag.Config
import com.ensimag.Vars
import com.ensimag.component.PlayerComponent

class PlayerEntity : Entity(){

    val playerComponent: PlayerComponent
        get() = getComponent(PlayerComponent::class.java)

    val hp get() = playerComponent.hp

    val isDie: Boolean
        get() = playerComponent.isDie

    fun move(x: Double, y: Double) {
        playerComponent.move(x, y)
    }

    fun moveRight() {
        move(Vars.PLAYER_SPEED, 0.0)
    }

    fun moveLeft() {
        move(-Vars.PLAYER_SPEED, 0.0)
    }

    fun moveUp() {
        move(0.0, -Vars.PLAYER_SPEED)
    }

    fun moveDown() {
        move(0.0, Vars.PLAYER_SPEED)
    }

    /*val cellX: Int
        get() = getComponent(CellMoveComponent::class.java).cellX

    val cellY: Int
        get() = getComponent(CellMoveComponent::class.java).cellY
    */
}