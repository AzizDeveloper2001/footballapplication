package uz.pdp.footballapp.retrofit

import okio.GzipSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
val BASE_URL="https://api-football-standings.azharimm.site/"
    fun getretrofi():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService= getretrofi().create(ApiService::class.java)
}