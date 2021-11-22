package uz.pdp.footballapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.pdp.footballapp.room.Dao.BaseDao
import uz.pdp.footballapp.room.Dao.LeaugesDao
import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.room.Entity.Leauge
import uz.pdp.footballapp.room.Entity.TeamEntity

@Database(entities = [BaseEntity::class,Leauge::class,TeamEntity::class],version = 2)
@TypeConverters(Converter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun leaugesDao():LeaugesDao
    abstract fun baseDao():BaseDao
    companion object{
        private var appDatabase:AppDatabase?=null
        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if(appDatabase==null){
                appDatabase= Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()


            }
            return appDatabase!!
        }
    }
}