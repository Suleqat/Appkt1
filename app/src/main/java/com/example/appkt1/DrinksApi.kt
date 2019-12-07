package com.example.appkt1

//******import data package here******
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DrinksApi {

    @GET("movies")
    suspend fun getDrinks() : Response<List<Drink>>


    companion object{
        operator fun invoke() : DrinksApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.simplifiedcoding.in/course-apis/recyclerview/")
                .build()
                .create(DrinksApi::class.java)
        }
    }
}