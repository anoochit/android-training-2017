package net.redlinesoft.a13_retrofit.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.redlinesoft.a13_retrofit.api.ApiClient;
import net.redlinesoft.a13_retrofit.api.ApiInterface;
import net.redlinesoft.a13_retrofit.model.Place;
import net.redlinesoft.a13_retrofit.adapter.PlaceAdapter;
import net.redlinesoft.a13_retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_place);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        // Api call
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Place>> call = apiService.doGetPlaceList();
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                int statusCode = response.code();
                Log.d("TAG", "code = " + statusCode);
                List<Place> place = response.body();

                adapter = new PlaceAdapter(place);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Log.d("TAG", "" + t.getMessage());
            }
        });



        return view;
    }

}
