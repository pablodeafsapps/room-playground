package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "images_entity",
    indices = [Index("element_id", unique = true)],
    foreignKeys = [ForeignKey(
        ElementEntity::class,
        parentColumns = ["element_id"],
        childColumns = ["element_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "image_id") val imageId: String,
    @ColumnInfo(name = "element_id") val elementId: String,
    @ColumnInfo(name = "description") val description: String
)