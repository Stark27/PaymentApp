package com.example.luismunoz.paymentapp.di

import com.example.luismunoz.paymentapp.BuildConfig
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.BankService
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.FeeService
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.PaymentMethodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor = AuthorizationInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePaymentMethodService(retrofit: Retrofit): PaymentMethodService {
        return retrofit.create(PaymentMethodService::class.java)
    }

    @Singleton
    @Provides
    fun provideBankService(retrofit: Retrofit): BankService {
        return retrofit.create(BankService::class.java)
    }

    @Singleton
    @Provides
    fun provideFeeService(retrofit: Retrofit): FeeService {
        return retrofit.create(FeeService::class.java)
    }

}