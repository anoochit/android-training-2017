package net.redlinesoft.a14_materialdesign_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        // TODO 11 : Create API call for place list



        return view;
    }

}
