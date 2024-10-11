package com.ensimag.component

import com.almasb.fxgl.dsl.random
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.event.Subscriber
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.Vars
import com.ensimag.entity.NPCEntity
import com.ensimag.entity.PlayerEntity

class NPCComponent : Component() {
    private val astar: AStarMoveComponent?= null
    private var time = 0.0
    private var idleTime = random(Vars.NPC_IDLE_TIME, Vars.NPC_IDLE_TIME+Vars.NPC_RANDOM_IDLE_TIME)
    private val moveComponent: CellMoveComponent? = null


    override fun onUpdate(tpf: Double) {
        val npcAnimation = (this.getEntity() as NPCEntity).getComponent(NPCAnimationComponenent::class.java)
        time+=tpf
        moveToRandomCell(tpf)
        doAnimation(npcAnimation)
    }

    fun moveToRandomCell(tpf: Double){
        time+=tpf
        if(time>idleTime){
            val cellX = (getEntity() as NPCEntity).cellX
            val cellY = (getEntity() as NPCEntity).cellY
            astar!!.grid
                .getRandomCell{it.isWalkable && it.distance(astar!!.grid.get(cellX,cellY)) < Vars.DISTANCE_RANDOM_WALK}
                .ifPresent {
                    astar!!.moveToCell(it.x, it.y)
                }
            idleTime = random(Vars.NPC_IDLE_TIME, Vars.NPC_IDLE_TIME+Vars.NPC_RANDOM_IDLE_TIME)
            time = 0.0
        }
    }

    fun doAnimation(npcAnimation: NPCAnimationComponenent){
        when {
            moveComponent!!.isMovingUp -> npcAnimation.loopWalkUp()
            moveComponent.isMovingDown -> npcAnimation.loopWalkDown()
            moveComponent.isMovingLeft -> npcAnimation.loopWalkLeft()
            moveComponent.isMovingRight -> npcAnimation.loopWalkRight()
            !moveComponent.isMoving -> npcAnimation.loopIdleDown()
        }
    }

    fun teleport() {
        astar!!.grid
            .getRandomCell { obj: AStarCell -> obj.isWalkable }
            .ifPresent { c: AStarCell -> astar.stopMovementAt(c.x, c.y) }
    }


}