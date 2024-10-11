package com.ensimag.events

import com.ensimag.entity.PlayerEntity
import com.ensimag.entity.TreeEntity
import com.almasb.fxgl.entity.Entity
import com.ensimag.entity.NPCEntity
import com.ensimag.events.Events.ON_ATTACK
import com.ensimag.events.Events.ON_CUTTING
import com.ensimag.events.Events.ON_DYING
import com.ensimag.events.Events.ON_EATING
import com.ensimag.events.Events.ON_HEALTH_VERY_LOW
import com.ensimag.events.Events.ON_HUNGRY_DAMAGE
import com.ensimag.events.Events.ON_INCREASING_HUNGER
import com.ensimag.events.Events.ON_OPENING_TREASURE
import com.ensimag.items.Items
import javafx.event.Event
import javafx.event.EventType

sealed class GameEvent(eventType: EventType<out GameEvent>) : Event(eventType)

object Events {
    val ON_DYING = EventType<OnDyingEvent>("ON_DYING")
    val ON_ATTACK = EventType<OnAttackEvent>("ON_ATTACK")
    val ON_INCREASING_HUNGER = EventType<OnIncreasingHungerEvent>("ON_INCREASING_HUNGER")
    val ON_EATING = EventType<OnEatingEvent>("ON_EATING")
    //val ON_BEING_HUNGRY = EventType<OnBeingHungryEvent>("ON_BEING_HUNGRY")
    val ON_HUNGRY_DAMAGE = EventType<OnHungryDamageEvent>("ON_HUNGRY_DAMAGE")
    val ON_HEALTH_VERY_LOW = EventType<OnHealthVeryLowEvent>("ON_HEALTH_VERY_LOW")
    val ON_CUTTING = EventType<OnCuttingEvent>("ON_CUTTING")
    val ON_OPENING_TREASURE = EventType<OnOpeningTreasure>("ON_OPENING_TREASURE")
    //val ON_ITEM_USED = EventType<OnItemUsedEvent>("ON_ITEM_USED")
}

class OnDyingEvent(
    val dyingEntity: PlayerEntity
) : GameEvent(ON_DYING)

class OnAttackEvent(
    val attackEntity: PlayerEntity,
    val attackedEntity: NPCEntity
) : GameEvent(ON_ATTACK)

class OnHealthVeryLowEvent(
    val dyingEntity: PlayerEntity
) : GameEvent(ON_HEALTH_VERY_LOW)

class OnIncreasingHungerEvent(
    val hungryEntity: PlayerEntity
) : GameEvent(ON_INCREASING_HUNGER)

class OnEatingEvent(
    val hungryEntity: PlayerEntity
) : GameEvent(ON_EATING)
/*
class OnBeingHungryEvent(
    val hungryEntity: PlayerEntity
) : GameEvent(ON_BEING_HUNGRY)
*/
class OnHungryDamageEvent(
    val hungryEntity: PlayerEntity
) : GameEvent(ON_HUNGRY_DAMAGE)

class OnCuttingEvent(
    val cutterEntity: PlayerEntity,
    val treeEntity: TreeEntity
) : GameEvent(ON_CUTTING)

class OnOpeningTreasure(
    val treasureEntity: Entity
) : GameEvent(ON_OPENING_TREASURE)


/*
class OnItemUsedEvent(
    val user: PlayerEntity,
    val item: Items
) : GameEvent(ON_I)*/