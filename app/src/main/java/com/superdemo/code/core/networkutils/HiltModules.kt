package com.superdemo.code.core.networkutils


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.superdemo.code.BuildConfig
import com.superdemo.code.core.utils.Constants
import com.superdemo.code.core.utils.InternetChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
object HiltModules {

    external fun GetToken(): String
    external fun GetTestBaseUrl(): String
    external fun GetLiveBaseUrl(): String

    init {
        System.loadLibrary("keys")
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun okHttpProvider(provideLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor {
                    val builder = it.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    builder.header("Token", GetToken())
                    builder.header("AccessToken", Constants.AT)
                    return@Interceptor it.proceed(builder.build())
                }
            )
            .addInterceptor(provideLoggingInterceptor)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(
            if (BuildConfig.DEBUG) {
                if (BuildConfig.FLAVOR == "dev") {
                    GetTestBaseUrl()
                } else {
                    GetLiveBaseUrl()
                }
            } else {
                GetLiveBaseUrl()
            }
        )
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    fun providesInternetChecker(@ApplicationContext context: Context): InternetChecker =
        InternetChecker(context)
}