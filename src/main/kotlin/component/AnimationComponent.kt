package com.ensimag.component

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.Config

abstract class AnimationComponent : Component() {
    abstract var animatedTexture: AnimatedTexture

    abstract var channelIdleDown: AnimationChannel
    abstract var channelIdleRight: AnimationChannel
    abstract var channelIdleUp: AnimationChannel
    abstract var channelIdleLeft: AnimationChannel

    abstract var channelWalkDown: AnimationChannel
    abstract var channelWalkRight: AnimationChannel
    abstract var channelWalkUp: AnimationChannel
    abstract var channelWalkLeft: AnimationChannel



    /**
     * Called after the component is added to entity.
     */
    override fun onAdded() {
        entity.viewComponent.addChild(animatedTexture)
    }

    /**
     * Called each frame when not paused.
     *
     * @param tpf time per frame
     */
    override fun onUpdate(tpf: Double) {
        super.onUpdate(tpf)
    }

    internal val isFacingUp: Boolean
        get() = isIn(channelIdleUp, channelWalkUp)

    internal val isFacingDown: Boolean
        get() = isIn(channelIdleDown, channelWalkDown)

    internal val isFacingRight: Boolean
        get() = isIn(channelIdleRight, channelWalkRight)

    internal val isFacingLeft: Boolean
        get() = isIn(channelIdleLeft, channelWalkLeft)

    private fun isIn(vararg channels: AnimationChannel) = channels.any { it === animatedTexture.animationChannel }

    fun loopIdleUp() {
        animatedTexture.loopNoOverride(channelIdleUp)
    }

    fun loopIdleDown() {
        animatedTexture.loopNoOverride(channelIdleDown)
    }

    fun loopIdleRight() {
        animatedTexture.loopNoOverride(channelIdleRight)
    }

    fun loopIdleLeft() {
        animatedTexture.loopNoOverride(channelIdleLeft)
    }

    fun loopWalkUp() {
        animatedTexture.loopNoOverride(channelWalkUp)
    }

    fun loopWalkDown() {
        animatedTexture.loopNoOverride(channelWalkDown)
    }

    fun loopWalkRight() {
        animatedTexture.loopNoOverride(channelWalkRight)
    }

    fun loopWalkLeft() {
        animatedTexture.loopNoOverride(channelWalkLeft)
    }

    fun playDeath(onFinished: Runnable) {
        //animatedTexture.playAnimationChannel(channelDeath)
        animatedTexture.onCycleFinished = onFinished

    }


}