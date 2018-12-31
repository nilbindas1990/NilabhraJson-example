package com.example.webq.nilabhrajson;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//Author : Nilabhra Chakraboty
public class MainActivity extends AppCompatActivity {

    String url = "https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/read.php";
    ListView listView;

    List<MyJson> myJsonList;

    FloatingActionButton add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        add_button = findViewById(R.id.add_button);

        myJsonList = new ArrayList<>();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this , AddUser.class);
                startActivity(addIntent);
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject inArrayObject = jsonArray.getJSONObject(i);

                                MyJson newJsonObject = new MyJson(inArrayObject.getString("id"),inArrayObject.getString("full_name"),inArrayObject.getString("email"));

                                myJsonList.add(newJsonObject);
                            }

                            //Custom adapter object
                            MyListAdapter myListAdapter = new MyListAdapter(myJsonList, getApplicationContext());



                            listView.setAdapter(myListAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("tag", "there is an error");
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Clearing Cache
    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

}
