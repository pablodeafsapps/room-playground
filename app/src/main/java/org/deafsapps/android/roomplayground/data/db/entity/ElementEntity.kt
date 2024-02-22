package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "elements_entity",
    indices = [Index("component_id"), Index("element_id", unique = true)],
    foreignKeys = [ForeignKey(
        ComponentEntity::class,
        parentColumns = ["component_id"],
        childColumns = ["component_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class ElementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "element_id") val elementId: String,
    @ColumnInfo(name = "component_id") val componentId: String,
    @ColumnInfo(name = "description") val description: String
)