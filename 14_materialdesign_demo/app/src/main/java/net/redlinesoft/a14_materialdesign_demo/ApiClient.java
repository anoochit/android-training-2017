package net.redlinesoft.a14_materialdesign_demo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // TODO 2 : Change base url to your api endpoint.
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
