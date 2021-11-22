package uz.pdp.footballapp.room.Dao

import androidx.room.Dao
import androidx.room.Query
import uz.pdp.footballapp.room.Entity.Leauge

@Dao
interface LeaugesDao:MainDao<Leauge> {
    @Query("select * from leauge")
    fun getalleauges():List<Leauge>

}