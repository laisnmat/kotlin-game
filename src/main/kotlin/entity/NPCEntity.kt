package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.event.Subscriber
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.NPCComponent

class NPCEntity : Entity(){
    private lateinit var subscribers: Subscriber
    private lateinit var astar: AStarMoveComponent

    private val npcComponent: NPCComponent
        get() = getComponent(NPCComponent::class.java)

    val cellX: Int
        get() = getComponent(CellMoveComponent::class.java).cellX

    val cellY: Int
        get() = getComponent(CellMoveComponent::class.java).cellY

    /*fun randomMovement(){
        npcComponent.randomMovement()
    }*/

    fun setSubscriber(subscriber: Subscriber){
        subscribers = subscriber
    }

    fun getSubscriber(): Subscriber? {
        if(!::subscribers.isInitialized){
            return null
        }
        return subscribers
    }





}