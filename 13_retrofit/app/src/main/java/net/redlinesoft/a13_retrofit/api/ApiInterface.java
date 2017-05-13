package net.redlinesoft.a13_retrofit.api;

import net.redlinesoft.a13_retrofit.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xavier on 5/3/2017 AD.
 */

public interface ApiInterface {

    //@GET("data02.json")
    @GET("place")
    Call<List<Place>> doGetPlaceList();
}
