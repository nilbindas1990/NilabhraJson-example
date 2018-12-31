package com.example.webq.nilabhrajson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.webq.nilabhrajson.MainActivity.trimCache;

public class UpdateUser extends AppCompatActivity{

    String readURL = "https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/read.php";

    String updateUrl  = "https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/update.php";

    EditText name, email, phone;
    Button update;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        update = findViewById(R.id.update);
        imageView = findViewById(R.id.imageView);

        String path = getApplication().getFilesDir().getAbsolutePath();
        imageView.setImageResource(getResources().getIdentifier("einstien", "drawable", getPackageName()));


//        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
//                .execute("https://upload.wikimedia.org/wikipedia/en/a/a9/Example.jpg");



        final String recivedID = getIntent().getStringExtra("id");

        if(recivedID != null){
            readUserContent(recivedID);
        }

        //Updating the Content.
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String current_name = name.getText().toString();
                String current_email = email.getText().toString();
                String current_phone = phone.getText().toString();

                updateUserContent(recivedID, current_name, current_email, current_phone);

            }
        });

    }

    private void updateUserContent(final String recivedID, final String current_name, final String current_email, final String current_phone) {

        RequestQueue read_RequestQueue = Volley.newRequestQueue(this);

        StringRequest updateRequest = new StringRequest(Request.Method.POST, updateUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UpdateUser.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("id",recivedID);
                params.put("full_name",current_name);
                params.put("email",current_email);
                params.put("phone",current_phone);
                return params;

            }
        };
        read_RequestQueue.add(updateRequest);
    }

    private void readUserContent(String id) {
        RequestQueue read_RequestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, readURL + "?id=" + id ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject inArrayObject = jsonArray.getJSONObject(i);

                                name.setText(inArrayObject.getString("full_name"));
                                email.setText(inArrayObject.getString("email"));
                                phone.setText(inArrayObject.getString("phone"));

                                //Log.d("Intent" , inArrayObject.getString("full_name"));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Log.e("tag", "there is an error");
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        read_RequestQueue.add(stringRequest);

        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
