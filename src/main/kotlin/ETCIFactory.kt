package com.ensimag

import apple.laf.JRSUIConstants.Hit
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import com.almasb.fxgl.physics.PhysicsComponent
import com.almasb.fxgl.physics.box2d.dynamics.BodyType
import com.ensimag.component.PlayerComponent
import com.ensimag.entity.PlayerEntity
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class ETCIFactory : EntityFactory{


    @Spawns("player")
    fun newPlayer(data: SpawnData): Entity {
        val physics = PhysicsComponent()
        physics.setBodyType(BodyType.DYNAMIC)

        val player  = FXGL.entityBuilder()
            .at(data.x, data.y)
            .type(EntityType.PLAYER)
            .viewWithBBox(Rectangle(8.0, 8.0, Color.BLUE))
            .bbox(HitBox(BoundingShape.box(8.0, 8.0)))
            .collidable()
            .with(PlayerComponent())
            .build()
        return player
    }
}