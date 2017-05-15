package net.redlinesoft.a13_retrofit_location.activity;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import net.redlinesoft.a13_retrofit_location.api.ApiClient;
import net.redlinesoft.a13_retrofit_location.api.ApiInterface;
import net.redlinesoft.a13_retrofit_location.R;
import net.redlinesoft.a13_retrofit_location.adapter.PlaceAdapter;
import net.redlinesoft.a13_retrofit_location.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private static final String TAG = "MainFragment";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Double locLat=13.90;
    Double locLon=100.55;

    public MainFragment() {
        // Required empty public constructor
    }

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);


        try {
            // extract value from bundle
            this.locLat=getArguments().getDouble("locLat");
            this.locLon=getArguments().getDouble("locLon");
            Log.d(TAG,"loc value = " + String.valueOf(locLat) + "," + String.valueOf(locLon));
        } catch (Exception e) {
            Log.d(TAG,e.getMessage());
        }

        // Progress bar
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        // Recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_place);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Api call
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Place>> call = apiService.doGetNearPlaceList(locLat,locLon);
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                int statusCode = response.code();
                Log.d("TAG", "code = " + statusCode);
                List<Place> place = response.body();

                adapter = new PlaceAdapter(place);
                recyclerView.setAdapter(adapter);

                // hide progress bar
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Log.d("TAG", "" + t.getMessage());
            }
        });

        return view;
    }

}
