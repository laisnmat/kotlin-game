package com.ensimag

import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getWorldProperties
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityWorldListener

class ETCIMap : EntityWorldListener{

    /**
     * Called after entity was added to the world.
     *
     * @param entity the entity
     */
    override fun onEntityAdded(entity: Entity?) {
        if (entity != null) {
            if(entity.type.equals(EntityType.CHICKEN)){
                getWorldProperties().increment("nbChicken", 1)
                logInfo("Chicken added. NbChicken = ${getWorldProperties().getInt("nbChicken")}")
            }
            if (entity.type.equals(EntityType.NAV)){
                    logInfo("Island ${entity.properties.getInt("island_id")} added.")

            }
        }
    }

    /**
     * Called after entity was removed from the world
     * but before entity has been cleaned.
     * This allows other parties to free resources before
     * entity clean.
     *
     * @param entity the entity
     */
    override fun onEntityRemoved(entity: Entity?) {
        if (entity != null) {
            if(entity.type.equals(EntityType.CHICKEN)){
                getWorldProperties().increment("nbChicken", -1)
                logInfo("Chicken removed. NbChicken = ${getWorldProperties().getInt("nbChicken")}")
            }
        }
    }


}