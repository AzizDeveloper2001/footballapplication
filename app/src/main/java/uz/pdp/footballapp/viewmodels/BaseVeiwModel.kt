package uz.pdp.footballapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.pdp.footballapp.repositories.FootballRepository
import uz.pdp.footballapp.room.Entity.BaseEntity
import uz.pdp.footballapp.room.Entity.TeamEntity
import uz.pdp.footballapp.utils.NetworkHelper
import uz.pdp.footballapp.utils.Resource

class BaseVeiwModel(
                    var networkHelper: NetworkHelper,
                    var repository: FootballRepository):ViewModel(){

        fun fetchdata():StateFlow<Resource>{
            var stateflow= MutableStateFlow<Resource>(Resource.Loadding)
        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                repository.getleauges().catch {
                                    Resource.Error(it.message!!)
                }.collect {
                    if(it.isSuccessful){
                        var stateList=ArrayList<BaseEntity>()
                        if(it.body()?.data!=null){

                            it.body()?.data!!.forEach { leauge->

                                repository.getstandings(leauge.id).collect { res->
                                    if(res.isSuccessful){
                                        val teamlist=ArrayList<TeamEntity>()
                                        if(res.body()?.data?.standings!=null){
                                            res.body()?.data?.standings!!.forEach {
                                               var id= it.team.id
                                                var logo=""
                                                if(it.team.logos!=null){
                                                     logo= it.team.logos[0].href.toString()
                                                }

                                                var name=it.team.displayName
                                                var abr=""
                                                if(it.team.abbreviation!=null){
                                                     abr=it.team.abbreviation
                                                }

                                                var wins= it.stats?.get(0)!!.value
                                                var lose=it.stats[1].value
                                                var d=it.stats[2].value
                                                var ga=it.stats[5].value
                                                var pts=it.stats[6].value
                                                var gd=it.stats[9].value
                                                teamlist.add(TeamEntity(id!!,name,abr,logo,wins,lose,d,ga,gd,pts))

                                            }
                                        }

                                        var luid=leauge.id
                                        var name=leauge.abbr
                                        var logo=leauge.logos.light
                                        val baseEntity=(BaseEntity(luid,name,logo,teamlist))
                                        repository.addLeaugesWithTeams(baseEntity)
                                        stateList.add(baseEntity)
                                    }
                                }
                            }

                        }
                               stateflow.emit(Resource.Successs(stateList))
                    }
                }

            } else {
                repository.getbaseInformation().collect {
                    if(it.isEmpty()){
                        stateflow.emit(Resource.Error("No internet connection"))
                    } else {
                        stateflow.emit(Resource.Successs(it))
                    }

                }

            }

        }

          return stateflow
    }
}