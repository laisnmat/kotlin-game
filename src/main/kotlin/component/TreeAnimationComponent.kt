package com.ensimag.component

import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.Config
import com.ensimag.EntityType
import com.ensimag.items.Items
import com.ensimag.logInfo
import javafx.util.Duration

class TreeAnimationComponent(): Component(){

    private var isCuttable = true
    private val image = image("tree.png")

    private var treeAmation = AnimationChannel(image, 8, 32, 32, Duration.seconds(40.0), 0, 15)

    private val animatedTexture: AnimatedTexture = AnimatedTexture(treeAmation)

    init {
        animatedTexture.playFrom(15)
        animatedTexture.onCycleFinished = Runnable {
            isCuttable = true
        }
    }

    override fun onAdded() {
        entity.viewComponent.addChild(animatedTexture)
    }

    fun cut(){
        if(isCuttable){
            animatedTexture.play()
            isCuttable = false
            getGameWorld().getSingleton(EntityType.PLAYER).getComponent(InventoryComponent::class.java).add(Items.WOOD)
        }
    }

}
