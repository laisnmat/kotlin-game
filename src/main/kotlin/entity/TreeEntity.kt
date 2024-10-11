package com.ensimag.entity

import com.almasb.fxgl.entity.Entity
import com.ensimag.component.Tree.TreeAnimationComponent
import com.ensimag.component.Tree.TreeComponent
import com.ensimag.logInfo

class TreeEntity : Entity() {
    val animationComponent: TreeAnimationComponent
        get() = getComponent(TreeAnimationComponent::class.java)
    val treeComponent: TreeComponent
        get() = getComponent(TreeComponent::class.java)
    var isCuttable: Boolean
        get() = treeComponent.isCuttable
        set(value) { treeComponent.isCuttable = value }


}