package com.ensimag.items

import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getNotificationService
import com.almasb.fxgl.dsl.getWorldProperties
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.texture.Texture
import com.ensimag.EntityType
import com.ensimag.entity.PlayerEntity
import com.ensimag.logInfo
import javafx.geometry.Point2D
import javafx.scene.Group
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment

class ActionConfig(
    val action: Actions,
    var isDisabled: Boolean = false,
    var isSelected: Boolean = false,
    val isDefault : Boolean = false,
    var quantity: Int = -1,
    var maxQuantity: Int = Int.MAX_VALUE
) {
    private var node : Group = Group()
    private lateinit  var background : Rectangle
    private lateinit  var text : Text
    private lateinit  var item : Texture
    private var itemDisabled : Texture ?= null
    private  var size : Double = 0.0
    private var gap : Double = 0.0
    private var borderWidth : Double = 0.0
    private lateinit var borderColor : Color
    private lateinit var backGroundColor : Color
    private lateinit var position : Point2D
    private var textSize : Double = 10.0


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
        if(isDisabled)
            setDisabled()
        else
            addListener()


        if(isDefault)
            select()
        return node
    }

    fun initBackground() : Rectangle {
        background = Rectangle(size, size)
        background.translateX = position.x
        background.fill = backGroundColor
        background.opacity = 0.6
        background.stroke = borderColor
        background.strokeWidth = borderWidth
        return background
    }

    fun initItem() : Texture {
        item = action.texture
        item.translateX = position.x + gap
        item.translateY = position.y + gap
        item.fitWidth = size - 2 * gap
        item.fitHeight = size - 2 * gap
        when (action) {
            Actions.FOOD -> {
                initDisabledItem(Actions.FOOD_DISABLED.texture)
            }
            Actions.POTIONS -> {
                initDisabledItem(Actions.POTIONS_DISABLED.texture)
            }
            Actions.AXE -> {
                initDisabledItem(Actions.AXE_DISABLED.texture)
            }
            else -> {}
        }


        return item
    }

    fun initDisabledItem(texture: Texture){
        itemDisabled = texture
        itemDisabled!!.translateX = position.x + gap
        itemDisabled!!.translateY = position.y + gap
        itemDisabled!!.fitWidth = size - 2 * gap
        itemDisabled!!.fitHeight = size - 2 * gap
    }

    fun initText() : Text {
        text = Text(if (quantity > 0) "$quantity" else "")
        text.x = item.x + item.translateX + gap
        text.y = item.y + size - 3* gap
        text.wrappingWidth = size - gap*2
        text.font = Font.font(textSize)
        text.fill = Color.web("white")
        text.textAlignment = TextAlignment.RIGHT
        return text
    }

    fun updateQuantity(newQuantity: Int) {
        quantity = newQuantity
    }

    fun incrementQuantity() : Boolean{
        if(quantity < maxQuantity){
            quantity++
            update()
            return true
        }
        else{
            getNotificationService().pushNotification("You can't have more than $maxQuantity ${action.description.lowercase()}")
            return false
        }
    }

    fun decrementQuantity() {
        if(quantity > 0){
            quantity--
        }
        update()
    }

    fun setDisabled() {
        isDisabled = true
        removeListener()
        node.children.remove(item)
        node.children.add(itemDisabled)
        text.text = ""
        if(isSelected){
            unSelect()
            (getGameWorld().getSingleton(EntityType.PLAYER) as PlayerEntity).actionsComponent.getActions().find { it.action == Actions.HAND }?.select()
        }
    }

    fun setEnabled() {
        isDisabled = false
        node.children.remove(itemDisabled)
        node.children.add(item)
        addListener()
    }

    fun select() {
        if(getWorldProperties().exists("selectedItem")){
            getWorldProperties().getValue<ActionConfig>("selectedItem").unSelect()
        }

        isSelected = true
        background.stroke = Color.RED
        background.strokeWidth = borderWidth + 2
        getWorldProperties().setValue("selectedItem", this)
    }

    fun unSelect() {
        isSelected = false
        background.stroke = borderColor
        background.strokeWidth = borderWidth
    }

    fun addListener() {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            if(isDisabled or isSelected) return@addEventHandler
            select()
        }
    }

    fun removeListener() {
        node.removeEventHandler(MouseEvent.MOUSE_CLICKED) {
            if(isDisabled or isSelected) return@removeEventHandler
            select()
        }
    }

    fun update() {
        if (quantity == 0) {
            setDisabled()
        } else {
            if (isDisabled) {
                setEnabled()
            }
            if(quantity > 0)
                text.text = "$quantity"
        }
    }

}