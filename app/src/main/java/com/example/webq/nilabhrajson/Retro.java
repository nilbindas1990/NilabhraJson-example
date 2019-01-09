package com.example.webq.nilabhrajson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro);

        getMyList();
    }

    private void getMyList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<MyList>> call = api.getMyList();

        call.enqueue(new Callback<List<MyList>>() {
            @Override
            public void onResponse(Call<List<MyList>> call, Response<List<MyList>> response) {


                List<MyList> lists = response.body();

                ArrayList<MyList> arrayList = new ArrayList<>(lists);

//                String[] list = new String[lists.size()];
//
//                //looping through all the heroes and inserting the names inside the string array
//                for (int i = 0; i < lists.size(); i++) {
//                    list[i] = lists.get(i).getId();
//                    //Log.d("order", list[i]);
//                }
                Log.d("order" , String.valueOf(arrayList));

                if (response.isSuccessful()) {
                    Toast.makeText(Retro.this, "run", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyList>> call, Throwable t) {
                Log.d("Rerror", t.getMessage());
            }
        });



    }
}
