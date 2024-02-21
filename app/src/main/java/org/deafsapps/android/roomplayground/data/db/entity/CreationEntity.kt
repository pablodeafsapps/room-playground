package org.deafsapps.android.roomplayground.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "creations_table",
    indices = [Index("creation_id", unique = true)]
)
data class CreationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "creation_id") val creationId: String,
    @ColumnInfo(name = "description") val description: String
)