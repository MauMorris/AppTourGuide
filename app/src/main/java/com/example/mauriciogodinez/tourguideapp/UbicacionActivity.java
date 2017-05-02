package com.example.mauriciogodinez.tourguideapp;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionApi;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UbicacionActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, ResultCallback<Status> {

    private GoogleMap mMap;
    String titlePlace = "";

    private GoogleApiClient mApiClient;
    private GoogleApiClient mRecognitionApiClient;
    private LocationRequest mLocationRequest;

    private Location mLastLocation;

    private final String LOG_TAG = "testTourTag";
    private TextView location_text;
    private boolean mRequestingLocationUpdates = true;
    private String REQUESTING_LOCATION_UPDATES_KEY = "location_request";
    private String LOCATION_KEY = "key request";
    protected static final String TAG="activity";
    protected ActivityDetectionBroadcastReceiver mBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        if (googlePlayServicesAvailable()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(UbicacionActivity.this);

            buildGoogleApiClient();
        }

        location_text = (TextView) findViewById(R.id.text_view_location);

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
        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        updateValuesFromBundle(savedInstanceState);

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

    private void buildActivityRecognition(){
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                mApiClient,
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);

    }

    private void buildGoogleApiClient() {
        mApiClient = new GoogleApiClient.Builder(UbicacionActivity.this)
                .addApi(LocationServices.API)
//                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(UbicacionActivity.this)
                .addOnConnectionFailedListener(UbicacionActivity.this)
                .build();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

        if (mMap != null) {
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

                    String location = "Latitud: " + xy.latitude;
                    latitud.setText(location);

                    location = "Longitud: " + xy.longitude;
                    longitud.setText(location);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mLastLocation);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and
            // make sure that the Start Updates and Stop Updates buttons are
            // correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
            }
            // Update the value of mCurrentLocation from the Bundle and update the
            // UI to show the correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the Bundle, we can be sure that
                // mCurrentLocationis not null.
                mLastLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }
            if (mLastLocation != null) {
                updateUi(mLastLocation.toString());
            }
        }
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

        CameraPosition cp = CameraPosition.builder()
                .target(xy)
                .zoom(z)
//                .bearing(z)
//                .tilt(z)
                .build();

        mMap.addMarker(mMarker);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp), 3000, null);
//        mMap.addPolyline(new PolylineOptions().geodesic(true).add(xy).add(xy));
//        mMap.addCircle(new CircleOptions().center(xy)
//                .radius(5000).clickable(true).strokeColor(Color.GREEN)
//        .fillColor(Color.argb(64,0,255,0)));
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, DetectedActivitiesIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // requestActivityUpdates() and removeActivityUpdates().
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Log.e(TAG, "Successfully added activity detection.");

        } else {
            Log.e(TAG, "Error adding or removing activity detection: " + status.getStatusMessage());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
                new IntentFilter(Constants.BROADCAST_ACTION));
//        buildActivityRecognition();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mApiClient.isConnected())
            mApiClient.disconnect();
//        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
//                mApiClient,
//                getActivityDetectionPendingIntent()
//        ).setResultCallback(this);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mApiClient, mLocationRequest, this);
        mRequestingLocationUpdates = false;
    }

    private void stopLocationUpdates() {
        mRequestingLocationUpdates = true;
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mApiClient, this);
    }

    private void startLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        //solo obtenemos una ubicacion (la ultima proporcionada por el telefono)
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
    }

    private void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
    }

    private void updateUi(String location) {
        location_text.setText(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        createLocationRequest();

        startLastLocation();

        if (mLastLocation != null) {
            updateUi(mLastLocation.toString());
        }

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(LOG_TAG, "Connection suspended: " + i);
        mApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(LOG_TAG, "Connection failed: " + connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        updateUi(mLastLocation.toString());
//        goToZoom(location.getLatitude(), location.getLongitude(), 12);
    }

    public String getActivityString(int detectedActivityType) {
        Resources resources = this.getResources();
        switch(detectedActivityType) {
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);
            default:
                return resources.getString(R.string.unidentifiable_activity, detectedActivityType);
        }
    }

    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver{
        protected static final String TAG = "receiver";


        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<DetectedActivity> updatedActivities =
                    intent.getParcelableArrayListExtra(Constants.ACTIVITY_EXTRA);

            String strStatus = "";
            for(DetectedActivity thisActivity: updatedActivities){
                strStatus +=  getActivityString(thisActivity.getType()) + thisActivity.getConfidence() + "%\n";
            }
            Toast.makeText(UbicacionActivity.this, strStatus, Toast.LENGTH_SHORT).show();
        }
    }

}
