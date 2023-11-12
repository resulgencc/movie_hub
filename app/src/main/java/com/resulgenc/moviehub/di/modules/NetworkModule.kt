package com.resulgenc.moviehub.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.resulgenc.moviehub.BuildConfig
import com.resulgenc.moviehub.data.network.interceptors.ConnectionInterceptor
import com.resulgenc.moviehub.data.network.interceptors.HeaderInterceptor
import com.resulgenc.moviehub.data.network.interceptors.QueryParameterInterceptor
import com.resulgenc.moviehub.data.network.services.AllMoviesServices
import com.resulgenc.moviehub.data.network.services.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the base URL for the Retrofit client.
     */
    @Provides
    @Singleton
    fun provideBaseURL(): String = BuildConfig.BASE_URL

    /**
     * Provides a Gson instance with custom configuration for JSON parsing.
     */
    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    // Interceptors

    /**
     * Provides a logging interceptor for debugging purposes.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    /**
     * Provides a header interceptor to add common headers to outgoing requests.
     */
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor(
            hashMapOf(
                Pair("Authorization", "Bearer ${BuildConfig.Token}"),
                Pair("Content-Type", "application/json;charset=utf-8"),
            )
        )
    }

    /**
     * Provides an API key interceptor to add an API key as a query parameter to requests.
     */
    @Provides
    @Singleton
    @Named("ApiKey")
    fun providerApiKeyInterceptor(): QueryParameterInterceptor {
        return QueryParameterInterceptor(
            key = "api_key",
            value = BuildConfig.ApiKey
        )
    }

    /**
     * Provides a connection interceptor to check and handle network connectivity.
     */
    @Provides
    @Singleton
    fun provideConnectionInterceptor(@ApplicationContext context: Context) = ConnectionInterceptor(context)

    // HTTP Client

    /**
     * Provides a customized OkHttpClient with various interceptors and timeouts.
     */
    @Provides
    @Singleton
    fun provideDefaultOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        @Named("ApiKey") apiKeyQueryParameterInterceptor: QueryParameterInterceptor,
        connectionInterceptor: ConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(apiKeyQueryParameterInterceptor)
            .addInterceptor(connectionInterceptor)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides a Retrofit instance with the specified configuration.
     */
    @Provides
    @Singleton
    fun provideDefaultRetrofit(
        baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /**
     * Provides an instance of the [AllMoviesServices] interface created using Retrofit.
     */
    @Provides
    @Singleton
    fun provideAllMoviesServices(retrofit: Retrofit): AllMoviesServices =
        retrofit.create(AllMoviesServices::class.java)

    /**
     * Provides a Retrofit instance with the specified configuration for MovieDetailService.
     */
    @Provides
    @Singleton
    fun provideMovieDetail(retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)
}
