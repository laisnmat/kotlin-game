package com.ensimag.events

import com.ensimag.entity.PlayerEntity
import com.ensimag.events.Events.ON_ATTACK
import com.ensimag.events.Events.ON_DYING
import com.ensimag.events.Events.ON_EATING
import com.ensimag.events.Events.ON_HEALTH_VERY_LOW
import com.ensimag.events.Events.ON_HUNGRY_DAMAGE
import com.ensimag.events.Events.ON_INCREASING_HUNGER
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
    //val ON_ITEM_USED = EventType<OnItemUsedEvent>("ON_ITEM_USED")
}

class OnDyingEvent(
    val dyingEntity: PlayerEntity
) : GameEvent(ON_DYING)

class OnAttackEvent(
    val attackEntity: PlayerEntity
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

/*
class OnItemUsedEvent(
    val user: PlayerEntity,
    val item: Items
) : GameEvent(ON_I)*/