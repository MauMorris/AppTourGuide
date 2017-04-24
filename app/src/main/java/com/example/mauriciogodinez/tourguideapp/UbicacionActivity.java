package com.example.mauriciogodinez.tourguideapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class UbicacionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient mApiClient;
    String titlePlace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (googlePlayServicesAvailable()) {
            setContentView(R.layout.activity_ubicacion);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(UbicacionActivity.this);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent place = getIntent();
        titlePlace = place.getStringExtra("ubicacion");
    }

    private boolean googlePlayServicesAvailable() {
        GoogleApiAvailability gaa = GoogleApiAvailability.getInstance();
        int isAvailable = gaa.isGooglePlayServicesAvailable(UbicacionActivity.this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (gaa.isUserResolvableError(isAvailable)) {
            Dialog dialog = gaa.getErrorDialog(UbicacionActivity.this, isAvailable, 0);
            dialog.show();
            return false;
        } else {
            Toast.makeText(UbicacionActivity.this, "cannot connect to Google", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

        if(mMap != null){
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_map_ubicacion, null);
                    TextView locally = (TextView) v.findViewById(R.id.text_view_ubicacion_locally);
                    TextView latitud = (TextView) v.findViewById(R.id.text_view_latitud);
                    TextView longitud = (TextView) v.findViewById(R.id.text_view_longitud);
                    TextView snipet = (TextView) v.findViewById(R.id.text_view_snipet);

                    LatLng xy = marker.getPosition();
                    locally.setText(marker.getTitle());
                    latitud.setText("Latitud: " + xy.latitude);
                    longitud.setText("Longitud: " + xy.longitude);
                    snipet.setText(marker.getSnippet());

                    return v;
                }
            });
            mMap.setMyLocationEnabled(true);
        }

        goToZoom(19.435731, -99.140243, 12);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.type_of_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.map_none:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.map_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.map_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.map_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goTo(double x, double y) {
        // Add a marker in Sydney and move the camera
        LatLng xy = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(xy).title("Marker in X"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(xy));
    }

    private void geolocate() throws IOException {
        Geocoder gc = new Geocoder(UbicacionActivity.this);
        List<Address> list = gc.getFromLocationName("Mexico", 1);
        Address address = list.get(0);
        String locally = address.getLocality();
        Toast.makeText(UbicacionActivity.this, locally, Toast.LENGTH_SHORT).show();
        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToZoom(lat, lng, 13);
    }

    private void goToZoom(double x, double y, float z) {
        LatLng xy = new LatLng(x, y);
        MarkerOptions mMarker = new MarkerOptions().
                title(titlePlace).
                position(xy).
                snippet("Museos");

        mMap.addMarker(mMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(xy, z));
    }
}
