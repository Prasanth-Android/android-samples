package com.example.navya.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {
    LatLng location;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        double latitude = Double.parseDouble(getIntent().getStringExtra("lat"));
        double longitude = Double.parseDouble(getIntent().getStringExtra("lon"));
        location = new LatLng(latitude , longitude);

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
//            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(location).title(getIntent().getStringExtra("title")));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 10);
            googleMap.animateCamera(cameraUpdate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
