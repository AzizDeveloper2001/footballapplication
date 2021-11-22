package uz.pdp.footballapp.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.pdp.footballapp.models.Leauges



interface ApiService {
    @GET("leagues")
    suspend fun getLeauges():Response<Leauges>

    @GET("leagues/{id}/standings?")
    suspend fun getLeaugeTeams(
                       @Path("id") id:String,
                       @Query("season") season:String="2021",
                       @Query("sort") sort:String="asc"):Response<uz.pdp.footballapp.models.Response>


}