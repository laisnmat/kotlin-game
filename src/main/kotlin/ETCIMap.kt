package com.ensimag

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityWorldListener

class ETCIMap : EntityWorldListener{
    /**
     * Called after entity was added to the world.
     *
     * @param entity the entity
     */
    override fun onEntityAdded(entity: Entity?) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }


}