package net.redlinesoft.a13_retrofit_location.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xavier on 5/3/2017 AD.
 */

public class ApiClient {

    public static final String BASE_URL="https://warm-ridge-87805.herokuapp.com/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
