package net.redlinesoft.a14_materialdesign_demo;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private static final String TAG = "NearbyFragment";
    private static final int REQ_FINE_LOCATION = 100;
    private View view;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location location;

    // Mock location
    private Double locLat = 13.90;
    private Double locLon = 100.55;


    public NearbyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nearby, container, false);

        // set title bar
        getActivity().setTitle(getString(R.string.label_place_nearby));

        // setup GoogleAPI for location service
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_place_neary);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    // TODO 19 : call place nearby and update ui
    public void updateUI(Double locLat, Double locLon) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // location request
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(3 * 1000)        // 3 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_FINE_LOCATION);
            }
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            Log.d(TAG, "old location data -> " + location.getLatitude() + "," + location.getLongitude());
            // call rest api and update ui
            this.locLat = location.getLatitude();
            this.locLon = location.getLongitude();
            updateUI(locLat, locLon);
        } else {
            // location is not available use default location
            updateUI(locLat, locLon);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQ_FINE_LOCATION:
                Log.d(TAG, "fine location result ");
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection Failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "new location data -> " + location.getLatitude() + "," + location.getLongitude());
        // call rest api and update ui
        this.locLat = location.getLatitude();
        this.locLon = location.getLongitude();
        updateUI(locLat, locLon);
    }
}
