package android.academy.nytimes.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper private constructor(private val url: String,
                                         private val interceptor: Interceptor) {

    companion object {
        @Volatile
        private var INSTANCE: RetrofitHelper? = null

        fun getInstance(url: String, interceptor: Interceptor): RetrofitHelper {
            var localInstance = INSTANCE
            if (localInstance == null) {
                synchronized(RetrofitHelper::class.java) {
                    localInstance = INSTANCE
                    if (localInstance == null) {
                        INSTANCE = RetrofitHelper(url, interceptor)
                        localInstance = INSTANCE
                    }
                }
            }
            return localInstance!!
        }
    }

    fun getNyTimesApi(): NYTimesApi = with(Retrofit.Builder()) {
        baseUrl(url)
        addConverterFactory(GsonConverterFactory.create(createGson()))
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        client(createOkHttpClient())
        build()
    }.create(NYTimesApi::class.java)


    private fun createGson() = with(GsonBuilder()) {
        setLenient()
        serializeNulls()
        create()
    }

    private fun createOkHttpClient() = with(OkHttpClient.Builder()) {
        addInterceptor(interceptor)
        build()
    }
}
