package org.deafsapps.android.roomplayground.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import org.deafsapps.android.roomplayground.data.db.entity.ComponentEntity
import org.deafsapps.android.roomplayground.data.db.entity.CreationEntity
import org.deafsapps.android.roomplayground.data.db.entity.CreationWithComponents
import org.deafsapps.android.roomplayground.data.db.entity.ElementEntity
import org.deafsapps.android.roomplayground.data.db.entity.ImageEntity

@Dao
interface CreationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCreation(creation: CreationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComponent(component: ComponentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveElement(element: ElementEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveElements(elements: List<ElementEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImages(images: List<ImageEntity>): List<Long>

    @Update
    suspend fun updateCreation(creation: CreationEntity): Int

    @Query("SELECT * FROM creations_table")
    @Transaction
    suspend fun getCreations(): List<CreationWithComponents>?

    @Query("DELETE FROM creations_table WHERE creation_id = :creationId")
    suspend fun deleteCreation(creationId: String): Int
}