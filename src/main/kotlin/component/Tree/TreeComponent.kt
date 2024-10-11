package com.ensimag.component.Tree

import com.almasb.fxgl.entity.component.Component
import com.ensimag.entity.TreeEntity
import com.ensimag.gameplay.Gameplay
import com.ensimag.items.Items

class TreeComponent : Component() {
    private lateinit var treeEntity : TreeEntity
    var isCuttable = true

    override fun onAdded() {
        treeEntity = entity as TreeEntity
    }

    fun cut(){
        if(isCuttable){
            treeEntity.animationComponent.playAnimation()
            isCuttable = false
            Gameplay.addItem(Items.WOOD)
        }
    }

}