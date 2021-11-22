package uz.pdp.footballapp.repositories

import kotlinx.coroutines.flow.flow
import uz.pdp.footballapp.retrofit.ApiService
import uz.pdp.footballapp.room.Dao.BaseDao
import uz.pdp.footballapp.room.Dao.LeaugesDao
import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.room.Entity.Leauge
import java.util.concurrent.Flow

class FootballRepository(var apiService: ApiService,var leaugesDao: LeaugesDao,var baseDao: BaseDao) {

    suspend fun getleauges()=
        flow {emit(apiService.getLeauges())  }


    suspend fun getstandings( id:String)=
        flow {emit(apiService.getLeaugeTeams(id = id))  }

    suspend fun getbaseInformation()=
        flow { emit(baseDao.getAllStandings()) }

    suspend fun addLeaugesWithTeams(baseEntity: BaseEntity)=baseDao.insert(baseEntity)

}