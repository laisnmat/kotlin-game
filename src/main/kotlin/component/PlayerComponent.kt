package com.ensimag.component

import com.almasb.fxgl.dsl.components.HealthDoubleComponent
import com.almasb.fxgl.dsl.fire
import com.almasb.fxgl.dsl.showMessage
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.entity.component.Required
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.Vars
import com.ensimag.component.PlayerComponent.MoveDirection.*
import com.ensimag.entity.PlayerEntity
import com.ensimag.events.OnDyingEvent
import com.ensimag.events.OnHealthVeryLowEvent
import com.ensimag.events.OnHungryDamageEvent
import com.ensimag.events.OnIncreasingHungerEvent


@Required(AStarMoveComponent::class)
class PlayerComponent : Component() {

    internal enum class MoveDirection {
        UP, RIGHT, DOWN, LEFT, NONE
    }

    private val moveComponent: CellMoveComponent? = null

    private val astar: AStarMoveComponent? = null

    private var currentMoveDir = NONE
    private var nextMoveDir = NONE

    var hp = HealthDoubleComponent(Vars.PLAYER_MAX_HEALTH)
    var hungerPoint = HungerDoubleComponent(Vars.PLAYER_MAX_HUNGER)
    var time = 0.0
    var isHungry = false
        private set
    var isDying = false
        private set

    override fun onUpdate(tpf: Double) {
        movementAnimation()
        healthTimer(tpf)
        hungerTimer(tpf)
    }

    fun up() {
        nextMoveDir = UP
    }

    fun down() {
        nextMoveDir = DOWN
    }

    fun left() {
        nextMoveDir = LEFT
    }

    fun right() {
        nextMoveDir = RIGHT
    }

    fun movementAnimation(){
        val player = (this.getEntity() as PlayerEntity).getComponent(PlayerAnimationComponent::class.java)
        val x = moveComponent!!.cellX
        val y = moveComponent.cellY

        if (x == 0 && currentMoveDir == LEFT) {
            astar!!.stopMovementAt(astar.grid.width - 1, moveComponent.cellY)
            return
        } else if (x == astar!!.grid.width - 1 && currentMoveDir == RIGHT) {
            astar.stopMovementAt(0, moveComponent.cellY)
            return
        }

        if (astar.isMoving) return
        when (currentMoveDir) {
            UP -> {
                player.loopIdleUp()
            }

            RIGHT -> {
                player.loopIdleRight()
            }

            DOWN -> {
                player.loopIdleDown()
            }

            LEFT -> {
                player.loopIdleLeft()
            }

            NONE -> {
            }
        }
        currentMoveDir = NONE

        when (nextMoveDir) {
            UP -> if (astar.grid.getUp(x, y).filter { c: AStarCell -> c.state.isWalkable }.isPresent
            ) {
                currentMoveDir = nextMoveDir
                player.loopWalkUp()
                nextMoveDir = NONE
            }

            RIGHT -> if (astar.grid.getRight(x, y).filter { c: AStarCell -> c.state.isWalkable }.isPresent
            ) {
                currentMoveDir = nextMoveDir
                player.loopWalkRight()
                nextMoveDir = NONE
            }

            DOWN -> if (astar.grid.getDown(x, y).filter { c: AStarCell -> c.state.isWalkable }.isPresent
            ) {
                currentMoveDir = nextMoveDir
                player.loopWalkDown()
                nextMoveDir = NONE
            }

            LEFT -> if (astar.grid.getLeft(x, y).filter { c: AStarCell -> c.state.isWalkable }.isPresent
            ) {
                currentMoveDir = nextMoveDir
                player.loopWalkLeft()
                nextMoveDir = NONE
            }

            NONE -> {
            }
        }

        when (currentMoveDir) {
            UP -> {
                astar.moveToUpCell()
            }
            RIGHT -> {
                astar.moveToRightCell()
            }
            DOWN -> {
                astar.moveToDownCell()
            }
            LEFT -> {
                astar.moveToLeftCell()
            }
            NONE -> {
            }
        }
    }

    fun healthTimer(tpf: Double){
        if(isHungry){
            time+=tpf
            if(time>Vars.TIME_HEALTH_DAMAGE){
                fire(OnHungryDamageEvent(entity as PlayerEntity))
                time = 0.0
            }
        }
        if(hp.value <= 5){
            fire(OnHealthVeryLowEvent(entity as PlayerEntity))
        }
        if(hp.isZero){
            fire(OnDyingEvent(entity as PlayerEntity))
        }
    }

    fun hungerTimer(tpf: Double){
        if(!isHungry){
            time+=tpf
            if(time>Vars.TIME_HUNGER_DAMAGE){
                fire(OnIncreasingHungerEvent(entity as PlayerEntity))
                time = 0.0
                if(hungerPoint.isZero){
                    //fire(OnBeingHungryEvent(entity as PlayerEntity))
                    isHungry = true
                }
            }
        }
    }

    fun hungerBarDecrease(){
        hungerPoint.damagePercentageMax(5.0)
    }

    fun hpDamage(){
        hp.damagePercentageMax(20.0)
    }

    fun dying(){
        val player = (this.getEntity() as PlayerEntity)
        isDying = true
        showMessage("You Lose...")
        //player.animationComponent.playDeath(Runnable { player.removeFromWorld() })
        /*TODO AJOUTER EVENEMENT DE MORT*/
        isDying = false
        isHungry = false
        hungerPoint.restoreFully()
        hp.restoreFully()
    }

    fun eating(){
        hungerPoint.restorePercentageMax(25.0)
        time = 0.0
        isHungry = false
    }

    fun teleport() {
        astar!!.grid
            .getRandomCell { obj: AStarCell -> obj.isWalkable }
            .ifPresent { c: AStarCell -> astar.stopMovementAt(c.x, c.y) }
    }
}