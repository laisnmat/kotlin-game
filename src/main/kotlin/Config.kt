package com.ensimag

import com.almasb.fxgl.logging.Logger
import javafx.util.Duration


object Config{
    val ANIMATION_DURATION: Duration = Duration.seconds(0.3)
    val PETS_ANIMATION_DURATION: Duration = Duration.seconds(1.2)
    const val SPRITE_SIZE_X: Int = 16
    const val SPRITE_SIZE_Y: Int = 32
    const val PETS_SPRITE_SIZE: Int = 32
    const val TEXTURE_CHARACTER: String = "character.png"
    const val TITLE = "Escape the Chicken Island"
    const val VERSION = "1.0"
    const val TILE_SIZE = 16
    private const val MAP_FOLDER = "tmx"
    private const val DEV_MAP = "MapFinal.tmx"
    const val MAP = "$MAP_FOLDER/$DEV_MAP"
    const val WIDTH = 1800
    const val HEIGHT = 1000
    const val ZOOM: Double = 3.0
}

object Vars{
    var MAP_HEIGHT: Int = 0
    var MAP_WIDTH: Int = 0
    var PLAYER_SPEED: Double = 100.0
    var CHICKEN_SPEED: Double = 20.0
    var PLAYER_MAX_HEALTH: Double = 10.0
    var PLAYER_MAX_HUNGER: Double = 10.0
    var NPC_IDLE_TIME: Double = 5.0
    var NPC_RANDOM_IDLE_TIME: Double = 10.0
    var DISTANCE_RANDOM_WALK: Double = 4.0
    var WIDTH_HEALTH_BAR: Double = 30.0
    var HEIGHT_HEALTH_BAR: Double = 8.0
    var TIME_HUNGER_INCREASE: Double = 8.0
    var TIME_HEALTH_DAMAGE: Double = 3.0
}

private val log = Logger.get("Main")

fun logInfo(message: String){
    log.info(message)
}

fun logWarning(message: String){
    log.warning(message)
}

fun logError(message: String){
    log.warning(message)
}