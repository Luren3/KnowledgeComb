package com.magicfrost.knowledgecomb.http.api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

/**
 * Created by MagicFrost.
 */
public interface Api {

    @GET("users/HellForGate/repos")
    Call<List<Map<String,Object>>> test();
}
