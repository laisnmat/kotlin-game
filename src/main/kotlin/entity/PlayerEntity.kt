package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.Player.*
import com.sun.org.apache.xpath.internal.operations.Bool

class PlayerEntity : Entity(){
    private lateinit var astar: AStarMoveComponent

    val playerComponent: PlayerComponent
        get() = getComponent(PlayerComponent::class.java)

    val animationComponent: PlayerAnimationComponent
        get() = getComponent(PlayerAnimationComponent::class.java)

    val actionsComponent: ActionsComponent
        get() = getComponent(ActionsComponent::class.java)

    val playerChildViewComponent: PlayerChildViewComponent
        get() = getComponent(PlayerChildViewComponent::class.java)

    val inventoryComponent: InventoryComponent
        get() = getComponent(InventoryComponent::class.java)

    val hp get() = playerComponent.hp
    val hungerPoint get() = playerComponent.hungerPoint
    val isHungry: Boolean
        get() = playerComponent.isHungry
    val isDying: Boolean
        get() = playerComponent.isDying

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

    fun hasFood(): Boolean {
        return actionsComponent.hasFood()
    }

    fun hasPotion(): Boolean {
        return actionsComponent.hasPotion()
    }

    fun teleport(){
        playerComponent.teleport()
    }
}