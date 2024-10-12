package com.ensimag.component.Treasure

import com.almasb.fxgl.dsl.random
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.Texture
import com.ensimag.entity.TreasureEntity
import com.ensimag.gameplay.Gameplay
import javafx.geometry.Rectangle2D

class TreasureComponent() : Component() {
    private lateinit var treasureEntity : TreasureEntity
    private val texture : Texture = texture("items/chest.png")
    var isOpenable = true

    override fun onAdded() {
        treasureEntity = entity as TreasureEntity
        close()
    }
    fun close(){
        treasureEntity.viewComponent.clearChildren()
        val subTexture = texture.subTexture(Rectangle2D(0.0, 0.0, 32.0, 32.0))
        subTexture.fitWidth = 16.0
        subTexture.fitHeight = 16.0
        treasureEntity.viewComponent.addChild(subTexture)
        isOpenable = true
    }

    fun open(){
        if(isOpenable){
            treasureEntity.viewComponent.clearChildren()
            val subTexture = texture.subTexture(Rectangle2D(32.0, 0.0, 32.0, 32.0))
            subTexture.fitWidth = 16.0
            subTexture.fitHeight = 16.0
            treasureEntity.viewComponent.addChild(subTexture)
            isOpenable = false
            Gameplay.addRandomItem()
        }
    }

    override fun onUpdate(tpf: Double) {
        if(!isOpenable){
            if(random(0, 1000) == 0){
                close()
            }
        }
    }
}