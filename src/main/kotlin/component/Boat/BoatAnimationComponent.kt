package com.ensimag.component.Boat

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.entity.BoatEntity
import com.ensimag.entity.TreeEntity
import javafx.util.Duration

class BoatAnimationComponent(): Component(){
    private lateinit var boatEntity: BoatEntity

    private val image = image("boat.png")

    private var boatAnimation = AnimationChannel(image, 4, 163, 224, Duration.seconds(2.0), 4, 7)

    private val animatedTexture: AnimatedTexture = AnimatedTexture(boatAnimation)


    init {
        animatedTexture.loop()
    }

    override fun onAdded() {
        boatEntity = entity as BoatEntity
        entity.viewComponent.addChild(animatedTexture)
    }

    fun playAnimation(){
        animatedTexture.loop()
    }
}
