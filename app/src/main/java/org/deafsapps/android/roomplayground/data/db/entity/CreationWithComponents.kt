package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation

data class CreationWithComponents(
    @Embedded val creation: CreationEntity,
    @Relation(
        entity = ComponentEntity::class,
        parentColumn = "creation_id",
        entityColumn = "creation_id"
    )
    val componentsAndElements: List<ComponentWithElements>
)

data class ComponentWithElements(
    @Embedded val component: ComponentEntity,
    @Relation(
        parentColumn = "component_id",
        entityColumn = "component_id"
    )
    val elements: List<ElementEntity>
)
