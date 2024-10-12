package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.ensimag.component.Treasure.TreasureComponent

class TreasureEntity() : Entity() {
    val treasureComponent : TreasureComponent
        get() = getComponent(TreasureComponent::class.java)
    var isOpenable: Boolean
        get() = treasureComponent.isOpenable
        set(value) {treasureComponent.isOpenable = value}

}