package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "components_table",
    indices = [Index("component_id", unique = true)],
    foreignKeys = [ForeignKey(
        CreationEntity::class,
        parentColumns = ["creation_id"],
        childColumns = ["creation_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class ComponentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "component_id") val componentId: String,
    @ColumnInfo(name = "creation_id") val creationId: String,
    @ColumnInfo(name = "description") val description: String,
)