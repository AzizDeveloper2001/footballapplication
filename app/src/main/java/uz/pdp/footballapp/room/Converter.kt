package uz.pdp.footballapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.pdp.footballapp.room.Entity.TeamEntity


class Converter {
    @TypeConverter
    fun listtostirng(list:List<TeamEntity>):String{
       return Gson().toJson(list)
    }

    @TypeConverter
    fun stringtolist(string: String):List<TeamEntity>{
        val type=object :TypeToken<List<TeamEntity>>(){}.type
        return Gson().fromJson(string,type)
    }
}