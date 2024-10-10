package com.ensimag.events

import com.almasb.fxgl.dsl.onEvent

object EventHandlers {

    fun initialize(){
        onEvent(Events.ON_DYING){
            val playerEntity = it.dyingEntity
            playerEntity.playerComponent.dying()
        }
        onEvent(Events.ON_INCREASING_HUNGER){
            val playerEntity = it.hungryEntity
            playerEntity.playerComponent.hungerBarDecrease()
        }
        onEvent(Events.ON_EATING){
            val playerEntity = it.hungryEntity
            playerEntity.playerComponent.eating()
        }
        /*onEvent(Events.ON_BEING_HUNGRY){
            val playerEntity = it.hungryEntity
        }*/
        onEvent(Events.ON_HUNGRY_DAMAGE){
            val playerEntity = it.hungryEntity
            playerEntity.playerComponent.hpDamage()
        }
    }

}