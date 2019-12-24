package com.ghandour.bbank.view.activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.ghandour.bbank.R;
import com.ghandour.bbank.data.local.SharedPreferencesManger;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ghandour.bbank.data.local.SharedPreferencesManger.LoadData;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.SaveData;
import static com.ghandour.bbank.data.local.SharedPreferencesManger.loadUserData;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.map_activity_set_location)
    Button mapActivitySetLocation;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().title("my place").position(latLng));
                longitude = latLng.longitude;
                latitude = latLng.latitude;


            }
        });


        // Add a marker in Sydney and move the camera

    }

    @OnClick(R.id.map_activity_set_location)
    public void onViewClicked() {
        try {
            getLocation(latitude,longitude);
        }catch (Exception e){
            e.printStackTrace();
        }
        finish();
    }

    private void getLocation(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
        if (geocoder.isPresent()) {
            List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
            Address address = addressList.get(0);
            String loc=address.getAddressLine(0);
            Toast.makeText(this, loc, Toast.LENGTH_SHORT).show();
            SaveData(MapsActivity.this,"LOCATION",loc);
            Toast.makeText(this, LoadData(this,"LOCATION"), Toast.LENGTH_SHORT).show();

            SaveData(MapsActivity.this,"LATITUDE",latitude);
            SaveData(MapsActivity.this,"LONGITUDE",longitude);


        }
    }
}
