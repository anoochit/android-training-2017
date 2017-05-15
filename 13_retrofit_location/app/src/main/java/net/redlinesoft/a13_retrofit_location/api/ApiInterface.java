package net.redlinesoft.a13_retrofit_location.api;

import net.redlinesoft.a13_retrofit_location.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xavier on 5/3/2017 AD.
 */

public interface ApiInterface {

    // get list place
    @GET("place")
    Call<List<Place>> doGetPlaceList();

    // get place nearby geolocation
    @GET("place/near/{lat}/{lon}")
    Call<List<Place>> doGetNearPlaceList(@Path("lat") Double lat,@Path("lon") Double lon);
}
