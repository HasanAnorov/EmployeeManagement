package com.ierusalem.employeemanagement.features.auth.di

import com.ierusalem.employeemanagement.features.auth.data.AuthRepositoryImpl
import com.ierusalem.employeemanagement.features.auth.data.AuthorizationService
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.features.auth.presentation.LoginViewModel
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

//val authModule = module {
//    single { PreferenceHelper(get()) }
//
//    single<AuthRepository> {
//        AuthRepositoryImpl(preferences = get(), api = get())
//    }
//
//    viewModel { LoginViewModel(authRepository = get()) }
//}

val appModule = module {
    single { PreferenceHelper(get()) }
    //    single {
////        val client = OkHttpClient.Builder()
////            .addInterceptor(
////                ChuckerInterceptor.Builder(get())
////                    .collector(ChuckerCollector(get()))
////                    .maxContentLength(25000L)
////                    .redactHeaders(emptySet())
////                    .alwaysReadResponseBody(false)
////                    .build()
////            )
////            .build()
////        val gson = GsonBuilder()
////            .setLenient()
////            .create()
//        Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
////            .addConverterFactory(GsonConverterFactory.create(gson))
////            .client(client)
//            .build()
//            .create(AuthorizationService::class.java)
//    }
    fun provideApi(): AuthorizationService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(client)
            .build()
            .create(AuthorizationService::class.java)
    }
    single<AuthRepository> { AuthRepositoryImpl(preferences = get(),api =  provideApi()) }
    viewModel<LoginViewModel>{get()}
}
