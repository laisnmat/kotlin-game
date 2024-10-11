package com.ensimag.component

import com.almasb.fxgl.entity.component.Component
import com.ensimag.items.Actions
import com.ensimag.items.Items

class ActionsComponent : Component() {
    private val actions = mutableListOf<Actions>()
    var nbFood = 0
    var nbPotions = 0

    init {
        actions.add(Actions.HAND)
        actions.add(Actions.SWORD)
        actions.add(Actions.AXE)
        actions.add(Actions.FOOD_DISABLED)
        actions.add(Actions.POTIONS_DISABLED)
    }

    fun addAction(action: Actions) {
        actions.add(action)
    }

    fun removeAction(action: Actions) {
        actions.remove(action)
    }

    fun getActions(): List<Actions> {
        return actions
    }


    fun addFood() {
        if(actions.contains(Actions.FOOD_DISABLED)){
            actions.remove(Actions.FOOD_DISABLED)
            actions.add(3, Actions.FOOD)
        }
        nbFood++
    }

    fun addPotions() {
        if(actions.contains(Actions.POTIONS_DISABLED)){
            actions.remove(Actions.POTIONS_DISABLED)
            actions.add(Actions.POTIONS)
        }
        nbPotions++
    }

    fun removeFood() {
        if(nbFood > 0){
            nbFood--
        }
        if(nbFood == 0){
            actions.remove(Actions.FOOD)
            actions.add(3, Actions.FOOD_DISABLED)
        }
    }

    fun removePotions() {
        if(nbPotions > 0){
            nbPotions--
        }
        if(nbPotions == 0){
            actions.remove(Actions.POTIONS)
            actions.add(Actions.POTIONS_DISABLED)
        }

    }

    fun isFoodEmpty(): Boolean {
        return nbFood == 0
    }

    fun isPotionsEmpty(): Boolean {
        return nbPotions == 0
    }

    fun clearFood() {
        nbFood = 0
    }

    fun clearPotions() {
        nbPotions = 0
    }



    fun clearActions() {
        actions.clear()
    }

}