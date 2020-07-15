package com.example.arviewtestapp.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api
{
    private static final Api ourInstance = new Api();
    private Retrofit retrofit;
    private static final String baseURL = "https://api.twitch.tv/kraken/";

    public static Api getInstance()
    {
        return ourInstance;
    }

    private Api()
    {
        retrofit = new Retrofit.Builder().baseUrl(baseURL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public APIrequests getApi()
    {
        return retrofit.create(APIrequests.class);
    }
}
