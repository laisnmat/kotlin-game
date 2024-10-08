package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.entity.component.Required
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarCell
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.ensimag.component.PlayerComponent.MoveDirection.*
import com.ensimag.entity.PlayerEntity


@Required(AStarMoveComponent::class)
class PlayerComponent : Component() {
    internal enum class MoveDirection {
        UP, RIGHT, DOWN, LEFT, NONE
    }

    private val moveComponent: CellMoveComponent? = null

    private val astar: AStarMoveComponent? = null

    private var currentMoveDir = NONE
    private var nextMoveDir = NONE

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

    override fun onUpdate(tpf: Double) {
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

    fun teleport() {
        astar!!.grid
            .getRandomCell { obj: AStarCell -> obj.isWalkable }
            .ifPresent { c: AStarCell -> astar.stopMovementAt(c.x, c.y) }
    }
}