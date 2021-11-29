package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//URL BASE DE ONDE IREMOS TIRAR OS DADOS
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

//INSTÂNCIA DA BIBLIOTECA MOSHI
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

//INSTÂNCIA DO RETROFIT
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()

//INTERFACE QUE DIZ O QUE A NOSSA API IRÁ FAZER
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

//DECLARAÇÃO DA API EM UM SINGLETON PARA QUE ESTÁ POSSA SER ACESSADA DE FORMA GLOBAL.
object MarsApi{
    val retrofitService:MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
}