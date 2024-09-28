package com.ensimag.view

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.texture.Texture
import javafx.geometry.Rectangle2D


class MyTexture{


    // set texture
    private val texture: Texture = FXGL.getAssetLoader().loadTexture("tileset.png");

    fun getTexture(): Texture{
        return texture
    }

    fun subTexture(x: Double, y: Double, width: Double, height: Double): Texture{
        return texture.subTexture(Rectangle2D(x, y, width, height))
    }

    fun get(n: Int): Texture{
        val x: Double = (n%21).toDouble()
        val y: Double = (n/21).toDouble()
        return texture.subTexture(Rectangle2D(x*16, y*16, 16.0, 16.0))
    }

    fun draw(n: Int, x: Double, y: Double){
        val t: Texture = get(n)
        FXGL.entityBuilder().at(x, y).view(t).buildAndAttach()
    }
}