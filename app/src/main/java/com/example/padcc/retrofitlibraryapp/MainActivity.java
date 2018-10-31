package com.example.padcc.retrofitlibraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  ContactAdapter.ItemClickListener{

    ContactAdapter contactAdapter;
    Contacts contacts;
    private String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        //creating the api interface
        ApiClient api = retrofit.create(ApiClient.class);
        //now making the call object , Here we are using the api method that we created inside the api interface
        Call<Contacts> call = api.getHeroes();
        //then finallly we are making the call using enqueue() it takes callback interface as an argument and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed  if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                //In this point we got our hero list thats damn easy right ;)
                contacts = response.body();

              //  Log.i("tag",contacts.get(0).getName());
                contactAdapter = new ContactAdapter(MainActivity.this, contacts.getContacts());
                contactAdapter.setClickListener(MainActivity.this);
                recyclerView.setAdapter(contactAdapter);
                contactAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onItemClick(View view, int position) {


        Toast.makeText(this, "Clicked " + contactAdapter.getItem(position) + " Row number " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
