package uz.pdp.footballapp.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TeamEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val abbreviation:String,
    val logo:String,
    val win:Int,
    val lose:Int,
    val draw:Int,
    val ga:Int,
    val gd:Int,
    val pts:Int
):Serializable
