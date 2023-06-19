package com.example.converterapp.network

import android.os.Build
import android.util.Log
import com.example.converterapp.common.Constant.BASE_URL
import com.example.converterapp.currency_converter.data.remote.CurrencyConverterAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{

        val readTimeout = 17L
        val connectTimeout = 17L
        val writeTimeout = 17L

        val builder =  OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor {
            Log.v("TAG", it)
        }

        logging.level = HttpLoggingInterceptor.Level.BODY

         return builder .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            //.addInterceptor(CurlLoggerInterceptor("CurlRequest: "))
            //.addInterceptor(getHeaderInterceptor())
          //  .addInterceptor(getApiErrorInterceptor(context, logApiErrorOnServer))
          //  .addInterceptor(AuthInterceptor(context))
          //  .addInterceptor(retryInterceptor)
            .build()
    }


    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->

            var request = chain.request()
            request = request.newBuilder()
                .header("platform", "android")
                //.header("Authorization", authToken)
                //.header("client-secret", clientSecret)
                //.header("client-id", clientId)
                .header("device-brand", Build.MANUFACTURER)
                .build()
            val response = chain.proceed(request)
            response
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(provideOkHttpClient(context, forAppModule)).build()
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApiService(retrofit: Retrofit): CurrencyConverterAPI {
        return retrofit.create(CurrencyConverterAPI::class.java)
    }
}