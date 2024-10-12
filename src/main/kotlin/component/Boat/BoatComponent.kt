package com.ensimag.component.Boat

import com.almasb.fxgl.entity.component.Component
import com.ensimag.entity.BoatEntity

class BoatComponent : Component() {
    private lateinit var boatEntity : BoatEntity

    override fun onAdded() {
        boatEntity = entity as BoatEntity
    }

}