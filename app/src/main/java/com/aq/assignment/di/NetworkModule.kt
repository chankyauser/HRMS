package com.aq.assignment.di

import android.content.Context
import androidx.room.Room
import com.aq.assignment.BuildConfig
import com.aq.assignment.apicall.APIClient
import com.aq.assignment.helper.Constants
import com.aq.assignment.room.UserDao
import com.aq.assignment.room.UserDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class  NetworkModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
    @Provides
    @Singleton
    fun provideAPIClient(retrofit: Retrofit) = retrofit.create(APIClient::class.java)!!

    @Provides
    fun provideStudentDao(@ApplicationContext appContext: Context) : UserDao {
        return UserDatabase.getDatabase(appContext).userDao()
    }



}