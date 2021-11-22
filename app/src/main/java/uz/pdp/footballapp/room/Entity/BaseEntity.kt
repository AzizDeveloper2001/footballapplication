package uz.pdp.footballapp.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class BaseEntity(
    @PrimaryKey
    val luid:String,
    val name:String,
    val logo:String,
    val  teamEntities:List<TeamEntity>
) : Serializable