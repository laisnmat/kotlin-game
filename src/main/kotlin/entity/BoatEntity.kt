package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.components.CollidableComponent
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import com.ensimag.EntityType
import com.ensimag.component.Boat.BoatAnimationComponent
import com.ensimag.component.Boat.BoatComponent

class BoatEntity(val data: SpawnData) : Entity() {

    val animationComponent: BoatAnimationComponent
        get() = getComponent(BoatAnimationComponent::class.java)
    val boatComponent: BoatComponent
        get() = getComponent(BoatComponent::class.java)

    init {
        this.type = EntityType.BOAT
    }

    fun display(){
        this.setPosition(data.x, data.y)
        this.boundingBoxComponent.addHitBox(HitBox(BoundingShape.box(data.get("width"),data.get<Double>("height") + 20)))
        this.addComponent(CollidableComponent(true))
        this.addComponent(BoatAnimationComponent())
        this.addComponent(BoatComponent())
    }

}