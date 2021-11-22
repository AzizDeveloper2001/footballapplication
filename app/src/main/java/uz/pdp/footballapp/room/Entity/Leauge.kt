package uz.pdp.footballapp.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Leauge(
    @PrimaryKey
    val id:String,
    val avatar:String,
    val name:String
)
