package net.redlinesoft.a14_materialdesign_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceFragment extends Fragment {

    private static final String TAG = "PlaceFragment";
    View view;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public PlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place, container, false);
        // set title
        getActivity().setTitle(getString(R.string.app_name));

        // TODO 10 : bind recyclerview prepare for push data from API

        // Recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_place);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // TODO 11 : Create API call for place list

        // Api call
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Place>> call = apiService.doGetPlaceList();
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                int statusCode = response.code();
                Log.d(TAG, "code = " + statusCode);
                final List<Place> place = response.body();

                adapter = new PlaceAdapter(getContext(), place);
                recyclerView.setAdapter(adapter);

                adapter.SetOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d(TAG, "click " + position);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {

            }
        });

        return view;
    }

}
