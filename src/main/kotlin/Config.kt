package com.ensimag

import com.almasb.fxgl.logging.Logger


object Config{
    const val TITLE = "Escape the Chicken Island"
    const val VERSION = "1.0"
    const val TILE_SIZE = 16
    const val MAP_FOLDER = "tmx/"
    const val DEV_MAP = "devmap.tmx"
    const val MAP = "$MAP_FOLDER/$DEV_MAP"
    const val WIDTH = 800
    const val HEIGHT = 600
    const val ZOOM: Double = 3.0

}

object Vars{
    var MAP_HEIGHT: Int = 0
    var MAP_WIDTH: Int = 0
    var PLAYER_SPEED: Double = 1.0
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