package com.ensimag.entity

import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.onCollision
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.physics.PhysicsComponent
import com.ensimag.Config
import com.ensimag.Vars
import com.ensimag.component.PlayerComponent
import com.ensimag.logInfo
import javafx.geometry.Point2D
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarGrid
import com.ensimag.EntityType

class PlayerEntity : Entity(){
    private lateinit var astar: AStarMoveComponent


    private val playerComponent: PlayerComponent
        get() = getComponent(PlayerComponent::class.java)


    fun moveRight() {
        playerComponent.right()

    }

    fun moveLeft() {
        playerComponent.left()
    }

    fun moveUp() {
        playerComponent.up()
    }

    fun moveDown() {
        playerComponent.down()
    }

}