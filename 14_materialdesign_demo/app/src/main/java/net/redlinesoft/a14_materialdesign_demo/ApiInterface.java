package net.redlinesoft.a14_materialdesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    // TODO 3 : add request method for place list and nearby place list.

    // get list place
    @GET("place")
    Call<List<Place>> doGetPlaceList();

    // get place nearby geolocation
    @GET("place/near/{lat}/{lon}")
    Call<List<Place>> doGetNearPlaceList(@Path("lat") Double lat,@Path("lon") Double lon);
}
