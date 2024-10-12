package com.ensimag.items

import com.almasb.fxgl.dsl.fire
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getNotificationService
import com.almasb.fxgl.dsl.getWorldProperties
import com.almasb.fxgl.texture.Texture
import com.ensimag.EntityType
import com.ensimag.entity.PlayerEntity
import com.ensimag.events.OnItemCompletedEvent
import com.ensimag.logInfo
import javafx.geometry.Point2D
import javafx.scene.Group
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment

class ItemConfig(
    val item: Items,
    val maxQuantity: Int,
    var quantity: Int = 0
) {
    private var node : Group = Group()
    private lateinit  var background : Rectangle
    private lateinit  var text : Text
    private lateinit  var texture : Texture
    private  var size : Double = 0.0
    private var gap : Double = 0.0
    private var borderWidth : Double = 0.0
    private lateinit var borderColor : Color
    private lateinit var backGroundColor : Color
    private lateinit var position : Point2D
    private var textSize : Double = 13.0


    fun initView(position: Point2D, size: Double, gap: Double, borderWidth: Double, borderColor: Color, backGroundColor: Color) : Group {
        this.size = size
        this.gap = gap
        this.borderWidth = borderWidth
        this.borderColor = borderColor
        this.position = position
        this.backGroundColor = backGroundColor

        node.children.add(initBackground())
        node.children.add(initItem())
        node.children.add(initText())
        update()
        return node
    }

    fun initBackground() : Rectangle {
        background = Rectangle(size, size)
        background.x = position.x
        background.y = position.y
        background.arcWidth = 10.0
        background.arcHeight = 10.0
        background.x = position.x
        background.fill = backGroundColor
        background.opacity = 0.7
        background.stroke = borderColor
        background.strokeWidth = borderWidth
        return background
    }

    fun initItem() : Texture {
        texture = item.image
        texture.x = background.x + size * 0.1
        texture.y = background.y + size * 0.1
        texture.fitWidth = 0.8 * size
        texture.fitHeight = 0.8 * size
        return texture
    }

    fun initText() : Text {
        text = Text("$quantity/$maxQuantity")
        text.x = background.x + gap
        text.y = background.y + size - gap
        text.wrappingWidth = size - gap * 2
        text.font = Font.font(textSize)
        text.fill = Color.WHITE
        text.textAlignment = TextAlignment.RIGHT
        return text
    }

    fun updateQuantity(newQuantity: Int) {
        quantity = newQuantity
    }

    fun incrementQuantity(amount: Int = 1){
        if(quantity < maxQuantity && quantity + amount >= maxQuantity){
            quantity += amount
            fire(OnItemCompletedEvent(getGameWorld().getSingleton(EntityType.PLAYER) as PlayerEntity))
        }
        else{
            quantity += amount
        }
        update()
    }

    fun decrementQuantity() {
        if(quantity > 0){
            quantity--
        }
        update()
    }

    fun isFull() : Boolean {
        logInfo("${item.name} is full: ${quantity >= maxQuantity} ($quantity/$maxQuantity)")
        return quantity >= maxQuantity
    }

    fun update() {
        text.text = "$quantity/$maxQuantity"
        if(quantity >= maxQuantity){
            text.fill = Color.GREEN
            background.stroke = Color.GREEN
        } else {
            text.fill = Color.RED
            background.stroke = Color.RED
        }
    }

}