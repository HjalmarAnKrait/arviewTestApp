package com.example.arviewtestapp.Networking;

import com.example.arviewtestapp.POJO.GamePOJO;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIrequests
{
    @GET("games/top")
    Call<JsonElement> getGameList(@Header("Client-ID") String client_id,@Header("Accept") String accept, @Query("limit") int limit);
}
