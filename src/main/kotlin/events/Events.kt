package com.ensimag.events

import com.ensimag.entity.PlayerEntity
import com.ensimag.events.Events.ON_DYING
import com.ensimag.events.Events.ON_EATING
import com.ensimag.events.Events.ON_HUNGRY_DAMAGE
import com.ensimag.events.Events.ON_INCREASING_HUNGER
import javafx.event.Event
import javafx.event.EventType

sealed class GameEvent(eventType: EventType<out GameEvent>) : Event(eventType)

object Events {
    val ON_DYING = EventType<OnDyingEvent>("ON_DYING")
    val ON_INCREASING_HUNGER = EventType<OnIncreasingHungerEvent>("ON_INCREASING_HUNGER")
    val ON_EATING = EventType<OnEatingEvent>("ON_EATING")
    //val ON_BEING_HUNGRY = EventType<OnBeingHungryEvent>("ON_BEING_HUNGRY")
    val ON_HUNGRY_DAMAGE = EventType<OnHungryDamageEvent>("ON_HUNGRY_DAMAGE")
}

class OnDyingEvent(
    val dyingEntity: PlayerEntity
) : GameEvent(ON_DYING)

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