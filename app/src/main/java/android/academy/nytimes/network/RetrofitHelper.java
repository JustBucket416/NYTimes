package android.academy.nytimes.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";
    private static volatile RetrofitHelper INSTANCE;
    static final String TOKEN = "aae03167237f4fbf836838d292db06a8";

    private RetrofitHelper() {
    }

    public static RetrofitHelper getInstance() {
        RetrofitHelper localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (RetrofitHelper.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new RetrofitHelper();
                }
            }
        }
        return localInstance;
    }

    NYTimesApi getNYTimesApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NYTimesApi.class);
    }
}
