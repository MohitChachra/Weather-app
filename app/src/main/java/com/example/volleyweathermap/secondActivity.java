package com.example.volleyweathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleyweathermap.Custom.OnCitySelectListener;
import com.example.volleyweathermap.Custom.customdialog;
import com.example.volleyweathermap.schema.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Queue;

public class secondActivity extends AppCompatActivity  {
ImageView image;
TextView temp,tempunit,city,weather,humidity,pressure;
customdialog dialog;
String[] cities={
        "Rohtak",
        "Patna",
        "Agartala",
        "Kohima",
        "Chandigarh",
        "Ranchi",
        "Thiruvananthapuram",
        "Mumbai",
        "Bhubaneswar",
        "Panaji",
        "Gandhinagar",
        "Dispur",
        "Amaravati",
        "Dehradun",
        "Shimla",
        "Bhopal",
        "Jaipur",
        "Aizawl",
        "Srinagar",
        "Kolkata",
        "Raipur",
        "Chennai",
        "Lucknow",
        "Gangtok",
        "Itanagar",
        "Bangalore",
        "Shillong",
        "Imphal"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        image = findViewById(R.id.weatherImage);
        temp = findViewById(R.id.temp);
        tempunit = findViewById(R.id.tempunit);
        weather = findViewById(R.id.wea);
        humidity = findViewById(R.id.hum);
        pressure = findViewById(R.id.press);
        city = findViewById(R.id.city);
        //dialog=new Dialog(this);
        //dialog.setContentView(R.layout.dialog);
        //dialog.setCancelable(true);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dialog = new customdialog(secondActivity.this, R.layout.dialog, cities, new OnCitySelectListener() {
            @Override
            public void OnSelected(int position) {
                dialog.dismiss();
            fechdata(cities[position]);
            }

        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

public void fechdata(String citys){
            String URL = "http://api.openweathermap.org/data/2.5/weather?q="+citys+",india&APPID=254509702eb8ae7d21135710e0af82c5";

            final Gson gson = new GsonBuilder().create();


            StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Data d = gson.fromJson(response, Data.class);
                    Toast.makeText(secondActivity.this, "" + d.getCoord().getLat(), Toast.LENGTH_SHORT).show();
                    temp.setText("" + (int) (d.getMain().getTemp() - 273));
                    city.setText(d.getName());
                    String we = d.getWeather().get(0).getMain();
                    weather.setText(we);
                    humidity.setText("" + d.getMain().getHumidity());
                    pressure.setText("" + d.getMain().getPressure() + " P");


                    //setting images
                    if (we.equals("Haze")) {
                        image.setImageResource(R.drawable.haze);
                    }
                    if (we.equals("Dust")) {
                        image.setImageResource(R.drawable.sadcloud);
                    }
                    if (we.equals("Rain")) {
                        image.setImageResource(R.drawable.rain);
                    }
                    if (we.equals("Wind")) {
                        image.setImageResource(R.drawable.windy);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }

