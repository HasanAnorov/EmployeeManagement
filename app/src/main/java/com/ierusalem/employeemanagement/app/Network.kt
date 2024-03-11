package com.ierusalem.employeemanagement.app

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ierusalem.employeemanagement.features.auth.data.AuthorizationService
import com.ierusalem.employeemanagement.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(25000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .build()
}

fun provideConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonConverterFactory))
        .client(okHttpClient)
        .build()
}

fun provideAuthService(retrofit: Retrofit): AuthorizationService {
    return retrofit.create(AuthorizationService::class.java)
}

val networkModule = module {
    single { provideHttpClient(get()) }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideAuthService(get()) }
}

