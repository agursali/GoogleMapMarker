package com.example.amolgursali.googlemapmarker;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    SupportMapFragment supportMapFragment;
    public static final String BASIC_URL="https://maps.googleapis.com/maps/api/geocode/json?";
    public static final String ADDRESS="address=techweb+center";
    public static final String KEY="&key=AIzaSyAbJMqP9f5ttJkbFBg2EHCA6RhUo2_4XP8";
    GoogleMap googlemap;
    String add="techweb center";
    List<Address> address;
    Geocoder g;
    LatLng l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
//        webService();
    }

    private void initialize()
    {
        supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        googlemap=supportMapFragment.getMap();
        MarkerOptions m=new MarkerOptions();
        try
        {
            g=new Geocoder(getApplicationContext());
            address=g.getFromLocationName(add,5);
            if(address!=null)
            {
                Address a=address.get(0);
                a.getLatitude();
                a.getLongitude();
                l=new LatLng(a.getLatitude(),a.getLongitude());
                LatLng addressreal = l;
                m.position(addressreal);
                Marker marker=googlemap.addMarker(m);
                marker.setTitle("Title");
                googlemap.getUiSettings().setZoomControlsEnabled(true);
                googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(addressreal,16));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

   /* @Override
    public void onMapReady(GoogleMap googleMap)
    {
        LatLng sydney = new LatLng(-33.852, 151.211);
        Geocoder g=new Geocoder(getApplicationContext());

        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/


   /* private void webService()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest s=new StringRequest(Request.Method.GET, BASIC_URL + ADDRESS + KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d("Response",response);
                try
                {
                    JSONObject j=new JSONObject(response);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }*/
}
