package uz.pdp.footballapp.room.Dao

import androidx.room.Dao
import androidx.room.Query
import uz.pdp.footballapp.room.Entity.BaseEntity

@Dao
interface BaseDao:MainDao<BaseEntity> {

    @Query("select * from baseentity")
    fun getAllStandings():List<BaseEntity>

}