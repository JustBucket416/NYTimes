package android.academy.nytimes.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitHelper private constructor(private val url: String,
                                         private val token: String) : Interceptor {

    companion object {
        private const val REQUEST_HEADER = "api-key"
        @Volatile
        private var INSTANCE: RetrofitHelper? = null

        fun getInstance(url: String, token: String): RetrofitHelper {
            var localInstance = INSTANCE
            if (localInstance == null) {
                synchronized(RetrofitHelper::class.java) {
                    localInstance = INSTANCE
                    if (localInstance == null) {
                        INSTANCE = RetrofitHelper(url, token)
                        localInstance = INSTANCE
                    }
                }
            }
            return localInstance!!
        }
    }

    fun getNyTimesApi(): NYTimesApi {
        val gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

        val client = OkHttpClient.Builder()
                .addInterceptor(this)
                .build()

        return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(NYTimesApi::class.java)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().addHeader(REQUEST_HEADER, token).build()
        return chain.proceed(request)
    }
}
