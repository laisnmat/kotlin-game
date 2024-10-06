package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.dsl.components.HealthDoubleComponent
import com.ensimag.entity.PlayerEntity

class PlayerComponent: Component() {
    lateinit var player: PlayerEntity

    /**
     * Called after the component is added to entity.
     */
    override fun onAdded() {
        super.onAdded()
    }

    /**
     * Called each frame when not paused.
     *
     * @param tpf time per frame
     */
    override fun onUpdate(tpf: Double) {
        super.onUpdate(tpf)
    }

    /**
     * Called before the component is removed from entity.
     */
    override fun onRemoved() {
        super.onRemoved()
    }

    val hp = HealthDoubleComponent(1.0)

    var isDie = false
        private set


    fun move(x: Double, y: Double) {
        player.translate(x, y)
    }
}