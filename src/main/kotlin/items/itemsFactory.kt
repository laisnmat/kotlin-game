package com.ensimag.items

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.texture.Texture
import javafx.scene.image.Image

enum class Actions(val image: Texture, val description: String){
    HAND(
        FXGL.getAssetLoader().loadTexture("items/hand.png"),
        "Hand"
    ),
    AXE(
        FXGL.getAssetLoader().loadTexture("items/axe_enabled.png"),
        "Axe"
    ),
    SWORD(
        FXGL.getAssetLoader().loadTexture("items/sword.png"),
        "Sword"
    ),
    AXE_DISABLED(
        FXGL.getAssetLoader().loadTexture("items/axe_disabled.png"),
        "Axe Disabled"
    ),
    FOOD(
        FXGL.getAssetLoader().loadTexture("items/chicken_enabled1.png"),
        "Food"
    ),
    FOOD_DISABLED(
        FXGL.getAssetLoader().loadTexture("items/chicken_disabled.png"),
        "Food Disabled"
    ),
    POTIONS(
        FXGL.getAssetLoader().loadTexture("items/potion_enabled.png"),
        "Potion"
    ),
    POTIONS_DISABLED(
        FXGL.getAssetLoader().loadTexture("items/potion_disabled.png"),
        "Potion Disabled"
    ),
}

enum class Items(val image: Texture, val description: String, val goalValue: Int){
    WOOD(
        FXGL.getAssetLoader().loadTexture("items/wood.png"),
        "Wood",
        100
    ),
    NAIL(
        FXGL.getAssetLoader().loadTexture("items/nail.png"),
        "Nail",
        200
    ),
    STRING(
        FXGL.getAssetLoader().loadTexture("items/string.png"),
        "String",
        15
    ),
    TOOLS(
        FXGL.getAssetLoader().loadTexture("items/tools.png"),
        "Tools",
        1
    ),
    MANUAL(
        FXGL.getAssetLoader().loadTexture("items/manual.png"),
        "Manual",
        1
    ),
    MAP(
        FXGL.getAssetLoader().loadTexture("items/map.png"),
        "Map",
        1
    ),

}


