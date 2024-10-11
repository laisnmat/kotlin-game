package com.ensimag

import com.almasb.fxgl.core.util.LazyValue
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.entityBuilder
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.almasb.fxgl.entity.components.CollidableComponent
import com.almasb.fxgl.pathfinding.CellMoveComponent
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import com.ensimag.component.*
import com.ensimag.entity.NPCEntity
import com.ensimag.entity.PlayerEntity
import com.ensimag.npc.Pets
import javafx.geometry.Point2D

class ETCIFactory : EntityFactory{


    @Spawns("player")
    fun newPlayer(data: SpawnData): Entity {
        val player = PlayerEntity()
        player.setPosition(data.x, data.y)
        player.localAnchor = Point2D(Config.SPRITE_SIZE_X.toDouble() - Config.TILE_SIZE.toDouble()/2.0, Config.SPRITE_SIZE_Y.toDouble() - Config.TILE_SIZE.toDouble()/2.0)
        player.boundingBoxComponent.addHitBox(HitBox(Point2D(0.0, Config.TILE_SIZE.toDouble()), BoundingShape.box(Config.TILE_SIZE.toDouble(), Config.TILE_SIZE.toDouble())))
        player.addComponent(CollidableComponent(true))
        player.addComponent(CellMoveComponent(Config.TILE_SIZE, Config.TILE_SIZE, Vars.PLAYER_SPEED))
        player.addComponent(AStarMoveComponent(LazyValue { FXGL.getAppCast<ETCIApp>().getGrid() }))
        player.addComponent(PlayerComponent())
        player.addComponent(PlayerAnimationComponent())
        player.addComponent(PlayerChildViewComponent())
        player.addComponent(InventoryComponent())
        player.addComponent(ActionsComponent())
        player.zIndex = 100
        player.type = EntityType.PLAYER
        return player

    }

    @Spawns("Bound")
    fun newBounds(data: SpawnData): Entity {
        return entityBuilder()
            .at(data.x, data.y)
            .type(EntityType.BOUND)
            .bbox(HitBox(BoundingShape.box(Config.TILE_SIZE.toDouble(), Config.TILE_SIZE.toDouble())))
            .collidable()
            .build()
    }
    @Spawns("Nav")
    fun newNav(data: SpawnData): Entity {
        return entityBuilder()
            .at(data.x, data.y)
            .type(EntityType.NAV)
            .bbox(HitBox(BoundingShape.box(data.get("width"),data.get("height"))))
            .collidable()
            .build()
    }


    @Spawns("Bridge")
    fun newBridge(data: SpawnData): Entity {
        return entityBuilder()
            .at(data.x, data.y)
            .type(EntityType.BRIDGE)
            .bbox(HitBox(BoundingShape.box(data.get("width"),data.get("height"))))
            .collidable()
            .build()
    }


    //TODO: draft
    @Spawns("Chicken")
    fun newChicken(data: SpawnData): Entity {
        val chicken = NPCEntity()
        chicken.setPosition(data.x, data.y)
        chicken.localAnchor = Point2D(Config.TILE_SIZE.toDouble()/2.0, Config.TILE_SIZE.toDouble()/2.0)
        chicken.type = EntityType.CHICKEN
        chicken.boundingBoxComponent.addHitBox(HitBox(BoundingShape.box(Pets.CHICKEN.spriteSize.toDouble(), Pets.CHICKEN.spriteSize.toDouble())))
        chicken.addComponent(CollidableComponent(true))
        chicken.addComponent(CellMoveComponent(Config.TILE_SIZE, Config.TILE_SIZE, Vars.CHICKEN_SPEED))
        chicken.addComponent(AStarMoveComponent(LazyValue{FXGL.getAppCast<ETCIApp>().getGrid()}))
        chicken.addComponent(NPCAnimationComponenent(Pets.CHICKEN))
        chicken.addComponent(NPCComponent())
        chicken.setScaleUniform(0.5)
        return chicken
    }

    @Spawns("Tree")
    fun newTree(data: SpawnData): Entity {
        return entityBuilder()
            .at(data.x, data.y)
            .type(EntityType.TREE)
            .with(TreeAnimationComponent())
            .bbox(HitBox(BoundingShape.box(32.0, 32.0)))
            .collidable()
            .scaleOrigin(data.get<Double>("width")/32.0, data.get<Double>("height")/32.0)
            .build()
    }
}