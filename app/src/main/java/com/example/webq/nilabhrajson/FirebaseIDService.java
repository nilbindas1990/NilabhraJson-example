package com.example.webq.nilabhrajson;

import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FirebaseIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();

        try {
            sendRegisteredToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRegisteredToken(String token) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("full_name" , "test")
                .add("email" , "test@test.test")
                .add("phone","8888888888")
                .build();

        Request request = new Request.Builder()
                .url("https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/create.php")
                .post(body)
                .build();

        okHttpClient.newCall(request).execute();
    }
}
