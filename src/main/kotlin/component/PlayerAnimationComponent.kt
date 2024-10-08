package com.ensimag.component

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.Config

class PlayerAnimationComponent() : AnimationComponent(){
        private val image = image(Config.TEXTURE_CHARACTER)

        override var channelIdleDown: AnimationChannel = AnimationChannel(image, 1, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 0, 0)
        override var channelIdleRight: AnimationChannel = AnimationChannel(image, 1, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 1, 1)
        override var channelIdleUp: AnimationChannel = AnimationChannel(image, 1, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 2, 2)
        override var channelIdleLeft: AnimationChannel = AnimationChannel(image, 1, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 3, 3)

        override var channelWalkDown: AnimationChannel = AnimationChannel(image, 4, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 0,  3)
        override var channelWalkRight: AnimationChannel = AnimationChannel(image, 4, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 4*1, 4*1 + 3)
        override var channelWalkUp: AnimationChannel = AnimationChannel(image, 4, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 4*2, 4*2 + 3)
        override var channelWalkLeft: AnimationChannel = AnimationChannel(image, 4, Config.SPRITE_SIZE_X, Config.SPRITE_SIZE_Y, Config.ANIMATION_DURATION, 4*3, 4*3 + 3)

        override var animatedTexture: AnimatedTexture = AnimatedTexture(channelIdleDown)

        init {
            animatedTexture.loop()
        }
}
