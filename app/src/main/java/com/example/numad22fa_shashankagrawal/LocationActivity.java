package com.example.numad22fa_shashankagrawal;
import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.text.MessageFormat;

public class LocationActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private TextView locationTextView;
    private TextView distanceTextView;
    private LocationRequest locationRequest;
    private double latitude = -91;
    private double longitude = -181;
    private double distance = 0;
    private LocationCallback locationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationTextView = findViewById(R.id.locationText);
        distanceTextView = findViewById(R.id.distanceText);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (savedInstanceState != null) {
            latitude = savedInstanceState.getDouble("latitude");
            longitude = savedInstanceState.getDouble("longitude");
            locationTextView.setText(MessageFormat.format("Lat: {0}\nLong: {1}", latitude,
                    longitude));
            distance = savedInstanceState.getDouble("distance");
            distanceTextView.setText("Distance: " + distance + "m");
        }
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        } else {
            instantiateLocation();
        }
    }

    public void instantiateLocation () {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Instantiating the Location request and setting the priority and the interval I need to update the location.
            locationRequest = locationRequest.create();
            locationRequest.setInterval(50);
            locationRequest.setFastestInterval(50);
            locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

            //instantiating the LocationCallBack
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null) {
                        if (locationResult == null) {
                            return;
                        }
                        //Showing the latitude, longitude and accuracy on the home screen.
                        for (Location location : locationResult.getLocations()) {
                            if (latitude != -91 && longitude != -181) {
                                distance += getDistance(latitude, location.getLatitude(), longitude, location.getLongitude());
                            }
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            distanceTextView.setText("Distance: " + String.format("%.2f", distance) + "m");
                            locationTextView.setText(MessageFormat.format("Lat: {0}\nLong: {1}", location.getLatitude(),
                                    location.getLongitude()));
                        }
                    }
                }
            };
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    public void resetDistance(View view) {
        distance = 0;
        distanceTextView.setText("Distance: " + String.format("%.2f", distance) + "m");
        latitude = -91;
        longitude = -181;
    }

    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                instantiateLocation();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Permissions Required")
                        .setMessage("Location permissions are required to continue using this feature.")
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Continue", null)
                        .show();
            }
        }
    }
    
    public double getDistance(double lat1, double lat2, double lon1, double lon2) {
        int R = 6371000; // metres
        double φ1 = lat1 * Math.PI/180; // φ, λ in radians
        double φ2 = lat2 * Math.PI/180;
        double Δφ = (lat2-lat1) * Math.PI/180;
        double Δλ = (lon2-lon1) * Math.PI/180;
        
        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                        Math.cos(φ1) * Math.cos(φ2) *
                                Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c; // in metres
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        bundle.putDouble("distance", distance);
    }

    public void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}





