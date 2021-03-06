package uz.pdp.footballapp.room.Dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface MainDao<T> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(entities: List<T>)

    @Update
    fun update(entity: T)

    @Update
    fun update(entities: List<T>)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(entities: List<T>)
}
