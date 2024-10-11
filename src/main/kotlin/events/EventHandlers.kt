package com.ensimag.events

import com.almasb.fxgl.dsl.getEventBus
import com.almasb.fxgl.dsl.onEvent
import com.ensimag.component.TreeAnimationComponent
import com.ensimag.gameplay.Gameplay
import com.ensimag.items.Actions

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
            if(playerEntity.hasFood()){
                playerEntity.playerComponent.eating()
            }

        }
        /*onEvent(Events.ON_BEING_HUNGRY){
            val playerEntity = it.hungryEntity
        }*/
        onEvent(Events.ON_HUNGRY_DAMAGE){
            val playerEntity = it.hungryEntity
            playerEntity.playerChildViewComponent.hpDamage()
            playerEntity.playerComponent.hpDamage()
        }
        onEvent(Events.ON_HEALTH_VERY_LOW){
            val playerEntity = it.dyingEntity
            playerEntity.playerChildViewComponent.healthVeryLow()
        }
        onEvent(Events.ON_ATTACK){
            val playerEntity = it.attackEntity
            val npcEntity = it.attackedEntity
            //TODO: Ajoute de la bouffe dans inventaire action + met à jour la vue

            if(playerEntity.actionsComponent.increaseQuantity(Actions.FOOD) == true){
                Gameplay.removeFromWorld(npcEntity)
            }

        }
        onEvent(Events.ON_CUTTING){
            val playerEntity = it.cutterEntity
            val treeEntity = it.treeEntity
            treeEntity.getComponent(TreeAnimationComponent::class.java).cut()
        }
    }

}