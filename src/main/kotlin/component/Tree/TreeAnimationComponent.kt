package com.ensimag.component.Tree

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.ensimag.entity.TreeEntity
import javafx.util.Duration

class TreeAnimationComponent(): Component(){
    private lateinit var treeEntity: TreeEntity

    private val image = image("tree.png")

    private var treeAnimation = AnimationChannel(image, 8, 32, 32, Duration.seconds(15.0), 0, 15)

    private val animatedTexture: AnimatedTexture = AnimatedTexture(treeAnimation)


    init {
        animatedTexture.playFrom(15)
        animatedTexture.onCycleFinished = Runnable {
            treeEntity.isCuttable = true
        }
    }

    override fun onAdded() {
        treeEntity = entity as TreeEntity
        entity.viewComponent.addChild(animatedTexture)
    }

    fun playAnimation(){
        animatedTexture.play()
    }
}
