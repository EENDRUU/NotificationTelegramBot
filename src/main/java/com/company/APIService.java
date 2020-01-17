package com.company;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    //Send message from Telegram group to e.g. my website
    @GET("konsumen")
    Call<List<Customers>> getData();

}
