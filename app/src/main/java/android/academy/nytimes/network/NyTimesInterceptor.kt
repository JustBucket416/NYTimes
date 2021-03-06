package android.academy.nytimes.network

import android.academy.nytimes.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NyTimesInterceptor @Inject constructor()
    : Interceptor {

    companion object {
        private const val REQUEST_HEADER = "api-key"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().addHeader(REQUEST_HEADER, Constants.TOKEN).build()
        return chain.proceed(request)
    }
}