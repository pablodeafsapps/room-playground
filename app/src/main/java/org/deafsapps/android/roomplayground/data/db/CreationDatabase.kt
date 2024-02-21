package org.deafsapps.android.roomplayground.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.deafsapps.android.roomplayground.data.db.entity.ComponentEntity
import org.deafsapps.android.roomplayground.data.db.entity.CreationEntity
import org.deafsapps.android.roomplayground.data.db.entity.ElementEntity
import org.deafsapps.android.roomplayground.data.db.entity.ImageEntity

@Database(
    entities = [CreationEntity::class, ComponentEntity::class, ElementEntity::class, ImageEntity::class],
    version = 1
)
abstract class CreationDatabase : RoomDatabase() {
    abstract fun creationDao(): CreationDao
}