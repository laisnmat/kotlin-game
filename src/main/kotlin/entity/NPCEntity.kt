package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.ensimag.component.NPC.NPCComponent

class NPCEntity : Entity(){

    private val npcComponent: NPCComponent
        get() = getComponent(NPCComponent::class.java)

    val cellX: Int
        get() = getComponent(CellMoveComponent::class.java).cellX

    val cellY: Int
        get() = getComponent(CellMoveComponent::class.java).cellY

    /*fun randomMovement(){
        npcComponent.randomMovement()
    }*/

    fun teleport(){
        npcComponent.teleport()
    }





}