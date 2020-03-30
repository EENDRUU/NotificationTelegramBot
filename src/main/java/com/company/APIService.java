package com.company;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.*;

public interface APIService {

    @FormUrlEncoded
    @POST("postChatID.php")
    Call<UserTelegram> addUser(
            @Field("chat_id") String chat_id,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("getChatID.php")
    Call<List<UserTelegram>> getEmployee();
}
