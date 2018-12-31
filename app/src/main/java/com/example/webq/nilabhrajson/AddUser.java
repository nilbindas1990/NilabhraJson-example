package com.example.webq.nilabhrajson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.Map;

import static com.example.webq.nilabhrajson.MainActivity.trimCache;

public class AddUser extends AppCompatActivity {

    EditText add_name , add_email , add_phone;
    Button add_user_btn;
    Button firebase_btn;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        add_name  = findViewById(R.id.add_name);
        add_email  = findViewById(R.id.add_email);
        add_phone  = findViewById(R.id.add_phone);
        add_user_btn  = findViewById(R.id.add_user_btn);
        firebase_btn = findViewById(R.id.fire_btn);


        add_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = add_name.getText().toString();
                String email = add_email.getText().toString();
                String phone = add_phone.getText().toString();
                //Toast.makeText(AddUser.this, "Clicked", Toast.LENGTH_SHORT).show();
                sentAddUserData(name , email , phone);
            }
        });

        //Checking Firebase event System.
//        firebase_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
//                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//            }
//        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void sentAddUserData(final String name, final String email, final String phone) {
        String addUrl  = "https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/create.php";

        RequestQueue add_requestQueue = Volley.newRequestQueue(this);

        StringRequest add_StringRequest = new StringRequest(Request.Method.POST, addUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddUser.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("full_name",name);
                params.put("email",email);
                params.put("phone",phone);
                return params;

            }
        };

        add_requestQueue.add(add_StringRequest);

        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
