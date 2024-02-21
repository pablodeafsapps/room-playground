package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CreationWithComponents(
    @Embedded val creation: CreationEntity,
    @Relation(
        entity = ComponentEntity::class,
        parentColumn = "creation_id",
        entityColumn = "creation_id"
    )
    val componentsWithElements: List<ComponentWithElements>
)

data class ComponentWithElements(
    @Embedded val component: ComponentEntity,
    @Relation(
        entity = ElementEntity::class,
        parentColumn = "component_id",
        entityColumn = "component_id"
    )
    val elementsWithImages: List<ElementWithImages>
)

data class ElementWithImages(
    @Embedded val element: ElementEntity,
    @Relation(
        parentColumn = "element_id",
        entityColumn = "element_id"
    )
    val images: List<ImageEntity>
)
