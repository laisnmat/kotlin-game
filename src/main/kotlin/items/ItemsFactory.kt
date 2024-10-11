package com.ensimag.items

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.texture.Texture

enum class Actions(val texture: Texture, val description: String){
    HAND(
        texture("items/hand.png"),
        "Hand"
    ),
    AXE(
        texture("items/axe_enabled.png"),
        "Axe"
    ),
    SWORD(
        texture("items/sword.png"),
        "Sword"
    ),
    AXE_DISABLED(
        texture("items/axe_disabled.png"),
        "Axe Disabled"
    ),
    FOOD(
        texture("items/chicken_enabled1.png"),
        "Chicken"
    ),
    FOOD_DISABLED(
        texture("items/chicken_disabled.png"),
        "Food Disabled"
    ),
    POTIONS(
        texture("items/potion_enabled.png"),
        "Potion"
    ),
    POTIONS_DISABLED(
        texture("items/potion_disabled.png"),
        "Potion Disabled"
    ),
}

enum class Items(val image: Texture, val description: String, val goalValue: Int){
    WOOD(
        texture("items/wood.png"),
        "Wood",
        5
    ),
    NAIL(
        texture("items/nail.png"),
        "Nail",
        200
    ),
    STRING(
        texture("items/string.png"),
        "String",
        15
    ),
    TOOLS(
        texture("items/tools.png"),
        "Tools",
        1
    ),
    MANUAL(
        texture("items/manual.png"),
        "Manual",
        1
    ),
    MAP(
        texture("items/map.png"),
        "Map",
        1
    ),

}


