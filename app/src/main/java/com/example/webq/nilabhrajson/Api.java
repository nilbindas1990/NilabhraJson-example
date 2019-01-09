package com.example.webq.nilabhrajson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://webqueuesolution.com/samples/projects/sandip/android_development/order/";

    @GET("read.php")
    Call<List<MyList>> getMyList();
}
