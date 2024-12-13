package com.ecommerce.demo.di

import com.ecommerce.demo.app.viewmodels.ProductViewModel
import com.ecommerce.demo.app.viewmodels.CartViewModel
import com.ecommerce.demo.data.CartRepositoryImpl
import com.ecommerce.demo.data.rapid.RapidDataSource
import com.ecommerce.demo.data.local.*
import com.ecommerce.demo.data.rapid.ProductApi
import com.ecommerce.demo.data.ProductRepositoryImpl
import com.ecommerce.demo.domain.*
import com.ecommerce.demo.domain.repositories.CartRepository
import com.ecommerce.demo.domain.repositories.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun dataModule(apiKey: String) = module {

    single {
        val client = OkHttpClient().newBuilder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "real-time-amazon-data.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }

        Retrofit.Builder()
            .baseUrl("https://real-time-amazon-data.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ProductApi::class.java)
    }

    singleOf(::RapidDataSource)
    singleOf(::LocalDataSource)
    singleOf(::MockDataSource)
    factory<ProductRepository> { ProductRepositoryImpl(get()) }
    factory<CartRepository> { CartRepositoryImpl(get()) }

}

val domainModule = module {
    factoryOf(::SearchProductUseCase)
    factoryOf(::GetProductInfoUseCase)
    factoryOf(::SaveProductToCartUseCase)
    factoryOf(::ClearCartUseCase)
    factoryOf(::GetCartUseCase)
}


val appModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::CartViewModel)
}
