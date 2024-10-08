package com.ensimag.component

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.dsl.random
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.Config
import com.ensimag.npc.Pets

class NPCAnimationComponenent(texture: Pets) : AnimationComponent() {
    val walk = image(texture.walk)
    val idle = image(texture.eat)

    override var channelIdleDown: AnimationChannel = AnimationChannel(idle, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*2, 4*2 + 3)
    override var channelIdleLeft: AnimationChannel = AnimationChannel(idle, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*1, 4*1 + 3)
    override var channelIdleUp: AnimationChannel = AnimationChannel(idle, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 0, 3)
    override var channelIdleRight: AnimationChannel = AnimationChannel(idle, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*3, 4*3 + 3)

    override var channelWalkDown: AnimationChannel = AnimationChannel(walk, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*2, 4*2 + 3)
    override var channelWalkLeft: AnimationChannel = AnimationChannel(walk, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*1, 4*1 + 3)
    override var channelWalkUp: AnimationChannel = AnimationChannel(walk, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 0,  3)
    override var channelWalkRight: AnimationChannel = AnimationChannel(walk, 4, texture.spriteSize, texture.spriteSize, Config.PETS_ANIMATION_DURATION, 4*3, 4*3 + 3)

    override var animatedTexture: AnimatedTexture = AnimatedTexture(channelIdleDown)

    init {
        animatedTexture.loop()
    }

    override fun onUpdate(tpf: Double) {
        randomIdleAnimation()
    }

    fun randomIdleAnimation(){
        if((0..100).random() == 0){

            var randomIdle = (0..3).random()
            when(randomIdle){
                0 -> animatedTexture.loopNoOverride(channelIdleDown)
                1 -> animatedTexture.loopNoOverride(channelIdleLeft)
                2 -> animatedTexture.loopNoOverride(channelIdleUp)
                3 -> animatedTexture.loopNoOverride(channelIdleRight)
            }
        }
    }
}